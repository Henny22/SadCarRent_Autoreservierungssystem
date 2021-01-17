package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import database.DataExchangeLogin;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class RegisterController  implements Initializable  {
	
	 @FXML
     private TextField textFieldStaffNumber;
     @FXML
     private TextField textFieldUsernameRegister;
     @FXML
     private PasswordField textFieldPasswordRegister;
     @FXML
     private Button buttonRegister;
     @FXML
     private Button buttonClose;
     @FXML
     private  PasswordField textFieldConfirmPassword;
     @FXML
     private Label labelPasswordNotMatch;
     @FXML
     private Label labelAlreadyRegistered;
     @FXML
     private Label labelSuccesRegistered;
     
     boolean NumberInStuff, NumberInUserData; 
     boolean existNumberInStuff = false, existNumberInUserData = false;
     
     DatabaseConnection connectNow = new DatabaseConnection();
     Connection connectDB = connectNow.getConnection();
	
     DataExchangeLogin exchangeLogin_Register = new DataExchangeLogin();
     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	  
    public void backToMainMenuOnActionFromRegister(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("LoginSystem.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
     
     public void registerButtonOnAction(ActionEvent e){
     if(exchangeLogin_Register.checkIfUsernameIsTaken(textFieldUsernameRegister.getText())==false) {
         if(textFieldPasswordRegister.getText().equals(textFieldConfirmPassword.getText())){
    			 if (textFieldStaffNumber.getText().equals("") || textFieldUsernameRegister.getText().equals("") || textFieldPasswordRegister.getText().equals("") || textFieldConfirmPassword.getText().equals("")) {
    				 labelAlreadyRegistered.setText("Please fil out this form");
    				 labelPasswordNotMatch.setText("");
    			 }else {
    				 labelAlreadyRegistered.setText(""); 
    				 labelPasswordNotMatch.setText("");
    				 registerUser(); 
    			 } 
    	 }else {
    		 labelAlreadyRegistered.setText("");	
    		 labelPasswordNotMatch.setText("Password does not match"); 
         }
    }else {
    	labelAlreadyRegistered.setText("Username is already taken! Please choose another one!");	
    }
     }
     
     
     	// dataCase:
  		// dataCase= 0 not found in Stuff & UserData
  		// dataCase= 1 found in Staff & not in UserData
  		// dataCase= 2 found in Staff & UserData
     public void registerUser(){
         if(textFieldStaffNumber.getText().matches("[0-9]*"))
         {
    	 int staffNumber = Integer.parseInt(textFieldStaffNumber.getText());
         String username = textFieldUsernameRegister.getText();
         String password = textFieldPasswordRegister.getText();
    	 
    	 int dataCase = exchangeLogin_Register.checkEmployeeInDatabase(staffNumber);
    	 int jobInt= exchangeLogin_Register.checkIFEmployeeAdministrator(staffNumber);
    	 
    	 labelSuccesRegistered.setText("");
    	 labelAlreadyRegistered.setText("");
    	 if (dataCase == 2) {
    		 labelPasswordNotMatch.setText("");
    		 labelAlreadyRegistered.setText("You are already registered. Please try to login!");
         }else if (dataCase == 1){
        	 exchangeLogin_Register.registerUser(staffNumber,username,password,jobInt);
        	 
        	 labelSuccesRegistered.setText("User has been registered successfully. Try to login now!");
         }else if (dataCase == 0){
             labelAlreadyRegistered.setText("We couldn't find this Staff Number. Please try to conntact HR!");
         }

         }else {
        	 labelAlreadyRegistered.setText("Only digits [0-9] for staff number allowed!");
         }
           
     }

}
