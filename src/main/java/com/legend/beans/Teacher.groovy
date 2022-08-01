package com.legend.beans

import groovy.transform.InheritConstructors
import groovy.transform.ToString
import lombok.Data
import lombok.EqualsAndHashCode


@EqualsAndHashCode
@Data
@ToString
@InheritConstructors
class Teacher {
    String name;
    Integer age;
}
