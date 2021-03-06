 package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {  
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    } 

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height"); 

        Dungeon dungeon = new Dungeon(width, height); 

        JSONArray jsonEntities = json.getJSONArray("entities");
 
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon,"Player", x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall("wall",x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder("boulder",x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch floorSwitch = new Switch("switch", x, y);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	break;
    	case "exit":
        	Exit exit = new Exit("Exit", x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
    	case "sword":
    		Sword sword = new Sword("sword", x, y);
    		onLoad(sword);
    		entity = sword;
    		break;
    	case "invincibility":
    		Invincibility invincibility = new Invincibility("invincibility",x, y);
    		onLoad(invincibility);
    		entity = invincibility;
    		break;
    	case "treasure":
    		Gold gold = new Gold("gold",x, y);
    		onLoad(gold);
    		entity = gold;
    		break;
    	case "enemy":
    		Enemy enemy = new Enemy(dungeon,"enemy",x,y);
    		onLoad(enemy);
    		entity = enemy;
    		break;
        case "key":
        	int ID = json.getInt("id");
        	Key key = new Key("key",x, y, ID);
        	onLoad(key);
        	entity = key;
        	break;
        case "portal":
        	int ID1 = json.getInt("id");
        	Portal portal = new Portal("portal", x, y, ID1);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "door":
        	int ID2 = json.getInt("id");
        	Door Closeddoor = new Door("closedDoor", x, y, ID2);
        	onLoad(Closeddoor);
        	entity = Closeddoor; 
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch switches);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Invincibility invincibility);

    public abstract void onLoad(Gold gold);
    
    public abstract void onLoad(Enemy enemy);
   
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Portal portal);

	public abstract void onLoad(Door Doors);


    // TODO Create additional abstract methods for the other entities

}
