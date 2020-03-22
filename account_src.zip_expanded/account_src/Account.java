class Account {
  int balance = 0;

  public synchronized void withdraw(int amount) {
    int temp = balance;
    try { Thread.sleep(1); }
    catch (InterruptedException e) {}
    balance = temp - amount;
  }

  public synchronized void deposit(int amount) {
    int temp = balance;
    try { Thread.sleep(1); }
    catch (InterruptedException e) {}
    balance = temp + amount;
  }
}
