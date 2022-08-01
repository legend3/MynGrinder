package com.legend.beans


import groovy.transform.ToString
import lombok.AllArgsConstructor
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springframework.beans.factory.annotation.Autowired

@EqualsAndHashCode
@ToString
@Data//自动生成get/set方法
@NoArgsConstructor//无参构造器
@AllArgsConstructor//有参构造器
//@InheritConstructors//继承父类所有的构造方法(groovy的注解)
class Student {
    String name;
    Integer age;
    @Autowired
    Teacher teacher;

}
