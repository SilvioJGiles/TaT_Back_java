package com.techlab.productos;

public class Producto {
    private static int contadorid = 1;

    private final int id;
    private final String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = contadorid++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: " + nombre +
               " | Precio: $" + precio +
               " | Stock: " + stock;
    }
}