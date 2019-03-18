package mx.unam.ciencias.edd.proyecto1;

import java.util.Comparator;
import java.text.Normalizer;

public class Comparador implements Comparator<String>{

	@Override public int compare(String a, String b){

		a = a.toLowerCase();
		b = b.toLowerCase();

		a = a.replaceAll(" ","");
		b = b.replaceAll(" ","");

		a = cleanString(a);
		b = cleanString(b); 

		String x = "";
		String y = "";

		for(int i = 0; i < a.length(); i++){
			if(('a' <= a.charAt(i) && a.charAt(i) <= 'z') || ('0' <= a.charAt(i) &&  a.charAt(i) <= '9'))
				x += a.charAt(i);
		}
		for(int i = 0; i < b.length(); i++){
			if(('a' <= b.charAt(i) && b.charAt(i) <= 'z') || ('0' <= b.charAt(i) &&  b.charAt(i) <= '9'))
				y += b.charAt(i);
		}

		return x.compareTo(y);

	}

	private String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "x");
        return texto;
    }

}