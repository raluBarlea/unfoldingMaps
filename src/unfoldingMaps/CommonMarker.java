package unfoldingMaps;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

public abstract class CommonMarker extends SimplePointMarker {

	public CommonMarker(Location location) {
		super(location);
	}
	
	public CommonMarker(Location location, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
		super(location, properties);
	}
//	public void draw(PGraphics pg, float x, float y) {
//		
//			if (selected ) {
//				showTitle(pg, x, y);  // You will implement this in the subclasses
//			}
//	}
	//public abstract void showTitle(PGraphics pg,float x, float y);
	
}
