package com.share.lifetime.base;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class LRUCacheTest {

	@Test
	public void test() {
		LRUCache<String, String> cache = new LRUCache<String, String>(5);

		cache.put("1", "1");
		cache.put("2", "2");
		cache.put("3", "3");
		cache.put("4", "4");
		cache.put("5", "5");
		cache.put("6", "6");
		loopLinkedHashMap(cache);
		cache.get("4");
		loopLinkedHashMap(cache);
		if (cache.get("1") == null) {
			cache.put("1", "1");
		}
		cache.put("7", "7");
		loopLinkedHashMap(cache);
	}

	public void loopLinkedHashMap(LinkedHashMap<String, String> linkedHashMap) {
		Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
		Iterator<Map.Entry<String, String>> iterator = set.iterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "\t");
		}
		System.out.println();
	}

}
