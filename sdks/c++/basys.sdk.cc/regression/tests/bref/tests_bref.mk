TARGET_NAME ?= tests_bref

MODULE_SRC := test_bref.cc

# automatic processing
_MKFILE_PATH := $(subst $(CURDIR)/,,$(dir $(abspath $(lastword $(MAKEFILE_LIST)))))
SRC += $(addprefix $(_MKFILE_PATH), $(MODULE_SRC))

include $(_MKFILE_PATH)/../test_support.mk