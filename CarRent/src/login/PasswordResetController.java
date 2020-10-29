package login;

import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import database.DataExchangeLogin;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class PasswordResetController implements Initializable {

	 private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	    private static final String NUMBER = "0123456789";

	    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	    private static SecureRandom random = new SecureRandom();
	    
	    DatabaseConnection connectNow = new DatabaseConnection();
	     Connection connectDB = connectNow.getConnection();
	    

	    @FXML
	    private TextField textFieldStaffID;
	    @FXML
	    private TextField textFieldUsernamePasswordGenerator;
	    @FXML
	    private Button buttonGeneratePassword;
	    @FXML
	    private Button buttonBackToLogin;
	    @FXML
	    private Label labelShowNewPassword;
	    @FXML
	    private Label labelPassword;
	    
	    boolean checkExistance;
	    
	    DataExchangeLogin exchangeLogin_PasswordReset = new DataExchangeLogin();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	 public void backToMainMenuOnAction(){
         Stage stage = (Stage) buttonBackToLogin.getScene().getWindow();
         stage.close();
      }
	
	 
	public void checkData(){ 
		if(textFieldStaffID.getText().isEmpty() == false && textFieldUsernamePasswordGenerator.getText().isEmpty() == false && textFieldStaffID.getText().matches("[0-9]*")) {
			  			
			int staffNumber = Integer.parseInt(textFieldStaffID.getText());
			String username = textFieldUsernamePasswordGenerator.getText(); 				    
		    	if(exchangeLogin_PasswordReset.checkDataCombination(staffNumber,username) == true) {
		    		String newGeneratedPassword = generateRandomString(5);
		    		exchangeLogin_PasswordReset.changePassword(staffNumber,username,newGeneratedPassword);
		 	   labelShowNewPassword.setText("Your password has been changed. New Password: "+newGeneratedPassword);
		        valueToClipboard(newGeneratedPassword);
		    }else {
		 	   labelShowNewPassword.setText("No Combination of StaffID and Username found. Try again!"); 
		    }     
		}else {
			   labelShowNewPassword.setText("Something went wrong. Please fil out the form correctly!"); 
		}
				}
	
	    	
		
   
   
   public static String generateRandomString(int length) {
       if (length < 1) throw new IllegalArgumentException();

       StringBuilder sb = new StringBuilder(length);
       for (int i = 0; i < length; i++) {

           int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
           char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

           sb.append(rndChar);
       }
       return sb.toString();
   }
   
   public void valueToClipboard(String value){
               final Clipboard clipboard = Clipboard.getSystemClipboard();
               final ClipboardContent content = new ClipboardContent();
               content.putString(value);
               clipboard.setContent(content);         
   }
}
