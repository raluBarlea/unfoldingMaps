package project1;

import processing.core.*;


public class MyPApplet extends PApplet{
	
	//load the image on the applet page
	PImage picture;
	public void setup(){
		size(400,400);
		background(255, 204, 0);
		picture=loadImage("beach.jpg");
	
}
	
	public void draw(){
		//java recalculculate the height parameter
		//make the picture dinamic
		picture.resize(0, height);
		//display the image 
		image(picture, 0, 0);
		//draw an ellipse and pizition it with relative coordonates
		//fill method is for color of ellipse
		fill(255,209,0);
		ellipse(width/4, height/5, width/5, height/5);
		
	}
	
}
