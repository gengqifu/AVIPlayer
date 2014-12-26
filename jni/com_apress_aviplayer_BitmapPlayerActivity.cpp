/*
 * apress_aviplayer_BitmapPlayerActivity.cpp
 *
 *  Created on: Dec 25, 2014
 *      Author: b710
 */

extern "C" {
#include <avilib.h>
}

#include <android/bitmap.h>

#include "Common.h"
#include "com_apress_aviplayer_BitmapPlayerActivity.h"

jboolean Java_com_apress_aviplayer_BitmapPlayerActivity_render(
		JNIEnv * env,
		jclass clazz,
		jlong avi,
		jobject bitmap)
{
	jboolean isFrameRead = JNI_FALSE;

	char* frameBuffer = 0;
	long frameSize = 0;
	int keyFrame = 0;

	if (0 > AndroidBitmap_lockPixels(env, bitmap, (void**) &frameBuffer))
	{
		ThrowException(env, "java/io/IOException", "Unable to lock pixels");
		goto exit;
	}

	frameSize = AVI_read_frame((avi_t*) avi, frameBuffer, &keyFrame);

	if (0 > AndroidBitmap_unlockPixels(env, bitmap))
	{
		ThrowException(env, "java/io/IOException", "Unable to unlock pixels");
		goto exit;
	}

	if (0 < frameSize)
	{
		isFrameRead = JNI_TRUE;
	}

exit:
	return isFrameRead;
}


