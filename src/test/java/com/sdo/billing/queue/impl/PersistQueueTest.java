package com.sdo.billing.queue.impl;

import java.io.File;
import static org.junit.Assert.assertEquals;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.sdo.billing.queue.PersistQueue;

public class PersistQueueTest {
	
	@Test
	public void test1() throws Exception {
		
		String qf = "queue_queue_abc.data.1";
		new File(qf).delete();
		String f1 = FileUtils.readFileToString(new File("testfile.1"),"UTF-8");
		String f2 = FileUtils.readFileToString(new File("testfile.2"),"UTF-8");
		String f3 = FileUtils.readFileToString(new File("testfile.3"),"UTF-8");
		String f4 = FileUtils.readFileToString(new File("testfile.4"),"UTF-8");
		
		PersistQueueManagerImpl qm= new PersistQueueManagerImpl();
		qm.setDataDir(".");
		qm.setCacheSize(0);
		qm.init();
		
		PersistQueue q = qm.getQueue("queue_abc");
		q.put(f1);
		q.put(f2);
		q.put(f3);
		q.put(f4);
		
		long idx1 = q.get();
		String t1 = q.getString(idx1);
		long idx2 = q.get();
		String t2 = q.getString(idx2);
		long idx3 = q.get();
		String t3 = q.getString(idx3);
		long idx4 = q.get();
		String t4 = q.getString(idx4);
		
		qm.close();
		
		assertEquals(f1,t1);
		assertEquals(f2,t2);
		assertEquals(f3,t3);
		assertEquals(f4,t4);
		
		long total = 500000*5;
		total += 2 + f1.getBytes("UTF-8").length;
		total += 2 + f2.getBytes("UTF-8").length;
		total += 6 + f3.getBytes("UTF-8").length;
		total += 6 + f4.getBytes("UTF-8").length;
		long filelen = new File(qf).length();
		
		System.out.println("total="+total);
		System.out.println("filelen="+filelen);
		assertEquals(total,filelen);
		
	}

}
