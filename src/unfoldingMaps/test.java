package unfoldingMaps;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;
import processing.core.PGraphics;

//An application that are representing earthquake base on the rssfeed
public class Test extends PApplet {
	//file with informations about city
	private String cityFile = "city-data.json";
	
	//file with informations about country
	private String countryFile = "countries.geo.json";
	
	//The map
	private UnfoldingMap map;
	
	//marker for each city
	private List<Marker> cityMarker;
	
	//marker for each country
	private List<Marker> countryMarker;
	private List<Marker> quekeMarker;
	//rssfile for earthqueke
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	private PGraphics pg;
	
	public void setup(){
		
		//Initializing the canvas
		size(900,700,OPENGL);
		//create and set the position of the map
		map = new UnfoldingMap(this,  200, 50, 650, 600, new Google.GoogleMapProvider());
		
		MapUtils.createDefaultEventDispatcher(this, map);
		
		//extract the information from city-file
		List<Feature> city = GeoJSONReader.loadData(this, cityFile);
		cityMarker = new ArrayList<Marker>();
		for(Feature cityFeature:city){
			cityMarker.add(new CityMarker(cityFeature));
		}
		//add the markers on the map
		map.addMarkers(cityMarker);
		
		//extract the information from the country file
		List<Feature> country = GeoJSONReader.loadData(this, countryFile);
		countryMarker = new ArrayList<Marker>();
	
		//put markers where a earthQueke had place
		List<PointFeature> queke = ParseFeed.parseEarthquake(this, earthquakesURL);
		quekeMarker = new ArrayList<Marker>();
		for(PointFeature q:queke){
			quekeMarker.add(new SimplePointMarker(q.getLocation()));
		}
		map.addMarkers(quekeMarker);
	}
	
	public void draw(){
		map.draw();
		onClick(cityMarker);
	}
	
	public void onClick(List<Marker> list){
		
		for(Marker mk:list){
			if(mk.isInside(map, mouseX, mouseY)){
				//((CityMarker) mk).showTitle(pg,5,5);
				System.out.println(((CityMarker) mk).getCountry());
			}
				
				

			
		}
		
	
	}

}
