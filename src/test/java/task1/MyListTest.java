package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task1.collection.MyList;
import task1.entities.Product;

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
}