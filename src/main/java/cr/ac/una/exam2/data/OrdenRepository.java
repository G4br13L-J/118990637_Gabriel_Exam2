package cr.ac.una.exam2.data;

import cr.ac.una.exam2.logic.entidades.Orden;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdenRepository {
    private List<Orden> ordenes = new ArrayList<>();
    private Long currentOrderId = 1L;

    public Orden save(Orden order) {
        order.setId(currentOrderId++);
        ordenes.add(order);
        return order;
    }

    public Optional<Orden> findById(Long orderId) {
        return ordenes.stream().filter(order -> order.getId().equals(orderId)).findFirst();
    }

    public List<Orden> findAll() {
        return ordenes;
    }
}
