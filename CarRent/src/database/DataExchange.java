package database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
		//********************************************************** General **************************************************************************************
		//
		//
	
	public List<String> getHeaderData() {
		List<String> dataHeaderList = new ArrayList<String>();
		try {
			
			
			ResultSet queryResultTotalOrders = connectDB.createStatement().executeQuery("SELECT COUNT(*) FROM orders");
			ResultSet queryResultTotalDelivered = connectDB.createStatement().executeQuery("select count(*) from orders where completed=1");
			ResultSet queryResultTotalPending = connectDB.createStatement().executeQuery("select count(*) from orders where completed=0");
			
			while (queryResultTotalOrders.next()) {  // loop
				dataHeaderList.add(queryResultTotalOrders.getString("COUNT(*)"));
			       }	
			while (queryResultTotalDelivered.next()) {  // loop
				dataHeaderList.add(queryResultTotalDelivered.getString("COUNT(*)"));
			       }
			while (queryResultTotalPending.next()) {  // loop
				dataHeaderList.add(queryResultTotalPending.getString("COUNT(*)"));
			       }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataHeaderList;
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

		String insertFields ="INSERT INTO orders(`IDCar`, `IDCus`, `Amount`, `IDLoc`, `Date`, `startDate`, `endDate`) VALUES ('";
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
	
	public int getHightestID() {
		int maxIDReservation=0;
		try {
			Statement statement = connectDB.createStatement();
			
			ResultSet queryResultMaxIDReservation = statement.executeQuery("select max(IDReservation) from orders");
			
			while (queryResultMaxIDReservation.next()) {  // loop
				maxIDReservation = queryResultMaxIDReservation.getInt("max(IDReservation)");
				
			       }		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxIDReservation;
	}
	
	public void createBill(double amount, int IDOrder, LocalDate date) {

		String insertFields ="INSERT INTO bills(`Total_amt`, `ID_order`, `BillDate`) VALUES ('";
        String insertValues =amount + "','"+ IDOrder +"','"+ date +"')";
        String insertToBils = insertFields + insertValues;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToBils);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	public void setOrderOnComplete(int IDReservation) {
		String updateOrderCompleted ="update orders set completed=1 where IDReservation="+IDReservation;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(updateOrderCompleted);	
		} catch (Exception e) {	
			e.printStackTrace();
		}   
	}
	
	public List<String> getOrderData(int IDReservation) {
		String getSelectedOrderData ="select t1.IDReservation, t2.Lastname, t3.Brand, t4.City,t1.Amount,t1.IDCar,t1.IDCus,t1.IDLoc from orders t1 left join customers t2 on t1.IDCus = t2.IDCus left join cars t3 on t1.IDCar = t3.IDCar left join locations t4 on t1.IDLoc = t4.IDLoc where IDReservation="+IDReservation;
		List<String> selectedOrderDataList = new ArrayList<String>();
		try {
			Statement statement = connectDB.createStatement();
			
			ResultSet queryResultOrderData = statement.executeQuery(getSelectedOrderData);
			
			while (queryResultOrderData.next()) {  // loop
				selectedOrderDataList.add(queryResultOrderData.getString("Lastname"));
				selectedOrderDataList.add(queryResultOrderData.getString("Brand"));
				selectedOrderDataList.add(queryResultOrderData.getString("City"));
				selectedOrderDataList.add(queryResultOrderData.getString("Amount"));
			       }		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectedOrderDataList;
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
			 statement.executeUpdate(insertToCars);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		}


	
	}

