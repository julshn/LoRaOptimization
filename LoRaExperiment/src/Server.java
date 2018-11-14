
public class Server {
	Location loc;//location
	double cap; //capacity data/time
	double p; //power
	
	public Server() {
	}
	
	public Server(Location loc, double cap, double p) {
		this.loc = loc;
		this.cap=cap;
		this.p=p;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public void setCap(double cap) {
		this.cap=cap;
	}
	public void setPow(double p) {
		this.p=p;
	}
	public Location getLoc() {
		return this.loc;
	}
	public double getCap() {
		return this.cap;
	}
	public double getPow() {
		return this.p;
	}
}
