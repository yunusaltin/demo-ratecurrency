package be.coincurrency.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("environments.api")
public class ApplicationConfiguration {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
