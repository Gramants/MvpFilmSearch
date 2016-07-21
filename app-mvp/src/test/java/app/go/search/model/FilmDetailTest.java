package app.go.search.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gramantieri on 22/05/2016.
 */
public class FilmDetailTest {


private FilmDetail fd;
    @Before
    public void setUp() throws Exception {
    fd=new FilmDetail();
    }

    @Test
    public void testTestJunitResultOK() throws Exception {
        assertEquals("Rocky-test-1988", fd.testJunitResult("Rocky","1988"));
    }

    @Test
    public void testTestJunitResultKO() throws Exception {
        assertEquals("Rocky-test-1988", fd.testJunitResult("Rocky IV", "1988"));
    }
}