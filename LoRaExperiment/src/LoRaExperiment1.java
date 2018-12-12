import java.util.*;
public class LoRaExperiment1 {
	public static void main(String[] args) {
		Location loc;
		Random rng = new Random();
		Sensor[] sensors = new Sensor[100];
		LoRa[] radios = new LoRa[10];
		ArrayList<Communication> communications = new ArrayList<Communication>();
		int id, ind = 0;
		int capR = 1;   //capacity of radio
		int capS = 1000; //capacity of server
		id = 1000;
		for (int i = 0; i < sensors.length; i++) {
			loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); //random location between 0 and 100 for each sensor
			//System.out.println(loc.getX() + loc.getY());
			sensors[i] = new Sensor(id, loc, 10, -1);
			id++;
		}
		id = 2000;
		for(int i = 0; i < radios.length; i++) {
			loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); 
			radios[i]= new LoRa(id, loc, capR, 1, 1 ,1); //add a new radio with values for bandwidth, coding rate, and spreading factor
			id++;
		}
		id = 3000;
		loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); //random location for the server
		Server server = new Server(id, loc, capS, -1);
		id++;
		
		double dis;
		int idMinSens, idMinRadio, idMinServ; //id of the sensor with minimum distance from the server
		double min = 0; //min distance
		double max = 0;
		double avg = 0; //mean distance
		
		for (int i = 0; i < sensors.length;i++) {
			for (int j = 0; j < radios.length;j++) {
				
			communications.add(ind, new Communication(sensors[i], radios[j], server));
			dis = communications.get(ind).getLength();
			if (i == 0 && j==0) {
				min = dis;
			}
			if (dis < min) {
				min = dis;
			}
			if (dis > max) {
				max = dis;
			}
			avg += dis;
			ind++;
			}
		}
		 avg /= (sensors.length * radios.length);
		 System.out.println("mean distance: " + avg);
		 System.out.println("minimum distance " + min);
		 System.out.println("maximum distance " + max);
		 
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
