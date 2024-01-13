import java.util.Arrays;
import java.util.List;

public class Solution {


    public static void main(String[] args) {
        runTestCase1();
        runTestCase2();
        runTestCase3();
    }

    static void runTestCase1() {
        List<Integer> memory = Arrays.asList(1, 4, 6, 2, 3, 1);
        List<Integer> tasks =Arrays.asList(1, 2, 1, 3, 4, 1);
        System.out.println(Result2.timeRequired(memory, tasks, 10));

    }

    static void runTestCase2() {
        List<Integer> memory = Arrays.asList(
                17, 18, 13, 11,
                13, 19, 15, 13, 10,
                13, 12, 11, 15, 19,
                16, 10, 11, 14, 18,
                19
        );

        List<Integer> tasks =Arrays.asList(
                4, 3, 4, 1, 1,
                3, 3, 4, 2, 2,
                4, 3, 5, 1, 3,
                4, 3, 2, 3, 1
        );
        System.out.println(Result2.timeRequired(memory, tasks, 213));
        //System.out.println(Result.timeRequired(memory, tasks, 213));
    }

    static void runTestCase3() {
        List<Integer> memory = Arrays.asList(
                1, 1, 1, 1, 1, 1
        );

        List<Integer> tasks = Arrays.asList(
                1,1, 1, 1, 2, 1
        );
        System.out.println(Result2.timeRequired(memory, tasks, 4));
    }


}
