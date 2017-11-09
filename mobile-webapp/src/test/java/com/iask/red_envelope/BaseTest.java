package com.iask.red_envelope;

import com.iask.red_envelope.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @author wwg
 *         测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/spring/spring-profile.xml"})
@ActiveProfiles(profiles = "development")
public class BaseTest {
    @Test
    public void testLoad() {


        BigDecimal a = new BigDecimal("0.20");
        BigDecimal b = new BigDecimal("0.21");

        // 随机生成一个红包
        BigDecimal multiply = new BigDecimal("100.00");
        int minMoney = a.multiply(multiply).intValue();
        int maxMoney = b.multiply(multiply).intValue();
        int randomMoney = minMoney;
        if(maxMoney > minMoney) {
            randomMoney = RandomUtil.getRandomBetweenMaxAndMin(maxMoney, minMoney);
        }
        String s_money = String.format("%.2f", (double)(randomMoney/100.0));

        System.out.println(">>>>"+s_money);


    }

}
