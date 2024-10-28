package uniandes.dpoo.hamburguesas.tests;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class ComboTest {

    @Test
    void testGetNombre() {
        Combo combo = new Combo("Combo Especial", 0.9, new ArrayList<>());
        assertEquals("Combo Especial", combo.getNombre());
    }

    @Test
    void testGetPrecio() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 10000));
        items.add(new ProductoMenu("Papas", 5000));
        
        Combo combo = new Combo("Combo Especial", 0.9, items);
        assertEquals(13500, combo.getPrecio()); // 90% of 15000 = 13500
    }

    @Test
    void testGenerarTextoFactura() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 10000));
        items.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo("Combo Especial", 0.9, items);
        String expected = "Combo Combo Especial\n" +
                " Descuento: 0.9\n" +
                "            13500\n";
        assertEquals(expected, combo.generarTextoFactura());
    }
}
