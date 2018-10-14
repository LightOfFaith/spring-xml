zookeeper(http://zookeeper.apache.org/)3.4.13

tar -zxvf zookeeper-3.4.13.tar.gz -C /usr/local/zookeeper/

/usr/local/zookeeper/3.4.13
启动zookeeper服务端
bin/zkServer.sh start
停止zookeeper服务端
bin/zkServer.sh stop

连接zookeeper服务端(ip:127.0.0.1，port:2181)
bin/zkCli.sh -server 127.0.0.1:2181

/usr/local/zookeeper/3.4.13/conf
cp zoo_sample.cfg zoo.cfg

create /zk_test my_data

ls /

get /zk_test

set /zk_test junk

delete /zk_test

help

conf/zoo.cfg(http://zookeeper.apache.org/doc/r3.4.13/zookeeperAdmin.html#sc_configuration)

配置参数说明
clientPort(客户端连接监听端口，客户端连接端口)
dataDir(zookeeper存储内存数据库快照位置)
tickTime(zookeeper使用的基本时间单位，以毫秒为单位。它用于调节心跳和超时)
dataLogDir(zookeeper日志存储位置)
electionAlg(选举实施使用。值“0”对应于原始的基于UDP的版本，“1”对应于未经认证的基于UDP的快速领导者选举版本，“2”对应于经认证的基于UDP的快速领导者选举版本，以及“3”对应于基于TCP的快速领导者选举版本。目前，算法3是默认值)
initLimit(时间数量(tickTime)，允许追随者(followers)连接并同步到领导者(leader)的时间)
leaderServes(领导者接受客户端连接。默认值“yes”,领导者协调更新。注意：当您在一个集合中有三个以上的ZooKeeper服务器时，强烈建议启用领导者选择。)
server.x=[hostname]:nnnnn[:nnnnn]
构建zookeeper服务器集群，当zookeeper服务器启动时，它通过查找数据目录(dataDir)中的myid文件，此文件包含服务器号，在ASCII中，它应该匹配server.x左侧的x。	
作为ZooKeeper集合一部分的每台机器都应该了解整体中的其他每台机器。您可以使用server.id = host：port：port形式的一系列行来完成此操作。参数host和port很简单。您可以通过创建名为myid的文件将服务器ID归因于每台计算机，每个服务器对应一个文件 ，该文件位于该服务器的数据目录中，由配置文件参数dataDir指定。
myid文件由一行组成，只包含该机器id的文本。所以服务器1的myid将包含文本“1”而没有别的。id在整体中必须是唯一的，并且应该具有1到255之间的值。
有两个端口号，第一个追随者用于连接领导者，第二个追随者用于领导者选举，仅当electionAlg为1,2或3（默认值）时，才需要领导者选举端口。如果electionAlg为0，则不需要第二个端口。如果要在一台计算机上测试多个服务器，则可以为每个服务器使用不同的端口。
例如：
server.1=zoo1:2888:3888
server.2=zoo2:2888:3888
server.3=zoo3:2888:3888
syncLimit(时间数量(tickTime)，允许追随者(followers)同步到领导者(leader)的时间。如果追随者落在领导者后面太远(同步时间超时)，他们就会被抛弃)


节点类型()

