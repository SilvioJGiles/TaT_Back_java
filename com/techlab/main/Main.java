package com.techlab.main;

import com.techlab.excepciones.StockInsuficienteEx;
import com.techlab.pedidos.*;
import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 7);
    }

    static void mostrarMenu() {
        System.out.println("\n=== SISTEMA TECHLAB ===");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
    }

    static void agregarProducto() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            productos.add(new Producto(nombre, precio, stock));
            System.out.println("Producto agregado!");

        } catch (NumberFormatException e) {
            System.out.println("Error: ingresaste un valor inválido.");
        }
    }

    static void listarProductos() {
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    static Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    static Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }

    static void buscarActualizarProducto() {
        System.out.println("Buscar por:");
        System.out.println("1) ID");
        System.out.println("2) Nombre");
        int criterio = scanner.nextInt();
        scanner.nextLine();

        Producto p = null;

        if (criterio == 1) {
            System.out.print("Ingrese ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            p = buscarPorId(id);

        } else if (criterio == 2) {
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();
            p = buscarPorNombre(nombre);

        } else {
            System.out.println("Opción inválida");
            return;
        }

        if (p != null) {
            System.out.println(p);

            System.out.print("Nuevo precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            p.setPrecio(precio);

            System.out.print("Nuevo stock: ");
            int stock = Integer.parseInt(scanner.nextLine());
            if (stock >= 0) p.setStock(stock);

        } else {
            System.out.println("Producto no encontrado");
        }
    }

    static void eliminarProducto() {
        System.out.print("ID a eliminar: ");
        int id = scanner.nextInt();

        Producto p = buscarPorId(id);

        if (p != null) {
            productos.remove(p);
            System.out.println("Eliminado!");
        }
    }

    static void crearPedido() {
        Pedido pedido = new Pedido();

        while (true) {
            listarProductos();
            System.out.print("ID producto (0 para salir): ");
            int id = scanner.nextInt();

            if (id == 0) break;

            Producto p = buscarPorId(id);

            if (p != null) {
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                try {
                    if (cantidad > p.getStock()) {
                        throw new StockInsuficienteEx("No hay suficiente stock");
                    }

                    p.setStock(p.getStock() - cantidad);
                    pedido.agregarLinea(new LineaPedido(p, cantidad));

                } catch (StockInsuficienteEx e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        pedidos.add(pedido);
        System.out.println("Pedido creado!");
        pedido.mostrarPedido();
    }

    static void listarPedidos() {
        for (Pedido p : pedidos) {
            p.mostrarPedido();
            System.out.println("-----");
        }
    }
}