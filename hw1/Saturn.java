import java.text.DecimalFormat;

public class Saturn extends Currency implements Exchangeable{

	public Saturn(double totalFunds) {
		super("SaturnSilver", totalFunds);
	}
	
	DecimalFormat df = new DecimalFormat("0.00");//we can only print 2 digits after the decimal points
	
	public void exchange(Currency other, double amount) {
		if(!(other instanceof Currency)) {
			System.out.println("Invalid transaction!");
			return;
		}
		if(totalFunds-amount<0) {
			System.out.println("Uh oh - Saturn only has an available balance of "+df.format(totalFunds)+", which is less than "+df.format(amount)+"!");
			System.out.println();
			return;
		}
		System.out.println("Converting from SaturnSilver to "+other.getName()+" and initiating transfer…");
		double EarthDollars=toEarthDollars(amount);
		double otherAmounts=other.fromEarthDollars(EarthDollars);
		other.addFunds(otherAmounts);
		System.out.println(df.format(amount)+" SaturnSilver = "+df.format(EarthDollars)+" EarthDollars = "+df.format(otherAmounts)+" "+other.getName());
		totalFunds-=amount;
		System.out.println("Saturn exchange fee is 0.00 SaturnSilver");
		System.out.println("Saturn has a total of "+df.format(totalFunds)+" SaturnSilver");
		System.out.println(other.getClass().getName()+" has a total of "+df.format(other.getAmount())+" "+other.getName());
		System.out.println();
	}


	public double toEarthDollars(double amount) {
		return amount/SaturnExchangeRate;
	}

	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars*SaturnExchangeRate;
	}

}
