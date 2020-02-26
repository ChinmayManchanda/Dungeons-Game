package unsw.dungeon;

public class Door extends Entity implements Entrances, State{
	
	
	State openDoor;
	State closedDoor;
	
	State state = openDoor;
	
	int id; 
	/**
	 * Constructor for Door
	 * @param name
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Door(String name, int x, int y, int ID) {
        super(name, x, y);
   
        this.closedDoor = new ClosedDoor(this);
        this.openDoor = new OpenedDoor(this); 
        this.id = ID;
        if (name.equals("closedDoor")) {
        	this.state = closedDoor;
        }
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter for OpenDoor State
	 * @return openDoor
	 */
	public State getOpenDoor() {
		return openDoor;
	}
	/**
	 * Setter for OpenDoor State
	 * @param openDoor
	 */
	public void setOpenDoor(State openDoor) {
		this.openDoor = openDoor;
	}
	/**
	 * Getter for ClosedDoor State
	 * @return closedDoor
	 */
	public State getClosedDoor() {
		return closedDoor;
	}
	/**
	 * Setter for ClosedDoor State
	 * @param closedDoor
	 */
	public void setClosedDoor(State closedDoor) {
		this.closedDoor = closedDoor;
	}
	/**
	 * Getter for State
	 * @return state
	 */
	public State getState() {
		return state;
	}
	/**
	 * Setter for State
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public boolean enter(Dungeon dungeon, int x ,int y, int id) {
		return this.state.enter(dungeon, x, y, id);
	}

	@Override
	public boolean open(int id) {
		return state.open(id);	
	}
	@Override
	public void enter(Dungeon dungeon, int x, int y) throws Exception {
		return;
		
	}



}
