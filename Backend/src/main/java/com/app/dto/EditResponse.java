package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EditResponse {
	private String message;
	private Object data;
	
	public EditResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	
}
