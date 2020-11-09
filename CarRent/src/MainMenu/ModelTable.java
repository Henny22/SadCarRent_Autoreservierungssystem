package MainMenu;

public class ModelTable {

	String IDCus, Firstname, Lastname, Email, Street;
	
	public ModelTable(String IDCus, String Firstname, String Lastname, String Email, String Street) {
			this.IDCus = IDCus;
			this.Firstname = Firstname;
			this.Lastname = Lastname;
			this.Email = Email;
			this.Street = Street;
	}
	
	
	public String getIDCus () {
		return IDCus;
	}
	
	public void setIDCus(String IDCus) {
		this.IDCus = IDCus;
	}
	
	public String getFirstname () {
		return Firstname;
	}
	
	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
	}
	
	public String getLastname () {
		return Lastname;
	}
	
	public void setLastname(String Lastname) {
		this.Lastname =Lastname;
	}
	
	public String getEmail () {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email =Email;
	}
	
	public String getStreet () {
		return Street;
	}
	
	public void setStreet(String Street) {
		this.Street =Street;
	}
}
