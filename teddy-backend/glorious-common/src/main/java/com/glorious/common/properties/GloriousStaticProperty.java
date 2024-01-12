package com.glorious.common.properties;


import com.glorious.common.mvc.YmlPropertyReader;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(
        name = "static.yml",
        value = "classpath:static.yml",
        factory = YmlPropertyReader.class
)
@ConfigurationProperties(prefix = "static")
public class GloriousStaticProperty {

    private String xlsxFolder;
    private String xlsxFileType;

    private String xlsxFolderHotSale;
    private String xlsxFolderLessInventory;

    private String interviewHeader;
}
