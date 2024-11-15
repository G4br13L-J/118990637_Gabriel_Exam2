package cr.ac.una.exam2.presentation;

import cr.ac.una.exam2.logic.entidades.Producto;
import cr.ac.una.exam2.logic.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productService;

    @GetMapping
    public List<Producto> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
