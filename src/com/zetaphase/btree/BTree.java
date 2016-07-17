package com.zetaphase.btree;

import com.zetaphase.tree.Node;

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
	
	private void balance(){
		balance(root);
	}
	
	private BNode findParent(int id){
		return findParent(root, id);
	}
	
	private BNode findParent(BNode node, int id){
		if (root.getData()==id){
			return null;
		}else if(node==null){
			return null;
		}else{
			if(node.getLeft()!=null){
				if(node.getLeft().getData()==id){
					return node;
				}
			}
			if(node.getRight()!=null){
				if(node.getRight().getData()==id){
					return node;
				}
			}
			findParent(node.getLeft(),  id);
			findParent(node.getRight(),  id);
		}
		return null;
	}
	
	BNode tmpParent;
	Boolean isLeft;
	
	private void balance(BNode node){
		//System.out.print(node);
		//System.out.println(height(node.getLeft()) - height(node.getRight()));
		if(height(node.getLeft()) - height(node.getRight()) == 2){
			System.out.println("hit");
			if (findParent(node.getData()).getLeft().getData()==node.getData()){
				isLeft=true;
			}else{
				isLeft=false;
			}
			tmpParent = findParent(node.getData());
			node = rotateWithLeftChild(node);
			if (isLeft){
				tmpParent.setLeft(node);
			}else{
				tmpParent.setRight(node);
			}
		}else if(height(node.getRight()) - height(node.getLeft()) == 2){
			System.out.println("hit");
			if (findParent(node.getData()).getLeft().getData()==node.getData()){
				isLeft=true;
			}else{
				isLeft=false;
			}
			tmpParent = findParent(node.getData());
			node = rotateWithRightChild(node);
			if (isLeft){
				tmpParent.setLeft(node);
			}else{
				tmpParent.setRight(node);
			}
		}
		if(node.getLeft()!=null){
			balance(node.getLeft());
		}
		if(node.getRight()!=null){
			balance(node.getRight());
		}
	}
	
	private void updateHeight(){
		updateHeight(root);
	}
	
	private void updateHeight(BNode node){
		if (node.getLeft()==null && node.getRight()==null){
			node.setHeight(0);
		}else if(node.getLeft()==null){
			node.setHeight(node.getRight().getHeight()+1);
		}else if(node.getRight()==null){
			node.setHeight(node.getLeft().getHeight()+1);
		}else{
			node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
		}
		if(node.getLeft()!=null){
			updateHeight(node.getLeft());
		}
		if(node.getRight()!=null){
			updateHeight(node.getRight());
		}
	}
	
	public boolean delete(int id){
		BNode parent = root;
		BNode current = root;
		boolean isLeftChild = false;
		while(current.getData()!=id){
			parent = current;
			if (current.getData()>id){
				isLeftChild = true;
				current = current.getLeft();
			}else{
				isLeftChild = false;
				current = current.getRight();
			}
			if (current==null){
				return false;
			}
		}
		if(current.getLeft()==null && current.getRight()==null){
			if(current==root){
				root=null;
			}
			if(isLeftChild){
				parent.setLeft(null);
			}else{
				parent.setRight(null);
			}
		}else if(current.getRight()==null){
			if(current==root){
				root = current.getLeft();
			}else if(isLeftChild){
				parent.setLeft(current.getLeft());
			}else{
				parent.setRight(current.getLeft());
			}
		}else if(current.getLeft()==null){
			if(current==root){
				root = current.getRight();
			}else if(isLeftChild){
				parent.setLeft(current.getRight());
			}else{
				parent.setRight(current.getRight());
			}
		}else if(current.getLeft()!=null && current.getRight()!=null){
			BNode successor = getSuccessor(current);
			if (current==root){
				root = successor;
			}else if(isLeftChild){
				parent.setLeft(successor);
			}else{
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
			if(case2){
				if(successor.getLeft()==null && successor.getRight()==null){
					successor.setHeight(0);
				}else if(successor.getLeft()==null){
					successor.setHeight(successor.getRight().getHeight()+1);
				}else if(successor.getRight()==null){
					successor.setHeight(successor.getLeft().getHeight()+1);
				}else{
					successor.setHeight(max(successor.getLeft().getHeight(), successor.getRight().getHeight()));
				}
				case2=false;
			}
		}
		updateHeight();
		System.out.println(this.toString());
		balance();
		return true;
	}
	private boolean case2 = false;
	public BNode getSuccessor(BNode node){
		BNode successor = null;
		BNode successorParent = null;
		BNode current = node.getRight();
		while(current != null){
			successorParent = successor;
			successor = current;
			//System.out.println(successor.getHeight());
			current = current.getLeft();
		}
		if(successor!=node.getRight()){
			successorParent.setLeft(successor.getRight());
			successor.setRight(node.getRight());
			int a = node.getRight().getLeft().getHeight();
			int b = node.getRight().getRight().getHeight();
			int c = node.getLeft().getHeight();
			if ((c>a)&&(c>b)){
				successor.setHeight(c+1);
			}else if((b>a) && (b>c)){
				successor.setHeight(b+2);
			}else if((a>b) && (a>c)){
				successor.setHeight(a+2);
			}
		}else{
			case2=true;
		}
		return successor;
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
			node.setRight(insert(val, node.getRight()));
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
	
	public BNode getRoot(){
		return root;
	}
	
	@Override
	public String toString(){
		return root.toString();
	}
	
	public static void main(String[] args) {
		BTree t = new BTree();
		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);
		t.insert(5);
		t.delete(6);
		t.insert(7);
		t.insert(8);
		t.insert(9);
		t.insert(10);
		t.insert(11);
		t.insert(12);
		t.insert(13);
		t.insert(14);
		t.insert(15);
		t.insert(16);
		t.delete(2);
		
		t.delete(4);
		//t.delete(9);
		//t.delete(11);
		/*
		System.out.println(t.getRoot().getHeight());
		System.out.println(t.getRoot().getLeft().getHeight());
		System.out.println(t.getRoot().getLeft().getLeft().getHeight());
		System.out.println(t.getRoot().getLeft().getRight().getHeight());
		System.out.println(t.getRoot().getRight().getHeight());
		System.out.println(t.getRoot().getRight().getLeft().getHeight());
		System.out.println(t.getRoot().getRight().getLeft().getLeft().getHeight());
		System.out.println(t.getRoot().getRight().getLeft().getRight().getHeight());
		//System.out.println(t.getRoot().getHeight());
		//System.out.println(t.getRoot().getLeft().getHeight());
		//System.out.println(t.getRoot().getLeft().getLeft().getHeight());
		//System.out.println(t.getRoot().getRight().getHeight());
		//t.insert(3);
		 */
		System.out.println(t);
	}

}
