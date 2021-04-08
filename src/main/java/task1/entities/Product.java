package task1.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private String name;
    private BigDecimal price;
    private LocalDate manufactureDate;

    /**
     * constant which defines precision of price
     * */
    private static final int PRICE_PRECISION=2;

    public Product() {
    }

    public Product(String name, BigDecimal price, LocalDate manufactureDate) {
        this.name = name;
        this.price = price.movePointRight(PRICE_PRECISION);//set precision for price
        this.manufactureDate = manufactureDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.movePointRight(PRICE_PRECISION);//set precision for price
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", manufactureDate=" + manufactureDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return name.equals(product.name) && price.equals(product.price) && manufactureDate.equals(product.manufactureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, manufactureDate);
    }
}
