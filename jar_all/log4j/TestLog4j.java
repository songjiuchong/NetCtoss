package com.tarena.test;

import org.apache.log4j.Logger;

public class TestLog4j {

	private static Logger logger = Logger.getLogger(TestLog4j.class);

	public static void main(String[] args) {
		logger.debug("调试信息");
		logger.info("普通信息");
		logger.warn("警告信息");
		logger.error("错误信息");
		logger.fatal("致命信息");
	}

}
