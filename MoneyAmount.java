package util.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Immutable financial money object.
 *
 * Internal scale: 6
 * Currency scale: 3
 * Rounding: HALF_UP
 *
 * This class prevents floating precision bugs in payroll systems.
 */
public final class MoneyAmount
        implements Comparable<MoneyAmount>, Serializable {

    private static final long serialVersionUID = 1L;

    /* ================= CONFIG ================= */

    private static final int INTERNAL_SCALE = 6;
    private static final int CURRENCY_SCALE = 3;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    private final BigDecimal value;

    /* ================= CONSTRUCTOR ================= */

    private MoneyAmount(BigDecimal value) {
        this.value = value.setScale(INTERNAL_SCALE, ROUNDING);
    }

    /* ================= FACTORY ================= */

    public static MoneyAmount zero() {
        return new MoneyAmount(BigDecimal.ZERO);
    }

    public static MoneyAmount of(String val) {
        Objects.requireNonNull(val, "Money value cannot be null");
        return new MoneyAmount(new BigDecimal(val));
    }

    public static MoneyAmount of(double val) {
        return new MoneyAmount(BigDecimal.valueOf(val));
    }

    public static MoneyAmount of(BigDecimal val) {
        Objects.requireNonNull(val, "Money value cannot be null");
        return new MoneyAmount(val);
    }

    /* ================= ACCESSORS ================= */

    public BigDecimal toBigDecimal() {
        return value;
    }

    public BigDecimal toCurrency() {
        return value.setScale(CURRENCY_SCALE, ROUNDING);
    }

    public String format() {
        return toCurrency().toPlainString();
    }

    /* ================= ARITHMETIC ================= */

    public MoneyAmount add(MoneyAmount other) {
        return new MoneyAmount(this.value.add(other.value));
    }

    public MoneyAmount subtract(MoneyAmount other) {
        return new MoneyAmount(this.value.subtract(other.value));
    }

    public MoneyAmount multiply(double multiplier) {
        return new MoneyAmount(this.value.multiply(BigDecimal.valueOf(multiplier)));
    }

    public MoneyAmount multiply(BigDecimal multiplier) {
        return new MoneyAmount(this.value.multiply(multiplier));
    }

    public MoneyAmount divide(double divisor) {
        return new MoneyAmount(
                this.value.divide(BigDecimal.valueOf(divisor), INTERNAL_SCALE, ROUNDING)
        );
    }

    public MoneyAmount divide(BigDecimal divisor) {
        return new MoneyAmount(
                this.value.divide(divisor, INTERNAL_SCALE, ROUNDING)
        );
    }

    public MoneyAmount percent(double percent) {
        return new MoneyAmount(
                this.value
                        .multiply(BigDecimal.valueOf(percent))
                        .divide(BigDecimal.valueOf(100), INTERNAL_SCALE, ROUNDING)
        );
    }

    /* ================= COMPARISON ================= */

    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isNegative() {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    @Override
    public int compareTo(MoneyAmount other) {
        return this.value.compareTo(other.value);
    }

    /* ================= OVERRIDES ================= */

    @Override
    public String toString() {
        return format();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyAmount)) return false;
        MoneyAmount that = (MoneyAmount) o;
        return value.compareTo(that.value) == 0;
    }

    @Override
    public int hashCode() {
        return value.stripTrailingZeros().hashCode();
    }
}
