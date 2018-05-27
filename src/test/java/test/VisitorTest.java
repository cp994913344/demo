package test;


import com.ljs.demo.common.mail.VisitorServcie;
import com.ljs.demo.pojo.domain.Visitor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VisitorTest {

    @Autowired
    VisitorServcie visitorServcie;

    @Test
    public void selectByPrimaryKey(){
        Visitor visitor = visitorServcie.selectByPrimaryKey(1);
        System.out.println(visitor.toString());
    }

}
