module fr.eseo.gaia_projet_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.eseo.gaia_projet_java to javafx.fxml;
    exports fr.eseo.gaia_projet_java;
}