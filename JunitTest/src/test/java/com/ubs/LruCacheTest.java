package com.ubs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LruCacheTest {

	@DisplayName("When first node added to cache it should ")
	@Test
	public void testFirstNodeInsertion() {
		// Given cache is empty;
		LruCache cache = new LruCache();

		// when first node added into cache;
		cache.putNode(11, 21);
		CacheNode expectedNode = new CacheNode(11, 12);

		// then head and tail of cache should point to first node
		boolean headRes = expectedNode.equals(cache.getHeadNode());
		assertEquals(true, headRes, "When first node added to cache it is not set as header of cache");

		boolean tailRes = expectedNode.equals(cache.getTailNode());
		assertEquals(true, tailRes, "When first node added to cache it is not set as tail of cache");
	}

	@Test
	public void testNodeInsertion() {
		// Given cache is not empty;
		LruCache cache = new LruCache();
		cache.putNode(11, 21);

		// when new node added to cache
		cache.putNode(51, 52);

		// then head of cache should be point to new node
		CacheNode expectedNode = new CacheNode(51, 52);
		boolean headRes = expectedNode.equals(cache.getHeadNode());
		assertEquals(true, headRes, "When new node added to cache it is not set as head of cache");
	}

	@Test
	public void testNodeOverride() {
		// Given cache is not empty;
		LruCache cache = new LruCache();
		cache.putNode(11, 11);
		cache.putNode(12, 11);
		cache.putNode(13, 11);

		// when we override value of middle node
		cache.putNode(12, 51);

		// then that node should be set as header of cache
		CacheNode expectedNode = new CacheNode(12, 51);
		boolean res = expectedNode.equals(cache.getHeadNode());
		assertEquals(true, res, "Head of cache is not update when node value is override");

		// when we override value of last node
		cache.putNode(11, 55);

		// then that node should be set as header of cache
		expectedNode = new CacheNode(11, 55);
		res = expectedNode.equals(cache.getHeadNode());
		assertEquals(true, res, "Head of cache is not update when node value is override");
	}

	@Test
	public void testCacheOverflow() {
		// Given cache is full with node;
		System.out.println("testCacheOverflow");
		LruCache cache = new LruCache();
		cache.putNode(11, 11);
		cache.putNode(12, 11);
		cache.putNode(13, 11);
		cache.putNode(14, 11);
		cache.putNode(15, 11);

		// when new node is added into cache
		cache.putNode(16, 11);

		// then tail of cache should be point to second last node
		CacheNode expectedNode = new CacheNode(12, 11);
		boolean res = expectedNode.equals(cache.getTailNode());
		assertEquals(true, res, " Tail of cache is not updated when cache size overflow");

	}

	@Test
	public void testSizeOfCache() {
		// Given cache is empty;
		LruCache cache = new LruCache();

		// When two node added into cache
		cache.putNode(11, 11);
		cache.putNode(31, 31);

		// then cache size should be 2
		int res = cache.getCurrentSize();
		assertEquals(2, res, " Cache size is not updating properly of node insertion");
	}

	@Nested
	class CacheNodeTest {

		@DisplayName(" Node with equal key should be consider as Equal")
		@Test
		public void testEqual() {
			// Given two node has same key
			CacheNode n1 = new CacheNode(11, 12);
			CacheNode n2 = new CacheNode(11, 22);

			// when node equal methos is calleed
			boolean res = n1.equals(n2);

			// then it should return true
			assertEquals(true, res, " Equal method is not returning true even for  two Node which has same key");
			;
		}
	}
}
