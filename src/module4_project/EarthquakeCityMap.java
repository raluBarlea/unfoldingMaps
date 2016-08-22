package module4_project;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.lf5.viewer.configure.MRUFileManager;

import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import module4.EarthquakeMarker;
import module5.CityMarker;
import parsing.ParseFeed;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet{
	
	//the map
	UnfoldingMap map;
	
	//rss feed
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	// city information
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";
	//list with markers for city and queke
	private List <Marker> cityMarkers;
	private List <Marker> quekeMarkers;
	private List<Marker> countryMarkers;
	
	//setup the canvas and map
	public void setup(){
		
		//initializing the map and set the canvas
		size(900,600,OPENGL);
		map = new UnfoldingMap(this, 200, 50, 650, 600, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		//create feature and markers for country.
		//is not necessary to add it on the map, use the information about it
		List <Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		//map.addMarkers(countryMarkers);
		
		//extract data about city and add it on the map
		List <Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for(Feature city:cities){
			cityMarkers.add(new CityMarker(city));
		}
		
		
		//a list of earthQueke
		List<PointFeature> earthQueke = ParseFeed.parseEarthquake(this, earthquakesURL);
		quekeMarkers = new ArrayList<Marker>();
		//check is queke is on the land
		for(PointFeature er:earthQueke){
			//int numberQueke=0;
			if (isLand(er)){
				//numberQueke++;
				quekeMarkers.add(new LandQuakeMarker(er));
			
			}else
				quekeMarkers.add(new OceanQuakeMarker(er));
				
		}
		//next line is use for visualization of earthqueke 
		
		
		printQuakes();
	
		map.addMarkers(quekeMarkers);
		map.addMarkers(cityMarkers);
		
		
	}
	public void draw(){
		background(0);
		mouseReleased();
		map.draw();
		addKey();
		//draw some buttons to interact with the map
		fill(255,255,255);
		//rect(x,y,with,height);
		rect(250,100,25,25);
				
		fill(100,100,100);
		rect(250,150,25,25);
		
	}
	public void mouseReleased(){
		if((mouseX>250) && (mouseX<275) && (mouseY>100) && (mouseY<125)){
			background(255,255,255);
		}else {
				if ( mouseX>250 && mouseX < 275 && mouseY > 150 && mouseY < 175 )
					background(100,100,100);
				}
			
	}
	public boolean isLand(PointFeature earthqueke){
		
		for(Marker country:countryMarkers){
			//printQueke();
			//check if is inside of some country
			if(isInCountry(earthqueke, country)){
				
			//Object n = x.getProperty("country");
			// System.out.println( country.getProperty("name"));
				
				return true;
			}
		}
		//not inside any country
		return false;
	}
	
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}

	private void printQuakes() {
		int totalWaterQuakes = quekeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numQuakes = 0;
			for (Marker marker : quekeMarkers)
			{
				EarthquekeMarker eqMarker = (EarthquekeMarker)marker;
				if (eqMarker.isOnLand()) {
					if (countryName.equals(eqMarker.getStringProperty("country"))) {
						numQuakes++;
					}
				}
			}
			if (numQuakes > 0) {
				totalWaterQuakes -= numQuakes;
				System.out.println(countryName + ": " + numQuakes);
			}
		}
		System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
	}
	public void addKey(){
		fill(255,255,240);
		rect(20,15,150,250);
		
		fill(0);
		textSize(12);
		textAlign(LEFT,CENTER);
		text("EarthQueke Key",50,75);
	}
}

	
