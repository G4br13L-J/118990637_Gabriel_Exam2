package cr.ac.una.exam2.logic.services;

import cr.ac.una.exam2.data.ProductoRepository;
import cr.ac.una.exam2.logic.entidades.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productRepository;

    public List<Producto> getAvailableProducts() {
        return productRepository.findAll();
    }

    public Producto getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
