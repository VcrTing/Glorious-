package com.glorious.common.properties;

import com.glorious.common.mvc.YmlPropertyReader;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(
        name = "glorious.yml",
        value = "classpath:glorious.yml",
        factory = YmlPropertyReader.class
)
@ConfigurationProperties(prefix = "mail")
public class GloriousMailProperty {

    private String name;
    private String recipient;
}
