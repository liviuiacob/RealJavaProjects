package Pachet;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public interface Fisiere {
	public default ArrayList<String> readFromFile(File file, String delimitator) throws IllegalArgumentException
	{
		ArrayList<String> fileResult = new ArrayList<String>();
		try
		{
			Scanner sc;
			try{
				sc = new Scanner(file);
			}
			catch(Exception ex) {
				throw new IllegalArgumentException("File does not exists! ");
			}
			while(sc.hasNextLine())
			{
				String nextLine = sc.nextLine();
				String[] splittedLine = nextLine.split(",");
				
				for(String str : splittedLine)
				{
					if(str.matches("[1-9]{1}[0-9]*"))
					{
						fileResult.add(str);
					}
					else {throw new IllegalArgumentException("Invalid information! The format is not respected ");}
				}
			}
			sc.close();
		}
		catch(IllegalArgumentException ex) 
		{
			throw ex;
		}
		
		return fileResult;
	}
}
