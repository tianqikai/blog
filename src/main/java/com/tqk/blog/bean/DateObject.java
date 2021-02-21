package com.tqk.blog.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DateObject {
	public long day;
	public long hour;
	public long min;
	public long sec;
	public String time;
}
