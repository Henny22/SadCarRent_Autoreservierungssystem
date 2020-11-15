package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class DataExchange {

	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
	public DataExchange () {
		
	}
	
	
		//
		//********************************************************** Orders Controller **************************************************************************************
		//
		//
	
	public String setHeaderData(){
        String numberOrders="";
        try {
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT COUNT(*) FROM orders");
            while(rs.next()){
                numberOrders = rs.getString("COUNT(*)");
            }
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        return numberOrders;
    }
	
	// 
	//
	//********************************************************** Orders Controller **************************************************************************************
	//
	//
	
	
	public ComboBox<String> loadDataComboBoxes(String queryCommand, ComboBox<String> comboBox, ObservableList <String> liste){
		// TODO Code in DataExchange verallgemeinern
		try {
			ResultSet rs = connectDB.createStatement().executeQuery(queryCommand);
			
			while (rs.next()) {  // loop
				comboBox.getItems().addAll(rs.getInt("IDCus")+ " | "+ rs.getString("Firstname")+ " | " +rs.getString("Lastname")+ " | " +rs.getInt("Postalcode")+" | "+rs.getDate("Birthdate")); 
			       }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comboBox;
	}
	
	public ComboBox<String> loadDataComboBoxCar(ComboBox<String> comboBoxCar, ComboBox<String> comboBoxCategory){
		comboBoxCar.getItems().clear();
		String ja = (String) comboBoxCategory.getValue();
		int i = Integer.parseInt(String.valueOf(ja.charAt(0)));
		try {
			ResultSet rsCarUpdated = connectDB.createStatement().executeQuery("select IDCar,brand,model from cars where IDCat="+i);
			while (rsCarUpdated.next()) {  // loop
				comboBoxCar.getItems().addAll(rsCarUpdated.getInt("IDCar")+ " | "+ rsCarUpdated.getString("brand")+ " | " +rsCarUpdated.getString("model")); 
			       }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comboBoxCar;
	}
	
	
	public void setDataInOrders(int IDCar, int IDCust, double amount, int IDLoc, LocalDate dateFrom, LocalDate dateTo ) {

		String insertFields ="INSERT INTO orders(`IDCar`, `IDCus`, `Amount`, `IDLoc`, `Date`, `from`, `to`) VALUES ('";
        String insertValues =IDCar + "','"+ IDCust +"','"+ amount +"','"+ IDLoc +"','"+ LocalDate.now() +"','"+ dateFrom +"','"+ dateTo +"')";
        String insertToOrders = insertFields + insertValues;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToOrders);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	public double getSelectedCarRate(int IDCar) {
		Statement statement;
		double amount=0;
		
		try {
			statement = connectDB.createStatement();
			String getSelectedCarRate ="select rate from cars where IDCar=+"+IDCar;
			ResultSet queryResultCarRate = statement.executeQuery(getSelectedCarRate);
			
			while (queryResultCarRate.next()) {  // loop
				amount = queryResultCarRate.getDouble("rate");
			       }	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return amount;
		
	}
	
	

	// 
	//
	//********************************************************** RegisterFormCustomer Controller **************************************************************************************
	//
	//
	
	public void setDataInCustomer(String Firstname, String Lastname, String Email, LocalDate Birthdate, String Street, String StreetNo , String City, String Postalcode, String State,String Country, String Mobilephone) {
		String insertFields ="INSERT INTO customers(`Firstname`, `Lastname`, `Email`, `Birthdate`, `Street`, `StreetNo`, `City`, `Postalcode`, `State`, `Country`, `Mobilephone`) VALUES ('";
        String insertValues =Firstname + "','"+ Lastname +"','"+ Email +"','"+ Birthdate +"','"+ Street +"','"+ StreetNo +"','"+ City +"','"+ Postalcode +"','"+ State +"','"+ Country +"','"+ Mobilephone +"')";
        String insertToCustomers = insertFields + insertValues;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToCustomers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	// 
	//
	//********************************************************** AddNewCarController Controller **************************************************************************************
	//
	//
	
	public int getHighestIDCar() {
		String getHighesIDCar = "SELECT IDCar FROM cars ORDER BY IDCar DESC LIMIT 0, 1";
		int highestIDCar = 0; 
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(getHighesIDCar);
	           
	           while (queryResult.next()){
	        	   highestIDCar = queryResult.getInt(1) + 1;  
	           }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return highestIDCar;
	}
	
	public void addNewCar(int highestIDCar, String textAreaCarDescription,  String txtfieldBrand, String txtfieldModel, int IDCat , String txtfieldRate , String txtfieldNoOfSeats , String txtfieldAvailability) {
		String insertFields ="INSERT INTO cars(`IDCar`,`car_description`, `brand`, `model`, `IDCat`, `rate`, `no_of_seats`, `Availability`) VALUES ('";
        String insertValues =highestIDCar + "','"+textAreaCarDescription + "','"+ txtfieldBrand +"','"+ txtfieldModel +"','"+ IDCat +"','"+ txtfieldRate +"','"+ txtfieldNoOfSeats +"','"+ txtfieldAvailability +"')";
        String insertToCars = insertFields + insertValues;
		try {
			 Statement statement = connectDB.createStatement();
			 //statement.executeUpdate(insertToCars);
			 System.out.println("worked");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		}
	}

