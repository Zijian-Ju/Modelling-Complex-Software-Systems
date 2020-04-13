class MonitorAccount extends Account {
  public synchronized void withdraw(int amount) {
    while (balance < amount) {
      try {
//	System.out.println("waiting...");
        wait(); 
      }
      catch (InterruptedException e) {}
    }
    super.withdraw(amount);
    //notify();
  }
  public synchronized void deposit(int amount) {
    super.deposit(amount);
    notifyAll();
  }
}
