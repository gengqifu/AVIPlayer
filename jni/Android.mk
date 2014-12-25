LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := AVIPlayer
LOCAL_SRC_FILES := \
	Common.cpp \
	com_apress_aviplayer_AbstractPlayerActivity.cpp
	
LOCAL_STATIC_LIBRARIES += avilib_static

include $(BUILD_SHARED_LIBRARY)

$(call import-module, transcode-1.1.7/avilib)
