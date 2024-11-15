package cr.ac.una.exam2.logic.entidades;

import java.util.Objects;

public class Producto {
    private Long id;
    private String nombre;
    private String origin;
    private int cantidadDisponible;
    private int espacioEmbalaje;
    private double precio;
    private String imagenUrl;
    private String direccion;

    public Producto() {
    }

    public Producto(Long id, String nombre, String origin, int cantidadDisponible, int espacioEmbalaje, double precio, String imagenUrl, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.origin = origin;
        this.cantidadDisponible = cantidadDisponible;
        this.espacioEmbalaje = espacioEmbalaje;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getEspacioEmbalaje() {
        return espacioEmbalaje;
    }

    public void setEspacioEmbalaje(int espacioEmbalaje) {
        this.espacioEmbalaje = espacioEmbalaje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
