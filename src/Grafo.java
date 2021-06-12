/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rip.Torres! ^-^
 */
public class Grafo {
    NodoVertice vertice;
    int con = 0;
    
    public Grafo(){
        vertice = null;
    }
    
    public boolean insertarVertice(char dato){
        NodoVertice nuevo = new NodoVertice(dato);
        if(nuevo == null) return false;
        
        if(vertice == null){
            vertice = nuevo;
            con++;
            return true;
        }
        
        //El nuevo se enlaza a la lista de vertices.
        irUltimo();
        vertice.sig = nuevo;
        nuevo.ant = vertice;
        con++;
        return true;
    }
    
    private void irUltimo(){
        while(vertice.sig!=null){
            vertice = vertice.sig;
        }
    }
    
    private void irPrimero(){
        while(vertice.ant!=null){
            vertice = vertice.ant;
        }
    }   
       
    private NodoVertice buscarVertice(char dato){
        if(vertice == null) return null;
        
        for(NodoVertice buscar = vertice; buscar!=null; buscar = buscar.sig){
            if(buscar.dato == dato){
                return buscar;
            }
        }
        return null;
    }
    
    public boolean insertarArista(char origen, char destino){
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        
        if(nodoOrigen == null || nodoDestino == null) return false;
        
        return nodoOrigen.insertarArista(nodoDestino);
    }
    
    public boolean eliminarArista(char origen, char destino){
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        if(nodoOrigen == null || nodoDestino == null) return false;
        
        return nodoOrigen.eliminarArista(nodoDestino);
    }
    
    public boolean unSoloVertice(){
        return vertice.ant == null && vertice.sig == null;
    }
    
    public boolean eliminarVertice(char dato){
        if(vertice == null) return false;
        NodoVertice temp = buscarVertice(dato);
        if(temp == null) return false;
        
        if(temp.arista != null) return false;
        
        quitaAristasDeOtrosVertice(temp);
        if(temp==vertice){
            if(unSoloVertice()) vertice = null;
            else{
                vertice = temp.sig;
                temp.sig.ant = temp.sig = null;
            }
            return true;
        }
        
        if(temp.sig == null){
            temp.ant.sig = temp.ant = null;
            return true;
        }
        
        temp.ant.sig = temp.sig;
        temp.sig.ant = temp.ant;
        temp.sig = temp.ant = null;
        return true;
    }
    
    private void quitaAristasDeOtrosVertice(NodoVertice NodoEliminar){
        irPrimero();
        for(NodoVertice buscar = vertice; buscar!=null; buscar = buscar.sig){
            buscar.eliminarArista(NodoEliminar);
        }
    }
    
    public int contarVertice(){
        int n = 0;
        for(NodoVertice contar = vertice; contar!=null; contar = contar.sig){
            n = n++;
        }
        return n;
    }
    
    public String crearMatrizDeAdyacencia(){        
        if(vertice == null) return null;
        if(vertice.arista==null) return null;
        String[][] matriz = new String[con][con];
        
        for(int r = 0; r<matriz.length; r++){
            for(int c = 0; c<matriz.length; c++){
                matriz[r][c]=0+"";
            }       
        }
        irPrimero();
        for(int a = 0; a<matriz.length; a++){
            for(int b = 0; b<matriz.length; b++){
                if(vertice.arista==null){
                    a++;
                    b=0;
                    vertice=vertice.sig;
                }else{
                    matriz[a][b]="1";
                    vertice.arista = vertice.arista.abajo;
                }
            }
        }
        return mostrarMatriz(matriz);
    }
    
    public String mostrarMatriz(String[][] matriz){
        String cadena = "";
        for(int q = 0; q<matriz.length; q++){
            for(int w = 0; w<matriz.length; w++){
                cadena += "["+matriz[q][w]+"]";
            }
            cadena += "\n";
        }
        return cadena;
    }
   
    public String listaDeAdyacencia(char dato){
        return buscarVertice(dato).toString();
    }
    
    
}
