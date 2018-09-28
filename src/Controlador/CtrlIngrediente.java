/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultasIngrediente;
import Modelo.Ingrediente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.CrearProducto;
import vistas.IngredienteGUI;

/**
 *
 * @author jose1
 */
public class CtrlIngrediente implements ActionListener, ListSelectionListener {

    private Ingrediente ing;
    private ConsultasIngrediente cing;
    private IngredienteGUI ingGui;
    private CrearProducto cp = new CrearProducto(null, true);

    public CtrlIngrediente(Ingrediente ing, ConsultasIngrediente cing, IngredienteGUI ingGui) {
        this.ing = ing;
        this.cing = cing;
        this.ingGui = ingGui;
        this.ingGui.btnAgregar.addActionListener(this);
        this.ingGui.btnEditar.addActionListener(this);
        this.ingGui.btnEliminar.addActionListener(this);
        this.ingGui.btnCrearProducto.addActionListener(this);
        this.ingGui.btnAgregarSeleccionado.addActionListener(this);
        this.ingGui.tblInventario.getSelectionModel().addListSelectionListener(this);
    }

    public void iniciar() {
        this.ingGui.setTitle("Ingredientes");
        this.ingGui.setLocationRelativeTo(null);

    }
    public void limpiar(){
        ingGui.txtNombre.setText("");
        ingGui.txtCantidad.setText("");
        ingGui.txtCodigo.setText("");
        ingGui.txtId.setText("");
        ingGui.cmbTipo.setSelectedItem("Seleccione Tipo");
        ingGui.cmbUnidades.setSelectedItem("Seleccione unidad");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.ingGui.btnAgregar) {
            ing.setCodigoIngrediente((ingGui.txtCodigo.getText()));
            ing.setNombreIngrediente(ingGui.txtNombre.getText());
            ing.setTipoIngrediente(ingGui.cmbTipo.getSelectedItem().toString());
            ing.setCantidadIngrediente(Double.parseDouble(ingGui.txtCantidad.getText()));
            ing.setUnidad(ingGui.cmbUnidades.getSelectedItem().toString());
            if (cing.registrar(ing)) {
                
                DefaultTableModel modelo = (DefaultTableModel) ingGui.tblInventario.getModel();
                modelo.setNumRows(0);
                cing.llenarTabla(ingGui.tblInventario);
                this.limpiar();

               
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }

        }

        if (e.getSource() == this.ingGui.btnEditar) {
            ing.setIdIngrediente(Integer.parseInt(ingGui.txtId.getText()));
            ing.setNombreIngrediente(ingGui.txtNombre.getText());
            ing.setTipoIngrediente(ingGui.cmbTipo.getSelectedItem().toString());
            ing.setCantidadIngrediente(Double.parseDouble(ingGui.txtCantidad.getText()));
            ing.setUnidad(ingGui.cmbUnidades.getSelectedItem().toString());
            ing.setCodigoIngrediente((ingGui.txtCodigo.getText()));
            if (cing.modificar(ing)) {
                DefaultTableModel modelo = (DefaultTableModel) ingGui.tblInventario.getModel();
                modelo.setNumRows(0);
                cing.llenarTabla(ingGui.tblInventario);
                this.limpiar();
                JOptionPane.showMessageDialog(null, "registro editado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar");
            }
            
        }
        if (e.getSource() == this.ingGui.btnEliminar) {
            ing.setIdIngrediente(Integer.parseInt(ingGui.txtId.getText()));
            int x = JOptionPane.showConfirmDialog(null, "Está seguro de querer eliminar este elemento?");
            if(x==0){
                if (cing.eliminar(ing)) {
                     DefaultTableModel modelo = (DefaultTableModel) ingGui.tblInventario.getModel();
                     modelo.setNumRows(0);
                     cing.llenarTabla(ingGui.tblInventario);
                     this.limpiar();
                JOptionPane.showMessageDialog(null, "registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
            }
            

        }
        if (e.getSource() == this.ingGui.btnCrearProducto) {
                 cp.setVisible(true);
            
        }
         if (e.getSource() == this.ingGui.btnAgregarSeleccionado) {
          String cant = JOptionPane.showInputDialog("Que cantidad desea seleccionar?");
          double cantidad = Double.parseDouble(cant);
          
          ing.setIdIngrediente(Integer.parseInt(ingGui.txtId.getText()));
          ing.setCodigoIngrediente((ingGui.txtCodigo.getText()));
            ing.setNombreIngrediente(ingGui.txtNombre.getText());
            ing.setCantidadIngrediente(cantidad);
            ing.setTipoIngrediente(ingGui.cmbTipo.getSelectedItem().toString());
            ing.setUnidad(ingGui.cmbUnidades.getSelectedItem().toString());
            if (cing.registrarSeleccionado(ing,cantidad)) {
                
                DefaultTableModel modelo = (DefaultTableModel) cp.tbIngredienteSeleccionado.getModel();
                modelo.setNumRows(0);
                cing.llenarTablaSeleccionado(cp.tbIngredienteSeleccionado);
               /* Ingrediente ing2 =new Ingrediente();
                ing2.setIdIngrediente(Integer.parseInt(ingGui.txtId.getText()));
                ing2.setCantidadIngrediente(Double.parseDouble(ingGui.txtCantidad.getText()));
                cing.actualizarCantidad(ing2, cantidad);
                DefaultTableModel modelotblInventario = (DefaultTableModel) ingGui.tblInventario.getModel();
                modelotblInventario.setNumRows(0);
                cing.llenarTabla(ingGui.tblInventario);
                */this.limpiar();

               
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
            
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (ingGui.tblInventario.getSelectedRow() != -1) {
            int fila = ingGui.tblInventario.getSelectedRow();

            ingGui.txtId.setText(ingGui.tblInventario.getValueAt(fila, 0).toString());
            ingGui.txtCodigo.setText(ingGui.tblInventario.getValueAt(fila, 1).toString());
            ingGui.txtNombre.setText(ingGui.tblInventario.getValueAt(fila, 2).toString());
            ingGui.cmbTipo.setSelectedItem(ingGui.tblInventario.getValueAt(fila, 3).toString());
            ingGui.txtCantidad.setText(ingGui.tblInventario.getValueAt(fila, 4).toString());
            ingGui.cmbUnidades.setSelectedItem(ingGui.tblInventario.getValueAt(fila, 5).toString());

        }
        //To change body of generated methods, choose Tools | Templates.
    }

}
