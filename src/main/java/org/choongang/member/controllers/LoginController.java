package org.choongang.member.controllers;

import org.choongang.global.AbstractController;
import org.choongang.global.Router;
import org.choongang.global.Service;
import org.choongang.global.constants.Menu;
import org.choongang.main.MainRouter;
import org.choongang.member.services.MemberServiceLocator;
import org.choongang.template.Templates;

/*
* 로그인 컨트롤러
* */
public class LoginController extends AbstractController {
    @Override
    public void show() { //모델과 뷰 연결 및 동작
        //System.out.println("로그인!");
        Templates.getInstance().render(Menu.LOGIN);
    }

    @Override
    public void prompt() {
        String userId = propmtWithValidation("아이디: ", s -> !s.isBlank());
        String userPw = propmtWithValidation("비밀번호: ", s -> !s.isBlank());

        RequestLogin form = RequestLogin.builder()
                .userId(userId)
                .userPw(userPw)
                .build();

        System.out.println(form);

        //로그인 처리...
        Router router = MainRouter.getInstance();

        try {
            Service service = MemberServiceLocator.getInstance().find(Menu.LOGIN);
            service.process(form);
            router.change(Menu.MAIN); //로그인 성공시 -> 메인페이지로 이동
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
            router.change(Menu.LOGIN); //로그인 실패시 -> 로그인 페이지
        }

    }
}
