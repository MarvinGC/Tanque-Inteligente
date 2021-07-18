package Pruebas;


import processing.core.PApplet;


public class Objetos extends PApplet{
	int objetivox;
	int objetivoy;
	
	public static void main(String[] args) {
		PApplet.main("Pruebas.Objetos");
	}
	
	public void settings() {
		size(1150, 550);
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
		
		line(0,300,width,300);
		
		//Tanque
		fill(66, 134, 244);
		ellipse(10,300, 100, 100);
		
		fill(66, 134, 244);
		rect(10,300,100,200);
		
	}
	

}
