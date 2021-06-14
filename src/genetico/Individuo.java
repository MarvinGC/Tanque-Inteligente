/*Clase ParOrdenado
 * Ultima Revisión: 12/06/21
 * */

package genetico;

import java.util.Comparator;

public class Individuo{
	
	int aptitud;
	int[] ADN;
	
	// Constructor ParOrdenado
	Individuo(){
		// Valores por defecto del tiro parabólico
		this(0,null);
	}
	
	// Constructo Parordenado, recibiendo los valores
	Individuo(int num, int[] adn){
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
	
	// Comparador de la aptitud de los individuos entre mas grande mejor
	public class CompMayorMejor implements Comparator<Individuo> {
		
		public int compare(Individuo a,  Individuo b) { 
	        return a.getAptitud() - b.getAptitud();
		}
	}
	
	// Comparador de la aptitud de los individuos entre menos mejor
	public class CompMenosMejor implements Comparator<Individuo> {
		
		public int compare(Individuo a,  Individuo b) { 
	        return b.getAptitud() - a.getAptitud();
		}
	}
	
}
