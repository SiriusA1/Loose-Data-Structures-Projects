package project2;
/**
 * Project 2 - Nearest Neighbors Tester
 *
 * @author Gerald Cohen, Ph.D. 2019
 */
public class zTester {

    public static void main(String[] a) {
        Integer[] testData = {1, 2, 3, 18, 23, 17, 14, 7, 24};
        NearestNeighbors<Integer> nearest = new NearestNeighbors<>(19);
        nearest.add(10);
        //nearest.setNearest(false);
        Object[] result = nearest.execute(testData);
        printValues(result);
        //nearest.clear();
        nearest.setTarget(9);
        result = nearest.execute();
        printValues(result);
    }

    private static void printValues(Object... v) {
        System.out.printf("%d values found: ", v.length);
        for (Object x : v) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
