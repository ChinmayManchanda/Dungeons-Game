package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvincibilityTest {

	@Test
	void testCreation() {
		Invincibility newPotion =new Invincibility("Potion", 1, 1);
		assertEquals(1, newPotion.getX());
		assertEquals(1, newPotion.getY());
		assertEquals("Potion", newPotion.getName());
	}

}
