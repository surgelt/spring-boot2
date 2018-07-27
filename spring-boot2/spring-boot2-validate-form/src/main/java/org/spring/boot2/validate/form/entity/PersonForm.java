package org.spring.boot2.validate.form.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

	@NotNull(message="名字不能为空")
	@Size(min = 2, max = 30,message="名字长度不对")
	private String name;

	@NotNull(message="年龄不能为空")
	@Min(value = 18,message="年龄需要大于18岁")
	private Integer age;

	public String getName() {
		return this.name;
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

	public String toString() {
		return "Person(Name: " + this.name + ", Age: " + this.age + ")";
	}
}