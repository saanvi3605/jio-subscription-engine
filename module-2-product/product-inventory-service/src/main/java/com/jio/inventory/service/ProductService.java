package com.jio.inventory.service;

import com.jio.inventory.client.CatalogClient;
import com.jio.inventory.client.CustomerClient;
import com.jio.inventory.client.UsageClient;
import com.jio.inventory.model.Product;
import com.jio.inventory.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final UsageClient usageClient;
    private final CustomerClient customerClient;
    private final CatalogClient catalogClient;

    public ProductService(ProductRepository repository,
                          UsageClient usageClient,
                          CustomerClient customerClient,
                          CatalogClient catalogClient) {
        this.repository    = repository;
        this.usageClient   = usageClient;
        this.customerClient = customerClient;
        this.catalogClient  = catalogClient;
    }

    /**
     * Create a new product inventory item.
     *
     * Wire B: validates customerId exists in TMF629.
     * Wire C: validates productOfferingId exists in TMF620 (if provided).
     * Wire 3: after saving, auto-initialises a ProductUsage tracker in TMF635.
     */
    public Product create(Product product) {
        // Wire B: validate customer exists in TMF629
        if (!customerClient.customerExists(product.getCustomerId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerId '" + product.getCustomerId() +
                "' does not exist in Customer Management (TMF629)");
        }

        // Wire C: validate productOffering exists in TMF620 (only if provided)
        if (product.getProductOfferingId() != null && !product.getProductOfferingId().isBlank()) {
            if (!catalogClient.offeringExists(product.getProductOfferingId())) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "productOfferingId '" + product.getProductOfferingId() +
                    "' does not exist in Product Catalog (TMF620). " +
                    "Create the offering first at POST /tmf-api/productCatalogManagement/v4/productOffering");
            }
        }

        product.setCreatedAt(OffsetDateTime.now());
        if (product.getStatus() == null || product.getStatus().isBlank()) {
            product.setStatus("active");
        }
        if (product.getStartDate() == null) {
            product.setStartDate(OffsetDateTime.now());
        }
        Product saved = repository.save(product);

        // Wire 3: auto-create a ProductUsage tracker in TMF635
        if ("active".equals(saved.getStatus())) {
            usageClient.initProductUsage(saved);
        }

        return saved;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Product> findByCustomerIdAndStatus(String customerId, String status) {
        return repository.findByCustomerIdAndStatus(customerId, status);
    }

    @Transactional(readOnly = true)
    public List<Product> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Product> findByProductOrderId(String productOrderId) {
        return repository.findByProductOrderId(productOrderId);
    }

    /**
     * PATCH — only update fields that are present in the request body.
     */
    public Optional<Product> patch(String id, Product patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getName()                 != null) existing.setName(patch.getName());
            if (patch.getStatus()               != null) existing.setStatus(patch.getStatus());
            if (patch.getStatusReason()         != null) existing.setStatusReason(patch.getStatusReason());
            if (patch.getDescription()          != null) existing.setDescription(patch.getDescription());
            if (patch.getStartDate()            != null) existing.setStartDate(patch.getStartDate());
            if (patch.getTerminationDate()      != null) existing.setTerminationDate(patch.getTerminationDate());
            if (patch.getCustomerName()         != null) existing.setCustomerName(patch.getCustomerName());
            if (patch.getProductOfferingId()    != null) existing.setProductOfferingId(patch.getProductOfferingId());
            if (patch.getProductOfferingName()  != null) existing.setProductOfferingName(patch.getProductOfferingName());
            if (patch.getProductSerialNumber()  != null) existing.setProductSerialNumber(patch.getProductSerialNumber());
            if (!patch.getProductCharacteristic().isEmpty()) existing.setProductCharacteristic(patch.getProductCharacteristic());
            existing.setUpdatedAt(OffsetDateTime.now());
            return repository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
