package module4_project;

import de.fhpotsdam.unfolding.data.PointFeature;
import jogamp.nativewindow.windows.RECT;
import jogamp.opengl.GLBufferObjectTracker.CreateStorageDispatch;
import processing.core.PGraphics;

public class OceanQuakeMarker extends EarthquekeMarker {

	public OceanQuakeMarker(PointFeature earthqueke) {
		super(earthqueke);
	// TODO Auto-generated constructor stub
		isOnLand = false;
	}
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		//draw a square
		pg.rect(x-radius, y-radius, 2*radius, 2*radius);

	}
}
