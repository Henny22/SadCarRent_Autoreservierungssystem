package MainMenu;


import java.net.URL;


import login.Main;
import menuSettings.SettingsController;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.ResourceBundle;

import database.DataExchange;
import database.DatabaseConnection;
import login.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {
	
	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
	    @FXML
	    private VBox pnItems = null;
	    @FXML
	    private Button btnOverview;

	    @FXML
	    private Button btnOrders;

	    @FXML
	    private Button btnCustomers;

	    @FXML
	    private Button btnCars;

	    @FXML
	    private Button btnFeedback;

	    @FXML
	    private Button btnSettings;

	    @FXML
	    private Button btnSignout;
	    
	  
	    
	    @FXML
	    private Button btnDataEvaluations;

	    @FXML
	    private Pane pnlCustomer;

	    @FXML
	    private Pane pnlOrders;

	    @FXML
	    private Pane pnlOverview;

	    @FXML
	    private Pane pnlMenus;
	    
	    @FXML
	    private Label labelTotalOrders;
	    
		@FXML
		private Label labelDelivered;
		
		@FXML
		private Label labelPending;
		
		@FXML
		private Button btnMaintenance;
		
		@FXML
		private Button btnDarkMode;
		@FXML
		private Button btnLightMode;
		@FXML
		private StackPane StackPane;
		@FXML
		private AnchorPane AnchorPane;
		@FXML
		private Label labelOnHold;
		@FXML
		private Button btnOpenAppointmentForm;
		
		DataExchange exchange = new DataExchange();
	
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		setHeaderData();
	}
	
	//Dokumentation genau erklären warum wir das so gemacht haben
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
        	// pnlMenus.setStyle("-fx-background-color : #53639F");
            //pnlMenus.toFront();
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
            //pnlOrders.setStyle("-fx-background-color : #464F67");
            //pnlOrders.toFront();
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
            //pnlOrders.setStyle("-fx-background-color : #464F67");
            //pnlOrders.toFront();
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
	
	public void setHeaderData() {
		
		ArrayList<String> headerDataList = new ArrayList<String>();
		headerDataList = (ArrayList<String>) exchange.getHeaderData();
		
		labelTotalOrders.setText(headerDataList.get(0));
		
	
		labelDelivered.setText(headerDataList.get(1));
	
		
		labelPending.setText(headerDataList.get(2));
		
	}
	
	
	public void backToLoginOnAction(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/login/LoginSystem.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
	
	public void openAppointmentForm() {
		 try{
		        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/ScheduleAppointment.fxml"));
		        Main.getStage().setScene(new Scene(root, 767, 580));
		        }catch(Exception e){
		          e.printStackTrace();
		          e.getCause();
		        }
	}
	
}
