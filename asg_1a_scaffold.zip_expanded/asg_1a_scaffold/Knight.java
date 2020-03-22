
/**
 * Knight class which can participate the Round Table meeting in the Great Hall and complete quests
 * 
 * @author zijian Ju 1006948 Zijianj@student.unimelb.edu.au
 *
 */
public class Knight extends Thread{
	//the id of the knight
	private int id;
	//the agenda for quests yet to be distributed
	private Agenda newAgenda;
	//the agenda for quests which have been completed
	private Agenda completeAgenda;
	//The great hall where knight can join the meeting, acquire and release quests
	private Hall hall;
	//the quest that the knight is currently working on
	private Quest quest = null;
	
	//create a knight by givn argument
	public Knight(int id, Agenda newAgenda, Agenda completeAgenda, Hall hall) {
		this.id = id;
		this.newAgenda = newAgenda;
		this.completeAgenda = completeAgenda;
		this.hall = hall;
	}
	
	//Knight will recursively acquire quest, complete quest, return to the hall and join the meeting.
	public void run() {
		while (!isInterrupted()) {
			//try to enter the hall 
            hall.enter(this);
			discuss();
			hall.sitDown(this);
			attendMeeting();
			hall.stand(this);
			//Assume the knight will leave the hall until finishing discussing
			discuss();
			hall.exit(this);
			completeQuest();
        }
	}
	

    //Get the current quest of the knight
	public Quest getQuest() {
		return this.quest;
	}
	
	//Discuss the knight's adventure for a while
	public void discuss() {
		try {
			sleep(Params.getMinglingTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Print a knight with id
	public String toString() {
		return String.format("Knight %d", id);
	}
	
	//Allow knight to complete assigned quest
	public void completeQuest() {
		try {
			quest.completed = true;
			sleep(Params.getQuestingTime());
			System.out.format("%s completes %s!\n", this.toString(), quest.toString());			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Allow knight to attend meeting and achieve release, acquire quests responsibilities
	public void attendMeeting() {
		while(!hall.isMeeting());
		//Pause the Thread when the meeting does not begin
		if(quest!= null && quest.completed) {
			completeAgenda.release(this);
		}
		newAgenda.assignQuest(this);
	}
	
	//Set the quest of the knight
	public void setQuest(Quest quest) {
		this.quest = quest;
	}

}
