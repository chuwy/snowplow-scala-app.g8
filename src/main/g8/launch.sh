#!/bin/sh

# Download Dataflow Runner to localdir
# wget http://dl.bintray.com/snowplow/snowplow-generic/dataflow_runner_0.3.0_darwin_amd64.zip
# unzip dataflow_runner_0.3.0_darwin_amd64.zip

# Launch cluster
# ./dataflow-runner up --emr-config cluster.json
# 
# Assemble Spark job
# sbt assembly

# Copy Spark job to S3
# aws s3 cp target/scala-2.11/sparkexample-0.1.0.jar s3://cross-batch-test/jars/sparkexample-0.1.0.jar

# Run playbook
# ./dataflow-runner run --emr-cluster j-1B38PA4E5CKHL --emr-playbook  playbook.json