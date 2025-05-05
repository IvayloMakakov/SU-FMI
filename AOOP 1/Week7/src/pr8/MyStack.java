package pr8;

import java.util.ArrayList;

public class MyStack {
    private ArrayList<Object> list;

    public MyStack() {
        this.list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Object peek() {
        return (!isEmpty()) ? list.get(list.size() - 1) : null;
    }

    public Object pop() {
        return (!isEmpty()) ? list.remove(list.size() - 1) : null;
    }

    public void push(Object o) {
        list.add(o);
    }
}
