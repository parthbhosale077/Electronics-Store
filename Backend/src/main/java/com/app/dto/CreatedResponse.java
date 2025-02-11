package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatedResponse {
	private String message;
	private LocalDateTime timeStamp;
	public CreatedResponse(String message) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}

}
