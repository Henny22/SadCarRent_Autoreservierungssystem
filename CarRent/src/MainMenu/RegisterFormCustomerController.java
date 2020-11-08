package MainMenu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.Main;
import database.DataExchange;

public class RegisterFormCustomerController implements Initializable {

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
	@FXML
	private Label lblMessage;
	
	DataExchange exchange = new DataExchange();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void createCustomerAndbackToCustomers() {
		if (txtfieldFirstname.getText().isEmpty() == false && txtfieldLastname.getText().isEmpty() == false
				&& txtfieldEmail.getText().isEmpty() == false && txtfieldBirthdate.getValue() != null
				&& txtfieldStreet.getText().isEmpty() == false && txtfieldStreetNo.getText().isEmpty() == false
				&& txtfieldCity.getText().isEmpty() == false && txtfieldPostalcode.getText().isEmpty() == false
				&& txtfieldState.getText().isEmpty() == false && txtfieldCountry.getText().isEmpty() == false
				&& txtfieldMobilephone.getText().isEmpty() == false) {
		exchange.setDataInCustomer(txtfieldFirstname.getText(), txtfieldLastname.getText(), txtfieldEmail.getText(),
				txtfieldBirthdate.getValue(), txtfieldStreet.getText(), txtfieldStreetNo.getText(),
				txtfieldCity.getText(), txtfieldPostalcode.getText(), txtfieldState.getText(),
				txtfieldCountry.getText(), txtfieldMobilephone.getText());
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
			Main.getStage().setScene(new Scene(root, 1050, 576));
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		}else {
			lblMessage.setText("Please fil out this form correctly!");
		}
		
	}

	public void createCustomerAndbackToOrders() {

		if (txtfieldFirstname.getText().isEmpty() == false && txtfieldLastname.getText().isEmpty() == false
				&& txtfieldEmail.getText().isEmpty() == false && txtfieldBirthdate.getValue() != null
				&& txtfieldStreet.getText().isEmpty() == false && txtfieldStreetNo.getText().isEmpty() == false
				&& txtfieldCity.getText().isEmpty() == false && txtfieldPostalcode.getText().isEmpty() == false
				&& txtfieldState.getText().isEmpty() == false && txtfieldCountry.getText().isEmpty() == false
				&& txtfieldMobilephone.getText().isEmpty() == false && txtfieldPostalcode.getText().matches("[0-9]*") && txtfieldMobilephone.getText().matches("[0-9]*")) {

					exchange.setDataInCustomer(txtfieldFirstname.getText(), txtfieldLastname.getText(), txtfieldEmail.getText(),
					txtfieldBirthdate.getValue(), txtfieldStreet.getText(), txtfieldStreetNo.getText(),
					txtfieldCity.getText(), txtfieldPostalcode.getText(), txtfieldState.getText(),
					txtfieldCountry.getText(), txtfieldMobilephone.getText());
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));
				Main.getStage().setScene(new Scene(root, 1050, 576));
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		} else {
			lblMessage.setText("Please fil out this form correctly!");
		}
	}

	public void backToOrders() {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	public void backToOrdersAndReload() {
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
		lblMessage.setText("");
	}
}
