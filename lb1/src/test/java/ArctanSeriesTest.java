import static org.junit.jupiter.api.Assertions.*;

import org.example.ArctanSeries;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArctanSeriesTest {

    private static final double EPSILON = 1e-6;

    private static final double EXPECTED_ZERO = 0.0;
    private static final double EXPECTED_SMALL_POS = 0.099668652491162;
    private static final double EXPECTED_SMALL_NEG = -0.099668652491162;
    private static final double EXPECTED_MIDDLE_POS = 0.46364760900081;
    private static final double EXPECTED_MIDDLE_NEG = -0.46364760900081;
    private static final double EXPECTED_HIGH_POS = 0.73281510178651;
    private static final double EXPECTED_HIGH_NEG = -0.73281510178651;
    private static final double EXPECTED_ONE = 0.78539816339745;
    private static final double EXPECTED_NEG_ONE = -0.78539816339745;

    static Stream<Object[]> arctanCases() {
        return Stream.of(
                new Object[]{0.0, 10, EXPECTED_ZERO},
                new Object[]{0.1, 10, EXPECTED_SMALL_POS},
                new Object[]{-0.1, 10, EXPECTED_SMALL_NEG},
                new Object[]{0.5, 15, EXPECTED_MIDDLE_POS},
                new Object[]{-0.5, 15, EXPECTED_MIDDLE_NEG},
                new Object[]{0.9, 50, EXPECTED_HIGH_POS},
                new Object[]{-0.9, 50, EXPECTED_HIGH_NEG},
                new Object[]{1.0, 300000, EXPECTED_ONE},
                new Object[]{-1.0, 300000, EXPECTED_NEG_ONE}
        );
    }

    @ParameterizedTest
    @MethodSource("arctanCases")
    void testArctan(double x, int terms, double expected) {
        assertEquals(expected, ArctanSeries.arctan(x, terms), EPSILON);
    }

    @Test
    void testDivergenceForGreaterThanOne() {
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.arctan(1.5, 10));
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.arctan(-1.2, 10));
    }
}