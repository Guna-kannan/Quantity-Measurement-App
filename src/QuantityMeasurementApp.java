public class QuantityMeasurementApp {

    private double value;
    private String unit; // "FEET" or "INCH"

    // Constructor
    public QuantityMeasurementApp(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    // Convert everything to inches
    private double toInches() {
        if (unit.equalsIgnoreCase("FEET")) {
            return value * 12; // 1 ft = 12 inches
        }
        return value; // already inches
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof QuantityMeasurementApp)) {
            return false;
        }

        QuantityMeasurementApp other = (QuantityMeasurementApp) obj;

        // Compare after conversion
        return Double.compare(this.toInches(), other.toInches()) == 0;
    }
}