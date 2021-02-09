package Pachet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Operations {
    List<MonitoredData> events = FileWorker.read();
    List<String> info = new ArrayList<String>();
    public void afisare(){
        String s="";
        for (MonitoredData i: events){
            s+=i.toString()+'\n';
        }
        FileWorker.write(s, 1);
    }
    public void distinctDays(){
        FileWorker.write("Number of distinct days: " + MonitoredData.numberOfDistinctDays(events), 2);
    }
    public void activitiesOccure(){
        Map<String, Long> map = MonitoredData.numberOfActivityOccurrences(events);
        StringBuilder activityOccurrences = new StringBuilder();
        for (Map.Entry<String, Long> s : map.entrySet())
            activityOccurrences.append(s.getKey() + " - " + s.getValue() + "\n");
        FileWorker.write("Number of activity occurrences:\n" + activityOccurrences.toString(), 3);
    }
    public void occureEachDay(){
        Map<LocalDate, Map<String, Long>> map1 = MonitoredData.numberOfActivitiesPerDay(events);
        StringBuilder activitiesPerDay = new StringBuilder();
        for (Map.Entry<LocalDate, Map<String, Long>> s : map1.entrySet()) {
            activitiesPerDay.append("Day " + s.getKey() + ": \n");
            for (Map.Entry<String, Long> entry : map1.get(s.getKey()).entrySet()) {
                activitiesPerDay.append(entry.getKey() + " - " + entry.getValue() + "\n");
            }
            activitiesPerDay.append("\n");
        }
        FileWorker.write("Activity occurences for each day: \n" + activitiesPerDay, 4);
    }
    public void activitiesDuration(){
        Map<String, Double> map2 = MonitoredData.durationOfActivities(events);
        StringBuilder durationOfActivities = new StringBuilder();
        for (Map.Entry<String, Double> entry : map2.entrySet()) {
            durationOfActivities.append(entry.getKey() + " - duration: " + String.format("%.2f", entry.getValue()) + " hours\n");
        }
        FileWorker.write("Activities duration:\n" + durationOfActivities.toString(), 5);
    }

    public void under5Min(){
        List<String> activities = MonitoredData.shortActivities(events);
        StringBuilder shortActivities = new StringBuilder();
        for (String s : activities) {
            shortActivities.append(s + "\n");
        }
        FileWorker.write("Activities that have 90% of durations < 5 minutes:\n" + shortActivities.toString(), 6);
    }
}
