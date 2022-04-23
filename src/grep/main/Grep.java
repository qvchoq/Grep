package grep.main;

import java.io.IOException;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final boolean ignoreCase;
    private final boolean regex;
    private final boolean invertFilter;

    public Grep(boolean ignoreCase, boolean regex, boolean invertFilter) {
        this.ignoreCase = ignoreCase;
        this.regex = regex;
        this.invertFilter = invertFilter;
    }

    public void filter(String word, String inputFileName) throws IOException {
        try {
            ArrayList<String> result = new ArrayList<>();

            File file = new File(inputFileName);
            BufferedReader in = new BufferedReader(new FileReader(file));

            Pattern pattern = Pattern.compile(word);
            String line;

            while ((line = in.readLine()) != null) {
                if (regex) {
                    Matcher matcher = pattern.matcher(line);
                    if (invertFilter) {
                        if (!matcher.find()) {
                            result.add(line);
                        }
                    } else {
                        if (matcher.find()) {
                            result.add(line);
                        }
                    }
                }
                if (!regex) {
                    if (invertFilter) {
                        if (!ignoreCase(line).contains(ignoreCase(word))) {
                            result.add(line);
                        }
                    } else {
                        if (ignoreCase(line).contains(ignoreCase(word))) {
                            result.add(line);
                        }
                    }
                }
            }
            in.close();
            result.forEach(System.out::println);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String ignoreCase(String text) throws IOException {
        if (ignoreCase) {
            return text.toLowerCase();
        } else {
            return text;
        }
    }
}