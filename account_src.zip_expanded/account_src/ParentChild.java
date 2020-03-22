class Parent extends Thread {
  Account a;
  java.util.Random r = new java.util.Random();

  Parent(Account a) {
    this.a = a;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      a.deposit(500);
      System.out.print("deposit 500; ");
      System.out.println("balance = " + a.balance);
      int s = r.nextInt(1000);
      try {
        Thread.sleep(s);
      }
      catch (InterruptedException e) {}
    }
  }
}

class Child extends Thread {
  String l;
  Account a;

  Child(Account a, String l) {
    this.l = l;
    this.a = a;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      a.withdraw(100);
      System.out.print(l + ": withdraw 100; ");
      System.out.println("balance = " + a.balance);
    }
  }
}
