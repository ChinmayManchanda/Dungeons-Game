package unsw.dungeon;

public class ClosedDoor implements State, Entrances{

	Door door;
	/**
	 * Constructor for ClosedDoor
	 * @param door
	 */
	public ClosedDoor(Door door) {
		this.door = door;
	}

	@Override
	public String toString() {
		return "ClosedDoor [door=" + door + "]";
	}

	@Override
	public boolean open(int id) {
		if (door.getId() == id) {
			door.setState(door.getOpenDoor());	
			return true;
		}
		return false;
	}

	@Override
	public boolean enter(Dungeon dungeon, int x, int y, int id) {
		Player player = dungeon.getPlayer();
		Door ClosedD = new Door("closedDoor",x,y,id);
		Door OpenD = new Door("openDoor",x,y,id);
		boolean cond1 = false;
		System.out.println("Do i");
		System.out.println(dungeon.getPlayer().getInventory().getAmountKey());
		
		
		if (dungeon.getEntities().contains(ClosedD) && dungeon.getPlayer().getInventory().getAmountKey() >= 1) {
			System.out.println("Do i");
			
			for (int z = 0; z < dungeon.getEntities().size();z++) {
				Entity d = dungeon.getEntities().get(z);
				if (d.equals(OpenD)) {
					d.x().set(door.getX());
					d.y().set(door.getY());
				}
			}
			dungeon.getPlayer().removeEntity(ClosedD);
			//return open(id);
		}	
		return false;
	}

	@Override
	public void enter(Dungeon dungeon, int x, int y) {
		return;
		
	}

}
