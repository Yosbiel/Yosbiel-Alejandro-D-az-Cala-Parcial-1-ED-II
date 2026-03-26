/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionviviendas_parcial1_edii;

/**
 *
 * @author Yosbiel A
 */
public class Vivienda {
    private String calle;
    private int numero;
    private int habitantes;
    private String propietario;
    
    public Vivienda(String calle, int numero, int habitantes, String propietario) {
        this.calle = calle;
        this.numero = numero;
        this.habitantes = habitantes;
        this.propietario = propietario;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public int getHabitantes() {
        return habitantes;
    }
    
    public String getPropietario() {
        return propietario;
    }
    
    @Override
    public String toString() {
        return "Vivienda: " + calle + " #" + numero + " | " + habitantes + " hab | Propietario: " + propietario;
    }
}
