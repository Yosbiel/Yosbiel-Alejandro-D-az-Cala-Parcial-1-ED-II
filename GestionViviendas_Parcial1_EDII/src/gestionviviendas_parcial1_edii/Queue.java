/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionviviendas_parcial1_edii;

/**
 *
 * @author Yosbiel A
 */
public interface Queue <X> {
    
    void add(X item);
    
    X poll();
    
    X peek();
    
    boolean isEmpty();
    
}
