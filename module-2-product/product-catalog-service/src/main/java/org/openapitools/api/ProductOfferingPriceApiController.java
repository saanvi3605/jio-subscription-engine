package org.openapitools.api;

import org.openapitools.model.ProductOfferingPrice;
import org.openapitools.model.ProductOfferingPriceCreate;
import org.openapitools.model.ProductOfferingPriceUpdate;
import org.openapitools.repository.ProductOfferingPriceRepository;
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
public class ProductOfferingPriceApiController implements ProductOfferingPriceApi {

    private final NativeWebRequest request;
    private final ProductOfferingPriceRepository repository;

    @Autowired
    public ProductOfferingPriceApiController(NativeWebRequest request, ProductOfferingPriceRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ProductOfferingPrice> createProductOfferingPrice(
            @Valid @RequestBody ProductOfferingPriceCreate productOfferingPrice) {
        ProductOfferingPrice entity = new ProductOfferingPrice();
        entity.setDescription(productOfferingPrice.getDescription());
        entity.setIsBundle(productOfferingPrice.getIsBundle());
        entity.setName(productOfferingPrice.getName());
        entity.setPercentage(productOfferingPrice.getPercentage());
        entity.setPriceType(productOfferingPrice.getPriceType());
        entity.setRecurringChargePeriodLength(productOfferingPrice.getRecurringChargePeriodLength());
        entity.setRecurringChargePeriodType(productOfferingPrice.getRecurringChargePeriodType());
        entity.setVersion(productOfferingPrice.getVersion());
        entity.setLifecycleStatus(productOfferingPrice.getLifecycleStatus());
        entity.setLastUpdate(OffsetDateTime.now());
        entity.setBundledPopRelationship(productOfferingPrice.getBundledPopRelationship());
        entity.setConstraint(productOfferingPrice.getConstraint());
        entity.setPlace(productOfferingPrice.getPlace());
        entity.setPopRelationship(productOfferingPrice.getPopRelationship());
        entity.setPrice(productOfferingPrice.getPrice());
        entity.setPricingLogicAlgorithm(productOfferingPrice.getPricingLogicAlgorithm());
        entity.setProdSpecCharValueUse(productOfferingPrice.getProdSpecCharValueUse());
        entity.setProductOfferingTerm(productOfferingPrice.getProductOfferingTerm());
        entity.setTax(productOfferingPrice.getTax());
        entity.setUnitOfMeasure(productOfferingPrice.getUnitOfMeasure());
        entity.setValidFor(productOfferingPrice.getValidFor());
        entity.setAtBaseType(productOfferingPrice.getAtBaseType());
        entity.setAtSchemaLocation(productOfferingPrice.getAtSchemaLocation());
        entity.setAtType(productOfferingPrice.getAtType());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @Override
    public ResponseEntity<List<ProductOfferingPrice>> listProductOfferingPrice(
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields,
            @Valid @RequestParam(value = "offset", required = false) @Nullable Integer offset,
            @Valid @RequestParam(value = "limit", required = false) @Nullable Integer limit) {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<ProductOfferingPrice> retrieveProductOfferingPrice(
            @PathVariable("id") String id,
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProductOfferingPrice> patchProductOfferingPrice(
            @PathVariable("id") String id,
            @Valid @RequestBody ProductOfferingPriceUpdate productOfferingPrice) {
        return repository.findById(id).map(existing -> {
            if (productOfferingPrice.getDescription() != null)
                existing.setDescription(productOfferingPrice.getDescription());
            if (productOfferingPrice.getIsBundle() != null)
                existing.setIsBundle(productOfferingPrice.getIsBundle());
            if (productOfferingPrice.getName() != null)
                existing.setName(productOfferingPrice.getName());
            if (productOfferingPrice.getPercentage() != null)
                existing.setPercentage(productOfferingPrice.getPercentage());
            if (productOfferingPrice.getPriceType() != null)
                existing.setPriceType(productOfferingPrice.getPriceType());
            if (productOfferingPrice.getRecurringChargePeriodLength() != null)
                existing.setRecurringChargePeriodLength(productOfferingPrice.getRecurringChargePeriodLength());
            if (productOfferingPrice.getRecurringChargePeriodType() != null)
                existing.setRecurringChargePeriodType(productOfferingPrice.getRecurringChargePeriodType());
            if (productOfferingPrice.getVersion() != null)
                existing.setVersion(productOfferingPrice.getVersion());
            if (productOfferingPrice.getLifecycleStatus() != null)
                existing.setLifecycleStatus(productOfferingPrice.getLifecycleStatus());
            if (productOfferingPrice.getValidFor() != null)
                existing.setValidFor(productOfferingPrice.getValidFor());
            if (productOfferingPrice.getPrice() != null)
                existing.setPrice(productOfferingPrice.getPrice());
            if (productOfferingPrice.getUnitOfMeasure() != null)
                existing.setUnitOfMeasure(productOfferingPrice.getUnitOfMeasure());
            if (productOfferingPrice.getAtSchemaLocation() != null)
                existing.setAtSchemaLocation(productOfferingPrice.getAtSchemaLocation());
            if (!productOfferingPrice.getBundledPopRelationship().isEmpty())
                existing.setBundledPopRelationship(productOfferingPrice.getBundledPopRelationship());
            if (!productOfferingPrice.getConstraint().isEmpty())
                existing.setConstraint(productOfferingPrice.getConstraint());
            if (!productOfferingPrice.getPlace().isEmpty())
                existing.setPlace(productOfferingPrice.getPlace());
            if (!productOfferingPrice.getPopRelationship().isEmpty())
                existing.setPopRelationship(productOfferingPrice.getPopRelationship());
            if (!productOfferingPrice.getPricingLogicAlgorithm().isEmpty())
                existing.setPricingLogicAlgorithm(productOfferingPrice.getPricingLogicAlgorithm());
            if (!productOfferingPrice.getProdSpecCharValueUse().isEmpty())
                existing.setProdSpecCharValueUse(productOfferingPrice.getProdSpecCharValueUse());
            if (!productOfferingPrice.getProductOfferingTerm().isEmpty())
                existing.setProductOfferingTerm(productOfferingPrice.getProductOfferingTerm());
            if (!productOfferingPrice.getTax().isEmpty())
                existing.setTax(productOfferingPrice.getTax());
            existing.setLastUpdate(OffsetDateTime.now());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteProductOfferingPrice(@PathVariable("id") String id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
