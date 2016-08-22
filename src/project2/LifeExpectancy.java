package project2;

import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
UnfoldingMap map;
Map<String, Float> lifeExpByCountry;
private Map<String,Float> loadLifeExpectancyFromCVS(String fileName){
	Map<String,Float> lifeExpMap = new HashMap<String,Float>();
}
public void setup(){
	size(800,600,OPENGL);
	map = new UnfoldingMap(this, 50, 50, 700, 500,new Google.GoogleMapProvider());
	//interact with map
	MapUtils.createDefaultEventDispatcher(this,map);
	
}
public void draw(){
	map.draw();
}
}
