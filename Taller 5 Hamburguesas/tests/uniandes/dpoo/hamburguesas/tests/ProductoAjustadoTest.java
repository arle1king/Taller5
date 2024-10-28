package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

class ProductoAjustadoTest {

    @Test
    void testGetNombre() {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        assertEquals("Hamburguesa", productoAjustado.getNombre());
    }

    @Test
    void testGetPrecio() {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        
        Ingrediente queso = new Ingrediente("Queso", 2000);
        productoAjustado.getAgregados().add(queso);

        assertEquals(12000, productoAjustado.getPrecio());
    }

    @Test
    void testGenerarTextoFactura() {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);

        Ingrediente queso = new Ingrediente("Queso", 2000);
        Ingrediente tomate = new Ingrediente("Tomate", 500);
        productoAjustado.getAgregados().add(queso);
        productoAjustado.getAgregados().add(tomate);
        
        productoAjustado.getEliminados().add(tomate);

        String expected = "Hamburguesa\n" +
                "    +Queso                2000\n" +
                "    +Tomate                500\n" +
                "    -Tomate\n" +
                "            12500\n";
        /**
        System.out.println(productoAjustado.generarTextoFactura());
        System.out.println(expected);
        */
        assertEquals(expected, productoAjustado.generarTextoFactura());
    }
}
