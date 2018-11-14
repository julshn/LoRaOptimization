
public class LoRa {
	double bw; //bandwidth
	double cr; //coding rate
	double sf; //spreading factor
	
	public LoRa() {
	}
	public LoRa(double bw, double cr, double sf) {
		this.bw = bw;
		this.cr = cr;
		this.sf = sf;
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
	public double getBW(double bw) {
		return this.bw;
	}
	public double getCR(double cr) {
		return this.cr;
	}
	public double getSF(double sf) {
		return this.sf;
	}
	public double getTR() {
		double tr=Math.pow(2, sf) / bw;
		return tr;
	}
}
