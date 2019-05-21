
/**
 * Load a tree from a text file.  Format is line based, with each line
 * representing one node in the tree.  The first line represents the root
 * of the tree and contains the root data item.  Each additional line
 * consists of a String for data, a flag (L or R) indicating whether this
 * node is the left or right child of its parent, and an int
 * representing the (0-based) index of its parent.
 *
 * Ordering of nodes is level order.
 *
 * @author John Donaldson (Spring 2018)
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeLoader {

    BinaryTree loadTreeFromFile(String fname) throws IOException {
	File file = new File(fname); // read in file from filename
	Scanner scanner = new Scanner(file); // read in from fname
	ArrayList<BinaryTree> treeList = new ArrayList<BinaryTree>(); // create list of trees
	if (scanner.hasNextLine()) { // if the file has a first line
	    String root = scanner.nextLine(); // read in root
	    BinaryTree first = new BinaryTree(root); // make empty tree on root
	    treeList.add(first); // add tree to list
	    while (scanner.hasNextLine()) { // while there is a next line in the input
		String line = scanner.nextLine();
		Scanner lineReader = new Scanner(line); // read line input by input
		if (!lineReader.hasNext()) { // handle last line of file
		    System.out.println("End of File");
		    break;
		}
		String rootToAdd = lineReader.next(); // get next data value
		BinaryTree toAdd = new BinaryTree(rootToAdd); // make empty tree on value
		treeList.add(toAdd);
		String lrFlag = lineReader.next(); // get LR flag
		int index = Integer.parseInt(lineReader.next()); // get index of parent
		if (lrFlag.equals("L")) {
		    BinaryTree parentTree = treeList.get(index);
		    parentTree.setLeft(toAdd); // make new tree left child of parent
		} else if (lrFlag.equals("R")) {
		    BinaryTree parentTree = treeList.get(index);
		    parentTree.setRight(toAdd); // make new tree right child of parent
		}
	    }
	} else { // if there is no text in file
	    BinaryTree empty = new BinaryTree();
	    return empty;
	}
	BinaryTree result = treeList.get(0); // get master tree
	return result;
    }

    // So you can test your tree loader
    public static void main(String[] args) throws IOException {
	if (args.length != 1) {
	    System.out.println("Usage:  java TreeLoader filename");
	} else {
	    TreeLoader tl = new TreeLoader();
	    BinaryTree t = tl.loadTreeFromFile(args[0]);
	    System.out.println(t);
	}
    }
}
