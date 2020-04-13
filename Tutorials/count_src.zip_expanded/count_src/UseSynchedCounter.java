class UseSynchedCounter extends Thread {
  SynchedCounter c;

  public UseSynchedCounter(SynchedCounter c) {
    this.c = c;
  }

  public void run () {
    for (int i = 0; i < 10; i++) {
      c.increment();
    }
  }

  public static void main(String [] args) {
    SynchedCounter c = new SynchedCounter();
    UseCounter c1 = new UseCounter(c);
    UseCounter c2 = new UseCounter(c);
    c1.start();
    c2.start();
    try {
      c1.join();
      c2.join();
    }
    catch (InterruptedException e) {}
    System.out.println(c.value);
  }
}
