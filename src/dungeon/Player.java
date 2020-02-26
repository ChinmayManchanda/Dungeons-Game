package unsw.dungeon;
import java.util.List;
import javafx.stage.Stage;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Person {


    private boolean invincible = false;
    private Inventory inventory;
    int moves_counter = 0;
    private int score = 0;
	
	/**
     * Create a player positioned in square (x,y)
     * @param x 
     * @param y 
     */
    public Player(Dungeon dungeon,String name, int x, int y) {
        super(dungeon,name, x, y);
        this.inventory = new Inventory();
    }
    /**
     * Checks if the switches are activated by the boulder
     * @return boolean
     */
	public boolean check_if_switches_activated() {
		boolean keep = false;
		int switches = 0;
		for (Entity e: getDungeon().getEntities()) {
			if (e.getName().equals("switch")) {
				Boulder boulder = new Boulder("boulder",e.getX(),e.getY());
				if (getDungeon().getEntities().contains(boulder)) {
					keep = true;
				} else {
					return false;
				}
				switches +=1;
			}
		}
		System.out.println(switches);
		return keep;
	}
	
	public void removeEntity(Entity item) {
		for (Entity i: getDungeon().getEntities()) {
			if (i.equals(item)) {
				i.x().set(getDungeon().getWidth() + 2);
				i.y().set(getDungeon().getWidth() + 2);
				getDungeon().removeEntity(i);
				break;
			}
		}
		
	}
	/**
	 * Uses the sword towards the direction given and removes the enemy if it hits
	 * @param direction 
	 */
	public void useSword(String direction) {
		if (this.inventory.get_sword() != null && this.inventory.get_sword().getHits() > 0) {
			List<Entity> entity = getDungeon().getEntities();
			this.inventory.get_sword().reduceHits();
			if (this.inventory.get_sword().getHits() == 0) {
				this.inventory.set_sword(null);
			}
			switch (direction) {
				case "up":
					Entity enemy1 = new Entity("enemy", this.getX(), this.getY() - 1);
					if (entity.contains(enemy1)) {
						removeEntity(enemy1);
						this.score += 100;
					}
					break;
				case "down":
					Entity enemy2 = new Entity("enemy", this.getX(), this.getY() + 1);
					if (entity.contains(enemy2)) {
						removeEntity(enemy2);
						this.score += 100;
					}
					break;
				case "left":
					Entity enemy3 = new Entity("enemy", this.getX() - 1, this.getY());
					if (entity.contains(enemy3)) {
						removeEntity(enemy3);
						this.score += 100;
					}
					break;
				case "right":
					Entity enemy4 = new Entity("enemy", this.getX() + 1, this.getY());
					if (entity.contains(enemy4)) {
						removeEntity(enemy4);
						this.score += 100;
					}
					break;
			}
		}
	}
	/**
	 * Check if avatar is allowed to move in the direction player pressed
	 * @throws Exception 
	 */
	@Override
	public boolean ifMove(String type) throws Exception {
    	
		if (check_for_wall(type) == false) {
    		return false;
    	} else if (check_for_boulders(type) == false) {
    		return false;
    	} else {
    		if (this.invincible == true)  {
    			this.moves_counter+=1;
    			if (this.moves_counter == 15) {
    				this.moves_counter = 0; 
    				this.invincible = false;
    			}
    		}
    		
    		for (int i = 0; i < getDungeon().getEntities().size(); i++) {
    			Entity e = getDungeon().getEntities().get(i);
        		if (e.getName().equals("enemy")) {
        			Enemy enemy = new Enemy(this.getDungeon(),"enemy",e.getX(),e.getY());
    				enemy.enemyMove();

    				if (e.getX() == this.getX() && e.getY()== this.getY() && invincible == true) {
    					removeEntity(e);
    				} else if (e.getX() == this.getX() && e.getY()== this.getY() && invincible == false) {
    					round_end("You are dead");
    				}
        		} 
        	}
    		return true; 
    	}
    }
   

	public boolean isInvincible() {
		return invincible;
	}

	/**
	 * Check if there is an item on the ground where the avator is on
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void check_for_entities (int x, int y) {
		for (int i = 0; i < getDungeon().getEntities().size(); i++) {
			Entity e = getDungeon().getEntities().get(i);
			if (e == null) continue;
			if (e.getX() == x && e.getY() == y) {
				if (e.getName().equals("sword")) {
					Sword sword = new Sword("sword",e.getX(),e.getY());
					this.inventory.set_sword(sword); 
					removeEntity(sword);
				} else if (e.getName().equals("invincibility")) {
					Invincibility item = new Invincibility("invincibility",e.getX(),e.getY());
					this.invincible = true;
					this.moves_counter+=1;
					removeEntity(item);
				} else if (e.getName().equals("gold")) {
					Gold item = new Gold("gold",e.getX(),e.getY());
					this.inventory.add_gold();
					this.score += 100;
					removeEntity(item);
				} else if (e.getName().equals("key")) {
    				Key item = new Key("key",e.getX(),e.getY(), ((Key) e).getId());
					this.inventory.add_key(item);
    				removeEntity(item);
				}
			}
		}
	}
	
	/** Just a system exit call
	 * @throws Exception 
	 */
	public void round_end(String message) throws Exception {
		EndScreenApplication end = null;
		if (message.equals("You are dead")) {
			end = new EndScreenApplication(message);
		} else {
			end = new EndScreenApplication(String.valueOf(this.getScore()));
		}
		end.start(new Stage());
	}
	
	/**
	 * Move the avator up or stays on the place if there is a boulder or wall. 
	 * Also if there is a portal go to another portal
	 * @throws Throwable 
	 */
    public void moveUp() throws Throwable {
        List<Entity> entity = getDungeon().getEntities();
        Entity check = new Entity ("boulder",this.getX(), this.getY() - 1);
        if (getY() > 0 && ifMove("up")) {
        	for (Entity p: entity) {
        		if (p == null) continue;
        		if (p.equals(check) == true) {
        			p.y().set(getY() - 2);
        		} else if (p.getName().equals("portal") == true  && p.getX() == this.getX() && p.getY() == this.getY()-1) {
        			((Portal) p).enter(getDungeon(), getX(), getY()-1);
        			return;
        		} else if (p.getName().equals("closedDoor") == true && p.getX() == this.getX() && p.getY() == this.getY()-1) {
        			if (!((Door) p).enter(getDungeon(), getX(), getY()-1, ((Door) p).getId())) {
        				return;
        			}
        		}
            }
        	check_for_entities(getX(),getY() - 1);
        	y().set(getY() - 1);
        }
    }
    /**
     * Move the avator down or stays on the place if there is a boulder or wall. 
	 * Also if there is a portal go to another portal
     * @throws Throwable 
     */
    public void moveDown() throws Throwable {
        List<Entity> entity = getDungeon().getEntities();
    	Entity check = new Entity ("boulder",this.getX(), this.getY() + 1);
        if (getY() < getDungeon().getHeight() - 1 && ifMove("down")) {
        	for (Entity p: entity) {
        		if (p == null) continue;
        		if (p.equals(check) == true) {
        			p.y().set(getY() + 2);
        		} else if (p.getName().equals("portal") == true && p.getX() == this.getX() && p.getY() == this.getY()+1) {
    				((Portal) p).enter(getDungeon(), getX(), getY()+1);
        			return;
        		} else if (p.getName().equals("closedDoor") == true && p.getX() == this.getX() && p.getY() == this.getY()+1) {
        			if (!((Door) p).enter(getDungeon(), getX(), getY()+1, ((Door) p).getId())) {
        				return;
        			}
        		}
            }
        	check_for_entities(getX(),getY() + 1);
        	y().set(getY() + 1);
    	}
    }
    public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	/**
     * Move the avator left or stays on the place if there is a boulder or wall. 
	 * Also if there is a portal go to another portal
	 * @throws Throwable 
     */
    public void moveLeft() throws Throwable {
        List<Entity> entity = getDungeon().getEntities();
    	Entity check = new Entity ("boulder",this.getX() - 1, this.getY());
        if (getX() > 0 && ifMove("left")) {
        	for (Entity p: entity) {
        		if (p == null) continue;
        		if (p.equals(check) == true) {
        			p.x().set(getX() - 2);
        		} else if (p.getName().equals("portal") == true  && p.getX() == this.getX()-1 && p.getY() == this.getY()) {
        			((Entrances) p).enter(getDungeon(), getX()-1, getY());
        			return;
        		} else if (p.getName().equals("closedDoor") == true && p.getX() == this.getX()-1 && p.getY() == this.getY()) {
        			if (!((Door) p).enter(getDungeon(), getX()-1, getY(), ((Door) p).getId())) {
        				return;
        			}
        		}
            }
        	check_for_entities(getX() - 1,getY());
        	x().set(getX() - 1);
        }
    }
    /**
     * Move the avator right or stays on the place if there is a boulder or wall. 
	 * Also if there is a portal go to another portal
     * @throws Throwable 
     */
    public void moveRight() throws Throwable {
        List<Entity> entity = getDungeon().getEntities();

    	Entity check = new Entity ("boulder",getX() + 1, getY());
        if (getX() < getDungeon().getWidth() - 1 && ifMove("right")) {
        	for (Entity p: entity) { 
        		if (p == null) continue;
        		if (p.equals(check) == true) {
        			p.x().set(getX() + 2);
        			break;
        		} else if (p.getName().equals("portal") == true  && p.getX() == this.getX()+1 && p.getY() == this.getY()) {
        			((Entrances) p).enter(getDungeon(), getX()+1, getY());
        			return;
        		} else if (p.getName().equals("closedDoor") == true && p.getX() == this.getX()+1 && p.getY() == this.getY()) {
        			if (!((Door) p).enter(getDungeon(), getX()+1, getY(), ((Door) p).getId())) {
        				return;
        			}
        		}
        	}
        	check_for_entities(getX() + 1,getY());
        	x().set(getX() + 1);
        	
        }
        
    }
    /**
     * Getter for Inventory
     * @return inventory
     */
	public Inventory getInventory() {
		return inventory;
	}
	/**
	 * Setter for Inventory
	 * @param inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}  

}
