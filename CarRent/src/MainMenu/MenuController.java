package MainMenu;


import java.net.URL;


import login.LoginSystemController;
import login.Main;
import menuSettings.SettingsController;

import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import database.DataExchange;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {
	
	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
	    @FXML
	    private VBox pnItems = null;
	    @FXML
	    private Button btnOverview;
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
	    private Button btnWebview;
	    @FXML
	    private Pane pnlCustomer;
	    @FXML
	    private Pane pnlOrders;
	    @FXML
	    private Pane pnlOverview;
	    @FXML
	    private Pane pnlMenus;
	    @FXML
	    private Label labelTotalOrders;
		@FXML
		private Label labelDelivered;	
		@FXML
		private Label labelPending;
		@FXML
		private Button btnMaintenance;
		@FXML
		private Button btnDarkMode;
		@FXML
		private Button btnLightMode;
		@FXML
		private StackPane StackPane;
		@FXML
		private AnchorPane AnchorPane;
		@FXML
		private Label labelOnHold;
		@FXML
		private Button btnOpenAppointmentForm;
		@FXML
		private Label lblCurrentDate;
		@FXML
		private Label lblCurrentDay;
		@FXML
		private Label lblTextCurrentDay1;
		@FXML
		private Label lblTextCurrentDay2;
		@FXML
		private Label lblTextCurrentDay3;
		@FXML
		private Label lblTextCurrentDay4;
		@FXML
		private Label lblTextCurrentDay5;
		@FXML
		private Label lblNextDay;
		@FXML
		private Label lblTextNextDay1;
		@FXML
		private Label lblTextNextDay2;
		@FXML
		private Label lblTextNextDay3;
		@FXML
		private Label lblTextNextDay4;
		@FXML
		private Label lblTextNextDay5;
		
		
		DataExchange exchange = new DataExchange();
		Format dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
	    String todaysDate = dateFormat.format(new Date());
	    DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
	    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		String currentDay = sdf2.format(new Date());

		
		public static int getDayNumberNew(LocalDate date) {
		    DayOfWeek day = date.getDayOfWeek();
		    return day.getValue();
		}
		
		public static String getDayStringNew(LocalDate date, Locale locale) {
		    DayOfWeek day = date.getDayOfWeek();
		    return day.getDisplayName(TextStyle.FULL, locale);
		}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> calendarDataListToday = new ArrayList<String>();
		calendarDataListToday = (ArrayList<String>) exchange.getCalendarData(LoginSystemController.getstaffID(),LocalDate.now());
		
		ArrayList<String> calendarDataListTomorrow = new ArrayList<String>();
		calendarDataListTomorrow = (ArrayList<String>) exchange.getCalendarData(LoginSystemController.getstaffID(), LocalDate.now().plusDays(1));
		
		if(calendarDataListToday.isEmpty()==false) {
		switch(calendarDataListToday.size()) {
		  case 1:
			  lblTextCurrentDay1.setText(calendarDataListToday.get(0));
		    break;
		  case 2:
			  lblTextCurrentDay1.setText(calendarDataListToday.get(0));
			  lblTextCurrentDay2.setText(calendarDataListToday.get(1));
		    break;
		  case 3:
			  lblTextCurrentDay1.setText(calendarDataListToday.get(0));
			  lblTextCurrentDay2.setText(calendarDataListToday.get(1));
			  lblTextCurrentDay3.setText(calendarDataListToday.get(2));
			  break;
		  case 4:
			  lblTextCurrentDay1.setText(calendarDataListToday.get(0));
			  lblTextCurrentDay2.setText(calendarDataListToday.get(1));
			  lblTextCurrentDay3.setText(calendarDataListToday.get(2));
			  lblTextCurrentDay4.setText(calendarDataListToday.get(3));
			  break;
		  case 5: 
			  lblTextCurrentDay1.setText(calendarDataListToday.get(0));
			  lblTextCurrentDay2.setText(calendarDataListToday.get(1));
			  lblTextCurrentDay3.setText(calendarDataListToday.get(2));
			  lblTextCurrentDay4.setText(calendarDataListToday.get(3));
			  lblTextCurrentDay4.setText(calendarDataListToday.get(4));
			  break;
		  default:
			  lblTextCurrentDay1.setText("");
			  lblTextCurrentDay2.setText("");
			  lblTextCurrentDay3.setText("");
			  lblTextCurrentDay4.setText("");
			  lblTextCurrentDay4.setText("");
		}
		}
		
		if(calendarDataListTomorrow.isEmpty()==false) {
		switch(calendarDataListTomorrow.size()) {
		  case 1:
			  lblTextNextDay1.setText(calendarDataListTomorrow.get(0));
		    break;
		  case 2:
			  lblTextNextDay1.setText(calendarDataListTomorrow.get(0));
			  lblTextNextDay2.setText(calendarDataListTomorrow.get(1));
		    break;
		  case 3:
			  lblTextNextDay1.setText(calendarDataListTomorrow.get(0));
			  lblTextNextDay2.setText(calendarDataListTomorrow.get(1));
			  lblTextNextDay3.setText(calendarDataListTomorrow.get(2));
			  break;
		  case 4:
			  lblTextNextDay1.setText(calendarDataListTomorrow.get(0));
			  lblTextNextDay2.setText(calendarDataListTomorrow.get(1));
			  lblTextNextDay3.setText(calendarDataListTomorrow.get(2));
			  lblTextNextDay4.setText(calendarDataListTomorrow.get(3));
			  break;
		  case 5: 
			  lblTextNextDay1.setText(calendarDataListTomorrow.get(0));
			  lblTextNextDay2.setText(calendarDataListTomorrow.get(1));
			  lblTextNextDay3.setText(calendarDataListTomorrow.get(2));
			  lblTextNextDay4.setText(calendarDataListTomorrow.get(3));
			  lblTextNextDay5.setText(calendarDataListTomorrow.get(4));
			  break;
		  default:
			  lblTextNextDay1.setText("");
			  lblTextNextDay2.setText("");
			  lblTextNextDay3.setText("");
			  lblTextNextDay4.setText("");
			  lblTextNextDay5.setText("");
		}
		}
	
	    lblCurrentDate.setText(getDayStringNew(LocalDate.now(), Locale.ENGLISH ) +", " + LocalDate.now()); 
	    lblCurrentDay.setText(getDayStringNew(LocalDate.now(), Locale.ENGLISH ));
	    lblNextDay.setText(getDayStringNew(LocalDate.now().plusDays(1), Locale.ENGLISH ));
		
	    
		if(SettingsController.getStylesheet() != null) {
			if(SettingsController.getStylesheet().equals("darkmode")) {
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/stylesheets/darkmode.css").toExternalForm());
			}
			else if(SettingsController.getStylesheet().equals("lightmode")) {
			AnchorPane.getStylesheets().clear();
			AnchorPane.getStylesheets().add(getClass().getResource("/stylesheets/lightmode.css").toExternalForm());
			}
		}
		
		setHeaderData();
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
        
     if ( actionEvent.getSource() == btnWebview) {
        	try{
        		Parent root = FXMLLoader.load(getClass().getResource("/menuWebView/Webview.fxml"));
                Main.getStage().setScene(new Scene(root, 1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
        }
	}  
	
	public void setHeaderData() {
		ArrayList<String> headerDataList = new ArrayList<String>();
		headerDataList = (ArrayList<String>) exchange.getHeaderData();
		
		labelTotalOrders.setText(headerDataList.get(0));
		
		labelDelivered.setText(headerDataList.get(1));
	
		labelPending.setText(headerDataList.get(2));
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
	
	public void openAppointmentForm() {
		 try{
		        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/ScheduleAppointment.fxml"));
		        Main.getStage().setScene(new Scene(root, 767, 580));
		        }catch(Exception e){
		          e.printStackTrace();
		          e.getCause();
		        }
	}
	
	
}
