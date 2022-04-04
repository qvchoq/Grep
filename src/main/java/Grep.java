package main.java;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final boolean _ignoreCase;
    private final boolean _regex;
    private final boolean _invertFilter;

    public Grep(boolean _ignoreCase, boolean _regex, boolean _invertFilter) {

        this._ignoreCase = _ignoreCase;
        this._regex = _regex;
        this._invertFilter = _invertFilter;

    }

    public void filter(String word, String inputFileName) throws IOException {
        try {
            File file = new File(inputFileName);
            BufferedReader in = new BufferedReader(new FileReader(file));

            if (!_regex) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (_invertFilter) {
                        if (!ignoreCase(line).contains(ignoreCase(word))) {
                            System.out.println(line);
                        }
                    } else {
                        if (ignoreCase(line).contains(ignoreCase(word))) {
                            System.out.println(line);
                        }
                    }
                }
            }
            if (_regex) {
                Pattern pattern = Pattern.compile(ignoreCase(word));
                String line;
                while ((line = in.readLine()) != null) {
                    Matcher matcher = pattern.matcher(ignoreCase(line));
                    if (_invertFilter) {
                        if (!matcher.find()) {
                            System.out.println(line);
                        }
                    } else {
                        if (matcher.find()) {
                            System.out.println(line);
                        }
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String ignoreCase(String text) throws IOException {
        if (_ignoreCase) {
            return text.toLowerCase();
        } else {
            return text;
        }

    }

}