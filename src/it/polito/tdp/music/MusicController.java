package it.polito.tdp.music;

import java.net.URL;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.Model;
import it.polito.tdp.music.model.TopArtists;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {

	Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Month> boxMese;

	@FXML
	private Button btnArtisti;

	@FXML
	private Button btnNazioni;

	@FXML
	private TextArea txtResult;

	@FXML
	void doElencoArtisti(ActionEvent event) {
		try {
			txtResult.clear();

			if (boxMese.getValue() != null) {
				List<TopArtists> artists = model.trovaArtisti(boxMese.getValue());
				for (TopArtists ta : artists) {
					txtResult.appendText(ta.toString());
				}
				btnNazioni.setDisable(false);

			} else {
				txtResult.setText("Selezionare un mese");
				return;
			}
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DB!");
		}
	}

	@FXML
	void doDistanza(ActionEvent event) {
		try {
			model.creaGrafo(boxMese.getValue());
			txtResult.setText(String.format("La massima distanza fra due nodi adiacenti del grafo e': %d",
					model.maxDistanzaCountry()));

		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DB!");
		}
	}

	@FXML
	void initialize() {
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert btnArtisti != null : "fx:id=\"btnArtisti\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert btnNazioni != null : "fx:id=\"btnNazioni\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicA.fxml'.";
		btnNazioni.setDisable(true);

	}

	public void setModel(Model model) {
		this.model = model;
		boxMese.setItems(FXCollections.observableArrayList(model.getMonths()));
	}
}
