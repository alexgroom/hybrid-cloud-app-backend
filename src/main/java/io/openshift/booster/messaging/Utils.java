package io.openshift.booster.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Utils {

    private final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /**
     * @param size
     */
    public void loadMemory(int size) {
        byte[] b = new byte[size];
        b[0] = 1;
        b[b.length - 1] = 1;
        LOGGER.info("Allocated memory {} mb", size);
    }

    /**
     * @param seconds
     */
    public void sleepInSeconds(int seconds) {
        try {
            SECONDS.sleep(seconds);
            LOGGER.info("Slept for {} seconds", seconds);
        } catch (InterruptedException e) {

        }
    }

    /**
     * @param milliSeconds
     */
    public void sleepInMilliSeconds(int milliSeconds) {
        try {
            MILLISECONDS.sleep(milliSeconds);
            LOGGER.info("Slept for {}  milli seconds", milliSeconds);
        } catch (InterruptedException e) {

        }
    }
}
