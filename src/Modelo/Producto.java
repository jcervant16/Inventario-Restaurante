/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jose1
 */
public class Producto {
    private int idProducto;
    private String codigoProducto;
    private String nombreProducto;
    private String tipoProducto;
    private double precioProducto;
    private boolean descuento;
    private double cantidadDescuento;
    private Ingrediente listaIngredientes;

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

    public double getCantidadDescuento() {
        return cantidadDescuento;
    }

    public void setCantidadDescuento(double cantidadDescuento) {
        this.cantidadDescuento = cantidadDescuento;
    }

    public Ingrediente getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(Ingrediente listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }
    
    
    
}
