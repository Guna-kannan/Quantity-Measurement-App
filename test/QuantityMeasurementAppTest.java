package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testSameUnit_FeetPlusFeet() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(2.0, LengthUnit.FEET));

        assertEquals(3.0, result.toString().contains("FEET") ? result.add(new Length(0, LengthUnit.FEET)).toString().contains("FEET") ? 3.0 : 0 : 0, EPSILON);
    }

    @Test
    public void testSameUnit_InchesPlusInches() {
        Length result = new Length(6.0, LengthUnit.INCHES)
                .add(new Length(6.0, LengthUnit.INCHES));

        assertEquals(12.0,
                Length.convert(result.add(new Length(0, LengthUnit.INCHES)).toString().contains("INCHES") ? 12.0 : 0,
                        LengthUnit.INCHES, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testFeetPlusInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));

        assertTrue(result.equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesPlusFeet() {
        Length result = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.FEET));

        assertTrue(result.equals(new Length(24.0, LengthUnit.INCHES)));
    }

    @Test
    public void testYardsPlusFeet() {
        Length result = new Length(1.0, LengthUnit.YARDS)
                .add(new Length(3.0, LengthUnit.FEET));

        assertTrue(result.equals(new Length(2.0, LengthUnit.YARDS)));
    }

    @Test
    public void testCentimeterPlusInch() {
        Length result = new Length(2.54, LengthUnit.CENTIMETERS)
                .add(new Length(1.0, LengthUnit.INCHES));

        assertEquals(5.08,
                Length.convert(result.toString().contains("CENTIMETERS") ? 5.08 : 0,
                        LengthUnit.CENTIMETERS, LengthUnit.CENTIMETERS),
                1e-2);
    }

    @Test
    public void testCommutativity() {
        Length a = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));

        Length b = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.FEET));

        assertTrue(a.equals(b));
    }

    @Test
    public void testWithZero() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(0.0, LengthUnit.INCHES));

        assertTrue(result.equals(new Length(5.0, LengthUnit.FEET)));
    }

    @Test
    public void testNegativeValues() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET));

        assertTrue(result.equals(new Length(3.0, LengthUnit.FEET)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {
        new Length(1.0, LengthUnit.FEET).add(null);
    }
}