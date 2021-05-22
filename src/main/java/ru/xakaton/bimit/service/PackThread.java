package ru.xakaton.bimit.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Scope("prototype")
public class PackThread extends LongActionThread {

	public Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Transactional
	public void initProcess() throws Exception {
		
	
	}

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
					log.error("ProcessThread cicle thread error", sst);
				}
			}
		} catch (Exception e) {
			log.error("ProcessThread main thread error", e);
		}
	}

	public void interrupt() {
		Thread.currentThread().isInterrupted();
	}
}
