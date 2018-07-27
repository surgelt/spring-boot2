package org.spring.boot2.rest.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Leader {

	/**
	 * 姓名
	 */
	@NotEmpty
	private String name;

	/**
	 * 生日
	 */
	@Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确")
	private String birthday;

	/**
	 * 年龄
	 */
	@Min(value = 0)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	// 省略gettes and setters
	
}