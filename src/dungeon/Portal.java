package unsw.dungeon;

import java.util.List;

public class Portal extends Entity implements Entrances{
	int id;
	/**
	 * Constructor for portal
	 * @param name
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Portal(String name, int x, int y, int ID) {
        super(name, x, y);
        this.id = ID;
        
    }

	public int getId() { 
		return id;
	}

	public void setId(int id) { 
		this.id = id;
	}

	@Override
	public void enter(Dungeon dungeon, int x, int y) {
		List<Entity> entities = dungeon.getEntities();
		if (x == this.getX() && y == this.getY()) {
			for (Entity nextEntity: entities) {
				if (nextEntity == null) { continue; }
				if (nextEntity.getName().equals("portal") == true) {
					if (this.getX() != nextEntity.getX() && this.getY() != nextEntity.getY() && ((Portal) nextEntity).getId() == this.getId()){
						Player player = dungeon.getPlayer();
						player.x().set(nextEntity.getX()); 
						player.y().set(nextEntity.getY());   
						System.out.println(nextEntity.getY());
						return;
					} else {
						continue;
					}
				}
			}
		}	
	}
	
}
