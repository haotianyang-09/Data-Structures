import java.text.DecimalFormat;

public class Mars extends Currency implements Exchangeable{
	
	public Mars(double totalFunds) {
	    super("MarsMoney", totalFunds);
	  }

	DecimalFormat df = new DecimalFormat("0.00");//we can only print 2 digits after the decimal points
	
	public double toEarthDollars(double amount) {
		return amount/MarsExchangeRate;
	}

	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars*MarsExchangeRate;
	}

	public void exchange(Currency other, double amount) {
		if(!(other instanceof Currency)) {
			System.out.println("Invalid transaction!");
			return;
		}
		if(totalFunds-amount*1.1<0) { //When the exchange funds is less than total funds, but the exchange fund and exchange fee in total is more than the current fund,the transaction still cannot be made
			System.out.println("Uh oh - Mars only has an available balance of "+df.format(totalFunds)+", which is less than "+df.format(amount*1.1)+"!");
			System.out.println();
			return;
		}
		System.out.println("Converting from MarsMoney to "+other.getName()+" and initiating transfer…");
		double EarthDollars=(toEarthDollars(amount));
		double otherAmounts=other.fromEarthDollars(EarthDollars);
		other.addFunds(otherAmounts);
		System.out.println(df.format(amount)+" MarsMoney = "+df.format(EarthDollars)+" EarthDollars = "+df.format(otherAmounts)+" "+other.getName());
		double exchangeFee=amount*0.1;
		totalFunds-=amount*1.1;
		System.out.println("Mars exchange fee is "+df.format(exchangeFee)+" MarsMoney");
		System.out.println("Mars has a total of "+df.format(totalFunds)+" MarsMoney");
		System.out.println(other.getClass().getName()+" has a total of "+df.format(other.getAmount())+" "+other.getName());
		System.out.println();
	}

}
