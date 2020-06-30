package Tree;

import ArrayList_NEW.*;

public class Forest<E> {

    private ArrayList_NEW<Forest<E>> links;
    private E root;

    public Forest(){
        root = null;
        links = new ArrayList_NEW<Forest<E>>();
    }

    public Forest(E root){
        this.root = root;
        links = new ArrayList_NEW<Forest<E>>();
    }

    public boolean isNull(){
        return root == null;
    }

    public E rootValue(){
        if(!this.isNull()){
            return root;
        }
        return null;
    }

    public void addLinks(Forest<E> newTree){
        links.add(newTree);
    }


    public ArrayList_NEW<Forest<E>> getSubForestList(){
        if(!isNull()){
            return links.copy();
        }
        return null;
    }
    
}