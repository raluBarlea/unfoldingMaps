package unfoldingMaps;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PGraphics;

public class CityMarker extends CommonMarker{
	public static int TRI_SIZE = 5;
	public CityMarker(Location location) {
		super(location);
	}
	
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}
	//method for draw personalized markers
	public void draw(PGraphics pg,float x, float y){
		pg.pushStyle();
		pg.fill(150,30,30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		pg.popStyle();
	}
	public void showTitle(PGraphics pg, float x, float y)
	{
		String name =getName();
		String country = getCountry();
		float population = getPopulation();
		
		pg.pushStyle();
		pg.fill(255,255,255);
		pg.text(name,x,y);
		pg.text(country, x+5, y);
		pg.text(population, x+10, y);
		pg.popStyle();
		
	}
	public String getName(){
		return getStringProperty("name");
	}
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}

}
