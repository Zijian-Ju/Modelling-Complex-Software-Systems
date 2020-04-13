class UseCounter extends Thread {
  Counter c;

  public UseCounter(Counter c) {
    this.c = c;
  }

  public void run () {
    for (int i = 0; i < 100; i++) {
      c.increment();
    }
  }

  public static void main(String [] args) {
    Counter c = new Counter();
    UseCounter c1 = new UseCounter(c);
    UseCounter c2 = new UseCounter(c);
    c1.start();
    c2.start();
    try {
      c1.join();
      c2.join();
    }
    catch (InterruptedException e) {}
    System.out.println("final value: " + c.value);
  }
}
