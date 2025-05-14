package com.sec01;

public class Student {
	private String name;
	private int age;
	private int height;
	private int weight;

	public Student() {
	}

	public Student(String name, int age, int height, int weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String studentInfo() {
		return String.format("이름: %s, 나이: %d, 신장: %d, 몸무게: %d", name, age, height, weight);
	}

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}
}
