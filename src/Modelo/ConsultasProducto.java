/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose1
 */
public class ConsultasProducto extends Conexion{
    
    
    public boolean registrar(Producto p){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO producto(codigo,nombreProducto,tipo,precio) VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigoProducto());
            ps.setString(2, p.getNombreProducto());
            ps.setString(3, p.getTipoProducto());
            ps.setDouble(4, p.getPrecioProducto());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }
        }
    }
    public boolean addCart(Producto p){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO ventas(codigoVenta,nombre,tipo,precio,cantidad,total) VALUES(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigoProducto());
            ps.setString(2, p.getNombreProducto());
            ps.setString(3, p.getTipoProducto());
            ps.setDouble(4, p.getPrecioProducto());
            ps.setInt(5, p.getCantidad());
            ps.setDouble(6, p.getPrecioProducto()*p.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }
        }
    }
    public void llenarTablaProducto(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();

        Object[] filas = null;
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT codigo,nombreProducto,"
                    + " tipo, precio FROM producto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (rs.next()) {
                filas = new Object[cantidadColumnas];
                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (Exception e) {
        }

    }
    public void llenarTablaVenta(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();

        Object[] filas = null;
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT codigoVenta,nombre,"
                    + " tipo, precio,cantidad,total FROM ventas";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (rs.next()) {
                filas = new Object[cantidadColumnas];
                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (Exception e) {
        }

    }
}
