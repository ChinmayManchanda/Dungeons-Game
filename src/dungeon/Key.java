package unsw.dungeon;

public class Key extends Entity {
	
	int id;
	/**
	 * Constructor for key
	 * @param name
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Key(String name, int x, int y, int ID) {
        super(name, x, y);
        this.id = ID;
    }
	public int getId() {
		return id; 
	}
	public void setId(int id) {
		this.id = id; 
	}
	
}
