package MainMenu;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import login.Main;
import database.DataExchangeLogin;
import database.DatabaseConnection;

public class SelectedCarWindowController implements Initializable{

	@FXML
	private Button btnBack;
	@FXML
	private Label labelIDCar;
	@FXML
	private Label labelBrand;
	@FXML
	private Label labelModel;
	@FXML
	private Label labelCarDescription;
	@FXML
	private Label labelIDCat;
	@FXML
	private Label labelRate;
	@FXML
	private Label labelNumberOfSeats;
	@FXML
	private Label labelAvailability;
	@FXML
    private Label labelICategorySize;
    @FXML
    private Label labelCategoryDescription;
    @FXML
    private Button btnDeleteCar;
    @FXML
    private Label labelError;
	
	
	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
    String getSelectedCarData="select IDCar, brand, model, car_description, IDCat, rate, no_of_seats, Availability from cars where IDCar="+CarsController.getIDCar();
    String getSelectedCarCategoryData="select label, Cat_Description from category where IDCat=";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 try {
	            Statement statement = connectDB.createStatement();
	            labelIDCar.setText(String.valueOf(CarsController.getIDCar()));
	            String IDCat= null;
	            ResultSet queryResultDataCar = statement.executeQuery(getSelectedCarData);
	               
	             while (queryResultDataCar.next()){
	            	labelBrand.setText( queryResultDataCar.getString("brand"));
	            	labelModel.setText( queryResultDataCar.getString("model"));     
	            	labelCarDescription.setText( queryResultDataCar.getString("car_description")); 
	            	labelCarDescription.setWrapText(true);
	            	IDCat = queryResultDataCar.getString("IDCat");
	            	labelRate.setText( queryResultDataCar.getString("rate"));
	            	labelNumberOfSeats.setText( queryResultDataCar.getString("no_of_seats"));
	            	labelAvailability.setText( queryResultDataCar.getString("Availability")); 
	           }
	             ResultSet queryResultCarCategoryData = statement.executeQuery("select label, Cat_Description from category where IDCat="+IDCat);
	             while (queryResultCarCategoryData.next()){
	            	 labelIDCat.setText(IDCat);
	            	 labelICategorySize.setText(queryResultCarCategoryData.getString("label"));
	            	 labelCategoryDescription.setText(queryResultCarCategoryData.getString("Cat_Description"));
	            	 labelCategoryDescription.setWrapText(true);
	             }
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public void deleteCar() {
		String deleteCar = "DELETE FROM cars WHERE IDCar ="+ CarsController.getIDCar();
		if(DataExchangeLogin.getIsAdministrator() == true) {
		try {		
			Statement statement = connectDB.createStatement();       
	        statement.executeUpdate(deleteCar);
	        labelError.setText("Car has been deleted");
			}catch (Exception e){
	            e.printStackTrace();
	            e.getCause();
	        }
		}
		else {
			labelError.setText("You are not authorized to do this action!");
		}
	}
	
	public void backToCarsMenuOnAction(){
		Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        try {
			Parent root = FXMLLoader.load(getClass().getResource("Cars.fxml"));
			Main.getStage().setScene(new Scene(root, 1050, 576));
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
     }
	
	


}
