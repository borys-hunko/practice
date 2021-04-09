package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task1.collection.MyList;
import task1.entities.Alcohol;
import task1.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
    void testValueAddedToTheBeginningOfList() {
        Product product = new Product(
                "asd",
                BigDecimal.valueOf(2222),
                LocalDate.now()
        );
        products.add(0, product);
        assertEquals(product, products.get(0));
    }

    @Test
    void testValueAddedToTheEndOfList() {
        Product product = new Product(
                "asd",
                BigDecimal.valueOf(2222),
                LocalDate.now()
        );
        products.add(products.size(), product);
        assertEquals(product, products.get(products.size() - 1));
    }

    @Test
    @DisplayName("insert element in somewhere in the middle of the list")
    void testValueAddedToTheMiddleOfList() {
        Product product = new Product(
                "asd",
                BigDecimal.valueOf(2222),
                LocalDate.now()
        );
        products.add(3, product);
        assertEquals(product, products.get(3));
    }

    @Test
    void testAddingOnTheWrongPosition() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> products.add(10, null));
        assertEquals("there is no element with index 10", exception.getMessage());
    }

    @Test
    @DisplayName("test get method when incorrect index passed")
    void testGetOnIncorrectIndex() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> products.get(1000));
        assertEquals("no element with index 1000", exception.getMessage());
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

    @Test
    void testValueAfterSettingTheNewOne() {
        Product newValue = new Product(
                "new product",
                BigDecimal.valueOf(999.1321),
                LocalDate.of(2020, 2, 2)
        );
        products.set(0, newValue);
        assertEquals(newValue, products.get(0));
    }

    @Test
    void testPassingIncorrectIndexToSet() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> products.set(1000, null));
        assertEquals("no element with index 1000", exception.getMessage());
    }

    @Test
    void testValueReturnedByRemove() {
        Product oldValue = products.get(0);
        assertEquals(oldValue, products.remove(0));
    }

    @Test
    void testRemovingFromTheBeginning() {
        Product removedProduct = products.remove(0);
        assertNotSame(removedProduct, products.get(0));
        assertEquals(4, products.size());
    }

    @Test
    void testRemovingFromTheEnd() {
        Product removedProduct = products.remove(products.size() - 1);
        assertNotSame(removedProduct, products.get(products.size() - 1));
        assertEquals(4, products.size());
    }

    @Test
    void testRemovingFromTheMiddle() {
        Product removedProduct = products.remove(2);
        assertNotSame(removedProduct, products.get(2));
        assertEquals(4, products.size());
    }

    @Test
    @DisplayName("remove all elements from the list. its capacity should be 10")
    void testShrinkingOfCapacityAfterRemove() {
        for (int i = 0; i < products.size(); ) {
            products.remove(0);
        }
        products.add(null);
        assertDoesNotThrow(() -> products.size());
    }

    @Test
    @DisplayName("pass existent value to indexOf. should return its index")
    void testIndexOfWithExistentElement() {
        Product product = products.get(2);
        assertEquals(2, products.indexOf(product));
    }

    @Test
    @DisplayName("pass non-existent value to indexOf. should return -1")
    void testIndexOfWithNonExistentElement() {
        assertEquals(-1, products.indexOf(null));
    }

    @Test
    @DisplayName("pass existent value to remove(Object o). should return true")
    void testRemovePassingExistentObject() {
        assertTrue(products.remove(products.get(2)));
    }

    @Test
    @DisplayName("pass non-existent value to remove(Object o). should return false")
    void testRemovePassingNonExistentObject() {
        assertFalse(products.remove(null));
    }

    @Test
    @DisplayName("call next(). should return first element")
    void testIteratorsNext() {
        Iterator<Product> iterator = products.iterator();
        assertEquals(products.get(0), iterator.next());
    }

    @Test
    @DisplayName("call next when list is empty. should throw exception")
    void testIteratorsNextOfEmptyList() {
        Iterator<Product> iterator = products.iterator();
        products.clear();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("call hasNext(). should return true")
    void testIteratorsHasNext() {
        Iterator<Product> iterator = products.iterator();
        assertEquals(products.get(0), iterator.next());
    }

    @Test
    @DisplayName("call hasNext when list is empty. should return false")
    void testIteratorsHasNextOfEmptyList() {
        Iterator<Product> iterator = products.iterator();
        products.clear();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("test iterators in foreach loop")
    void testIteratorInLoop() {
        int index = 0;
        for (Product p : products) {
            assertEquals(products.get(index), p);
            ++index;
        }
    }

    @Test
    @DisplayName("call next(), then call remove(). should remove first element")
    void testIteratorRemoveAfterNextInvocation() {
        Iterator<Product> iterator = products.iterator();
        Product removedProduct = iterator.next();
        iterator.remove();
        assertNotSame(removedProduct, products.get(0));
    }

    @Test
    @DisplayName("call remove() without next(). should throw exception")
    void testIteratorRemoveWithoutNextCalling() {
        Iterator<Product> iterator = products.iterator();
        assertThrows(NoSuchElementException.class, iterator::remove);
    }

    @Test
    void testContainsAllShouldReturnTrue() {
        List<Product> list = List.of(products.get(0), products.get(2));
        assertTrue(products.containsAll(list));
    }

    @Test
    void testContainsAllShouldReturnFalse() {
        List<Product> list = List.of(products.get(0), new Product());
        assertFalse(products.containsAll(list));
    }

    @Test
    void testRemoveAll() {
        List<Product> removedItems = List.of(products.get(0), products.get(2), products.get(3));
        products.removeAll(removedItems);
        for (Product p : products) {
            assertFalse(removedItems.contains(p));
        }
    }

    @Test
    @DisplayName("pass existing in the list elements to removeAll. it should return true")
    void testReturnedValueOfRemoveAllIfListHasChanged() {
        List<Product> removedItems = List.of(products.get(0), products.get(2), products.get(3));
        assertTrue(products.removeAll(removedItems));
    }

    @Test
    @DisplayName("pass non-existing in the list elements to removeAll. it should return false")
    void testReturnedValueOfRemoveAllIfListHasNotChanged() {
        List<Product> removedItems = List.of(new Product(), new Alcohol());
        assertFalse(products.removeAll(removedItems));
    }

    @Test
    void testRetainAll() {
        List<Product> items = List.of(this.products.get(0), this.products.get(2), this.products.get(3));
        this.products.retainAll(items);
        for (Product p : items) {
            assertTrue(items.contains(p));
        }
    }

    @Test
    @DisplayName("insert collection and check if size has changed")
    void testAddingAllTo() {
        List<Product> insertedProducts = List.of(new Product(),
                new Product("newProd",
                        BigDecimal.valueOf(100000),
                        LocalDate.now()
                ));
        products.addAll(2, insertedProducts);
        assertEquals(7, products.size());
    }

    @Test
    @DisplayName("check values after adding collection in the beginning")
    void testValuesAddedToTheBeginningOfList() {
        List<Product> insertedProducts = List.of(new Product(),
                new Product("newProd",
                        BigDecimal.valueOf(100000),
                        LocalDate.now()
                ));
        products.addAll(0, insertedProducts);
        for (int i = 0; i < insertedProducts.size(); ++i) {
            assertEquals(insertedProducts.get(i), products.get(i));
        }
    }

    @Test
    @DisplayName("check values after adding collection in the end")
    void testValuesAddedToTheEndOfList() {
        List<Product> insertedProducts = List.of(new Product(),
                new Product("newProd",
                        BigDecimal.valueOf(100000),
                        LocalDate.now()
                ));
        products.addAll(products.size(), insertedProducts);
        int insertedProductsIndex = insertedProducts.size() - 1;
        for (int i = products.size() - 1; i > products.size()-3; --i) {
            assertEquals(insertedProducts.get(insertedProductsIndex), products.get(i));
            insertedProductsIndex--;
        }
    }

    @Test
    @DisplayName("insert collection in somewhere in the middle of the list")
    void testValuesAddedAllToTheMiddleOfList() {
        List<Product> insertedProducts = List.of(new Product(),
                new Product("newProd",
                        BigDecimal.valueOf(100000),
                        LocalDate.now()
                ));
        products.addAll(2, insertedProducts);
        int insertedProductsIndex = 0;

        for (int i = 2; i < 2 + insertedProducts.size(); ++i) {
            assertEquals(insertedProducts.get(insertedProductsIndex), products.get(i));
            insertedProductsIndex++;
        }
    }
}
