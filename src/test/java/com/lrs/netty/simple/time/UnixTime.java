package com.lrs.netty.simple.time;

import java.util.Date;

public class UnixTime {

	private final static long TIME_SECONDS_1900 = 2208988800L;

	private final long value;

	public UnixTime() {
		this(System.currentTimeMillis() / 1000 - TIME_SECONDS_1900);
	}

	public UnixTime(long value) {
		this.value = value;
	}

	public long value() {
		return value;
	}

	@Override
	public String toString() {
		return new Date((value() - 2208988800L) * 1000L).toString();
	}
}
