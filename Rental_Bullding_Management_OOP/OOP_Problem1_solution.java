import java.util.Arrays;

class BuildingForRent {
	private int buildingDimensions;
	private float advanceAmount;
	
	public BuildingForRent(int buildingDimensions){
		this.buildingDimensions=buildingDimensions;
	}
	
	public float getAdvanceAmount(){
		return this.advanceAmount;
	}
    
    public void setAdvanceAmount(float advanceAmount){
    	this.advanceAmount = advanceAmount;
    }
        
    public int getBuildingDimensions(){
    	return this.buildingDimensions;
    }
    
    public void calculateAdvanceAmount(){
        float advanceAmount = -1.0f;
        if(this.buildingDimensions>0){
            advanceAmount=this.buildingDimensions*10;
        }
        this.advanceAmount=advanceAmount;
  }

	@Override
	public String toString() {
		return "BuildingForRent (buildingDimensions=" + this.buildingDimensions
				+ ")";
	}
   
}

class HouseForRent extends BuildingForRent{
	
	private static String[][] facilitiesDetailsArr = {{"Security","Parking","Amusement-Park"},{"2000","3400","2900"}};
    private String[] facilities;
	private int noOfBedrooms;
	
    public HouseForRent(int buildingDimensions,int noOfBedrooms,String[] facilities){
    	super(buildingDimensions); 
    	this.noOfBedrooms=noOfBedrooms;
        this.facilities = facilities;
    }
          
    public int getNoOfBedrooms(){
    	return this.noOfBedrooms;
    }
    
    public String[] getFacilities(){
    	return this.facilities;
    }
    
    //To_Trainee
    public Boolean validateNoOfBedrooms(){
    	if(noOfBedrooms>0 && noOfBedrooms<6) {
    		return true;
    	}
		return false;
    }
        

    //To_Trainee
    @Override
    public void calculateAdvanceAmount(){
       float totalfacilitycharge = 0.0f;
    	super.calculateAdvanceAmount();
    	float basicAmount = this.getAdvanceAmount();
    	this.validateNoOfBedrooms();
    	if(basicAmount == -1.0||this.validateNoOfBedrooms()==false||this.facilities==null) {
    		this.setAdvanceAmount(-1.0f);
    	}
    	else {
    		for(int i = 0;i<=facilities.length-1;i++) {
    			for(int j = 0;j<=facilitiesDetailsArr[0].length-1;j++) {
    				if(facilities[i].equalsIgnoreCase(facilitiesDetailsArr[0][j])) {
    					totalfacilitycharge += Integer.parseInt(facilitiesDetailsArr[1][j]);
    				}
    			}
    		}
    		if(totalfacilitycharge!=0.0f) {
    			int bedroomcharges = noOfBedrooms * 20000;
    			this.setAdvanceAmount(bedroomcharges + totalfacilitycharge + basicAmount);
    		}
    		else {
    			this.setAdvanceAmount(-1.0f);
    		}
    	}
    		
    }


	@Override
	public String toString() {
		return "HouseForRent (BuildingForRent (buildingDimensions=" + this.getBuildingDimensions()
				+ ")facilities=" + Arrays.toString(this.facilities)
				+ ", noOfBedrooms=" + this.noOfBedrooms + ")";
	}	
}

class ShopForRent extends BuildingForRent{
	char shopType;
	
	public ShopForRent(int buildingDimensions,char shopType){
		super(buildingDimensions);
		this.shopType=shopType;
	}
	
	//To_Trainee
    public int identifyShopRent(){
    	int shopRent = 0;

    	if(shopType == 'A'||shopType=='a') {
    		shopRent = 45000;
    	}
    	else if(shopType == 'B'||shopType == 'b') {
    		shopRent = 30000;
    	}
    	else if (shopType == 'C'||shopType == 'c') {
    		shopRent = 25000;
    	}
    	else {
    		shopRent = -1;
    	}

    	return shopRent;

    }
        
    //To_Trainee  
    @Override
    public void calculateAdvanceAmount(){
    	
    	super.calculateAdvanceAmount();
    	float basicAmount = super.getAdvanceAmount();
    	int shopRent = this.identifyShopRent();
    	if(basicAmount == -1.0f||shopRent == -1) {
    		this.setAdvanceAmount(-1.0f);
    	}
    	else {
    		this.setAdvanceAmount(shopRent + basicAmount);
    	}
    	
    }

	@Override
	public String toString() {
		return "ShopForRent (BuildingForRent (buildingDimensions=" + this.getBuildingDimensions()
				+ ")shopType=" + this.shopType + ")";
	}	   
}

class Tester{
	public static void main(String[] args){
		String[] facilities = {"PArking","SecUrity"};
		HouseForRent obj1 = new HouseForRent(2000,5,facilities);
		obj1.calculateAdvanceAmount();
		System.out.println("House Rent Amount:" + obj1.getAdvanceAmount());
					
		ShopForRent obj2 = new ShopForRent(10000,'A');
		obj2.calculateAdvanceAmount();
		System.out.println("Shop Rent Amount:" +obj2.getAdvanceAmount());
	}
}