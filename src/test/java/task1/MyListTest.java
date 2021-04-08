package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task1.collection.MyList;
import task1.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MyListTest {
    private MyList<Product> products;

    @BeforeEach
    void initProducts() {
        products = new MyList<>();
        StringBuilder productNameBuilder = new StringBuilder("product#");
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setManufactureDate(LocalDate.now());
            product.setPrice(BigDecimal.valueOf(i));
            product.setName(productNameBuilder.append(i).toString());
            products.add(product);
        }
    }

    @Test
    @DisplayName("test size() after initialization")
    void testSizeAfterInit() {
        assertEquals(5, products.size(), "should return 0");
    }

    @Test
    @DisplayName("test isEmpty()")
    void testIsEmpty() {
        assertFalse(products.isEmpty(), "list must be empty");
    }

    @Test
    @DisplayName("test add method. add element on one to list")
    void testAddingOneElementShouldReturnTrue() {
        Product product = new Product();
        product.setName("Pr");
        product.setPrice(BigDecimal.valueOf(122));
        product.setManufactureDate(LocalDate.of(2021, 1, 1));
        assertTrue(products.add(product));
    }

    @Test
    @DisplayName("test whether add method increases size of list")
    void testSizeAfterAddingElement() {
        products.add(null);
        assertEquals(6, products.size());
    }

    @Test
    @DisplayName("test whether add method increases size of list")
    void testMemoryAllocationInAdd() {
        for (int i = 0; i < 11; i++) {
            products.add(null);
        }
        assertEquals(16, products.size());
    }

    @Test
    void testAddingToTheBeginningOfList() {
        products.add(0, null);
        assertEquals(6, products.size());
    }

    @Test
    void testAddingToTheEndOfList() {
        products.add(products.size(), null);
        assertEquals(6, products.size());
    }

    @Test
    @DisplayName("insert element in somewhere in the middle of the list")
    void testAddingToTheMiddleOfList() {
        products.add(2, null);
        assertEquals(6, products.size());
    }

    @Test
    void testAddingOnTheWrongPosition() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> products.add(10, null));
        assertEquals("there is no element with index 10", exception.getMessage());
    }

    @Test
    @DisplayName("test get method when incorrect index passed")
    void testGetOnIncorrectIndex(){
        Throwable exception=assertThrows(IndexOutOfBoundsException.class,
                ()->products.get(1000));
        assertEquals("no element with index 1000",exception.getMessage());
    }

    @Test
    void testReturnedValueOfSet() {
        Product newValue = new Product(
                "new product",
                BigDecimal.valueOf(999.1321),
                LocalDate.of(2020, 2, 2)
        );
        Product oldValue = products.get(0);
        assertEquals(oldValue, products.set(0, newValue));
    }
}
