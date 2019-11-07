#!/bin/sh

##
MVN="mvn -ntp -Duser.home=/home/jenkins/agent"

CWD=$(pwd)
echo "CWD: $CWD"

GIT_DIFF=$(/usr/bin/git diff --name-only origin/CI_Test)

JAVA_SDK_CHANGED=$(echo $GIT_DIFF | grep ".*/sdks/java/.*" | wc -l)
JAVA_COMPONENTS_CHANGED=$(echo $GIT_DIFF | grep "components/.*" | wc -l)
JAVA_EXAMPLES_CHANGED=$(echo $GIT_DIFF | grep "examples/.*" | wc -l)

#if [ $((JAVA_SDK_CHANGED > 0)) ];
#then
    cd ./sdks/java/basys.sdk
    $MVN clean install
    cd "$CWD"
#elif [ $((JAVA_COMPONENTS_CHANGED > 0)) ];
#then
    cd ./components/basys.components
    $MVN clean install
    cd "$CWD"
#elif [ $((JAVA_EXAMPLES_CHANGED > 0)) ];
#then
    cd ./examples/basys.examples
    $MVN verify
    cd "$CWD"
#fi
