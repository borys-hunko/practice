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

    /**
     * @param o searched value
     * @return true if list contains value, false if it doesn't
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < numOfElements; ++i) {
            if (Objects.equals(array[i], o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return iterator of current list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int position = -1;
            private boolean isRemovable = false;

            /**
             * check if list has another one element
             * */
            @Override
            public boolean hasNext() {
                return position < numOfElements - 1;
            }

            /**
             * @return next element
             * @throws NoSuchElementException in case there is no next element
             * */
            @Override
            public T next() {
                if (position + 1 == numOfElements) {
                    throw new NoSuchElementException();
                }
                isRemovable = true;
                return array[++position];
            }

            /**
             * remove element if next() was called
             * @throws NoSuchElementException in case next() wasn't called
             * */
            @Override
            public void remove() {
                if (!isRemovable)
                    throw new NoSuchElementException();
                MyList.this.remove(position);
                isRemovable = false;
                position--;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, numOfElements);
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

    /**
     * check if list contains all elements from given collection
     *
     * @param c collection to check
     * @return if list contains all elements of collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (var it : c) {
            if (!contains(it)) {
                return false;
            }
        }
        return true;
    }

    /**
     * add elements of collection in the end of the list
     * @param c collection of elements to added
     * @return true if list has changed after calling this method
     * */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(numOfElements, c);
    }

    /**
     * add elements of collection on index
     * @param c collection of elements to added
     * @param index index on which collections are added
     * @return true if list has changed after calling this method
     * */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexForAdd(index);
        T[] insertedArray = (T[]) c.toArray();
        if (insertedArray.length == 0) {
            return false;
        }
        int newNumOfElements = insertedArray.length + numOfElements;
        if (array.length < newNumOfElements) {
            allocateMemoryForArray(array, (int) (newNumOfElements * 1.5));
        }
        System.arraycopy(array, 0, array, 0, numOfElements - index);
        System.arraycopy(insertedArray, 0, array, index, insertedArray.length);
        System.arraycopy(array,
                index,
                array,
                index + insertedArray.length,
                newNumOfElements-insertedArray.length-index);
        numOfElements=newNumOfElements;
        return true;
    }

    /**
     * remove all elements containing in the passed collection
     *
     * @param c collection of elements to be removed
     * @return true if list has changed after calling this method
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return removeAllIf(c, true);
    }

    /**
     * @param c           collection containing elements
     * @param removeIfHas defines if we remove elements containing in collection
     */
    private boolean removeAllIf(Collection<?> c, boolean removeIfHas) {
        boolean hasChanged = false;
        for (var elementToRemove : c) {
            for (int j = 0; j < numOfElements; ++j) {
                if (Objects.equals(elementToRemove, array[j]) == removeIfHas) {
                    remove(j);
                    hasChanged = true;
                }
            }
        }
        return hasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeAllIf(c, false);
    }

    @Override
    public void clear() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        numOfElements = 0;
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
            if (Objects.equals(array[i], o)) {
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
            if (Objects.equals(array[i], o)) {
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

