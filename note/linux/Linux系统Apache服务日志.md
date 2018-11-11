Apache 服务的日志文件，默认情况下主要有两种

/var/log/httpd/access_log 记录用户访问网站的记录信息

/var/log/httpd/error_log 记录用户错误请求的信息，包括 Web 服务启动或运行过程中的问题，比如网页找不到、文件权限设置不正确等。   

以下我们就介绍下 Apache 服务日志的分析和优化：

Apache 服务日志的分析
Apache 服务日志的优化
Apache 服务日志的分析
Apache 的访问日志默认存放在 Apache 安装目录的 logs 目录下，名称为 access.log，具体位置可以在 httpd.conf 或 Apache 安装目录下的 conf/vhosts/ 目录中的站点配置文件**.conf 中进行指定

例如日志格式为 ：

123.118.235.44 - - [21/Jan/2015:20:23:53 +0800] "GET /info.php?=PHPE9568F34-D428-11d2-A769-00AA001ACF42 HTTP/1.1" 200 2524
各字段说明

       项目      样例

       客户端 IP     123.118.235.44

       由客户端 identd 进程判断的 RFC1413 身份(identity)    -

       注意：输出中的符号 "-" 表示此处的信息无效。除非在严格 控制的内部网络中，此信息通常很不可靠，不应该被使用。只有在将 IdentityCheck 指令设为 On 时，Apache 才会试图得到这项信息。

       记录用户HTTP的身份验证    -

       服务器完成请求处理时的时间    [21/Jan/2015:20:23:53 +0800]格式:[日/月/年:时:分:秒 时区]  

       请求方式，请求资源，协议   GET /info.php?=PHPE9568F34-D428-11d2-A769-00AA001ACF42 HTTP/1.1

       协议状态码     200

       服务器向客户端发送的字节数   2524

以下列举一些命令便于快速分析日志：

1、获得访问前 10 位的 IP 地址

cat access.log|awk '{print $1}'|sort|uniq -c|sort -nr|head -10
2、访问次数最多的文件或页面,取前 10

cat access.log|awk '{print $11}'|sort|uniq -c|sort -nr|head -10

cat access.log|awk '{counts[$(11)]+=1}; END {for(url in counts) print counts[url], url}'

 3、统计此日志文件中所有的流量

cat access.log |awk '{sum+=$10} END {print sum/1024/1024/1024 "G"}'

4、列出输出大于 200000byte (约200kb) 的 exe 文件以及对应文件发生次数

cat access.log |awk '($10 > 200000 && $7~/\.exe/){print $7}'|sort -n|uniq -c|sort -nr|head -100
 5、如果日志最后一列记录的是页面文件传输时间，则有列出到客户端最耗时的页面

cat access.log |awk '($7~/\.php/){print $NF " " $1 " " $4 " " $7}'|sort -nr|head -20

6、列出最最耗时的页面(超过 60 秒的)的以及对应页面发生次数

cat access.log |awk '($NF > 60 && $7~/\.php/){print $7}'|sort -n|uniq -c|sort -nr|head -20

7.列出传输时间超过 30 秒的文件

cat access.log |awk '($NF > 30){print $7}'|sort -n|uniq -c|sort -nr|head -20

8、统计 404 的连接

awk '($9 ~/404/)' access.log | awk '{print $9,$7}' | sort |uniq -c

结果太多就看一下前 10

awk '($9 ~/404/)' access.log | awk '{print $9,$7}' | sort |uniq -c |sort -nr | head -10

9、统计 HTTP Status

cat access.log |awk '{print $9}'|sort|uniq -c|sort -rn

10、蜘蛛分析查看是哪些蜘蛛来访问过。

cat access.log |awk '{print $12}' | grep -iE 'bot|crawler|slurp|spider' |sort |uniq -c

查看正在来访的蜘蛛

/usr/sbin/tcpdump -i eth1 -l -s 0 -w - dst port 80 | strings | grep -i user-agent | grep -iE 'bot|crawler|slurp|spider'

Apache 服务日志的优化
在网站逐渐成规模后，Apache 访问日志的记录量（文件大小）会达到数 GB 大小。

ECS服务器的磁盘盘容量有限（根据购买大小而定），如果不进行优化，Apache 的访问日志文件在网站运行一段时间（几周）之后可能会占去系统盘容量的一大部分比例。

由于日志文件是纯文本文件，可以考虑通过压缩日志文件，这样备份下来的日志文件可以减小到数十或数百 MB，可以大大减小磁盘空间的浪费。

1、可以通过修改 /etc/logrotate.d/httpd 文件来实现压缩 Apache 日志的功能。

注意：以实际文件路径为准

[root@www ~]# vim /etc/logrotate.d/httpd 
/var/log/httpd/*log {
    missingok
    notifempty
    compress
    sharedscripts
    delaycompress
    postrotate
        /sbin/service httpd reload > /dev/null 2>/dev/null || true
    endscript
}
注意：compress 参数就是指定 Apache 通过 gzip 压缩转储日志

2、也可以配置 Apache 日志自动分割，需要修改 Apache 的配置文件，如 httpd.conf：

注意：以实际需要配置的站点配置文件为准

默认配置为：

CustomLog "/www/web_logs/domain_access_log" common
ErrorLog "/www/web_logs/domain_error_log" 
将其改为：

ErrorLog "| /usr/sbin/rotatelogs /www/web_logs/%Y_%m_%d_domain_error_log 86400 480" 　　
CustomLog "| /usr/sbin/rotatelogs /www/web_logs/%Y_%m_%d_domain_access_log 86400 480" common
以上两行语句会使 Apache 每天生成一个 error_log 和 access_log，方便您检查日志，也便于您备份网站日志。

修改后重启 Apache 服务即可生效。

注意：rotatelogs 路径需根据用户实际情况修改，实际路径一般可通过 which rotatelogs 命令查看

 rotatelogs 配置语法解释：

rotatelogs [ -l ] logfile [ rotationtime [ offset ]] | [ filesizeM ]
 选项

-l

使用本地时间代替GMT时间作为时间基准。注意：在一个改变GMT偏移量(比如夏令时)的环境中使用-l会导致不可预料的结果。

logfile

它加上基准名就是日志文件名。如果logfile中包含”%”，则它会被视为用于strftime()的格式字符串；否则它会被自动加上以秒为单位的”.nnnnnnnnnn”后缀。这两种格式都表示新的日志开始使用的时间。

rotationtime

日志文件滚动的以秒为单位的间隔时间。

offset

相对于UTC的时差的分钟数。如果省略，则假定为”0″并使用UTC时间。比如，要指定UTC时差为”-5小时”的地区的当地时间，则此参数应为”-300″。

filesizeM

指定以filesizeM文件大小滚动，而不是按照时间或时差滚动。

 
