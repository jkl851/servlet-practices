## JSP



helloweb 프로젝트 생성후(war로 생성한다)

Java EE Tools - Generate Deployment Descriptor stub

![화면 캡처 2021-10-07 113224](img/1.png)



JSP 파일을 생성한다. 생성이후 오류가 잡혀있는데, 톰캣 런타임 설정을 해줘야한다.

![화면 캡처 2021-10-07 115245](img/2.png)



프로젝트 폴더에 properties

![image-20211007115612266](img/3.png)



톰캣 체크 후 apply

![image-20211007115720712](img/4.png)



이후 project-clean로 이동

![image-20211007115851025](img/5.png)



자신의 프로젝트를 선택후 clean을 누른다.

![image-20211007115909376](img/6.png)

hello.JSP의 런타임이 톰캣으로 설정되면서 오류가 사라진다. 



**어플리케이션 Add**

이클립스 하단의 Servers - 마우스 오른쪽 - Add

![image-20211007120536225](img/7.png)



오른쪽으로 프로젝트를 add 한다

![image-20211007120509422](img/8.png)



서버실행 : 오른쪽의 재생버튼 클릭

![image-20211007120711512](img/9.png)



URL을 통해 브라우저에서 .jsp 확인

![image-20211007120914265](img/10.png)



## Servlet



![image-20211007121026652](img/11.png)



URL을 소문자를 사용하여 간단히 재설정한다.

![image-20211007121216055](img/12.png)



abstract method 에서 필요한 부분만 고르며,

Constructors from superclass 체크해제

![image-20211007121259886](img/13.png)



간단한 코드작성후

![image-20211007122300415](img/14.png)



재설정한 URL을 통해 브라우저에서 확인

![image-20211007122320588](img/15.png)



