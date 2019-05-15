
public class Server {
	Location loc;//location
	double cap; //capacity data/time
	double p; //power
	int id; //number starting with 3
	int numComm;
	public Server() {
	}
	
	public Server(int id, Location loc, double cap, double p) {
		this.id = id;
		this.loc = loc;
		this.cap=cap;
		this.p=p;
		numComm = 0;
	}
	public void setID(int id) {
		this.id = id;
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
	public int getID() {
		return this.id;
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
	public void addCommunication() {
		numComm++;
	}
}
