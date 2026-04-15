import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        QuantityMeasurementApp q1 = new QuantityMeasurementApp(1.0);
        QuantityMeasurementApp q2 = new QuantityMeasurementApp(1.0);
        QuantityMeasurementApp q3 = new QuantityMeasurementApp(2.0);

        // Test 1: Same values
        System.out.println("Test 1 (1ft == 1ft): " + q1.equals(q2)); // true

        // Test 2: Different values
        System.out.println("Test 2 (1ft == 2ft): " + q1.equals(q3)); // false

        // Test 3: Same reference
        System.out.println("Test 3 (same object): " + q1.equals(q1)); // true

        // Test 4: Null check
        System.out.println("Test 4 (null): " + q1.equals(null)); // false

        // Test 5: Different type
        System.out.println("Test 5 (different type): " + q1.equals("test")); // false
    }
}