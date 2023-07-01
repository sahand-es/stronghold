module Stronghold {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;
    requires com.google.gson;
    requires org.jetbrains.annotations;
    requires passay;
    requires java.desktop;
    requires org.apache.commons.lang3;
    requires javafx.base;

    exports model.chat;
    opens model.chat;
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
    exports view.shape.map;
    opens view.shape.map;
    exports view.shape.trade;
    opens view.shape.trade;
    exports view.shape.government;
    opens view.shape.government;
    exports view.shape.government.popularity;
    opens view.shape.government.popularity;
    exports view.shape.profile;
    opens view.shape.profile;
    exports view.GUIController;
    opens view.GUIController;
}