package org.example;

import org.example.Exception.IndexOutOfSizeException;
import org.example.Exception.ItemIsNullException;
import org.example.Exception.ItemNotFoundException;

import java.util.Arrays;

public class StringListService implements StringList{

    private final String[] massive;
    private int size;


    public StringListService () {
        massive = new String[6];
    }
    public StringListService (int DEFAULT_MASSIVE_SIZE) {
        massive = new String[DEFAULT_MASSIVE_SIZE];
    }
    private void checkItemIsNull (String item) {
        if (item == null) {
            throw new ItemIsNullException("Item не может быть Null");
        }
    }
    private void checkIndexOutOfSize (int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfSizeException("Индекс вне массива");
        }
    }

    @Override
    public String add(String item) {
        massive[size] = item;
        checkItemIsNull(item);
        size ++;
        return item;
    }

    @Override
    public String add(int index, String item) {
            checkItemIsNull(item);
            if (index == size) {
                massive[size++] = item;
                return item;
            }
            System.arraycopy(massive,index,massive,index + 1,size - index);
            massive[index] = item;
            size++;
            return item;
        //massive[index] = item;
        //if (index < 0 || index >= size) {
            //throw new IndexOutOfSizeException("Индекс вне массива");
        //}
        //else if (item == null) {
            //throw new ItemIsNullException("Item не может быть Null");
        //}
        //return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndexOutOfSize(index);
        checkItemIsNull(item);
        massive[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkItemIsNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ItemNotFoundException("Элемент не найден");
        }
        if (index == size) {
            massive[size--] = null;
            return item;
        }
        System.arraycopy(massive,index + 1,massive, index, size - index);
        size--;

        return item;
    }

    @Override
    public String remove(int index) {
        checkIndexOutOfSize(index);
        if (index == -1) {
            throw new ItemNotFoundException("Элемент не найден");
        }
        String item = massive[index];
        if (index == size) {
            massive[size--] = null;
            return item;
        }
        System.arraycopy(massive,index + 1,massive, index, size - index);
        size--;

        return item;
    }

    private int containsItem (String item) {
        int itemIndex = -1;
        for (int i = 0; i < size; i++) {
            if (massive[i].equals(item)) {
                itemIndex = i;
            }
        }
        return itemIndex;
    }
    @Override
    public boolean contains(String item) {
        int itemIndex = containsItem(item);
        if (item == null) {
            throw new ItemIsNullException("Item не может быть Null");
        }
        if (itemIndex != -1) {
            return true;
        } else {
            throw new ItemNotFoundException("Элемент не найден");
        }
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            String s = massive[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size; i >= 0; i--) {
            String s = massive[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndexOutOfSize(index);
        return massive[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        for (int i = 0; i < size; i++) {
            if (this.get(i) != otherList.get(i))
                return false;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(massive, size);
    }

    @Override
    public String toString() {
        return "StringListService{" +
                "massive=" + Arrays.toString(massive) +
                ", size=" + size +
                '}';
    }
}
