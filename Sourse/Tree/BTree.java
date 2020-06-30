package Tree;
import java.io.IOException;

class Vertex<E>{
    E data;
    BTree<E> left;
    BTree<E> right;

    public Vertex(E data){
        this.data = data;
        left = null;
        right = null;
    }


}

public class BTree<E> {
    protected Vertex<E> root;

    public BTree() {
        root = null;
    }


    public BTree(E data) {
        root = new Vertex<>(data);
    }


    public E getValue() {
        return root.data;
    }

    public BTree<E> getRoot() {
        return this;
    }

    public void setRoot(E data){
        root.data = data;
    }
    public void setRight(E data) {
        root.right = new BTree<>(data);
    }


    public void setLeft(E data) {
        root.left = new BTree<>(data);
    }

    public BTree<E> getLeft() {
        return root.left;
    }

    public BTree<E> getRight() {
        return root.right;
    }

    public boolean isNull() {
        return root == null;
    }

    public boolean isNullR(){
        return root.right == null;
    }

    public  boolean isNullL(){
        return root.left == null;
    }
}