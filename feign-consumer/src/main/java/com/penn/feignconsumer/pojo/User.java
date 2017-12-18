package com.penn.feignconsumer.pojo;

public class User {

	private String name;
	private Integer age;

	public User() {

	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return "name=" + name + ", age=" + age;
	}

	// getter && setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
