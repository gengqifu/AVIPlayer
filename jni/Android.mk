LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := AVIPlayer
LOCAL_SRC_FILES := \
	Common.cpp \
	com_apress_aviplayer_AbstractPlayerActivity.cpp \
	com_apress_aviplayer_BitmapPlayerActivity.cpp
	
LOCAL_STATIC_LIBRARIES += avilib_static

LOCAL_LDLIBS += -ljnigraphics

include $(BUILD_SHARED_LIBRARY)

$(call import-module, transcode-1.1.7/avilib)
