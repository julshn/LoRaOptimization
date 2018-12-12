public class Sensor {
	Location loc;
	double dr; //data rate
	double p; //if power is negative, it means that we are not considering it
	int id; //number starting with 1
	
	public Sensor() {
	}
	
	public Sensor(int id, Location loc, double dr, double p) {
		this.id = id;
		this.loc = loc; //this only copies the reference
		this.dr = dr;
		this.p = p;
	}
	
	public void setID(int id) {
		this.id = id;
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
	
	public int getID() {
		return this.id;
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
