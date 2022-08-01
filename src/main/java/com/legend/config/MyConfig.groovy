package com.legend.config

import com.legend.beans.Student
import com.legend.beans.Teacher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(value = "com.legend")//标注配置类取代xml文件
class MyConfig {
    @Bean
    Student getStudent() {
        return new Student()
    }
    @Bean
    Teacher getTeacher() {
        return new Teacher()
    }
}
