package com.yunyisheng.app.yunys.net;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2017/12/20.
 */

public class Api {
    public static final String BASE_PATH = "http://172.16.26.212:8080/";
    private static UserService userService;
    private static ShortMessageService shortMessageService;
    private static CompanyService companyService;

    public static UserService userService(){
        if(userService == null){
            synchronized (Api.class){
                if(userService == null){
                    userService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(UserService.class);
                }
            }
        }
        return userService;
    }
    public static ShortMessageService shortMessageService(){
        if(shortMessageService == null){
            synchronized (Api.class){
                if(shortMessageService == null){
                    shortMessageService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(ShortMessageService.class);
                }
            }
        }
        return shortMessageService;
    }
    public static CompanyService companyService(){
        if(companyService == null){
            synchronized (Api.class){
                if(companyService == null){
                    companyService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(CompanyService.class);
                }
            }
        }
        return companyService;
    }

}
