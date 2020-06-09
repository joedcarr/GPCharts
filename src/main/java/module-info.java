module uk.co.joedcarr.gpcharts {
    requires javafx.controls;
    requires javafx.fxml;

    opens uk.co.joedcarr.gpcharts to javafx.fxml;
    exports uk.co.joedcarr.gpcharts;
}