
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


class GrepTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    void assertFileContent(String expectedContent) {
        String content = output.toString();
        assertEquals(expectedContent, content);
    }

    @Test
    public void test1() throws IOException {
        Grep grep = new Grep(true, false, false);
        grep.filter("собака", "files/test_1.txt");
        assertFileContent("""
                *-------------------------------*
                собака съела
                собака рыла ямУ
                СОБАКА
                *-------------------------------*
                """);


    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
