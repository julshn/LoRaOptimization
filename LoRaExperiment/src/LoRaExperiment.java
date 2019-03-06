import java.util.*;
public class LoRaExperiment {
	// Parameters:
	  ArrayList<Sensor> sensors;
	  ArrayList<LoRa> radios;
	  ArrayList<Server> servers;
	  ArrayList<Communication> communications;
	  int numberOfCommunications=1;
	  double randomFactor;
	  int maxIter; //maximum number of iterations
	  int numberOfSensors;
	  int numberOfRadios;
	  int numberOfServers;
	  double amountOfData; //number of kB
	  
	  public LoRaExperiment() {
		}
		public void LoRaExperiment(ArrayList<Sensor> sensors) {
			
		}
	  public void generateRandomMatrix() { 
	  }

	  public void startLoRaOptimization() {
	  //solve 
	  }
	  public double solve() {
	  /*set up data, sensors, servers, radios
	  for(int i = 0; i < maxIter; i++) {
	  sendData();
	  updateCommunications();
	  updateBest();
	  }*/
		  return 0;
	  }

	  public void setUpData(){
	  }
	  public void setUpSensors(){
	  //create array of sensors with data rate, location, power
	  }
	  public void setUpRadios(){
	  //create array of radios with BW, CR, SF, capacity, location
	  }

	  public void setUpServers(){
	  //create array of servers with power, location
	  }
	  public void sendData() {
	  //like moveAnts
	  for(Data d: data) {
	      //data sent to a radio, then to a server
	  }
	  }

	  public void selectNextRadio() {
	  }
	  public int SelectNextSensor(){
		  return 0;
	  }
	  public double calculateProbabilities(){
		  return 0;
	  }
	  public void updateCommunications(){
	  }
	  public void updateBest(){
	  }
	  public void clearCommunications(){
	  }
  
	
}
