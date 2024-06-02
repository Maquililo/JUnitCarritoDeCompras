package org.example;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    /*
    Esta clase representa el producto de venta.
    Tiene la una propiedad de id para identificar de manera única cada instancia de la misma.
    Adicionalmente, tiene el nombre y el precio del producto a vender.
    El AtomicInteger se utiliza para generar identificadores únicos, aditivos, para cada instancia.
    */

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final int id = atomicInteger.getAndIncrement();
    private String nombre;
    private Double precio;
}
