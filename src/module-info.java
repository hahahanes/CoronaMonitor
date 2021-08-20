module Wetter_Corona {
	requires javafx.controls;
	requires java.net.http;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
