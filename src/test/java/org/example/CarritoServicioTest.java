package org.example;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

// Para mantener los test organizados
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarritoServicioTest extends TestCase {

    CarritoServicio carritoServicio = new CarritoServicio();

    public void test1CrearCarrito() {
        // Creación del primer carrito
        Carrito carrito1 = carritoServicio.crearCarrito();

        // Verifición de que el id del primer carrito creado sea 1
        assertEquals(1, carrito1.getId());

        // Creación del segundo y tercer carrito
        Carrito carrito2 = carritoServicio.crearCarrito();
        Carrito carrito3 = carritoServicio.crearCarrito();

        // Verifición de que el id del segundo carrito sea diferente de 1
        assertNotEquals(1, carrito2.getId());

        // Verifición de que el id del tercer carrito sea 3
        assertEquals(3, carrito3.getId());
    }

    public void test2CrearProducto() {
        // Creación del primer producto
        Producto producto1 = carritoServicio.crearProducto("Manzana", (double) 10);

        // Verifición de que el primer producto tenga el nombre especificado (Manzana)
        assertEquals("Manzana", producto1.getNombre());

        // Verifición de que el primer producto no tenga otro nombre del creado, como "Pera"
        assertNotEquals("Pera", producto1.getNombre());

        // Verifición de que el primer producto tenga el precio especificado (10)
        assertEquals((double) 10, producto1.getPrecio());

        // Verifición de que el producto no sea menor o igual a cero
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.crearProducto(
                "Manzana", (double) -10
        ));

        // Creación del segundo producto
        Producto producto2 = carritoServicio.crearProducto("Pera", (double) 5);

        // Verifición de que el segundo producto tenga el nombre especificado (Pera)
        assertEquals("Pera", producto2.getNombre());

        // Verifición de que el segundo producto tenga el precio especificado (5)
        assertEquals((double) 5, producto2.getPrecio());

        // Verifición de que el segundo producto no tenga otro precio del creado, como "15"
        assertNotEquals((double)15, producto2.getPrecio());

        // Verifición de que el producto no sea menor o igual a cero
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.crearProducto(
                "Piña", (double) -10
        ));
    }

    public void test3AgregarItemAlCarrito() {
        // Creación de nuevo carrito y producto para utilizar
        Carrito carrito4 = carritoServicio.crearCarrito();
        Producto producto3 = carritoServicio.crearProducto("Manzana", (double) 10);

        // Creación del primer itemCarrito (carrito1 y producto1)
        ItemCarrito itemCarrito1 = carritoServicio.agregarItemAlCarrito(
                4, 3, 2
        );

        // Verifición de que el primer itemCarrito tenga el contenido especificado (4, 3, 1)
        assertEquals(4, itemCarrito1.getCarritoId());
        assertEquals(3, itemCarrito1.getProductId());
        assertEquals(2, itemCarrito1.getCantidad());

        // Verifición de que que exista un carrito o producto para realmente crear el itemCarrito
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.agregarItemAlCarrito(
                5,1,2
        ));
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.agregarItemAlCarrito(
                4,2,2
        ));

        // Verifición de que la cantidad no sea menor o igual a cero
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.agregarItemAlCarrito(
                4,1,0
        ));

        // Verficación de itemCarrito ya creado
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.agregarItemAlCarrito(
                4,3,2
        ));

        // Creación de nuevo producto para utilizar
        Producto producto4 = carritoServicio.crearProducto("Pera", (double) 5);

        // Adición de un nuevo producto en el mismo carrito
        itemCarrito1 = carritoServicio.agregarItemAlCarrito(
                4, 4, 1
        );
    }

    public void test4EliminarItemDelCarrito() {
        // Creación de nuevo carrito, producto e itemCarrito para utilizar
        Carrito carrito5 = carritoServicio.crearCarrito();
        Producto producto5 = carritoServicio.crearProducto("Manzana", (double) 10);
        ItemCarrito itemCarrito3 = carritoServicio.agregarItemAlCarrito(
                5, 5, 5
        );

        // Verifición de que este itemCarrito tenga el contenido especificado (5, 5, 5)
        assertEquals(5, itemCarrito3.getCarritoId());
        assertEquals(5, itemCarrito3.getProductId());
        assertEquals(5, itemCarrito3.getCantidad());
        assertNotEquals(1, itemCarrito3.getCantidad());

        // Verifición de que exista el itemCarrito a eliminar
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.eliminarItemDelCarrito(
                carrito5.getId(), 6
        ));
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.eliminarItemDelCarrito(
                6, producto5.getId()
        ));
    }

    public void test5ModificarItemDelCarrito() {
        // Creación de nuevo carrito, producto e itemCarrito para utilizar
        Carrito carrito6 = carritoServicio.crearCarrito();
        Producto producto6 = carritoServicio.crearProducto("Manzana", (double) 10);
        ItemCarrito itemCarrito4 = carritoServicio.agregarItemAlCarrito(
                6, 6, 2
        );

        // Verifición de que este itemCarrito tenga el contenido especificado (6, 6, 2)
        assertEquals(6, itemCarrito4.getCarritoId());
        assertEquals(6, itemCarrito4.getProductId());
        assertEquals(2, itemCarrito4.getCantidad());

        // Verifición de que la cantidad a modificar no sea cero o menor
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.modificarItemDelCarrito(
                carrito6.getId(), producto6.getId(), -2
        ));

        // Verifición de que exista el itemCarrito a modificar
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.modificarItemDelCarrito(
                carrito6.getId(), 7, 2
        ));
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.modificarItemDelCarrito(
                7, producto6.getId(), 2
        ));
    }

    public void test6CalcularTotal() {
        // Creación de nuevo carrito, productos e itemCarritos para utilizar
        Carrito carrito7 = carritoServicio.crearCarrito();
        Producto producto7 = carritoServicio.crearProducto("Manzana", (double) 10);
        Producto producto8 = carritoServicio.crearProducto("Pera", (double) 5);
        ItemCarrito itemCarrito5 = carritoServicio.agregarItemAlCarrito(
                7, 7, 2
        );
        ItemCarrito itemCarrito6 = carritoServicio.agregarItemAlCarrito(
                7, 8, 1
        );

        // Verifición de que este carrito, producto e itemCarrito tenga el contenido especificado (7, 7, 2) y (7, 8, 1)
        assertEquals(7, itemCarrito5.getCarritoId());
        assertEquals(7, itemCarrito5.getProductId());
        assertEquals(2, itemCarrito5.getCantidad());
        assertEquals(7, itemCarrito6.getCarritoId());
        assertEquals(8, itemCarrito6.getProductId());
        assertEquals(1, itemCarrito6.getCantidad());

        // Verifición de que el carrito exista
        assertThrows(IllegalArgumentException.class, () -> carritoServicio.calcularTotal(
                8
        ));

        // Verifición de que el calculo sea correcto (2 manzanas de 10 y 1 pera de 5)
        assertNotEquals((double) 15, carritoServicio.calcularTotal(7));
        assertEquals((double) 25, carritoServicio.calcularTotal(7));
    }
}
