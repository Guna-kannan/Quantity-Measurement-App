import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {


    public static void main(String[] args) {

        QuantityMeasurementApp f1 = new QuantityMeasurementApp(1.0, "FEET");
        QuantityMeasurementApp i1 = new QuantityMeasurementApp(12.0, "INCH");

        QuantityMeasurementApp f2 = new QuantityMeasurementApp(2.0, "FEET");
        QuantityMeasurementApp i2 = new QuantityMeasurementApp(24.0, "INCH");

        QuantityMeasurementApp i3 = new QuantityMeasurementApp(10.0, "INCH");

        // Test 1: 1 ft == 12 inch
        System.out.println("Test 1 (1ft == 12in): " + f1.equals(i1)); // true

        // Test 2: 2 ft == 24 inch
        System.out.println("Test 2 (2ft == 24in): " + f2.equals(i2)); // true

        // Test 3: Not equal
        System.out.println("Test 3 (1ft == 10in): " + f1.equals(i3)); // false

        // Test 4: Same reference
        System.out.println("Test 4 (same object): " + f1.equals(f1)); // true

        // Test 5: Null
        System.out.println("Test 5 (null): " + f1.equals(null)); // false
    }
}