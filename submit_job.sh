#!/bin/bash

java -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.log.org.apache.http=INFO -cp target/animoto-api-1.3.2-jar-with-dependencies.jar com.animoto.api.submitjob.SubmitJob $*
