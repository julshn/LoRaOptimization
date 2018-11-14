import java.util.*;
public class LoRaExperiment1 {
	public static void main(String[] args) {
		Location loc;
		Random rng = new Random();
		Sensor[] sensors = new Sensor[100];
		LoRa[] radios = new LoRa[10];
		for (int i = 0; i < sensors.length; i++) {
			loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); //random location between 0 and 100 for each sensor
			System.out.println(loc.getX() + loc.getY());
			sensors[i] = new Sensor(loc, 10, -1);
		}
		for(int i = 0; i < radios.length; i++) {
			radios[i]= new LoRa(1, 1 ,1); //add a new radio with values for bandwidth, coding rate, and spreading factor
		}
		loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); //random location for the server
		Server server = new Server(loc, 100, -1);
		
		double dis;
		int indSens = 0; //index of the sensor with minimum distance from the server
		double min = 0; //min distance
		double avg = 0; //mean distance
		
		for (int i = 0; i < sensors.length;i++) {
			dis = Location.getDistance(sensors[i].getLoc(), server.getLoc());
			if (i == 0) {
				min = dis;
			}
			if (dis < min) {
				min = dis;
				indSens= i;
			}
			avg += dis;
		}
		 avg /= sensors.length;
		 System.out.println("mean distance: " + avg);
		 System.out.println("minimum distance " + min);
		 
		/*double maxTR = 0; //maximum transmission rate
		int indLoRa = 0; //index of radio with maximum transmission rate
		double tr;
		for (int i = 0; i < radios.length; i++) {
			if (radios[i].getTR() > maxTR) {
				maxTR = radios[i].getTR();
				indLoRa = i;
			}
		}*/
	}
}
