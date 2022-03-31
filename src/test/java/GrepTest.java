import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class GrepTest {

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
        grep.filter("собака", "src/files/test_1.txt");
        assertFileContent("""
                собака съела\r
                собака рыла ямУ\r
                СОБАКА\r
                """);
    }

    @Test
    public void test2() throws IOException {
        Grep grep = new Grep(true, true, false);
        grep.filter("[+у+]", "src/files/test_1.txt");
        assertFileContent("""
                собеку\r
                собака рыла ямУ\r
                выгуляли собаку\r
                """);
    }

    @Test
    public void test3() throws IOException {
        Grep grep = new Grep(true, true, true);
        grep.filter("[+у+]", "src/files/test_1.txt");
        assertFileContent("""
                собака съела\r
                кошка нашла\r
                СОБАКА\r
                sobaku\r
                sobakU\r
                """);
    }

    @Test
    public void test4() throws IOException {
        Grep grep = new Grep(true, false, false);
        grep.filter("человек", "src/files/test_2.txt");
        assertFileContent("""
                Скайлер смог собрать всего около 1000 человек, которых он и направил на север под командованием генерала Ричарда Монтгомери.\r
                """);
    }

    @Test
    public void test5() throws IOException {
        Grep grep = new Grep(false, true, false);
        grep.filter("\\d+", "src/files/test_2.txt");
        assertFileContent("""
                27 июня 1775 года Филлип Скайлер, командующий американскими войсками в Нью-Йорке,\r
                Скайлер смог собрать всего около 1000 человек, которых он и направил на север под командованием генерала Ричарда Монтгомери.\r
                14 ноября Арнольд подошёл к Квебеку, а Монтгомери захватил Монреаль и присоединился к Арнольду 2 декабря.\r
                а Арнольд был ранен в ногу. В мае на помощь Карлтону прибыли 9000 британских солдат и 4000 гессенцев под командованием\r
                """);
    }

    @Test
    public void test6() throws IOException {
        Grep grep = new Grep(false, true, false);
        grep.filter("\\d+", "src/files/test_2.txt");
        assertFileContent("""
                27 июня 1775 года Филлип Скайлер, командующий американскими войсками в Нью-Йорке,\r
                Скайлер смог собрать всего около 1000 человек, которых он и направил на север под командованием генерала Ричарда Монтгомери.\r
                14 ноября Арнольд подошёл к Квебеку, а Монтгомери захватил Монреаль и присоединился к Арнольду 2 декабря.\r
                а Арнольд был ранен в ногу. В мае на помощь Карлтону прибыли 9000 британских солдат и 4000 гессенцев под командованием\r
                """);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
