package org.choongang.global;

import org.choongang.global.constants.Menu;

public interface ControllerLocator {

    //어떤 컨트롤러가 올지 모르기때문에 다형성 이용
    Controller find(Menu menu);
}
