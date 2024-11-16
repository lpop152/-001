package campus;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringbootDatasourceTest {

	@Autowired
	DataSource source;
	
	public void testDataSource() throws SQLException{
		System.out.println(source.getClass());
	}
}
