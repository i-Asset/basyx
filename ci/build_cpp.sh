#!/bin/bash
set -e

CWD=$(pwd)
echo "CWD: $CWD"

GIT_DIFF=$(/usr/bin/git diff --name-only origin/CI_Test)

CPP_SDK_CHANGED=$(echo $GIT_DIFF | grep -e ".*/basys\.sdk\.cc/.*" | wc -l)

if  ((CPP_SDK_CHANGED > 0));
then
    echo "Building and testing BaSyx C++ SDK"

    mkdir build && cd build
    cmake ../sdks/c++/basys.sdk.cc -DBASYX_UTILITY_PROJECTS=OFF -DBUILD_SHARED_LIBS=ON
    make tests_util tests_vab
    ctest
else
    echo "No files in C++ SDK changed."
    echo "Skipping CI Tests for C++ SDK."
fi
