class UseMonitorAccount {
    public static void main(String [] args)
    {
        Account a = new MonitorAccount();
        Parent p = new Parent(a);
        Child c = new Child(a, "c");

        p.start();
        c.start();

        try {
            p.join();
            c.join();
        }
        catch (InterruptedException e) {}
    }
}

