package logger.guides.threads;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerThreadsOne {

	private static Logger logger = LoggerFactory.getLogger(LoggerThreadsOne.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		logger.info("启动进程 ...");
		final ThreadPoolExecutor pool = ApplyThreadPool.getThreadPoolExector(16);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				pool.shutdown();
			}
		}));

		logger.info("启动线程 ...");
		pool.execute(new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					logger.info("thread running 1-{} ...", i);
				}
			}
		}));
		pool.execute(new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					logger.info("thread running 2-{} ...", i);
				}
			}
		}));
		logger.info("结束线程 ...");

		pool.shutdown();
		try {
			pool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("结束进程 ...");
	}

}
