module fr.eseo.gaia_projet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens fr.eseo.gaia_projet_java to javafx.fxml;
    exports fr.eseo.gaia_projet_java;
    exports fr.eseo.gaia_projet_java.controller;
    opens fr.eseo.gaia_projet_java.controller to javafx.fxml;
}