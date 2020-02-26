package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortalTest {

	@Test
	void testPortalCreation() {
		Portal newPortal = new Portal("Portal", 1, 1, 1); 
		assertEquals(1, newPortal.getX());
		assertEquals(1, newPortal.getY());
		assertEquals("Portal", newPortal.getName());
	}
	
	@Test
	void testEnter() throws Throwable {
		Dungeon p = new Dungeon(20, 20);
		Player q = new Player(p, "player", 4, 4);
		Portal portalEnter = new Portal("portal", 4, 5, 1);
		Portal portalExit = new Portal("portal", 10, 10, 1);
		
		p.setPlayer(q);
		p.addEntity(portalExit);
		p.addEntity(portalEnter);
		q.moveDown();
		System.out.println(q.getY());
		assertEquals(10, q.getY());
	}

}
