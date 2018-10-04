/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConsultasIngrediente;
import Modelo.ConsultasProducto;
import Modelo.Ingrediente;
import Modelo.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.CrearProducto;
import vistas.IngredienteGUI;
import vistas.Principal;
import vistas.VentasGui;

/**
 *
 * @author jose1
 */
public class CtrlIngrediente implements ActionListener, ListSelectionListener {

    private Ingrediente ing;
    private ConsultasIngrediente cing;
    private IngredienteGUI ingGui;
    private CrearProducto cp = new CrearProducto(null, true);
    private Producto p = new Producto();
    private ConsultasProducto consultas = new ConsultasProducto();
    private VentasGui vg= new VentasGui(null, true);
    private Principal inicio;

    public CtrlIngrediente(Ingrediente ing, ConsultasIngrediente cing, IngredienteGUI ingGui,Principal p) {
        this.ing = ing;
        this.cing = cing;
        this.ingGui = ingGui;
        this.inicio = p;
        this.ingGui.btnAgregar.addActionListener(this);
        this.ingGui.btnEditar.addActionListener(this);
        this.ingGui.btnEliminar.addActionListener(this);
        this.ingGui.btnCrearProducto.addActionListener(this);
        this.ingGui.btnAgregarSeleccionado.addActionListener(this);
        this.ingGui.tblInventario.getSelectionModel().addListSelectionListener(this);
//        this.vg.tblVentas.getSelectionModel().addListSelectionListener(this);
        this.cp.btnCrear.addActionListener(this);
        this.inicio.jButton1.addActionListener(this);
        this.inicio.jButton2.addActionListener(this);

    }

   

    public void iniciar() {
        this.inicio.setTitle("Principal");
        this.inicio.setLocationRelativeTo(null);

    }

    public void limpiar() {
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
            if(ingGui.txtId.getText().isEmpty()||ingGui.txtCodigo.getText().isEmpty()||ingGui.txtNombre.getText().isEmpty()||
              ingGui.txtCantidad.getText().isEmpty()||ingGui.cmbTipo.getSelectedItem().equals("Seleccione Tipo")||
              ingGui.cmbUnidades.getSelectedItem().equals("Seleccione Tipo")){
                JOptionPane.showMessageDialog(null, "Hay algún campo vacio");
            }else{
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
        }

        if (e.getSource() == this.ingGui.btnEditar) {
            if(ingGui.txtId.getText().isEmpty()||ingGui.txtCodigo.getText().isEmpty()||ingGui.txtNombre.getText().isEmpty()||
              ingGui.txtCantidad.getText().isEmpty()||ingGui.cmbTipo.getSelectedItem().equals("Seleccione Tipo")||
              ingGui.cmbUnidades.getSelectedItem().equals("Seleccione Tipo")){
                JOptionPane.showMessageDialog(null, "No editó ningun campo");
            }else{
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
                JOptionPane.showMessageDialog(null, "Registro editado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar");
            }
            }

        }
        if (e.getSource() == this.ingGui.btnEliminar) {
            ing.setIdIngrediente(Integer.parseInt(ingGui.txtId.getText()));
            int x = JOptionPane.showConfirmDialog(null, "Está seguro de querer eliminar este elemento?");
            if (x == 0) {
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
            if (cing.registrarSeleccionado(ing, cantidad)) {

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
                 */
                this.limpiar();

            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }

        }
        if (e.getSource() == this.cp.btnCrear) {

            p.setCodigoProducto(cp.txtCodigo.getText());
            p.setNombreProducto(cp.txtNombreProducto.getText());
            p.setTipoProducto(cp.cmbTipoProducto.getSelectedItem().toString());
            p.setPrecioProducto(Double.parseDouble(cp.txtPrecio.getText()));
            if (consultas.registrar(p)) {
                DefaultTableModel modelo = (DefaultTableModel) vg.tlbProductosCreados.getModel();
                modelo.setNumRows(0);
                consultas.llenarTablaProducto(vg.tlbProductosCreados);
                JOptionPane.showMessageDialog(null, "Producto Creado exitosamente");
            
            }else {
            JOptionPane.showMessageDialog(null, "Error al guardarProducto");
        }
        }
          if (e.getSource() == this.inicio.jButton1) {
              ingGui.setLocationRelativeTo(null);
              ingGui.setSize(815, 508);
              ingGui.setVisible(true);
           
        }
          if (e.getSource() == this.inicio.jButton2) {
              vg.setLocationRelativeTo(null);
              vg.setSize(573, 473);
              vg.setVisible(true);
           
        }
          if (e.getSource() == this.vg.btnaddCart) {
              
           
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
