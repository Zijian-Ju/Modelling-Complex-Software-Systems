class Counter {
  int value = 0;

  void increment() {
    int temp = value;
    try { Thread.sleep(0); } 
    catch (Exception e) {}
    value = temp + 1;
  }
}
