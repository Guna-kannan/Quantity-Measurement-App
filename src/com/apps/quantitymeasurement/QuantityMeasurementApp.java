package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public enum LengthUnit {
            FEET(12.0),
            INCHES(1.0),
            YARDS(36.0),
            CENTIMETERS(0.393701);

            private final double factor;

            LengthUnit(double factor) {
                this.factor = factor;
            }

            public double getFactor() {
                return factor;
            }
        }

        public Length(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        // Convert to base (inches)
        private double toBaseUnit() {
            return value * unit.getFactor();
        }

        // -------- UC5 (reuse) --------
        public static double convert(double value, LengthUnit from, LengthUnit to) {
            if (from == null || to == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double base = value * from.getFactor();
            return base / to.getFactor();
        }

        // -------- UC6: ADD METHOD --------
        public Length add(Length other) {
            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            // convert both to base
            double sumBase = this.toBaseUnit() + other.toBaseUnit();

            // convert back to THIS unit
            double result = sumBase / this.unit.getFactor();

            return new Length(result, this.unit);
        }

        // Optional static version
        public static Length add(Length l1, Length l2) {
            if (l1 == null || l2 == null)
                throw new IllegalArgumentException("Lengths cannot be null");

            return l1.add(l2);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // Demo
    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        System.out.println("1 ft + 12 in = " + result);
    }
}