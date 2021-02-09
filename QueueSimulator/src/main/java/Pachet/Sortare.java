package Pachet;

import java.util.Comparator;

public class Sortare implements Comparator<Task>{
	public int compare(Task x, Task y)
	{
		return x.getArrivingTime()-y.getArrivingTime();
	}
}
