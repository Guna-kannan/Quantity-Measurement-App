import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {


    @Test
    void givenSameFeetValues_ShouldReturnTrue() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(1.0);

        assertTrue(q1.equals(q2));
    }

    @Test
    void givenDifferentFeetValues_ShouldReturnFalse() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(2.0);

        assertFalse(q1.equals(q2));
    }

    @Test
    void givenSameReference_ShouldReturnTrue() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0);

        assertTrue(q1.equals(q1));
    }

    @Test
    void givenNull_ShouldReturnFalse() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0);

        assertFalse(q1.equals(null));
    }

    @Test
    void givenDifferentObjectType_ShouldReturnFalse() {
        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0);

        assertFalse(q1.equals("test"));
    }
}