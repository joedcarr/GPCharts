package uk.co.joedcarr.gpcharts.models;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import ru.olerom.formula.ergast.objects.Driver;
import ru.olerom.formula.ergast.objects.RaceResult;
import ru.olerom.formula.ergast.objects.Schedule;

import java.util.List;
import java.util.Map;

public class Result {
    private final SimpleObjectProperty<Driver> driver;
    //private final SimpleListProperty<RaceResult> raceResults;
    private final SimpleMapProperty<Schedule, RaceResult> raceResults;

    public Result(Driver driver, List<RaceResult> raceResults) {
        this.driver = new SimpleObjectProperty<>(driver);
        //this.raceResults = new SimpleListProperty<>(FXCollections.observableArrayList(raceResults));
        this.raceResults = new SimpleMapProperty<>(FXCollections.observableHashMap());
    }

    public void addRaceResult(Schedule round, RaceResult raceResult) {
        raceResults.put(round, raceResult);
    }

    public Driver getDriver() {
        return driver.get();
    }

    public Map<Schedule, RaceResult> getRaceResults() {
        return raceResults.get();
    }

    public SimpleObjectProperty<Driver> driverProperty() {
        return driver;
    }

    public SimpleMapProperty<Schedule, RaceResult> raceResultsProperty() {
        return raceResults;
    }

}
