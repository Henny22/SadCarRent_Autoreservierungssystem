package MainMenu;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.DataExchange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.LoginSystemController;
import login.Main;

public class ScheduleAppointmentController implements Initializable  {

	
	@FXML
	private DatePicker datePickerAppointment;
	@FXML
	private TextField txtFieldDescription;
	@FXML
	private Button btn7AM;
	@FXML
	private Button btn8AM;
	@FXML
	private Button btn9AM;
	@FXML
	private Button btn10AM;
	@FXML
	private Button btn11AM;
	@FXML
	private Button btn12PM;
	@FXML
	private Button btn1PM;
	@FXML
	private Button btn2PM;
	@FXML
	private Button btn3PM;
	@FXML
	private Button btn4PM;
	@FXML
	private Button btn5PM;
	@FXML
	private Button btn6PM;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private TextField txtFieldFrom;
	@FXML
	private TextField txtFieldTo;
	@FXML
	private Label lblConfirmMessage;
	
	
	DataExchange exchange= new DataExchange();
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSchedule;
	
	boolean fromSet=false;
	boolean toSet=false;
	int from=0;
	int to=0;
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
			
	}
	
	public void getFromTo(ActionEvent actionEvent) {	
		lblErrorMessage.setText("");
		if (actionEvent.getSource() == btn7AM && fromSet==false) {
			txtFieldFrom.setText("7:00");
			from=7;
			fromSet=true;
        }
		
		if (actionEvent.getSource() == btn8AM && fromSet==false) {
			txtFieldFrom.setText("8:00");
			from=8;
			fromSet=true;
        }else if(actionEvent.getSource() == btn8AM) {
        	txtFieldTo.setText("8:00");
        	to=8;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn9AM && fromSet==false) {
			txtFieldFrom.setText("9:00");
			from=9;
			fromSet=true;
        }else if(actionEvent.getSource() == btn9AM) {
        	txtFieldTo.setText("9:00");
        	to=9;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn10AM && fromSet==false) {
			txtFieldFrom.setText("10:00");
			from=10;
			fromSet=true;
        }else if(actionEvent.getSource() == btn10AM) {
        	txtFieldTo.setText("10:00");
        	to=10;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn11AM && fromSet==false) {
			txtFieldFrom.setText("11:00");
			from=11;
			fromSet=true;
        }else if(actionEvent.getSource() == btn11AM) {
        	txtFieldTo.setText("11:00");
        	to=11;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn12PM && fromSet==false) {
			txtFieldFrom.setText("12:00");
			from=12;
			fromSet=true;
        }else if(actionEvent.getSource() == btn12PM) {
        	txtFieldTo.setText("12:00");
        	to=12;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn1PM && fromSet==false) {
			txtFieldFrom.setText("13:00");
			from=13;
			fromSet=true;
        }else if(actionEvent.getSource() == btn1PM) {
        	txtFieldTo.setText("13:00");
        	to=13;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn2PM && fromSet==false) {
			txtFieldFrom.setText("14:00");
			from=14;
			fromSet=true;
        }else if(actionEvent.getSource() == btn2PM) {
        	txtFieldTo.setText("14:00");
        	to=14;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn3PM && fromSet==false) {
			txtFieldFrom.setText("15:00");
			from=15;
			fromSet=true;
        }else if(actionEvent.getSource() == btn3PM) {
        	txtFieldTo.setText("15:00");
        	to=15;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn4PM && fromSet==false) {
			txtFieldFrom.setText("16:00");
			from=16;
			fromSet=true;
        }else if(actionEvent.getSource() == btn4PM) {
        	txtFieldTo.setText("16:00");
        	to=16;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn5PM && fromSet==false) {
			txtFieldFrom.setText("17:00");
			from=17;
			fromSet=true;
        }else if(actionEvent.getSource() == btn5PM) {
        	txtFieldTo.setText("17:00");
        	to=17;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn6PM && fromSet==false) {
			txtFieldFrom.setText("18:00");
			from=18;
			fromSet=true;
        }else if(actionEvent.getSource() == btn6PM) {
        	txtFieldTo.setText("18:00");
        	to=18;
        	toSet=true;
        }
		
		if(from>=to && fromSet == true && toSet == true) {
			lblErrorMessage.setText("Please choose the right time. Try it again!");
			
			resetForm();
		}
	}
	
	public void resetForm() {
		fromSet=false;
		toSet=false;
		datePickerAppointment.getEditor().clear();
		txtFieldDescription.setText("");
		txtFieldFrom.setText("");
		lblErrorMessage.setText("");
		txtFieldTo.setText("");
	}
	
	public void createAppointment() {
	
	if (txtFieldFrom.getText().isEmpty()== false && txtFieldTo.getText().isEmpty()== false && datePickerAppointment.getValue() != null && txtFieldDescription.getText().isEmpty()== false ) {
	if(datePickerAppointment.getValue().isBefore(LocalDate.now())==false){
		exchange.sendAppointmentData(LoginSystemController.getstaffID(),datePickerAppointment.getValue(), from, to, txtFieldDescription.getText());
		lblConfirmMessage.setText("Appointment has been created!");
		resetForm();
	}else {
		
		lblErrorMessage.setText("You cant create appointment in the past!");
	}} else {
		lblErrorMessage.setText("Please fil out the form. Try it again!");
	}
	}
	
	public void cancelButtonOnAction(ActionEvent event){
		try{
	            Parent root = FXMLLoader.load(getClass().getResource("/Mainmenu/Menu.fxml"));       
	            Main.getStage().setScene(new Scene(root,1050,576));
	            }catch(Exception e){
	              e.printStackTrace();
	              e.getCause();
	            }
    }

}
