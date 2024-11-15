package cr.ac.una.exam2.logic.entidades;

public class ProductoCarrito {
    private Long id;
    private Producto producto;
    private int cantidad;

    public ProductoCarrito() {
    }

    public ProductoCarrito(Producto product, int quantity) {
        this.producto = product;
        this.cantidad = quantity;
    }

    public ProductoCarrito(Long id, Producto producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
