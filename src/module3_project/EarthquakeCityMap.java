package module3_project;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;
import processing.core.PShape;

public class EarthquakeCityMap extends PApplet{
	
	//the map
	private UnfoldingMap map;
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	PShape square;
	public void setup() {
		
		size(950, 600, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		int blue = color(0, 0, 255);
		int yellow = color(255, 255, 0);
		int red = color(255,0,0);
		//a list with simple point markers
		List<SimplePointMarker> markers = new ArrayList<SimplePointMarker>();
		
		List<PointFeature> earthquakes =ParseFeed.parseEarthquake(this, earthquakesURL);
		//add markers to every pointFeature
		for(PointFeature pt:earthquakes){
			 SimplePointMarker mk = new SimplePointMarker(pt.getLocation());
			 map.addMarker(mk);
			 markers.add(mk);	 
	    	//System.out.println(pt.getProperties());
			 
			//using the magnitude property from rssfeed
	    	Object magObj = pt.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	// PointFeatures also have a getLocation method
	    	if (mag > 4){
	    		mk.setColor(blue);
	    		mk.setRadius(3);
	    	}
	    	if ((mag > 4)&&(mag < 4.9)){
	    		mk.setColor(yellow);
	    		mk.setRadius(5);
	    	}
	    	if ( mag >= 5 ) {
	    		mk.setColor(red);
	    		mk.setRadius(7);
	    	}
	    }
		square = createShape(RECT, 0, 0, 60, 60);
	}
	public void draw(){
		map.draw();
		shape(square,50,25);
		
	}
//	public void addKey(){
//		PShape square = createShape(RECT, 0, 0, 100, 100);
//	}
}
