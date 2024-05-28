package org.choongang.main;

import org.choongang.global.Controller;
import org.choongang.global.ControllerLocator;
import org.choongang.global.Router;
import org.choongang.global.constants.Menu;
import org.choongang.main.controllers.MainController;
import org.choongang.member.controllers.JoinController;
import org.choongang.member.controllers.LoginController;
import org.choongang.member.controllers.MemberControllerLocator;

public class MainRouter implements Router {


    private static Router instance;

    private MainRouter(){}
    public static Router getInstance(){
        if(instance == null){
            instance = new MainRouter();
        }
        return instance;
    }

    @Override
    public void change(Menu menu) {
        //특정 enum 상수(메뉴)를 입력하면 그 상수를 찾아서 연결하는 형태
        ControllerLocator memlocator = MemberControllerLocator.getInstance();

        Controller controller = null;

        switch(menu) {
            case JOIN: controller =  memlocator.find(Menu.JOIN); break;
            case LOGIN: controller = memlocator.find(Menu.LOGIN); break;
            default: controller = new MainController();
        }
        //컨트롤러쪽 run메서드 실행
        controller.run(); //common() -> show() -> prompt() 동시 실행
    }

    @Override
    public void start() { //라우터를 실행하면 시작되는 메서드
        while (true){ //무한루프로 계속 실행
            change(Menu.MAIN); //가장 첫 화면은 메인 컨트롤러 출력 화면

        }
    }
}
