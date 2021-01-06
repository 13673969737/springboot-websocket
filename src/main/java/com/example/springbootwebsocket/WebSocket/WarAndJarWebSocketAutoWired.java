package com.example.springbootwebsocket.WebSocket;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 判断是否使用webscoket容器，如果使用的是生产环境且是war包的方式不装配bean，
 * 使用方式@Conditional(WarAndJarWebscoketAutoWired.class)
 **/
public class WarAndJarWebSocketAutoWired implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String packageStyle = env.getProperty("package.style");
        String active = env.getProperty("spring.profiles.active");
        //如果是jar不用管环境 如果不是jar就要看是不是开发环境 如果是开发环境就用springboot内置tomcat加载websocket
        boolean res = packageStyle.equals("jar")?true:active.equals("dev");
        return res;
    }
}