package DataLayer;



import BLL.MenuItem;
import BLL.Order;
import BLL.Restaurant;

import java.io.*;


public class FileWriter {
    Restaurant rest= new Restaurant();
    public FileWriter(String filename) throws IOException {
        InputStream file = new FileInputStream(filename);
        ObjectInputStream object = new ObjectInputStream(file);
        try {
            rest = (Restaurant) object.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            file.close();
            object.close();
        }
    }
}

