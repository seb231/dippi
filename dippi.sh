#!/bin/bash

while getopts ":d:q:f:" option;
do
 case "${option}"
 in
 d) d=${OPTARG};;
 q) q=${OPTARG};;
 f) f=$OPTARG;;
 esac
done
shift $((OPTIND-1))

java -jar target/uberjar/dippi-0.1.0-SNAPSHOT-standalone.jar -d "${d}"  -q "${q}"  -f "${f}"
