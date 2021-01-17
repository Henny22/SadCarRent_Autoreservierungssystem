package menuCars;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DataExchange;
import database.DataExchangeLogin;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.Main;

public class AddNewCarController implements Initializable {
	
	@FXML
	private TextField txtfieldBrand;
	@FXML
	private TextField txtfieldModel;
	@FXML
	private ComboBox <String> comboBoxCarCategory;
	@FXML
	private TextField txtFieldDescription;
	@FXML
	private TextField txtfieldRate;
	@FXML
	private TextField txtfieldNoOfSeats;
	@FXML
	private Button btnClose;
	@FXML
	private Label lblConfirmMessage;
	@FXML
	private Label labelErrorMessage;
	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnReset;
	
	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    DataExchange exchange = new DataExchange();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ResultSet rsCategory = connectDB.createStatement().executeQuery("Select IDCat,Label from category");			
			while (rsCategory.next()) { 
				comboBoxCarCategory.getItems().addAll(rsCategory.getInt("IDCat")+ " | "+ rsCategory.getString("Label")); 
			       }	
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void addNewCar() {
		boolean dataSubmitted;
		labelErrorMessage.setText("");
		lblConfirmMessage.setText("");
		if (txtFieldDescription.getText().isEmpty() == false && txtfieldBrand.getText().isEmpty() == false
				&& txtfieldModel.getText().isEmpty() == false &&  txtfieldRate.getText().isEmpty() == false 
				&& txtfieldNoOfSeats.getText().isEmpty() == false && txtfieldNoOfSeats.getText().matches("[0-9]") 
				&&  txtfieldRate.getText().matches("[0-9]*")){
		if (DataExchangeLogin.getIsAdministrator() == true) {
			int IDCat = getIDFromComboBox(comboBoxCarCategory);	
			int highestIDCar = exchange.getHighestIDCar();   
			try {
			dataSubmitted = exchange.addNewCar(highestIDCar,txtFieldDescription.getText(), txtfieldBrand.getText(), txtfieldModel.getText(), IDCat , txtfieldRate.getText() , txtfieldNoOfSeats.getText() );
			if(dataSubmitted == true) {
			lblConfirmMessage.setText("Car has been inserted in to the Database!");
			}else {
			labelErrorMessage.setText("Something went wrong!");	
			}
			} catch(Exception e) {
				e.printStackTrace();

			}
		}else {
			labelErrorMessage.setText("You are not authorized to do this action!");
		}
		}else {
			labelErrorMessage.setText("Please fil out this form correctly");
		}
	}
	
	public int getIDFromComboBox(ComboBox<String> cb) {
		String firstCharComboBox = (String) cb.getValue();
		int value = Integer.parseInt(String.valueOf(firstCharComboBox.charAt(0)));
		return value;
	}
	
	public void backToCarsMenuOnAction(){
		try{
            Parent root = FXMLLoader.load(getClass().getResource("/menuCars/Cars.fxml"));       
            Main.getStage().setScene(new Scene(root,1050,576));
            }catch(Exception e){
              e.printStackTrace();
              e.getCause();
            }
		Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();      
     }
	
	public void resetForm() {
		txtfieldBrand.setText("");
		txtfieldModel.setText("");	
		txtFieldDescription.setText("");	
		txtfieldRate.setText("");
		txtfieldNoOfSeats.setText("");	
		labelErrorMessage.setText("");
	 }
}
