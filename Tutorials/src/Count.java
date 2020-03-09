class Count extends Thread {

    // number of increments per thread
    static int N = 1000;

    // shared data
    static volatile int counter = 0;
    
    // protocol variables
    // ...

    public void run() {
      int temp;
      for (int i = 0; i < N; i++) {
        // non-critical section

        // pre-protocol section

        // critical section
        temp = counter;
        counter = temp + 1;

        // post-protocol section
      }
    }

    public static void excute() {
        Count p = new Count();
        Count q = new Count();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
       
        if(counter!= 2000) {
        	System.out.println("hithere");
        	System.out.println("The final value of the counter is " + counter);
        }
        counter = 0;
    }
    public static void main(String[] args) {
    	for(int i = 0; i<100000 ;i++) {
    		excute();
    	}

    }
}
