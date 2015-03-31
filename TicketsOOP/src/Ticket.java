import java.util.Date;
public class Ticket {

	private int priority;
	private String reporter; //Stores person or department who reported issue
	private String description;
	private Date dateReported;
	private boolean resolved;
	
	//STATIC Counter - accessible to all Ticket objects.
	//If any Ticket object modifies this counter, all Ticket objects will have the modified value
	//Make it private - only Ticket objects should have access
	private static int staticTicketIDCounter = 1;
	//The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
	protected int ticketID;
	
	protected boolean getResolved(){
		return this.resolved;
	}
	
	protected void setResolvedTrue(){
		this.resolved = true;
	}
	
	protected void setResolvedFalse(){
		this.resolved = false;
	}
	
	public Ticket(String desc, int p, String rep, Date date) {
		this.description = desc;
		this.priority = p;
		this.reporter = rep; 
		this.dateReported = date;
		this.resolved = false;
		this.ticketID = staticTicketIDCounter; 
		staticTicketIDCounter++;
	}
	
	protected int getPriority() {
		return priority;
	}
	
	protected int getTicketID(){
		return this.ticketID;
	}

	public String toString(){
		return("ID= " + this.ticketID + " Issued: " + this.description + " Priority: " +
				this.priority + " Reported by: " 
				+ this.reporter + " Reported on: " + this.dateReported);
	}
	
	
}
