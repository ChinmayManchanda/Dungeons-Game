package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoorTest {

	@Test
	void testClosedDoor() throws Throwable {
		Dungeon p = new Dungeon(10, 10);
		Player q = new Player(p, "player", 4, 4);
		Door closedDoorD = new Door("closedDoor",4,5,1);
		Door closedDoorU = new Door("closedDoor",4,3,2);
		Door closedDoorR = new Door("closedDoor",5,4,3);
		Door closedDoorL = new Door("closedDoor",3,4,4);
		Key keyD = new Key("key", 1,1,1);
		p.setPlayer(q);
		p.addEntity(closedDoorD);
		p.addEntity(closedDoorU);
		p.addEntity(closedDoorR);
		p.addEntity(closedDoorL);
		q.moveDown();
		assertEquals(4, q.getY());
		
		q.moveLeft();
		assertEquals(4, q.getX());
		
		q.moveRight();
		assertEquals(4, q.getX());
		
		q.moveUp();
		assertEquals(4, q.getY());
		q.getInventory().add_key(keyD);

		q.moveDown();
		assertEquals(5, q.getY());
		
	}
	
	@Test
	void testOpenedDoor() throws Throwable {
		Dungeon p = new Dungeon(10, 10);
		Player q = new Player(p, "player", 4, 4);
		Door openedDoorD = new Door("openedDoor",4,5,1);
		Door openedDoorU = new Door("openedDoor",4,3,2);
		Door openedDoorR = new Door("openedDoor",5,4,3);
		Door openedDoorL = new Door("openedDoor",3,4,4);
		p.setPlayer(q);
		p.addEntity(openedDoorD);
		p.addEntity(openedDoorU);
		p.addEntity(openedDoorR);
		p.addEntity(openedDoorL);
		q.moveDown();
		assertEquals(5, q.getY());
		q.setY(4);
		
		q.moveUp();
		assertEquals(3, q.getY());
		q.setY(4);
		
		q.moveRight();
		assertEquals(5, q.getX());
		q.setX(4);
		
		q.moveLeft();
		assertEquals(3, q.getX());
		q.setX(4);
	}
	

}
