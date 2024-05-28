package org.choongang.member.controllers;

import org.choongang.global.AbstractController;
import org.choongang.global.Router;
import org.choongang.global.Service;
import org.choongang.global.constants.Menu;
import org.choongang.main.MainRouter;
import org.choongang.member.services.MemberServiceLocator;
import org.choongang.template.Templates;

import java.util.function.Predicate;

/*
* 회원가입 컨트롤러
*
* */
public class JoinController extends AbstractController {
    @Override
    public void show() { //모델과 뷰 연결 및 동작
       // System.out.println("회원가입!"); //동작하는지 확인
        Templates.getInstance().render(Menu.JOIN);
    }

    @Override
    public void prompt() {
//        System.out.print("아이디: ");
//        String userId = sc.nextLine();
//        System.out.println(userId);
        String userId = propmtWithValidation("아이디(6자리 이상): ", s -> s.length() >= 6);
        String userPw = propmtWithValidation("비밀번호(8자리 이상): ", s -> s.length() >= 8);
        String confirmPw = propmtWithValidation("비밀번호 확인: ", s -> {
            boolean match = s.equals(userPw);
            //비밀번호가 일치하지 않을때 출력
            if(!match) {
                System.err.println("\n비밀번호가 일치하지 않습니다.");
            }
            return match;
        });

        //비어있으면 통과되지않는다.
        String userNm = propmtWithValidation("회원명: ", s -> !s.isBlank());
        //System.out.printf("userId= %s, userPw= %s, confirmPw= %s, userNm=%s\n", userId,userPw,confirmPw, userNm);

        RequestJoin form = RequestJoin.builder()
                .userId(userId)
                .userPw(userPw)
                .confirmPw(confirmPw)
                .userNm(userNm)
                .build();

        Router router = MainRouter.getInstance();
        try {
            //회원가입 처리...
            // System.out.println(form);
            Service service = MemberServiceLocator.getInstance().find(Menu.JOIN);
            service.process(form);

            //회원 가입 성공시 -> 로그인 화면으로 이동
            router.change(Menu.LOGIN);
        } catch (RuntimeException e){
            //회원가입 실패시 -> 회원 가입 화면으로 이동
            System.err.println(e.getMessage());
            router.change(Menu.JOIN);
        }
    }

}
