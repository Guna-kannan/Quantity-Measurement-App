package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Length {

        private final double value;
        private final LengthUnit unit;

        // Enum (same as UC4)
        public enum LengthUnit {
            FEET(12.0),
            INCHES(1.0),
            YARDS(36.0),
            CENTIMETERS(0.393701);

            private final double conversionFactor;

            LengthUnit(double conversionFactor) {
                this.conversionFactor = conversionFactor;
            }

            public double getConversionFactor() {
                return conversionFactor;
            }
        }

        // Constructor
        public Length(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (inches)
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        // -------- UC5: STATIC CONVERT METHOD --------
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            // Convert to base (inches)
            double baseValue = value * source.getConversionFactor();

            // Convert to target
            return baseValue / target.getConversionFactor();
        }

        // Instance conversion
        public Length convertTo(LengthUnit targetUnit) {
            double newValue = convert(this.value, this.unit, targetUnit);
            return new Length(newValue, targetUnit);
        }

        // Equals (same as UC4)
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

    // Demo methods
    public static void demonstrateLengthConversion(double value,
                                                   Length.LengthUnit from,
                                                   Length.LengthUnit to) {

        double result = Length.convert(value, from, to);
        System.out.println(value + " " + from + " = " + result + " " + to);
    }

    public static void demonstrateLengthConversion(Length length,
                                                   Length.LengthUnit to) {

        Length converted = length.convertTo(to);
        System.out.println(length + " = " + converted);
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);

        Length l = new Length(36.0, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(l, Length.LengthUnit.YARDS);
    }
}
