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




netstat -tunlp | grep port

`ps -ef | grep $CATALINA_HOME/ | grep -v grep | grep -v tail | wc -l`

$0:shell的名称
$n:第n个位置参数
$*:含有所有参数内容的单个值，由IFS环境变量中的第一个字符分隔；没定义IFS的话，由空格分隔。
$#:位置参数的总数

cat:列出指定文件的内容
chgrp:修改指定文件或目录的默认属组
chmod:为指定文件或目录修改系统安全权限
clear:从终端仿真器或虚拟控制台终端删除文本
cp:将指定文件复制到另一个位置
crontab:初始化用户的crontable文件对应的编辑器（如果允许的话）
cut:删除文件行中指定的位置
date:以各种格式显示日期
df:显示所有挂载设备的当前磁盘空间使用情况
du:显示指定文件路径的磁盘使用情况
file:查看指定文件的文件类型
find:对文件进行递归查找
free:查看系统上可用的和已用的内存
gawk:使用编程语言命令的流编辑器
grep:在文件中查找指定的文本字符串
getopt:解析命令选项（包括长格式选项）
groups:显示指定用户的组成员关系 
groupadd:创建新的系统组
groupmod:修改已有的系统组
gzip:采用Lempel-Ziv编码的GNU项目压缩工具
head:显示指定文件内容的开头部分
ln:创建针对指定文件的符号链接或硬链接 
ls:列出目录内容
mkdir:在当前目录下创建指定目录
more:列出指定文件的内容，在每屏数据后暂停下来
mount:显示虚拟文件系统上挂载的磁盘设备或将磁盘设备挂载到虚拟文件系统上
mv:重命名文件
passwd:修改某个系统用户账户的密码
ps:显示系统上运行中进程的信息
pwd:显示当前目录
rm:删除指定文件 
rmdir:删除指定目录 
sed:使用编辑器命令的文本流行编辑器
sleep:在指定的一段时间内暂停bash shell操作
sudo:以root用户账户身份运行应用
tail:显示指定文件内容的末尾部分
tar:将数据和目录归档到单个文件中
top:显示活动进程以及其他重要的系统统计数据
touch:新建一个空文件，或更新一个已有文件的时间戳
umount:从虚拟文件系统上删除一个已挂载的磁盘设备
useradd:新建一个系统用户账户
userdel:删除已有系统用户账户
usermod:修改已有系统用户账户
vi:调用vim文本编辑器
whereis:显示指定命令的相关文件，包括二进制文件、源代码文件以及手册页
which:查找可执行文件的位置
who:显示当前系统中的登录用户
whoami:显示当前用户的用户名
zip:Windows下PKZIP程序的Unix版本

sleep 在指定的一段时间内暂停bash shell操作
sleep 10 暂停10秒钟
最基本的重定向将命令的输出发送到一个文件中。bash shell用大于号（>）来完成这项功能

grep -v要进行反向搜索（输出不匹配该模式的行），可加-v参数。
wc命令可以对数据中的文本进行计数。默认情况下，它会输出3个值： 文本的行数、文本的词数、文本的字节数
wc -l 打印行数

$0:shell的名称。
$n:第n个位置参数。
$*:含有所有参数内容的单个值，由IFS环境变量中的第一个字符分隔；没定义IFS的话，由空格分隔。
$@:将所有的命令行参数展开为多个参数。
$#:位置参数的总数。
$?:最近一个命令的退出状态码。
$-:当前选项标记。
$$:当前shell的进程ID（PID）
$!:最近一个后台命令的PID。

exit 0 命令成功结束
exit 1 一般性未知错误
exit 2 不适合的shell命令
exit 126 命令不可执行 
exit 127 没找到命令

if command 
then 
    commands 
fi

if command 
then 
   commands 
else 
   commands 
fi

字符串比较
str1 = str2(检查str1是否和str2相同)
str1 != str2(检查str1是否和str2不同)
-n str1(检查str1的长度是否非0)
-z str1(检查str1的长度是否为0)


ftp client install(yum search ftp)

open Host Port(ftp Host Port)
name : username
password : password
pwd
ls 
mget

yum -y install ftp
mysql client install(yum search mysql)
yum -y install mysql(mariadb-libs.x86_64)