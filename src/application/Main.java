package application;
	
import Controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.AgsListe;
import model.Destination;
import model.DestinationList;


public class Main extends Application {
	
	BorderPane root = new BorderPane();
		
	GridPane grid = new GridPane();
	
	Controller controller = new Controller();
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			
			
			//Auflistung der Städte
			VBox center = new VBox();
			root.setCenter(center);
			
			
			//Überschrift Tabelle
			Label ort = new Label("Ort");
			ort.setMinWidth(200);
			grid.add(ort, 0, 0);
			Label inzidenz = new Label("Inzidenz");
			inzidenz.setMinWidth(200);
			grid.add(inzidenz, 1, 0);
			
			
			
			
			
			//Schaltflächen zum Hinufügen neuer Orte
			HBox buttons = new HBox();
			center.getChildren().addAll(grid,buttons);
			
			Label stadtNichtGefunden = new Label("Stadt nicht gefunden.");
			stadtNichtGefunden.setVisible(false);
			center.getChildren().add(stadtNichtGefunden);
			
			TextField neuerOrt = new TextField("neuer Ort...");
			neuerOrt.setOnAction(e->controller.addOrt(neuerOrt.getText(), grid, stadtNichtGefunden));
			
			Button add = new Button("Hinzufügen");
			buttons.getChildren().addAll(neuerOrt,add);
			add.setOnAction(e-> controller.addOrt(neuerOrt.getText(), grid, stadtNichtGefunden));
			
			//Tabelle mit aktuellen Werten füllen
			controller.updateTable(grid);
			
			
			
					
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public static void main(String[] args) {
		AgsListe.readStadtListe();
		launch(args);
	}
}
