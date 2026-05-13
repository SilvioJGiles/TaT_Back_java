package com.techlab.excepciones;

public class StockInsuficienteEx extends Exception {
    public StockInsuficienteEx(String mensaje) {
        super(mensaje);
    }
}