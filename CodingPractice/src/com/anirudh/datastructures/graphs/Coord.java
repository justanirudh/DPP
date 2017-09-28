package com.anirudh.datastructures.graphs;

/**
 * Created by paanir on 7/15/17.
 */
// dont use this. If a matrix, do DFS in a matrix
public class Coord {
    private Integer x;
    private Integer y;

    Coord(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //If implementing equals, ALWAYS implement hashcode
    //graph.get() in line 130 checks the get with the hash, hence need to implement both below
    //Gives NPE if not implemented either one of them. AWESOME
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Coord)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Coord c = (Coord) o;

        // Compare the data members and return accordingly
        return x == c.getX() && y == c.getY();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }
}
