package com.share.lifetime.util;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdWorkerTest {

	/**
	 * 开始时间截(2018-01-01)
	 */
	private long twepoch = 1514736000000L;// 1288834974657L、1514736000000L

	/**
	 * 机器ID所占的位数
	 */
	private int workerIdBits = 5;

	/**
	 * 数据标识ID所占的位数
	 */
	private int datacenterIdBits = 5;

	/**
	 * 序列在ID中占的位数
	 */
	private int sequenceBits = 12;

	/**
	 * 机器ID向左移12位
	 */
	private int workerIdShift = sequenceBits;

	/**
	 * 数据标识ID向左移17位(12+5)
	 */
	private int datacenterIdShift = sequenceBits + workerIdBits;

	/**
	 * 时间截向左移22位(12+5+5)
	 */
	private int timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;


	private static final int SIZE = 100000; // 10w

	@Test
	public void testNextId() throws InterruptedException, ParseException {
		Set<Long> uidSet = new HashSet<Long>(SIZE);
		IdWorker idWorker = new IdWorker(10L, 10L, 11L);
		for (int i = 0; i < SIZE; i++) {
			Long id = idWorker.nextId();
			uidSet.add(id);
		}
		for (Long id : uidSet) {
			log.info("id:{}", id);
		}
		checkUniqueID(uidSet);
	}

	@Test
	public void testParseIdInfo() {
		log.info("{}", parseIdInfo("101758493678415565"));
	}

	private void checkUniqueID(Set<Long> uidSet) {
		log.info("{}", uidSet.size());
		Assert.assertEquals(SIZE, uidSet.size());
	}

	public JSONObject parseIdInfo(String id) {
		id = Long.toBinaryString(Long.parseLong(id));
		int len = id.length();
		JSONObject jsonObject = new JSONObject();
		int sequenceStart = len < workerIdShift ? 0 : len - workerIdShift;
		int workerStart = len < datacenterIdShift ? 0 : len - datacenterIdShift;
		int timeStart = len < timestampLeftShift ? 0 : len - timestampLeftShift;
		String sequence = id.substring(sequenceStart, len);
		String workerId = sequenceStart == 0 ? "0" : id.substring(workerStart, sequenceStart);
		String dataCenterId = workerStart == 0 ? "0" : id.substring(timeStart, workerStart);
		String time = timeStart == 0 ? "0" : id.substring(0, timeStart);
		int sequenceInt = Integer.valueOf(sequence, 2);
		jsonObject.put("sequence", sequenceInt);
		int workerIdInt = Integer.valueOf(workerId, 2);
		jsonObject.put("workerId", workerIdInt);
		int dataCenterIdInt = Integer.valueOf(dataCenterId, 2);
		jsonObject.put("dataCenter", dataCenterIdInt);
		long diffTime = Long.parseLong(time, 2);
		long startTime = twepoch;
		long timeLong = diffTime + startTime;
		String date = DateFormatUtils.formatByDateTimePattern(new Date(timeLong));
		jsonObject.put("date", date);
		return jsonObject;
	}


}
