package MainMenu;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.DataExchange;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.Main;

public class OrdersController implements Initializable {

	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    @FXML
    private ComboBox<String> ComboBoxCustomer;
	
    @FXML
    private ComboBox<String> ComboBoxCategory;
    
    @FXML
    private ComboBox<String> ComboBoxCar;
    
    @FXML
    private ComboBox <String>ComboBoxLocation;
    
    @FXML
    private Button btnReset;
    
    @FXML
    private Button btnConfirm;
    
    @FXML
    private Button btnRegister;
    
    @FXML
    private Label lblAmount;
    
    @FXML
    private DatePicker datePickerFrom;
    
    @FXML
    private DatePicker datePickerTo;
    
    @FXML
    private Label lblErrorText;
    
    @FXML
    private Label labelTotalOrders;
    
    @FXML
    private Button btnOrders;
    
    @FXML
    private Button btnCustomers;
    
    @FXML
    private Button btnOverview;
    
    @FXML
    private Button btnPackages;
    
    @FXML
    private Pane pnlMenus;
    
    @FXML
    private Pane pnlOrders;
    
    @FXML
    private Button btnSignOut;

    
    DataExchange exchange = new DataExchange();
    

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTotalOrders.setText(exchange.setHeaderData());
		try {
			ResultSet rsCustomer = connectDB.createStatement().executeQuery("select IDCus,Firstname,Lastname,Postalcode,Birthdate from customers");
			ResultSet rsCategory = connectDB.createStatement().executeQuery("Select IDCat,Label,Cat_Description from category");
			ResultSet rsCar = connectDB.createStatement().executeQuery("select IDCar,brand,model from cars");
			//
			ResultSet rsLocation = connectDB.createStatement().executeQuery("select IDLoc,Street,City from locations");
			while (rsCustomer.next()) {  // loop
				ComboBoxCustomer.getItems().addAll(rsCustomer.getInt("IDCus")+ " | "+ rsCustomer.getString("Firstname")+ " | " +rsCustomer.getString("Lastname")+ " | " +rsCustomer.getInt("Postalcode")+" | "+rsCustomer.getDate("Birthdate")); 
			       }
			while (rsCategory.next()) {  // loop
				ComboBoxCategory.getItems().addAll(rsCategory.getInt("IDCat")+ " | "+ rsCategory.getString("Label")+ " | " +rsCategory.getString("Cat_Description")); 
			       }
			while (rsCar.next()) {  // loop
				ComboBoxCar.getItems().addAll(rsCar.getInt("IDCar")+ " | "+ rsCar.getString("brand")+ " | " +rsCar.getString("model")); 
			       }
			while (rsLocation.next()) {  // loop
				ComboBoxLocation.getItems().addAll(rsLocation.getInt("IDLoc")+ " | "+ rsLocation.getString("City")+ " | " +rsLocation.getString("Street")); 
			       }		
			datePickerFrom.setValue(LocalDate.now());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOrders) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
            
        }
        if (actionEvent.getSource() == btnCustomers) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        if(actionEvent.getSource()==btnPackages)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if (actionEvent.getSource() == btnSignOut) {
            try{
            	Parent root = FXMLLoader.load(getClass().getResource("/login/LoginSystem.fxml"));
                Main.getStage().setScene(new Scene(root, 520, 400));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
    }
	
	
	
	public void loadDataComboBoxCar() {
		ComboBoxCar = exchange.loadDataComboBoxCar(ComboBoxCar,ComboBoxCategory);
		/*ComboBoxCar.getItems().clear();
		String ja = ComboBoxCategory.getValue();
		int i = Integer.parseInt(String.valueOf(ja.charAt(0)));
		try {
			ResultSet rsCarUpdated = connectDB.createStatement().executeQuery("select IDCar,brand,model from cars where IDCat="+i);
			while (rsCarUpdated.next()) {  // loop
				ComboBoxCar.getItems().addAll(rsCarUpdated.getInt("IDCar")+ " | "+ rsCarUpdated.getString("brand")+ " | " +rsCarUpdated.getString("model")); 
			       }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void loadTotalAmount(){
		String c = ComboBoxCar.getValue();
		int IDCar = Integer.parseInt(String.valueOf(c.charAt(0)));
		
		LocalDate dateFrom = datePickerFrom.getValue();
		LocalDate dateTo = datePickerTo.getValue();
		
		long daysFrom = datePickerFrom.getValue().toEpochDay();
		long daysTo = datePickerTo.getValue().toEpochDay();
		
		int  rentDuration  = (int) Math.abs(daysFrom-daysTo)+1;
		
		if(ComboBoxCar.getValue() != null && dateFrom.compareTo(dateTo)<=0)  {
			double amount = exchange.getSelectedCarRate(IDCar);
			amount = amount *rentDuration;
			lblAmount.setText(amount+"€");
			
		}else {
			lblAmount.setText("Car not selected or wrong date!");
		}
	}
	
	public void sendData() {
		if (ComboBoxCustomer.getValue() != null && ComboBoxCategory.getValue() != null && ComboBoxCar.getValue() != null && ComboBoxLocation.getValue() != null && datePickerFrom.getValue() != null && datePickerTo.getValue() != null ) {
			lblErrorText.setVisible(false);
			
			int IDCust = getIDFromComboBox(ComboBoxCustomer);
			int IDCat = getIDFromComboBox(ComboBoxCategory);
			int IDCar = getIDFromComboBox(ComboBoxCar);
			int IDLoc = getIDFromComboBox(ComboBoxLocation);
					
			LocalDate dateFrom = datePickerFrom.getValue();
			LocalDate dateTo = datePickerTo.getValue();
			long daysFrom = datePickerFrom.getValue().toEpochDay();
			long daysTo = datePickerTo.getValue().toEpochDay();
			
			int  rentDuration  = (int) Math.abs(daysFrom-daysTo)+1;
			if (dateFrom.compareTo(dateTo)<=0) {	
	
				double amount = exchange.getSelectedCarRate(IDCar);
				amount = amount *rentDuration;
				lblAmount.setText(amount+"€");
	
				exchange.setDataInOrders(IDCar,IDCust,amount,IDLoc,dateFrom,dateTo);
		         
		        lblErrorText.setText("Order was succesful!");
		        lblErrorText.setVisible(true);
			} else {
				lblErrorText.setText("Not a valid Date!");
			}
			
			}
		else {
			lblErrorText.setText("Please select products before submitting!");
		}

	}
	
	public int getIDFromComboBox(ComboBox cb) {
		String a = (String) cb.getValue();
		int value = Integer.parseInt(String.valueOf(a.charAt(0)));
		return value;
	}
	
	public void openRegisterForm() {
		 try{   
	            Parent root = FXMLLoader.load(getClass().getResource("RegisterFormCustomer.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("Register form customer");
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.setScene(new Scene(root, 520, 627));
	            stage.show();
	        }catch(Exception e){
	          e.printStackTrace();
	          e.getCause();
	        }
	}
	
	

}
