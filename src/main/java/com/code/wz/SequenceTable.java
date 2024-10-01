package com.code.wz;

/**
 * @author lizhengqiang
 * @create 2024-09-29 22:58
 * 线性表----顺序存储-----顺序表
 **/
public class SequenceTable {
    public static void main(String[] args) {
        List list = new List();
        list.initList();
        list.insert(1,0);
        list.insert(2,1);
        list.insert(3,2);
        list.insert(4,3);
        list.insert(6,1);
        list.foreach();
        System.out.println("length:"+list.getLength());
        list.remove(1);

        System.out.println();
        list.foreach();
        System.out.println("length:"+list.getLength());
    }
}

class List {
    private Integer length;

    private Object[] elementData;

    private final static int MAXSIZE = 100;

    public void initList(){
        elementData = new Object[MAXSIZE];
        length = 0;
    }

    public void destroyList(){
        elementData = null;
        length = null;
    }

    public void clearList(){
        length = 0;
    }

    public int getLength(){
        return length;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public Object get(int index){
        if (index < 0 || index >= length){
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }

    public int locateElem(Object elem){
        for (int i = 0; i < length; i++) {
            if (elementData[i].equals(elem)){
                return i;
            }
        }
        return -1;
    }

    public void insert(Object elem, int index){
        if (index < 0 || index > length || length == MAXSIZE){
            throw new IndexOutOfBoundsException();
        }
        for (int i = length - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = elem;
        length++;
    }

    public void remove(int index){
        if(index < 0 || index > length - 1){
            throw new IndexOutOfBoundsException();
        }
        for (int i = index + 1; i < length; i++) {
            elementData[i - 1] = elementData[i];
        }
        length--;
    }

    public void foreach(){
        for (int i = 0; i < length; i++) {
            System.out.print(elementData[i] + " ");
        }
    }
}