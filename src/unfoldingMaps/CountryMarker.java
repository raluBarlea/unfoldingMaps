package unfoldingMaps;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PGraphics;

public class CountryMarker extends CommonMarker{
	
	public CountryMarker(Location location) {
		super(location);
	}
	
	public CountryMarker(Feature country){
		super(((PointFeature)country).getLocation(), country.getProperties());
	}
	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	//drawing a design for country markers
//	public void draw(PGraphics pg,float x,float y){
//		pg.pushStyle();
//		pg.fill(150,30,30);
//		pg.rect(x, x+3, y-3,y+3);
//		pg.popStyle();
//		
//	}
}
