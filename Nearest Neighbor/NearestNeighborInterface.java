package project2;
/**
 * Find the nearest or furthest values
 *
 * @author Gerald Cohen, Copyright (c) 2019
 */
public interface NearestNeighborInterface<T, V> {
    /**
     * Add an individual value to the set of values to be evaluated.
     * @param value 
     */
    void add(V value);

    /**
     * Clear the pool of values
     */
    void clear();
    
    /**
     * Reset the values stored so that other targets can be evaluated
     */
    void reset();
    
    /**
     * Change or establish the target value
     * @param target 
     */
    void setTarget(V target);

    /**
     * Select nearest or furthest neighbors
     * @param nearest true for nearest, false for furthest
     */
    void setNearest(boolean nearest);

    /**
     * Operation to be performed returning an object of type T. 
     * The return type can also be an array of type T.
     * @param value parameters
     * @return resulting object
     */
    default T execute(V... value) {
        return null;
    }

}
