package unsw.dungeon;

public class Exit extends Entity implements Entrances{
	/**
	 * Constructor for exit portal
	 * @param name
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Exit(String name, int x, int y) {
        super(name, x, y);
        
    }

	@Override
	public void enter(Dungeon dungeon, int x, int y) throws Exception {
		if (x == this.getX() && y == this.getY()) {
			dungeon.getPlayer().round_end("");
		} 
		
	}

}
