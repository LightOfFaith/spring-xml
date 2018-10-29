1.@Primary(等同于<bean primary="true|false"/>):表明多个同类型对象存在时，如多个数据源配置，那么需要使用此注解在@Bean或@Component(或在Spring XML配置<bean primary="true|false"/>此属性)，来表明此@Primary标记的对象(或在Spring XML配置<bean primary="true|false"/>此属性标记的对象)，使用@Autowired(required = true)注解将会自动注入此标记对象的值。反之，则需要@Autowired(required = true)配合@Qualifier(value = "")注解自定义需要注入的对象名称。


The @PropertySource annotation provides a convenient and declarative mechanism for adding a PropertySource to Spring’s Environment.
@ImportResource annotation to load XML configuration files



java -Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -jar -Dspring.profiles.active=production app-1.0.0.jar
@Configuration
@PropertySources(value = { @PropertySource(value = "classpath:config/db/mysql-common.properties", encoding = "UTF-8"),
		@PropertySource(value = "classpath:config/db/mysql-${spring.profiles.active}.properties", encoding = "UTF-8") })

