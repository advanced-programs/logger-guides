package logger.guides.threads;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadRun implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(ThreadRun.class);

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		logger.info("count:{}", count.getAndAdd(1));
	}

}
