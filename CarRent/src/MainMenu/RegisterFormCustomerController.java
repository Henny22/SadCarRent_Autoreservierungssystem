package MainMenu;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import database.DataExchange;



public class RegisterFormCustomerController implements Initializable{

	
	@FXML
	private TextField txtfieldFirstname;
	@FXML
	private TextField txtfieldLastname;
	@FXML
	private TextField txtfieldEmail;
	@FXML
	private DatePicker txtfieldBirthdate;
	@FXML
	private TextField txtfieldStreet;
	@FXML
	private TextField txtfieldStreetNo;
	@FXML
	private TextField txtfieldCity;
	@FXML
	private TextField txtfieldPostalcode;
	@FXML
	private TextField txtfieldState;
	@FXML
	private TextField txtfieldCountry;
	@FXML
	private TextField txtfieldMobilephone;
	@FXML
	private Button btnReset;
	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnClose;
	
	DataExchange exchange = new DataExchange();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void createCustomer() {
		DataExchange exchange = new DataExchange();
		exchange.setDataInCustomer(txtfieldFirstname.getText(),txtfieldLastname.getText(),txtfieldEmail.getText(),txtfieldBirthdate.getValue(),txtfieldStreet.getText(),txtfieldStreetNo.getText(),txtfieldCity.getText(),txtfieldPostalcode.getText(),txtfieldState.getText(),txtfieldCountry.getText(),txtfieldMobilephone.getText());
		resetForm();
	}
	
	 public void backToOrders(){
         Stage stage = (Stage) btnClose.getScene().getWindow();
         stage.close();
      }
	 
	 public void resetForm() {
		 txtfieldFirstname.clear();
		 txtfieldLastname.clear();
		 txtfieldEmail.clear();
		 txtfieldBirthdate.setValue(null);
		 txtfieldStreet.clear();
		 txtfieldStreetNo.clear();
		 txtfieldCity.clear();
		 txtfieldPostalcode.clear();
		 txtfieldState.clear();
		 txtfieldCountry.clear();
		 txtfieldMobilephone.clear();	
		 
	 }
}
