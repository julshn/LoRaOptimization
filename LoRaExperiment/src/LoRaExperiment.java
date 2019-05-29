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
      
      private double[][] numSR; //records number of communications happening between each sensor and radio (should i change this to data?
      private double[][] numRS; //records number of communications happening between each radio and server
      
      private double[][] costSR;
      private double[][] costRS;
      
      private double[][] probabilitiesSR;
      private double[][] probabilitiesRS;
      private double[] sortedProbSR;
      
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
            numSR = new double[numberOfSensors][numberOfRadios]; 
            numRS = new double [numberOfRadios][numberOfServers];
            costSR = new double[numberOfSensors][numberOfRadios]; 
            costRS = new double [numberOfRadios][numberOfServers];
            probabilitiesSR = new double[numberOfSensors][numberOfRadios]; 
            probabilitiesRS = new double[numberOfRadios][numberOfServers];
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
            numSR = new double[numberOfSensors][numberOfRadios]; 
            numRS = new double [numberOfRadios][numberOfServers];
            costSR = new double[numberOfSensors][numberOfRadios]; 
            costRS = new double [numberOfRadios][numberOfServers];
            probabilitiesSR = new double[numberOfSensors][numberOfRadios]; 
            probabilitiesRS = new double[numberOfRadios][numberOfServers];
      }

      public void startLoRaOptimization() {
          for(int i=1;i<=5;i++)
            {
                s+=("\nAttempt #" +i);
                solve();
                s+="\n";
            }
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
      public ArrayList<Communication> solve() {
            clearCommunications();
            for(int i=0;i<numberOfSensors;i++)
            {
                sendData(i);
                updateBest(i);
            }
            /*s+=("\nBest tour length: " + (bestTourLength - numberOfCities));
            s+=("\nBest tour order: " + Arrays.toString(bestTourOrder));*/
            return (ArrayList<Communication>) bestCommunication.clone();
      }

      public void sendData(int ind) {
          double data = amountOfData;
          int j;
              while(data > 0) {
              int indR = selectNextRadio(ind);
              j = indR;
              LoRa r = radios.get(indR);
              while (r.numComm > r.cap){
                  //find second largest probability
                
              }
              int indS= selectNextServer(indR); //how to get the correct radio index? because now my probability array is sorted
              Server s = servers.get(indS);
              s.addCommunication();
              while (s.numComm > s.cap){
                //find second largest probability
              }
              if (amountOfData > Math.min(r.cap, s.cap)) {
                 data -= Math.min(r.cap, s.cap);
              }
              else
                  data = 0;
             // updateBest(new Communication);
              } 
      }

      public int selectNextRadio(int ind) {
          calculateProbabilitiesR(ind);
          double temp;
          int max = 0;
          for (int i = 0; i < probabilitiesSR[0].length;i++) {
              if(probabilitiesSR[ind][i] > probabilitiesSR[ind][max]) {
                  temp = probabilitiesSR[ind][max];
                  probabilitiesSR[ind][max] = probabilitiesSR[ind][i];
                  probabilitiesSR[ind][i] = temp;
                  max = i;
              }
          }
          
          return 0;
      }
      public int selectNextServer(int ind){
          calculateProbabilitiesS(ind);
          double temp;
          int max = 0;
          for (int i = 0; i < probabilitiesRS[0].length;i++) {
              if(probabilitiesRS[ind][i] > probabilitiesRS[ind][max]) {
                  temp = probabilitiesRS[ind][max];
                  probabilitiesRS[ind][max] = probabilitiesRS[ind][i];
                  probabilitiesRS[ind][i] = temp;
                  max = i;
              }
          }
          
          return 0;
      }
      public void calculateProbabilitiesR(int ind){
          double sum = 0;
          for(int i = 0; i < numberOfRadios;i++){
            sum+= Math.pow(numSR[ind][i], alpha) * Math.pow(1 / costSR[ind][i], beta);
          }
          for(int i = 0; i < numberOfRadios;i++) {
              probabilitiesSR[ind][i] = Math.pow(numSR[ind][i], alpha) * Math.pow(1/ costSR[ind][i], beta);
              probabilitiesSR[ind][i] /= sum;
          }
      }
      public void calculateProbabilitiesS(int ind) {
          double sum = 0;
          for(int i = 0; i < numberOfServers;i++){
            sum+= Math.pow(numRS[ind][i], alpha) * Math.pow(1 / costRS[ind][i], beta);
          }
          for(int i = 0; i < numberOfServers;i++) {
              probabilitiesRS[ind][i] = Math.pow(numRS[ind][i], alpha) * Math.pow(1/ costRS[ind][i], beta);
              probabilitiesRS[ind][i]/=sum;
          }          
      }
      public void updateCommunications(){
          //make an array of all the communications that are happening (ie if some device's capacity runs out) 
      }
      public void updateBest(int ind){ //minimize cost
          double max = probabilitiesRS[0][0] * probabilitiesSR[0][0];
          for (int i = 0; i < probabilitiesSR.length; i++) {
              for (int j = 0; j < probabilitiesRS.length;j++) {
                  if(probabilitiesSR[ind][i] * probabilitiesRS[ind][j] > max) {
                      max = probabilitiesSR[ind][i] * probabilitiesRS[ind][j];
                      //bestCommunication = new Communication(sensors[])
                  }
                  
              }
          }
      }
      public void clearCommunications(){
          for (int i = 0; i < communications.size(); i++) {
              communications.set(i, null);
          }
      }
}
    


