/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jose1
 */
public class Conexion {
    private static Connection conexion;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    private static final String url = "jdbc:mysql://localhost:3306/inventario";

    public Conexion() {
    conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url,user,pass);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error al conectar "+ e.getMessage());
        }
    
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void desconectar(){
        conexion = null;
    }
    
    
}
