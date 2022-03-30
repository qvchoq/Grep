
import java.io.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class Grep {

    private final boolean _ignoreCase;
    private final boolean _regex;
    private final boolean _invertFilter;

    public Grep(boolean _ignoreCase, boolean _regex, boolean _invertFilter) {

        this._ignoreCase = _ignoreCase;
        this._regex = _regex;
        this._invertFilter = _invertFilter;

    }

    public String filter(String word, String inputFileName) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        System.out.println("*-------------------------------*");
        try {
            File file = new File(inputFileName);
            BufferedReader in = new BufferedReader(new FileReader(file));

            if (!_regex) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (_invertFilter) {
                        if (!line.contains(word)) {
                            result.add(line);
                        }
                    } else {
                        if (line.contains(word)) {
                            result.add(line);
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
                            result.add(line);
                        }

                    } else {
                        if (matcher.find()) {
                            result.add(line);
                        }
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        for (String s : result) {
            System.out.println(s);
        }
        return "*-------------------------------*";
    }

    public String ignoreCase(String text) throws IOException {
        if (_ignoreCase) {
            return text.toLowerCase();
        } else {
            return text;
        }

    }

}