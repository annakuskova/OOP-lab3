import java.util.Objects;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.xCoord, this.yCoord});
    }

    public boolean equals(Object ob) {
        if (ob != null && this.getClass() == ob.getClass()) {
            Location location = (Location)ob;
            return this.xCoord == location.xCoord && this.yCoord == location.yCoord;
        } else {
            return false;
        }
    }
}