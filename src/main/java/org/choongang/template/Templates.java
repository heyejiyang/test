package org.choongang.template;

import org.choongang.global.constants.Menu;
import org.choongang.template.main.MainTpl;
import org.choongang.template.member.JoinTpl;
import org.choongang.template.member.LoginTpl;
import org.choongang.template.member.MypageTpl;

import java.lang.management.LockInfo;
import java.util.HashMap;
import java.util.Map;

public class Templates {

    //싱글톤
    private static Templates instance;

    private Map<Menu,Template> tpls; //기존꺼 있으면 쓰고 없으면 새로 생성해서...

    private Templates(){
        tpls = new HashMap<>();
    }
    public static Templates getInstance(){
        if(instance == null){
            instance = new Templates();
        }
        return instance;
    }
    public void render(Menu menu){
//        Template tpl = null;
//        switch (menu){
//            case JOIN:
//            case LOGIN:
//            case MYPAGE:
//            default: tpl = new MainTpl();
//        }
        System.out.println(find(menu).getTpl());
    }
    public Template find(Menu menu){
        Template tpl = tpls.get(menu);
        if(tpl != null){
            return tpl;
        }

        switch (menu){
            case JOIN: tpl = new JoinTpl(); break;
            case LOGIN: tpl = new LoginTpl(); break;
            case MYPAGE: tpl = new MypageTpl(); break;
            default: tpl = new MainTpl();
        }
        tpls.put(menu,tpl);
        return tpl;
    }
    public String line(){
        //줄 통일성있게 만들어주기위한함수
        return "----------------------------------\n";
    }
    public String dpubleLine(){
        return "===================================\n";
    }
}
