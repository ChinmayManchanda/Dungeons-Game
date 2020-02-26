package unsw.dungeon;

import java.util.List;

public class Person extends Entity {
	private Dungeon dungeon;
    
	/** Constructor for Enemy.
	 * @param dungeon
	 * @param name
	 * @param x
	 * @param y
	 */
	public Person(Dungeon dungeon,String name, int x, int y) {
        super(name, x, y);
        this.dungeon = dungeon;
        
    }
	/** Getter method for dungeon.
	 * @return dungeon
	 */ 
	public Dungeon getDungeon() {
		return dungeon;
	}

	/** Setter method for dungeon.
	 * @param Dungeon
	 */ 
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	/** Checks for walls in the direction person will move to.
	 * @param String type
	 * @return boolean value
	 * @throws Exception 
	 */ 
	public boolean check_for_wall(String type) throws Exception {
    	List<Entity> entity = dungeon.getEntities();
    	check_round_over();
    	for (Entity p: entity) {
    		if (p == null) continue;
    		if (p.getName().equals("wall") == true) {
    			switch(type) {
    				case "up":
		    			if (p.getY() == this.getY() - 1 && p.getX() == this.getX()) {
		    				return false;
		    			}
		    			break;
    				case "down":
	    				if (p.getY() == this.getY() + 1 && p.getX() == this.getX()) {
	    					return false;
	    				}
	    				break;
    				case "right":
    					if (p.getX() == this.getX() + 1 && p.getY() == this.getY()) {
    		    			return false;
    		    		}
    					break;
    				case "left":
    					if (p.getX() == this.getX() - 1 && p.getY() == this.getY()) {
    		    			return false;
    		    		}
    					break;
    			}
    		}
    	}
		return true;
     }
	 /** Checks for boulders in the direction person will move to.
	 * @param String type
	 * @return boolean value
	 */
	 public boolean check_for_boulders (String type) {
		List<Entity> entity1 = dungeon.getEntities();
		for (Entity p: entity1) {
    		if (p == null) continue;
    		if (p.getName().equals("boulder") == true) {
    			for (Entity j: entity1) {
    				if (j == null) continue;
    				if (j.getName().equals("wall") || j.getName().equals("boulder")) {
    					switch(type) {
	    					case "up":
	    						if (j.getY() == p.getY() - 1 && j.getX() == p.getX() && p.getY() == this.getY() - 1 && p.getX() == this.getX() ) {
	    							return false;
	    						}
	    						break;
	    					case "down":
	    						if (j.getY() == p.getY() + 1 && j.getX() == p.getX() && p.getY() == this.getY() + 1 && p.getX() == this.getX()) {
	    							return false;
	    						}
	    						break;
	    					case "right":
	    						if (j.getX() == p.getX() + 1 && j.getY() == p.getY() && p.getX() == this.getX() + 1 && p.getY() == this.getY()) {
	    							return false;
	    						}
	    						break;
	    					case "left":
	    						if (j.getX() == p.getX() - 1 && j.getY() == p.getY() && p.getX() == this.getX() - 1 && p.getY() == this.getY()) {
	    							return false;
	    						}
	    						break;
    						default:
    							break;
    					}
    				}
    			}
			}
    	}
    	return true;
	}
	 
	 /** Checks for closed door in the direction person will move to.
		 * @param String type
		 * @return boolean value
	 * @throws Exception 
	 */
	 /*
	public boolean check_for_closed_door(String type) throws Exception {
    	List<Entity> entity = dungeon.getEntities();
    	check_round_over();
    	for (Entity p: entity) {
    		if (p == null) continue;
    		if (p.getName().equals("closedDoor") == true) {
    			switch(type) {
    				case "up":
		    			if (p.getY() == this.getY() - 1 && p.getX() == this.getX()) {
		    				return false;
		    			}
		    			break;
    				case "down":
	    				if (p.getY() == this.getY() + 1 && p.getX() == this.getX()) {
	    					return false;
	    				}
	    				break;
    				case "right":
    					if (p.getX() == this.getX() + 1 && p.getY() == this.getY()) {
    		    			return false;
    		    		}
    					break;
    				case "left":
    					if (p.getX() == this.getX() - 1 && p.getY() == this.getY()) {
    		    			return false;
    		    		}
    					break;
				}
    		}
    	}
		return true;
     }
     */
	 
	/** Checks for walls and boulders in the direction person will move to.
	 * @param String type
	 * @return boolean value
	 * @throws Exception 
	 * @throws Throwable 
	 */
	public boolean ifMove(String type) throws Exception, Throwable {
		if (check_for_wall(type) == false) {
    		return false;
    	} else if (check_for_boulders(type) == false) {
    		return false;
		} else {
    		return true; 
    	}
    }
	
	/** Checks if person has reached an exit and if the round is over or not.
	 * @throws Exception 
	 */
	public void check_round_over() throws Exception {
    	List<Entity> entity = dungeon.getEntities();
    	for (Entity p: entity) {
    		if (p == null) continue;
    		if (p.getName().equals("Exit") == true) {
    			Entrances exit = (Entrances) p;
    			exit.enter(dungeon, this.getX(), this.getY());
    		}
    	}
		
    }
    
}
