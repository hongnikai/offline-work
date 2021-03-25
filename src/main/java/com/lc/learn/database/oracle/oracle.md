plsql F5 查看索引占用情况 table access full 全表扫描

1.查询表条数：
（1）count(1) 数据量小的时候可用
（2）select owner,table_name,num_rows from dba_tables where owner = 'FXJS' and table_name = 'S02_BSG_SSO_OPERATOR'


