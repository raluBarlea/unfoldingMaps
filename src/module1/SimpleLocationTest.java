package module1;

public class SimpleLocationTest {
	
public static void main(String[] args) {
	SimpleLocation baiaMare = new SimpleLocation(47.6688677,23.500655);
	SimpleLocation cluj = new SimpleLocation(46.7833642,23.5463009);
	baiaMare = cluj;
	cluj = baiaMare;
	System.out.println("baiaMare"+baiaMare.latitude+baiaMare.longitude);
	System.out.println("cluj"+cluj.latitude+cluj.longitude);
}
}
