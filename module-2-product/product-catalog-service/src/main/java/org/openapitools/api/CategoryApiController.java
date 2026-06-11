package org.openapitools.api;

import org.openapitools.model.Category;
import org.openapitools.model.CategoryCreate;
import org.openapitools.model.CategoryUpdate;
import org.openapitools.repository.CategoryRepository;
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
public class CategoryApiController implements CategoryApi {

    private final NativeWebRequest request;
    private final CategoryRepository repository;

    @Autowired
    public CategoryApiController(NativeWebRequest request, CategoryRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryCreate category) {
        Category entity = new Category();
        entity.setDescription(category.getDescription());
        entity.setIsRoot(category.getIsRoot());
        entity.setName(category.getName());
        entity.setParentId(category.getParentId());
        entity.setVersion(category.getVersion());
        entity.setLifecycleStatus(category.getLifecycleStatus());
        entity.setLastUpdate(OffsetDateTime.now());
        entity.setProductOffering(category.getProductOffering());
        entity.setSubCategory(category.getSubCategory());
        entity.setValidFor(category.getValidFor());
        entity.setAtBaseType(category.getAtBaseType());
        entity.setAtType(category.getAtType());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @Override
    public ResponseEntity<List<Category>> listCategory(
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields,
            @Valid @RequestParam(value = "offset", required = false) @Nullable Integer offset,
            @Valid @RequestParam(value = "limit", required = false) @Nullable Integer limit) {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Category> retrieveCategory(
            @PathVariable("id") String id,
            @Valid @RequestParam(value = "fields", required = false) @Nullable String fields) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Category> patchCategory(
            @PathVariable("id") String id,
            @Valid @RequestBody CategoryUpdate category) {
        return repository.findById(id).map(existing -> {
            if (category.getDescription() != null)     existing.setDescription(category.getDescription());
            if (category.getIsRoot() != null)          existing.setIsRoot(category.getIsRoot());
            if (category.getName() != null)            existing.setName(category.getName());
            if (category.getParentId() != null)        existing.setParentId(category.getParentId());
            if (category.getVersion() != null)         existing.setVersion(category.getVersion());
            if (category.getLifecycleStatus() != null) existing.setLifecycleStatus(category.getLifecycleStatus());
            if (category.getValidFor() != null)        existing.setValidFor(category.getValidFor());
            if (!category.getProductOffering().isEmpty()) existing.setProductOffering(category.getProductOffering());
            if (!category.getSubCategory().isEmpty())  existing.setSubCategory(category.getSubCategory());
            existing.setLastUpdate(OffsetDateTime.now());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
