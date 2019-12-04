#!/bin/bash
set -e

CWD=$(pwd)
echo "CWD: $CWD"

GIT_DIFF=$(/usr/bin/git diff --name-only origin/CI_Test)

CPP_SDK_CHANGED=$(echo $GIT_DIFF | grep ".*/basys.sdk.cc/.*" | wc -l)

df -h
echo ""
free -m

if  ((CPP_SDK_CHANGED > 0));
then
    echo "Building and testing BaSyx C++ SDK"

    mkdir build && cd build
    cmake ../sdks/c++/basys.sdk.cc -DBASYX_UTILITY_PROJECTS=OFF -DBUILD_SHARED_LIBS=ON
    make all
    ctest
fi
