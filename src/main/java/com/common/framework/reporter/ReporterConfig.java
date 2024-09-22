package com.common.framework.reporter;

import com.common.framework.constants.BaseConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class ReporterConfig {

    private static final Properties property = new Properties();

    static void loadProperty() {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources" + File.separator +  BaseConstant.REPORTER_CONFIG)) {
            property.load(fileInputStream);
        } catch (IOException e) {
            log.error("IOException occurred while loading property file...");
        }
    }

    public static String getPropertyValue(String key) {
        loadProperty();
        if (Objects.isNull(property.getProperty(key))) {
            log.error("Property name: {} is NOT found.", key);
        }
        return property.getProperty(key);
    }
}
