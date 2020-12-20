package database;

import java.io.IOException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import menuOrders.createInvoice;

public class DataExchange {

	DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
	
	public DataExchange () {
		
	}
	//
	//********************************************************** MainMenu ***********************************************************************************
	//
	//
	
	
	public ArrayList<String> getCalendarData(int staffID, LocalDate date){
		ArrayList<String> calendarDataList = new ArrayList<String>();
		try {
			ResultSet queryAppointsments = connectDB.createStatement().executeQuery("select startTime,endTime,description from appointments where date='"+date+"' and IDStaff="+staffID);
			String dataAppointment;
			while (queryAppointsments.next()) {  // loop
				dataAppointment =String.valueOf(queryAppointsments.getInt("startTime"))+":00 - "+ String.valueOf(queryAppointsments.getInt("endtime"))+":00  : "+queryAppointsments.getString("description");;
				calendarDataList.add(dataAppointment);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return calendarDataList;
	}
	
	
	//
	//********************************************************** MainMenu --> ScheduleAppointmentController  *****************************************************
	//
	//
	
	public boolean checkAppointment(int IDStaff,LocalDate dateAppointment,int startTime,int endTime) {
		boolean appointmentAvailable = false;
		
		
		
		return appointmentAvailable;
	}
	
	
	public void sendAppointmentData(int IDStaff, LocalDate dateAppointment, int startTime, int endTime, String description) {
		String insertFields ="INSERT INTO appointments(`IDStaff`, `date`, `startTime`, `endTime`, `description`) VALUES ('";
        String insertValues =IDStaff + "','"+ dateAppointment +"','"+ startTime +"','"+ endTime +"','"+ description +"')";
        String insertToAppointments = insertFields + insertValues;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToAppointments);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	
	
	//
	//********************************************************** General ******************************************************************************************
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
			ResultSet rsCarUpdated = connectDB.createStatement().executeQuery("select IDCar,brand,model from cars where IDCat="+i+" and Availability=1");
			
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
        String setCarAvailability = "update cars set Availability=0 where IDCar="+IDCar;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToOrders);
			 statement.executeUpdate(setCarAvailability);
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
	
	public void createInvoice(double amount, int IDOrder, LocalDate date ,int IDCust, int IDCat, int IDCar, int IDLoc, LocalDate dateFrom, LocalDate dateTo) {

		//fil data into database
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
		
		//get data from database to create Word Invoice
		String [] invoiceData = new String [20];
		
		//Data Customer
				try {
					Statement statement = connectDB.createStatement();
					ResultSet queryResultCustomerData = statement.executeQuery("select Firstname,Lastname,Street,StreetNo,City,Postalcode,Country from customers where IDCus="+IDCust);
					invoiceData[0] = Integer.toString(IDCust);
					while (queryResultCustomerData.next()) {  // loop 
						invoiceData[1] = queryResultCustomerData.getString("Firstname");
						invoiceData[2] = queryResultCustomerData.getString("Lastname");
						invoiceData[3] = queryResultCustomerData.getString("Street");
						invoiceData[4] = queryResultCustomerData.getString("StreetNo");
						invoiceData[5] = queryResultCustomerData.getString("City");
						invoiceData[6] = Integer.toString(queryResultCustomerData.getInt("Postalcode"));
						invoiceData[7] = queryResultCustomerData.getString("Country");
					       }		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		//Data Location
				try {
					Statement statement = connectDB.createStatement();
					ResultSet queryResultLocationData = statement.executeQuery("select Street,StreetNo,City,Country from locations where IDLoc="+IDLoc);
					invoiceData[8] = Integer.toString(IDLoc);
					while (queryResultLocationData.next()) {  // loop 
						invoiceData[9] = queryResultLocationData.getString("Street");
						invoiceData[10] = queryResultLocationData.getString("StreetNo");
						invoiceData[11] = queryResultLocationData.getString("City");
						invoiceData[12] = queryResultLocationData.getString("Country");
					       }		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		//Data Category
				try {
					Statement statement = connectDB.createStatement();
					ResultSet queryResultCategoryData = statement.executeQuery("select label from category where IDCat="+IDCat);
					
					while (queryResultCategoryData.next()) {  // loop 
						invoiceData[13] = queryResultCategoryData.getString("Label");
					       }		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
		//Data Car
				try {
					Statement statement = connectDB.createStatement();
					ResultSet queryResultCarData = statement.executeQuery("select brand,model from cars where IDCar="+IDCar);
					invoiceData[14] = Integer.toString(IDCar);
					while (queryResultCarData.next()) {  // loop 
						invoiceData[15] = queryResultCarData.getString("brand");
						invoiceData[16] = queryResultCarData.getString("model");
					       }		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

		        invoiceData[17] = Double.toString(amount);
		        invoiceData[18] = dateFrom.format(formatter);
		        invoiceData[19] = dateTo.format(formatter);
		        
		        createInvoice invoice1 = new createInvoice ();
		        try {
					invoice1.createWord(invoiceData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void createBillData(int IDCust, int IDCat, int IDCar, int IDLoc, LocalDate dateFrom, LocalDate dateTo, double amount) {
		
		
		
	}
	
	public void setOrderOnComplete(int IDReservation) {
		int IDCar = 0;
		String updateOrderCompleted ="update orders set completed=1 where IDReservation="+IDReservation;
		String getIDCar = "select IDCar from orders where IDReservation="+IDReservation;
		
		try {
			 Statement statement = connectDB.createStatement();
			 
			 ResultSet queryIDCar = statement.executeQuery(getIDCar);
			 while (queryIDCar.next()) {  // loop
					IDCar = queryIDCar.getInt("IDCar");
			 }
			 statement.executeUpdate(updateOrderCompleted);	
			 statement.executeUpdate("update cars set Availability=1 where IDCar="+IDCar);	
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
	
	public boolean addNewCar(int highestIDCar, String textAreaCarDescription,  String txtfieldBrand, String txtfieldModel, int IDCat , String txtfieldRate , String txtfieldNoOfSeats ) {
		boolean dataSubmitted= false;
		String insertFields ="INSERT INTO cars(`IDCar`,`car_description`, `brand`, `model`, `IDCat`, `rate`, `no_of_seats`) VALUES ('";
        String insertValues =highestIDCar + "','"+textAreaCarDescription + "','"+ txtfieldBrand +"','"+ txtfieldModel +"','"+ IDCat +"','"+ txtfieldRate +"','"+ txtfieldNoOfSeats +"')";
        String insertToCars = insertFields + insertValues;
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToCars);
			 dataSubmitted=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataSubmitted=false;
		}   
		return dataSubmitted;
		}

	// 
	//
	//********************************************************** MaintenanceController Controller **************************************************************************************
	//
	//
	
	public void createMaintenanceContract(int IDCar, String company, String service, LocalDate startDate, LocalDate endDate, double total_amt) {
		String insertFields ="INSERT INTO maintenance_contracts(`IDCar`,`maintenanceCompany`, `service`, `date`, `periodOfTime`, `total_amt`) VALUES ('";
        String insertValues =IDCar + "','"+company + "','"+ service +"','"+ startDate +"','"+ endDate +"','"+ total_amt +"')";
        String insertToMaintenance_contracts = insertFields + insertValues;
        String updateAvailabilityCar = "update cars set Availability=0, inMaintenance=1 where IDCar="+IDCar;
        try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(insertToMaintenance_contracts);
			 statement.executeUpdate(updateAvailabilityCar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
	public List<String> getCarAndMaintenance(int IDMaintenance) {
		String getSelectedOrderData ="select t1.IDMaintenance, t1.date, t1.periodOfTime, t1.total_amt, t2.brand, t2.model from maintenance_contracts t1 left join cars t2 on t1.IDCar=t2.IDCar where IDMaintenance="+IDMaintenance;
		List<String> selectedCarAndMaintenanceList = new ArrayList<String>();
		try {
			Statement statement = connectDB.createStatement();
			
			ResultSet queryResultOrderData = statement.executeQuery(getSelectedOrderData);
			
			while (queryResultOrderData.next()) {  // loop
				selectedCarAndMaintenanceList.add(queryResultOrderData.getString("date"));
				selectedCarAndMaintenanceList.add(queryResultOrderData.getString("periodOfTime"));
				selectedCarAndMaintenanceList.add(queryResultOrderData.getString("total_amt"));
				selectedCarAndMaintenanceList.add(queryResultOrderData.getString("brand"));
				selectedCarAndMaintenanceList.add(queryResultOrderData.getString("model"));
			       }		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectedCarAndMaintenanceList;
	}
	
	public void setMainteneneOnComplete(int IDMaintenance) {
		int IDCar = 0;
		String updateOrderCompleted ="update maintenance_contracts set completed=1 where IDMaintenance="+IDMaintenance;
		String getIDCar = "select IDCar from maintenance_contracts where IDMaintenance="+IDMaintenance;
				
		try {
			 Statement statement = connectDB.createStatement();
			 statement.executeUpdate(updateOrderCompleted);
			 
			 ResultSet queryIDCar = statement.executeQuery(getIDCar);
			 while (queryIDCar.next()) {  // loop
					IDCar = queryIDCar.getInt("IDCar");
			 }	 
			 statement.executeUpdate("update cars set Availability=1, inMaintenance=0 where IDCar="+IDCar);	
		} catch (Exception e) {	
			e.printStackTrace();
		}   
	}

	
	// 
	//
	//********************************************************** FeedbackController Controller **************************************************************************************
	//
	//
	
	public boolean checkValidStaffData(int StaffID, String Password) {
		boolean StaffIDAndPasswordValid= false;
		String checkStaffIDAndPassword ="select * from staffaccounts where IDStaff="+StaffID + " and Password='"+Password+"'";
		
		Statement statement;
		try {
			statement = connectDB.createStatement();
			ResultSet queryStaffIDAndPassword = statement.executeQuery(checkStaffIDAndPassword);
			while (queryStaffIDAndPassword.next()) {  
				StaffIDAndPasswordValid= true;
		 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return StaffIDAndPasswordValid;
	}
	
	public boolean checkIfDataForCustomer(int IDReservation) {
		boolean dataExisting=false;
		String checkFeedbackDataExists ="select IDReservation from feedback where IDReservation="+IDReservation;
		
		Statement statement;
		try {
			statement = connectDB.createStatement();
			ResultSet queryDataExists = statement.executeQuery(checkFeedbackDataExists);
		if(queryDataExists.next() == true) {
			dataExisting= true;
		}else {
			dataExisting= false;
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return dataExisting;
	}
		
	public void sendFeedbackData(int IDReservation, String vehicleProcedure, String levelCustomerService, String expectations, String rentalProcedure, String overallImpression) {
		int IDCus = 0;
		String getIDCus="select IDCus from orders where IDReservation="+IDReservation;
		Statement statement ;
		try {
			statement = connectDB.createStatement();
			ResultSet queryResultIDCus = statement.executeQuery(getIDCus);
	           
	           while (queryResultIDCus.next()){
	        	   IDCus = queryResultIDCus.getInt("IDCus") ;  
	           }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String insertFields ="INSERT INTO feedback(`IDCus`,`IDReservation`, `vehicleProcedure`, `levelCustomerService`, `expectations`, `rentalProcedure`, `overallImpression`) VALUES ('";
        String insertValues = IDCus + "','"+ IDReservation + "','"+ vehicleProcedure +"','"+ levelCustomerService +"','"+ expectations +"','"+ rentalProcedure +"','"+ overallImpression +"')";
        String insertToFeedback = insertFields + insertValues;
        
        try {
			 statement = connectDB.createStatement();
			 statement.executeUpdate(insertToFeedback);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
}

