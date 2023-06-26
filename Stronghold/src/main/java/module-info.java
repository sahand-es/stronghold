module Stronghold {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires org.jetbrains.annotations;
    requires passay;
    requires java.desktop;

    exports model;
    opens model;
    exports view.shape;
    opens view.shape;
    exports view;
    opens view;
}