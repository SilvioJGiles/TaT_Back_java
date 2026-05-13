package com.techlab.pedidos;

import java.util.ArrayList;

public class Pedido {
    private static int contadorid = 1;
    private final int id;
    private final ArrayList<LineaPedido> lineas;

    public Pedido() {
        this.id = contadorid++;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido l : lineas) {
            total += l.calcularSubtotal();
        }
        return total;
    }

    public void mostrarPedido() {
        System.out.println("Pedido ID: " + id);
        for (LineaPedido l : lineas) {
            System.out.println(l.getProducto().getNombre() +
                    " x" + l.getCantidad() +
                    " = $" + l.calcularSubtotal());
        }
        System.out.println("TOTAL: $" + calcularTotal());
    }
}