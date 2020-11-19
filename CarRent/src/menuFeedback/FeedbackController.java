package menuFeedback;

import java.net.URL;
import java.util.ResourceBundle;
import database.DataExchange;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import login.Main;

public class FeedbackController  implements Initializable {

	
	@FXML
	private TextField txtFieldStaffID;
	@FXML
	private PasswordField passwordFieldPassword;
	@FXML
	private Button btnLockScreen;
	@FXML
	private Label lblErrorText;
	@FXML
	private Pane pnlLockScreen;
	@FXML
	private Pane pnlFeedbackForm;
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
	private Button btnDataEvaluations;
	@FXML
	private Button btnSettings;
	@FXML
	private RadioButton rButton11;
	@FXML
	private RadioButton rButton12;
	@FXML
	private RadioButton rButton13;
	@FXML
	private RadioButton rButton14;
	@FXML
	private RadioButton rButton15;
	
	@FXML
	private RadioButton rButton21;
	@FXML
	private RadioButton rButton22;
	@FXML
	private RadioButton rButton23;
	@FXML
	private RadioButton rButton24;
	@FXML
	private RadioButton rButton25;
	
	@FXML
	private RadioButton rButton31;
	@FXML
	private RadioButton rButton32;
	@FXML
	private RadioButton rButton33;
	@FXML
	private RadioButton rButton34;
	@FXML
	private RadioButton rButton35;
	
	@FXML
	private RadioButton rButton41;
	@FXML
	private RadioButton rButton42;
	@FXML
	private RadioButton rButton43;
	@FXML
	private RadioButton rButton44;
	@FXML
	private RadioButton rButton45;
	
	@FXML
	private RadioButton rButton51;
	@FXML
	private RadioButton rButton52;
	@FXML
	private RadioButton rButton53;
	@FXML
	private RadioButton rButton54;
	@FXML
	private RadioButton rButton55;
	
	@FXML
	private Button btnSubmit;
	@FXML
	private TextField txtFieldContractNumber;
	@FXML
	private ComboBox<String> comboBoxPorpuse;
	@FXML
	private ToggleGroup firstChoiceGroup;
	@FXML
	private ToggleGroup secondChoiceGroup;
	@FXML
	private ToggleGroup thirdChoiceGroup;
	@FXML
	private ToggleGroup fourthChoiceGroup;
	@FXML
	private ToggleGroup fifthChoiceGroup;
	@FXML
	private Label lblErrorTextFeedbackform;
	@FXML
	private TextField txtFieldIDStaffUnlock;
	@FXML
	private Button btnUnlockUI;
	@FXML
	private PasswordField passwordFieldPasswordUnlockUI;
	@FXML
	private Button btnUnlockUIhere;
	@FXML
	private Label lblErrorTextFeedbackformUnlock;
	
	DataExchange exchange = new DataExchange();
	ObservableList<String> oblist = FXCollections.observableArrayList();
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBoxPorpuse.setItems(FXCollections.observableArrayList("Business","Personal/Leisure","Replacement Car","Other"));
		
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
	}   
	
	public void lockScreenAndChangeToPanelFeedbackForm() {
		
		boolean isValid = exchange.checkValidStaffData(Integer.parseInt(txtFieldStaffID.getText()),passwordFieldPassword.getText());
		checkScreenUnlocks(isValid);
		txtFieldStaffID.setText("");
		passwordFieldPassword.setText("");
	}

	public void checkScreenUnlocks(boolean isValid) {
		if (isValid == true) {
			pnlLockScreen.setVisible(false);
			pnlFeedbackForm.setVisible(true);
			btnOverview.setDisable(true);
			btnOrders.setDisable(true);
			btnCustomers.setDisable(true);
			btnCars.setDisable(true);
			btnFeedback.setDisable(true);
			btnSignout.setDisable(true);
			btnMaintenance.setDisable(true);
			btnSettings.setDisable(true);
			btnDataEvaluations.setDisable(true);
		}else {
			pnlLockScreen.setVisible(true);
			pnlFeedbackForm.setVisible(false);
			btnOverview.setDisable(false);
			btnOrders.setDisable(false);
			btnCustomers.setDisable(false);
			btnCars.setDisable(false);
			btnFeedback.setDisable(false);
			btnSignout.setDisable(false);
			btnMaintenance.setDisable(false);
			btnSettings.setDisable(false);
			btnDataEvaluations.setDisable(false);
		}
	}
	
	public void unlockScreen() {
		if(txtFieldIDStaffUnlock.getText().isEmpty() == false && passwordFieldPasswordUnlockUI.getText().isEmpty() == false ) {
			if(exchange.checkValidStaffData(Integer.parseInt(txtFieldIDStaffUnlock.getText()),passwordFieldPasswordUnlockUI.getText()) == true) {
				checkScreenUnlocks(false);
				txtFieldIDStaffUnlock.setText("");
				passwordFieldPasswordUnlockUI.setText("");
				lblErrorTextFeedbackform.setText("");
				btnUnlockUIhere.setVisible(false);
				txtFieldIDStaffUnlock.setVisible(false);
				passwordFieldPasswordUnlockUI.setVisible(false);
			}else {
				lblErrorTextFeedbackformUnlock.setText("Login wrong. Cant Unlock!");
			}
			
		}
	}
	
	public void openLoginFormAndUIUnlockButton(ActionEvent actionEvent) {
		if(actionEvent.getSource()==btnUnlockUI) {
			btnUnlockUIhere.setVisible(true);
			txtFieldIDStaffUnlock.setVisible(true);
			passwordFieldPasswordUnlockUI.setVisible(true);
		}
	}
	
	public void submitFeedbackForm() {
		String vehicleProcedure = null, levelCustomerService= null, expectations= null, rentalProcedure= null, overallImpression= null;
		if(txtFieldContractNumber.getText().isEmpty() == false && comboBoxPorpuse.getValue() != null && firstChoiceGroup.getSelectedToggle() != null && secondChoiceGroup.getSelectedToggle() != null && thirdChoiceGroup.getSelectedToggle()!= null && fourthChoiceGroup.getSelectedToggle()!= null && fifthChoiceGroup.getSelectedToggle()!= null) {
			int IDReservation = Integer.parseInt(txtFieldContractNumber.getText());
			if(exchange.checkIfDataForCustomer(IDReservation) ==false) {
				
			// Überprüfung erster Radiobuttons
			if(rButton11.isSelected()) {
				vehicleProcedure="2";
			}else if(rButton12.isSelected()) {
				vehicleProcedure="1";
			}else if(rButton13.isSelected()) {
				vehicleProcedure="0";
			}else if(rButton14.isSelected()) {
				vehicleProcedure="-1";
			}else if(rButton15.isSelected()) {
				vehicleProcedure="-2";
			}
			// Überprüfung zweiter Radiobuttons
			if(rButton21.isSelected()) {
				levelCustomerService="2";
			}else if(rButton22.isSelected()) {
				levelCustomerService="1";
			}else if(rButton23.isSelected()) {
				levelCustomerService="0";
			}else if(rButton24.isSelected()) {
				levelCustomerService="-1";
			}else if(rButton25.isSelected()) {
				levelCustomerService="-2";
			}
			// Überprüfung dritter Radiobuttons
			if(rButton31.isSelected()) {
				expectations="2";
			}else if(rButton32.isSelected()) {
				expectations="1";
			}else if(rButton33.isSelected()) {
				expectations="0";
			}else if(rButton34.isSelected()) {
				expectations="-1";
			}else if(rButton35.isSelected()) {
				expectations="-2";
			}
			// Überprüfung vierter Radiobuttons
			if(rButton41.isSelected()) {
				rentalProcedure="2";
			}else if(rButton42.isSelected()) {
				rentalProcedure="1";
			}else if(rButton43.isSelected()) {
				rentalProcedure="0";
			}else if(rButton44.isSelected()) {
				rentalProcedure="-1";
			}else if(rButton45.isSelected()) {
				rentalProcedure="-2";
			}
			// Überprüfung fünfter Radiobuttons
			if(rButton51.isSelected()) {
				overallImpression="2";
			}else if(rButton52.isSelected()) {
				overallImpression="1";
			}else if(rButton53.isSelected()) {
				overallImpression="0";
			}else if(rButton54.isSelected()) {
				overallImpression="-1";
			}else if(rButton55.isSelected()) {
				overallImpression="-2";
			}
			exchange.sendFeedbackData(IDReservation,vehicleProcedure,levelCustomerService,expectations,rentalProcedure,overallImpression);
			resetForm();
			lblErrorTextFeedbackform.setText("Feedback has been submitted. Please contact Stuff!");
			}else {
				lblErrorTextFeedbackform.setText("Feedback has been already submitted for this order. Please contact Stuff!");
			}
		}else {
		
			lblErrorTextFeedbackform.setText("Please fil out the form correctly!");
		}		
	}
	
	public void resetForm() {
		txtFieldContractNumber.setText("");
		comboBoxPorpuse.getSelectionModel().clearSelection();
		firstChoiceGroup.getSelectedToggle().setSelected(false);
		secondChoiceGroup.getSelectedToggle().setSelected(false);
		thirdChoiceGroup.getSelectedToggle().setSelected(false);
		fourthChoiceGroup.getSelectedToggle().setSelected(false);
		fifthChoiceGroup.getSelectedToggle().setSelected(false);
	}
	
	
	
		
	}

