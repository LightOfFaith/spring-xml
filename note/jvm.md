https://docs.oracle.com/en/
https://docs.oracle.com/en/java/
https://docs.oracle.com/javase/7/docs/technotes/guides/vm/index.html
https://docs.oracle.com/javase/8/docs/index.html
https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#BABCBGHF

https://docs.oracle.com/javase/tutorial/index.html

http://jvmmemory.com/

JDK 8
-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m
JDK 7
-Xms512m -Xmx1024m -XX:PermSize=128m -XX:MaxPermSize=256m

-Dproperty=value
设置系统属性值。属性变量是一个没有空格表示属性名的字符串。value变量是表示属性值的字符串。如果value是一个带空格的字符串，那么用引号括起来 (for example -Dfoo="foo bar").

-Xloggc:filename
设置应将重定向的GC事件信息重定向到的文件以进行日志记录。写入此文件的信息类似于-verbose:gc自每个记录事件之前的第一个GC事件以来经过的时间的输出。如果两者都使用相同的java命令，则该-Xloggc选项将覆盖。-verbose:gc

-Xmnsize
设置年轻代（托儿所）的堆的初始和最大大小（以字节为单位）。字母k或K表示千字节，m或M指示兆字节，g或G指示千兆字节。
堆的年轻代区域用于新对象。GC在该区域比在其他区域更频繁地进行。如果年轻一代的规模太小，那么将会进行大量的小型垃圾收集。如果大小太大，则只执行完整的垃圾收集，这可能需要很长时间才能完成。Oracle建议您将年轻代的大小保持在整个堆大小的一半到四分之一之间。
-Xmssize
设置堆得初始大小(以字节为单位)，该值必须是1024的倍数且大于1MB。字母k或K表示千字节，m或M指示兆字节，g或G指示千兆字节
-Xmxsize
设置内存分配池的最大大小(以字节为单位)，该值必须是1024的倍数且大于2MB。字母k或K表示千字节，m或M指示兆字节，g或G指示千兆字节

-XX：MaxMetaspaceSize = size
设置可以为类元数据分配的最大本机内存量。默认情况下，大小不受限制。应用程序的元数据量取决于应用程序本身，其他正在运行的应用程序以及系统上可用的内存量。

-XX:MaxNewSize=size(byte)
设置年轻代（托儿所）的堆的最大大小（以字节为单位）。默认值按人体工程学设置。

-XX:MetaspaceSize=size
设置分配的类元数据空间的大小，该空间将在第一次超出时触发垃圾回收。根据使用的元数据量，增加或减少垃圾收集的阈值。默认大小取决于平台。

-XX:MaxPermSize = size
设置最大永久生成空间大小（以字节为单位）。此选项在JDK 8中已弃用，并由该-XX:MaxMetaspaceSize选项取代。

-XX:PermSize = size
设置分配给永久生成的空间（以字节为单位），如果超出则会触发垃圾回收。此选项在JDK 8中已弃用，并被该-XX:MetaspaceSize选项取代。

-XX:+PrintGC
允许在每个GC上打印消息。默认情况下，禁用此选项。
-XX：+ PrintGCApplicationConcurrentTime
允许打印自上次暂停后经过的时间（例如，GC暂停）。默认情况下，禁用此选项。
-XX：+ PrintGCApplicationStoppedTime
允许打印暂停（例如，GC暂停）持续多长时间。默认情况下，禁用此选项。
