package MainMenu;


import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import login.Main;

public class CarsController implements Initializable{

	@FXML
    private TableView <ModelTable>tableTableview;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnIDCar;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnBrand;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnModel;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnSeats;
    
    @FXML
    private TableColumn <ModelTable,String> tableColumnRate;
    
    @FXML
    private Button btnAddCar;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnFeedback;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCars;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnDataEvaulations;
    
    
    @FXML
    private Pane pnlOrders;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnOrders) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("Orders.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
            
        }
        if (actionEvent.getSource() == btnCustomers) {
        	try{
                Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
                }catch(Exception e){
                  e.printStackTrace();
                  e.getCause();
                }
 
        }
        if (actionEvent.getSource() == btnOverview) {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));       
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
                Parent root = FXMLLoader.load(getClass().getResource("Cars.fxml"));       
                Main.getStage().setScene(new Scene(root,1050,576));
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

}
