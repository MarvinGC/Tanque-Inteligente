package Pruebas;


import processing.core.PApplet;


public class Objetos extends PApplet{
	int objetivox;
	int objetivoy;
	
	public static void main(String[] args) {
		PApplet.main("Pruebas.Objetos");
	}
	
	public void settings() {
		size(1500, 800);
		objetivox = (int) (width*0.8);
		objetivoy = 490;
	}
	public void setup() {
		background(255);
		
	}
	public void draw() {
		
		background(255);
		
		escenografia();
		
	}
	public void escenografia() {
		
		line(0,500,width,500);
		
		//Tanque
		fill(66, 134, 244);
		ellipse(10,450, 100, 100);
		
		fill(66, 134, 244);
		rect(10,450,100,200);
		
	}
	

}
