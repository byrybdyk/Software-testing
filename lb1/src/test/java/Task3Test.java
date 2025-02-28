import org.example.Task3.Amount;
import org.example.Task3.ComputerEquipment;
import org.example.Task3.Human;
import org.example.Task3.Kamorka;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    private Kamorka kamorka;
    private ComputerEquipment computerEquipment;
    private Human Ford;
    private Human Zaphod;

    @BeforeEach
    void setUp() {
        computerEquipment = new ComputerEquipment();
        kamorka = new Kamorka();
        kamorka.setComputerEquipment(computerEquipment);

        Ford = new Human();
        Ford.setName("Ford");

        Zaphod = new Human();
        Zaphod.setName("Zaphod");
    }

    static Stream<Arguments> provideEnterKamorkaCases() {
        return Stream.of(
                Arguments.of(true, true),
                Arguments.of(false, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideEnterKamorkaCases")
    void testEnterKamorka(boolean isOpened, boolean shouldEnter) {
        kamorka.setOpened(isOpened);
        if (shouldEnter) {
            assertDoesNotThrow(() -> Ford.enterRoom(kamorka));
        } else {
            assertThrows(IllegalArgumentException.class, () -> Ford.enterRoom(kamorka));
        }
    }

    static Stream<Arguments> provideStrengtCases() {
        return Stream.of(
                Arguments.of( 10,true),
                Arguments.of( 0,true),
                Arguments.of( -10,false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStrengtCases")
    void testStrength(int Strength, boolean shouldSet) {
        Human human = new Human();
        if(shouldSet) {
            assertDoesNotThrow(() -> human.setStrenght(Strength));
        } else {
            assertThrows(IllegalArgumentException.class, () -> human.setStrenght(Strength));
        }
    }

    static Stream<Arguments> providePullInHumanCases() {
        return Stream.of(
                Arguments.of(true, 10, 5, true),
                Arguments.of(false, 10, 5, false),
                Arguments.of(true, 5, 10, false),
                Arguments.of(false, 5, 10, false)
        );
    }

    @ParameterizedTest
    @MethodSource("providePullInHumanCases")
    void testPullInHuman(boolean isOpened, int fordStrength, int zaphodStrength, boolean shouldPullIn) {
        kamorka.setOpened(isOpened);
        Ford.setStrenght(fordStrength);
        Zaphod.setStrenght(zaphodStrength);

        if (shouldPullIn) {
            assertDoesNotThrow(() -> Ford.pullHumanInKamorka(Zaphod, kamorka));
        } else {
            assertThrows(IllegalArgumentException.class, () -> Ford.pullHumanInKamorka(Zaphod, kamorka));
        }
    }

    static Stream<Arguments> provideDetectComputerEquipmentCases() {
        return Stream.of(
                Arguments.of(true, Amount.Full, true),
                Arguments.of(true, Amount.Empty, false),
                Arguments.of(false, Amount.Full, false),
                Arguments.of(false, Amount.Empty, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDetectComputerEquipmentCases")
    void testDetectComputerEquipmentInOpenedKamorka(boolean isOpened, Amount amount, boolean shouldDetect) {
        kamorka.setOpened(isOpened);
        computerEquipment.setAmount(amount);

        if (shouldDetect) {
            assertDoesNotThrow(() -> Ford.detectComputerEquipmentInKamorka(kamorka));
        } else {
            assertThrows(IllegalArgumentException.class, () -> Ford.detectComputerEquipmentInKamorka(kamorka));
        }
    }
}
