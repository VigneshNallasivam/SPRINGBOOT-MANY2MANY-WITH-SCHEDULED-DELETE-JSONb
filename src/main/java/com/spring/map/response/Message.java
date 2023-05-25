package com.spring.map.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message 
{
	private String message;
	private Object object;
	public Message(String message) 
	{
		this.message = message;
	}
	public Message(String message, Object object) 
	{
		this.message = message;
		this.object = object;
	}
	
}
