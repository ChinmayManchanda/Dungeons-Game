package unsw.dungeon;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void PlayerCollideWallTest() throws Exception {
		Dungeon p = new Dungeon(5, 10);
		Player q = new Player(p, "Player", 2, 2);
		Wall down = new Wall("Wall", 2, 3);
		Wall up = new Wall("Wall", 2, 1);
		Wall right = new Wall("Wall", 3, 2);
		Wall left = new Wall("Wall", 1, 2);
		int counter = 0;
		p.setPlayer(q);
		p.addEntity(down);
		p.addEntity(up);
		p.addEntity(right); 
		p.addEntity(left);
		//check if getter for Player in Dungeon works
		assertEquals(q, p.getPlayer());
		//check that the number of entities is correct
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(4, counter);
		
		//check player can move down when wall is there
		//assertFalse(q.ifMove("down"));
		//assertFalse(q.ifMove("up"));
		//assertFalse(q.ifMove("right"));
		//assertFalse(q.ifMove("left"));
		
	}
	
	@Test
	void PlayerMoveBoulderTest() throws Exception {
		Dungeon p = new Dungeon(20, 20);
		Player q = new Player(p, "Player", 4, 4);
		Boulder down = new Boulder("Boulder", 4, 5);
		Boulder up = new Boulder("Boulder", 4, 3);
		Boulder right = new Boulder("Boulder", 5, 4);
		Boulder left = new Boulder("Boulder", 3, 4);
		int counter = 0;
		p.setPlayer(q);
		p.addEntity(down);
		p.addEntity(up);
		p.addEntity(right);
		p.addEntity(left);
		//check if getter for Player in Dungeon works
		assertEquals(q, p.getPlayer());
		//check that the number of entities is correct
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(4, counter);
		
		//check player can move down when Boulder is there
		assertTrue(q.ifMove("down"));
		assertTrue(q.ifMove("up"));
		assertTrue(q.ifMove("right"));
		assertTrue(q.ifMove("left"));
		
	}
	
	@Test
	void PlayerMoveBoulderCollideWallTest() {
		Dungeon p = new Dungeon(20, 20);
		Player q = new Player(p, "Player", 4, 4);
		Boulder downB = new Boulder("Boulder", 4, 5);
		Boulder upB = new Boulder("Boulder", 4, 3);
		Boulder rightB = new Boulder("Boulder", 5, 4);
		Boulder leftB = new Boulder("Boulder", 3, 4);
		Wall downW = new Wall("Wall", 4, 6);
		Wall upW = new Wall("Wall", 4, 2);
		Wall rightW = new Wall("Wall", 6, 4);
		Wall leftW = new Wall("Wall", 2, 4);
		int counter = 0;
		p.setPlayer(q);
		p.addEntity(downB);
		p.addEntity(upB);
		p.addEntity(rightB);
		p.addEntity(leftB);
		p.addEntity(downW);
		p.addEntity(upW);
		p.addEntity(rightW);
		p.addEntity(leftW);
		//check if getter for Player in Dungeon works
		assertEquals(q, p.getPlayer());
		//check that the number of entities is correct
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(8, counter);
		
		//check player cannot move down when Boulder is there next to a Wall
		//assertFalse(q.ifMove("down"));
		//assertFalse(q.ifMove("up"));
		//assertFalse(q.ifMove("right"));
		//assertFalse(q.ifMove("left"));
		
	}
	
	@Test
	void PlayerMoveBoulderCollideBoulderTest() {
		Dungeon p = new Dungeon(20, 20);
		Player q = new Player(p, "Player", 4, 4);
		Boulder downB = new Boulder("Boulder", 4, 5);
		Boulder upB = new Boulder("Boulder", 4, 3);
		Boulder rightB = new Boulder("Boulder", 5, 4);
		Boulder leftB = new Boulder("Boulder", 3, 4);
		Boulder downCB = new Boulder("Boulder", 4, 6);
		Boulder upCB = new Boulder("Boulder", 4, 2);
		Boulder rightCB = new Boulder("Boulder", 6, 4);
		Boulder leftCB = new Boulder("Boulder", 2, 4);
		int counter = 0;
		p.setPlayer(q);
		p.addEntity(downB);
		p.addEntity(upB);
		p.addEntity(rightB);
		p.addEntity(leftB);
		p.addEntity(downCB);
		p.addEntity(upCB);
		p.addEntity(rightCB);
		p.addEntity(leftCB);
		//check if getter for Player in Dungeon works
		assertEquals(q, p.getPlayer());
		//check that the number of entities is correct
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(8, counter);
		
		//check player cannot move down when Boulder is there next to a Boulder
		//assertFalse(q.ifMove("down"));
		//assertFalse(q.ifMove("up"));
		//assertFalse(q.ifMove("right"));
		//assertFalse(q.ifMove("left"));
		
	}
	
	@Test
	void PlayerCheckSwitches() {
		Dungeon p = new Dungeon(5, 10);
		Player q = new Player(p, "Player", 2, 2);
		Switch switch1 = new Switch("switch", 3, 3);
		Boulder boulder = new Boulder("boulder", 3, 3);
		p.setPlayer(q);
		p.addEntity(switch1);
		p.addEntity(boulder);
		assertTrue(q.check_if_switches_activated());
	}
	
	@Test
	void PlayerUseSword() {
		Dungeon p = new Dungeon(5, 10);
		Player q = new Player(p, "player", 2, 2);
		Enemy eDown = new Enemy(p, "enemy", 2, 3);
		Enemy eUp = new Enemy(p, "enemy", 2, 1);
		Enemy eRight = new Enemy(p, "enemy", 3, 2);
		Enemy eLeft = new Enemy(p, "enemy", 1, 2);
		Sword sword = new Sword("Sword", 2, 2);
		Inventory Inven = new Inventory();
		p.setPlayer(q);
		p.addEntity(sword);
		p.addEntity(eDown);
		p.addEntity(eUp);
		p.addEntity(eRight);
		p.addEntity(eLeft);
		q.setInventory(Inven);;
		int counter = 0;
		Inven.set_sword(sword);
		assertEquals(sword, Inven.get_sword());
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(5, counter);
		counter = 0;
		q.useSword("down");
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(4, counter);
		counter = 0;
		q.useSword("up");
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(3, counter);
		counter = 0;
		q.useSword("right");
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(2, counter);
		counter = 0;
		q.useSword("left");
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(1, counter);	
	}
	
	@Test
	void PlayerGetItems() {
		Dungeon p = new Dungeon(5, 10);
		Player q = new Player(p, "player", 2, 2);
		Sword sword = new Sword("sword", 2, 2);
		Inventory Inven = new Inventory();
		int counter = 0;
		p.setPlayer(q);
		q.setInventory(Inven);
		p.addEntity(sword);
		Inven.set_sword(sword);
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(1, counter);
		counter = 0;
		q.check_for_entities(2, 2);
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(0, counter);

		Invincibility Invin = new Invincibility("invincibility", 2, 2);
		p.addEntity(Invin);
		counter = 0;
		q.check_for_entities(2, 2);
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(0, counter);
		
		Gold gold = new Gold("gold", 2, 2);
		p.addEntity(gold);
		counter = 0;
		q.check_for_entities(2, 2);
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(0, counter);
		
		Key key = new Key("key", 2, 2, 1);
		p.addEntity(key);
		counter = 0;
		q.check_for_entities(2, 2);
		for (Entity w: p.getEntities()) {
			counter++;
		}
		assertEquals(0, counter);		
	}
	

}