module com.example.carRace {
    requires javafx.controls;
    requires javafx.fxml;



    exports com.carRace;
    opens com.carRace to javafx.fxml;
}