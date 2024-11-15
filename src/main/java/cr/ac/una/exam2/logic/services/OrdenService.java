package cr.ac.una.exam2.logic.services;

import cr.ac.una.exam2.data.OrdenRepository;
import cr.ac.una.exam2.data.ProductoRepository;
import cr.ac.una.exam2.logic.entidades.Orden;
import cr.ac.una.exam2.logic.entidades.Producto;
import cr.ac.una.exam2.logic.entidades.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository orderRepository;

    @Autowired
    private ProductoRepository productRepository;

    // Esto se va a usar para calcular el espacio de embalaje de los productos
    private static final int[] BOX_SIZES = {12, 7, 5, 3, 2}; // Tamaños de caja en orden descendente

    // Aqui se crea una orden con los productos que se le pasan (agregados a la tabla de orden/carrito)
    public Orden createOrder(List<ProductoCarrito> items) {
        int totalSpacesNeeded = calculateTotalSpaces(items);
        boolean packedSuccessfully = calculatePacking(totalSpacesNeeded);
        Orden order = new Orden(null, items, packedSuccessfully);

        if (packedSuccessfully) {
            order = orderRepository.save(order);
        }

        return order;
    }

    // Aqui se agrega un producto a la tabla de la orden

    public Orden addItemToOrder(Long orderId, Long productId, int quantity) {
        // Se valida que el producto exista en el repo
        Orden order = findOrderById(orderId);
        Producto product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductoCarrito newItem = new ProductoCarrito(product, quantity);
        order.getProductos().add(newItem);
        return order;
    }

    // Este metodo lo que hace es elminar un producto de la tabla de orden
    public Orden removeItemFromOrder(Long orderId, Long productId) {
        Orden order = findOrderById(orderId);

        order.getProductos().removeIf(item -> item.getProducto().getId().equals(productId));
        return order;
    }

    public Orden updateItemQuantity(Long orderId, Long productId, int newQuantity) {
        Orden order = findOrderById(orderId);

        Optional<ProductoCarrito> orderItem = order.getProductos().stream()
                .filter(item -> item.getProducto().getId().equals(productId))
                .findFirst();

        if (orderItem.isPresent()) {
            orderItem.get().setCantidad(newQuantity);
        } else {
            throw new RuntimeException("Producto no encontrado en la orden");
        }

        return order;
    }

    private Orden findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    public int calculateTotalSpaces(List<ProductoCarrito> items) {
        return items.stream()
                .mapToInt(item -> item.getCantidad() * item.getProducto().getEspacioEmbalaje())
                .sum();
    }

    // Este metodo calcula si se puede embalar los productos sin dejar espacios libres
    public boolean calculatePacking(int totalSpacesNeeded) {
        int remainingSpace = totalSpacesNeeded;
        int[] boxCount = new int[BOX_SIZES.length]; // Array para contar el uso de cada tamaño de caja

        for (int i = 0; i < BOX_SIZES.length; i++) {
            int boxSize = BOX_SIZES[i];

            // Calcular el número de cajas necesarias de este tamaño
            boxCount[i] = remainingSpace / boxSize;
            remainingSpace %= boxSize;

            // Si no hay espacio restante, significa que se puede embalar sin dejar espacios libres
            if (remainingSpace == 0) {
                return true;
            }
        }

        // Si al final queda espacio restante, no se puede embalar sin dejar espacios libres
        return false;
    }

}
