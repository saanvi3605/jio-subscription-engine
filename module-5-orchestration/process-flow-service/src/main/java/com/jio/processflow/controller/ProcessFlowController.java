package com.jio.processflow.controller;

import com.jio.processflow.model.FlowCharacteristic;
import com.jio.processflow.model.ProcessFlow;
import com.jio.processflow.service.ProcessFlowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tmf-api/processFlowManagement/v4")
public class ProcessFlowController {

    private final ProcessFlowService service;

    public ProcessFlowController(ProcessFlowService service) {
        this.service = service;
    }

    /**
     * Start a new process flow.
     *
     * Body:
     * {
     *   "name": "Onboard Rohan Sharma",          (optional)
     *   "processFlowDefinition": {
     *     "id": "CustomerOnboarding"             (required)
     *            | "ExistingCustomerOrder"
     *            | "FastCheckout"
     *   },
     *   "characteristic": [
     *     { "name": "firstName",          "value": "Rohan"         },
     *     { "name": "lastName",           "value": "Sharma"        },
     *     { "name": "email",              "value": "r@jio.com"     },
     *     { "name": "phone",              "value": "+91-9876543210"},
     *     { "name": "paymentMethodType",  "value": "UPI"           },
     *     { "name": "paymentMethodDetail","value": "rohan@upi"     },
     *     { "name": "productOfferingId",  "value": "offer-abc-123" }  (FastCheckout / ExistingCustomerOrder)
     *     { "name": "customerId",         "value": "cust-xxx"      }  (ExistingCustomerOrder only)
     *   ]
     * }
     */
    @PostMapping("/processFlow")
    public ResponseEntity<ProcessFlow> start(@RequestBody Map<String, Object> body) {
        @SuppressWarnings("unchecked")
        Map<String, Object> defMap = (Map<String, Object>) body.get("processFlowDefinition");
        if (defMap == null || defMap.get("id") == null) {
            return ResponseEntity.badRequest().build();
        }

        String definitionId = (String) defMap.get("id");
        String name = (String) body.get("name");

        @SuppressWarnings("unchecked")
        List<Map<String, String>> rawChars = (List<Map<String, String>>) body.get("characteristic");
        List<FlowCharacteristic> characteristics = rawChars == null ? List.of() :
            rawChars.stream()
                .map(m -> new FlowCharacteristic(m.get("name"), m.get("value")))
                .toList();

        ProcessFlow flow = service.start(definitionId, name, characteristics);
        return ResponseEntity.status(HttpStatus.CREATED).body(flow);
    }

    /** List all process flows. Optionally filter by definitionId. */
    @GetMapping("/processFlow")
    public ResponseEntity<List<ProcessFlow>> list(
            @RequestParam(required = false) String definitionId) {
        if (definitionId != null) {
            return ResponseEntity.ok(service.findByDefinition(definitionId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    /** Get a single process flow by id. */
    @GetMapping("/processFlow/{id}")
    public ResponseEntity<ProcessFlow> get(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * List available process flow definitions.
     * Returns the three built-in definitions with their required input fields.
     */
    @GetMapping("/processFlowDefinition")
    public ResponseEntity<List<Map<String, Object>>> listDefinitions() {
        return ResponseEntity.ok(List.of(
            Map.of(
                "id",          "CustomerOnboarding",
                "description", "Registers a new person as a Jio customer with billing account and payment method. No order placed.",
                "tasks",       4,
                "requiredInput", List.of("firstName","lastName","email","phone","paymentMethodType","paymentMethodDetail")
            ),
            Map.of(
                "id",          "ExistingCustomerOrder",
                "description", "Places and completes an order for an already-onboarded customer. Provisions product and usage tracker.",
                "tasks",       6,
                "requiredInput", List.of("customerId","productOfferingId")
            ),
            Map.of(
                "id",          "FastCheckout",
                "description", "Onboards a brand-new customer AND immediately places, completes the order, and activates the plan — all in one call.",
                "tasks",       9,
                "requiredInput", List.of("firstName","lastName","email","phone","paymentMethodType","paymentMethodDetail","productOfferingId")
            )
        ));
    }
}
