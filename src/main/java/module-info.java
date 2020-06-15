module uk.co.joedcarr.gpcharts {
    requires ergast;

    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens uk.co.joedcarr.gpcharts to javafx.fxml;
    opens uk.co.joedcarr.gpcharts.models;
    exports uk.co.joedcarr.gpcharts;
}