package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwordTest {

	@Test
	void testCreationOfSword() {
		Sword newSword = new Sword("Sword", 1,1);
		assertEquals(5, newSword.getHits());
		assertEquals(1, newSword.getX());
		assertEquals(1, newSword.getY());
		assertEquals("Sword", newSword.getName());
	}
	
	@Test
	void testReduceDurability() {
		Sword newSword = new Sword("Sword", 1,1);
		assertEquals(5, newSword.getHits());
		newSword.reduceHits();
		assertEquals(4, newSword.getHits());
	}


}
