package com.yunyisheng.app.yunys.present;

import com.yunyisheng.app.yunys.activity.RegisterActivity;
import com.yunyisheng.app.yunys.model.BaseStatusModel;
import com.yunyisheng.app.yunys.net.Api;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/5.
 */

public class RegisterPresent extends XPresent<RegisterActivity> {
    public void getShortMessage(String phone){
        Api.shortMessageService().getShortMessage(phone)
                .compose(XApi.<BaseStatusModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseStatusModel>getScheduler()) //线程调度
                .compose(getV().<BaseStatusModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseStatusModel>() {
                    @Override
                    public void onNext(BaseStatusModel baseStatusModel) {
                        getV().checkMsgResault(baseStatusModel);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showToastMsg("请求数据失败！");
                    }

                });
    }
    public void registerCompany(String company_name,String name,String phone,String password,String code){
        Api.companyService().registerCompany(company_name,name,phone,password,code)
                .compose(XApi.<BaseStatusModel>getApiTransformer())
                .compose(XApi.<BaseStatusModel>getScheduler())
                .compose(getV().<BaseStatusModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseStatusModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showToastMsg("请求数据失败！");
                    }

                    @Override
                    public void onNext(BaseStatusModel baseStatusModel) {
                        getV().checkRegiterInfo(baseStatusModel);
                    }
                });


    }
}
