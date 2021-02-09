package Pachet;

import java.util.List;



public class ConcreteStrategyQueue implements Strategy{
	public void addTask (List<Server> servers, Task t)
	{
		int min = 999;
		int id = 0;
		for(Server i : servers)
			if(i.getWaitingTasks() < min) {
				min = i.getWaitingTasks();
				id = i.getId();
			}
		for(Server i : servers) {
			if(i.getId() == id) {
				i.setOpen(true);
				i.addTask(t);
				
			}
		}
	}

}

