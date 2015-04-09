package com.guygrigsby.jvarm.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registers implements Map<String, Integer> {

	private boolean negative;
	private boolean zero;
	private boolean carry;
	private boolean overflow;
	
	private Map<String, Integer> map;
	
	public Registers() {
		this(new HashMap<String, Integer>());
	}
	
	public Registers(Map<String, Integer> mapIn) {
		map = mapIn;
		map.put("R0", 0);
		map.put("R1", 0);
		map.put("R2", 0);
		map.put("R3", 0);
		map.put("R4", 0);
		map.put("R5", 0);
		map.put("R6", 0);
		map.put("R7", 0);
		map.put("R8", 0);
		map.put("R9", 0);
		map.put("R10", 0);
		map.put("R11", 0);
		map.put("R10", 0);
		map.put("R13", 0);
		map.put("R14", 0);
		map.put("R15", 0);
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size() {
		return map.size();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Integer get(Object key) {
		return map.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Integer put(String key, Integer value) {
		return map.put(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Integer remove(Object key) {
		return map.remove(key);
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Integer> m) {
		map.putAll(m);
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		return map.keySet();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public Collection<Integer> values() {
		return map.values();
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	public Set<java.util.Map.Entry<String, Integer>> entrySet() {
		map.put("N", isNegative()?1:0);
		map.put("Z", isZero()?1:0);
		map.put("C", isCarry()?1:0);
		map.put("V", isOverflow()?1:0);
		return map.entrySet();
	}
	/**
	 * @return the negative
	 */
	public boolean isNegative() {
		return negative;
	}

	/**
	 * @param negative the negative to set
	 */
	public void setNegative(boolean negative) {
		this.negative = negative;
		map.put("N", isNegative()?1:0);
	}

	/**
	 * @return the zero
	 */
	public boolean isZero() {
		return zero;
	}

	/**
	 * @param zero the zero to set
	 */
	public void setZero(boolean zero) {
		this.zero = zero;
		map.put("Z", isZero()?1:0);
	}

	/**
	 * @return the carry
	 */
	public boolean isCarry() {
		return carry;
	}

	/**
	 * @param carry the carry to set
	 */
	public void setCarry(boolean carry) {
		this.carry = carry;
		map.put("C", isCarry()?1:0);
	}

	/**
	 * @return the overflow
	 */
	public boolean isOverflow() {
		return overflow;
	}

	/**
	 * @param overflow the overflow to set
	 */
	public void setOverflow(boolean overflow) {
		this.overflow = overflow;
		map.put("V", isOverflow()?1:0);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return map.equals(o);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	public int hashCode() {
		return map.hashCode();
	}
}
