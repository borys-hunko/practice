package task1.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Alcohol extends Beverage{
    private double alcoholContent;
    private int minimumAge;

    public Alcohol() {
    }

    public Alcohol(String name,
                   BigDecimal price,
                   LocalDate manufactureDate,
                   LocalDate expirationDate,
                   double nutritionalValue,
                   double volume,
                   double alcoholContent,
                   int minimumAge) {
        super(name, price, manufactureDate, expirationDate, nutritionalValue, volume);
        this.alcoholContent = alcoholContent;
        this.minimumAge = minimumAge;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                "alcoholContent=" + alcoholContent +
                ", minimumAge=" + minimumAge +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Alcohol that = (Alcohol) o;
        return Double.compare(that.alcoholContent, alcoholContent) == 0 && minimumAge == that.minimumAge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alcoholContent, minimumAge);
    }
}
