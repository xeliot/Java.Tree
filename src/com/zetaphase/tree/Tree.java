package com.zetaphase.tree;

public class Tree {
	
	public Node root;
	
	public Tree(){
		this.root = null;
	}
	
	public boolean find(int id){
		Node current = root;
		while(current != null){
			if(current.getData() == id){
				return true;
			}else if(current.getData() == id){
				current = current.getLeft();
			}else{
				current = current.getRight();
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
