public class Sensor {
	Location loc;
	double dr; //data rate
	double p; //if power is negative, it means that we are not considering it
	
	public Sensor() {
	}
	
	public Sensor(Location loc, double dr, double p) {
		this.loc = loc; //this only copies the reference
		this.dr = dr;
		this.p = p;
	}
	public void setLoc(Location l) {
		this.loc = new Location(l.getX(), l.getY());
	}
	public void setDataRate(double dr) {
		this.dr = dr;
	}
	public void setPower(double p) {
		this.p = p;
	}
	public Location getLoc() {
		return this.loc;
	}
	public double getDR() {
		return this.dr;
	}
	public double getP() {
		return this.p;
	}
}
