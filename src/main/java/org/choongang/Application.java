package org.choongang;

import org.choongang.main.MainRouter;

public class Application {
    //jar파일 배포 했을때 이 main메서드가 젤 먼저 실행될것
    public static void main(String[] args) {
        //메인 라우터 - 싱글톤
        MainRouter.getInstance().start();
    }
}
