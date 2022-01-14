# TencentLog [![](https://jitpack.io/v/vi1zen/VUpdateApp.svg)](https://jitpack.io/#YuIosXiao/TencentLog/2.0.0)

TencentLog是一个腾讯日志cls库，腾讯sdk：https://github.com/TencentCloud/tencentcloud-cls-sdk-android， 尝试编译了几次都不成功，所以新建一个库以供使用

# Dependency
step1.
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
step2.
```
implementation 'com.github.YuIosXiao:TencentLog:2.0.0'
```
## CLS Android SDK接入demo示例

```
fun upLog() {
        val endpoint = "ap-guangzhou.cls.tencentcs.com"
        val secretId = ""
        val secretKey = ""
        val topicId = ""

        val ts = System.currentTimeMillis() /1000
        val logItem = LogItem(ts)
        logItem.PushBack(LogContent("whitelist_domain", "www.baidu.com"))
        logItem.PushBack(LogContent("whitelist_ip", "192.168.1.43"))
        Logs.LogGroup.newBuilder()

        val logGroup = Logs.LogGroup.newBuilder()
        logGroup.addLogs(logItem.mContents)
        val app = App.mContext
        val req = PutLogsRequest(topicId, "", "", logGroup)
        try {
            val resq = App.clsClient.PutLogs(req)
            if (null != resq.get()) {
                System.out.println("-----------"+resq.get().GetAllHeaders().toString())
            }
        } catch (e: Exception) {
            System.out.println("-----------"+e.message)
        }
    }
```

```
class App : Application() {

    companion object {
        lateinit var mContext: Context
        lateinit var clsClient: AsyncClient
    }

    override fun onCreate() {
        super.onCreate()

        mContext = applicationContext

        val endpoint = "ap-guangzhou.cls.tencentcs.com"
        val secretId = ""
        val secretKey = ""

        clsClient = AsyncClient(endpoint, secretId, secretKey, NetworkUtils.getLocalMachineIP(), 5, 10)
    }

}
```
```
app的build.gradle中要引入
implementation("com.google.protobuf:protobuf-java:3.13.0")
implementation("com.google.protobuf:protobuf-java-util:3.13.0")
```
