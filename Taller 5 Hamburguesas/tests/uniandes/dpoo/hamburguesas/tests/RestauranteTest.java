package uniandes.dpoo.hamburguesas.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    void testIniciarPedido() {
        assertDoesNotThrow(() -> restaurante.iniciarPedido("John Doe", "123 Main St"));
        assertNotNull(restaurante.getPedidoEnCurso());

        // Attempting to start another order while one is in progress
        Exception exception = assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante.iniciarPedido("Jane Doe", "456 Elm St");});
        assertTrue(exception.getMessage().contains("pedido en curso"));
    }

    @Test
    void testCerrarYGuardarPedido() throws Exception {
        // Initialize an order
        restaurante.iniciarPedido("John Doe", "123 Main St");

        // Closing and saving the order
        assertDoesNotThrow(() -> restaurante.cerrarYGuardarPedido());

        // Ensuring that no active order remains after closure
        assertNull(restaurante.getPedidoEnCurso());
        
        Exception exception = assertThrows(NoHayPedidoEnCursoException.class, () -> {restaurante.cerrarYGuardarPedido();});
        assertTrue(exception.getMessage().contains("no hay un pedido en curso"));
        
    }

    @Test
    void testCargarInformacionRestaurante() {
        // Assume we have these sample data files for ingredients, menu, and combos
        File archivoIngredientes = new File("./data/ingredientes.txt");
        File archivoMenu = new File("./data/menu.txt");
        File archivoCombos = new File("./data/combos.txt");

        assertDoesNotThrow(() -> restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos));
       
    }
    
    @Test
    void testIngredienteRepetidoException() {
        File archivoIngredientesRepetidos = new File("./data/ingredientesTest.txt"); // archivo con ingredientes duplicados
        File archivoMenu = new File("./data/menu.txt");
        File archivoCombos = new File("./data/combos.txt");

        Exception exception = assertThrows(IngredienteRepetidoException.class, () -> {restaurante.cargarInformacionRestaurante(archivoIngredientesRepetidos, archivoMenu, archivoCombos);});
        assertTrue(exception.getMessage().contains("está repetido"));
    }

    @Test
    void testProductoRepetidoException() {
        File archivoIngredientes = new File("./data/ingredientes.txt");
        File archivoMenuRepetido = new File("./data/menuTest.txt"); // archivo con productos duplicados en el menú
        File archivoCombos = new File("./data/combos.txt");

        Exception exception = assertThrows(ProductoRepetidoException.class, () -> {restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenuRepetido, archivoCombos);});
        assertTrue(exception.getMessage().contains("está repetido"));
        
    }

    @Test
    void testProductoFaltanteException() {
        File archivoIngredientes = new File("./data/ingredientes.txt");
        File archivoMenu = new File("./data/menu.txt");
        File archivoCombosFaltantes = new File("./data/combosTest.txt"); // archivo de combos que incluye productos faltantes

        Exception exception = assertThrows(ProductoFaltanteException.class, () -> {restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombosFaltantes);});
        assertTrue(exception.getMessage().contains("no aparece"));
    }

    @Test
    void testProductoReException() {
        File archivoIngredientes = new File("./data/ingredientes.txt");
        File archivoMenu = new File("./data/menu.txt");
        File archivoCombosRepetidos = new File("./data/combosRTest.txt"); // archivo de combos que incluye productos faltantes

        Exception exception = assertThrows(ProductoRepetidoException.class, () -> {restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombosRepetidos);});
        assertTrue(exception.getMessage().contains("está repetido"));
    }

    @Test
    void testGetIngredientes() {
        ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
        assertNotNull(ingredientes);
        assertEquals(0, ingredientes.size()); // Should be empty initially
    }

    @Test
    void testGetMenuBase() {
        ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
        assertNotNull(menuBase);
        assertEquals(0, menuBase.size()); // Should be empty initially
    }

    @Test
    void testGetMenuCombos() {
        ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
        assertNotNull(menuCombos);
        assertEquals(0, menuCombos.size()); // Should be empty initially
    }

	@Test
	void testGetPedidos() {
	    ArrayList<Pedido> pedidos = restaurante.getPedidos();
	    assertNotNull(pedidos);
	    assertEquals(0, pedidos.size()); // Should be empty initially
	}
}
	
