/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Controlador.CtrlIngrediente;
import Modelo.ConsultasIngrediente;
import Modelo.Ingrediente;
import vistas.IngredienteGUI;
import vistas.Principal;

/**
 *
 * @author jose1
 */
public class InventarioApp  {
    
    
    public static void main(String[] args) {
        Ingrediente in = new Ingrediente();
        ConsultasIngrediente ci = new ConsultasIngrediente();
        IngredienteGUI ingui = new IngredienteGUI(null, true);
        
        Principal p = new Principal();
        
//        Producto p = new Producto();
//        ConsultasProducto cp= new ConsultasProducto();
//        CrearProducto GuiCp = new CrearProducto(null, true);
// 
//        CtrlProducto controlp = new CtrlProducto(p, cp, GuiCp);
        
        CtrlIngrediente control = new CtrlIngrediente(in, ci, ingui,p);
        
        control.iniciar();
        p.setSize(740, 331);
        p.setVisible(true);
    }

   
    
}
