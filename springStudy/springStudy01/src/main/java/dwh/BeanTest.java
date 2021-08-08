package dwh;

import dwh.entity.TestBean;
import dwh.service.SearchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 测试启动类
 * @author: dengwenhao
 * @create: 2021-05-30
 **/
public class BeanTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        SearchService service = (SearchService) context.getBean("service");
        service.connect();
        TestBean test1 = (TestBean) context.getBean("bean3");
        TestBean test2 = (TestBean) context.getBean("beanByOneCon");
        System.out.println(test1.toString());
    }
}
