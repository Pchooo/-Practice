import Tree.*;
import ArrayList_NEW.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BTree<Character> s = new BTree<>('0');
        BTree<Character> tmp = s;
        if(!readTreeStart(s)) {
            System.out.println("Wring input!");
        }
        else{
            System.out.println("Correct input!");
            System.out.println("Flipped bin tree: ");
            displayBinTree(s, 1);
            ArrayList_NEW<Forest<Character>> forest = new ArrayList_NEW<Forest<Character>>();
            System.out.println("Making a forest from binary tree...");
            toForest(s,forest);
            displayForest(forest);
        }
    }

// начальный метод чтения дерева, представленного скобочным выражением
    public static boolean readTreeStart(BTree<Character> root) throws IOException {
        char tmp = (char) System.in.read();
        while (tmp == ' ') {
            tmp = (char) System.in.read();
        }
        if (tmp == '(') {
            return readTreeRec(root);
        } else {
            return false;
        }
    }

    // рекурсивный метод чтения дерева, представленного скобочным выражением
    public static boolean readTreeRec(BTree<Character> root) throws IOException {
        //Extra spaces read
        char tmp = (char) System.in.read();
        char rootVal = ' ';
        BTree<Character> left;
        while (tmp == ' ') {
            tmp = (char) System.in.read();
        }
        if (tmp == ')') {//null
            return true;
        }

        //Read root
        root.setRoot(tmp);
        tmp = (char) System.in.read();
        while (tmp == ' ') {
            tmp = (char) System.in.read();
        }
        if (tmp == ')') {//null
            return true;
        }
        //ReadLeft
        if (tmp == '(') {
            root.setLeft(null);
            if (!readTreeRec(root.getLeft())) {
                return false;
            }
        } else if (tmp != '/') {
            root.setLeft(tmp);
        }
        tmp = (char) System.in.read();
        while (tmp == ' ') {
            tmp = (char) System.in.read();
        }
        if (tmp == ')') {
            return true;
        }
        //read right
        if(tmp == '('){
            root.setRight(null);
            if (!readTreeRec(root.getRight())) {
                return false;
            }
        }else if(tmp != '/'){
            root.setRight(tmp);
        }
        //read to end
        tmp = (char) System.in.read();
        if(tmp == ')'){
            return true;
        }
        else {
            return false;
        }

    }

//метод отображения бинарного дерева
    public static void displayBinTree(BTree<Character> b, int n){//Prints graphic bin tree

        if(!b.isNull()){
            System.out.print(" "+b.getValue());
            if(!b.isNullR()){
                displayBinTree(b.getRight(), n+1);
            }
            else{
                System.out.println();
            }
            if(!b.isNullL()){
                for(int i = 1; i <= n; i++){
                    System.out.print("  ");
                }
                displayBinTree(b.getLeft(),n+1);
            }
        }
    }
// метод отображения леса
    public static void displayForest(ArrayList_NEW<Forest<Character>> forest){
        System.out.println("Flipped forest: ");
        for(int i = forest.size()-1; i >= 0; i--){
            displayForestTree(forest.get(i), 1);
        }
    }

    public static void displayForestTree(Forest<Character> tree, int n){
        if(tree != null){
            System.out.print(" "+tree.rootValue());
            ArrayList_NEW<Forest<Character>> list = tree.getSubForestList();
            if(list.size() != 0){
                Forest<Character> lastElem = list.get(list.size()-1);
                displayForestTree(lastElem, n+1);
            }
            else{
                System.out.println();
            }
            for(int j = list.size()-2; j>=0; j--){
                for(int i = 1; i <=n; i++){
                    System.out.print("  ");
                }
                displayForestTree(list.get(j), n+1);
            }
        }
    }


//создание леса
    public static boolean toForest(BTree<Character> btree, ArrayList_NEW<Forest<Character>> currenForest){//forest creation function
        if(btree == null || btree.isNull()){
            return false;
        }
        Forest<Character> tree = new Forest<>(btree.getValue());//creating a tree from sent root
        currenForest.add(tree);
        System.out.println("Add  root "+btree.getValue()+" to forest");
        if(!btree.isNullL()){//left subtree of a binary tree - the son of tree
            toTreeNode(currenForest.get(currenForest.size()-1), btree.getLeft());
        }
        if(!btree.isNullR()){//right binary tree subtree - another forest tree
            toForest(btree.getRight(), currenForest);
        }
        return true;
    }

    public static boolean toTreeNode(Forest<Character> tree, BTree<Character> subtree){//non-binary tree creation function
      Forest<Character> newTree = new Forest<>(subtree.getValue());
      tree.addLinks(newTree);
      System.out.println("\tAdd the subtree "+ subtree.getValue()+" in the root "+tree.rootValue());
      if(!subtree.isNullL()){//the left subtree of the subtree is the son of this subtree
          toTreeNode(newTree, subtree.getLeft());
      }
      if(!subtree.isNullR()){//the right subtree of the subtree is the brother of this subtree
          toTreeNode(tree, subtree.getRight());
      }
       return true;
    }



}

