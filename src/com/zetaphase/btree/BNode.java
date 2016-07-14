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
	
	public int getHeight(int val){}

}
