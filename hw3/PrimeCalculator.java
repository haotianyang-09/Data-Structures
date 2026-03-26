public class PrimeCalculator{
    private ArrayQueue<Integer> numbers=new ArrayQueue<>();
    private ArrayQueue<Integer> primes=new ArrayQueue<>();

    public PrimeCalculator(){
        this.numbers=numbers;
        this.primes=primes;
    }
    
    public void primesTo(int n){
        try {
            if(n<2){
                throw new IllegalStateException("Input must be a number greater than or equal to 2.");
            }

            else{
                for(int i=2;i<=n;i++){
                    numbers.enqueue(i);
                }
                
                while(!numbers.isEmpty()){
                    int primeNumber=numbers.dequeue();
                    primes.enqueue(primeNumber);
                    int numberSize=numbers.size();

                    for(int j=0;j<numberSize;j++){
                        int nonPrimeNumber=numbers.dequeue();
                        if(nonPrimeNumber%primeNumber!=0){
                            numbers.enqueue(nonPrimeNumber);
                        }
                    }
                }
            }
            

            System.out.println("Printing primes up to "+n+":");

            while(primes.size()!=1){
                System.out.print(primes.dequeue()+", ");
            }

            System.out.println(primes.dequeue()+".");
            System.out.println();
        }

        catch(IllegalStateException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}