package Pachet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MonitoredData {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String activity;

    public MonitoredData (LocalDateTime startTime, LocalDateTime endTime, String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public String toString() {
        return "startTime: " + startTime + " endTime: " + endTime + " activity: " + activity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getActivity() {

        return activity;
    }

    public Double getDurationInHours() {
        return (java.time.Duration.between(startTime, endTime).toMinutes() / 60d);
    }

    public Long getDurationInMinutes() {
        return java.time.Duration.between(startTime, endTime).toMinutes();
    }

    public static int numberOfDistinctDays(List<MonitoredData> events) {
        return events.stream().collect(Collectors.groupingBy
                (d -> LocalDate.of(d.getStartTime().getYear(), d.getStartTime().getMonth(), d.getStartTime().getDayOfMonth()))).size();
    }

    public static Map<String,Long> numberOfActivityOccurrences(List<MonitoredData> events) {
        return events.stream()
                .collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
    }

    public static Map<LocalDate, Map<String,Long>> numberOfActivitiesPerDay(List<MonitoredData> events) {
        return events.stream().
                collect(Collectors.groupingBy(d -> LocalDate.of(d.getStartTime().getYear(), d.getStartTime().getMonth(),
                        d.getStartTime().getDayOfMonth()),
                        Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));
    }

    public static Map<String,Double> durationOfActivities(List<MonitoredData> events) {
        Map<String,Double> map = events.stream().collect(Collectors.groupingBy(MonitoredData::getActivity,
                Collectors.summingDouble(MonitoredData::getDurationInHours)));
        return map.entrySet().stream().collect(Collectors.toMap(d -> d.getKey(), d -> d.getValue()));
    }

    public static List<String> shortActivities(List<MonitoredData> events) {
        Map<String,List<Long>> durationMap = events.stream().collect(Collectors.groupingBy(MonitoredData::getActivity,
                Collectors.mapping(MonitoredData::getDurationInMinutes, Collectors.toList())));
        Map<String,List<Long>> filteredMap = durationMap.entrySet().stream().filter(p -> {
            int initialSize = p.getValue().size();
            int finalSize = p.getValue().stream().filter(d -> d.compareTo(5L) < 0).collect(Collectors.toList()).size();
            if (finalSize >= (90 * initialSize / 100))
                return true;
            return false;

        }).collect(Collectors.toMap(d -> d.getKey(), d -> d.getValue()));
        return new ArrayList<>(filteredMap.keySet());
    }
}