#!/usr/bin/expect
set timeout -1
set mysql_user [lindex $argv 0]
set mysql_host [lindex $argv 1]
set mysql_source_dir [lindex $argv 2]
set mysql_target_dir [lindex $argv 3]
set mysql_passwd [lindex $argv4]
echo "INFO : scp mysql dump file . HOST=$mysql_host. USER=$mysql_user. SOURCE_DIR= $mysql_source_dir. TARGET_DIR=$mysql_target_dir"
spawn scp root@192.168.198.138:/root/sh/test.sh /data/t1.sh
expect "password"
send "123456\r"
expect eof
