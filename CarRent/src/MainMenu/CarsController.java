package MainMenu;


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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TableColumn <ModelTable,String> tableColumnAvailability;
    
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
    private TextField filterField;
    
    
    @FXML
    private Pane pnlOrders;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	static int IDCar;
    public static int getIDCar(){
        return IDCar;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableColumnIDCar.setCellValueFactory(new PropertyValueFactory<>("IDCar"));
		tableColumnBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
		tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
		tableColumnSeats.setCellValueFactory(new PropertyValueFactory<>("Seats"));
		tableColumnRate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
		tableColumnAvailability.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("select IDCar,brand,model,no_of_seats,rate,Availability from cars");
            
            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("IDCar"),rs.getString("brand"),rs.getString("model"),rs.getString("no_of_seats"), rs.getString("rate"), rs.getString("Availability")));
            }
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        tableTableview.setItems(oblist);		
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
						
						if (ModelTable.getBrand().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
							return true; 
						} else if (ModelTable.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; 
						}
		                  else if (ModelTable.getAvailability().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true;
		                }
						else { 
						     return false;
						}
					});
				});

		        //Packe die FilteredList in eine SortedList.
				SortedList<ModelTable> sortedData = new SortedList<>(filteredData);
				
				// Verbinde die SortedList mit der TablewView. Sonst sortieren nicht m�glich
				// 	  Otherwise, sorting the TableView would have no effect.
				sortedData.comparatorProperty().bind(tableTableview.comparatorProperty());
				
				// F�ge die Sortierte (und gefilterte) liste zur Tabelle hinzu
				tableTableview.setItems(sortedData);
		    }

	 public void checkTableClick(MouseEvent event){

         ObservableList<ModelTable> tableList;
         tableList = tableTableview.getSelectionModel().getSelectedItems();
         IDCar =  Integer.parseInt(tableList.get(0).getIDCar());
        
         try{
         Parent root = FXMLLoader.load(getClass().getResource("selectedCarWindow.fxml"));
     
         Stage stage = new Stage();
	     stage.initStyle(StageStyle.UNDECORATED);
	     stage.setTitle("Order View");
	     //stage.setAlwaysOnTop(true);
	     
	     stage.initOwner(Main.getStage());
	     stage.initModality(Modality.WINDOW_MODAL);
	     stage.setScene(new Scene(root, 500, 485));
	     stage.setX(1150);
	     stage.setY(300);
	     stage.show();
 }catch(Exception e){
   e.printStackTrace();
   e.getCause();
 }
}
}
