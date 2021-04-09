package task1.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Beverage extends Product {
    private LocalDate expirationDate;
    private double nutritionalValue;
    private double volume;

    public Beverage() {
    }

    public Beverage(String name,
                    BigDecimal price,
                    LocalDate manufactureDate,
                    LocalDate expirationDate,
                    double nutritionalValue,
                    double volume) {
        super(name, price, manufactureDate);
        this.expirationDate = expirationDate;
        this.nutritionalValue = nutritionalValue;
        this.volume = volume;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(double nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "expirationDate=" + expirationDate +
                ", nutritionalValue=" + nutritionalValue +
                ", volume=" + volume +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beverage beverage = (Beverage) o;
        return Double.compare(beverage.nutritionalValue, nutritionalValue) == 0 && Double.compare(beverage.volume, volume) == 0 && Objects.equals(expirationDate, beverage.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate, nutritionalValue, volume);
    }
}
