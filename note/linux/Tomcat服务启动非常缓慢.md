概述
本文主要介绍Tomcat服务启动非常缓慢的解决方法。
问题症状
Tomcat启动非常缓慢，查看日志如下。
问题原因
SecureRandom这个jre的工具类的问题。
解决方案
在Tomcat环境中解决
可以通过配置JRE使用非阻塞的Entropy Source。
在catalina.sh文件中加入如下内容，
-Djava.security.egd=file:/dev/./urandom
加入后重启Tomcat，查看Tomcat服务启动日志，启动耗时下降。
在JVM环境中解决
打开 $JAVA_PATH/jre/lib/security/java.security这个文件。
在文件中找到如下内容。
securerandom.source=file:/dev/urandom
将内容替换成如下内容
securerandom.source=file:/dev/./urandom