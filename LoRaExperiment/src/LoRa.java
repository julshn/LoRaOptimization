
public class LoRa {
	double bw; //bandwidth
	double cr; //coding rate (same as data rate)
	double sf; //spreading factor
	Location loc;
	int cap;
	int id; //number starting with 2
	int numComm; //number of communications going through the radio
	double data = 0; //amount of data going through the radio 
	public LoRa() {
	}
	public LoRa(int id, Location location, int cap, double bw, double cr, double sf) {
		this.bw = bw;
		this.cr = cr;
		this.sf = sf;
		this.id = id;
		loc = location;
		numComm = 0;
	}
	public void setID (int id) {
		this.id = id;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public void setBW(double bw) {
		this.bw=bw;
	}
	public void setCR(double cr) {
		this.cr=cr;
	}
	public void setSF(double sf) {
		this.sf=sf;
	}
	public int getID() {
		return this.id;
	}
	public Location getLoc() {
		return this.loc;
	}
	public double getBW() {
		return this.bw;
	}
	public double getCR() {
		return this.cr;
	}
	public double getSF() {
		return this.sf;
	}
	public double getTR() {
		double tr=Math.pow(2, sf) / bw;
		return tr;
	}
	public void addCommunication() {
		numComm++;
	}
	public void addData(double amount) {
		data += amount;
	}
}
