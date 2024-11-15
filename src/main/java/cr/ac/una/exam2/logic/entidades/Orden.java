package cr.ac.una.exam2.logic.entidades;

import java.util.List;

public class Orden {
    private Long id;
    private List<ProductoCarrito> productos;
    private boolean embalajeExitoso;

    public Orden() {
    }

    public Orden(Long id, List<ProductoCarrito> productos, boolean embalajeExitoso) {
        this.id = id;
        this.productos = productos;
        this.embalajeExitoso = embalajeExitoso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductoCarrito> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCarrito> productos) {
        this.productos = productos;
    }

    public boolean isEmbalajeExitoso() {
        return embalajeExitoso;
    }

    public void setEmbalajeExitoso(boolean embalajeExitoso) {
        this.embalajeExitoso = embalajeExitoso;
    }
}
