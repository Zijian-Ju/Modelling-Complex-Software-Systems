import java.util.LinkedList;
import java.util.Queue;

/**
 * The agenda class for handling quests in meeting
 * 
 * @author Zijian Ju zijianj@student.unimelb.edu.au
 *
 */
public class Agenda {
	//The capacity of new agenda for holding quest.
	// Assume there is only one request on agenda at a time
	public static int MAX_CAPACITY = 1;
	
	//List of quests on Agenda
	private Queue<Quest> quests;
	//Name of the agenda
	private String name;
	
	//Create an agenda with given name and an empty list of quests
	public Agenda(String name) {
		this.name = name;
		quests = new LinkedList<>();
	}
	
	//Add new quest on the agenda
	public synchronized void addNew(Quest newQuest) {
		while(quests.size() >= MAX_CAPACITY) {
			try {
				wait();
			}
			catch(InterruptedException e) {}		
		}
		quests.add(newQuest);
		System.out.format("%s added to %s.\n", newQuest.toString(), name);
		//notify all threads when new quest is added into agenda
		notifyAll();
	}
	
	//remove the first completed quest on list.
	public synchronized void removeComplete() {
		while(quests.isEmpty()) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		System.out.format("%s removed from %s.\n", quests.peek().toString(), name);
		quests.remove();
		//notify the thread to continue adding quest
		notifyAll();
	}
	
	//Assign a quest to a given Knight
	public synchronized void assignQuest(Knight knight) {
		//Pause related thread(King Arthur) when there is no quests on agenda
		while(quests.isEmpty()) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		Quest temp = quests.remove();
		knight.setQuest(temp);
		System.out.format("%s acquires %s.\n", knight.toString(), temp.toString());
		notifyAll();
	}
	
	//Release a quest, only one knight can release his quest for a time
	public synchronized void release(Knight knight) {
		quests.add(knight.getQuest());
		System.out.format("%s releases %s.\n", knight.toString(), knight.getQuest().toString());
		notifyAll();
	}
}
