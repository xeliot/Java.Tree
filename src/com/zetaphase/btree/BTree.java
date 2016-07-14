package com.zetaphase.btree;

public class BTree {

	private BNode root;
	
	public BTree() {
		root = null;
	}

	public boolean isEmpty(){
		return root == null;
	}
	
	public void clear(){
		root = null;
	}
	
	public void insert(int data){
		root = insert(data, root);
	}	
	
	private int height(BNode t){
		return t == null ? -1 : t.getHeight();
	}
	
	private int max(int lhs, int rhs){
		return lhs > rhs ? lhs : rhs;
	}
	
	private BNode insert(int val, BNode node){
		if (node==null){
			node = new BNode(val);
		}else if(val < node.getData()){
			node.setLeft(insert(val, node.getLeft()));
			if(height(node.getLeft()) - height(node.getRight()) == 2){
				if(val<node.getLeft().getData()){
					node = rotateWithLeftChild(node);
				}else{
					node = doubleWithLeftChild(node);
				}
			}
		}else if(val > node.getData()){
			node.setLeft(insert(val, node.getRight()));
			if(height(node.getRight()) - height(node.getLeft()) == 2){
				if(val>node.getRight().getData()){
					node = rotateWithRightChild(node);
				}else{
					node = doubleWithRightChild(node);
				}
			}
		}else
			;
		node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
		return node;
	}
	
	private BNode rotateWithLeftChild(BNode node){
		BNode child = node.getLeft();
		node.setLeft(child.getRight());
		child.setRight(node);
		node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
		child.setHeight(max(height(child.getLeft()), node.getHeight())+1);
		return child;
	}
	
	private BNode rotateWithRightChild(BNode node){
		BNode child = node.getRight();
		node.setRight(child.getLeft());
		child.setLeft(node);
		node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
		child.setHeight(max(height(child.getRight()), node.getHeight())+1);
		return child;
	}
	
	private BNode doubleWithLeftChild(BNode node){
		node.setLeft(rotateWithRightChild(node.getLeft()));
		return rotateWithLeftChild(node);
	}
	
	private BNode doubleWithRightChild(BNode node){
		node.setRight(rotateWithLeftChild(node.getRight()));
		return rotateWithRightChild(node);
	}
	
	public int countNodes(){
		return countNodes(root);
	}
	
	private int countNodes(BNode node){
		if (node == null){
			return 0;
		}else{
			int count = 1;
			count += countNodes(node.getLeft());
			count += countNodes(node.getRight());
			return count;
		}
	}
	
	public boolean search(int val){
		return search(root, val);
	}
	
	private boolean search(BNode node, int val){
		boolean found = false;
		while((node != null)  && !found){
			int nodeVal = node.getData();
			if(val < nodeVal){
				node = node.getLeft();
			}else if (val > nodeVal){
				node = node.getRight();
			}else{
				found = true;
				break;
			}
			found = search(node, val);
		}
		return found;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
