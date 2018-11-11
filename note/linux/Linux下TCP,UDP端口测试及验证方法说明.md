Linux 系统中，有时需要在系统中测试端口的连通性，以便确认系统的TCP、UDP协议栈是否可以正常运行。本文对此进行简要说明。

TCP端口测试
使用 telnet 测试现有监听端口连通性
可以使用 Linux 自带的 telnet 工具来测试现有端口的连通性，测试命令为：
telnet <host> <port>
# host 是目标服务器 IP，port是待测试端口号
示例：

可以如下指令测试 22 端口的连通性：

telnet 127.0.0.1 22

UDP端口测试
telnet 仅能用于 TCP 协议的端口测试，若要对UDP端口进行测试，可以使用 nc 程序。

使用如下指令确认系统内是否已经安装了 nc：
which nc
示例输出：

[root@centos]# which nc

/usr/bin/nc
如果 nc 未被安装，根据操作系统的不同， 使用yum 或 apt-get 等工具进行安装，本文不再详述。

使用如下指令测试目标服务器 UDP 端口的连通性：

?用法：
nc -vuz <目标服务器 IP> <待测试端口>

示例输出：
[root@centos]# nc -vuz 192.168.0.1 25
Connection to 192.168.0.1 25 port [udp/smtp] succeeded!

# 若返回结果中包含 "succeeded" 字样，则说明相应的端口访问正常。如果无任何返回信息，则说明相应端口访问失败。