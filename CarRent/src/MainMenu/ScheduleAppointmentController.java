package MainMenu;

import java.net.URL;
import java.util.ResourceBundle;

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
import login.Main;

public class ScheduleAppointmentController implements Initializable  {

	
	@FXML
	private DatePicker datePickerAppointment;
	@FXML
	private TextField txtFieldDescription;
	@FXML
	private Label lblFrom;
	@FXML
	private Label lblTo;
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
	private Button btnCancel;
	@FXML
	private Button btnSchedule;
	
	boolean fromSet=false;
	boolean toSet=false;
	int weightingFrom=0;
	int weightingTo=0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public void getFromTo(ActionEvent actionEvent) {	
		lblErrorMessage.setText("");
		if (actionEvent.getSource() == btn7AM && fromSet==false) {
			lblFrom.setText("7:00");
			weightingFrom=7;
			fromSet=true;
        }
		
		if (actionEvent.getSource() == btn8AM && fromSet==false) {
			lblFrom.setText("8:00");
			weightingFrom=8;
			fromSet=true;
        }else if(actionEvent.getSource() == btn8AM) {
        	lblTo.setText("8:00");
        	weightingTo=8;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn9AM && fromSet==false) {
			lblFrom.setText("9:00");
			weightingFrom=9;
			fromSet=true;
        }else if(actionEvent.getSource() == btn9AM) {
        	lblTo.setText("9:00");
        	weightingTo=9;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn10AM && fromSet==false) {
			lblFrom.setText("10:00");
			weightingFrom=10;
			fromSet=true;
        }else if(actionEvent.getSource() == btn10AM) {
        	lblTo.setText("10:00");
        	weightingTo=10;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn11AM && fromSet==false) {
			lblFrom.setText("11:00");
			weightingFrom=11;
			fromSet=true;
        }else if(actionEvent.getSource() == btn11AM) {
        	lblTo.setText("11:00");
        	weightingTo=11;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn12PM && fromSet==false) {
			lblFrom.setText("12:00");
			weightingFrom=12;
			fromSet=true;
        }else if(actionEvent.getSource() == btn12PM) {
        	lblTo.setText("12:00");
        	weightingTo=12;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn1PM && fromSet==false) {
			lblFrom.setText("13:00");
			weightingFrom=13;
			fromSet=true;
        }else if(actionEvent.getSource() == btn1PM) {
        	lblTo.setText("13:00");
        	weightingTo=13;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn2PM && fromSet==false) {
			lblFrom.setText("14:00");
			weightingFrom=14;
			fromSet=true;
        }else if(actionEvent.getSource() == btn2PM) {
        	lblTo.setText("14:00");
        	weightingTo=14;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn3PM && fromSet==false) {
			lblFrom.setText("15:00");
			weightingFrom=15;
			fromSet=true;
        }else if(actionEvent.getSource() == btn3PM) {
        	lblTo.setText("15:00");
        	weightingTo=15;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn4PM && fromSet==false) {
			lblFrom.setText("16:00");
			weightingFrom=16;
			fromSet=true;
        }else if(actionEvent.getSource() == btn4PM) {
        	lblTo.setText("16:00");
        	weightingTo=16;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn5PM && fromSet==false) {
			lblFrom.setText("17:00");
			weightingFrom=17;
			fromSet=true;
        }else if(actionEvent.getSource() == btn5PM) {
        	lblTo.setText("17:00");
        	weightingTo=17;
        	toSet=true;
        }
		
		if (actionEvent.getSource() == btn6PM && fromSet==false) {
			lblFrom.setText("18:00");
			weightingFrom=18;
			fromSet=true;
        }else if(actionEvent.getSource() == btn6PM) {
        	lblTo.setText("18:00");
        	weightingTo=18;
        	toSet=true;
        }
		
		if(weightingTo<=weightingFrom && fromSet == true && toSet == true) {
			lblErrorMessage.setText("Please choose the right time. Try it again!");
			fromSet=false;
			toSet=false;
			lblFrom.setText("");
			lblTo.setText("");
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
