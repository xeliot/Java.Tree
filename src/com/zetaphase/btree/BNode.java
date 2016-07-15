package com.zetaphase.btree;

public class BNode {

	private BNode left, right;
	private int data;
	private int height;
	private BNode parent;
	
	
	//constructor
	public BNode() {
		left = null;
		right = null;
		parent = null;
		data = 0;
		height = 0;
	}
	
	//alternate constructor
	public BNode(int n){
		left = null;
		right = null;
		parent = null;
		data = n;
		height = 0;
	}
	
	public void setParent(BNode node){
		parent = node;
	}
	
	public BNode getParent(){
		return parent;
	}
	
	public void setHeight(int val){
		height = val;
	}
	
	public int getHeight(){
		return height;
	}

	public void setData(int val){
		data = val;
	}
	
	public int getData(){
		return data;
	}
	
	public void setLeft(BNode node){
		left = node;
	}
	
	public BNode getLeft(){
		return left;
	}
	
	public void setRight(BNode node){
		right = node;
	}
	
	public BNode getRight(){
		return right;
	}

	@Override
	public String toString() {
		return "BNode[" + data + ", " + left + ", " + right + "]";
	}
}
