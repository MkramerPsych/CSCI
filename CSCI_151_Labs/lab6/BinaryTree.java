import java.util.LinkedList;

public class BinaryTree {

    protected String data;
    protected BinaryTree left, right;

    BinaryTree() {
	data = null;
	left = right = null;
    }

    BinaryTree(String item) {
	data = item;
	left = new BinaryTree();
	right = new BinaryTree();
    }

    BinaryTree(String item, BinaryTree left, BinaryTree right) {
	data = item;
	this.left = left;
	this.right = right;
    }

    public String getData() {
	return data;
    }

    public BinaryTree getLeft() {
	return left;
    }

    public BinaryTree getRight() {
	return right;
    }

    public void setData(String obj) {
	data = obj;
    }

    public void setLeft(BinaryTree tree) {
	left = tree;
    }

    public void setRight(BinaryTree tree) {
	right = tree;
    }

    public boolean isEmpty() {
	return left == null;
    }

    public boolean isLeaf() {
	return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    public int nodeCount() {
	if (isEmpty())
	    return 0;
	else
	    return 1 + left.nodeCount() + right.nodeCount();
    }

    public int prune() {
	if (isEmpty()) {
	    return 0;
	} else if (isLeaf()) { // if node is leaf
	    data = null;
	    left = right = null; // remove leaf
	    return 1; // return 1 leaf pruned
	} else { // if internal node
	    return left.prune() + right.prune(); // prune left and right children
	}
    }

    public boolean isFull() {
	if (isEmpty()) {
	    return true;
	} else {
	    if (getLeft().isFull() && getRight().isFull()) { // if children are full
		if (getLeft().height() == getRight().height()) { // and heights are equal
		    return true;
		}
	    }
	}
	return false;
    }

    public boolean isComplete() {
	if (isEmpty()) {
	    return true;
	} else {
	    if (getLeft().isComplete() && getRight().isComplete()) { // if children are complete
		int leftHeight = getLeft().height();
		int rightHeight = getRight().height();
		if (leftHeight <= rightHeight + 1 && leftHeight >= rightHeight) { // range for height differential
		    if (getLeft().isFull() || getRight().isFull()) { // if one child or the other is full
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public int leafCount() {
	if (isEmpty()) { // if tree is empty no leaves
	    return 0;
	} else if (isLeaf()) { // if node is a leaf, 1 leaf
	    return 1;
	} else { // if internal node, leaf is left subtree leaf count + right
	    return left.leafCount() + right.leafCount();
	}
    }

    public BinaryTree mirrorImage() {
	if (isEmpty()) { // if tree is empty return empty tree
	    BinaryTree empty = new BinaryTree();
	    return empty;
	} else { // flip tree by swapping left and right for each subtree
	    BinaryTree mirror = new BinaryTree(this.data, right.mirrorImage(), left.mirrorImage());
	    return mirror;
	}
    }

    public int height() {
	if (isEmpty()) { // if tree is empty height is -1
	    return -1;
	} else {
	    int leftHeight = left.height(); // get left subtree height
	    int rightHeight = right.height(); // get right subtree height
	    if (leftHeight >= rightHeight) { // if left is taller or equal
		return 1 + leftHeight; // return left height
	    } else {
		return 1 + rightHeight;
	    }
	}
    }

    public int levelCount(int level) {
	if (isEmpty()) { // if empty 0 nodes
	    return 0;
	} else if (level == 0) { // if root, 1 node
	    return 1;
	} else { // else, return each subtree level count for parent
	    return left.levelCount(level - 1) + right.levelCount(level - 1);
	}

    }

    public int weightBalanceFactor() { // maximum of abs of node counts in left and right subtree
	if (isEmpty()) {
	    return 0;
	} else {
	    int leftCount = getLeft().nodeCount();
	    int rightCount = getRight().nodeCount();
	    int BF = Math.abs(leftCount - rightCount); // Balance factor for single node
	    return Math.max(BF, Math.max(getLeft().weightBalanceFactor(), getRight().weightBalanceFactor()));
	}

    }

    public int nodeSum() {
	if (isEmpty()) {
	    return 0;
	} else {
	    int temp = Integer.parseInt(getData());
	    return temp + getLeft().nodeSum() + getRight().nodeSum();
	}

    }

    public void doubles() {
	if (!isEmpty()) {
	    int temp = Integer.parseInt(getData());
	    temp = 2 * temp;
	    String ret = (temp + "");
	    setData(ret);
	    getLeft().doubles();
	    getRight().doubles();
	}
    }

    public int maxPathSum() {
	if (isEmpty()) {
	    return 0;
	} else {
	    int temp = Math.max(getLeft().maxPathSum(), getRight().maxPathSum());
	    return Integer.parseInt(getData()) + temp;
	}
    }

    public String preOrder() { // root, left, right
	if (isEmpty()) {
	    return "";
	}
	String left = getLeft().preOrder();
	String temp = getData();
	String right = getRight().preOrder();
	return temp + " " + left + right;
    }

    public String inOrder() { // left, root, right
	if (isEmpty()) {
	    return "";
	}
	String left = getLeft().inOrder();
	String temp = getData();
	String right = getRight().inOrder();
	return left + temp + " " + right;
    }

    public String postOrder() { // left, right, root
	if (isEmpty()) {
	    return "";
	}
	String left = getLeft().postOrder();
	String temp = getData();
	String right = getRight().postOrder();
	return left + right + temp + " ";
    }

    public String levelOrder() {
	String order = data;
	LinkedList<BinaryTree> test = new LinkedList<BinaryTree>(); //
	test.add(left); // add root data value
	test.add(right);
	String tempLeft = getLeft().getData();
	String tempRight = getRight().getData();
	while (!test.isEmpty()) {
	    BinaryTree subtree = test.remove();
	    if (subtree.left != null) {
		test.add(subtree.left);
	    }
	    if (subtree.right != null) {
		test.add(subtree.right);
	    }
	    if (subtree.data != null) {
		order = order + " " + subtree.data;
	    }
	}

	return order;
    }

    public String toString(String indent) {
	if (isEmpty())
	    return "";
	else
	    return right.toString(indent + "   ") + "\n" + indent + "/\n" + indent + data + "\n" + indent + "\\"
		    + left.toString(indent + "   ");
    }

    public String toString() {
	return toString("");
    }
}
