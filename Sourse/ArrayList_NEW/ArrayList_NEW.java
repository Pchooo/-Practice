package ArrayList_NEW;

import Tree.Forest;

public class ArrayList_NEW<T> {
    private final int INIT_SIZE = 10;
    private final int RESIZE_DEL = 4;
    private final int FOR_RESIZE = 10;
    private int lastIndex = -1;
    private  Object[] array = new Object[INIT_SIZE];

    public void add(T elem){
        if(lastIndex+1 == array.length){
            resize(array.length+FOR_RESIZE);
        }
        lastIndex++;
        array[lastIndex] = elem;
    }

    public void add(T elem, int index){
        if(index <= lastIndex && index >-1){
            array[index] = elem;
        }
        else{
            add(elem);
        }
    }

    public T get(int index){
        if(index <= lastIndex ) {
            return (T) array[index];
        }else{
            return (T)array[lastIndex];
        }
    }


    public void  resize(int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, lastIndex+1);
        array = newArray;
    }

    public int size(){
        return lastIndex+1;
    }

    public void remove(int index){
        for(int i = index; i<lastIndex; i++){
            array[i]=array[i+1];
        }
        array[lastIndex] = null;
        lastIndex--;
        if(array.length > INIT_SIZE && lastIndex < (array.length/RESIZE_DEL)){//элементов в массиве в RESIZE_DEL меньше, по сравнению с его размером
            resize(array.length/2);
        }
    }

    public ArrayList_NEW<T> copy(){
        ArrayList_NEW<T> newList = new ArrayList_NEW<T>();
        for(int i = 0; i < this.size(); i++){
            newList.add(this.get(i));
        }
        return newList;
    }
}