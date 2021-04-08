package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task1.collection.MyList;
import task1.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyListTest {
    private MyList<Product> products;

    @BeforeEach
    void initProducts() {
        products = new MyList<>();
    }

    @Test
    @DisplayName("test size() after initialization")
    void testSizeAfterInit(){
        assertEquals(0,products.size(),"should return 0");
    }

    @Test
    @DisplayName("test isEmpty()")
    void testIsEmpty(){
        assertTrue(products.isEmpty(),"list must be empty");
    }

    @Test
    @DisplayName("test add method. add element on one to list")
    void testAddingOneElementShouldReturnTrue(){
        Product product=new Product();
        product.setName("Pr");
        product.setPrice(BigDecimal.valueOf(122));
        product.setManufactureDate(LocalDate.of(2021,1,1));
        assertTrue(products.add(product));
    }

    @Test
    void testSizeAfterAddingElement(){
        products.add(null);
        assertEquals(1,products.size());
    }

    @Test
    @DisplayName("test whether add method increases capacity of array in the list")
    void testMemoryAllocationInAdd(){
        for (int i = 0; i < 11; i++) {
            products.add(null);
        }
        assertEquals(11,products.size());
    }
}