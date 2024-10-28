package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class PedidoTest {

    @Test
    void testGenerarTextoFactura() {
        Pedido pedido = new Pedido("Cliente", "Direccion");

        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        Combo combo = new Combo("Combo Especial", 0.9, new ArrayList<>());
        
        pedido.agregarProducto(producto);
        pedido.agregarProducto(combo);

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("Combo Especial"));
        assertTrue(factura.contains("IVA"));
        assertTrue(factura.contains("Precio Total"));
    }

    @Test
    void testGuardarFactura() throws IOException {
        Pedido pedido = new Pedido("Cliente", "Direccion");
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        pedido.agregarProducto(producto);
        
       
        
        File facturaFile = new File("factura.txt");
        pedido.guardarFactura(facturaFile);
        assertTrue(facturaFile.exists());

        if (facturaFile.exists()) {
            facturaFile.delete();
            
        
        }
    }
}
