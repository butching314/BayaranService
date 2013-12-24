package com;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class FooBean {
	public static void test() {
		List<String> li = ImmutableList.of("ok");
		System.out.println(li);
	}
}
