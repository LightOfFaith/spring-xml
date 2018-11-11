Linux 可以利用 crontab 来实现定时任务，自动化操作。本文对此进行简要说明。

crontab 操作示例
列出定时任务
crontab -l         
#列出所有的定时任务，如果没有定时任务，返回no crontab for root信息。

新建定时任务
crontab -e             
#打开crontab定时任务编辑界面，按I键进入编辑模式。
格式：分时日月周 <命令>

第1列表示分钟1～59 每分钟用或者 /1表示 
第2列表示小时1～23（0表示0点） 
第3列表示日期1～31 
第4列表示月份1～12 
第5列标识号星期0～6（0表示星期天） 
第6列要运行的命令 
示例：

5 * * * * /etc/cpu_monitor.sh     
#开机后，每5分钟执行一次cpu监控脚本。
30 21 * * * /usr/local/etc/rc.d/lighttpd restart         
# 每晚的21:30重启 apache。 
45 4 1,10,22 * * /usr/local/etc/rc.d/lighttpd restart    
#每月1、10、22日的4:45重启 apache。 
查看定时任务
可以执行 crontab -l 查看已经配置的定时任务。

删除定时任务
 
#删除所有定时任务。
crontab -r 
#单独删除某一个定时任务，进入编辑模式，注释掉需要删除的定时任务语句即可
crontab -e        
# 注：使用Crontab进行清理、删除、更改配置操作，有一定风险，请在测试前创建快照备份。
常见问题
执行任务时报错： You (*) are not allowed to use this program (crontab)
问题现象
非 root 用户在执行 crontab -l  或 crontab -e 等命令时，出现报错：

 You (***) are not allowed to use this program (crontab)
问题原因
这是由于 crontab 任务有权限控制，非 root 用户默认没有操作 crontab 的权限。可以通过创建文件 /etc/cron.allow 或者 /etc/cron.deny 来控制权限。

如果 /etc/cron.allow 文件存在，那么只有这个文件中列出的用户可以使用 cron， 同时 /etc/cron.deny文件被忽略； 

如果 /etc/cron.allow 文件不存在，那么文件 /cron.deny 中列出的用户 将不能用使用 cron。    

处理办法
遇到类似问题，请切换到 root 用户，按以下步骤检查：

1. 如果系统中没有 cron.allow 文件，检查 /etc/cron.deny 文件中是否有该非 root 用户的用户名。

如果有，编辑 cron.deny 文件删除该用户的用户名，保存后通过 service crond restart 重启cron服务

 

2. 如果系统中有 cron.allow 文件，可以将该非 root 用户（以testuser1用户名为例）添加到 cron.allow 文件中。保存后重启cron服务。
检查完以上两步，切换到该非root用户（testuser1），检查一下是否可以执行或编辑cron定制任务。