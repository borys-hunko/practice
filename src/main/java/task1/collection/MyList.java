package task1.collection;

import java.util.*;

public class MyList<T> implements List<T> {
    private static final int NOT_FOUND = -1;
    /**
     * array which contains elements
     */
    private T array[];
    /**
     * number of elements in the list
     */
    private int numOfElements;
    /**
     * initial capacity of array
     */
    private static final int INITIAL_CAPACITY = 10;

    /**
     * initialize array and numOfElements
     */
    public MyList() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        numOfElements = 0;
    }

    /**
     * @return number of elements in the list
     */
    @Override
    public int size() {
        return numOfElements;
    }

    /**
     * @return if there is elements in the list
     */
    @Override
    public boolean isEmpty() {
        return numOfElements == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();

    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();

    }


    /**
     * @param t element to add to the list
     * @return true if method added element successfully
     */
    @Override
    public boolean add(T t) {
        add(numOfElements, t);
        return true;
    }

    /**
     * @param array   array to be increased in size
     * @param newSize new size of array
     */
    private void allocateMemoryForArray(T[] array, int newSize) {
        this.array = Arrays.copyOf(array, newSize);
    }

    @Override
    public boolean remove(Object o) {
        int removeIndex = indexOf(o);
        if (removeIndex == NOT_FOUND) {
            return false;
        }
        remove(removeIndex);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();

    }

    /**
     * return element with given index
     *
     * @param index index of needed elemnt
     * @return element with given index
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * check index for being more >=0 and less then size
     *
     * @param index index to be checked
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= numOfElements) {
            throw new IndexOutOfBoundsException("no element with index " + index);
        }
    }

    /**
     * set new value for element with given index
     *
     * @param index   index of element with new value
     * @param element new value
     * @return old value of element with given index
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = array[index];
        array[index] = element;
        return oldValue;
    }

    /**
     * add element on particular position
     *
     * @param index   index of array cell on which elemnt will be inserted
     * @param element element to be added
     */
    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        if (numOfElements == array.length) {
            allocateMemoryForArray(array, (int) (array.length * 1.5));
        }
        System.arraycopy(array, index,
                array, index + 1,
                numOfElements - index);
        array[index] = element;
        ++numOfElements;
    }

    /**
     * check if index is correct for add methods
     *
     * @param index index of element
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > numOfElements) {
            throw new IndexOutOfBoundsException("there is no element with index " + index);
        }
    }

    /**
     * remove item with given index
     *
     * @param index index of value to be removed
     * @return value removed element
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = array[index];
        int newSize = array.length;
        if (array.length > INITIAL_CAPACITY) {
            --newSize;
        }
        if (index < numOfElements - 1) {
            int lengthOfSubArray = newSize - index - 1;//length of copied sub-array
            System.arraycopy(array, index + 1, array, index, lengthOfSubArray);
        }
        array[numOfElements] = null;
        --numOfElements;
        return oldValue;
    }

    /**
     * search for items and return its index
     *
     * @param o sought value
     * @return index of first appearance of element equal to the passed one
     * or -1 if there is no such element
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < numOfElements; ++i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * search for items and return last index of its appearance
     *
     * @param o sought value
     * @return last index of element equal to the passed one
     * or -1 if there is no such element
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = numOfElements - 1; i >= 0; --i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();

    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();

    }
}

