abstract class VehicleRental {
	private Customer customer;
	private int noOfKms;
	private int journeyDays;
	
	public VehicleRental(Customer customer, int noOfKms) {
		this.customer = customer;
		this.noOfKms = noOfKms;
		this.journeyDays = 0;
	}
	
	public int getJourneyDays() {
		return this.journeyDays;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public int identifyJourneyDays() {
		this.journeyDays = 	this.noOfKms/300;
		return this.noOfKms % 300;
	}
	
	public abstract double calculateFinalAmount();	
}

class Customer {
	public static String[] memberCustIdArr = {"1001P", "1051R", "1072P", "2019R", "2913R", "2931P"};
	public static int[] memberBillAmountArr = {2050, 5345, 6896, 9100, 4500, 3234};
	
	private String custId;
	private String custName;
	
	public Customer(String custId, String custName) {
		this.custId = custId;
		this.custName = custName;
	}
	
	public String getCustId() {
		return this.custId;
	}
	
	public String getCustName() {
		return this.custName;
	}
	
	//To_Trainee
	public float calculateDiscount(float rentalAmount) {
		float discountAmount = 0.0f;
		for(int i = 0;i < Customer.memberCustIdArr.length;i++) {
			if(Customer.memberCustIdArr[i].equalsIgnoreCase(custId)) {
				this.upgradeCustomer(rentalAmount);
				if(getCustId().charAt(4) =='P') {
					discountAmount = rentalAmount *0.15f;
					break;
			       }
				else if(getCustId().charAt(4) == 'R') {
					discountAmount = rentalAmount * 0.10f;
					break;
				}
			
			
				
			}
			else{
			    discountAmount = -1.0f;
			}
		}//khatam yaha hora;
		
		return discountAmount;
	}
	public void upgradeCustomer(float rentalAmount) {
		if (rentalAmount >= 15000) {
			int index = 0;
			for (int ind=0; ind<Customer.memberCustIdArr.length; ind++) {
				if (Customer.memberCustIdArr[ind].equals(this.custId)) {
					index = ind;
					break;
				}
			}
			this.custId = this.custId.substring(0,4)+"P";
			Customer.memberCustIdArr[index] = this.custId;
		}
	}	
}

class CarRental extends VehicleRental {
	private String carType;
	
	public CarRental(Customer customer, int noOfKms, String carType) {
		super(customer, noOfKms);
		this.carType = carType;
	}
	
	//To_Trainee
	public double calculateFinalAmount() {
		double finalAmount = 0.0;
		int rentPerDay = CarDetails.identifyPerDayRent(carType);
		int excessKms = super.identifyJourneyDays();
		this.identifyJourneyDays();
	    if(rentPerDay == -1 || excessKms <0 || super.getJourneyDays() < 0) {
	    	finalAmount =  -1.0f;
	    	return finalAmount;
	    }
	    else  {
	    	float rentalAmount = super.getJourneyDays() * rentPerDay;
	    	int excessKmsAmount = 12 * excessKms;
	    	rentalAmount += excessKmsAmount;
	    	float discountAmount = super.getCustomer().calculateDiscount(rentalAmount);
	    	if(discountAmount == -1.0f) {
	    		finalAmount = -1.0;
	    		return finalAmount;
	    	}
	    	finalAmount = rentalAmount- discountAmount;
	    	int o = 0;
	    	for(int i = o;i < Customer.memberCustIdArr.length;i++) {
	    		if(Customer.memberCustIdArr[i].equals(super.getCustomer().getCustId())){
	    			o =i;
	    		}
	    	}
	    	Customer.memberBillAmountArr[o] += finalAmount;
	    }
		return finalAmount;
	   
		
	}
	
	
}
class CarDetails {
	private static String[] carTypesArr = {"Hatch-back","Sedan","SUV"};
	private static int[] perDayRentsArr = {3500,5000,6000};
	
	//To_Trainee
	public static int identifyPerDayRent(String carType) {
		int rentPerDay = 0;
		boolean z = false;
		for(int j = 0;j<carTypesArr.length;j++) {
			if(CarDetails.carTypesArr[j].equalsIgnoreCase(carType)) {
				rentPerDay = CarDetails.perDayRentsArr[j];
				z = true;
			}
			if(!z) {
				rentPerDay = -1;
			}
			
		}
		return rentPerDay;
		
	}
}

class Tester {
	public static void main(String[] args) {
		Customer custObj = new Customer("1051R","Wilch");
		
		CarRental carRentalObj = new CarRental(custObj,10,"SUV");
		for (int ind=0; ind<Customer.memberCustIdArr.length; ind++) {
			System.out.print(Customer.memberCustIdArr[ind]+":"+Customer.memberBillAmountArr[ind]+" ");
		}
		
		System.out.println();
		
		double finalAmount = carRentalObj.calculateFinalAmount();
		System.out.println("Final bill amount: "+finalAmount);
		for (int ind=0; ind<Customer.memberCustIdArr.length; ind++) {
			System.out.print(Customer.memberCustIdArr[ind]+":"+Customer.memberBillAmountArr[ind]+" ");
		}
	}
}