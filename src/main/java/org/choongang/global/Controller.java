package org.choongang.global;

public interface Controller {
    //모델 찾아서 연결하고 뷰를 보여준다

    void show(); //서비스(모델)와 뷰를 서로 연결하는 역할

    //모든 페이지마다 공통적으로 나오는 요소들.. ex)제목.. 입력 프롬프트.. 공통 문구..
    //동시에 실행될수있도록 ..
    //템플릿 메서드 패턴
    /*
    * 공통 (common())
      메뉴항목 (show())
      입력 (prompt())
    * */
    void run();

}
