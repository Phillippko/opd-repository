package opd.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import ru.spbstu.opd.business.service.ReportService;

@Configuration
@EnableJpaRepositories
public class Config {
    @Bean
    ReportService reportService() {
        return new ReportService();
    }

}
