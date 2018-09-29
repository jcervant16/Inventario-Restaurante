/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.CrearProducto;
import vistas.VentasGui;

/**
 *
 * @author jose1
 */
public class CtrlProducto implements ActionListener {
    
    private Producto p = new Producto();
    private ConsultasProducto consultas = new ConsultasProducto();
    private CrearProducto GuiCp = new CrearProducto(null, true);
    private VentasGui vg= new VentasGui(null, true);
    
    public CtrlProducto (){
        
        this.GuiCp.btnCrear.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
     if(e.getSource() == this.GuiCp.btnCrear){
          JOptionPane.showMessageDialog(GuiCp,"hola");
//         p.setCodigoProducto(GuiCp.txtCodigo.getText());
//         p.setNombreProducto(GuiCp.txtNombreProducto.getText());
//         p.setTipoProducto(GuiCp.cmbTipoProducto.getSelectedItem().toString());
//         p.setPrecioProducto(Double.parseDouble(GuiCp.txtPrecio.getText()));
//        if(cp.registrar(p)){
//            DefaultTableModel modelo = (DefaultTableModel) vg.tlbProductosCreados.getModel();
//            modelo.setNumRows(0);
//            cp.llenarTablaProducto(vg.tlbProductosCreados);
//        }
        
     }
    }

   
    
}
