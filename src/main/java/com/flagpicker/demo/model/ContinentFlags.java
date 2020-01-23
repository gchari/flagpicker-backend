package com.flagpicker.demo.model;

import org.springframework.data.annotation.Id;

public class ContinentFlags {

	@Id
	public String continent;
	public Flag[] countries;

	public ContinentFlags() {}

	public ContinentFlags(String continent, Flag[] countries) {
		this.continent = continent;
		this.countries = countries;
	}

	@Override
	public String toString() {
		return String.format(
				"ContinentFlags[continent='%s']",
				continent);
	}
}