package com.hong.test;

import com.hong.temp.StackDemo;

public class TestStackDemo {

	public static void main(String[] args) {
		StackDemo<String> sd = new StackDemo<String>();
		System.out.println("push:");
		System.out.println(sd.push("aaa"));
		System.out.println(sd.push("bbb"));
		System.out.println(sd.push("ccc"));
		System.out.println(sd.push("ddd"));
		System.out.println("pop:");
		System.out.println("size = " + sd.getSize() + " get element = " + sd.pop());
		System.out.println("size = " + sd.getSize() + " get element = " + sd.pop());
		System.out.println("size = " + sd.getSize() + " get element = " + sd.pop());
		System.out.println("size = " + sd.getSize() + " get element = " + sd.pop());
//		System.out.println("size = " + sd.getSize() +" get element = "+ sd.pop());

	}

}
