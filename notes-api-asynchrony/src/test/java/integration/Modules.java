package integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

@Configuration
@Profile("test-source")
public class Modules {

    @Bean
    public DataSource dataSource() {
        return mock(DataSource.class, RETURNS_DEEP_STUBS);
    }
}
