package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InventoryTest {

	@Test
	void testCreation() {
		Inventory newInven = new Inventory();
		assertEquals(null, newInven.sword);
		assertEquals(0, newInven.amountKey);
		assertEquals(0, newInven.gold);
	}
	
	@Test
	void testAddGold() {
		Inventory newInven = new Inventory();
		newInven.add_gold();
		assertEquals(100, newInven.get_gold());
		newInven.add_gold();
		newInven.add_gold();
		assertEquals(300, newInven.get_gold());
	}
	
	@Test
	void testAddKey() {
		Inventory newInven = new Inventory();
		Key key = new Key("key", 1, 2, 1);
		newInven.add_key(key);
		assertEquals(1, newInven.getAmountKey());
		Key key1 = new Key("key", 1, 3, 2);
		newInven.add_key(key1);
		assertEquals(1, newInven.getAmountKey());
		newInven.remove_key(key);
		assertEquals(0, newInven.getAmountKey());
		newInven.remove_key(key1);
		assertEquals(0, newInven.getAmountKey());
		newInven.add_key(key1);
		assertEquals(1, newInven.getAmountKey());
	}

}
