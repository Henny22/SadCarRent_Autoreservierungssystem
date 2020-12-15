package menuMaintenance;
import javafx.scene.layout.AnchorPane;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import login.Main;
import menuSettings.SettingsController;

public class MaintenanceController implements Initializable {

	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    @FXML
    private AnchorPane AnchorPane;
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
	@FXML
	private Label lblErrorText;
	@FXML
	private TextField txtFieldAmount;
	@FXML
	private Label lblErrorTextDate;
	@FXML
	private Pane pnlMaintenanceCheck;
	@FXML
	private Pane pnlMaintenanceCreate;
	@FXML
	private Button btnMaintenanceWrite;
	@FXML
	private Button btnMaintenanceConfirm;
	@FXML
	private Label lblCarBrandConfirm;
	@FXML
	private Label lblCarModelConfirm;
	@FXML
	private Label lblDateFromConfirm;
	@FXML
	private Label lblDateToConfirm;
	@FXML
	private Label lblAmountConfirm;
	@FXML
	private Button btnConcludeConfirm;
	@FXML
	private ComboBox<String> comboBoxListMaintenance;
	@FXML
	private Label lblErrorTextConclude;
	@FXML
	private Button btnSettings;
	@FXML
	private Label lblConfirmText;
	@FXML
	private Label lblTextConclude;
	
	ObservableList<String> oblist = FXCollections.observableArrayList();
	
	 DataExchange exchange = new DataExchange();
	 private List<String> selectedMaintenanceDataList;
	
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
		loadComboBoxCars();
		loadComboMaintenance();		
		comboBoxCompany.setItems(FXCollections.observableArrayList("Sternpark Lippstadt","Autofit Linde Salzkotten","Autoservice Leitwolf UG München"));
		comboBoxService.setItems(FXCollections.observableArrayList("Yearly check up","Engine & Exhaust","Exterior","Tire change","Other"));
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
	}  
	
	public void setOtherTextfieldEditable() {
	if(comboBoxService.getValue() != null) {
		if(comboBoxService.getValue().equals("Other") ) {
			txtFieldReason.setEditable(true);
			txtFieldReason.setPromptText("Please fil in your other option here");
		}else {
			txtFieldReason.setEditable(false);
			txtFieldReason.setPromptText("");
			txtFieldReason.setText("");
			}
		}
	}
	
	public int getIDFromComboBox(ComboBox<String> cb) {
		String selectedValues = (String) cb.getValue();
		String stringValue  = selectedValues.substring( 0, selectedValues.indexOf("|"));
		stringValue = stringValue.trim();
		int value = Integer.parseInt(stringValue);
		return value;
	}
	
	public void confirmMaintenanceContract() {
		lblConfirmText.setText("");
		lblErrorText.setText("");
		if (ComboBoxCars.getValue() != null && comboBoxCompany.getValue() != null && comboBoxService.getValue() != null && datePickerFrom.getValue() != null &&  datePickerTo.getValue() != null && datePickerFrom.getValue().isBefore(datePickerTo.getValue()) && txtFieldAmount.getText().matches("^[0-9.]+$")) {
			//
			if(comboBoxService.getValue() == "Other" && txtFieldReason.getText().isEmpty() == false ) {
				String otherString = comboBoxService.getValue()+": "+txtFieldReason.getText() ;
				exchange.createMaintenanceContract(getIDFromComboBox(ComboBoxCars),comboBoxCompany.getValue() , otherString, datePickerFrom.getValue(), datePickerTo.getValue(), Double.parseDouble(txtFieldAmount.getText()));
				resetForm();
				lblConfirmText.setText("Maintenanced contract has been submitted.");
				loadComboBoxCars();
			}			
			else if(comboBoxService.getValue().equals("Other")==false ){
				exchange.createMaintenanceContract(getIDFromComboBox(ComboBoxCars),comboBoxCompany.getValue() , comboBoxService.getValue(), datePickerFrom.getValue(), datePickerTo.getValue(), Double.parseDouble(txtFieldAmount.getText()));
				resetForm();
				lblErrorText.setText("Maintenanced contract has been submitted.");
				loadComboBoxCars();
			}
			
		}else {
			lblErrorText.setText("Please fil out this form correctly!");
		}
	}
	
		public void checkDatePickerValid() {
		if(datePickerFrom.getValue()!= null &&datePickerTo.getValue()!= null ) {	
		if(datePickerFrom.getValue().isBefore(datePickerTo.getValue())) {
			lblErrorTextDate.setText("");
		}else {
			lblErrorTextDate.setText("The right date is before the left one. Please choose a date after");
		}
		}
		
	}
		
	public void loadComboBoxCars() {
		try {
			ComboBoxCars.getItems().clear();
			ResultSet rsCars = connectDB.createStatement().executeQuery("SELECT IDCar,brand,model from cars where Availability=1 && inMaintenance=0");

			while (rsCars.next()) {  
				ComboBoxCars.getItems().addAll(rsCars.getInt("IDCar")+ " | "+ rsCars.getString("brand")+ " | " +rsCars.getString("model")); 
			       }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		}
	
	
	public void resetForm() {
		ComboBoxCars.getSelectionModel().clearSelection();
		comboBoxCompany.getSelectionModel().clearSelection();
		comboBoxService.getSelectionModel().clearSelection();
		datePickerFrom.getEditor().clear();
		datePickerTo.getEditor().clear();
		txtFieldReason.setText("");
		txtFieldAmount.setText("");
		lblErrorText.setText("");
		lblErrorTextDate.setText("");
		}
	
	public void changeTOpnlMaintenanceCreate(ActionEvent actionEvent) {		
		pnlMaintenanceCreate.setVisible(true);
		pnlMaintenanceCheck.setVisible(false);   
}

	public void changeTOpnlMaintenanceCheck(ActionEvent actionEvent) {
		pnlMaintenanceCreate.setVisible(false);
		pnlMaintenanceCheck.setVisible(true);
		
	}
	
	
	//-------------> Panel pnlMaintenanceCheck
	
	
	
		public void loadComboMaintenance() {
			
			comboBoxListMaintenance.getItems().clear();
			try {
				ResultSet rsOrders = connectDB.createStatement().executeQuery("select IDMaintenance, maintenanceCompany,service from maintenance_contracts where completed=0");
				while (rsOrders.next()) {  
					comboBoxListMaintenance.getItems().addAll(rsOrders.getInt("IDMaintenance")+ " | Maintenance Company: "+ rsOrders.getString("maintenanceCompany")+ " | Service: " +rsOrders.getString("service")); 
				       }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		public void setConcludeMaintenanceOrder() {
			
			if(comboBoxListMaintenance.getValue() != null) {
			selectedMaintenanceDataList = new ArrayList<String>();
			int IDMaintenance = getIDFromComboBox(comboBoxListMaintenance);
			
			selectedMaintenanceDataList = exchange.getCarAndMaintenance(IDMaintenance);
	
			lblDateFromConfirm.setText(selectedMaintenanceDataList.get(0));
			lblDateToConfirm.setText(selectedMaintenanceDataList.get(1));
			lblAmountConfirm.setText(selectedMaintenanceDataList.get(2));
			lblCarBrandConfirm.setText(selectedMaintenanceDataList.get(3));
			lblCarModelConfirm.setText(selectedMaintenanceDataList.get(4));
			lblErrorTextConclude.setText("");
			}	
		}
		
		public void concludeMaintenenceContract() {
			lblTextConclude.setText("");
			lblErrorTextConclude.setText("");
			if(comboBoxListMaintenance.getValue() != null) {
				int IDMaintenance = getIDFromComboBox(comboBoxListMaintenance);
				exchange.setMainteneneOnComplete(IDMaintenance);
				lblTextConclude.setText("Maintenance contract has been signed as completed!");
				lblDateFromConfirm.setText("");
				lblDateToConfirm.setText("");
				lblAmountConfirm.setText("");
				lblCarBrandConfirm.setText("");
				lblCarModelConfirm.setText("");
				comboBoxListMaintenance.getSelectionModel().clearSelection();
				loadComboMaintenance();
			}else {
				lblErrorTextConclude.setText("Please select a not completed Maintenance contract before submitting!");
			}	
		}		
}
