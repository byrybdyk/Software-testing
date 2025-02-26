import org.example.MergeSort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    private static Stream<Object[]> mergeSortCases() {
        return Stream.of(
                new Object[]{new int[]{}, ""},
                new Object[]{new int[]{1}, ""},
                new Object[]{new int[]{4, 2, 5, 3, 1, 6},
                        "merge([2, 4])\n" +
                        "merge([2, 4, 5])\n" +
                        "merge([1, 3])\n" +
                        "merge([1, 3, 6])\n" +
                        "merge([1, 2, 3, 4, 5, 6])"},
                new Object[]{new int[]{7,7,7,7,7},
                        "merge([7, 7])\n" +
                        "merge([7, 7, 7])\n" +
                        "merge([7, 7])\n" +
                        "merge([7, 7, 7, 7, 7])"},
                new Object[]{new int[]{10, 2, 3, 7, 8, 6, 5, 1, 4, 9},
                        "merge([2, 10])\n" +
                        "merge([2, 3, 10])\n" +
                        "merge([7, 8])\n" +
                        "merge([2, 3, 7, 8, 10])\n" +
                        "merge([5, 6])\n" +
                        "merge([1, 5, 6])\n" +
                        "merge([4, 9])\n" +
                        "merge([1, 4, 5, 6, 9])\n" +
                        "merge([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])"}
        );
    }

    @ParameterizedTest
    @MethodSource("mergeSortCases")
    void testTrackingMergeSteps(int[] input, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            MergeSort.sort(input, true);
        } finally {
            System.setOut(originalOut);
        }

        String actualOutput = outputStream.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "Test failed: the merge steps do not match!");
    }
}