#!/bin/bash

ps -eo pid,user,%cpu,%mem,etime,command --sort=-%cpu | awk 'BEGIN {print "["} NR>1 {print (NR>2?",":"") "{\"pid\":\""$1"\",\"user\":\""$2"\",\"cpu\":\""$3"\",\"mem\":\""$4"\",\"etime\":\""$5"\",\"command\":\""$6"\"}"} END {print "]"}'
