package com.ubs;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

	private int maxSize;
	private int currentSize;
	private CacheNode headNode;
	private CacheNode tailNode;

	Map<Integer, CacheNode> map;

	public LruCache() {
		maxSize = 5;
		currentSize = 0;
		headNode = null;
		tailNode = null;
		map = new HashMap<Integer, CacheNode>();
	}

	public void putNode(int key, int value) {
		if (map.containsKey(key)) {
			CacheNode currentNode = map.get(key);
			currentNode.setValue(value);
			removeNode(currentNode);
			setNodeAsHeader(currentNode);
		} else {
			CacheNode newNode = new CacheNode(key, value);
			map.put(key, newNode);
			if (headNode == null) {
				headNode = newNode;
				tailNode = newNode;
			} else {
				newNode.setNext(headNode);
				headNode.setPre(newNode);
				headNode = newNode;
			}
			currentSize = currentSize + 1;
			System.out.println(" Current size : " + currentSize);
			if (currentSize > maxSize) {
				System.out.println(" Cache overflow");
				removeLastNode();
			}
		}
	}

	public void setNodeAsHeader(CacheNode node) {
		if (headNode == null) {
			headNode = node;
			tailNode = node;
		} else {
			node.setNext(headNode);
			headNode.setPre(node);
			headNode = node;
		}
	}

	public void removeNode(CacheNode node) {
		if (node.getPre() != null) {
			CacheNode pre = node.getPre();
			pre.setNext(node.getNext());
		}
		if (node.getNext() != null) {
			CacheNode next = node.getNext();
			next.setPre(node.getPre());
		}
		if (node.equals(headNode)) {
			CacheNode next = node.getNext();
			next.setPre(null);
			headNode = next;
		}
		if (node.equals(tailNode)) {
			CacheNode pre = node.getPre();
			pre.setNext(null);
			tailNode = pre;
		}
	}

	public void removeLastNode() {
		CacheNode pre = tailNode.getPre();
//		System.out.println(" Current Tail : " + tailNode);

		tailNode.setNext(null);
		tailNode.setPre(null);

		pre.setNext(null);

		tailNode = pre;
//		System.out.println(" New  Tail : " + tailNode);
		currentSize = currentSize - 1;

	}

	public int getCurrentSize() {
		return currentSize;
	}

	public CacheNode getHeadNode() {
		return headNode;
	}

	public void setHeadNode(CacheNode headNode) {
		this.headNode = headNode;
	}

	public CacheNode getTailNode() {
		return tailNode;
	}

	public void setTailNode(CacheNode tailNode) {
		this.tailNode = tailNode;
	}

}

class CacheNode {
	Integer key;
	Integer value;
	CacheNode next;
	CacheNode pre;

	public CacheNode(Integer key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public CacheNode getNext() {
		return next;
	}

	public void setNext(CacheNode next) {
		this.next = next;
	}

	public CacheNode getPre() {
		return pre;
	}

	public void setPre(CacheNode pre) {
		this.pre = pre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheNode other = (CacheNode) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CacheNode [key=" + key + ", value=" + value + "]";
	}

}