package Ki;

import java.util.Random;

/**
 * Created by Paul on 09.07.2015.
 */
public class KiStarter {
    public static void main(String[] args) {
        // KI try to login
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(999);
        Ki ki = new Ki("vanuabalavu.pms.ifi.lmu.de", 30305, "DR.roBOTo" + randomInt, "muffigemotten");
        Thread kiTread = new Thread(ki);
        kiTread.setName("KI-Thread");
        kiTread.start();
    }
}
