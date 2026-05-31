package com.unicartagena;

import java.util.Objects;

public class PedidoOnline {

    private String codigoPedido;
    private String nombreCliente;
    private String producto;
    private double precio;
    private String estado;

    public PedidoOnline(String codigoPedido, String nombreCliente,
                        String producto, double precio, String estado) {
        this.codigoPedido = codigoPedido;
        this.nombreCliente = nombreCliente;
        this.producto = producto;
        this.precio = precio;
        this.estado = estado;
    }

    // Getters
    public String getCodigoPedido() { return codigoPedido; }
    public String getNombreCliente() { return nombreCliente; }
    public String getProducto()      { return producto; }
    public double getPrecio()        { return precio; }
    public String getEstado()        { return estado; }

    // Setters
    public void setCodigoPedido(String codigoPedido) { this.codigoPedido = codigoPedido; }
    public void setNombreCliente(String nombreCliente){ this.nombreCliente = nombreCliente; }
    public void setProducto(String producto)          { this.producto = producto; }
    public void setPrecio(double precio)              { this.precio = precio; }
    public void setEstado(String estado)              { this.estado = estado; }

    @Override
    public String toString() {
        return "PedidoOnline{" +
                "codigo='"   + codigoPedido  + '\'' +
                ", cliente='" + nombreCliente + '\'' +
                ", producto='" + producto    + '\'' +
                ", precio="   + precio       +
                ", estado='"  + estado       + '\'' +
                '}';
    }

    // equals y hashCode por codigoPedido (obligatorio para Map y colecciones)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoOnline)) return false;
        PedidoOnline that = (PedidoOnline) o;
        return Objects.equals(codigoPedido, that.codigoPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPedido);
    }
}