package zjy.kotlingank.app

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.scwang.smartrefresh.header.BezierCircleHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.zjy.kotlingank.R
import kotlin.properties.Delegates

class GankApplication:Application(){
    var context: Context by Delegates.notNull()
    private set

    override fun onCreate() {
        super.onCreate()
        context = this
        Fresco.initialize(this)
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
            BezierCircleHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            //指定为经典Footer，默认是 BallPulseFooter
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
            BallPulseFooter(context)
        }
    }


}