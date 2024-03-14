package ru.hse.software.restaurant.Server.configuration;

import lombok.Data;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Data
public class DatabaseConfig {

    private String url;
    private String user;
    private String password;

    public DatabaseConfig(String filepath) {
        Yaml yaml = new Yaml();
        try(InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(filepath)){
            Map<String, Object> obj = yaml.load(inputStream);
            Map<String, Object> database = (Map<String, Object>) obj.get("database");

            this.url = (String) database.get("url");
            this.user = (String) database.get("user");
            this.password = (String) database.get("password");

        } catch (IOException e) {
            throw new RuntimeException("Error reading configuration database with YAML file", e);
        }
    }

}
