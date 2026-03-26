import java.text.DecimalFormat;

public class Neptune extends Currency implements Exchangeable{

	public Neptune(double totalFunds) {
		super("NeptuneNuggets" , totalFunds);
	}
	
	DecimalFormat df = new DecimalFormat("0.00");//we can only print 2 digits after the decimal points
	
	public void exchange(Currency other, double amount) {
		if(!(other instanceof Currency)) {
			System.out.println("Invalid transaction!");
			return;
		}
		if(totalFunds-amount<5) {//When the exchange funds is less than total funds, but the exchange fund and exchange fee in total is more than the current fund,the transaction still cannot be made
			System.out.println("Uh oh - Neptune only has an available balance of "+totalFunds+", which is less than "+df.format(amount+5)+"!");
			System.out.println();
			return;
		}
		System.out.println("Converting from NeptuneNuggets to "+other.getName()+" and initiating transfer…");
		double EarthDollars=toEarthDollars(amount);
		double otherAmounts=other.fromEarthDollars(EarthDollars);
		other.addFunds(otherAmounts);
		System.out.println(df.format(amount)+" NeptuneNuggets = "+df.format(EarthDollars)+" EarthDollars = "+df.format(otherAmounts)+" "+other.getName());
		totalFunds=totalFunds-amount-5;
		System.out.println("Neptune exchange fee is 5.00 NeptuneNuggets");
		System.out.println("Neptune has a total of "+df.format(totalFunds)+" NeptuneNuggets");
		System.out.println(other.getClass().getName()+" has a total of "+df.format(other.getAmount())+" "+other.getName());
		System.out.println();
	}



	public double toEarthDollars(double amount) {
		return amount/NeptuneExchangeRate;
	}

	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars*NeptuneExchangeRate;
	}
	
}
