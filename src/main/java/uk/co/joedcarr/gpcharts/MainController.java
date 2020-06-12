package uk.co.joedcarr.gpcharts;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.olerom.formula.ergast.Ergast;
import ru.olerom.formula.ergast.objects.Driver;

import java.io.IOException;
import java.time.Year;

public class MainController {
    private Ergast api;
    @FXML
    private Spinner<Integer> seasonSpinner;
    // TODO: Get first/latest season from API.
    private final int MIN_YEAR = 1950;
    private final int MAX_YEAR = Year.now().minusYears(1).getValue();
    @FXML
    private TableView<Driver> resultsTable;

    @FXML
    private void initialize() {
        seasonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_YEAR, MAX_YEAR));
        resultsTable.setPlaceholder(new Label(String.format("No results to show.%nClick 'Load Results' to begin.")));
    }

    @FXML
    private void loadResults() throws IOException {
        api = new Ergast(seasonSpinner.getValue(), Ergast.DEFAULT_LIMIT, Ergast.DEFAULT_OFFSET);
        resultsTable.getItems().clear();
        resultsTable.getColumns().clear();
        var driverCol = new TableColumn<Driver, String>("Driver");
        driverCol.setCellValueFactory(new PropertyValueFactory<>("familyName"));
        resultsTable.getColumns().add(driverCol);
        resultsTable.getItems().addAll(api.getDrivers());
    }

}
