package uk.co.joedcarr.gpcharts;

import javafx.fxml.FXML;
import ru.olerom.formula.ergast.Ergast;

import java.io.IOException;

public class MainController {

    @FXML
    private void listDrivers() throws IOException {
        var ergast = new Ergast(2010, Ergast.DEFAULT_LIMIT, Ergast.DEFAULT_OFFSET);
        ergast.getDrivers().forEach(System.out::println);
    }

}
