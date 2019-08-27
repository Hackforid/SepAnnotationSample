# Sample for android.enableSeparateAnnotationProcessing=true

Without "android.enableSeparateAnnotationProcessing=true", you can see then annotation processor create a resource file at `./testlib/build/intermediates/javac/debug/classes/META-INF/services/inject/invoker_info`

With "android.enableSeparateAnnotationProcessing=true", the `invoker_info` still created, but not pass to `javac/debug/classes`

I wanna read this resource file from jar(:testlib) in some gradle transform to make IOC etc.