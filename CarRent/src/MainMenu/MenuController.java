package MainMenu;

import java.net.URL;
import java.util.ResourceBundle;

import login.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController implements Initializable {

	@FXML
    private Button btnSignout;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void backToLoginOnAction(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/login/LoginSystem.fxml"));
        Main.getStage().setScene(new Scene(root, 520, 400));
        }catch(Exception e){
          e.printStackTrace();
          e.getCause();
        }
    }
}
