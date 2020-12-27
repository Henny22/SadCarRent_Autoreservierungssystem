package menuDataEvaluations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.Main;
import menuSettings.SettingsController;

public class DataEvaluationsController implements Initializable {

	@FXML
	private Button btnOrders;
	@FXML
	private Button btnCustomers;
	@FXML
	private Button btnOverview;
	@FXML
	private Button btnCars;
	@FXML
	private Button btnMaintenance;
	@FXML
	private Button btnFeedback;
	@FXML
	private Button btnSignout;
	@FXML
	private Button btnSettings;
	@FXML
	private Button btnDataEvaluations;
	@FXML
	private Tab tabTab1;
	@FXML
	private Tab tabTab2;
	@FXML
	private Tab tabTab3;
	@FXML
	private Tab tabTab4;
	@FXML
	private Tab tabTab5;
	@FXML
	private Tab tabTabAdd;
	@FXML
	private WebView webviewWebview1;
	@FXML
	private TextField textFieldTab1;
	@FXML
	private WebView webviewWebview2;
	@FXML
	private WebView webviewWebview3;
	@FXML
	private WebView webviewWebview4;
	@FXML
	private WebView webviewWebview5;
	
	@FXML
	private TextField textFieldTab2;
	@FXML
	private TextField textFieldTab3;
	@FXML
	private TextField textFieldTab4;
	@FXML
	private TextField textFieldTab5;
	@FXML
	private TabPane tabPaneTabPane1;
	@FXML
	private Button btnAddTab;
	@FXML
	private Label lblErrorTabs;
	@FXML
    private AnchorPane AnchorPane;
	
	int tabCounter=0;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabPaneTabPane1.getTabs().remove(tabTab3);
		tabPaneTabPane1.getTabs().remove(tabTab4);
		tabPaneTabPane1.getTabs().remove(tabTab5);
		
		if(SettingsController.getStylesheet() != null) {
			if(SettingsController.getStylesheet().equals("darkmode")) {
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/stylesheets/style.css").toExternalForm());
			}
			else if(SettingsController.getStylesheet().equals("lightmode")) {
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/test/testStyle.css").toExternalForm());
			} 
			}
	}

public void handleClicks(ActionEvent actionEvent) {
        
		if (actionEvent.getSource() == btnOrders) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/menuOrders/Orders.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }     
        }
        
        if (actionEvent.getSource() == btnCustomers) {
        	try{
                Parent root = FXMLLoader.load(getClass().getResource("/menuCustomers/Customers.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
        if (actionEvent.getSource() == btnOverview) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/Menu.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
        if(actionEvent.getSource()==btnCars)
        {
        	try{
                Parent root = FXMLLoader.load(getClass().getResource("/menuCars/Cars.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
        if(actionEvent.getSource()==btnMaintenance)
        {
        	try{
                Parent root = FXMLLoader.load(getClass().getResource("/menuMaintenance/Maintenance.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
        if(actionEvent.getSource()==btnFeedback)
        {
        	try{
            	Parent root = FXMLLoader.load(getClass().getResource("/menuFeedback/Feedback.fxml"));
                Main.getStage().setScene(new Scene(root, 1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }  
    
        if (actionEvent.getSource() == btnSignout) {
            try{
            	Parent root = FXMLLoader.load(getClass().getResource("/login/LoginSystem.fxml"));
                Main.getStage().setScene(new Scene(root, 520, 400));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
        if (actionEvent.getSource() == btnSettings) {
            try{
            	Parent root = FXMLLoader.load(getClass().getResource("/menuSettings/Settings.fxml"));
                Main.getStage().setScene(new Scene(root, 1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
     if ( actionEvent.getSource() == btnDataEvaluations) {
        	try{
            	Parent root = FXMLLoader.load(getClass().getResource("/menuDataEvaluations/DataEvaluations.fxml"));
                Main.getStage().setScene(new Scene(root, 1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
	}  
	
	public void loadTab(KeyEvent e){		
	    if(e.getCode().toString().equals("ENTER") && tabPaneTabPane1.getSelectionModel().getSelectedIndex() == 0 )
	    {
	    	WebEngine engine = webviewWebview1.getEngine();
			 engine.load(textFieldTab1.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    }
	    if(e.getCode().toString().equals("ENTER") && tabPaneTabPane1.getSelectionModel().getSelectedIndex() == 1 )
	    {
	    	WebEngine engine = webviewWebview2.getEngine();
			 engine.load(textFieldTab2.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    }
	    if(e.getCode().toString().equals("ENTER") && tabPaneTabPane1.getSelectionModel().getSelectedIndex() == 2 )
	    {
	    	WebEngine engine = webviewWebview3.getEngine();
			 engine.load(textFieldTab3.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			 textFieldTab3.selectAll();
			 textFieldTab3.requestFocus();
	    }
	    if(e.getCode().toString().equals("ENTER") && tabPaneTabPane1.getSelectionModel().getSelectedIndex() == 3 )
	    {
	    	WebEngine engine = webviewWebview4.getEngine();
			 engine.load(textFieldTab4.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    }
	    if(e.getCode().toString().equals("ENTER") && tabPaneTabPane1.getSelectionModel().getSelectedIndex() == 4 )
	    {
	    	WebEngine engine = webviewWebview5.getEngine();
			 engine.load(textFieldTab5.getText());	
			 engine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    }
	    
	}
	
	public void addTab() {
		
		if(tabCounter==0) {
			tabPaneTabPane1.getTabs().addAll(tabTab3);
			btnAddTab.setLayoutX(155);
			tabPaneTabPane1.getSelectionModel().select(tabTab3);	
			tabPaneTabPane1.requestLayout();	
		} else if (tabCounter==1) {
			tabPaneTabPane1.getTabs().addAll(tabTab4);
			btnAddTab.setLayoutX(197);
			tabPaneTabPane1.getSelectionModel().select(tabTab4);
			tabPaneTabPane1.requestLayout();	
		} else if (tabCounter==2) {
			tabPaneTabPane1.getTabs().addAll(tabTab5);
			btnAddTab.setVisible(false);
			tabPaneTabPane1.getSelectionModel().select(tabTab5);
			tabPaneTabPane1.requestLayout();	
		} 
		
	tabCounter+=1;
	
	
		
	}
	
	
	
}
