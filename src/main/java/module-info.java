module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens View to javafx.fxml;
    exports View;

    opens Controller to javafx.fxml;
    exports Controller;

    opens Model to javafx.base;
    exports Model;
}