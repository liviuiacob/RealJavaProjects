package Pachet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class FileWorker {
    private static final String FILENAME = "Activities.txt";

    public static List<MonitoredData> read() {
        List<MonitoredData> events = new ArrayList<MonitoredData>();

        try (Stream<String> stream = Files.lines(Paths.get(FILENAME))) {

            stream.forEach(s -> {
                String[] words = s.split("\\s{2,}");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startTime = LocalDateTime.parse(words[0],formatter);
                LocalDateTime endTime = LocalDateTime.parse(words[1],formatter);
                events.add(new MonitoredData(startTime,endTime,words[2]));
            });
            return events;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void write(String s, int i){
        BufferedWriter bw = null;
        try {
            //Specify the file name and path here
            File file = new File("task"+i+".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(s);
            System.out.println("File written Successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }

}
