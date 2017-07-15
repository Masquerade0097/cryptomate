package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CryptoMateUtils {

    private static final Logger log = LoggerFactory.getLogger(CryptoMateUtils.class);

    private List<String> supportedCcys = new ArrayList<>();

    public CryptoMateUtils() {
        parseSupportedCcys();
    }

    private void parseSupportedCcys() {
        String filePath = "speechAssets/customSlotTypes/LIST_OF_CURRENCIES";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                supportedCcys.add(line);
            }
        }
        catch (IOException e) {
            log.error("Could not get list of supported currencies.");
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Boolean isSupported(String ccy) {
        return getSupportedCcys().contains(ccy);
    }

    public List<String> getSupportedCcys() {
        return this.supportedCcys;
    }

    public static String getCurrencyName(String ccyInput) {
        log.info("ccyInput: {}", ccyInput);

        if (ccyInput == null || ccyInput.isEmpty()) {
            return null;
        }

        String validCcy;

        if (ccyInput.contains(" ")) {
            validCcy = ccyInput.replaceAll("\\s", "-");
        } else {
            validCcy = ccyInput;
        }

        return validCcy.toLowerCase();
    }
}
