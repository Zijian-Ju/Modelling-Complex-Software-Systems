class SynchedCounter extends Counter {
  synchronized void increment() {
    int temp = value;
    try { Thread.sleep(1); }
    catch (InterruptedException e) {}
    value = temp + 1;
  }
}
