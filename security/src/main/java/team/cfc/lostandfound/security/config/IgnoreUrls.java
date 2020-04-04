package team.cfc.lostandfound.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "source.ignored")
@Component
@Data
public class IgnoreUrls {
    List<String> urls = new ArrayList<>();
}
