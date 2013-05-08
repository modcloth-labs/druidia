#!/bin/bash

NUM=10
MAX=50

function kill_childs {
  local name

  name=$(basename $0)
  for p in `ps auxww | grep $name | grep -v grep | awk '{print $2}'`; do
    echo "killing $p"
    #kill $p
  done
  exit
}

function hit_server {
  while true; do
    curl --header "Content-Type: application/json" --data "{ \"foo\": \"bar\" }" http://localhost:8080/create/foo
  done
}

function usage() {
  cat << EOF
    usage: $(basename $0) options

    load test druidia

    OPTIONS:
      -n <NUMBER> number of child processed to launch (defaults to 10)
      -h          show this process
EOF
}

trap "kill_childs" SIGINT SIGTERM EXIT

while getopts "n:h" OPTION; do
  case $OPTION in
    n)
      if [[ $OPTARG -lt $MAX ]]; then
        NUM=$OPTARG
      else
        NUM=$MAX
      fi
      ;;
    *)
      usage
      exit
      ;;
  esac
done

i=0
while [[ $i -lt $NUM ]]; do
  echo "spawing child $i"
  hit_server &
  echo $i
  i=`expr $i + 1`
done

while true; do
  sleep 60
done

