package com.yanzheng.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yanzheng.tenxunlog.common.LogContent
import com.yanzheng.tenxunlog.common.LogItem
import com.yanzheng.tenxunlog.common.Logs
import com.yanzheng.tenxunlog.request.PutLogsRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.txlog).setOnClickListener {
//            uploadLog()
        }

//        txlog.setOnClickListener {
//            uploadLog()
//        }
    }

    fun uploadLog() {
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
}