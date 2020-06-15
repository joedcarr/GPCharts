package uk.co.joedcarr.gpcharts;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ru.olerom.formula.ergast.Ergast;
import ru.olerom.formula.ergast.objects.Driver;
import ru.olerom.formula.ergast.objects.RaceResult;
import ru.olerom.formula.ergast.objects.Schedule;
import uk.co.joedcarr.gpcharts.models.Result;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {
    private Ergast api;
    @FXML
    private Spinner<Integer> seasonSpinner;
    // TODO: Get first/latest season from API.
    private final int MIN_YEAR = 1950;
    private final int MAX_YEAR = Year.now().minusYears(1).getValue();
    @FXML
    //private TableView<Driver> resultsTable;
    private TableView<Result> resultsTable;

    @FXML
    private void initialize() {
        seasonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_YEAR, MAX_YEAR));
    }

    //@FXML
    /*private void loadResults() throws IOException {
        api = new Ergast(seasonSpinner.getValue(), Ergast.DEFAULT_LIMIT, Ergast.DEFAULT_OFFSET);
        resultsTable.getItems().clear();
        resultsTable.getColumns().clear();
        var driverCol = new TableColumn<Driver, String>("Driver");
        driverCol.setCellValueFactory(new PropertyValueFactory<>("familyName"));
        resultsTable.getColumns().add(driverCol);
        resultsTable.getItems().addAll(api.getDrivers());
    }*/

    @FXML
    private void loadResults() throws IOException {
        api = new Ergast(seasonSpinner.getValue(), Ergast.DEFAULT_LIMIT, Ergast.DEFAULT_OFFSET);
        resultsTable.getItems().clear();
        resultsTable.getColumns().clear();

        ObservableList<Result> results = FXCollections.observableArrayList();
        List<Driver> drivers = api.getDrivers();
        drivers.forEach(driver -> results.add(new Result(driver)));
        Schedule schedule = api.getSchedules().get(0);
        List<RaceResult> raceResults = api.getRaceResults(schedule.getRound());

        for(RaceResult raceResult : raceResults) {
            Optional<Result> result = results.stream().filter(r -> r.getDriver() == raceResult.getDriver()).findFirst();
            result.ifPresent(value -> value.addRaceResult(schedule.getRound(), raceResult));
        }

        var driverCol = new TableColumn<Result, String>("Driver");
        driverCol.setCellValueFactory(features -> {
            Driver driver = features.getValue().getDriver();
            return new ReadOnlyStringWrapper(driver.getGivenName().charAt(0) + " " + driver.getFamilyName());
        });
        resultsTable.getColumns().add(driverCol);
        resultsTable.getItems().addAll(results);
    }

}
