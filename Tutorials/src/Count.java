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

    public static int excute() {
        Count p = new Count();
        Count q = new Count();
        int temp = 0;
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
 
        temp = counter;
        counter = 0;
        return temp;
    }
    public static void main(String[] args) {
    	int count = 0;
    	for(int i = 0; i<100 ;i++) {
    		if (excute() == 2000) {count ++;
    		
    		}
    	}
    	System.out.println(count);

    }
}
