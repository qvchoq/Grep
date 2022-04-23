package GrepTest;

import grep.main.Grep;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class GrepTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    void assertConsoleContent(String expectedContent) {
        String content = output.toString();
        assertEquals(expectedContent, content);
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyWord() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.filter(null, "./resources/test_1.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotExistingFile() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.filter("", "./resources/test_3.txt");
        assertConsoleContent("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPath() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.filter("word", "");
        assertConsoleContent("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDirectory() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.filter("word", "./resources/");
        assertConsoleContent("");
    }

    @Test
    public void testEmptyFile() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.filter("word", "./resources/test_empty.txt");
        assertConsoleContent("");
    }

    @Test
    public void testIgnoreCase() throws IOException {
        Grep grep = new Grep(true, false, false);
        grep.filter("собака", "./resources/test_1.txt");
        assertConsoleContent("собака съела" + System.lineSeparator() +
                "собака рыла ямУ" + System.lineSeparator() +
                "СОБАКА" + System.lineSeparator());
    }

    @Test
    public void testRegex() throws IOException {
        Grep grep = new Grep(false, true, false);
        grep.filter("\\d+", "./resources/test_2.txt");
        assertConsoleContent("27 июня 1775 года Филлип Скайлер, командующий американскими войсками в Нью-Йорке," + System.lineSeparator() +
                        "Скайлер смог собрать всего около 1000 человек, которых он и направил на север под командованием генерала Ричарда Монтгомери." + System.lineSeparator() +
                "14 ноября Арнольд подошёл к Квебеку, а Монтгомери захватил Монреаль и присоединился к Арнольду 2 декабря." + System.lineSeparator() +
                "а Арнольд был ранен в ногу. В мае на помощь Карлтону прибыли 9000 британских солдат и 4000 гессенцев под командованием" + System.lineSeparator());
    }

    @Test
    public void testInvertFilter() throws IOException {
        Grep grep = new Grep(false, false, true);
        grep.filter("Монреаль", "./resources/test_2.txt");
        assertConsoleContent("Ещё в самом начале Войны за независимость, когда американская Континентальная армия осаждала Бостон," + System.lineSeparator() +
                "Конгресс США стал рассматривать возможность вторжения в Канаду (провинцию Квебека)." + System.lineSeparator() +
                "27 июня 1775 года Филлип Скайлер, командующий американскими войсками в Нью-Йорке," + System.lineSeparator() +
                "получил указание захватить часть Канады, пользуясь тем, что британские войска в Канаде крайне немногочисленны." + System.lineSeparator() +
                "Скайлер смог собрать всего около 1000 человек, которых он и направил на север под командованием генерала Ричарда Монтгомери." + System.lineSeparator() +
                "Квебек под командованием Бенедикта Арнольда." + System.lineSeparator() +
                "а Арнольд был ранен в ногу. В мае на помощь Карлтону прибыли 9000 британских солдат и 4000 гессенцев под командованием" + System.lineSeparator());
    }


    @Test
    public void testNoOptions() throws IOException {
        Grep grep = new Grep(false, false, false);
        grep.filter("Филлип", "./resources/test_2.txt");
        assertConsoleContent("27 июня 1775 года Филлип Скайлер, командующий американскими войсками в Нью-Йорке," + System.lineSeparator());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(System.out);
    }
}
