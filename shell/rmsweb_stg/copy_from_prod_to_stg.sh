#!/bin/bash

cd `dirname $0`

# インポート対象日付
import_target_date=`date '+%Y%m%d'`

# インポート対象ファイルの指定
import_target_file="/data/rms/rmsweb/backup/rmsdb${import_target_date}.sql"

# DB
db_schema="rmsdb_stg"
db_user="rms_stg"
db_pass="rms_stg"

# 月報ファイル
prod_report_dir="/data/rms/rmsweb/report/"
stg_report_dir="/data/rms/rmsweb_stg/report/"

# --------- DB COPY
echo "*** database copy"
echo "target file : ${import_target_file}"
# DEFINERが入ってる行を削除したファイルを作成
cat ${import_target_file} | sed '/^\/\*\!50013 DEFINER=/d' > rmsdb_nodefiner.sql
# インポート
mysql -u${db_user} -p${db_pass} ${db_schema} < rmsdb_nodefiner.sql
# インポート対象ファイルの削除
rm rmsdb_nodefiner.sql

# --------- 月報ファイル
echo "*** report file copy"
rsync -av --delete ${prod_report_dir} ${stg_report_dir}


exit 0
