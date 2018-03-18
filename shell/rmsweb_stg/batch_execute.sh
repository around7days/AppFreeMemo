#!/bin/bash

cd `dirname $0`

# 環境変数の取得
. ./env.conf

# 起動情報
echo "environment=${environment}"
echo "version=${version}"
echo "param=$@"

sleep 5

# バッチ起動
echo "java -jar ${rmswar} batch $@ --spring.profiles.active=${environment} &"
java -jar ${rmswar} batch $@ --spring.profiles.active=${environment} &

exit 0
