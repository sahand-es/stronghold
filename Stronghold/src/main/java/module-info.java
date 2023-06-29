module Stronghold {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;
    requires com.google.gson;
    requires org.jetbrains.annotations;
    requires passay;
    requires java.desktop;

    exports model;
    opens model;
    exports model.map;
    opens model.map;
    exports view.shape;
    opens view.shape;
    exports view.fxmlcontroller;
    opens view.fxmlcontroller;
    exports view;
    opens view;
}