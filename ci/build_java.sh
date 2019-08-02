#!/bin/sh

##
MVN="mvn -ntp"

GIT_DIFF=$(/usr/bin/git diff --name-only origin/CI_Test)

JAVA_SDK_CHANGED=$(echo $GIT_DIFF | grep ".*/sdks/java/.*" | wc -l)
JAVA_COMPONENTS_CHANGED=$(echo $GIT_DIFF | grep "components/.*" | wc -l)
JAVA_EXAMPLES_CHANGED=$(echo $GIT_DIFF | grep "examples/.*" | wc -l)

#if [ $((JAVA_SDK_CHANGED > 0)) ];
#then
    $MVN -f ./sdks/java/basys.sdk/pom.xml verify
#elif [ $((JAVA_COMPONENTS_CHANGED > 0)) ];
#then
    $MVN -f ./components/basys.components/pom.xml verify
#elif [ $((JAVA_EXAMPLES_CHANGED > 0)) ];
#then
    $MVN -f ./examples/basys.examples/pom.xml verify
#fi
