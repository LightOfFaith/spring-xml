问题现象
服务器 ping 不通，系统内核也没有禁 ping
问题原因
可能是系统内部防火墙策略设置所致。

处理办法

tcpdump -i eth1 host 本地公网ip