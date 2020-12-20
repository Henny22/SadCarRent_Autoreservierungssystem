package menuSettings;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import login.Main;
import javafx.scene.layout.AnchorPane;

public class SettingsController implements Initializable {

	@FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnCars;

    @FXML
    private Button btnFeedback;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;
    
    @FXML
	private Button btnMaintenance;
    
    @FXML
    private Button btnOverview;
    
    @FXML
	private Button btnDarkMode;
	@FXML
	private Button btnLightMode;
	@FXML
	private AnchorPane AnchorPane;
	@FXML
	private Button btnDataEvaluations;
	
	private static String styleSheet;		
	public static String getStylesheet() {
		return styleSheet;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(SettingsController.getStylesheet() != null) {
			if(SettingsController.getStylesheet().equals("darkmode")) {
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/stylesheets/style.css").toExternalForm());
			}
			else if(SettingsController.getStylesheet().equals("lightmode")) {
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/test/testStyle.css").toExternalForm());
			}
			}
		
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
        
        if (actionEvent.getSource() == btnSettings) {
            try{
            	Parent root = FXMLLoader.load(getClass().getResource("/menuSettings/Settings.fxml"));
                Main.getStage().setScene(new Scene(root, 1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
        
        if ( actionEvent.getSource() ==btnDataEvaluations) {
        	try{
            	Parent root = FXMLLoader.load(getClass().getResource("/menuDataEvaluations/DataEvualations.fxml"));
                Main.getStage().setScene(new Scene(root, 1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
	}  
	
	public void changeTheme(ActionEvent actionEvent) {
		
		if (actionEvent.getSource() == btnDarkMode) {
			//Main.getStage().getScene().getStylesheets().add("/stylesheets/style.css");	
			styleSheet= "darkmode";
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/stylesheets/style.css").toExternalForm());
        }
		if (actionEvent.getSource() == btnLightMode) {
			styleSheet = "lightmode";
			/*Main.getStage().getScene().
			 * getStylesheets().clear();
			Main.getStage().getScene().setUserAgentStylesheet(null);
			Main.getStage().getScene().getStylesheets().add(getClass().getResource().toExternalForm());*/
			
			//Main.getStage().getScene().getStylesheets().clear();
			//Main.getStage().getScene().getStylesheets().add();
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/test/testStyle.css").toExternalForm());
			
			//Application.setUserAgentStylesheet(null);
			//Main.getStage().getScene().setUserAgentStylesheet("/test/testStyle.css");
        }
	}
}
