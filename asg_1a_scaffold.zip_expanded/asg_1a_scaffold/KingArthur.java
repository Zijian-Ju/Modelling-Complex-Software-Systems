
/**
 * Zijian Ju 1006948
 * KingArthur class which is responsible for hosting the Round table meeting 
 * and distributing quests to knights
 * 
 * @author zijianj@student.unimelb.edu.au
 *
 */
public class KingArthur extends Thread{
	//the hall where King Arthur present the meeting
	private Hall hall;
	
	//Create King Arthur within a givn hall
	public KingArthur(Hall hall) {
		this.hall = hall;
	}
	
	//repeatly entering the hall, hosting meeting and leaving the hall
	public void run() {
		while (!isInterrupted()) {
			try {
				sleep(Params.getKingWaitingTime());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hall.enter(this);
			hall.startMeeting();
			hall.endMeeting();
			hall.exit(this);
		}
	}
	
	//Print the king's name
	public String toString() {
		return "King Arthur";
	}
	
	
	

}
