
public interface Exchangeable {
	double MarsExchangeRate=1.3;
	double SaturnExchangeRate=0.87;
	double NeptuneExchangeRate=2.0;
	
	public void exchange(Currency other, double amount);
	
}
