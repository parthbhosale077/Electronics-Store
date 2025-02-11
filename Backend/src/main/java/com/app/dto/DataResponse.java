package com.app.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataResponse {
	private String message;
	private List<?> data;
	
	public DataResponse(String message, List<?> data) {
		super();
		this.message = message;
		this.data = data;
	}
}
