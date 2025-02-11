package com.app.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesDTO {
	
	private Date startDate;
	private Date endDate;

}
