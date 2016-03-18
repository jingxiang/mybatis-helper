package com.kalman.domain;

import com.alibaba.fastjson.JSON;

/**
 * @since 2016年3月18日
 * @author kalman03
 */
public class BaseDO {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
