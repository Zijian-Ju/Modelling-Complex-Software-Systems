class UseAccount {
    public static void main(String [] args)
    {
        Account a = new Account();
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

