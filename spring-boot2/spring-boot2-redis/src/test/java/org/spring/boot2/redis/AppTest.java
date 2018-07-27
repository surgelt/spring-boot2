package org.spring.boot2.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot2.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	public static Logger logger = LoggerFactory.getLogger(AppTest.class);

	@Test
	public void contextLoads() {
	}

	@Autowired
	RedisDao redisDao;

	@Test
	public void testRedis() {
		redisDao.setKey("name", "forezp");
		redisDao.setKey("age", "11");
		logger.info(redisDao.getValue("name"));
		logger.info(redisDao.getValue("age"));
	}
}
