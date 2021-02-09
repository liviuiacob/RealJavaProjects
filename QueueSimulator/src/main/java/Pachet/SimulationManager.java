package Pachet;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
//import java.util.List;
import java.util.Collections;

public class SimulationManager implements Runnable, Fisiere{
	public int timeLimit = 100; //max processing time
	public int maxProcessingTime = 10;
	public int minProcessingTime = 2;
	public int minArrivalTime = 2;
	public int maxArrivalTime = 30;
	public int numberOfServers = 3;
	public int numberOfClients = 100;
	public int maxsimulationTime = 0;
	public double avgTime =0;
	private File outFile;
	public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
	//public ArrayList<Thread> threads;
	public ArrayList<Task> generatedTasks;
	
	//queue management and client distribution
	private Scheduler scheduler;
	
	public SimulationManager(File file, File outFile) {
		ArrayList<String> fileResult;
		try
		{
			fileResult = readFromFile(file, "[1-9]{1}[0-9]*");
			this.numberOfClients = Integer.parseInt(fileResult.get(0));
			this.numberOfServers = Integer.parseInt(fileResult.get(1));
			this.maxsimulationTime = Integer.parseInt(fileResult.get(2));
			this.minArrivalTime = Integer.parseInt(fileResult.get(3));
			this.maxArrivalTime = Integer.parseInt(fileResult.get(4));
			this.minProcessingTime =Integer.parseInt(fileResult.get(5));
			this.maxProcessingTime= Integer.parseInt(fileResult.get(6));
			this.avgTime = 0;
			this.outFile = outFile;
			
			try
			{
				this.outFile.createNewFile();
			}
			catch(Exception ex) {System.out.println("File does not exists and could not be created ");return;}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return;
		}
		
		generateNRandomTasks();
		scheduler = new Scheduler(numberOfServers, numberOfClients);		
	}
	
	public int randomProcessingTime() {
		return (minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime)));
	}
	public int randomArrivingTime() {
		return (minArrivalTime + (int) (Math.random() * (maxArrivalTime  - minArrivalTime )));
	}
	
	private void generateNRandomTasks() {
		generatedTasks = new ArrayList<Task>(numberOfClients);
		for (int i=0; i<numberOfClients; i++)
		{
			Task t= new Task (randomArrivingTime(), randomProcessingTime(), i);
			generatedTasks.add(t);
		}
		Collections.sort(generatedTasks, new Sortare());
	}
	private int getMaxWait(int maxWait)
	{
		if(generatedTasks.isEmpty()) //&& maxWait <= 0)
		{
			maxWait = scheduler.getMaxQueueWait();
		}
		else
		{
			maxWait--;
		}
		
		return maxWait;
	}
	
	
	public void run() {
		FileWriter fWriter;
		
		try{fWriter =  new FileWriter(this.outFile.toString());}catch (Exception ex){System.out.println(ex.getMessage());return;}
		int currentTime = 0;
		int maxWait = 0;
		int nrProcessedClients = 0;
		while(currentTime < maxsimulationTime && (!generatedTasks.isEmpty() || maxWait > 0))
		{
			while(!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivingTime() == currentTime)
			{
				scheduler.dispatchTask(generatedTasks.get(0));
				if(currentTime + generatedTasks.get(0).getProcessingTime() < maxsimulationTime)
				{
					nrProcessedClients++;
					avgTime+=currentTime-generatedTasks.get(0).getArrivingTime();
					//avgTime+=
					//avgTime.addAndGet(-client.get(0).getArrivalTime().intValue() + client.get(0).getServiceTime().intValue() + currentTime.intValue());
				}
				generatedTasks.remove(0);
			}
			String result = getResult(currentTime);
			//System.out.println(result);
			try{
				fWriter.write(result);
			}catch(Exception ex) {}
			maxWait = getMaxWait(maxWait);
			currentTime++;
			try{
				Thread.sleep(1000);
			}catch(Exception ex) {}
			
		}
		scheduler.ucideThreaduri();
		try{
			fWriter.write("Average waiting time: " + avgTime / nrProcessedClients);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		try{
			fWriter.close();
		}catch(Exception ex) {}
	}
	
		
	
	
	private String getResult(int currentTime)
	{
		
		String result = "\nTime: " +currentTime + "\n";
		result =result + "Waiting clients: ";
		for (Task i: generatedTasks)
		{
			result=result + i.toString();
		}
		result = result+ "\n" + scheduler.toString();
		
		
		return result;
	}
	
	public static void main(String args[]) {
		try{
	        //SimulationManager gen = new SimulationManager(new File(args[0]), new File(args[1]));
	        SimulationManager gen = new SimulationManager(new File("in-test-1.txt"), new File("out-test-1.txt"));
	        Thread simThread = new Thread(gen);
	        simThread.start();
	        try{
	        	simThread.join();
	        }catch(Exception ex) {}
	        }
	        catch(Exception ex){
	        	System.out.println(ex.getMessage());
	        }
	}
	//cum fac .jar si sa ruleze din cmd
	//cum trebuia facut cu strategia
	//
}
