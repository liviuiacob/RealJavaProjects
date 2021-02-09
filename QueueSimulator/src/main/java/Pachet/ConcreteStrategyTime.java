package Pachet;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
	public void addTask (List<Server> servers, Task t)
	{
		int min = 999;
		int id = 0;
		for(Server i : servers)
			if(i.getWaitingPeriod() < min) {
				min = i.getWaitingPeriod();
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
