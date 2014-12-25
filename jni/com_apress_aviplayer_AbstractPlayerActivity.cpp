/*
 * com_apress_aviplayer_AbstractPlayerActivity.cpp
 *
 *  Created on: Dec 23, 2014
 *      Author: b710
 */

extern "C" {
#include <avilib.h>
}

#include "Common.h"
#include "com_apress_aviplayer_AbstractPlayerActivity.h"

jlong JNICALL Java_com_apress_aviplayer_AbstractPlayerActivity_open(
		JNIEnv * env,
		jclass clazz,
		jstring fileName)
{
	avi_t* avi = 0;
	const char* cFileName = env->GetStringUTFChars(fileName, 0);
	if( 0 == cFileName ) {
		goto exit;
	}

	avi = AVI_open_input_file(cFileName, 1);

	env->ReleaseStringUTFChars(fileName, cFileName);

	if( 0 == avi ) {
		ThrowException(env, "java/io/IOException", AVI_strerror());
	}

exit:
	return (jlong)avi;
}

jint JNICALL Java_com_apress_aviplayer_AbstractPlayerActivity_getWidth(
		JNIEnv * env,
		jclass clazz,
		jlong avi)
{
	return AVI_video_width((avi_t*)avi);
}

jint JNICALL Java_com_apress_aviplayer_AbstractPlayerActivity_getHeight(
		JNIEnv * env,
		jclass clazz,
		jlong avi)
{
	return AVI_video_height((avi_t*)avi);
}

jdouble JNICALL Java_com_apress_aviplayer_AbstractPlayerActivity_getFrameRate(
		JNIEnv * env,
		jclass clazz,
		jlong avi)
{
	return AVI_frame_rate((avi_t*)avi);
}

void JNICALL Java_com_apress_aviplayer_AbstractPlayerActivity_close(
		JNIEnv * env,
		jclass clazz,
		jlong avi)
{
	AVI_close((avi_t*)avi);
}



