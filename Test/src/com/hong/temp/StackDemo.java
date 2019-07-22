package com.hong.temp;

import java.util.ArrayList;
import java.util.List;
/**
 * 自定义堆
 * 后进先出
 * @author mc
 *
 * @param <T>
 */
public class StackDemo<T> {
	private Integer id;
	private List<T> list;

	public StackDemo() {
		super();
		this.list = new ArrayList<T>();
	}

	public StackDemo(Integer size) {
		super();
		if (size == null) {
			size = 16;
		}
		this.list = new ArrayList<T>(size);
	}
	public int getSize() {
		return list.size();
	}

	public T push(T t) {
		list.add(t);
		return t;
	}

	public T pop() {
		T t = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return t;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "StackDemo [id=" + id + ", list=" + list + "]";
	}

}
