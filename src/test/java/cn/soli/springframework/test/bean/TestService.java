package cn.soli.springframework.test.bean;

public class TestService {

    private String name;

    public TestService(String name) {
        this.name = name;
    }

    public void test() {
        System.out.println("TestService.test(): " + name);
    }

}
