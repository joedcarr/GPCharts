package uk.co.joedcarr.gpcharts;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import ru.olerom.formula.ergast.Ergast;

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
    private void initialize() {
        seasonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_YEAR, MAX_YEAR));
    }

    @FXML
    private void listDrivers() throws IOException {
        api = new Ergast(seasonSpinner.getValue(), Ergast.DEFAULT_LIMIT, Ergast.DEFAULT_OFFSET);
        api.getDrivers().forEach(System.out::println);
    }

}
