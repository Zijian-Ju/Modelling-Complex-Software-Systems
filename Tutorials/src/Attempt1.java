
public class Attempt1 extends Thread {
    static int N = 1000;
    int id;
    static volatile int [] flag = {0,0};
    // shared data
    static volatile int counter = 0;
    static volatile int turn = 0;
    // protocol variables
    public Attempt1(int id) {
    	this.id = id;
    }
    public void run() {
      int temp;
      for (int i = 0; i < N; i++) {
        // non-critical section
    	flag[id] = 1;
        // pre-protocol section
    	while(flag[1-id]!= 0) {
    		if(turn == 1-id) {
    			flag[id] = 0;
    			while (turn == 1-id);
    			flag[id] = 1;
    		}
    	}
        // critical section
        temp = counter;
        counter = temp + 1;
        // post-protocol section
        turn = 1 - id;
        flag[id] = 0;
      }
    }
    public static void main(String[] args) {
        int count = 0;
    	for(int i = 0; i<100 ;i++) {
            Attempt1 p = new Attempt1(0);
            Attempt1 q = new Attempt1(1);
            p.start();
            q.start();
            try { p.join(); q.join(); }
            catch (InterruptedException e) { }
            if(counter == 2000) {
            	count ++ ;
            }
            counter = 0;
    	}
    	System.out.println(count);
    }
}
