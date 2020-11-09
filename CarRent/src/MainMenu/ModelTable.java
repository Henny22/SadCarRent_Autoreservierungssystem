package MainMenu;

public class ModelTable {

	String IDCus, Firstname, Lastname, Email, Street;
	String IDCar, Brand, Model, Seats, Rate, Availability;
	public ModelTable(String IDCus, String Firstname, String Lastname, String Email, String Street) {
			this.IDCus = IDCus;
			this.Firstname = Firstname;
			this.Lastname = Lastname;
			this.Email = Email;
			this.Street = Street;
	}
	
	public ModelTable(String IDCar, String Brand, String Model, String Seats, String Rate, String Availability) {
		this.IDCar = IDCar;
		this.Brand = Brand;
		this.Model = Model;
		this.Seats = Seats;
		this.Rate = Rate;
		this.Availability = Availability;
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
	
	
	// ****************************************CarsController********************************************************************
	public String getIDCar () {
		return IDCar;
	}
	
	public void setIDCar(String IDCar) {
		this.IDCar =IDCar;
	}
	
	public String getBrand () {
		return Brand;
	}
	
	public void setBrand(String Brand) {
		this.Brand =Brand;
	}

	public String getModel () {
		return Model;
	}
	
	public void setModel(String Model) {
		this.Model =Model;
	}
	
	public String getSeats () {
		return Seats;
	}
	
	public void setSeats(String Seats) {
		this.Seats =Seats;
	}
	
	public String getRate () {
		return Rate;
	}
	
	public void setRate(String Rate) {
		this.Rate =Rate;
	}
	
	public String getAvailability () {
		return Availability;
	}
	
	public void setAvailability(String Availability) {
		this.Availability =Availability;
	}
	
	
}