import dao.ConnectionProvider;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class ConnectionProviderTest extends TestCase {

    @Test
    public void connectionTest(){
        assertEquals(true,ConnectionProvider.getConnection()!=null);
    }
}
