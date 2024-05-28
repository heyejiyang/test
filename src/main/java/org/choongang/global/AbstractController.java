package org.choongang.global;

import org.choongang.global.constants.Menu;
import org.choongang.main.MainRouter;
import org.choongang.template.Templates;

import java.util.Scanner;
import java.util.function.Predicate;

public abstract class AbstractController implements Controller {
    //템플릿 메서드 패턴 구현
     /*
    * 공통 (common())
      메뉴항목 (show())
      입력 (prompt())
    * */

    //메뉴입력받기 위한 Scanner
    protected Scanner sc;

    //생성자 매개변수를 통해서 초기화, '메뉴입력' 기본 동작 프롬프트 띄워주기
    public AbstractController(){
        sc = new Scanner(System.in);
    }

    //상단 공통 출력 부분
    public void common() {
        System.out.println("학생 관리 프로그램 ver1.0");
        System.out.println(Templates.getInstance().dpubleLine());
    }

    /*입력 항목
    * - 문자: q, exit, quit(종료)
    * - 숫자: 메뉴 항목
    * */
    public void prompt(){
        //System.out.println(Templates.getInstance().dpubleLine());
        System.out.print("메뉴 선택: ");
        String menu = sc.nextLine();
        if(menu.equals("q")|| menu.equals("quit")||menu.equals("exit")){
            System.out.println("종료합니다.");
            System.exit(0); //0 - 정상종료, 1 - 비정상 종료
        }

        try{
            int m = Integer.parseInt(menu);
            change(m); //메뉴변경
        }catch (Exception e){ //숫자 이외의 값이 들어갔을때 예외발생
            System.out.println("메뉴는 숫자로 입력하세요.");
        }
    }
    @Override
    public final void run(){ //절대 바뀌면 안되는 절차 -> final
        common();
        show();
        prompt();
    }

    public void change(int menuNo){
        Menu menu = null;
        switch (menuNo){
            case 1: menu = Menu.JOIN; break; //회원가입
            case 2:  menu = Menu.LOGIN; break; //로그인
            default: menu = Menu.MAIN; //메인 메뉴
        }
        //메뉴 컨트롤러 변경 처리 -> Router 구현체에 넣어줌
        MainRouter.getInstance().change(menu);
    }

    /*
    입력과 검증을 함께 진행
    * @param message: 항목 메시지
    * @param predicate: 판별식
    * @return
    * */
    protected String propmtWithValidation(String message, Predicate<String> predicate){
        String str = null;
        do{
            System.out.print(message);
            str = sc.nextLine();
        }while (!predicate.test(str)); //판별식이 실패했을 경우 계속 반복

        return str;
    }
}
