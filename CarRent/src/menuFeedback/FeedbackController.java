package menuFeedback;

import java.net.URL;
import java.util.ResourceBundle;
import database.DataExchange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import login.Main;

public class FeedbackController  implements Initializable {

	
	@FXML
	private TextField txtFieldStaffID;
	@FXML
	private PasswordField passwordFieldPassword;
	@FXML
	private Button btnLockScreen;
	@FXML
	private Label lblErrorText;
	@FXML
	private Pane pnlLockScreen;
	@FXML
	private Pane pnlFeedbackForm;
	@FXML
	private Button btnOrders;
	@FXML
	private Button btnCustomers;
	@FXML
	private Button btnOverview;
	@FXML
	private Button btnCars;
	@FXML
	private Button btnMaintenance;
	@FXML
	private Button btnFeedback;
	@FXML
	private Button btnSignout;
	@FXML
	private Button btnDataEvaluations;
	@FXML
	private Button btnSettings;
	@FXML
	private RadioButton rButtonProcedureVerySatisfactory;
	@FXML
	private RadioButton rButtonProcedureSatisfactory;
	@FXML
	private RadioButton trButtonProcedureNeutral;
	@FXML
	private RadioButton rButtonProcedureDissatisfactory;
	@FXML
	private RadioButton rButtonProcedureVeryDissatisfactory;
	
	@FXML
	private RadioButton rButtonLevelVerySatisfactory;
	@FXML
	private RadioButton rButtonLevelSatisfactory;
	@FXML
	private RadioButton rButtonLevelNeutral;
	@FXML
	private RadioButton rButtonLevelDissatisfactory;
	@FXML
	private RadioButton rButtonLevelVeryDissatisfactory;
	
	@FXML
	private RadioButton rButtonExpectationsVerySatisfactory;
	@FXML
	private RadioButton rButtonExpectationsSatisfactory;
	@FXML
	private RadioButton rButtonExpectationsNeutral;
	@FXML
	private RadioButton rButtonExpectationsDissatisfactory;
	@FXML
	private RadioButton rButtonExpectationsVeryDissatisfactory;
	
	@FXML
	private RadioButton rButtonProcessVerySatisfactory;
	@FXML
	private RadioButton rButtonProcessSatisfactory;
	@FXML
	private RadioButton rButtonProcessNeutral;
	@FXML
	private RadioButton rButtonProcessDissatisfactory;
	@FXML
	private RadioButton rButtonProcessVeryDissatisfactory;
	
	@FXML
	private RadioButton rButtonImpressionVerySatisfactory;
	@FXML
	private RadioButton rButtonImpressionSatisfactory;
	@FXML
	private RadioButton rButtonImpressionNeutral;
	@FXML
	private RadioButton rButtonImpressionDissatisfactory;
	@FXML
	private RadioButton rButtonImpressionVeryDissatisfactory;
	
	@FXML
	private Button btnSubmit;
	@FXML
	private TextField txtFieldContractNumber;
	@FXML
	private ComboBox<String> comboBoxPorpuse;
	@FXML
	private ToggleGroup firstChoiceGroup;
	@FXML
	private ToggleGroup secondChoiceGroup;
	@FXML
	private ToggleGroup thirdChoiceGroup;
	@FXML
	private ToggleGroup fourthChoiceGroup;
	@FXML
	private ToggleGroup fifthChoiceGroup;
	
	DataExchange exchange = new DataExchange();
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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
	}   
	
	public void lockScreenAndChangeToPanelFeedbackForm() {

		boolean isValid = exchange.checkValidStaffData(Integer.parseInt(txtFieldStaffID.getText()),passwordFieldPassword.getText());
		checkScreenUnlocks(isValid);		
	}

	public void checkScreenUnlocks(boolean isValid) {
		if (isValid == true) {
			pnlLockScreen.setVisible(false);
			pnlFeedbackForm.setVisible(true);
			btnOverview.setDisable(true);
			btnOrders.setDisable(true);
			btnCustomers.setDisable(true);
			btnCars.setDisable(true);
			btnFeedback.setDisable(true);
			btnSignout.setDisable(true);
			btnMaintenance.setDisable(true);
			btnSettings.setDisable(true);
			btnDataEvaluations.setDisable(true);
		}else {
			pnlLockScreen.setVisible(true);
			pnlFeedbackForm.setVisible(false);
		}
	}
	
	public void submitFeedbackForm() {
		if(firstChoiceGroup.getSelectedToggle() != null && secondChoiceGroup.getSelectedToggle() != null && thirdChoiceGroup.getSelectedToggle()!= null && fourthChoiceGroup.getSelectedToggle()!= null && fifthChoiceGroup.getSelectedToggle()!= null) {
			
			System.out.println("es geht!");
		}else {
			System.out.println("es geht NICHT !");
		}
	}
	
	public void checkIfAllSelected() {
		boolean allSelected = false;
	 if(firstChoiceGroup.getSelectedToggle() != null && secondChoiceGroup.getSelectedToggle() != null && thirdChoiceGroup.getSelectedToggle()!= null && fourthChoiceGroup.getSelectedToggle()!= null && fifthChoiceGroup.getSelectedToggle()!= null) {
				
				System.out.println("es geht!");
			}else {
				System.out.println("es geht NICHT !");
			}
		}
		
	}

