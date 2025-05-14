package com.sec02;

public class Employee {
	private String name;
	private String position;

	public Employee(String name, String position) {
		this.name = name;
		this.position = position;
	}

	@Override
	public String toString() {
		return name + " (" + position + ")";
	}
}
