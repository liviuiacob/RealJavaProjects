package Pachet;

public class Task {
	private int arrivingTime;
	private int processingTime;
	private int finishTime;
	private int numar;
	
	public Task() {
		
	}
	public Task(int arrivingTime, int processingTime, int numar) {
		this.arrivingTime = arrivingTime;
		this.processingTime = processingTime;
		this.numar=numar;
	}
	
	public int getArrivingTime() {
		return arrivingTime;
	}
	public void setArrivingTime(int arrivingTime) {
		this.arrivingTime = arrivingTime;
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}
	
	public int getNumar() {
		return numar;
	}
	public void setNumar(int numar) {
		this.numar=numar;
	}
	public void setFinishTime(int n){
		finishTime=n;
	}
	public int getFinishTime() {
		return finishTime;
	}
	public String toString() 
	{
		return "(" + numar + "," + arrivingTime + "," + processingTime + ")";
	}
	
	

}
