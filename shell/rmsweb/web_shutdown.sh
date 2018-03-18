#!/bin/bash

cd `dirname $0`

# 環境変数の取得
. ./env.conf

# プロセスIDの取得
pid=`cat ${pid_file}`

# プロセス停止
kill ${pid}

# プロセスIDファイル削除
rm rmsweb.pid

exit 0
