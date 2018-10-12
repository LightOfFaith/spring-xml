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