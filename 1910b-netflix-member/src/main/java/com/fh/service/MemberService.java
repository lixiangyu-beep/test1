package com.fh.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberService {

	@RequestMapping("getMember")
	public String getMember() {
		return "我是一个会员服务";
	}
}
