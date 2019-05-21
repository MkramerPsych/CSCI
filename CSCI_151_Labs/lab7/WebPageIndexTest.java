import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class WebPageIndexTest {

	@Test
	public void testMain() {
		try {
			WebPageIndex test = new WebPageIndex("test1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
