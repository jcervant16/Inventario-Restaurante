
package Modelo;


public class Ingrediente {
    private int idIngrediente;
    private String codigoIngrediente;
    private String nombreIngrediente;
    private String tipoIngrediente;
    private double cantidadIngrediente;
    private String unidad;

    public String getCodigoIngrediente() {
        return codigoIngrediente;
    }

    public void setCodigoIngrediente(String codigoIngrediente) {
        this.codigoIngrediente = codigoIngrediente;
    }
    
    
    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getTipoIngrediente() {
        return tipoIngrediente;
    }

    public void setTipoIngrediente(String tipoIngrediente) {
        this.tipoIngrediente = tipoIngrediente;
    }

    public double getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(double cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
}
