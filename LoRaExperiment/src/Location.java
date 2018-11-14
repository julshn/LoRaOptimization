
public class Location{
	double x;
	double y;
	
	public Location() {
	}
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public static double getDistance(Location loc1, Location loc2) {
		double d = Math.sqrt(Math.pow(loc1.getX()-loc2.getX(), 2) + Math.pow(loc1.getY() - loc2.getY(), 2));
		return d;
	}
}

