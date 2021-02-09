package presentation;

public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//delete all from tables
		Controller c= new Controller();
		//c.read("D:\\PT2020\\pt2020_302210_iacob_liviu_assignment_3\\fisier.txt");
        c.read(args[0]);
	}

}
