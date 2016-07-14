package com.zetaphase.tree;

public class Tree {
	
	public static Node root;
	
	public Tree(){
		this.root = null;
	}
	
	public boolean find(int id){
		Node current = root;
		while(current != null){
			if(current.getData() == id){
				return true;
			}else if(current.getData()>id){
				current = current.getLeft();
			}else{
				current = current.getRight();
			}
		}
		return false;
	}
	
	public boolean delete(int id){
		Node parent = root;
		Node current = root;
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
			Node successor = getSuccessor(current);
			if (current==root){
				root = successor;
			}else if(isLeftChild){
				parent.setLeft(successor);
			}else{
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
		}
		return true;
	}
	
	public Node getSuccessor(Node node){
		Node successor = null;
		Node successorParent = null;
		Node current = node.getRight();
		while(current != null){
			successorParent = successor;
			successor = current;
			current = current.getLeft();
		}
		if(successor!=node.getRight()){
			successorParent.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}
	
	public void insert(int id){
		Node newNode = new Node(id);
		if(root==null){
			root=newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(id<current.getData()){
				current = current.getLeft();
				if(current==null){
					parent.setLeft(newNode);
					return;
				}
			}else{
				current = current.getRight();
				if(current==null){
					parent.setRight(newNode);
					return;
				}
			}
		}
	}
	
	public void display(Node root){
		if(root!=null){
			display(root.getLeft());
			System.out.print(" " + root.getData());
			display(root.getRight());
		}
	}
	
	public int getLeft(int val){
		Node current = root;
		while(current != null){
			if(current.getData() == val){
				return current.getLeft().getData();
			}else if(current.getData()>val){
				current = current.getLeft();
			}else{
				current = current.getRight();
			}
		}
		return -1;
	}
	
	public int getRight(int val){
		Node current = root;
		while(current != null){
			if(current.getData() == val){
				return current.getRight().getData();
			}else if(current.getData()>val){
				current = current.getLeft();
			}else{
				current = current.getRight();
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Tree b = new Tree();
		b.insert(3);b.insert(8);
		b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
		b.insert(20);b.insert(25);b.insert(15);b.insert(16);
		b.delete(10);
		b.display(b.root);
		System.out.println();
		System.out.println(b.getLeft(15));
	}

}
