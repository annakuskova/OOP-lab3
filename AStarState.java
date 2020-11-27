/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
import java.util.HashMap;
import java.util.ArrayList;

public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    public HashMap<Location, Waypoint> openWaypoints;
    public HashMap<Location, Waypoint> closedWaypoints;

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null) {
            throw new NullPointerException("map cannot be null");
        } else {
            this.map = map;
            this.openWaypoints = new HashMap<>();
            this.closedWaypoints = new HashMap<>();
        }
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (this.numOpenWaypoints() != 0)
        {
            ArrayList<Waypoint> waypoints = new ArrayList<>(this.openWaypoints.values());
            float cost = waypoints.get(0).getTotalCost();
            Waypoint minimal = waypoints.get(0);
            for (int i = 1; i < waypoints.size(); i++) {
                if (waypoints.get(i).getTotalCost() < cost) {
                    cost = waypoints.get(i).getTotalCost();
                    minimal = waypoints.get(i);
                }
            }
            return minimal;
        } else {
            return null;
        }

    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if (!this.openWaypoints.containsKey(newWP.getLocation())) {
            this.openWaypoints.put(newWP.getLocation(), newWP);
            return false;
        } else {
            if (this.openWaypoints.get(newWP.getLocation()).getPreviousCost() > newWP.getPreviousCost()) {
               this.openWaypoints.replace(newWP.getLocation(), newWP);
               return true;
            }
        }
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return this.openWaypoints.values().size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        this.closedWaypoints.put(loc, this.openWaypoints.get(loc));
        this.openWaypoints.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        if (this.closedWaypoints.containsKey(loc)) {
            return true;
        }
        return false;
    }
}
