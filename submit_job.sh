#!/bin/bash

#java -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.log.org.apache.http=INFO -cp target/animoto-api-1.3.2-jar-with-dependencies.jar com.animoto.api.submitjob.SubmitJob $*

java -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG -cp target/animoto-api-1.3.2-jar-with-dependencies.jar  com.animoto.api.submitjob.SubmitJob -j direct-and-render -k bb0d0e005ac4012dc17712313b013462 -x c0fe4cfca8bf544b8d0e687247a600ef55ff82e3 -t https://platform-staging.animoto.com -s http://s3-p.animoto.com/Song/jzMfYZeYWirq5AZT6a6atA/l.mp3 -I images.txt
