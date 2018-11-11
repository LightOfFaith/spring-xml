Linux 如果要保存用户登录操作记录，则可以通过在 /etc/profile 进行相关配置来实现。本文对此进行简要说明。

在  /etc/profile 最下方添加以下信息，唯一修改的地方就是 LOG_DIR 对应的目录位置，默认或根据需要保存的目录进行相应修改即可。

添加后需要执行 source /etc/profile 使其生效。

LOGIP=`who -u am i 2>/dev/null| awk '{print $NF}'|sed -e 's/[()]//g'`
LOG_DIR=/var/log/history
if [ -z $LOGIP ]
then
LOGIP=`hostname`
fi
if [ ! -d $LOG_DIR ]
then
mkdir -P $LOG_DIR
chmod 777 $LOG_DIR
fi
if [ ! -d $LOG_DIR/${LOGNAME} ]
then
mkdir -P $LOG_DIR/${LOGNAME}
chmod 300 $LOG_DIR/${LOGNAME}
fi
export HISTSIZE=4096
LOGTM=`date +"%Y%m%d_%H%M%S"`
export HISTFILE="$LOG_DIR/${LOGNAME}/${LOGIP}-$LOGTM"
chmod 600 $LOG_DIR/${LOGNAME}/*-* 2>/dev/null