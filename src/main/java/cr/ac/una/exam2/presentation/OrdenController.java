package cr.ac.una.exam2.presentation;

import cr.ac.una.exam2.logic.entidades.Orden;
import cr.ac.una.exam2.logic.entidades.ProductoCarrito;
import cr.ac.una.exam2.logic.services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {
    @Autowired
    private OrdenService orderService;

    @PostMapping
    public Orden createOrder(@RequestBody List<ProductoCarrito> items) {
        return orderService.createOrder(items);
    }

    @PostMapping("/{orderId}/items")
    public Orden addItemToOrder(@PathVariable Long orderId, @RequestParam Long productId, @RequestParam int quantity) {
        return orderService.addItemToOrder(orderId, productId, quantity);
    }

    @DeleteMapping("/{orderId}/items/{productId}")
    public Orden removeItemFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.removeItemFromOrder(orderId, productId);
    }

    @PutMapping("/{orderId}/items/{productId}")
    public Orden updateItemQuantity(@PathVariable Long orderId, @PathVariable Long productId, @RequestParam int quantity) {
        return orderService.updateItemQuantity(orderId, productId, quantity);
    }

    @PostMapping("/validate-packing")
    public ResponseEntity<Map<String, Object>> validatePacking(@RequestBody List<ProductoCarrito> items) {
        int totalSpacesNeeded = orderService.calculateTotalSpaces(items);
        boolean packedSuccessfully = orderService.calculatePacking(totalSpacesNeeded);

        Map<String, Object> response = new HashMap<>();
        response.put("packedSuccessfully", packedSuccessfully);
        response.put("totalSpacesNeeded", totalSpacesNeeded);

        return ResponseEntity.ok(response);
    }

}
