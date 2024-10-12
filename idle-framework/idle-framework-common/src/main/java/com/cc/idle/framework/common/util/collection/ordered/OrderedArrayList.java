package com.cc.idle.framework.common.util.collection.ordered;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 在添加元素时，会根据元素的排序方式进行排序
 *
 * @param <T>
 */
public class OrderedArrayList<T> {
    private final ArrayList<T> elementData = new ArrayList<>();
    private final Comparator<T> comparator;

    /**
     * 创建时需要指定排序方式
     *
     * @param comparator
     */
    public OrderedArrayList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * 在列表中查找排序位置
     *
     * @param t
     * @return
     */
    public int findOrder(T t) {
        if (t == null) {
            return 0;
        }
        int index = 0;
        for (T elementDatum : elementData) {
            index++;
            int compare = comparator.compare(t, elementDatum);
            if (compare >= 0) {
                break;
            }
        }
        return index;
    }

    public void add(T t) {
        int order = findOrder(t);
        elementData.add(order, t);
    }

    public void remove(T t) {
        elementData.remove(t);
    }

    public ArrayList<T> getElements() {
        return elementData;
    }

}
