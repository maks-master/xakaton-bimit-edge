package ru.xakaton.bimit.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LongActionThread implements Runnable {

	public static final UUID THREAD_UUID = java.util.UUID.randomUUID();
	public Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public abstract void initProcess() throws Exception;

	public abstract void interrupt();

	public void run() {
		long sleeptime = 0;
		try {
			boolean notEndProcess = true;
			while (notEndProcess) {
				Thread.sleep(sleeptime * 100L);
				try {

					initProcess();
					sleeptime = 1;

				} catch (InterruptedException inex) {
					// Restore the interrupted status
					Thread.currentThread().interrupt();
				} catch (Exception sst) {
					log.error("MainThread cicle thread error", sst);
				}

			}
		} catch (Exception e) {
			log.error("MainThread main thread error", e);
		}
	}

}
