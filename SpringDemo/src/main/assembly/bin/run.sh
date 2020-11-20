#!/bin/bash
#
# 启动脚本时,如果遇到"-bash: ./run.sh: /bin/bash^M: bad interpreter: No such file or directory"提示错误解决办法
#
# 第一种: vi/vim编辑模式下,输入 ":set ff=unix", 然后退出保存。
#
# 第二种: 命令行运行 sed -i 's/\r$//' run.sh
#
# 选择以上任意一种就可以把run.sh的fileformat从windows的dos转换成linux的unix, 推荐第二种方法,一步到位

cd `dirname $0`
#-------------------------------------------------------------------
# 定义变量
#-------------------------------------------------------------------

#当前路径
BIN_DIR=`pwd`

cd ..

#上一级目录路径
DEPLOY_DIR=`pwd`

#配置文件路径
CONF_DIR=${DEPLOY_DIR}/config

#lib路径--删除
#LIB_DIR=${DEPLOY_DIR}/lib

#依赖包--删除,打包时已在manifest文件指定了
#LIB_JARS=`ls ${LIB_DIR}|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

# 模块名
MODEL_NAME="springdemo"

# 运行包名--如有变化,需要修改启动的JAR包名字
MODEL_JAR="${DEPLOY_DIR}/springdemo-0.0.1-SNAPSHOT.jar"

# 运行参数
MODEL_VARS="--spring.config.location=${CONF_DIR}/ --spring.profiles.active=prod --logging.config=${CONF_DIR}/log4j2-prod.xml"

# JVM参数
# JVM_VARS="-server -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xms1g -Xmx2g"
JVM_VARS="-server -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xms512m -Xmx2g"

# 前台/后台: 0-前台， 1-后台
MODEL_DAEMON=1

# 日志 '&-':表示关闭标准输出日志
# MODEL_LOG="${DEPLOY_DIR}/logs/jar-execute.log"


#-------------------------------------------------------------------
# 以下内容请不要修改
#-------------------------------------------------------------------

SLEEP_MIN=5

# model info can be define here
MODEL_SYMBOL=${MODEL_NAME}
GREP_KEY="Diname="${MODEL_SYMBOL}


#----------------------------------------------------------
# function print usage
#----------------------------------------------------------

print_usage()
{
echo ""
echo "h|H|help|HELP             ---Print help information."
echo "start                     ---Start the ${MODEL_NAME} server."
echo "stop                      ---Stop the ${MODEL_NAME} server."
echo "restart                   ---Restart the ${MODEL_NAME} server."
echo "status                    ---Status the ${MODEL_NAME} server."
}

#-------------------------------------------------------------------
# function model_is_exist (兼容alpine)
#-------------------------------------------------------------------

modelService_is_exist()
{
localServerId=`ps -ef |grep -w "${GREP_KEY}" | grep -v grep | awk '{print $2}'`
if [ -z "${localServerId}" ]
then
return 1
else
expr ${localServerId} + 0 &>/dev/null
if [ $? -ne 0 ]
then
localServerId=`ps -ef |grep -w "${GREP_KEY}" | grep -v grep | awk '{print $1}'|grep -Eo '[0-9]+' `
fi
echo "pid is ${localServerId}"
return 0
fi
}

#-------------------------------------------------------------------
# function check_user_id
# return 0 ---- supper user
# return 1 ---- normal user
#-------------------------------------------------------------------

# check_user_id ()
# {
# localMyId=$(id|awk '{print $1}')
# localMyId=${localMyId:4:1}
# if [ "${localMyId}" -eq "0" ]
# then
# return 0
# else
# return 1
# fi
# }

#-------------------------------------------------------------------
# function model_start
#-------------------------------------------------------------------

model_start ()
{
modelService_is_exist

if [ $? -eq "0" ]
then
        echo "${MODEL_NAME} is running yet. pid ${localServerId}."
        return 0
else
        if [ $MODEL_DAEMON = 0 ]
        then
                echo "try to start ${MODEL_NAME} ... foreground"
                $JAVA_HOME/bin/java -${GREP_KEY} -jar ${JVM_VARS} ${MODEL_JAR} ${MODEL_VARS}
        else
                echo "try to start ${MODEL_NAME} ... backgroud"
                nohup $JAVA_HOME/bin/java -${GREP_KEY} -jar ${JVM_VARS} ${MODEL_JAR} ${MODEL_VARS} >/dev/null 2>&1&
                sleep $SLEEP_MIN
                modelService_is_exist
                if [ $? -eq "0" ]
                then
                        echo "${MODEL_NAME} is running. pid ${localServerId}."
                        return 0
                else
                        echo "failed to start ${MODEL_NAME}! see the output log for more details."
                        return 1
                fi
        fi
fi
}

#-------------------------------------------------------------------
# function model_stop
#-------------------------------------------------------------------

model_stop()
{
echo "try to stop ${MODEL_NAME} ..."
modelService_is_exist

if [ $? -eq 0 ]
then
kill -9 ${localServerId}
if [ $? -ne 0 ]
then
echo "failed to stop ${MODEL_NAME}!"
return 1
else
echo "${MODEL_NAME} stopped."
return 0
fi
else
echo "${MODEL_NAME} is not running!"
return 1
fi
}


model_restart()
{
model_stop

model_start
}

#-------------------------------------------------------------------
# function model_status
#-------------------------------------------------------------------

model_status()
{
modelService_is_exist
if [ $? -eq 0 ]
then
echo "${MODEL_NAME} is running. pid ${localServerId}."
else
echo "${MODEL_NAME} is not running."
fi
}

#-------------------------------------------------------------------
#
#-------------------------------------------------------------------

#-------------------------------------------------------------------
# function parse_para
#-------------------------------------------------------------------

parse_para()
{
case "$1" in
start) model_start;;
stop) model_stop;;
status) model_status;;
restart) model_restart;;
*) echo "illage parameter : $1";print_usage;;
esac
}

#-------------------------------------------------------------------
# main
#-------------------------------------------------------------------

parse_para $1