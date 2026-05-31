package com.unicartagena;

import java.util.*;
import java.util.stream.*;

public class GestorTiendaOnline {

    // ─── Colecciones SDK de Java
    private List<PedidoOnline>          listaGeneral;      // registro completo
    private Queue<PedidoOnline>         pendientes;        // FIFO
    private Deque<PedidoOnline>         historial;         // LIFO (reemplaza Pila)
    private Map<String, PedidoOnline>   indicePorCodigo;   // búsqueda rápida

    public GestorTiendaOnline() {
        listaGeneral    = new ArrayList<>();
        pendientes      = new LinkedList<>();
        historial       = new ArrayDeque<>();
        indicePorCodigo = new HashMap<>();
    }

    // ─── 1. REGISTRAR PEDIDO
    public void registrarPedido(String codigo, String cliente,
                                String producto, double precio) throws Exception {
        if (codigo == null || codigo.isBlank())
            throw new Exception("El código no puede estar vacío.");
        if (indicePorCodigo.containsKey(codigo))
            throw new Exception("Ya existe un pedido con el código: " + codigo);

        PedidoOnline nuevo = new PedidoOnline(codigo, cliente, producto, precio, "PENDIENTE");
        listaGeneral.add(nuevo);
        pendientes.offer(nuevo);
        indicePorCodigo.put(codigo, nuevo);
        System.out.println("✔ Pedido registrado: " + nuevo);
    }

    // ─── 2. VER LISTA GENERAL
    public void mostrarListaGeneral() {
        if (listaGeneral.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\n══ LISTA GENERAL ══");
        listaGeneral.forEach(System.out::println);
    }

    // ─── 3. VER PEDIDOS PENDIENTES
    public void mostrarPendientes() {
        if (pendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }
        System.out.println("\n══ PEDIDOS PENDIENTES ══");
        pendientes.forEach(System.out::println);
    }

    // ─── 4. PROCESAR SIGUIENTE PEDIDO
    public void procesarSiguiente() throws Exception {
        if (pendientes.isEmpty())
            throw new Exception("No hay pedidos pendientes para procesar.");

        PedidoOnline pedido = pendientes.poll();   // saca del frente (FIFO)
        pedido.setEstado("PROCESADO");
        historial.push(pedido);                    // apila en historial (LIFO)
        System.out.println("✔ Pedido procesado: " + pedido);
    }

    // ─── 5. VER HISTORIAL ────────────────────────────────────────────────
    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("El historial está vacío.");
            return;
        }
        System.out.println("\n══ HISTORIAL DE PROCESADOS ══");
        historial.forEach(System.out::println);
    }

    // ─── 6. BUSCAR POR CÓDIGO (Map O(1))
    public void buscarPorCodigo(String codigo) {
        PedidoOnline encontrado = indicePorCodigo.get(codigo);
        if (encontrado == null) {
            System.out.println("No se encontró pedido con código: " + codigo);
        } else {
            System.out.println("✔ Encontrado: " + encontrado);
        }
    }

    // ─── 7. CANCELAR PEDIDO PENDIENTE
    public void cancelarPendiente(String codigo) throws Exception {
        boolean eliminado = pendientes.removeIf(
                p -> p.getCodigoPedido().equals(codigo)
        );
        if (!eliminado)
            throw new Exception("No se encontró el pedido en pendientes: " + codigo);

        PedidoOnline pedido = indicePorCodigo.get(codigo);
        if (pedido != null) pedido.setEstado("CANCELADO");
        System.out.println("✔ Pedido cancelado: " + codigo);
    }

    // ─── 8. DESHACER ÚLTIMO PROCESAMIENTO
    public void deshacerUltimo() throws Exception {
        if (historial.isEmpty())
            throw new Exception("El historial está vacío, nada que deshacer.");

        PedidoOnline ultimo = historial.pop();     // retira la cima
        ultimo.setEstado("PENDIENTE");
        pendientes.offer(ultimo);                  // devuelve a la cola
        System.out.println("✔ Procesamiento deshecho. Pedido vuelto a pendientes: " + ultimo);
    }

    // ─── 9. CANTIDAD DE PEDIDOS
    public void mostrarCantidades() {
        System.out.println("\n══ CANTIDADES ══");
        System.out.println("Lista general : " + listaGeneral.size());
        System.out.println("Pendientes    : " + pendientes.size());
        System.out.println("Historial     : " + historial.size());
        System.out.println("Índice (Map)  : " + indicePorCodigo.size());

        System.out.println("\n── Conteo por estado (Stream) ──");
        listaGeneral.stream()
                .collect(Collectors.groupingBy(PedidoOnline::getEstado, Collectors.counting()))
                .forEach((estado, count) ->
                        System.out.println("  " + estado + ": " + count));
    }

    // ─── 10. FILTRAR POR ESTADO (Stream)
    public void filtrarPorEstado(String estado) {

        System.out.println("\n══ FILTRO POR ESTADO: " + estado + " ══");
        List<PedidoOnline> resultado = listaGeneral.stream()

                .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            System.out.println("No hay pedidos con ese estado.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    // ─── 11. ORDENAR POR PRECIO (Stream)
    public void ordenarPorPrecio() {

        System.out.println("\n══ PEDIDOS ORDENADOS POR PRECIO ══");
        listaGeneral.stream()
                .sorted(Comparator.comparingDouble(PedidoOnline::getPrecio))
                .forEach(System.out::println);
    }

    // ─── 12. AGRUPAR POR ESTADO (Stream)
    public void agruparPorEstado() {

        System.out.println("\n══ AGRUPADOS POR ESTADO ══");
        Map<String, List<PedidoOnline>> grupos = listaGeneral.stream()
                .collect(Collectors.groupingBy(PedidoOnline::getEstado));

        grupos.forEach((estado, lista) -> {
            System.out.println("\n▶ " + estado + " (" + lista.size() + "):");
            lista.forEach(p -> System.out.println("   " + p));
        });
    }

    // ─── 13. ESTADÍSTICAS (Stream)
    public void mostrarEstadisticas() {
        if (listaGeneral.isEmpty()) {

            System.out.println("Sin datos para estadísticas.");
            return;
        }
        DoubleSummaryStatistics stats = listaGeneral.stream()
                .mapToDouble(PedidoOnline::getPrecio)
                .summaryStatistics();

        System.out.println("\n══ ESTADÍSTICAS DE PRECIOS ══");
        System.out.printf("  Total pedidos : %d%n",   stats.getCount());
        System.out.printf("  Precio mínimo : %.2f%n", stats.getMin());
        System.out.printf("  Precio máximo : %.2f%n", stats.getMax());
        System.out.printf("  Precio promedio: %.2f%n",stats.getAverage());
        System.out.printf("  Suma total    : %.2f%n", stats.getSum());
    }

    // ─── 14. BUSCAR CON STREAM
    public void buscarConStream(String nombre) {
        System.out.println("\n══ BÚSQUEDA POR CLIENTE: " + nombre + " ══");
        listaGeneral.stream()

                .filter(p -> p.getNombreCliente().toLowerCase()
                        .contains(nombre.toLowerCase()))
                .forEach(System.out::println);
    }
}