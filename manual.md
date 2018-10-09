https://www.ibm.com/developerworks/cn/
https://docs.spring.io/spring/docs/4.3.18.RELEASE/spring-framework-reference/htmlsingle/
ContextRefreshedEvent：Spring容器初始化或刷新触发事件,先初始化或刷新Root WebApplicationContext，再初始化或刷新WebApplicationContext。

BeanUtils.copyProperties(Object source, Object target);



Spring-Boot(https://docs.spring.io/spring-boot/docs/)
server.error.whitelabel.enabled=false(关闭默认错误页面)
##Spring Boot Actuator
management.port = 8888(访问端口)
management.address = 127.0.0.1(不允许远程管理连接)
management.context-path = /manage(访问前缀)http://localhost:8888/manage/health
management.security.enabled = false(禁用安全检查，显示详细信息)
The prefix ‟endpoints + . + name” is used to uniquely identify the endpoint that is being configured.

(http://localhost:8888/manage/springbeans)
endpoints.beans.enabled = true
endpoints.beans.id = springbeans
endpoints.beans.path = /beans
endpoints.beans.sensitive = false

(http://localhost:8888/manage/applicationinfo)
endpoints.info.enabled=true
endpoints.info.id: applicationinfo
endpoints.info.path: /info
endpoints.info.sensitive: false


