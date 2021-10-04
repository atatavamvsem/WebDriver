package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ResourcesProperties {
    private static FileInputStream fileTestDataInputStream;
    private static Properties DATA_PROPERTIES;

    static {
        try {
            fileTestDataInputStream = new FileInputStream("src/test/resources/testData.properties");
            DATA_PROPERTIES = new Properties();
            DATA_PROPERTIES.load(fileTestDataInputStream);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            if (Objects.nonNull(fileTestDataInputStream))
                try {
                    fileTestDataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getDataProperty(String key) {
        return DATA_PROPERTIES.getProperty(key);
    }
}
