-- テスト
create database rmsdb_test;
use rmsdb_test;
create user rms_test identified by 'rms_test';
grant all on rmsdb_test.* to rms_test;

-- 開発
create database rmsdb_dev;
use rmsdb_dev;
create user rms_dev identified by 'rms_dev';
grant all on rmsdb_dev.* to rms_dev;

-- ステージング
create database rmsdb_stg;
use rmsdb_stg;
create user rms_stg identified by 'rms_stg';
grant all on rmsdb_stg.* to rms_stg;

-- 本番
create database rmsdb;
use rmsdb_prod;
create user rms_prod identified by 'rms_prod';
grant all on rmsdb_prod.* to rms_prod;

