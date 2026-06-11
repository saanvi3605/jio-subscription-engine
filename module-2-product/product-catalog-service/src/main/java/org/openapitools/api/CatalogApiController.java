package org.openapitools.api;

import org.openapitools.model.Catalog;
import org.openapitools.model.CatalogCreate;
import org.openapitools.model.CatalogUpdate;
import org.openapitools.repository.CatalogRepository;
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
public class CatalogApiController implements CatalogApi {

    private final NativeWebRequest request;
    private final CatalogRepository repository;

    @Autowired
    public CatalogApiController(NativeWebRequest request, CatalogRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Catalog> createCatalog(@Valid @RequestBody CatalogCreate catalog) {
        Catalog entity = new Catalog();
        entity.setCatalogType(catalog.getCatalogType());
        entity.setDescription(catalog.getDescription());
        entity.setName(catalog.getName());
        entity.setVersion(catalog.getVersion());
        entity.setLifecycleStatus(catalog.getLifecycleStatus());
        entity.setLastUpdate(OffsetDateTime.now());
        entity.setCategory(catalog.getCategory());
        entity.setRelatedParty(catalog.getRelatedParty());
        entity.setValidFor(catalog.getValidFor());
        entity.setAtBaseType(catalog.getAtBaseType());
        entity.setAtType(catalog.getAtType());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @Override
    public ResponseEntity<List<Catalog>> listCatalog(
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields,
            @Valid @RequestParam(value = "offset", required = false) @Nullable Integer offset,
            @Valid @RequestParam(value = "limit", required = false) @Nullable Integer limit) {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Catalog> retrieveCatalog(
            @PathVariable("id") String id,
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Catalog> patchCatalog(
            @PathVariable("id") String id,
            @Valid @RequestBody CatalogUpdate catalog) {
        return repository.findById(id).map(existing -> {
            if (catalog.getCatalogType() != null)     existing.setCatalogType(catalog.getCatalogType());
            if (catalog.getDescription() != null)     existing.setDescription(catalog.getDescription());
            if (catalog.getName() != null)            existing.setName(catalog.getName());
            if (catalog.getVersion() != null)         existing.setVersion(catalog.getVersion());
            if (catalog.getLifecycleStatus() != null) existing.setLifecycleStatus(catalog.getLifecycleStatus());
            if (catalog.getValidFor() != null)        existing.setValidFor(catalog.getValidFor());
            if (!catalog.getCategory().isEmpty())     existing.setCategory(catalog.getCategory());
            if (!catalog.getRelatedParty().isEmpty()) existing.setRelatedParty(catalog.getRelatedParty());
            existing.setLastUpdate(OffsetDateTime.now());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteCatalog(@PathVariable("id") String id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
