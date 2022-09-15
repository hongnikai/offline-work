1.netstat  -aon|findstr  "80"  查看端口号占用
2.taskkill /f /pid 4216        强行杀死进程
3.tasklist|findstr "5896"      根据pid查看占用进程