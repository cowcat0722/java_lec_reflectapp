package ex02;

public class UserController {


    // /login
    @RequestMapping(uri = "/login")
    public void login() {
        System.out.println("로그인 호출됨");
    }

    // /join
    @RequestMapping(uri = "/join")
    public void join() {
        System.out.println("회원가입 호출됨");
    }

    @RequestMapping(uri = "/userinfo")
    public void userinfo() {
        System.out.println("유저정보 보기");
    }

    @RequestMapping(uri = "/updatepassword")
    public void updatePassword() {
        System.out.println("패스워드 수정하기");
    }
}
