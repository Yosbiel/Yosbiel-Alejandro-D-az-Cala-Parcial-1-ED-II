/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionviviendas_parcial1_edii;

/**
 *
 * @author Yosbiel A
 */
public class Pais {
    private String nombre;
    
    public Pais(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return "País: " + nombre;
    }
}
