package org.example;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Builder
public class Carrito {

    /*
    Esta clase representa el carrito de compras.
    Tiene la única propiedad de id para identificar de manera única cada instancia de la misma.
    El AtomicInteger se utiliza para generar identificadores únicos, aditivos, para cada instancia.
    */

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final int id = atomicInteger.getAndIncrement();
}
