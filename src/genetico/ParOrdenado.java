package genetico;

import java.util.Comparator;

public class ParOrdenado{
	
	int num;
	int[] ADN;
	ParOrdenado(){
		this(0,null);
	}
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
	public class OrdxNum implements Comparator<ParOrdenado> {
		
		public int compare(ParOrdenado a,  ParOrdenado b) { 
	        return a.getNum() - b.getNum();
	    } 
	}
	public class OrdxNumReverso implements Comparator<ParOrdenado> {
		
		public int compare(ParOrdenado a,  ParOrdenado b) { 
	        return b.getNum() - a.getNum();
	    } 
	}
	
}
