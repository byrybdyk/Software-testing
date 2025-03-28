import com.zarubov.math.*;
import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;

class TargetTests {

    private static final double EPS = 0.01;
    private static final double funEps = 0.01;
    private static final double testEps = 0.5;

    private static Cot cotMock;
    private static Cos cosMock;
    private static Csc cscMock;
    private static Tan tanMock;
    private static Log logMock;
    private static Ln lnMock;

    @BeforeAll
    static void init() throws Exception {
        cotMock = Mockito.mock(Cot.class);
        cosMock = Mockito.mock(Cos.class);
        cscMock = Mockito.mock(Csc.class);
        tanMock = Mockito.mock(Tan.class);
        logMock = Mockito.mock(Log.class);
        lnMock = Mockito.mock(Ln.class);

        loadMockData(cotMock, "src/test/resources/mocks/CotMock.csv");
        loadMockData(cosMock, "src/test/resources/mocks/CosMock.csv");
        loadMockData(cscMock, "src/test/resources/mocks/CscMock.csv");
        loadMockData(tanMock, "src/test/resources/mocks/TanMock.csv");
        loadMockData(logMock, "src/test/resources/mocks/LogMock.csv");
        loadMockData(lnMock, "src/test/resources/mocks/LnMock.csv");
    }

    private static void loadMockData(Object mock, String filePath) throws Exception {
        FileReader reader = new FileReader(filePath);
        var records = CSVFormat.DEFAULT.parse(reader);
        for (var record : records) {
            double input = Double.parseDouble(record.get(0));
            double output = Double.parseDouble(record.get(1));
            if (mock instanceof Cot) {
                Mockito.when(((Cot) mock).calculate(input, EPS)).thenReturn(output);
            } else if (mock instanceof Cos) {
                Mockito.when(((Cos) mock).calculate(input, EPS)).thenReturn(output);
            } else if (mock instanceof Csc) {
                Mockito.when(((Csc) mock).calculate(input, EPS)).thenReturn(output);
            } else if (mock instanceof Tan) {
                Mockito.when(((Tan) mock).calculate(input, EPS)).thenReturn(output);
            } else if (mock instanceof Log) {
                double base = Double.parseDouble(record.get(1));
                double logOutput = Double.parseDouble(record.get(2));
                Mockito.when(((Log) mock).calculate(input, base, EPS)).thenReturn(logOutput);
            } else if (mock instanceof Ln) {
                Mockito.when(((Ln) mock).calculate(input, EPS)).thenReturn(output);
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetMocks(Double input, Double expected) {
        Target target = new Target(cotMock, cosMock, cscMock, tanMock, logMock, lnMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetCot(Double input, Double expected) {
        Cot cot = new Cot(cosMock, new NormaliseTrigonometrical());
        Target target = new Target(cot, cosMock, cscMock, tanMock, logMock, lnMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetCsc(Double input, Double expected) {
        Csc csc = new Csc(new NormaliseTrigonometrical(), cosMock);
        Target target = new Target(cotMock, cosMock, csc, tanMock, logMock, lnMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetTan(Double input, Double expected) {
        Tan tan = new Tan(new NormaliseTrigonometrical(), cosMock);
        Target target = new Target(cotMock, cosMock, cscMock, tan, logMock, lnMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetCos(Double input, Double expected) {
        Cos cos = new Cos(new NormaliseTrigonometrical());
        Cot cot = new Cot(cos, new NormaliseTrigonometrical());
        Csc csc = new Csc(new NormaliseTrigonometrical(), cos);
        Tan tan = new Tan(new NormaliseTrigonometrical(), cos);
        Target target = new Target(cot, cos, csc, tan, logMock, lnMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetLog(Double input, Double expected) {
        Log log = new Log(lnMock);
        Target target = new Target(cotMock, cosMock, cscMock, tanMock, log, lnMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetLn(Double input, Double expected) {
        Ln ln = new Ln();
        Log log = new Log(ln);
        Target target = new Target(cotMock, cosMock, cscMock, tanMock, log, ln);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TargetIn.csv")
    void testTargetNoMock(Double input, Double expected) {
        Cos cos = new Cos(new NormaliseTrigonometrical());
        Cot cot = new Cot(cos, new NormaliseTrigonometrical());
        Csc csc = new Csc(new NormaliseTrigonometrical(), cos);
        Tan tan = new Tan(new NormaliseTrigonometrical(), cos);
        Ln ln = new Ln();
        Log log = new Log(ln);
        Target target = new Target(cot, cos, csc, tan, log, ln);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }
}