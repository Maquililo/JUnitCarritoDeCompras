package org.example;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrito {

    /*
    Esta clase representa un producto dentro de un carrito de compras.
    Contiene la información sobre el id del carrito al que pertenece, el id del producto que representa
    y la cantidad de ese producto en el carrito.
    */

    private int carritoId;
    private int productId;
    private int cantidad;
}
