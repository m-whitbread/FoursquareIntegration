-dontnote

-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes EnclosingMethod

-keep class * implements java.io.Serializable { *;}
-keepclassmembers class * implements java.io.Serializable { *;}

# Butterknife
-keep public class * implements butterknife.internal.ViewBinder { public <init>(); }

-dontwarn butterknife.internal.**

-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

# OkHttp
-dontwarn okhttp3.internal.huc.DelegatingHttpsURLConnection
-dontwarn okhttp3.internal.huc.OkHttpsURLConnection
-dontwarn okio.*

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# Gson
-keep class sun.misc.Unsafe { *; }

-keep class uk.co.markomeara.whitbread.data.*  { *; }

-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Rx
-dontwarn sun.misc.Unsafe
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontwarn sun.misc.Unsafe

# Retrolambda
-dontwarn java.lang.invoke.*

# Picasso
-dontwarn com.squareup.okhttp.**