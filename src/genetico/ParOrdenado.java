/*Clase ParOrdenado
 * Ultima Revisión: 12/06/21
 * */

package genetico;

import java.util.Comparator;

public class ParOrdenado{
	
	int aptitud;
	int[] ADN;
	
	// Constructor ParOrdenado
	ParOrdenado(){
		// Valores por defecto del tiro parabólico
		this(0,null);
	}
	
	// Constructor Par Ordenado, recibiendo los valores
	ParOrdenado(int num, int[] adn){
		this.aptitud = num;
		this.ADN = adn;
	}
	
	
	public int getAptitud() {
		return aptitud;
	}
	public void setAptitud(int num) {
		this.aptitud = num;
	}
	public int[] getAdn() {
		return ADN;
	}
	public void setAdn(int[] arr) {
		this.ADN = arr;
	}
	
	// Comparador entre un numero y la ordenada
	public class OrdxNum implements Comparator<ParOrdenado> {
		
		public int compare(ParOrdenado a,  ParOrdenado b) { 
	        return a.getAptitud() - b.getAptitud();
	    } 
	}
	
	// Cumple la misma función del anterior comparador, pero a la inversa
	public class OrdxNumReverso implements Comparator<ParOrdenado> {
		
		public int compare(ParOrdenado a,  ParOrdenado b) { 
	        return b.getAptitud() - a.getAptitud();
	    } 
	}
	
}
