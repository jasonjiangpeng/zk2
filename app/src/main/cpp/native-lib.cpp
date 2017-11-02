#include <jni.h>
#include <string>
#include <sys/cdefs.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_administrator_ndkdemo1_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++asdsad";

    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_administrator_ndkdemo1_MainActivity_getAdd(JNIEnv *env, jobject instance, jint a, jint b) {

    // TODO
    return a+b;
}