package Pachet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
	private BlockingQueue<Task> tasks;
	private int waitingPeriod; // cat asteapta ultimul client pana termina (durata cozii)
	private int waitingTasks=0;
	private boolean open = true;
	private int id;
	private int tasksDone = 0; // cati clienti au terminat
	private int serverWaitingTime = 0; // timpul total cat a fost deschisa coada
	
	public Server(int id, int maxLoad) {
		this.id = id;
		this.tasks = new ArrayBlockingQueue<Task>(maxLoad);
		this.waitingPeriod=0;
		//init queue si period	
	}
	
	public int getId() {
		return id;
	}
	public int getWaitingTasks() {
		return waitingTasks;
	}
	
	
	public void addTask(Task newTask) {
		//add to queue and increment period
		tasks.add(newTask);
		waitingPeriod+=newTask.getProcessingTime();
	}
	
	public int getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(int waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public double getAverageTimeQueue() {
		return serverWaitingTime / tasksDone;
	}
	
	public int getTasksDone() {
		return tasksDone;
	}
	
	public int getServerWaitingTime() {
		return serverWaitingTime;
	}

	public BlockingQueue<Task> getTasks() {
		return tasks;	
	}
	
	public String toString()
	{
		String result;
		if(getWaitingPeriod() == 0 || tasks.peek() == null || tasks.peek().getProcessingTime() == 0)
		{
			result = "closed";
		}
		else
		{
			result = tasks.toString();
		}
		return result;
	}

	
	public void run() 
	{
		while(open)
		{	
			while(tasks.peek() != null)
			{
				try{
					int p= tasks.peek().getProcessingTime();
					Thread.sleep(1000);
					waitingPeriod--;
					p=p-1;
					tasks.peek().setProcessingTime(p);
					if(p == 0)
					{
						tasks.peek().setProcessingTime(0);
						//System.out.println("__________________________________________");
						
						tasks.poll();
						//break;
					}
					}catch(Exception ex) {
						//System.out.println("1");
					}
				
				
			}
			
			setOpen(false);
		}
	}
	
	
	
	
	
	/*
	public void run() {
		while(open) {
			try {
				Task t = tasks.take();
				System.out.println("Server " + id + ": " + "Task " + t.getNumar() + " is being processed");
				Thread.sleep((long) (t.getProcessingTime()*1000));
				serverWaitingTime += t.getProcessingTime();
				tasksDone++;
				waitingPeriod-=t.getProcessingTime();
				if (tasks.isEmpty())
					setOpen(false);
				System.out.println("Task " + t.getNumar() + " was processed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//next task
			//stop thread for a time equal to the task's processing time
			//decrement period
		}
		//System.out.println("Average service time was " + serverWaitingTime / tasksDone);
	}
	*/
	

}