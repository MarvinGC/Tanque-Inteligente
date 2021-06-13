/*Clase ParOrdenado
 * Función: 
 * */

package genetico;

import java.util.Comparator;

public class ParOrdenado{
	
	int num;
	int[] ADN;
	
	// Constructor ParOrdenado
	ParOrdenado(){
		// Valores por defecto del tiro parabólico
		this(0,null);
	}
	
	// Constructo Parordenado, recibiendo los valores
	ParOrdenado(int num, int[] arr){
		this.num = num;
		this.ADN = arr;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int[] getArr() {
		return ADN;
	}
	public void setArr(int[] arr) {
		this.ADN = arr;
	}
	
	// Comparador entre un numero y la ordenada
	public class OrdxNum implements Comparator<ParOrdenado> {
		
		public int compare(ParOrdenado a,  ParOrdenado b) { 
	        return a.getNum() - b.getNum();
	    } 
	}
	
	// Cumple la misma función del anterior comparador, pero a la inversa
	public class OrdxNumReverso implements Comparator<ParOrdenado> {
		
		public int compare(ParOrdenado a,  ParOrdenado b) { 
	        return b.getNum() - a.getNum();
	    } 
	}
	
}
