package com.flagpicker.demo.model;

public class Flag {

	public String name;
	public String flag;

	public Flag() {}

	public Flag(String name, String flag) {
		this.name = name;
	    this.flag = flag;
	}

	@Override
	public String toString() {
		return String.format(
				"Flag[name='%s', flag='%s']",
				name, flag);
	}

}