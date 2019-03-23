package mx.unam.ciencias.edd.proyecto1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mx.unam.ciencias.edd.Lista;

/**
 * Proporciona la entrada en el formato deseado 
 * sobre el que trabajara nuestra aplicación.
 */
public class FormatoEntrada {

    /** Bandera "-r". */
    private boolean banderaR = false;

    /** Bandera "-o". */
    private boolean banderaO = false;

    /** Lista con nuestro texto deseado. */
    private Lista<String> texto = new Lista<String>();

    /** Dirección en caso de salida. */
    private String salida;

    /** Constructor que genera nuestro formato. 
     * @param args Dirección de los datos de entrada.
     */
    FormatoEntrada(String[] args){
        entrada(args);
    }

    /**
     * Regresa una lista con nuestro formato de entrada.
     * @param args Dirección de los datos de entrada.
     */  
    public void entrada(String[] args) {

        // Verificamos si en los argumentos hay archivos de entrada y en caso de que sí procedemos a insertarlos en texto.
        Lista<String> texto = new Lista<>();
        boolean hayEntrada = false;
        for(int i = 0; i < args.length; i++){   
                
            if(esBanderaR(args[i]))
                banderaR = true;

            else if(esBanderaO(args[i])){

                try{
                    salida = args[++i];
                }
                catch(Exception e){
                    throw new IndexOutOfBoundsException("Hace falta un argumento.");
                } 
            }
            else {
                hayEntrada = true;
                agregaArchivo(args[i]);
            }
        }
        //En caso de no haber tenido archivo de entrada leemos desde la consola
        if(!hayEntrada){
            entradaEstandar(); 
        }

    }

    /**
     * Agrega los elementos desde entrada estándar
     */
    private void entradaEstandar(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cad;
        boolean hayLinea = true;
        while(hayLinea){
            try{
                cad = reader.readLine();
            }
            catch(Exception e){
                hayLinea = false;
            }
        }
    }

    /** 
     * Agrega los elementos de un archivo dada su dirección.
     * @param dir La dirección del archivo.
     */
    private void agregaArchivo(String dir){

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(dir));
            String line = reader.readLine();
            while(line != null){
                texto.agrega(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch(Exception e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }
    }

    /**
     * Recibe una cadena y revisa 
     * si corresponde a la bandera "-r". 
     * @param s Cadena a comparar.
     */
    private boolean esBanderaR(String s){
        return (s.equals("-r"));
    }

    /**
     * Recibe una cadena y revisa 
     * si corresponde a la bandera "-o". 
     * @param s Cadena a comparar.
     */
    private boolean esBanderaO(String s){
        return (s.equals("-o"));
    }
    /** Regresa la lista con nuestro texto */
    public Lista<String> getTexto(){
        return texto;
    }

    /** Verifica si hay archivo de salida*/
    public boolean haySalida(){
        return (salida != null);
    }   

    /** Obtenemos la dirección del archivo de salida */
    public String getSalida(){
        return salida;
    }

    /** Revisa si la bandera de reversa está activada */
    public boolean hayReversa(){
        return banderaR;
    }

}
