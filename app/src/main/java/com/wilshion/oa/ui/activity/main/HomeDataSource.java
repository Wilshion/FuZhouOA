package com.wilshion.oa.ui.activity.main;

import android.app.Activity;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.address_list.AddressListActivity;
import com.wilshion.oa.ui.activity.email.EmailListActivity;
import com.wilshion.oa.ui.activity.msg.MsgListActivity;
import com.wilshion.oa.ui.activity.my_file.MyFileActivity;
import com.wilshion.oa.ui.activity.news.NewsListActivity;
import com.wilshion.oa.ui.activity.notification.NotificationListActivity;
import com.wilshion.oa.ui.activity.schedule.ScheduleListActivity;
import com.wilshion.oa.ui.activity.staff_search.StaffSearchActivity;
import com.wilshion.oa.ui.activity.weather.WeatherActivity;
import com.wilshion.oa.ui.activity.work_flow.WorkFlowListActivity;
import com.wilshion.oa.ui.activity.work_log.WorkLogListActivity;
import com.wilshion.oa.ui.activity.zip_code.ZipCodeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 10:22.
 * [description : 首页九宫格数据]
 * [version : 1.0]
 */
public class HomeDataSource {
    /**
     * 排布模型
     */
    public static final class HomeItemBean {
        public int resId;
        public String title;
        public Class<? extends Activity> targetClazz;

        public HomeItemBean(int resId, String title, Class<? extends Activity> targetClazz) {
            this.resId = resId;
            this.title = title;
            this.targetClazz = targetClazz;
        }
    }

    public static final List<HomeItemBean> getHomeData() {
        List<HomeItemBean> result = new ArrayList<>();

        HomeItemBean msgItem = new HomeItemBean(R.mipmap.ic_launcher, "短信", MsgListActivity.class);
        HomeItemBean emailItem = new HomeItemBean(R.mipmap.ic_launcher, "邮件", EmailListActivity.class);
        HomeItemBean notiItem = new HomeItemBean(R.mipmap.ic_launcher, "公告通知", NotificationListActivity.class);
        HomeItemBean newsItem = new HomeItemBean(R.mipmap.ic_launcher, "新闻资讯", NewsListActivity.class);
        HomeItemBean scheduleItem = new HomeItemBean(R.mipmap.ic_launcher, "今日日程", ScheduleListActivity.class);
        HomeItemBean logItem = new HomeItemBean(R.mipmap.ic_launcher, "工作日志", WorkLogListActivity.class);
        HomeItemBean fileItem = new HomeItemBean(R.mipmap.ic_launcher, "我的文件", MyFileActivity.class);
        HomeItemBean flowItem = new HomeItemBean(R.mipmap.ic_launcher, "工作流", WorkFlowListActivity.class);
        HomeItemBean staffItem = new HomeItemBean(R.mipmap.ic_launcher, "人员查询", StaffSearchActivity.class);
        HomeItemBean addrItem = new HomeItemBean(R.mipmap.ic_launcher, "通讯簿", AddressListActivity.class);
        HomeItemBean codeItem = new HomeItemBean(R.mipmap.ic_launcher, "区号邮编", ZipCodeActivity.class);
        HomeItemBean weatherItem = new HomeItemBean(R.mipmap.ic_launcher, "天气预报", WeatherActivity.class);


        result.add(msgItem);
        result.add(emailItem);
        result.add(notiItem);
        result.add(newsItem);
        result.add(scheduleItem);
        result.add(logItem);
        result.add(fileItem);
        result.add(flowItem);
        result.add(staffItem);
        result.add(addrItem);
        result.add(codeItem);
        result.add(weatherItem);
        return result;
    }
}
