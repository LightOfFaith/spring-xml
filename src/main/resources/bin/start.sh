#!/bin/bash

###Spring Boot 启动###

USAGE="Usage: $1 {\e[00;32mstart\e[00m|\e[00;31mstop\e[00m|\e[00;32mstatus\e[00m|\e[00;31mrestart\e[00m}"

##Spring Boot 项目名称
SPRING_BOOT_PROJECT_NAME=$2

##Spring Boot 项目进程ID
SPRING_BOOT_PROJECT_NAME_PID=`ps -ef | grep java | grep $SPRING_BOOT_PROJECT_NAME | grep -v grep | grep -v tail | tr -s " " | cut -d " " -f 2`

if [[ "$SPRING_BOOT_PROJECT_NAME" = "" ]]; then
	echo -e "\e[00;31m请输入项目名称\e[00m"
fi

if [[ "$1" = "" ]]; then
	echo -e $USAGE
	exit 1
fi

function start(){
	count=`ps -ef | grep java | grep $SPRING_BOOT_PROJECT_NAME | grep -v grep | grep -v tail | wc -l`
	if [[ $count != 0 ]]; then
		echo -e "\e[00;32m$SPRING_BOOT_PROJECT_NAME is running with pid: $SPRING_BOOT_PROJECT_NAME_PID\e[00m"
	else
		echo -e "\e[00;32mStarting $SPRING_BOOT_PROJECT_NAME\e[00m"
		nohup java -Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -jar -Dspring.profiles.active=prod $SPRING_BOOT_PROJECT_NAME & > /dev/null 2>&1
	fi
	return 0
}

function status(){
	if [[ -n $SPRING_BOOT_PROJECT_NAME_PID ]]; then
		echo -e "\e[00;32m$SPRING_BOOT_PROJECT_NAME is running with pid: $SPRING_BOOT_PROJECT_NAME_PID\e[00m"
	else
		echo -e "\e[00;31m$SPRING_BOOT_PROJECT_NAME is not running\e[00m"
	fi
	return 0
}

function stop(){
	if [[ -n $SPRING_BOOT_PROJECT_NAME_PID ]]; then
		echo -e "\e[00;31mStoping $SPRING_BOOT_PROJECT_NAME\e[00m"
		curl -X POST http://localhost:[port]/manage/shutdown
		sleep 2
		kill -9 $SPRING_BOOT_PROJECT_NAME_PID
	else
		echo -e "\e[00;31m$SPRING_BOOT_PROJECT_NAME is not running\e[00m"
	fi
	return 0
}

function restart(){
	stop
	sleep 2
	start
	return 0
}

case $1 in
	start )
		start;;
	stop  )
		stop;;
	restart  )
		restart;;
	status  )
		status;;
		*  )
		echo -e $USAGE;;
esac
exit 0