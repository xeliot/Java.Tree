package com.zetaphase.tree;

public class Node {
	private int data;
	private Node left;
	private Node right;	
	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
	
	public void setData(int val){
		this.data = val;
	}
	
	public int getData(){
		return this.data;
	}
	
	public void setLeft(Node node){
		left = node;
	}
	
	public Node getLeft(){
		return left;
	}
	
	public void setRight(Node node){
		right = node;
	}
	
	public Node getRight(){
		return right;
	}
	
}
