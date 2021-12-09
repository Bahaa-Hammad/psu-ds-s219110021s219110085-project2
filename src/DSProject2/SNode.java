package DSProject2;

public class SNode<T> {
    private T item; //The Object Instance

    private SNode<T> next; //The Next Object in the list

    //Constructor for creating a node
    public SNode(T item) {
        this.item = item;
    }

    //Getters and Setters for the current node:
    public T getItem() {
        return this.item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    //Getters and Setters for the NEXT node:
    public SNode<T> getNext() {
        return this.next;
    }

    public void setNext(SNode<T> next) {
        this.next = next;
    }
}
