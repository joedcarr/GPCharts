package uk.co.joedcarr.gpcharts.models;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.olerom.formula.ergast.objects.Driver;
import ru.olerom.formula.ergast.objects.RaceResult;

import java.util.List;

public class Result {
    private final SimpleObjectProperty<Driver> driver;
    private final SimpleListProperty<RaceResult> raceResults;

    public Result(Driver driver) {
        this.driver = new SimpleObjectProperty<>(driver);
        this.raceResults = new SimpleListProperty<>(FXCollections.emptyObservableList());
    }

    public void addRaceResult(int round, RaceResult raceResult) {
        raceResults.add(round, raceResult);
    }

    public Driver getDriver() {
        return driver.get();
    }

    public ObservableList<RaceResult> getRaceResults() {
        return raceResults.get();
    }

    public SimpleObjectProperty<Driver> driverProperty() {
        return driver;
    }

    public SimpleListProperty<RaceResult> raceResultsProperty() {
        return raceResults;
    }

}
