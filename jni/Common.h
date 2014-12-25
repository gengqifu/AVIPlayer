/*
 * Common.h
 *
 *  Created on: Dec 23, 2014
 *      Author: b710
 */

#ifndef COMMON_H_
#define COMMON_H_

#include <jni.h>

void ThrowException(
		JNIEnv * env,
		const char* className,
		const char* message);



#endif /* COMMON_H_ */
