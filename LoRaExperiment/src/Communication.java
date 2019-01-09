
public class Communication implements Cloneable{
	Sensor sensor;
	LoRa radio;
	Server server;
	
	public Communication() {
	}
	
	public Communication(Sensor sensor, LoRa radio, Server server) {
		this.sensor = sensor;
		this.radio=radio;
		this.server=server;
	}
	
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public void setRadio(LoRa radio) {
		this.radio=radio;
	}
	public void setServer(Server server) {
		this.server=server;
	}
	
	public int getSensorID() {
		return this.sensor.getID();
	}
	public int getRadioID() {
		return this.radio.getID();
	}
	public int getServerID() {
		return this.server.getID();
	}
	public double getLength() {
		double d;
		Location locSens = sensor.getLoc();
		Location locR= radio.getLoc();
		Location locServ = server.getLoc();
		d = Location.getDistance(locSens, locR)+Location.getDistance(locR, locServ);
		return d;
	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
