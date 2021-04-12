package task1.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private LocalDate manufactureDate;

    public Product() {
    }

    public Product(long id, String name, BigDecimal price, LocalDate manufactureDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufactureDate = manufactureDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
