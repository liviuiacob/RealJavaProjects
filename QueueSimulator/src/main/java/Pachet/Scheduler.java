package Pachet;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private ArrayList<Server> servers;
	private int maxNoServers=0;
	private int maxTasksPerServer=0;
	private Strategy strategy;
	private ArrayList<Thread> threads;
	
	public Scheduler(int nrServers, int nrTasks) {
		//for maxNoServers
		//create server object
		//this.setMaxNoServers(nrServers);
		//this.setMaxTasks(nrTasks);
		this.maxNoServers=nrServers;
		this.maxTasksPerServer=nrTasks;
		this.servers=new ArrayList<Server> (nrServers);
		this.threads = new ArrayList<Thread>(nrServers);
		for (int i=0; i<nrServers; i++)
		{
			/*
			Server s = new Server(i, getMaxTasks());
			servers.add(s);
			s.setOpen(false);
			//Thread thr= new Thread(servers.get(i));
			//TODO:create thread with the object
			 * */
			servers.add(new Server(i, maxNoServers));
			threads.add(new Thread(servers.get(i)));
			threads.get(i).start();
			
		}
	}
	public int getMaxNoServers() {
		return maxNoServers;
	}
	public void setMaxNoServers(int nr) {
		this.maxNoServers=nr;
	}
	public int getMaxTasks() {
		return maxTasksPerServer;
	}
	public void setMaxTasks(int nr) {
		this.maxTasksPerServer=nr;
	}
	public void changeStrategy(SelectionPolicy policy) {
		if(policy == SelectionPolicy.SHORTEST_QUEUE)
			strategy = new ConcreteStrategyQueue();
		else if(policy == SelectionPolicy.SHORTEST_TIME)
			strategy = new ConcreteStrategyTime();
	}
	private int minTimeQueue()
	{
		int waitingTime = 999;
		int minQueue = 0;
		for(int i = 0; i < maxNoServers; i++)
		{
			if(servers.get(i).getWaitingPeriod() < waitingTime)
			{
				waitingTime = servers.get(i).getWaitingPeriod();
				minQueue = i;
			}
			if(waitingTime == 0)
			{
				break;
			}
		}
		
		return minQueue;
	}
	public void dispatchTask(Task t)
	{
		int minTimeQueue = minTimeQueue();
		servers.get(minTimeQueue).addTask(t);
		if(servers.get(minTimeQueue).isOpen() != true)
		{
			servers.get(minTimeQueue).setOpen(true);
			threads.set(minTimeQueue, new Thread(servers.get(minTimeQueue)));
			threads.get(minTimeQueue).start();
		}
	}
	public void ucideThreaduri()
	{
		for(Server q : servers)
		{
			q.setOpen(false);
		}
	}
	
	public int getMaxQueueWait()
	{
		int max = 0;
		for(Server q : servers)
		{
			if (q.getWaitingPeriod()>max) {
				max=q.getWaitingPeriod();
			}
		}
		
		return max;
	}
	
	public List<Server> getServers() {
		return servers;
	}
	public String toString() {
		String rezultat="";
		for (Server i: servers) {
			rezultat+= "Queue " + i.getId() + ": " + i.toString() + "\n";
		}
		
		return rezultat;
	}
	
}