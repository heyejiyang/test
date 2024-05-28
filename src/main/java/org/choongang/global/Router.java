package org.choongang.global;

import org.choongang.global.constants.Menu;

/*
* 사용자가 입력한 메뉴 번호, 문구에 따라서 해당하는 컨트럴로러 연결하는 역할
* 상수형태로 관리
* */
public interface Router {
    void change(Menu menu); //특정 enum 상수(메뉴)를 입력하면 그 상수를 찾아서 연결하는 형태

    void start(); //메뉴 변경하고 컨트롤러 시작

}
