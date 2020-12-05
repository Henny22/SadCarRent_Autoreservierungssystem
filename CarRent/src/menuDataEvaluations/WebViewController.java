package menuDataEvaluations;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebViewController implements Initializable {

	@FXML
    private WebView webView1;
	@FXML
	private TextField textFieldTab1;
	@FXML
    private WebView webViewTab1;
	@FXML
	private Button btnTab1;
	
	@FXML
    private WebView webView2;
	@FXML
	private TextField textFieldTab2;
	@FXML
    private WebView webViewTab2;
	@FXML
	private Button btnTab2;
	
	@FXML
    private WebView webView3;
	@FXML
	private TextField textFieldTab3;
	@FXML
    private WebView webViewTab3;
	@FXML
	private Button btnTab3;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	
	public void loadTab(ActionEvent actionEvent){
		
		 if (actionEvent.getSource() == btnTab1) {
			 WebEngine engine = webViewTab1.getEngine();
			 engine.load(textFieldTab1.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 }

		 if (actionEvent.getSource() == btnTab2) {
			 WebEngine engine = webViewTab2.getEngine();
			 engine.load(textFieldTab2.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 } 
		 if (actionEvent.getSource() == btnTab3) {
			 WebEngine engine = webViewTab3.getEngine();
			 engine.load(textFieldTab3.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 } 
	}
}
