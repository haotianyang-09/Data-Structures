public abstract class Currency implements Exchangeable {
	protected String currencyName;
	protected double totalFunds;
	
	public Currency(String currencyName, double totalFunds) {
		this.currencyName=currencyName;
		this.totalFunds=totalFunds;
	}
	
	public abstract double toEarthDollars(double amount);
	public abstract double fromEarthDollars(double EarthDollars);
	
	public String getName() {
		return currencyName;
	}
	
	public double getAmount() {
		return totalFunds;
	}
	
	public void addFunds(double amount) {
		totalFunds+=amount;
	}
	
}
