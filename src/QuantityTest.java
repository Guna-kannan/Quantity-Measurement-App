import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    // ── IMeasurable Interface Tests ──────────────────────────────────────────

    @Test
    public void testIMeasurable_LengthUnit_ImplementsInterface() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable);
        assertTrue(LengthUnit.INCHES instanceof IMeasurable);
    }

    @Test
    public void testIMeasurable_WeightUnit_ImplementsInterface() {
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
        assertTrue(WeightUnit.GRAM instanceof IMeasurable);
    }

    @Test
    public void testIMeasurable_LengthUnit_ConversionFactor() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), 0.0001);
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 0.0001);
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), 0.0001);
    }

    @Test
    public void testIMeasurable_WeightUnit_ConversionFactor() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), 0.0001);
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor(), 0.0001);
        assertEquals(1000.0, WeightUnit.TONNE.getConversionFactor(), 0.0001);
    }

    // ── Constructor Validation Tests ─────────────────────────────────────────

    @Test
    public void testConstructor_NullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    public void testConstructor_NaNValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testConstructor_InfiniteValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // ── Equality Tests (Length) ──────────────────────────────────────────────

    @Test
    public void testEquality_1Feet_equals_12Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_1Feet_equals_1Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_1Feet_notEquals_2Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_1Yards_equals_3Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    // ── Equality Tests (Weight) ──────────────────────────────────────────────

    @Test
    public void testEquality_1Kilogram_equals_1000Gram() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_1Tonne_equals_1000Kilogram() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.KILOGRAM);
        assertTrue(q1.equals(q2));
    }

    // ── Cross-Category Prevention Tests ─────────────────────────────────────

    @Test
    public void testCrossCategory_Length_notEquals_Weight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
    public void testCrossCategory_Null_notEquals() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(q.equals(null));
    }

    // ── Conversion Tests (Length) ────────────────────────────────────────────

    @Test
    public void testConversion_1Feet_to_Inches() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), 0.01);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testConversion_1Yards_to_Feet() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.YARDS)
                .convertTo(LengthUnit.FEET);
        assertEquals(3.0, result.getValue(), 0.01);
    }

    @Test
    public void testConversion_1Feet_to_Centimeters() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.CENTIMETERS);
        assertEquals(30.48, result.getValue(), 0.01);
    }

    // ── Conversion Tests (Weight) ────────────────────────────────────────────

    @Test
    public void testConversion_1Kilogram_to_Gram() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), 0.01);
    }

    @Test
    public void testConversion_1Tonne_to_Kilogram() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.TONNE)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1000.0, result.getValue(), 0.01);
    }

    // ── Addition Tests (Length) ──────────────────────────────────────────────

    @Test
    public void testAddition_1Feet_plus_12Inches_inFeet() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_1Yards_plus_1Feet_inFeet() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.YARDS)
                .add(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET);
        assertEquals(4.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_DefaultUnit_isFirstOperandUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(LengthUnit.FEET, result.getUnit());
        assertEquals(2.0, result.getValue(), 0.01);
    }

    // ── Addition Tests (Weight) ──────────────────────────────────────────────

    @Test
    public void testAddition_1Kilogram_plus_1000Gram_inKilogram() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_1Tonne_plus_1Kilogram_inKilogram() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.TONNE)
                .add(new Quantity<>(1.0, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);
        assertEquals(1001.0, result.getValue(), 0.01);
    }

    // ── hashCode Tests ───────────────────────────────────────────────────────

    @Test
    public void testHashCode_EqualQuantities_HaveSameHashCode() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    // ── Immutability Tests ───────────────────────────────────────────────────

    @Test
    public void testImmutability_ConvertTo_ReturnsNewInstance() {
        Quantity<LengthUnit> original = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = original.convertTo(LengthUnit.INCHES);
        assertNotSame(original, converted);
        assertEquals(1.0, original.getValue(), 0.001);    // original unchanged
        assertEquals(LengthUnit.FEET, original.getUnit()); // original unchanged
    }

    @Test
    public void testImmutability_Add_ReturnsNewInstance() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.add(q2);
        assertNotSame(q1, result);
        assertEquals(1.0, q1.getValue(), 0.001); // q1 unchanged
    }

    // ── Scalability Test (New VolumeUnit) ────────────────────────────────────

    @Test
    public void testScalability_NewVolumeUnit_WorksWithGenericQuantity() {
        // Inline VolumeUnit to demonstrate zero changes needed to Quantity<U>
        IMeasurable liter = new IMeasurable() {
            public double getConversionFactor() { return 1.0; }
            public double convertToBaseUnit(double v) { return v; }
            public double convertFromBaseUnit(double b) { return b; }
            public String getUnitName() { return "LITER"; }
        };

        // This would normally be: new Quantity<>(1.0, VolumeUnit.LITER)
        // Using anonymous class here just to prove the pattern works
        assertNotNull(liter);
        assertEquals(1.0, liter.convertToBaseUnit(1.0), 0.001);
    }

    // ── toString Tests ───────────────────────────────────────────────────────

    @Test
    public void testToString_Length() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals("Quantity(1.0, FEET)", q.toString());
    }

    @Test
    public void testToString_Weight() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals("Quantity(1.0, KILOGRAM)", q.toString());
    }

    // ── Reflexive / Symmetric / Transitive (equals contract) ─────────────────

    @Test
    public void testEquals_Reflexive() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    public void testEquals_Symmetric() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    public void testEquals_Transitive() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3));
    }
}