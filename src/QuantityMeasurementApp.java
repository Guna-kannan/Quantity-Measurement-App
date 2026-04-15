public class QuantityMeasurementApp {

    private double value; // value in feet

    // Constructor
    public QuantityMeasurementApp(double value) {
        this.value = value;
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {

        // Same reference
        if (this == obj) {
            return true;
        }

        // Null check
        if (obj == null) {
            return false;
        }

        // Type check
        if (!(obj instanceof QuantityMeasurementApp)) {
            return false;
        }

        // Type casting
        QuantityMeasurementApp other = (QuantityMeasurementApp) obj;

        // Compare double values safely
        return Double.compare(this.value, other.value) == 0;
    }
}