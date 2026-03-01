
# 💰 MoneyAmount

[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](#)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](#)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)](#)
[![Precision](https://img.shields.io/badge/Precision-BigDecimal-orange.svg)](#)
[![Thread Safe](https://img.shields.io/badge/Thread-Safe-success.svg)](#)

An immutable, precision-safe monetary value object for Java applications.

`MoneyAmount` eliminates floating-point precision errors by wrapping `BigDecimal` in a controlled, immutable domain object with consistent rounding rules.

Designed for financial, billing, invoicing, accounting, trading, and general-purpose monetary calculations.

---

## ✨ Features

* ✅ Immutable & Thread-Safe
* ✅ Fixed Internal Precision
* ✅ Centralized Rounding Policy
* ✅ No Floating-Point Leakage
* ✅ Comparable & Serializable
* ✅ Zero External Dependencies

---

## ⚙️ Precision Model

| Purpose                 | Scale      | Rounding |
| ----------------------- | ---------- | -------- |
| Internal calculations   | 6 decimals | HALF_UP  |
| Currency storage/output | 3 decimals | HALF_UP  |

This prevents cumulative rounding drift while maintaining correct currency formatting.

---

## 📦 Installation

### Option 1 — Copy Class

Add:

```text
util.money.MoneyAmount
```

into your project.

### Option 2 — Package as JAR

Compile and distribute as a lightweight standalone module.

---

# 🚀 Quick Start

## Create MoneyAmount

```java
MoneyAmount amount = MoneyAmount.of("1250.75");
```

```java
MoneyAmount amount = MoneyAmount.of(1250.75);
```

```java
MoneyAmount zero = MoneyAmount.zero();
```

---

# ➕ Arithmetic

```java
MoneyAmount total = price.add(tax);

MoneyAmount balance = total.subtract(payment);

MoneyAmount result = amount.multiply(1.5);

MoneyAmount unitPrice = total.divide(10);

MoneyAmount vat = amount.percent(15);
```

---

# 🔗 Chaining Example

```java
MoneyAmount total =
        price
            .multiply(quantity)
            .add(tax)
            .subtract(discount);
```

All operations return new immutable instances.

---

# 💾 Database Integration

For SQL columns like:

```sql
DECIMAL(19,3)
```

Use:

```java
preparedStatement.setBigDecimal(1, amount.toCurrency());
```

Never use:

```java
preparedStatement.setDouble(...)
```

---

# 🖥 Display Formatting

```java
String display = amount.format();
```

Output example:

```
1250.750
```

---

# 🔎 Comparison

```java
if (amount.isNegative()) {
    // handle deficit
}

if (amount.isZero()) {
    // handle zero case
}
```

---

# 🛡 Why Not double?

Floating-point types introduce precision errors:

```java
double x = 0.1 + 0.2; // 0.30000000000000004
```

`MoneyAmount` guarantees deterministic financial precision using `BigDecimal`.

---

# 🧠 Design Philosophy

* Internal precision > storage precision
* Rounding centralized
* Arithmetic is immutable
* Safe for concurrent environments
* No implicit scale changes

---

# 📊 Equality Behavior

Numeric equality ignores scale:

```java
MoneyAmount.of("100.0")
    .equals(MoneyAmount.of("100.000"));
```

Returns:

```
true
```

---

# 🏗 Project Structure

```
util/
 └── money/
      └── MoneyAmount.java
```

---

# 🔮 Roadmap

* [ ] Multi-currency support
* [ ] Currency enum binding
* [ ] Audit-trace calculation mode
* [ ] Non-negative Money variant
* [ ] Maven Central distribution

---

# 📄 License

MIT License (recommended)

You are free to use, modify, and distribute.

---

# ⭐ Contributing

Pull requests are welcome.
If you find precision edge cases or improvement opportunities, feel free to open an issue.

---

# 🏁 Summary

`MoneyAmount` provides:

* Precision-safe monetary calculations
* Immutable design
* Consistent rounding
* Lightweight integration
* Enterprise-grade safety

---

