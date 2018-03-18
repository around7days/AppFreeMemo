#!/bin/bash

cd `dirname $0`

# 本日日付
mydate=`date +"%Y%m%d"`
# バックアップフォルダ
backup_dir="/data/rms/rmsweb/backup"

# 月報ファイル
report_dir="/data/rms/rmsweb/report"
backup_report_path="${backup_dir}/rmsfile${mydate}.tar.gz"

# DB
db_schema="rmsdb"
db_user="rms_prod"
db_pass="rms_prod"
backup_db_path="${backup_dir}/rmsdb${mydate}.sql"

# ファイルバックアップ
tar -zcvf ${backup_report_path} ${report_dir} > /dev/null
ls -l ${backup_report_path}

# データベースバックアップ
mysqldump -u${db_user} -p${db_pass} ${db_schema} > ${backup_db_path}
ls -l ${backup_db_path}

exit 0
