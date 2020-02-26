package unsw.dungeon;

public class Sword extends Entity {
	private int hits;
	/**
	 * The constructor for sword
	 * @param name
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Sword(String name, int x, int y) {
        super(name, x, y);
        hits = 5;
    }
	/**
	 * The setter to set the durability of the sword
	 * @param hits
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}
	/**
	 * Reduce the durability of sword by one
	 */
	public void reduceHits() {
		this.hits-=1;
	}
	/**
	 * Gets the durability of the sword
	 * @return hits
	 */
	public int getHits() {
		return this.hits; 
	}
	
}
