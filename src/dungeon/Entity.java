package unsw.dungeon;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String name;

    /**
     * Create an entity positioned in square (x,y)
     * @param name
     * @param x
     * @param y
     */
    public Entity(String name, int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.name = name;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public String getName() { 
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getY() {
        return y().get();  
    }

    public int getX() {
        return x().get();
    }
	
    public void setY(int ya) {
        this.y = new SimpleIntegerProperty(ya);
    }

    public void setX(int xa) {
        this.x = new SimpleIntegerProperty(xa);
    }
    
	@Override
	public boolean equals(Object obj) {
		Entity other = (Entity) obj;
		if (other == null) return false;
		if (Objects.deepEquals(this.name,other.name)
				&& this.getX() == other.getX() && this.getY() == other.getY()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Entity [x=" + x + ", y=" + y + ", name=" + name + "]";
	}
    
}
