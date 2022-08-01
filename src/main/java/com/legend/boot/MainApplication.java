package com.legend.boot;

//import com.atguigu.boot.bean.Pet;
//import com.atguigu.boot.bean.User;
import com.legend.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类;主配置类: 可以在范围内(@ComponentScan("com.atguigu"))扫描到所有组件
 * @SpringBootApplication: 这是一个SpringBoot应用
 */
@SpringBootApplication(scanBasePackages = "com.legend.boot")//默认扫描com.atguigu.boot路径，利用scanBasePackages指定扫面路径！

//也可以用@SpringBootApplication的合成注解:
//@SpringBootConfiguration  //代表当前是一个(Configuration)配置类
//*@EnableAutoConfiguration
    //1.@AutoConfigurationPackage
        //@Import(AutoConfigurationPackages.Registrar.class)利用Registrar给容器中导入一系列组件
            //Registrar类中registerBeanDefinitions方法:
                // AnnotationMetadata注解元信息
                // new PackageImports(metadata).getPackageNames()   //将指定的一个包下的所有组件导入进来(MainApplication 所在包下)
    //2.@Import(AutoConfigurationImportSelector.class)
        //AutoConfigurationImportSelector
            //方法：selectImports
                //1、利用getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件
                    //2、调用List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes)获取到所有需要导入到容器中的配置类
                        //3、利用工厂加载 Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)；得到所有的组件
                                //4、(FACTORIES_RESOURCE_LOCATION)从META-INF/spring.factories位置来加载一个文件。
                                    //默认扫描我们当前系统(springboot包)里面所有META-INF/spring.factories位置的文件
                                        //spring-org.legend.beans.boot-autoconfigure-2.3.4.RELEASE.jar包里面也有META-INF/spring.factories: 文件里面写死了spring-boot一启动就要给容器中加载的所有配置类
                                            //虽然我们127个场景的所有自动配置启动的时候默认全部加载。xxxxAutoConfiguration
                                            //按照条件装配规则（@Conditional），最终会按需配置。

//@ComponentScan("com.atguigu") //扫描哪些，Spring注解
public class MainApplication {
    public static void main(String[] args) {
//        //1.返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);//固定写法
//        //2.查看容器组件(springboot帮我们配置好了所有web开发的常见场景)
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        int beanDefinitionCount = run.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
//
//        String[] beanNamesForType = run.getBeanNamesForType(CacheAspectSupport.class);
//        System.out.println("==========: " + beanNamesForType.length);
        //3.从容器中获取组件
//        Pet tom01 = run.getBean("tom",  Pet.class);//"Tom"，自定义名称
//        Pet tom02 = run.getBean("tom", Pet.class);
//        System.out.println("是否为同一个组件实例：" + (tom01 == tom02));
//
        //4、如果被@Configuration(proxyBeanMethods = true)标注，该类(MyConfig)就是springboot的代理对象:
        // com.atguigu.org.legend.beans.boot.config.MyConfig$$EnhancerBySpringCGLIB$$75c390fc@2375b321
//        MyConfig myConfig = run.getBean(MyConfig.class);
//        System.out.println(myConfig);
//        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件(user01)是否在容器中有。
//        //保持组件单实例
//        User user1 = myConfig.user01();
//        User user2 = myConfig.user01();
//        System.out.println(user1 == user2);//true
//
//        User user3 = run.getBean("user01", User.class);
//        Pet tom = run.getBean("tom", Pet.class);
//        //proxyBeanMethods = true:单例模式-->true
//        //proxyBeanMethods = false:非单例-->false
//        System.out.println("是否为用户的同一宠物: " + (user3.getPet() == tom));
//        //5、获取组件
//        String[] beanNamesForType = run.getBeanNamesForType(User.class);//获取容器中所有User类型的组件
//        for(String s : beanNamesForType){
//            System.out.println(s);
//        }
//        JsonMapper bean1 = run.getBean(JsonMapper.class);//获取容器中JsonMapper类型的组件
//        System.out.println(bean1);

        //Conditional条件
//        boolean tom =  run.containsBean("tom");
//        System.out.println("容器中是否有tom组件：" + tom);
//        boolean user01 =  run.containsBean("user01");
//        System.out.println("容器中是否有user01组件：" + user01);
//
//        //Import
//        boolean haha = run.containsBean("haha");
//        System.out.println("haha:" + haha);
//        boolean hehe = run.containsBean("hehe");
//        System.out.println("hehe:" + hehe);

    }
}
