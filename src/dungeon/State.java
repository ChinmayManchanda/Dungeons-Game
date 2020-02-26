package unsw.dungeon;

public interface State {
	
	public boolean open(int id);
	public boolean enter(Dungeon dungeon, int x ,int y, int id);
}
