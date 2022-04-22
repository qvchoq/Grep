package grep.main;

import java.io.*;
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
            File file = new File(inputFileName);
            BufferedReader in = new BufferedReader(new FileReader(file));

            if (!regex) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (invertFilter) {
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
            if (regex) {
                Pattern pattern = Pattern.compile(ignoreCase(word));
                String line;
                while ((line = in.readLine()) != null) {
                    Matcher matcher = pattern.matcher(ignoreCase(line));
                    if (invertFilter) {
                        if (!matcher.find()) {
                            System.out.println(line);;
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
        if (ignoreCase) {
            return text.toLowerCase();
        } else {
            return text;
        }
    }

}