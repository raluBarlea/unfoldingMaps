package module1;

import javax.print.attribute.standard.MediaSize.Other;

public class SimpleLocation {
	public double latitude;
	public double longitude;
	public SimpleLocation(double latitude,double longitude){
		
		this.latitude=latitude;
		this.longitude=longitude;
	}
//	public double GetDistance(SimpleLocation other){
//		return GetDistance(this.latitude,this.longitude,other.latitude,other.longitude);
//	}
}
