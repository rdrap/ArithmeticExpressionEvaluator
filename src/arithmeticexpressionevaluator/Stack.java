package arithmeticexpressionevaluator;

import java.util.*;

public class Stack<T> extends ArrayList<T> {

    public Stack() {}

    public void push(T s) {
        this.add(0, s);
    }

    public T pop() {
        return this.remove(0);
    }

    public void remove() {
        this.remove(0);
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public T getTop() {
        return this.get(0);
    }

    public T getSecond() {
        return this.get(1);
    }
}
