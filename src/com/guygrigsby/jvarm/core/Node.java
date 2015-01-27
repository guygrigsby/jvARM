package com.guygrigsby.jvarm.core;

public abstract class Node {
	
	private Node next;
	
	public void setNext(Node nextNode) {
		next = nextNode;
	}
	
	public Node getNext() {
		return next;
	}

}
