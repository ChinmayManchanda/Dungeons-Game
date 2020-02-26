package unsw.dungeon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;




public class Enemy extends Person {
	
//	
	/** Constructor for Enemy.
	 * @param dungeon
	 * @param name
	 * @param x
	 * @param y
	 */
	public Enemy(Dungeon dungeon,String name, int x, int y) {
        super(dungeon, name, x, y);
        
	}
	
	/** Checks if the enemy has any boulders or walls next to it.
	 * @param String type
	 */
	@Override
	public boolean ifMove(String type) {
		if (this.check_for_wall(type) == false) {
    		return false;
    	} else {
    		return true;
    	}
    }
	
	
	/** Checks if the enemy has any boulders or walls next to it.
	 * @param String type
	 * @return boolean value
	 */
	@Override
	public boolean check_for_wall(String type) {
    	List<Entity> entity = this.getDungeon().getEntities();
    	//check_round_over();
    	for (Entity p: entity) {
    		if (p == null) continue;
    		if (p.getName().equals("Wall") == true || p.getName().equals("boulder")) {
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
	/** BFS algorithm for the movement of enemy. Also checks invincibility of the player and acts according to it.
	 */
	public void enemyMove() {
		
		Graph newgraph = new Graph();
		Queue<Node> q = new LinkedList<Node>();
		List<Node> visited = new CopyOnWriteArrayList<Node>();
		Node p = new Node(this.getX(),this.getY());
		
		newgraph.addNode(p);
		q.add(p);
		Node dest = new Node(this.getDungeon().getPlayer().getX(), this.getDungeon().getPlayer().getY());

		while (q.size() > 0) {
			Node curr = q.element();
			visited.add(curr);
			q.remove(curr);
			ArrayList<Node> possible = check_all_adjacent(newgraph, curr);
			// maps out all the possible squares that the enemy can go to
			for (Node nextPoss: possible) {
				if (visited.contains(nextPoss) != true) {
					q.add(nextPoss);
					visited.add(nextPoss);
					newgraph.addNode(nextPoss);
				}
			}
		
			
	
		}


		
		
		for (Entity i: this.getDungeon().getEntities()) {
			if (i.getName().equals("enemy")) {
				
				String idNode = newgraph.DijkstraShortestPath(p, dest);
				
				for (Node visNext: visited) {
					if (visNext.getId().equals(idNode)) {
						if (this.getDungeon().getPlayer().isInvincible()) {
							int currEnePosX = i.x().get();
							int currEnePosY = i.y().get();
							int goingEnePosX = visNext.getxCor();
							int goingEnePosY = visNext.getyCor();
							int runEnePosX = currEnePosX + (currEnePosX - goingEnePosX);
							int runEnePosY = currEnePosY + (currEnePosY - goingEnePosY);
							if (checker(runEnePosX, runEnePosY) == true) {
								i.x().set(runEnePosX);
								i.y().set(runEnePosY);
								return;
							} else {
								change_direction(i, runEnePosX, runEnePosY);
							}
							return;
						} else {
							i.x().set(visNext.getxCor());
							i.y().set(visNext.getyCor());
							return;
						}
					}
					
				}
			}
		}	 
		
	}
	public Boolean checker(int x, int y) {
		List<Entity> entities = this.getDungeon().getEntities();
		for (Entity iterator: entities) {
			if (iterator.x().get() == x && iterator.y().get() == y) {
				if (iterator.getName().equals("wall") || iterator.getName().equals("boulder")) {
					return false;
				} 
			}
		}
		return true;
	}
	public void change_direction(Entity enemy, int x, int y) {		
		Entity wall1 = new Entity("wall", this.x().get(), this.y().get() - 1);
		Entity wall2 = new Entity("wall", this.x().get(), this.y().get() + 1);
		Entity wall3 = new Entity("wall", this.x().get() - 1, this.y().get());
		Entity wall4 = new Entity("wall", this.x().get() + 1, this.y().get());
		Entity b1 = new Entity("boulder",this.x().get(), this.y().get() - 1);
		Entity b2 = new Entity("boulder",this.x().get(), this.y().get() + 1);
		Entity b3 = new Entity("boulder",this.x().get() - 1, this.y().get());
		Entity b4 = new Entity("boulder",this.x().get() + 1, this.y().get());
		List<Entity> entities = this.getDungeon().getEntities();
		if (enemy.x().get() != x) {
			if (!entities.contains(wall1) && !entities.contains(b1)) {
				enemy.x().set(x().get());
				enemy.y().set(y().get() - 1);
			} else if (!entities.contains(wall2) && !entities.contains(b2)) {
				enemy.x().set(x().get());
				enemy.y().set(y().get() + 1);
			}
		} else {
			if (!entities.contains(wall3) && !entities.contains(b3)) {
				enemy.x().set(x().get() - 1);
				enemy.y().set(y().get());
			} else if (!entities.contains(wall4) && !entities.contains(b4)) {
				enemy.x().set(x().get() + 1);
				enemy.y().set(y().get());
			}
		}
	}
	/** Just a system exit call
	 */
	public void round_end() {
		System.exit(0);
	}
	
	/** Gives all the points where the enemy can go from the point P.
	 * @param Point p
	 * @return List of points.
	 */
	public ArrayList<Node> check_all_adjacent(Graph g, Node p) {
		ArrayList<Node> check= new ArrayList<Node>();
		Entity wall1 = new Entity("wall", p.getxCor(), p.getyCor() - 1);
		Entity wall2 = new Entity("wall", p.getxCor(), p.getyCor() + 1);
		Entity wall3 = new Entity("wall", p.getxCor() - 1, p.getyCor());
		Entity wall4 = new Entity("wall", p.getxCor() + 1, p.getyCor());
		Entity b1 = new Entity("boulder",p.getxCor(), p.getyCor() - 1);
		Entity b2 = new Entity("boulder",p.getxCor(), p.getyCor() + 1);
		Entity b3 = new Entity("boulder",p.getxCor() - 1, p.getyCor());
		Entity b4 = new Entity("boulder",p.getxCor() + 1, p.getyCor());
		
		List<Entity> entities = this.getDungeon().getEntities();
		if (!entities.contains(wall1) && !entities.contains(b1)) {
			Node test = new Node(p.getxCor(), p.getyCor() - 1);
			g.addEdge(p, test);
			check.add(test);
		}
		if (!entities.contains(wall2) && !entities.contains(b2)) {
			Node test = new Node(p.getxCor(), p.getyCor() + 1);
			g.addEdge(p, test);
			check.add(test);
		}
		if (!entities.contains(wall3) && !entities.contains(b3)) {
			Node test = new Node(p.getxCor() - 1, p.getyCor());
			g.addEdge(p, test);
			check.add(test);
		}
		if (!entities.contains(wall4) && !entities.contains(b4)) {
			Node test = new Node(p.getxCor() + 1, p.getyCor());
			g.addEdge(p, test);
			check.add(test);
		}
		return check;
	}
	
}
