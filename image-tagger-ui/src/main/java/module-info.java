module dev.nuculabs.imagetaggerui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;

    opens dev.nuculabs to javafx.fxml;
    opens dev.nuculabs.pages.main to javafx.fxml;

    exports dev.nuculabs;
    exports dev.nuculabs.pages.main;
}