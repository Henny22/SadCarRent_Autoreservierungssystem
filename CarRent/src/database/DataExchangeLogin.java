package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DataExchangeLogin {

	static DatabaseConnection connectNow = new DatabaseConnection();
   static Connection connectDB = connectNow.getConnection();
    
    public enum enumEmployeeInDatabase{
		foundNotInStaffAndFoundUserData,
		foundInStaffAndNotFoundInUserData,
		foundInStaffAndUserData,
		foundNotInStaffAndFoundNotInUserData
	}
    static enumEmployeeInDatabase EmployeeInDatabase = enumEmployeeInDatabase.foundNotInStaffAndFoundNotInUserData;
    static boolean isAdministrator = false;
    
    public static boolean getIsAdministrator() {
    	return isAdministrator;
    }
    //
	//********************************************************** LoginSystem Controller **************************************************************************************
	//
	//
	
	public boolean validateLogin(String Username, String Password) {
		String verifyLogin = "SELECT count(1) FROM staffaccounts WHERE Username ='" + Username +"' AND Password ='"+ Password+ "'";
		boolean validLogin= false;
        try{
           Statement statement = connectDB.createStatement(); 
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           
           while (queryResult.next()){
               if (queryResult.getInt(1)==1){

            	   validLogin =true;
               }else {
            	   validLogin=false;
               }
           }
           
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return validLogin;
	}
	
	public void getIsAdministrator(String Username, String Password) {
		
				String checkIsAdministrator = "select Administrator from staffaccounts where Username='"+Username+"' and Password='"+Password+"'";
				
				try{
			           Statement statement = connectDB.createStatement(); 
			           ResultSet queryResult = statement.executeQuery(checkIsAdministrator);
			           
			           while (queryResult.next()){
			               if (queryResult.getBoolean(1)==true){
			            	   isAdministrator =true;
			               }else {
			            	   isAdministrator=false;
			               }
			           }
			        }catch (Exception e){
			            e.printStackTrace();
			            e.getCause();
			        }
	}
	
	//
	//********************************************************** PasswordReset Controller **************************************************************************************
	//
	//
	
	public boolean checkDataCombination(int staffNumber, String username) {
		 String checkData = "SELECT IDStaff,Username from staffaccounts where IDStaff="+staffNumber+" && "+" Username='"+username+"'";
         boolean checkExistance= false;
      try {
          Statement statement = connectDB.createStatement();
          ResultSet queryResultCheckData = statement.executeQuery(checkData);
          checkExistance = queryResultCheckData.next();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return checkExistance;
	}

	public void changePassword(int staffNumber, String username, String newGeneratedPassword) {
		try {
		Statement statement = connectDB.createStatement();
 
        String setNewPassword  ="Update staffaccounts SET Password='"+newGeneratedPassword+"' WHERE IDStaff="+staffNumber+" && Username='"+username+"'";
            
        statement.executeUpdate(setNewPassword);
          
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	//********************************************************** Register Controller **************************************************************************************
	//
	//
	
	
	// int case :
	// case= 0 not found in Stuff & UserData
	// case= 1 found in Staff & not in UserData
	// case= 2 found in Staff & UserData
	public int checkEmployeeInDatabase(int staffNumber) {
		String checkStuffNumberInStaff = "SELECT IDStaff from staffdata where IDStaff="+staffNumber+"";
        String checkStuffNumberInUserData = "SELECT IDStaff from staffaccounts where IDStaff="+staffNumber+"";
        int dataCase=0;
        try{
            Statement statement = connectDB.createStatement(); 
            Statement statement2 = connectDB.createStatement(); 
            
            ResultSet queryResultNumberInStaff = statement.executeQuery(checkStuffNumberInStaff);
            ResultSet queryResultNumberInUserData = statement2.executeQuery(checkStuffNumberInUserData);
           
            
            boolean existNumberInStuff = queryResultNumberInStaff.next();
            boolean existNumberInUserData = queryResultNumberInUserData.next();
            if (existNumberInStuff== true && existNumberInUserData== false) {
            	dataCase = 1;
            }else if(existNumberInStuff== true && existNumberInUserData== true) {
            	dataCase = 2;
            } else {
            	dataCase = 0;
            }
            
          }catch (Exception e){
             e.printStackTrace();
             e.getCause();
         }
        return dataCase;
	}
	
	public int checkIFEmployeeAdministrator(int staffNumber) {
		String checkIFAdministrator ="SELECT Job from staffdata where IDStaff="+staffNumber+" && job='Administrator'";
		int jobint=0;
		try {
		Statement statement = connectDB.createStatement();  
		ResultSet queryResultIFAdministrator = statement.executeQuery(checkIFAdministrator);
                
         if(queryResultIFAdministrator.next() == true) {
        	 jobint= 1;
         }
         else {
        	 jobint = 0 ;
         }
         
		} catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
		return jobint;
	}
	
	public void registerUser(int staffNumber, String username, String password, int jobint) {
		
		String insertFields ="INSERT INTO staffaccounts( IDStaff, Username, Password,Administrator) VALUES ('";
        String insertValues =staffNumber + "','"+ username +"','"+ password +"','"+ jobint +"')";
        String insertToRegister = insertFields + insertValues;
		try {		
		Statement statement = connectDB.createStatement();       
        statement.executeUpdate(insertToRegister);
		}catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
	}
	
}
