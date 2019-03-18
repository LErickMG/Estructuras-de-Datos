package mx.unam.ciencias.edd.proyecto1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto1.FormatoEntrada;
import mx.unam.ciencias.edd.proyecto1.Comparador;

import java.util.Iterator;

/**
 * Uso de la aplicación.
 */
public class UsoProyecto1 {

    /**
     * Clase principal que corre la aplicación.
     * @param args 
     */  
    public static void uso(String[] args) {
        
        FormatoEntrada archivo = new FormatoEntrada(args);
        if(!archivo.haySalida())
            imprimeConsola(archivo);
        else 
            imprimeEnArchivo(archivo);
    }

    /**
     * Recibe una lista y lo imprime en consola.
     * @param archivo Formato a imprimir.
     */
    public static void imprimeConsola(FormatoEntrada archivo){

        Lista<String> ord = archivo.getTexto().mergeSort(new Comparador());
        
        if(archivo.hayReversa())
            ord = ord.reversa();

        boolean saltoLinea = false;
        for(String str : ord){
            if(saltoLinea)
                System.out.println("");
            System.out.print(str);
            saltoLinea = true;
        }
    }

    /**
     * Recibe una lista y genera un archivo de salida con el texto en ella.
     * @param archivo Formato a imprimir.
     */
    public static void imprimeEnArchivo(FormatoEntrada archivo){

        try{

            Lista<String> ord = archivo.getTexto().mergeSort(new Comparador());

            if(archivo.hayReversa())
            ord = ord.reversa();

            File file;
            FileWriter writer;
            file = new File(archivo.getSalida());
            writer = new FileWriter(file);
            boolean saltoLinea = false;
            for(String str : ord){
                if(saltoLinea) 
                    writer.write("\n");
                writer.write(str);
                saltoLinea = true;     
            }
            writer.close();
            ord.limpia();
        }
        catch(IOException e){
            e.printStackTrace();
        }
            
    }

}
