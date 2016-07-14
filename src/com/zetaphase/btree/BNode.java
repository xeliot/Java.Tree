package com.zetaphase.btree;

public class BNode {

	private BNode left, right;
	private int data;
	private int height;
	
	//constructor
	public BNode() {
		left = null;
		right = null;
		data = 0;
		height = 0;
	}
	
	//alternate constructor
	public BNode(int n){
		left = null;
		right = null;
		data = n;
		height = 0;
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
}
