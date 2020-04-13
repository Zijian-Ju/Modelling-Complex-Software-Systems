class UseMonitorAccount_2C {
    public static void main(String [] args)
    {
        Account a = new MonitorAccount();
        Parent p = new Parent(a);
        Child c1 = new Child(a, "c1");
        Child c2 = new Child(a, "c2");

        p.start();
        c1.start();
	    c2.start();

        try {
            p.join();
            c1.join();
	        c2.join();
        }
        catch (InterruptedException e) {}
    }
}

