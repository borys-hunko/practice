package task1.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private String name;
    private BigDecimal price;
    private LocalDate manufactureDate;

    public Product() {
    }

    public Product(String name, BigDecimal price, LocalDate manufactureDate) {
        this.name = name;
        this.price = price;//set precision for price
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
        this.price = price;//set precision for price
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


}
