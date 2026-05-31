package com.unicartagena;

import java.util.Scanner;

public class Main {

    static GestorTiendaOnline gestor = new GestorTiendaOnline();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("Sistema cerrado. ¡Hasta luego!");
        sc.close();
    }

    // ─── MENÚ
    static void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   SISTEMA DE TIENDA ONLINE       ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1.  Registrar pedido            ║");
        System.out.println("║  2.  Ver lista general           ║");
        System.out.println("║  3.  Ver pedidos pendientes      ║");
        System.out.println("║  4.  Procesar siguiente pedido   ║");
        System.out.println("║  5.  Ver historial procesados    ║");
        System.out.println("║  6.  Buscar por código (Map)     ║");
        System.out.println("║  7.  Cancelar pedido pendiente   ║");
        System.out.println("║  8.  Deshacer último proceso     ║");
        System.out.println("║  9.  Ver cantidades              ║");
        System.out.println("║  10. Filtrar por estado          ║");
        System.out.println("║  11. Ordenar por precio          ║");
        System.out.println("║  12. Agrupar por estado          ║");
        System.out.println("║  13. Estadísticas de precios     ║");
        System.out.println("║  14. Buscar por nombre cliente   ║");
        System.out.println("║  0.  Salir                       ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    // ─── PROCESAR OPCIÓN
    static void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarPedido();
                case 2 -> gestor.mostrarListaGeneral();
                case 3 -> gestor.mostrarPendientes();
                case 4 -> gestor.procesarSiguiente();
                case 5 -> gestor.mostrarHistorial();
                case 6 -> {
                    String cod = leerTexto("Código a buscar: ");
                    gestor.buscarPorCodigo(cod);
                }
                case 7 -> {
                    String cod = leerTexto("Código del pedido a cancelar: ");
                    gestor.cancelarPendiente(cod);
                }
                case 8  -> gestor.deshacerUltimo();
                case 9  -> gestor.mostrarCantidades();
                case 10 -> {
                    String estado = leerTexto("Estado (PENDIENTE / PROCESADO / CANCELADO): ");
                    gestor.filtrarPorEstado(estado);
                }
                case 11 -> gestor.ordenarPorPrecio();
                case 12 -> gestor.agruparPorEstado();
                case 13 -> gestor.mostrarEstadisticas();
                case 14 -> {
                    String nombre = leerTexto("Nombre del cliente a buscar: ");
                    gestor.buscarConStream(nombre);
                }
                case 0  -> {}
                default -> System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }
    }

    // ─── REGISTRAR PEDIDO ─────────────────────────────────────────────────
    static void registrarPedido() throws Exception {
        System.out.println("\n── Nuevo Pedido ──");
        String codigo   = leerTexto("Código del pedido : ");
        String cliente  = leerTexto("Nombre del cliente: ");
        String producto = leerTexto("Producto          : ");
        double precio   = leerDouble("Precio            : ");
        gestor.registrarPedido(codigo, cliente, producto, precio);
    }

    // ─── UTILIDADES DE ENTRADA ────────────────────────────────────────────
    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    static int leerInt(String mensaje) {
        System.out.print(mensaje);
        try {
            int val = Integer.parseInt(sc.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingresa un número válido.");
            }
        }
    }
}