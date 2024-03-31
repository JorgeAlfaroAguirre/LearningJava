package com.ltp.gradesubmission;

import com.ltp.gradesubmission.repository.GradeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GradeRepository gradeRepository(){
        return new GradeRepository();
    }

}
