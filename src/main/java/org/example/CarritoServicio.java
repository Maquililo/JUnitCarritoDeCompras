package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CarritoServicio {

    /*
    Esta clase proporciona servicios para manipular carritos de compras, productos y los mismos en el carrito.
    Permite crear carritos y productos, agregar y eliminar elementos del carrito, y calcular el total del mismo.
    */

    // Listas para almacenar carritos, productos e items de carrito respectivamente.
    private final List<Carrito> carritos = new ArrayList<>();
    private final List<Producto> productos = new ArrayList<>();
    private final List<ItemCarrito> itemCarritos = new ArrayList<>();

    // Método para crear un nuevo carrito
    public Carrito crearCarrito() {
        Carrito carrito = Carrito.builder().build();
        carritos.add(carrito);

        return carrito;
    }

    /*
    Método para crear un nuevo producto.
    Verifica que el precio del producto sea mayor que cero antes de añadirlo a la lista de productos.
    */
    public Producto crearProducto(String nombre, Double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
        }

        Producto producto = Producto.builder()
                .nombre(nombre)
                .precio(precio)
                .build();

        productos.add(producto);

        return producto;
    }

    /*
    Método para agregar un producto al carrito.
    Verifica que el carrito o producto especificado exista.
    Verifica que el precio del producto sea mayor que cero.
    Verificar si ya existe un ItemCarrito con la misma combinación de carritoId y productoId.
    */
    public ItemCarrito agregarItemAlCarrito(int carritoId, int productoId, int cantidad) {
        boolean carritoExiste = false;
        boolean productoExiste = false;

        for (Carrito carrito : carritos) {
            if (carrito.getId() == carritoId) {
                carritoExiste = true;
                break;
            }
        }

        for (Producto producto : productos) {
            if (producto.getId() == productoId) {
                productoExiste = true;
                break;
            }
        }

        if (!carritoExiste || !productoExiste) {
            throw new IllegalArgumentException("El carrito o producto especificado no existe.");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad del producto en el carrito debe ser mayor que cero.");
        }

        for (ItemCarrito item : itemCarritos) {
            if (item.getCarritoId() == carritoId && item.getProductId() == productoId) {
                throw new IllegalArgumentException("El producto ya está en el carrito.");
            }
        }

        ItemCarrito itemCarrito = ItemCarrito.builder()
                .carritoId(carritoId)
                .productId(productoId)
                .cantidad(cantidad)
                .build();

        itemCarritos.add(itemCarrito);

        return itemCarrito;
    }

    /*
    Método para eliminar un item del carrito.
    Verifica que el carrito o producto especificado exista y
    que ambos, en cojunto, tengan una clase itemCarrito existente.
    */
    public void eliminarItemDelCarrito(int carritoId, int productoId) {
        boolean itemExiste = false;

        for (ItemCarrito itemCarrito : itemCarritos) {
            if (itemCarrito.getCarritoId() == carritoId && itemCarrito.getProductId() == productoId) {
                itemExiste = true;
                itemCarritos.remove(itemCarrito);
                break;
            }
        }

        if (!itemExiste) {
            throw new IllegalArgumentException("El carrito o producto especificado no existe, o el producto no existe en el carrito");
        }
    }

    /*
    Método para modificar un item del carrito.
    Verifica que la cantidad a modificar sea mayor que cero.
    Verifica que el carrito o producto especificado exista y
    que ambos, en cojunto, tengan una clase itemCarrito existente.
    */
    public void modificarItemDelCarrito(int carritoId, int productoId, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad del producto en el carrito debe ser mayor que cero.");
        }

        boolean itemExiste = false;

        for (ItemCarrito itemCarrito : itemCarritos) {
            if (itemCarrito.getCarritoId() == carritoId && itemCarrito.getProductId() == productoId) {
                itemExiste = true;
                itemCarrito.setCantidad(cantidad);
                break;
            }
        }

        if (!itemExiste) {
            throw new IllegalArgumentException("El carrito o producto especificado no existe, o el producto no existe en el carrito");
        }
    }

    /*
    Método para calcular el precio total del contenido de un carrito.
    Verifica que el carrito especificado exista.
    */
    public double calcularTotal(int carritoId) {
        boolean carritoExiste = false;

        for (Carrito carrito : carritos) {
            if (carrito.getId() == carritoId) {
                carritoExiste = true;
                break;
            }
        }

        if (!carritoExiste) {
            throw new IllegalArgumentException("El carrito especificado no existe.");
        }

        double total = 0;

        for (ItemCarrito itemCarrito : itemCarritos) {
            if (itemCarrito.getCarritoId() == carritoId) {
                for (Producto producto : productos) {
                    if (itemCarrito.getProductId() == producto.getId()) {
                        total = total + itemCarrito.getCantidad() * producto.getPrecio();
                        break;
                    }
                }
            }
        }

        return total;
    }
}
