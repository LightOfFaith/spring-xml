tar -zxvf zookeeper-3.4.13.tar.gz -C /usr/local/zookeeper/

/usr/local/zookeeper/3.4.13
bin/zkServer.sh start

bin/zkCli.sh -server 127.0.0.1:2181

/usr/local/zookeeper/3.4.13/conf
cp zoo_sample.cfg zoo.cfg

create /zk_test my_data

ls /

get /zk_test

set /zk_test junk

delete /zk_test

help

vi /etc/profile

#set java environment
export JAVA_HOME=/usr/java/jdk/jdk1.8.0_121
export JRE_HOME=/usr/java/jdk/jdk1.8.0_121/jre
export CLASSPATH=.:$JAVA_HOME/lib$:JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin/$JAVA_HOME:$PATH
source /etc/profile