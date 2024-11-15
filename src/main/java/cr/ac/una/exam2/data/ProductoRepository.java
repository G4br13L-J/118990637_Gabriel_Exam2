package cr.ac.una.exam2.data;

import cr.ac.una.exam2.logic.entidades.Producto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductoRepository {
    private final List<Producto> productos = new ArrayList<>();

    public ProductoRepository() {
        productos.add(new Producto(
                        1L, "Naranja Malagueña",
                        "Costa Rica",
                        50,
                        1,
                        150,
                        "https://flagcdn.com/cr.svg",
                        "/images/Naranja Malagüeña.jpg"));
        productos.add(new Producto(
                        2L,
                        "Naranja Clementina",
                        "Chile",
                        10,
                        1,
                        250,
                        "https://flagcdn.com/cl.svg",
                        "/images/clemevilla.jpg"));
        productos.add(new Producto(
                3L,
                "Manzana Ana",
                "Costa Rica",
                15,
                2,
                1000,
                "https://flagcdn.com/cr.svg",
                "/images/apple-417924_640.jpg"));
        productos.add(new Producto(4L,"Manzana Amarilla",
                "USA",
                15,
                3,
                500,
                "https://flagcdn.com/us.svg",
                "/images/manzana-amarilla.jpg"));
        productos.add(new Producto(
                5L,
                "Tomate importado",
                "España",
                25,
                1,
                250,
                "https://flagcdn.com/es.svg",
                "/images/Tomate-en-rama.jpeg"));
        productos.add(new Producto(
                 6L,
                "Granada importada",
                "Egipto",
                15,
                3,
                500,
                "https://flagcdn.com/eg.svg",
                "/images/E2M11.jpg"));
        productos.add(new Producto(
                7L,
                "Manga Verde",
                "Costa Rica",
                12,
                3,
                1000,
                "https://flagcdn.com/cr.svg",
                "/images/mango-verde-fresco-aislado-sobre-fondo-blanco-trazado-recorte-mango_26628-1001.avif"));
        productos.add(new Producto(
                8L,
                "Manga Madura",
                "Costa Rica",
                12,
                2,
                1000,
                "https://flagcdn.com/cr.svg",
                "/images/maxresdefault.jpg"));
        productos.add(new Producto(
                9L,
                "Tomate",
                "Costa Rica",
                30,
                1,
                125,
                "https://flagcdn.com/cr.svg",
                "/images/Costa-Rica-rechazo-ingreso-de-chiles-dulces-tomates-y-hongos-desde-Panama-con-plaguicidas-prohibidos-aqui-1.webp"));
        productos.add(new Producto(
                10L,
                "Limón Mesino",
                "Costa Rica",
                20,
                2,
                100,
                "https://flagcdn.com/cr.svg",
                "/images/Limon-mesino-700x366.png"));
        productos.add(new Producto(
                11L,
                "Banano",
                "Costa Rica",
                60,
                2,
                125,
                "https://flagcdn.com/cr.svg",
                "/images/AgenciaUN_0909_1_40-1080x675.webp"));
        productos.add(new Producto(
                12L,
                "Plátano Verde",
                "Costa Rica",
                60,
                3,
                250,
                "https://flagcdn.com/cr.svg",
                "/images/5dcafcc27789670cf8dc87c1_11.png"));
        productos.add(new Producto(
                13L,
                "Plátano Maduro",
                "Costa Rica",
                30,
                3,
                125,
                "https://flagcdn.com/cr.svg",
                "/images/platano-maduro.webp"));
        productos.add(new Producto(
                14L,
                "Sandía amarilla",
                "Costa Rica",
                8,
                2,
                2500,
                "https://flagcdn.com/cr.svg",
                "/images/Sandia-Amarilla.jpg"));
        productos.add(new Producto(15L,
                "Sandía roja",
                "Costa Rica",
                8,
                2,
                2500,
                "https://flagcdn.com/cr.svg",
                "/images/Sandia-Seleccion-n-Especial-Kg-3-A-6-Kg-Por-Sandia-1-26271.webp"));
        productos.add(new Producto(
                16L,
                "Melón Cantalupa",
                "Costa Rica",
                12,
                1,
                1000,
                "https://flagcdn.com/cr.svg",
                "/images/download_0.jpg"));
        productos.add(new Producto(
                17L,
                "Melón Honey",
                "Costa Rica",
                12,
                4,
                1000,
                "https://flagcdn.com/cr.svg",
                "/images/1349.jpg"));
        productos.add(new Producto(
                18L,
                "Piña Dorada",
                "Costa Rica",
                12,
                4,
                1000,
                "https://flagcdn.com/cr.svg",
                "/images/Pi-a-Dorada-Unidad-1-42186.webp"));
        productos.add(new Producto(
                19L,
                "Piña Rosada",
                "Costa Rica",
                8,
                3,
                2500,
                "https://flagcdn.com/cr.svg",
                "/images/w800px_27413-215554-la-pina-rosada-un-manjar-exclusivo-de-costa-rica-que-llega-a-los-mercados.jpeg"));
        productos.add(new Producto(
                20L,
                "Culantro Castilla",
                "Costa Rica",
                16,
                2,
                150,
                "https://flagcdn.com/cr.svg",
                "/images/Diseno-sin-titulo-16.png"));
    }

    public List<Producto> findAll() {
        return productos;
    }

    public Optional<Producto> findById(Long id) {
        return productos.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

}
