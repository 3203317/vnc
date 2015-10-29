package com.nwyun.birdegg.client;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class LogWriter {
	String _name;
	int _level;
	LogWriter _next;
	static LogWriter _log_writers;
	static int _globalLogLevel = 30;

	public LogWriter(String name) {
		_name = name;
		_level = _globalLogLevel;
		_next = _log_writers;
		_log_writers = this;
	}

	public void setLevel(int level) {
		_level = level;
	}

	public void write(int level, String str) {
		if (0 == level) {
			System.err.println(_name + ": " + str);
		} else {
			System.out.println(_name + ": " + str);
		}
	}

	public void error(String str) {
		write(0, str);
	}

	public void status(String str) {
		write(10, str);
	}

	public void info(String str) {
		write(30, str);
	}

	public void debug(String str) {
		write(100, str);
	}

	public static boolean setLogParams(String params) {
		_globalLogLevel = Integer.parseInt(params);
		LogWriter current = _log_writers;
		while (null != current) {
			current.setLevel(_globalLogLevel);
			current = current._next;
		}
		return true;
	}

	static LogWriter getLogWriter(String name) {
		LogWriter current = _log_writers;
		while (null != current) {
			if (name.equalsIgnoreCase(current._name))
				return current;
			current = current._next;
		}
		return null;
	}
}
