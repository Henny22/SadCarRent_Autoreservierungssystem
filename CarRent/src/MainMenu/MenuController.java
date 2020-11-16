package MainMenu;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.DatabaseConnection;
import login.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
        if(actionEvent.getSource()==btnFeedback)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
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
    }
	
	public void setHeaderData(){
        String numberOrders="";
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT COUNT(*) FROM orders");
            while(rs.next()){
                numberOrders = rs.getString("COUNT(*)");
            }
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        labelTotalOrders.setText(numberOrders);
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
	
	 
}
