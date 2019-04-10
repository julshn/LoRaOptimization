import java.util.*;
public class LoRaExperiment {
	// Parameters:
	  public String s = "";
	  private ArrayList<Sensor> sensors;
	  private ArrayList<LoRa> radios;
	  private ArrayList<Server> servers;
	  Location loc;
	  private ArrayList<Communication> communications;
	  private int numberOfCommunications=1;
	  private double alpha = 1, beta = 5;
	  private double randomFactor;
	  // private double dataFactor; //amount of data per sensor in kb
	  private int maxIter; //maximum number of iterations
	  private int numberOfSensors;
	  private int numberOfRadios;
	  private int numberOfServers;
	  private double amountOfData; //number of kB
	  private double[][] numSR; //records number of communications happening between each sensor and radio
	  private double[][] numRS; //records number of communications happening between each radio and server
	  private Random rng = new Random();
	  private ArrayList<Communication> bestCommunication;

	  public LoRaExperiment() {
	  }
	  public LoRaExperiment(int numSensors, int numRadios, int numServers) {
			numberOfSensors = numSensors;
			numberOfRadios = numRadios;
			numberOfServers = numServers;
			setUpSensors(numberOfSensors);
			setUpServers(numberOfServers);
			setUpRadios(numberOfRadios);
			double amountOfData = 100000;
			int maxIter = 5;
			double[][] numSR = new double[numberOfSensors][numberOfRadios]; 
			double[][] numRS = new double [numberOfRadios][numberOfServers];
		}
	  public LoRaExperiment(ArrayList<Sensor> sensors,  ArrayList<LoRa> radios, ArrayList<Server> servers, double kb, double df, int iter) {
			this.sensors = (ArrayList<Sensor>) sensors.clone();
			this.radios = (ArrayList<LoRa>) radios.clone();
			this.servers = (ArrayList<Server>) servers.clone();
			numberOfSensors = sensors.size();
			numberOfRadios = radios.size();
			numberOfServers = servers.size();
			double amountOfData = kb;
			int maxIter = iter;
			double[][] numSR = new double[numberOfSensors][numberOfRadios]; 
			double[][] numRS = new double [numberOfRadios][numberOfServers];
	  }
	  /*public double[][] generateRandomMatrix(int m, int n) { 
		  double[][] randomMatrix = new double[m][n];
	        
	        for(int i=0;i<n;i++)
	        {
	            for(int j=0;j<n;j++)
	            {
	                if(i==j)
	                    randomMatrix[i][j]=0;
	                else
	                    randomMatrix[i][j]=Math.abs(rng.nextDouble()* 100);
	            }
	        }
	         
	        s+=("\t");
	        for(int i=0;i<n;i++)
	            s+=(i+"\t");
	        s+="\n";
	        
	        for(int i=0;i<n;i++)
	        {
	            s+=(i+"\t");
	            for(int j=0;j<n;j++)
	                s+=(randomMatrix[i][j]+"\t");
	            s+="\n";
	        }
	        
	        int sum=0;
	        
	        for(int i=0;i<n-1;i++)
	            sum+=randomMatrix[i][i+1];
	        sum+=randomMatrix[n-1][0];
	        return randomMatrix;
	  }*/

	  public void startLoRaOptimization() {
		  for(int i=1;i<=5;i++)
	        {
	            s+=("\nAttempt #" +i);
	            solve();
	            s+="\n";
	        }
	  }
	  public ArrayList<Communication> solve() {
		  setUpData(); 
	        clearCommunications();
	        for(int i=0;i<maxIter;i++)
	        {
	            sendData();
	            updateCommunications();
	            updateBest();
	        }
	        /*s+=("\nBest tour length: " + (bestTourLength - numberOfCities));
	        s+=("\nBest tour order: " + Arrays.toString(bestTourOrder));*/
	        return (ArrayList<Communication>) bestCommunication.clone();
	  }

	  public void setUpData(){
	  }
	  public void setUpSensors(int num){
		  int id = 1000;
		  for (int i = 0; i < num; i++) {
				loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); //random location between 0 and 100 for each sensor
				//System.out.println(loc.getX() + loc.getY());
				sensors.add(new Sensor(id, loc, 10, -1));
				id++;
			}	  
	  }
	  public void setUpRadios(int num){
		  //create array of radios with BW, CR, SF, capacity, location
		  int id = 2000;
		  int capR = 100;
			for(int i = 0; i < num; i++) {
				loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); 
				radios.add(new LoRa(id, loc, capR, 1, 1 ,1)); //add a new radio with values for bandwidth, coding rate, and spreading factor
				id++;
			}
	  }

	  public void setUpServers(int num){
	  //create array of servers with power, location
		  int id = 3000;
		  int capS = 100;
			for (int i = 0; i < num; i++) {
				loc = new Location(rng.nextDouble()* 50., rng.nextDouble() * 50.); //random location for the server
				servers.add(new Server(id, loc, capS, -1));
				id++;
			}
	  }
	  public void sendData() {
	  //like moveAnts
	  /*for(Data d: data) {
	      //data sent to a radio, then to a server
	  }*/
	  }

	  public void selectNextRadio() {
		  
	  }
	  public int selectNextServer(){
		  
		  return 0;
	  }
	  public double calculateProbabilities(){
		  double probabilitiesSR[][] = new double[numberOfSensors][numberOfRadios];
		  double probabilitiesRS[][] = new double[numberOfRadios][numberOfServers];
		  return 0;
	  }
	  public void updateCommunications(){
	  }
	  public void updateBest(){
	  }
	  public void clearCommunications(){
	  }
  
	
}
