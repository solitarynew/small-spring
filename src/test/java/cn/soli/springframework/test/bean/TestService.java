package cn.soli.springframework.test.bean;

public class TestService {

    private TestDao testDao;

    private String name;

    public void test() {
        System.out.println(name + ": TestService.test()");
        testDao.test();
    }

}
