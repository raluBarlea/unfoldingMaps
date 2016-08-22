package lesson3_w2;
import de.fhpotsdam.unfolding.geo.Location;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet {
private UnfoldingMap map;
public void setup(){
	size(950,600,OPENGL);//set canvas dimension
	map =new UnfoldingMap(this, 150, 50, 700, 500,new Google.GoogleMapProvider());
	//200,50 position 
	map.zoomLevel(2);
	MapUtils.createDefaultEventDispatcher(this, map);
	Location valLoc = new Location(-38.142,-73.03f);
	Location nigeria = new Location(9.05,7.32);
	Location niger = new Location(13.48,9.00);
	//add properties to the new location
	List <PointFeature> bigEqs = new ArrayList<PointFeature>();
	PointFeature valEq = new PointFeature(valLoc);
	PointFeature valNigeria = new PointFeature(nigeria);
	PointFeature valNiger = new PointFeature(niger);
		valEq.addProperty("title","Valdivia,Chile");
		valEq.addProperty("magnitude", "9.5");
		valEq.addProperty("date", "May 22,1960");
		valEq.addProperty("year", "1960");
		valNigeria.addProperty("year", "2001");
		valNiger.addProperty("year", "1999");
	bigEqs.add(valEq);
	bigEqs.add(valNigeria);
	bigEqs.add(valNiger);
	
	List<Marker> markers = new ArrayList<Marker>();
	for(PointFeature eq:bigEqs){
		markers.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
	}
	map.addMarkers(markers);//add the marker to the map
	//set the marker color depending of the year
	int red = color(255,0,0);
	int gray = color(220,220,220);
	int year;
	for(Marker mk : markers){
		year = Integer.parseInt((String) mk.getProperty("year"));
		if ( year > 2000)
		{
			mk.setColor(red);
		}else
		{
			mk.setColor(gray);
		}
		}
		
}
public void draw(){
	
	background(220,220,220);//set color to canvas
	map.draw();
	//addKey();//draw a map legend using methods from processing library
}

}
