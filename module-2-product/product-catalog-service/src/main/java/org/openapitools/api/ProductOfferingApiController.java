package org.openapitools.api;

import org.openapitools.model.ProductOffering;
import org.openapitools.model.ProductOfferingCreate;
import org.openapitools.model.ProductOfferingUpdate;
import org.openapitools.repository.ProductOfferingRepository;
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
public class ProductOfferingApiController implements ProductOfferingApi {

    private final NativeWebRequest request;
    private final ProductOfferingRepository repository;

    @Autowired
    public ProductOfferingApiController(NativeWebRequest request, ProductOfferingRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ProductOffering> createProductOffering(
            @Valid @RequestBody ProductOfferingCreate productOffering) {
        ProductOffering offering = new ProductOffering();
        offering.setName(productOffering.getName());
        offering.setDescription(productOffering.getDescription());
        offering.setIsBundle(productOffering.getIsBundle());
        offering.setIsSellable(productOffering.getIsSellable());
        offering.setLifecycleStatus(productOffering.getLifecycleStatus());
        offering.setStatusReason(productOffering.getStatusReason());
        offering.setVersion(productOffering.getVersion());
        offering.setLastUpdate(OffsetDateTime.now());
        offering.setAgreement(productOffering.getAgreement());
        offering.setAttachment(productOffering.getAttachment());
        offering.setBundledProductOffering(productOffering.getBundledProductOffering());
        offering.setCategory(productOffering.getCategory());
        offering.setChannel(productOffering.getChannel());
        offering.setMarketSegment(productOffering.getMarketSegment());
        offering.setPlace(productOffering.getPlace());
        offering.setProdSpecCharValueUse(productOffering.getProdSpecCharValueUse());
        offering.setProductOfferingPrice(productOffering.getProductOfferingPrice());
        offering.setProductOfferingTerm(productOffering.getProductOfferingTerm());
        offering.setProductSpecification(productOffering.getProductSpecification());
        offering.setResourceCandidate(productOffering.getResourceCandidate());
        offering.setServiceCandidate(productOffering.getServiceCandidate());
        offering.setServiceLevelAgreement(productOffering.getServiceLevelAgreement());
        offering.setValidFor(productOffering.getValidFor());
        offering.setAtBaseType(productOffering.getAtBaseType());
        offering.setAtSchemaLocation(productOffering.getAtSchemaLocation());
        offering.setAtType(productOffering.getAtType());
        ProductOffering saved = repository.save(offering);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<ProductOffering>> listProductOffering(
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields,
            @Valid @RequestParam(value = "offset", required = false) @Nullable Integer offset,
            @Valid @RequestParam(value = "limit", required = false) @Nullable Integer limit) {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<ProductOffering> retrieveProductOffering(
            @PathVariable("id") String id,
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProductOffering> patchProductOffering(
            @PathVariable("id") String id,
            @Valid @RequestBody ProductOfferingUpdate productOffering) {
        return repository.findById(id).map(existing -> {
            if (productOffering.getName() != null)            existing.setName(productOffering.getName());
            if (productOffering.getDescription() != null)    existing.setDescription(productOffering.getDescription());
            if (productOffering.getIsBundle() != null)       existing.setIsBundle(productOffering.getIsBundle());
            if (productOffering.getIsSellable() != null)     existing.setIsSellable(productOffering.getIsSellable());
            if (productOffering.getLifecycleStatus() != null) existing.setLifecycleStatus(productOffering.getLifecycleStatus());
            if (productOffering.getStatusReason() != null)   existing.setStatusReason(productOffering.getStatusReason());
            if (productOffering.getVersion() != null)        existing.setVersion(productOffering.getVersion());
            if (productOffering.getValidFor() != null)       existing.setValidFor(productOffering.getValidFor());
            if (productOffering.getProductSpecification() != null) existing.setProductSpecification(productOffering.getProductSpecification());
            if (!productOffering.getCategory().isEmpty())    existing.setCategory(productOffering.getCategory());
            if (!productOffering.getChannel().isEmpty())     existing.setChannel(productOffering.getChannel());
            if (!productOffering.getProductOfferingPrice().isEmpty()) existing.setProductOfferingPrice(productOffering.getProductOfferingPrice());
            if (!productOffering.getProductOfferingTerm().isEmpty()) existing.setProductOfferingTerm(productOffering.getProductOfferingTerm());
            if (!productOffering.getPlace().isEmpty())       existing.setPlace(productOffering.getPlace());
            existing.setLastUpdate(OffsetDateTime.now());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteProductOffering(
            @PathVariable("id") String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
