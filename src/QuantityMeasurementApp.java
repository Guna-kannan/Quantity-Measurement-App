public class QuantityMeasurementApp {

    // Generic equality demonstration — works for any IMeasurable category
    public <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Equality Check:");
        System.out.println("  " + q1 + " == " + q2 + " → " + q1.equals(q2));
        System.out.println();
    }

    // Generic conversion demonstration
    public <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {
        System.out.println("Conversion:");
        System.out.println("  " + q + " → " + q.convertTo(targetUnit));
        System.out.println();
    }

    // Generic addition demonstration
    public <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println("Addition:");
        System.out.println("  " + q1 + " + " + q2 + " in " + targetUnit.getUnitName()
                + " → " + q1.add(q2, targetUnit));
        System.out.println();
    }

    // Cross-category prevention demonstration
    public void demonstrateCrossCategoryPrevention() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println("Cross-Category Prevention:");
        // equals() uses Quantity<?> raw type to allow comparison between different categories
        System.out.println("  1.0 FEET == 1.0 KILOGRAM → " + length.equals(weight));
        System.out.println("  (Compiler also prevents: Quantity<LengthUnit> cannot be assigned to Quantity<WeightUnit>)");
        System.out.println();
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = new QuantityMeasurementApp();

        System.out.println("====== LENGTH OPERATIONS (UC1–UC8 preserved) ======");

        // Equality
        app.demonstrateEquality(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCHES)
        );

        // Conversion
        app.demonstrateConversion(
                new Quantity<>(1.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        // Addition
        app.demonstrateAddition(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        System.out.println("====== WEIGHT OPERATIONS (UC9 preserved) ======");

        // Equality
        app.demonstrateEquality(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM)
        );

        // Conversion
        app.demonstrateConversion(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                WeightUnit.GRAM
        );

        // Addition
        app.demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM),
                WeightUnit.KILOGRAM
        );

        System.out.println("====== CROSS-CATEGORY SAFETY ======");
        app.demonstrateCrossCategoryPrevention();
    }
}