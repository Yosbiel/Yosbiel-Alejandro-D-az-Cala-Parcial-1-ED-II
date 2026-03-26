/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionviviendas_parcial1_edii;

/**
 *
 * @author Yosbiel A
 */
public class CArbolGeneral {
    private ArbolGeneral<Object> arbol;
    
    public CArbolGeneral() {
        arbol = new ArbolGeneral<>();
    }
    
    public void iniciar() {
        System.out.println("SISTEMA DE GESTIÓN DE VIVIENDAS\n");
        
        inicializarEstructuraBase();
        cargarViviendasDePrueba();
        
        System.out.println("ESTRUCTURA COMPLETA");
        mostrarArbolCompleto();
        
        System.out.println("\nLISTAR VIVIENDAS POR CONSEJO POPULAR");
        listarViviendasPorCP("Guane 1");
        listarViviendasPorCP("Isabel Rubio");
        
        System.out.println("\nTOTAL DE VIVIENDAS EN EL MUNICIPIO");
        int total = contarViviendasEnMunicipio();
        System.out.println("Total: " + total + " viviendas");
        
        System.out.println("\nBUSCAR POR PROPIETARIO");
        buscarViviendaPorPropietario("Pérez");
        
        System.out.println("\nRECORRIDOS");
        arbol.bfs();
        arbol.dfs();
        
        System.out.println("\nMÉTRICAS");
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Nodos totales: " + arbol.contarNodos());
        System.out.println("Grado: " + arbol.gradoArbol());
    }
    
    private void inicializarEstructuraBase() {
        Pais cuba = new Pais("Cuba");
        NodoGeneral<Object> nodoCuba = new NodoGeneral<>(cuba);
        
        Provincia pinarRio = new Provincia("Pinar del Río");
        NodoGeneral<Object> nodoPinarRio = new NodoGeneral<>(pinarRio);
        arbol.agregarHijo(nodoCuba, nodoPinarRio);
        
        Municipio guane = new Municipio("Guane");
        NodoGeneral<Object> nodoGuane = new NodoGeneral<>(guane);
        arbol.agregarHijo(nodoPinarRio, nodoGuane);
        
        // Consejos Populares con datos reales
        agregarConsejoPopular(nodoGuane, "Guane 1", 6429, 19.70);
        agregarConsejoPopular(nodoGuane, "Guane 2", 7004, 60.30);
        agregarConsejoPopular(nodoGuane, "Isabel Rubio", 7049, 29.92);
        agregarConsejoPopular(nodoGuane, "Molina - El Valle", 3358, 105.50);
        agregarConsejoPopular(nodoGuane, "Sábalo", 3390, 61.50);
        agregarConsejoPopular(nodoGuane, "Combate de las Tenerías", 2132, 77.00);
        agregarConsejoPopular(nodoGuane, "Los Portales", 3306, 125.00);
        agregarConsejoPopular(nodoGuane, "Punta de la Sierra", 3050, 238.37);
        
        arbol.establecerRaiz(nodoCuba);
    }
    
    private void agregarConsejoPopular(NodoGeneral<Object> nodoMunicipio, String nombre, double poblacion, double area) {
        ConsejoPopular cp = new ConsejoPopular(nombre, poblacion, area);
        NodoGeneral<Object> nodoCP = new NodoGeneral<>(cp);
        arbol.agregarHijo(nodoMunicipio, nodoCP);
    }
    
    private void cargarViviendasDePrueba() {
        agregarVivienda("Guane 1", "Martí", 12, 4, "Pérez");
        agregarVivienda("Guane 1", "Céspedes", 8, 3, "García");
        agregarVivienda("Guane 2", "Línea", 23, 2, "Fernández");
        agregarVivienda("Isabel Rubio", "Principal", 10, 3, "Pérez");
        agregarVivienda("Sábalo", "Playa", 3, 4, "Rodríguez");
        agregarVivienda("Los Portales", "Cueva", 2, 4, "Pérez");
        agregarVivienda("Punta de la Sierra", "Monte", 1, 5, "Torres");
    }
    
    private void agregarVivienda(String nombreCP, String calle, int numero, int habitantes, String propietario) {
        NodoGeneral<Object> nodoCP = buscarConsejoPopular(nombreCP);
        if (nodoCP != null) {
            Vivienda vivienda = new Vivienda(calle, numero, habitantes, propietario);
            NodoGeneral<Object> nodoVivienda = new NodoGeneral<>(vivienda);
            arbol.agregarHijo(nodoCP, nodoVivienda);
        }
    }
    
    private NodoGeneral<Object> buscarConsejoPopular(String nombreCP) {
        return buscarConsejoPopularRecursivo(arbol.obtenerRaiz(), nombreCP);
    }
    
    private NodoGeneral<Object> buscarConsejoPopularRecursivo(NodoGeneral<Object> actual, String nombreCP) {
        if (actual == null) return null;
        
        Object info = actual.getInfo();
        if (info instanceof ConsejoPopular) {
            ConsejoPopular cp = (ConsejoPopular) info;
            if (cp.getNombre().equals(nombreCP)) {
                return actual;
            }
        }
        
        NodoGeneral<Object> hijo = actual.getPrimerHijo();
        while (hijo != null) {
            NodoGeneral<Object> encontrado = buscarConsejoPopularRecursivo(hijo, nombreCP);
            if (encontrado != null) return encontrado;
            hijo = hijo.getSiguienteHermano();
        }
        
        return null;
    }
    
    private void listarViviendasPorCP(String nombreCP) {
        NodoGeneral<Object> nodoCP = buscarConsejoPopular(nombreCP);
        if (nodoCP == null) {
            System.out.println("Consejo Popular no encontrado: " + nombreCP);
            return;
        }
        
        System.out.println("\n" + nombreCP + ":");
        NodoGeneral<Object> hijo = nodoCP.getPrimerHijo();
        int contador = 0;
        
        while (hijo != null) {
            Object info = hijo.getInfo();
            if (info instanceof Vivienda) {
                contador++;
                System.out.println("   " + contador + ". " + info.toString());
            }
            hijo = hijo.getSiguienteHermano();
        }
        
        if (contador == 0) {
            System.out.println("   (No hay viviendas registradas)");
        }
    }
    
    private int contarViviendasEnMunicipio() {
        return contarViviendasRecursivo(arbol.obtenerRaiz());
    }
    
    private int contarViviendasRecursivo(NodoGeneral<Object> actual) {
        if (actual == null) return 0;
        
        int total = 0;
        if (actual.getInfo() instanceof Vivienda) {
            total = 1;
        }
        
        NodoGeneral<Object> hijo = actual.getPrimerHijo();
        while (hijo != null) {
            total += contarViviendasRecursivo(hijo);
            hijo = hijo.getSiguienteHermano();
        }
        
        return total;
    }
    
    private void buscarViviendaPorPropietario(String propietario) {
        System.out.println("\nBuscando propietario: " + propietario);
        buscarViviendaPorPropietarioRecursivo(arbol.obtenerRaiz(), propietario);
    }
    
    private void buscarViviendaPorPropietarioRecursivo(NodoGeneral<Object> actual, String propietario) {
        if (actual == null) return;
        
        Object info = actual.getInfo();
        if (info instanceof Vivienda) {
            Vivienda v = (Vivienda) info;
            if (v.getPropietario().equalsIgnoreCase(propietario)) {
                System.out.println("   " + v.toString());
            }
        }
        
        NodoGeneral<Object> hijo = actual.getPrimerHijo();
        while (hijo != null) {
            buscarViviendaPorPropietarioRecursivo(hijo, propietario);
            hijo = hijo.getSiguienteHermano();
        }
    }
    
    private void mostrarArbolCompleto() {
        mostrarArbolRecursivo(arbol.obtenerRaiz(), "");
    }
    
    private void mostrarArbolRecursivo(NodoGeneral<Object> actual, String prefijo) {
        if (actual == null) return;
        
        System.out.println(prefijo + actual.getInfo().toString());
        
        NodoGeneral<Object> hijo = actual.getPrimerHijo();
        while (hijo != null) {
            mostrarArbolRecursivo(hijo, prefijo + "   ");
            hijo = hijo.getSiguienteHermano();
        }
    }
}
