package menuMaintenance;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.DataExchange;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.Main;

public class MaintenanceController implements Initializable {

	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
	@FXML
	private Button btnOrders;
	@FXML
	private Button btnCustomers;
	@FXML
	private Button btnOverview;
	@FXML
	private Button btnCars;
	@FXML
	private Button btnFeedback;
	@FXML
	private Button btnSignout;
	@FXML
	private Button btnMaintenance;
	@FXML
	private ComboBox<String> ComboBoxCars;
	@FXML
	private ComboBox<String> comboBoxCompany;
	@FXML
	private ComboBox<String> comboBoxService;
	@FXML
	private TextField txtFieldReason;
	@FXML
	private DatePicker datePickerFrom;
	@FXML
	private DatePicker datePickerTo;
	
	ObservableList<String> oblist = FXCollections.observableArrayList();
	
	 DataExchange exchange = new DataExchange();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ResultSet rsCars = connectDB.createStatement().executeQuery("SELECT IDCar,brand,model from cars where Availability=1 && inMaintenance=0");

			while (rsCars.next()) {  
				ComboBoxCars.getItems().addAll(rsCars.getInt("IDCar")+ " | "+ rsCars.getString("brand")+ " | " +rsCars.getString("model")); 
			       }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		comboBoxCompany.setItems(FXCollections.observableArrayList("Sternpark Lippstadt","Autofit Linde Salzkotten","Autoservice Leitwolf UG München"));
		comboBoxService.setItems(FXCollections.observableArrayList("Yearly check up","Engine & Exhaust","Exterior","Tire change"));
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
}
