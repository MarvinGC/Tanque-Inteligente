package ejemplos;


import processing.core.*;


public class Animacion extends PApplet{
	// Inicia el main
	public static void main(String[] args) {
		// Título de la ventana
		PApplet.main("ejemplos.Animacion");
	}

	Animation animation1, animation2;
	
	float xpos;
	float ypos;
	float drag = (float) 30.0;
	
	public void settings() {
		 size(640, 360);
	}
	
	public void setup() {
	  background(255, 204, 0);
	  frameRate(24);
	  animation1 = new Animation("PT_Shifty_", 38);
	  animation2 = new Animation("PT_Teddy_", 60);
	  ypos = (float) (height * 0.25);
	}
	
	public void draw() { 
	  float dx = mouseX - xpos;
	  xpos = xpos + dx/drag;
	
	  // Display the sprite at the position xpos, ypos
	  if (mousePressed) {
	    background(153, 153, 0);
	    animation1.display(xpos-animation1.getWidth()/2, ypos);
	  } else {
	    background(255, 204, 0);
	    animation2.display(xpos-animation1.getWidth()/2, ypos);
	  }
	}
	
	
	
	// Class for animating a sequence of GIFs
	
	class Animation {
	  PImage[] images;
	  int imageCount;
	  int frame;
	  
	  Animation(String imagePrefix, int count) {
	    imageCount = count;
	    images = new PImage[imageCount];
	
	    for (int i = 0; i < imageCount; i++) {
	      // Use nf() to number format 'i' into four digits
	      String filename = imagePrefix + nf(i, 4) + ".gif";
	      images[i] = loadImage(filename);
	    }
	  }
	
	  void display(float xpos, float ypos) {
	    frame = (frame+1) % imageCount;
	    image(images[frame], xpos, ypos);
	  }
	  
	  int getWidth() {
	    return images[0].width;
	  }
	}

}
