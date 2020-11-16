package MenuCustomers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.Main;
import universal.ModelTable;

public class CustomersController implements Initializable{

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
		private Button btnSignOut;
		@FXML
		private Button btnSignout;
		@FXML
		private Button btnRegister;
		@FXML
		private Button btnDataEvualations;
		
		
		@FXML
	    private Pane pnlMenus;
	    
	    @FXML
	    private Pane pnlOrders;
	    
	    @FXML
	    private TextField filterField;
		
	 	@FXML
	    private TableView <ModelTable>tableTableview;
	    
	    @FXML
	    private TableColumn <ModelTable,String> tableColumnCustomerID;
	    
	    @FXML
	    private TableColumn <ModelTable,String> tableColumnFirstname;
	    
	    @FXML
	    private TableColumn <ModelTable,String> tableColumnLastname;
	    
	    @FXML
	    private TableColumn <ModelTable,String> tableColumnEmail;
	    
	    @FXML
	    private TableColumn <ModelTable,String> tableColumnStreet;
	
	    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
	    	    
	    DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("IDCus"));
		tableColumnFirstname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
		tableColumnLastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
		tableColumnStreet.setCellValueFactory(new PropertyValueFactory<>("Street"));
        
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("select IDCus,Firstname,Lastname,Email,Street from Customers");
            
            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("IDCus"),rs.getString("Firstname"),rs.getString("Lastname"),rs.getString("Email"), rs.getString("Street")));
            }       
        } catch (Exception e) {
           e.printStackTrace();
        }
        tableTableview.setItems(oblist);		
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
        if(actionEvent.getSource()==btnFeedback)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
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
	
	public void searchBarOnAction(ActionEvent e){    
		FilteredList<ModelTable> filteredData = new FilteredList<>(oblist, b-> true);
		       filterField.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(ModelTable -> {
						// Wenn der Filter leer ist, dann  zeige alle Customers.
										
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						// Vergleiche die Zellen mit der Eingabe
						String lowerCaseFilter = newValue.toLowerCase();
						
						if (ModelTable.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
							return true; 
						} else if (ModelTable.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; 
						}
		                  else if (ModelTable.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; 
						}else if (ModelTable.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; 
						}
						else if (ModelTable.getStreet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						     return true;
						}     
						else { 
						     return false;
						}
					});
				});

		        //Packe die FilteredList in eine SortedList.
				SortedList<ModelTable> sortedData = new SortedList<>(filteredData);
				
				// Verbinde die SortedList mit der TablewView. Sonst sortieren nicht möglich
				// 	  Otherwise, sorting the TableView would have no effect.
				sortedData.comparatorProperty().bind(tableTableview.comparatorProperty());
				
				// Füge die Sortierte (und gefilterte) liste zur Tabelle hinzu
				tableTableview.setItems(sortedData);
		    }

	
	public void openRegisterForm() {
		 try{   
	            Parent root = FXMLLoader.load(getClass().getResource("RegisterFormCustomerv2.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("Register form customer");
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.setScene(new Scene(root, 520, 627));
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.show();
	        }catch(Exception e){
	          e.printStackTrace();
	          e.getCause();
	        } 
	}

}
