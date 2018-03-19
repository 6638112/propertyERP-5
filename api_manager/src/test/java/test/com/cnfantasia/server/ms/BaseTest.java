package test.com.cnfantasia.server.ms;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	
	protected ApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(
				"classpath:/com/cnfantasia/server/common/config/core/core.xml",
				"classpath:/com/cnfantasia/server/ms/ms_all.xml"
				);
	}
}
