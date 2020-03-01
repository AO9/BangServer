#!/bin/bash
#
# description: ad init script
# processname: ad  
# chkconfig: 234 20 80  
source /etc/profile

ENV="dev"
if [ x"$2" != x ];then
    ENV=$2
fi

echo "using profile : $ENV"
MAIN_CLASS=com.gto.bang.PjBangbangApplication
echo $MAIN_CLASS

DEPLOY_DIR=/data/BangBang

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=$LIB_DIR"/*"
LOGS_DIR=/data/bangbang-logs
if [ ! -d $LOGS_DIR ]; then
    mkdir -p $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

start() {
  su root -c "nohup java -Duser.timezone=GMT+8 -Dspring.profiles.active=$ENV -classpath $LIB_JARS:$DEPLOY_DIR/conf:. $MAIN_CLASS > $STDOUT_FILE 2>&1 &"
  echo "Please check the STDOUT file: $STDOUT_FILE"
}

stop() {
	PID=`ps -ef | grep java | grep "$DEPLOY_DIR" |grep $MAIN_CLASS |awk '{print $2}'`
	if [ x"$PID" != x ];then
	    echo "`date +"%Y-%m-%d %H:%M:%S"` Kill $DEPLOY_DIR+$MAIN_CLASS processId $PID"
	    kill $PID;
	
	    while [[ x"" == x ]]
	    do
	        sleep 1
	        PID=`ps -ef | grep java | grep "$DEPLOY_DIR" |grep $MAIN_CLASS |awk '{print $2}'`
	        if [ x"$PID" == x ];then
	            echo "`date +"%Y-%m-%d %H:%M:%S"` Process has been killed"
	            break;
	        fi
	    done
	fi
}
 
case $1 in
        start)
          start
        ;;
        stop)  
          stop
        ;;
        restart)
          stop
          start
        ;;
        status)
                status
                exit $?  
        ;;
        kill)
                terminate
        ;;
        *)
                echo -e "no parameter"
        ;;
esac    
exit 0
