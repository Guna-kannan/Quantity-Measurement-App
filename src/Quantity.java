import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // Constructor
    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // equals() — with cross-category prevention
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Quantity)) return false;

        Quantity<?> that = (Quantity<?>) obj;

        // Prevent cross-category comparison (e.g., FEET vs KILOGRAM)
        if (!this.unit.getClass().equals(that.unit.getClass())) return false;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = that.unit.convertToBaseUnit(that.value);

        return Double.compare(thisBase, thatBase) == 0;
    }

    // hashCode() — consistent with equals()
    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(unit.getClass(), baseValue);
    }

    // convertTo() — returns a new Quantity in the target unit
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        double rounded = Math.round(converted * 100.0) / 100.0;
        return new Quantity<>(rounded, targetUnit);
    }

    // add() — adds two quantities, result in first operand's unit
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    // add() — adds two quantities, result in specified target unit
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double resultInTarget = targetUnit.convertFromBaseUnit(base1 + base2);
        double rounded = Math.round(resultInTarget * 100.0) / 100.0;
        return new Quantity<>(rounded, targetUnit);
    }

    // toString()
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}