http://dubbo.apache.org/zh-cn/index.html

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>dubbo</artifactId>
    <version>2.6.4</version>
</dependency>
<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.16.Final</version>
</dependency>
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.10</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>2.12.0</version>
</dependency>

http://dubbo.apache.org/zh-cn/docs/user/quick-start.html
用 Spring 配置声明暴露服务
provider.xml：
<!-- 提供方应用信息，用于计算依赖关系 -->
<dubbo:application name="app"  />


通过 Spring 配置引用远程服务
consumer.xml：
<!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
<dubbo:application name="consumer-of-app" />



http://dubbo.apache.org/zh-cn/docs/user/references/registry/zookeeper.html

com.alibaba.dubbo.config.ServiceConfig
com.alibaba.dubbo.config.ReferenceConfig



