package unsw.dungeon;

public class OpenedDoor implements State, Entrances{

	Door door;
	/**
	 * Constructor for OpenedDoor
	 * @param door
	 */
	public OpenedDoor(Door door) {
		this.door = door; 
	}


	@Override
	public void enter(Dungeon dungeon, int x, int y) {
		return;	
	}

	@Override
	public boolean open(int id) {
		return true;
		
	}

	@Override
	public boolean enter(Dungeon dungeon, int x, int y, int id) {
		return true;
		
	}


}
