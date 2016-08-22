package module4_project;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

public class LandQuakeMarker extends EarthquekeMarker {

	public LandQuakeMarker(PointFeature earthqueke) {
		super(earthqueke);
		isOnLand = true;
	}
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		pg.ellipse(x, y, 5, 5);
	
	}
	public String getCountry() {
		return (String) getProperty("country");
	}
}
