package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.AgsListe.StadtNichtGefundenAusnahme;
import model.Destination;
import model.DestinationList;

public class Controller {
	
	DestinationList list = new DestinationList();
	
	
	public Controller() {
		try {
			this.list = loadDestinationList();
		} catch (Exception e) {
			this.list = new DestinationList();
		}
		
	}
	
	
	private DestinationList loadDestinationList() throws IOException, ClassNotFoundException {


		FileInputStream fiStream = new FileInputStream("aktuelleListe.txt");
		ObjectInputStream oiStream = new ObjectInputStream(fiStream);
		DestinationList ret = (DestinationList) oiStream.readObject();
		oiStream.close();
		
		return ret;
	}
	
	private void saveDestinationList(DestinationList list) throws IOException {
	
			FileOutputStream foStream = new FileOutputStream("aktuelleListe.txt");
			ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
			ooStream.writeObject(list);
			ooStream.flush();
			ooStream.close();
		
	}


	/*
	 * fügt einen Ort zu der Liste hinzu, akualisiert die Ansicht
	 */
	public void addOrt(String name, GridPane grid, Label errorMessage) {
		errorMessage.setVisible(false);
		try {
			this.list.add(new Destination(name));
			saveDestinationList(this.list);
		} catch (StadtNichtGefundenAusnahme | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMessage.setVisible(true);
			
		}
		updateTable(grid);
	}
	
	/*
	 * erstellt GridPane aus Destinationen des Arrays
	 */
		
	public void updateTable(GridPane grid) {
		cleanTable(grid);
		int row = 1;
		for (Destination dest : list) {
			Label ort = new Label(dest.getOrt());
			grid.add(ort, 0, row);
			Label inz = new Label(dest.getInzidenz()+"");
			grid.add(inz, 1, row);
			Button deleteButton = new Button ("löschen");
			deleteButton.setOnAction(e->deleteEntry(grid,dest,list));
			grid.add(deleteButton, 2 , row);
			deleteButton.setVisible(false);
			ort.setOnMouseClicked(e->{if(deleteButton.isVisible())
				deleteButton.setVisible(false);
				else deleteButton.setVisible(true);
			});
			ort.setOnMouseEntered(e->ort.setBackground(new Background (new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), new Insets (-5.0)))));
			ort.setOnMouseExited(e->ort.setBackground(null));
			
			row++;
		}
	}
	
	private void deleteEntry(GridPane grid, Destination dest, DestinationList list) {
		list.remove(dest);
		updateTable(grid);
	}
	
	private void cleanTable(GridPane grid) {
		grid.getChildren().clear();
		
		//Überschrift Tabelle
		Label ort = new Label("Ort");
		ort.setMinWidth(200);
		grid.add(ort, 0, 0);
		Label inzidenz = new Label("Inzidenz");
		inzidenz.setMinWidth(200);
		grid.add(inzidenz, 1, 0);
		
	}
	
	
}
