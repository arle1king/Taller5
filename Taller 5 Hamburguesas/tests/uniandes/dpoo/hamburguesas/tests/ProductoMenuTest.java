package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;



class ProductoMenuTest {

    @Test
    void testGetNombre() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        assertEquals("Hamburguesa", producto.getNombre());
    }

    @Test
    void testGetPrecio() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        assertEquals(10000, producto.getPrecio());
    }

    @Test
    void testGenerarTextoFactura() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        String expected = "Hamburguesa\n            10000\n";
        assertEquals(expected, producto.generarTextoFactura());
    }
}
