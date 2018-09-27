/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Controlador.CtrlIngrediente;
import Modelo.ConsultasIngrediente;
import Modelo.Ingrediente;
import vistas.CrearProducto;
import vistas.IngredienteGUI;

/**
 *
 * @author jose1
 */
public class InventarioApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ingrediente in = new Ingrediente();
        ConsultasIngrediente ci = new ConsultasIngrediente();
        IngredienteGUI ingui = new IngredienteGUI(null, true);
      //  CrearProducto cp = new CrearProducto(null, true);
        
        CtrlIngrediente control = new CtrlIngrediente(in, ci, ingui);
        control.iniciar();
        ingui.setVisible(true);
    }
    
}
