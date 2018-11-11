背景介绍
绝大多数服务器出于安全考虑一般都禁止了使用 root 账户登录系统，一般都是通过普通用户远程连接到服务器后，再使用 su 命令切换到 root 执行一些需要超级权限的工作。

但是这样的操作流程也会存在不安全因素，如果系统有多个普通用户，每个用户都需要执行一些需要 root 权限的操作，如果都使用 su 的话，那么 root 账号的密码就需要告诉所有的普通用户，这在一定程度上会对系统造成一些不安全的因素，此时 sudo 命令就能派上用场了。

注: debian 系统默认没有安装 sudo，需要使用如下命令先进行安装

apt-get install sudo
 

命令使用
sudo命令的使用流程是：

将当前用户切换到 root 或者其他指定的用户下，然后以 root 或者其他指定的用户身份执行命令.命令执行完成后，退回到当前用户下.

以上操作可以通过配置 /etc/sudoers 文件来进行。例如普通用户 test 是无法访问 /etc/passwd 的：

如果要让 test 可以访问到这个文件，则在 /etc/sudoers 添加如下内容:

test     ALL = /bin/more  /etc/passwd

这样就能在 test 用户下输入 test 的密码就可以执行 sudo  cat  /etc/shadow 操作了

sudo 使用时间戳文件进行类似的校验功能。当用户输入密码后会得到 5 分钟的操作时间（默认值可以在编译的时候更改），超时之后必须重新输入密码才可以继续操作。这样一来就会让某些调用 root 权限的程序或者脚本出现问题，此时可以通过下面的设置让普通用户无需重复输入密码执行具有 root 权限的程序，例如要让 test 用户拥有重启 /etc/init.d/nginx 的权限，可以在 /etc/sudoers 添加如下配置:

 test  ALL = NOPASSWD: /etc/init.d/nginx start
然后在 test 用户下就可以无需输入密码启动 nginx 了：

另外，可以通过在 /etc/sudoers 文件中添加如下配置使得 test 用户具有 root 用户的所有权限而不必输入 root 用户的密码:

test  ALL=(ALL) NOPASSWD: ALL
这样在 test 用户登录系统后，就可以通过执行如下命令切换到 root 用户：