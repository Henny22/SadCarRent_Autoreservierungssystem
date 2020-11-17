package menuOrders;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
    private Label labelTotalOrders1;
    
    @FXML
    private Button btnOrders;
    
    @FXML
    private Button btnCustomers;
    
    @FXML
    private Button btnOverview;
    
    @FXML
    private Button btnFeedback;
    
    @FXML
    private Button btnDataEvaluations;
    
    @FXML
    private Button btnSettings;
    
    @FXML
    private Pane pnlOrderCreate;
    
    @FXML
    private Pane pnlOrderCheck;
    
    @FXML
    private Button btnSignout;
    
    @FXML
    private Button btnCars;
    
    @FXML
    private Button btnOrderWrite;
    
    @FXML
    private Button btnOrderConfirm;

    @FXML
    private ComboBox<String> comboBoxListOrders;
    
    @FXML
    private Button btnConclude;
    
    @FXML
    private Label lblErrorTextConclude;
    
    @FXML
    private Label lblLastname;
    @FXML
    private Label lblBrand;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblAmountConfirm;
    
    @FXML
    private Label labelDelivered;
    @FXML
    private Label labelDelivered1;
    @FXML
    private Label labelPending;
    @FXML
    private Label labelPending1;
    @FXML
    private Label labelHold;
    @FXML
    private Label labelHold1;
    @FXML
    private Button btnMaintenance;
    
    ObservableList<String> oblist = FXCollections.observableArrayList();
    
    DataExchange exchange = new DataExchange();
	private List<String> selectedOrderDataList;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		setHeaderData();
		//exchange.loadDataComboBoxes("select IDCus,Firstname,Lastname,Postalcode,Birthdate from customers",ComboBoxCustomer,oblist.add("IDCus","Firstname"));
		// TODO Code in DataExchange verallgemeinern
		try {
			ResultSet rsCustomer = connectDB.createStatement().executeQuery("select IDCus,Firstname,Lastname,Postalcode,Birthdate from customers");
			ResultSet rsCategory = connectDB.createStatement().executeQuery("Select IDCat,Label,Cat_Description from category");
			ResultSet rsCar = connectDB.createStatement().executeQuery("select IDCar,brand,model from cars");
			ResultSet rsLocation = connectDB.createStatement().executeQuery("select IDLoc,Street,City from locations");
			
			while (rsCustomer.next()) {  
				ComboBoxCustomer.getItems().addAll(rsCustomer.getInt("IDCus")+ " | "+ rsCustomer.getString("Firstname")+ " | " +rsCustomer.getString("Lastname")+ " | " +rsCustomer.getInt("Postalcode")+" | "+rsCustomer.getDate("Birthdate")); 
			       }
			while (rsCategory.next()) {  
				ComboBoxCategory.getItems().addAll(rsCategory.getInt("IDCat")+ " | "+ rsCategory.getString("Label")+ " | " +rsCategory.getString("Cat_Description")); 
			       }
			while (rsCar.next()) {  
				ComboBoxCar.getItems().addAll(rsCar.getInt("IDCar")+ " | "+ rsCar.getString("brand")+ " | " +rsCar.getString("model")); 
			       }
			while (rsLocation.next()) {  
				ComboBoxLocation.getItems().addAll(rsLocation.getInt("IDLoc")+ " | "+ rsLocation.getString("City")+ " | " +rsLocation.getString("Street")); 
			       }
			loadComboBoxOrder();
			datePickerFrom.setValue(LocalDate.now());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void setHeaderData() {
		
		ArrayList<String> headerDataList = new ArrayList<String>();
		headerDataList = (ArrayList<String>) exchange.getHeaderData();
		
		labelTotalOrders.setText(headerDataList.get(0));
		labelTotalOrders1.setText(headerDataList.get(0));
	
		labelDelivered.setText(headerDataList.get(1));
		labelDelivered1.setText(headerDataList.get(1));
		
		labelPending.setText(headerDataList.get(2));
		labelPending1.setText(headerDataList.get(2));
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
	
	
	
	public void loadDataComboBoxCar() {
		ComboBoxCar = exchange.loadDataComboBoxCar(ComboBoxCar,ComboBoxCategory);
	}
	
	public void loadTotalAmount(){
		String firstCharComboBoxComboBoxCar = ComboBoxCar.getValue();
			
		LocalDate dateFrom = datePickerFrom.getValue();
		LocalDate dateTo = datePickerTo.getValue();
		
		long daysFrom = datePickerFrom.getValue().toEpochDay();
		long daysTo = datePickerTo.getValue().toEpochDay();
		
		int  rentDuration  = (int) Math.abs(daysFrom-daysTo)+1;
		
		if(ComboBoxCar.getValue() != null && dateFrom.compareTo(dateTo)<=0)  {
			double amount = exchange.getSelectedCarRate(Integer.parseInt(String.valueOf(firstCharComboBoxComboBoxCar.charAt(0))));
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
				exchange.createBill(amount,exchange.getHightestID(),LocalDate.now());
				setHeaderData();
		        lblErrorText.setText("Order was succesful and bill has been created!");
		        lblErrorText.setVisible(true);
			} else {
				lblErrorText.setText("Not a valid Date!");
			}
			}
		else {
			lblErrorText.setText("Please select products before submitting!");
			}
		}
	
	public int getIDFromComboBox(ComboBox<String> cb) {
		String selectedValues = (String) cb.getValue();
		String stringValue  = selectedValues.substring( 0, selectedValues.indexOf("|"));
		stringValue = stringValue.trim();
		int value = Integer.parseInt(stringValue);
		return value;
	}
	
	public void openRegisterForm() {
		 try{   
	            Parent root = FXMLLoader.load(getClass().getResource("RegisterFormCustomer.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("Register form customer");
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.setScene(new Scene(root, 520, 627));
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.show();
	        }catch(Exception e){
	          e.printStackTrace();
	          e.getCause();
	        } 
	}
		
	public void resetForm() {
		 ComboBoxCustomer.getSelectionModel().clearSelection();
		 //: TODO ComboBoxCategory.getSelectionModel().clearSelection();
		 ComboBoxCar.getSelectionModel().clearSelection();	
		 ComboBoxLocation.getSelectionModel().clearSelection();
		 datePickerTo.getEditor().clear();
		 lblAmount.setText("");
		 lblErrorText.setText("");	
	 }
	
	
	
	public void changeTOpnlOrderCreate(ActionEvent actionEvent) {		
        	pnlOrderCreate.setVisible(true);
			pnlOrderCheck.setVisible(false);    
	}

	public void changeTOpnlOrderCheck(ActionEvent actionEvent) {
		pnlOrderCreate.setVisible(false);
		pnlOrderCheck.setVisible(true);
	}
	
	
	
	
	//-------------> Panel CompleteOrder
	
	
	
	public void loadComboBoxOrder() {
		comboBoxListOrders.getItems().clear();
		try {
			ResultSet rsOrders = connectDB.createStatement().executeQuery("SELECT IDReservation,Date, startDate, endDate from orders where completed=0");
			while (rsOrders.next()) {  
				comboBoxListOrders.getItems().addAll(rsOrders.getInt("IDReservation")+ " | Order Date: "+ rsOrders.getString("Date")+ " | Rent start: " +rsOrders.getString("startDate")+ " | Rent end: " +rsOrders.getString("endDate")); 
			       }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setConcludeDataOrder() {
		if(comboBoxListOrders.getValue() != null) {
		selectedOrderDataList = new ArrayList<String>();
		int IDReservation = getIDFromComboBox(comboBoxListOrders);
		
		selectedOrderDataList = exchange.getOrderData(IDReservation);
		
		lblLastname.setText(selectedOrderDataList.get(0));
		lblBrand.setText(selectedOrderDataList.get(1));
		lblCity.setText(selectedOrderDataList.get(2));
		lblAmountConfirm.setText(selectedOrderDataList.get(3));
		}
		}
	
	public void concludeDataOrder() {
		if(comboBoxListOrders.getValue() != null) {
			int IDReservation = getIDFromComboBox(comboBoxListOrders);
			exchange.setOrderOnComplete(IDReservation);
			lblErrorTextConclude.setText("Order has been signed as completed!");
			lblLastname.setText("");
			lblBrand.setText("");
			lblCity.setText("");
			lblAmountConfirm.setText("");
			loadComboBoxOrder();
		}else {
			lblErrorTextConclude.setText("Please select a not completed Order before submitting!");
		}
			
	}	
}
