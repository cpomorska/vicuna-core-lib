package com.scprojekt.domain.core.model.banking.dto;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * Value object representing money with currency.
 * Provides immutable monetary operations with proper currency handling.
 */
@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {
    
    private BigDecimal amount;
    private String currency;
    
    private Money(BigDecimal amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }
        
        validateCurrency(currency);
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency.toUpperCase();
    }
    
    /**
     * Creates a Money instance with the specified amount and currency
     */
    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }
    
    /**
     * Creates a Money instance from double value
     */
    public static Money of(double amount, String currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }
    
    /**
     * Creates a Money instance from string value
     */
    public static Money of(String amount, String currency) {
        return new Money(new BigDecimal(amount), currency);
    }
    
    /**
     * Creates a zero Money instance with the specified currency
     */
    public static Money zero(String currency) {
        return new Money(BigDecimal.ZERO, currency);
    }
    
    /**
     * Creates a Money instance with value 1 in the specified currency
     */
    public static Money one(String currency) {
        return new Money(BigDecimal.ONE, currency);
    }
    
    /**
     * Adds another Money instance to this one
     */
    public Money add(Money other) {
        validateSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }
    
    /**
     * Subtracts another Money instance from this one
     */
    public Money subtract(Money other) {
        validateSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }
    
    /**
     * Multiplies this Money by a BigDecimal multiplier
     */
    public Money multiply(BigDecimal multiplier) {
        if (multiplier == null) {
            throw new IllegalArgumentException("Multiplier cannot be null");
        }
        return new Money(this.amount.multiply(multiplier), this.currency);
    }
    
    /**
     * Multiplies this Money by a double multiplier
     */
    public Money multiply(double multiplier) {
        return multiply(BigDecimal.valueOf(multiplier));
    }
    
    /**
     * Divides this Money by a BigDecimal divisor
     */
    public Money divide(BigDecimal divisor) {
        if (divisor == null) {
            throw new IllegalArgumentException("Divisor cannot be null");
        }
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Money(this.amount.divide(divisor, 2, RoundingMode.HALF_UP), this.currency);
    }
    
    /**
     * Divides this Money by a double divisor
     */
    public Money divide(double divisor) {
        return divide(BigDecimal.valueOf(divisor));
    }
    
    /**
     * Returns the absolute value of this Money
     */
    public Money abs() {
        return new Money(this.amount.abs(), this.currency);
    }
    
    /**
     * Returns the negated value of this Money
     */
    public Money negate() {
        return new Money(this.amount.negate(), this.currency);
    }
    
    /**
     * Checks if this Money is greater than another
     */
    public boolean isGreaterThan(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) > 0;
    }
    
    /**
     * Checks if this Money is greater than or equal to another
     */
    public boolean isGreaterThanOrEqualTo(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) >= 0;
    }
    
    /**
     * Checks if this Money is less than another
     */
    public boolean isLessThan(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) < 0;
    }
    
    /**
     * Checks if this Money is less than or equal to another
     */
    public boolean isLessThanOrEqualTo(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) <= 0;
    }
    
    /**
     * Checks if this Money is equal to another
     */
    public boolean isEqualTo(Money other) {
        if (other == null) {
            return false;
        }
        return this.currency.equals(other.currency) && 
               this.amount.compareTo(other.amount) == 0;
    }
    
    /**
     * Checks if this Money is zero
     */
    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }
    
    /**
     * Checks if this Money is positive
     */
    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }
    
    /**
     * Checks if this Money is negative
     */
    public boolean isNegative() {
        return this.amount.compareTo(BigDecimal.ZERO) < 0;
    }
    
    /**
     * Returns the minimum of this Money and another
     */
    public Money min(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) <= 0 ? this : other;
    }
    
    /**
     * Returns the maximum of this Money and another
     */
    public Money max(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) >= 0 ? this : other;
    }
    
    /**
     * Converts this Money to another currency using the provided exchange rate
     */
    public Money convertTo(String targetCurrency, BigDecimal exchangeRate) {
        if (targetCurrency == null || targetCurrency.trim().isEmpty()) {
            throw new IllegalArgumentException("Target currency cannot be empty");
        }
        if (exchangeRate == null || exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exchange rate must be positive");
        }
        
        if (this.currency.equals(targetCurrency.toUpperCase())) {
            return this; // Same currency, no conversion needed
        }
        
        validateCurrency(targetCurrency);
        BigDecimal convertedAmount = this.amount.multiply(exchangeRate);
        return new Money(convertedAmount, targetCurrency);
    }
    
    /**
     * Formats this Money as a string with currency symbol
     */
    public String format() {
        try {
            Currency curr = Currency.getInstance(this.currency);
            return curr.getSymbol() + " " + this.amount.toString();
        } catch (IllegalArgumentException e) {
            return this.currency + " " + this.amount.toString();
        }
    }
    
    /**
     * Returns a string representation without currency symbol
     */
    public String toPlainString() {
        return this.amount.toPlainString();
    }
    
    private void validateSameCurrency(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Other money cannot be null");
        }
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                String.format("Currency mismatch: %s vs %s", this.currency, other.currency)
            );
        }
    }
    
    private void validateCurrency(String currency) {
        try {
            Currency.getInstance(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency code: " + currency);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", currency, amount);
    }
    
    /**
     * Common currency constants
     */
    public static final class Currencies {
        public static final String USD = "USD";
        public static final String EUR = "EUR";
        public static final String GBP = "GBP";
        public static final String JPY = "JPY";
        public static final String CHF = "CHF";
        public static final String CAD = "CAD";
        public static final String AUD = "AUD";
        public static final String CNY = "CNY";
        
        private Currencies() {
            // Utility class
        }
    }
}
