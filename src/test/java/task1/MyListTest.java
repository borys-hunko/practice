package task1;

import org.junit.jupiter.api.BeforeEach;
import task1.collection.MyList;
import task1.entities.Product;

public class MyListTest {
    private MyList<Product> products;

    @BeforeEach
    void initProducts() {
        products = new MyList<>();
    }


}