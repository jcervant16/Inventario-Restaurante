
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose1
 */
public class ConsultasIngrediente extends Conexion {

    public boolean registrar(Ingrediente ing) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO ingredientes(nombreIngrediente, "
                + "tipoIngrediente, cantidadIngrediente, "
                + "unidad,codigo) VALUES (?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            // ps.setInt(0, ing.getIdIngrediente());
            ps.setString(1, ing.getNombreIngrediente());
            ps.setString(2, ing.getTipoIngrediente());
            ps.setDouble(3, ing.getCantidadIngrediente());
            ps.setString(4, ing.getUnidad());
            ps.setString(5, ing.getCodigoIngrediente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }

        }
        
    }
    public boolean registrarSeleccionado(Ingrediente ing,double valor) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO ingredienteseleccionado(idIngrediente,codigo,nombre, "
                + "tipo, cantidad, "
                + "unidad) VALUES (?,?,?,?,?,?)";

        try {
             ps = con.prepareStatement(sql);
            ps.setInt(1, ing.getIdIngrediente());
            ps.setString(2, ing.getCodigoIngrediente());
            ps.setString(3, ing.getNombreIngrediente());
            ps.setString(4, ing.getTipoIngrediente());
            ps.setDouble(5, ing.getCantidadIngrediente());
            ps.setString(6, ing.getUnidad());
             ps.execute();
            return true;
                 
            
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }

        }
    }
    public boolean modificar(Ingrediente ing) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE ingredientes SET nombreIngrediente=?, "
                + "tipoIngrediente=?, cantidadIngrediente=?, unidad=?,codigo=?"
                + "WHERE idIngredientes=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, ing.getNombreIngrediente());
            ps.setString(2, ing.getTipoIngrediente());
            ps.setDouble(3, ing.getCantidadIngrediente());
            ps.setString(4, ing.getUnidad());
            ps.setString(5, ing.getCodigoIngrediente());
            ps.setInt(6, ing.getIdIngrediente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }

        }
    }
      public boolean actualizarCantidad(Ingrediente ing, double resta) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE ingredientes SET  "
                + "cantidadIngrediente=?"
                + "WHERE idIngredientes=?";

        try {
            ps = con.prepareStatement(sql);
            if(resta>ing.getCantidadIngrediente()){
                return false;
            }else{
                
            
            ps.setDouble(1, ing.getCantidadIngrediente()-resta);
            ps.setInt(2, ing.getIdIngrediente());
            ps.execute();
            return true;
            }
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }

        }
      }

    public boolean eliminar(Ingrediente ing) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM ingredientes WHERE idIngredientes=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ing.getIdIngrediente());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }

        }

    }

    public boolean buscar(Ingrediente ing) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "SELECT * FROM ingredientes WHERE codigo=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ing.getCodigoIngrediente());
            rs = ps.executeQuery();
            if (rs.next()) {
                ing.setIdIngrediente(Integer.parseInt(rs.getString("idIngredientes")));
                ing.setCodigoIngrediente(rs.getString("codigo"));
                ing.setNombreIngrediente(rs.getString("nombreIngrediente"));
                ing.setTipoIngrediente(rs.getString("tipoIngrediente"));
                ing.setUnidad(rs.getString("unidad"));
                ing.setCantidadIngrediente(Double.parseDouble(rs.getString("cantidadIngrediente")));
                return true;

            }

            return false;
        } catch (SQLException e) {
            System.err.print(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e.toString());
            }

        }

    }
     

    public void llenarCombo(JComboBox combo) {
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT nombreIngrediente FROM ingredientes";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            combo.addItem("Seleccione producto");

            while (rs.next()) {
                combo.addItem(rs.getString("nombreIngrediente"));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());

        }
    }

    public void llenarTabla(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();

        Object[] filas = null;
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "SELECT idIngredientes,codigo,nombreIngrediente,"
                    + " tipoIngrediente, cantidadIngrediente, unidad FROM ingredientes";
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
    
    public void llenarTablaSeleccionado(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();

        Object[] filas = null;
        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
             String sql = "SELECT idIngrediente,codigo,nombre,"
                    + " tipo, cantidad, unidad FROM ingredienteseleccionado";
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
