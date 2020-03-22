
public class Test {

	public static void main(String[] args) throws InterruptedException {
        Agenda agendaNew = new Agenda("New Agenda");
        Agenda agendaComplete = new Agenda("Complete Agenda");
        
        Producer producer = new Producer(agendaNew);
        Consumer consumer = new Consumer(agendaComplete);
        
        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();
	}

}
