package com.wilshion.oa.ui.activity.main;

import android.app.Activity;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.address_list.AddressListActivity;
import com.wilshion.oa.ui.activity.email.EmailListActivity;
import com.wilshion.oa.ui.activity.msg.MsgListActivity;
import com.wilshion.oa.ui.activity.my_file.MyDocumentActivity;
import com.wilshion.oa.ui.activity.news.NewsListActivity;
import com.wilshion.oa.ui.activity.notification.NotificationListActivity;
import com.wilshion.oa.ui.activity.schedule.ScheduleListActivity;
import com.wilshion.oa.ui.activity.work_flow.WorkFlowListActivity;
import com.wilshion.oa.ui.activity.work_log.WorkDiaryListActivity;

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

        HomeItemBean msgItem = new HomeItemBean(R.drawable.ic_home_sms, "微讯", MsgListActivity.class);
        HomeItemBean emailItem = new HomeItemBean(R.drawable.ic_home_email, "邮件", EmailListActivity.class);
        HomeItemBean notiItem = new HomeItemBean(R.drawable.ic_home_notify, "公告通知", NotificationListActivity.class);
        HomeItemBean newsItem = new HomeItemBean(R.drawable.ic_home_news, "新闻资讯", NewsListActivity.class);
        HomeItemBean scheduleItem = new HomeItemBean(R.drawable.ic_home_calendar, "今日日程", ScheduleListActivity.class);
        HomeItemBean logItem = new HomeItemBean(R.drawable.ic_home_diary, "工作日志", WorkDiaryListActivity.class);
        HomeItemBean fileItem = new HomeItemBean(R.drawable.ic_home_folder, "我的公文", MyDocumentActivity.class);
        HomeItemBean flowItem = new HomeItemBean(R.drawable.ic_home_workflow, "工作流", WorkFlowListActivity.class);
//        HomeItemBean staffItem = new HomeItemBean(R.drawable.ic_home_query, "人员查询", StaffSearchActivity.class);
        HomeItemBean addrItem = new HomeItemBean(R.drawable.ic_home_address, "通讯簿", AddressListActivity.class);
//        HomeItemBean codeItem = new HomeItemBean(R.drawable.ic_home_zipcode, "区号邮编", ZipCodeActivity.class);
//        HomeItemBean weatherItem = new HomeItemBean(R.drawable.ic_home_weather, "天气预报", WeatherActivity.class);


        result.add(msgItem);
        result.add(emailItem);
        result.add(notiItem);
        result.add(newsItem);
        result.add(scheduleItem);
        result.add(logItem);
        result.add(fileItem);
        result.add(flowItem);
//        result.add(staffItem);
        result.add(addrItem);
//        result.add(codeItem);
//        result.add(weatherItem);
        return result;
    }
}
