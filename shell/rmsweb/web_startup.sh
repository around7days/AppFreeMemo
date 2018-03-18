#!/bin/bash

cd `dirname $0`

# 環境変数の取得
. ./env.conf

# 起動情報
echo "environment=${environment}"
echo "version=${version}"

sleep 5

# 起動
echo "java -jar ${rmswar} --spring.profiles.active=${environment} &"
java -jar ${rmswar} --spring.profiles.active=${environment} &

# プロセスIDの出力
echo $! > ${pid_file}

exit 0
