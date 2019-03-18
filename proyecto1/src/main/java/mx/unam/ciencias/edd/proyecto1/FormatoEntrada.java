package mx.unam.ciencias.edd.proyecto1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        Lista<String> texto = new Lista<>();

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
                agregaArchivo(args[i]);
            }
        }
    }

    /** 
     * Agrega los elementos de un archivo dada su dirección.
     * @param dir La dirección del archivo.
     */
    public void agregaArchivo(String dir){

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

    public Lista<String> getTexto(){
        return texto;
    }

    public boolean haySalida(){
        return (salida != null);
    }

    public String getSalida(){
        return salida;
    }

    public boolean hayReversa(){
        return banderaR;
    }

}
