package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	Sword sword;
	int gold;
	private ArrayList<Key> key = null;
	int amountKey;
	/**
	 * The constructor for inventory, inside inventory is;
	 * sword = null
	 * gold = 0
	 * key = 0
	 */
	public Inventory() { 
		this.sword = null;
		this.gold = 0;
		key = new ArrayList<>();
		this.amountKey = 0;
	}
	/**
	 * Setter for sword in inventory
	 * @param sword
	 */
	public void set_sword(Sword sword) {
		this.sword = sword; 
	}
	/**
	 * Getter for sword in inventory
	 * @return sword
	 */
	public Sword get_sword() {
		return this.sword;
	}
	/**
	 * Add gold by 100 
	 */
	public void add_gold() {
		this.gold += 100;
	}
	/**
	 * Add key however if they already have key does nothing
	 */
	public void add_key(Key key) {
		if (this.getAmountKey() == 0) {
			this.key.add(key);
			amountKey+=1;
		}
	}
	public ArrayList<Key> getKey() {
		return key;
	}
	/**
	 * Remove key by one if inventory already has ONE key
	 */
	public void remove_key(Key key) {
		if (this.getAmountKey() == 1) {
			this.key.remove(key);
			amountKey-=1;
		}
	}
	/**
	 * Getter for key in inventory
	 * @return key
	 */
	public int getAmountKey() {
		return amountKey;
	}
	/**
	 * Getter for gold in inventory
	 * @return gold
	 */
	public int get_gold() {
		return this.gold;
	}

	@Override
	public String toString() {
		return "Inventory [sword=" + sword + ", gold=" + gold + "]";
	}

	

	
	
	
}
