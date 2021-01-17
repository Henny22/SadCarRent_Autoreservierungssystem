package login;

import java.net.URL;
import database.DataExchangeLogin;
import database.DatabaseConnection;
import java.sql.Connection;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginSystemController implements Initializable {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button buttonLogin;
    @FXML
    private Label labelStatus;
    @FXML
    private Button buttonCancel;
    
    
    public static int staffID=0;
    
    public static int getstaffID() {
    	return staffID;
    }
    
      
     DatabaseConnection connectNow = new DatabaseConnection();
     Connection connectDB = connectNow.getConnection();

     DataExchangeLogin exchangeLogin = new DataExchangeLogin();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void validateLogin(){	
    	if(textFieldUsername.getText().isEmpty() == false && textFieldPassword.getText().isEmpty() == false){
    		if (exchangeLogin.validateLogin(textFieldUsername.getText(),textFieldPassword.getText()) == true){
                    exchangeLogin.getIsAdministrator(textFieldUsername.getText(),textFieldPassword.getText());
                    staffID = exchangeLogin.getStaffID(textFieldUsername.getText(),textFieldPassword.getText());
    				loginMainMenu();
            }else {
                   labelStatus.setText("Invalid login. Please try again");
               } 
    	}
    	else {
    		labelStatus.setText("Please enter username and password!");
    	}
    }
    
    
    public void loginMainMenu(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/Mainmenu/Menu.fxml"));       
        Main.getStage().setScene(new Scene(root,1050,576));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
    public void showResetPassword(){
        try{   
            Parent root = FXMLLoader.load(getClass().getResource("PasswordReset.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Password reset");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 400, 310));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
    public void showRegistry(){
        try{ 
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
    
     public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
          
}
