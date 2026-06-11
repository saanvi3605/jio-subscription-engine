package org.openapitools.api;

import org.openapitools.model.ProductSpecification;
import org.openapitools.model.ProductSpecificationCreate;
import org.openapitools.model.ProductSpecificationUpdate;
import org.openapitools.repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.lang.Nullable;

import jakarta.validation.Valid;
import jakarta.annotation.Generated;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
@Controller
@RequestMapping("${openapi.productCatalogManagement.base-path:/tmf-api/productCatalogManagement/v4}")
public class ProductSpecificationApiController implements ProductSpecificationApi {

    private final NativeWebRequest request;
    private final ProductSpecificationRepository repository;

    @Autowired
    public ProductSpecificationApiController(NativeWebRequest request, ProductSpecificationRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ProductSpecification> createProductSpecification(
            @Valid @RequestBody ProductSpecificationCreate productSpecification) {
        ProductSpecification entity = new ProductSpecification();
        entity.setBrand(productSpecification.getBrand());
        entity.setDescription(productSpecification.getDescription());
        entity.setIsBundle(productSpecification.getIsBundle());
        entity.setLifecycleStatus(productSpecification.getLifecycleStatus());
        entity.setName(productSpecification.getName());
        entity.setProductNumber(productSpecification.getProductNumber());
        entity.setVersion(productSpecification.getVersion());
        entity.setLastUpdate(OffsetDateTime.now());
        entity.setAttachment(productSpecification.getAttachment());
        entity.setBundledProductSpecification(productSpecification.getBundledProductSpecification());
        entity.setProductSpecCharacteristic(productSpecification.getProductSpecCharacteristic());
        entity.setProductSpecificationRelationship(productSpecification.getProductSpecificationRelationship());
        entity.setRelatedParty(productSpecification.getRelatedParty());
        entity.setResourceSpecification(productSpecification.getResourceSpecification());
        entity.setServiceSpecification(productSpecification.getServiceSpecification());
        entity.setTargetProductSchema(productSpecification.getTargetProductSchema());
        entity.setValidFor(productSpecification.getValidFor());
        entity.setAtBaseType(productSpecification.getAtBaseType());
        entity.setAtType(productSpecification.getAtType());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @Override
    public ResponseEntity<List<ProductSpecification>> listProductSpecification(
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields,
            @Valid @RequestParam(value = "offset", required = false) @Nullable Integer offset,
            @Valid @RequestParam(value = "limit", required = false) @Nullable Integer limit) {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<ProductSpecification> retrieveProductSpecification(
            @PathVariable("id") String id,
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProductSpecification> patchProductSpecification(
            @PathVariable("id") String id,
            @Valid @RequestBody ProductSpecificationUpdate productSpecification) {
        return repository.findById(id).map(existing -> {
            if (productSpecification.getBrand() != null)
                existing.setBrand(productSpecification.getBrand());
            if (productSpecification.getDescription() != null)
                existing.setDescription(productSpecification.getDescription());
            if (productSpecification.getIsBundle() != null)
                existing.setIsBundle(productSpecification.getIsBundle());
            if (productSpecification.getLifecycleStatus() != null)
                existing.setLifecycleStatus(productSpecification.getLifecycleStatus());
            if (productSpecification.getName() != null)
                existing.setName(productSpecification.getName());
            if (productSpecification.getProductNumber() != null)
                existing.setProductNumber(productSpecification.getProductNumber());
            if (productSpecification.getVersion() != null)
                existing.setVersion(productSpecification.getVersion());
            if (productSpecification.getValidFor() != null)
                existing.setValidFor(productSpecification.getValidFor());
            if (productSpecification.getTargetProductSchema() != null)
                existing.setTargetProductSchema(productSpecification.getTargetProductSchema());
            if (!productSpecification.getAttachment().isEmpty())
                existing.setAttachment(productSpecification.getAttachment());
            if (!productSpecification.getBundledProductSpecification().isEmpty())
                existing.setBundledProductSpecification(productSpecification.getBundledProductSpecification());
            if (!productSpecification.getProductSpecCharacteristic().isEmpty())
                existing.setProductSpecCharacteristic(productSpecification.getProductSpecCharacteristic());
            if (!productSpecification.getProductSpecificationRelationship().isEmpty())
                existing.setProductSpecificationRelationship(productSpecification.getProductSpecificationRelationship());
            if (!productSpecification.getRelatedParty().isEmpty())
                existing.setRelatedParty(productSpecification.getRelatedParty());
            if (!productSpecification.getResourceSpecification().isEmpty())
                existing.setResourceSpecification(productSpecification.getResourceSpecification());
            if (!productSpecification.getServiceSpecification().isEmpty())
                existing.setServiceSpecification(productSpecification.getServiceSpecification());
            existing.setLastUpdate(OffsetDateTime.now());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteProductSpecification(@PathVariable("id") String id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
