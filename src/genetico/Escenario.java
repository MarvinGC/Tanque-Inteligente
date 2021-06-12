/*Proyeto Tanque
 * Proyecto para Inteligencia Artificial
 * Versión 1.1
 * Ultima modificación: 12/06/21
 * Esperando a ser más optimizado*/


package genetico;

// Importan Librerias
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/// Variables Cambiantes 

public class Escenario extends PApplet{
	public static void main(String[] args) {
		PApplet.main("genetico.Escenario");
	}
	
	//Processing
	float i = 0;
	TiroParabolico tp ; 
	int objetivox;
	int objetivoy;
	int[][] poblacion;
	ArrayList<ParOrdenado> vivos;
	int in = 0;
	
	
	// Evolucion
	int modelo = 36;
	int material_g = 10;
	int num_pob = 10;
	int presion = (int) (num_pob * 0.2);
	float mutacion_v = (float) 0.3;
	boolean mejor = false;

	
	public void settings() {
		size(1500, 800);
		objetivox = (int) (width*0.8);
		objetivoy = 490;
	}
	public void setup() {
		PImage titlebaricon = loadImage("icon.png");
		surface.setIcon(titlebaricon);
		surface.setResizable(true);
		//surface.setTitle("SimpleAI - Intelligent Points");
		background(255);
		
		//Inicializar una poblacion
		poblacion = crearPoblacion(); 
		vivos = new ArrayList<ParOrdenado>();
		
		System.out.println("Poblacion Inicial:");
		
		//Se muestra la poblacion inicial
		mostrarArrCuad(poblacion);
		int x = sumX(poblacion[in]);
		int ang = sumAng(poblacion[in]);
		in++;
		
		tp = new TiroParabolico(x,ang,0,0);
	}
	public void draw() {
		
		background(255);
		bala(20,480);
		
		escenografia();
		
	}
	public void escenografia() {
		
		line(0,500,width,500);
		
		//Tanque
		fill(66, 134, 244);
		ellipse(10,490, 20, 20);
		
		//Objetivo
		fill(66, 134, 244);
		ellipse(objetivox,objetivoy, 20, 20);
	}
	public void bala(int posx, int posy) {
		
		if(tp.pixelesY() - posy > -500) {

			fill(247, 4, 4);
			//System.out.println("x --> " + tp.posicionX() + " y --> " + tp.posicionY());
			ellipse((float) tp.pixelesX() + posx, 
					(float) (tp.pixelesY() - posy) * -1,
					10,10);
			tp.t += 0.01;
		}
		else {
			System.out.println("\n"+(int) (tp.pixelesX() + posx));
			evaluar((int) (tp.pixelesX() + posx));
			if(in == num_pob) {
				poblacion = seleccionNatural();
				mostrarArrCuad(poblacion);
				vivos = new ArrayList<ParOrdenado>();
				in = 0;
			}
			int x = sumX(poblacion[in]);
			int ang = sumAng(poblacion[in]);
			
			tp = new TiroParabolico(x,ang,0,0);
		}		
	}
	public void evaluar(int puntaje) {
		int a = (int) (aptitud(poblacion[in],puntaje) * 1000000)+1;
		vivos.add(new ParOrdenado(a,poblacion[in]));
		in++;
	}

	
	public int[]  individuo(int min, int max){
        int[] arreglo = new int[material_g];
        for (int i = 0; i < material_g; i++) {
            arreglo[i] = new Random().nextInt(max) + min;
        }
        return arreglo;
    }

    public int[][] crearPoblacion(){
    	int[][] poblacion = new int[num_pob][material_g];
    	
    	for (int i = 0; i < num_pob; i++) {
			poblacion[i] =  individuo(1,9);
		}
        return poblacion;
    }
    
    public float aptitud(int[] individuo, int puntaje) {
    	int diferencia = Math.abs(objetivox - puntaje);
    	if(diferencia == 0)
    		mejor = true;

    	return (float) (1.0 / (1.0 + diferencia)); 
    }
	public int sumX(int[] individuo) {
		int x = 0;
		for (int i = 0; i < individuo.length/2; i++) {
			x += individuo[i];
		}
		return x;
	}
	public int sumAng(int[] individuo) {
		int x = 0;
		for (int i = individuo.length/2; i < individuo.length; i++) {
			x+=individuo[i];
		}
		return x;
	}
	public int[][] seleccionNatural(){
		
	    //Ordena los pares ordenados y se queda solo con el array de valores
		Collections.sort(vivos,new ParOrdenado().new OrdxNum());
		
		/*El mejor*/
		ParOrdenado mejor = new ParOrdenado(
										vivos.get(vivos.size()-1).num,
										vivos.get(vivos.size()-1).ADN);
		System.out.println("\nMejor: "+mejor.num);
		mostrarArr(mejor.ADN);
		System.out.println();
		

		int[][] poblacion = new int[num_pob][material_g];
		ArrayList<Integer> rifa = new ArrayList<Integer>();
		
		//Sorteo
		for (ParOrdenado especimen : vivos) {
			
			int puntaje = especimen.getNum();
			
			for(int i=0; i<puntaje; i++) {
				rifa.add(vivos.indexOf(especimen));
			}
		}
		
		//Se mezcla el material genetico para crear nuevos individuos
	    for (int i = 0; i < num_pob; i++){
	    	
	        //Se elige un punto para hacer el intercambio
	        int punto = new Random().nextInt(material_g-1)+1; 
	        //Se eligen dos padres
	        int a = Math.round((float) (Math.random() * (rifa.size() - 1)));
	        int b = a;
	        while(b==a)
	        	b = Math.round((float) (Math.random() * (rifa.size() - 1)));
			
	        //Se mezcla el material genetico de los padres en cada nuevo individuo
			poblacion[i] = mitosis(vivos.get(rifa.get(a)).ADN,
									vivos.get(rifa.get(b)).ADN, 	punto);
	    }
	    
		return mutacion(poblacion);
	}
	public int[][] mutacion(int[][]population) {
	    int rango = population.length - presion;
	    for (int i = 0; i < rango ; i++) {
	    	Random a = new Random();
	        if(a.nextFloat() <= mutacion_v) {
	            int punto = a.nextInt(material_g-1);
	            int nuevo_valor = a.nextInt(8) + 1;
	            
	            //el nuevo no sea igual al viejo
	            while( nuevo_valor == population[i][punto])
	                nuevo_valor = a.nextInt(8) + 1;
	  
	            //Se aplica la mutacion
	            population[i][punto] = nuevo_valor;
	        }
	    }
	  
	    return population;
    }
	public int[] mitosis(int[] a, int[] b, int punto) {
        int[] arr = new int[material_g];
        for (int i = 0; i < material_g; i++) {
			if(i<punto)
				arr[i] = a[i];
			else
				arr[i] = b[i];
		}
		return arr;
	}
	public int[][] listaAArr(ArrayList<ParOrdenado> lista) {
		int[][] arr = new int[lista.size()][0];
		for (int i = 0; i < lista.size(); i++) {
			arr[i] = lista.get(i).ADN;
		}
		return arr;
	}
	public void mostrarArr(int[] arr) {
		System.out.print("{");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j]);
			if(j<arr.length-1)
				System.out.print(",");
		}
		System.out.print("}");
	}
	public void mostrarArrCuad(int[][] arr) {
		System.out.print("{");
		for (int i = 0; i < arr.length; i++) {
			System.out.print("{");
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
				if(j<arr[i].length-1)
					System.out.print(",");
			}
			System.out.print("}");
			if(i<arr.length-1)
				System.out.print(", ");
		}
		System.out.print("}");
	}
}
