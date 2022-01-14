package com.yanzheng.myapplication

import android.app.Application
import android.content.Context
import com.yanzheng.tenxunlog.AsyncClient
import com.yanzheng.tenxunlog.util.NetworkUtils

class App : Application() {

    companion object {
        lateinit var mContext: Context
        lateinit var clsClient: AsyncClient
    }

    override fun onCreate() {
        super.onCreate()

        mContext = applicationContext

//        val endpoint = "ap-guangzhou.cls.tencentcs.com"
//        val secretId = ""
//        val secretKey = ""
//
//        clsClient = AsyncClient(endpoint, secretId, secretKey, NetworkUtils.getLocalMachineIP(), 5, 10)
    }

}