package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

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
     
     boolean NumberInStuff, NumberInUserData; 
     boolean existNumberInStuff = false, existNumberInUserData = false;
     
     DatabaseConnection connectNow = new DatabaseConnection();
     Connection connectDB = connectNow.getConnection();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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
        if(textFieldPasswordRegister.getText().equals(textFieldConfirmPassword.getText())){
              labelAlreadyRegistered.setText(""); 
              labelPasswordNotMatch.setText("");
              registerUser();  
         }else {
             labelPasswordNotMatch.setText("Password does not match"); 
         }    
     }
     
     public void registerUser(){
         
         int staffNumber = Integer.parseInt(textFieldStaffNumber.getText());
         String username = textFieldUsernameRegister.getText();
         String password = textFieldPasswordRegister.getText();
                   
         String checkStuffNumberInStaff = "SELECT IDStaff from staffdata where IDStaff="+staffNumber+"";
         String checkStuffNumberInUserData = "SELECT IDStaff from staffaccounts where IDStaff="+staffNumber+"";
         
         try{
            Statement statement = connectDB.createStatement(); 
            Statement statement2 = connectDB.createStatement(); 
            Statement statement3 = connectDB.createStatement(); 
            ResultSet queryResultNumberInStaff = statement.executeQuery(checkStuffNumberInStaff);
            ResultSet queryResultNumberInUserData = statement2.executeQuery(checkStuffNumberInUserData);
            
            existNumberInStuff = queryResultNumberInStaff.next();
            existNumberInUserData = queryResultNumberInUserData.next();
            
            if (existNumberInStuff ==true && existNumberInUserData == false){
                String insertFields ="INSERT INTO staffaccounts( IDStaff, Username, Password) VALUES ('";
                String insertValues =staffNumber + "','"+ username +"','"+ password +"')";
                String insertToRegister = insertFields + insertValues;
                statement3.executeUpdate(insertToRegister);
                labelAlreadyRegistered.setText("User has been registered successfully. Try to login now!");
            }
            else if (existNumberInStuff ==true && existNumberInUserData == true) {
                labelAlreadyRegistered.setText("You are already registered. Please try to login!");
            }else if (textFieldStaffNumber.getText().equals("") && textFieldUsernameRegister.getText().equals("")&& textFieldPasswordRegister.getText().equals("") && textFieldConfirmPassword.getText().equals("")) {
                labelAlreadyRegistered.setText("Please fil out this form!");
            }else {
                labelAlreadyRegistered.setText("We couldn't find this Staff Number. Please try to conntact HR!");
            }        
         }catch (Exception e){
             e.printStackTrace();
             e.getCause();
         }
         
     }

}
