import java.util.HashMap;

/**
 * Zijian Ju 1006948
 * Hall class to monitor the status of knights and the King Arthur
 * It is responsible for :
 * -- Whether King Arthur is in the hall
 * -- Whether the knights in the hall are seated
 * 
 * @author zijian Ju(1006948) Zijianj@student.unimelb.edu.au
 *
 */
public class Hall {
	//name of the hall
	private String name;
	
	//Boolean value to show whether the king is in the hall
	private boolean isKingHere = false;
	//Boolean value to show whether the meeting begins
	private boolean isMeeting = false;
	
	//The knights who are currently in the hall and whether seated at the round table
	private HashMap<Knight, Boolean> knights = new HashMap<>();
	
	//Agenda to record quests that are not acquired
	private Agenda newAgenda;
	//Agenda to record quests that have been completed
	private Agenda completeAgenda;
	
	//Create a hall with a name and agendas
	public Hall (String name, Agenda newAgenda, Agenda completeAgenda) {
		this.name = name;
		this.newAgenda = newAgenda;
		this.completeAgenda = completeAgenda;
	}
	
	//Record a knight entering the hall
	public synchronized void enter(Knight knight) {
		//When king is in the hall, prevent knight from coming in
		while(isKingHere) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		knights.put(knight, false);
		System.out.format("%s enters %s.\n", knight.toString(), name);
	}
	
	//Record King Arthur entering the hall
	public synchronized void enter(KingArthur kingArthur) {
		isKingHere = true;
		System.out.format("%s enters the %s.\n", kingArthur.toString(), name);
	}
	
	//Record a knight leaving the hall
	public synchronized void exit(Knight knight) {
		while(isKingHere) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		knights.remove(knight, false);
		//Assume all the knight who is going to exit the hall must have an uncompleted quese
		System.out.format("%s exits from %s.\n", knight.toString(), name);
		System.out.format("%s sets of to complete %s!\n", knight.toString(), knight.getQuest().toString());
	}
	
	//Record King Arthur entering the hall
	public synchronized void exit(KingArthur kingArthur) {
		isKingHere = false;
		System.out.format("%s exits the %s.\n", kingArthur.toString(), name);
		//Notify All the threads when the king leaves
		notifyAll();
	}
	
	
	//Check whether all the knights are seating at the RoundTable
	public synchronized boolean isAllSeated() {
		if(!knights.isEmpty()) {
			for(Knight k : knights.keySet()) {
				if(!knights.get(k)) return false;	
			}
		}
		//If no knights in the hall. consider it is true as no knight can come in after king's comming
		return true;
	}
	
	//Check whether all the knights are standing from the RoundTable
	public synchronized boolean isAllStanding() {
		if(!knights.isEmpty()) {
			for(Knight k : knights.keySet()) {
				if(knights.get(k)) return false;	
			}
		}
		return true;
	}
	
	//Let the knight sit at the round table
	public synchronized void sitDown(Knight knight) {
		knights.put(knight, true);
		System.out.format("%s sits at the Round Table.\n", knight.toString());
		//Notify the locked threads which is waiting the knight has been seated
		notifyAll();		
	}
	
	//Let the knight stand at the round table
	public synchronized void stand(Knight knight) {
		knights.put(knight, false);
		System.out.format("%s stands from the Round Table.\n", knight.toString());
		//Notify the locked threads which is waiting the knight stands up
		notifyAll();
	}
	
	//Public access to check whether there is meeting 
	public synchronized boolean isMeeting() {
		return isMeeting;
	}

	//Set the status of whether there is meeting in the hall
	public synchronized void startMeeting() {
		//the meeting cannot start until all knights are seated
		while(!isAllSeated()) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		this.isMeeting = true;
		System.out.println("Meeting begins!");
	}

	//End the meeting after all knight stand from table
	public synchronized void endMeeting() {
		//the meeting cannot start until all knights are seated
		while(!isAllStanding()) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		this.isMeeting = false;
		System.out.println("Meeting ends!");
	}

}
