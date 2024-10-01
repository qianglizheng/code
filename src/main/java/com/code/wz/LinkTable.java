package com.code.wz;

/**
 * @author lizhengqiang
 * @create 2024-09-30 19:20
 **/
public class LinkTable {
    public static void main(String[] args) throws InterruptedException {
        LinkList<String> list = new LinkList<>();
        list.initList();
        list.add(new LinkList.Node("test",null),1);
        list.add(new LinkList.Node("qq",null),2);
        list.add(new LinkList.Node("q",null),1);
        list.foreach();
        list.remove(1);
        System.out.println();
        list.foreach();
    }
}

class LinkList<E> {
    private Node head;

    public static class Node {
        String data;

        Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void initList() {
        head = new Node("head", null);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void destroyList() {
        while (head != null) {
            Node temp = head;
            head = head.next;
            temp = null;
        }
    }

    public void clearList() {
        Node t = head;
        while (head.next != null) {
            Node temp = head.next;
            head = temp;
            temp = null;
        }
        t.next = null;
    }

    public int size() {
        Node t = head;
        int size = 0;
        while (t != null) {
            size++;
            t = t.next;
        }
        return size;
    }

    public String get(int index) {
        if (index < 0 || index > size()) {
            return null;
        }
        Node t = head;
        int i = 0;
        while(t != null){
            if (i == index){
                return t.data;
            }
            t = t.next;
            i++;
        }
        return null;
    }

    public int getByValue(String value) {
        Node t = head;
        int i = 0;
        while(t != null){
            if (value.equals(t.data)){
                break;
            }
            t = t.next;
            i++;
        }
        return i;
    }

    public void add(Node node,int index) {
        Node t = head;
        int i = 0;
        while(t != null){
            if (i == index - 1){
                node.next = t.next;
                t.next = node;
            }
            i++;
            t = t.next;
        }
    }

    public void remove(int index) {
        int i = 0;
        Node t = head;
        while(i < index - 1){
            t = t.next;
            i++;
        }
        t.next = t.next.next;
    }

    public void foreach() {
        Node t = head;
        do {
            System.out.println(t.data);
            t = t.next;
        }
        while (t != null);
    }
}