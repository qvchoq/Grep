package main.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;
import java.io.IOException;

public class GrepLauncher {

    @Option(name = "-i", usage = "Ignore case")
    private boolean _ignoreCase;

    @Option(name = "-v", usage = "Reverse filter")
    private boolean _invertFilter;

    @Option(name = "-r", usage = "Regex filter")
    private boolean _regex;

    @Argument(required = true, metaVar = "word", usage = "Word / Regex with regex filter")
    private String word;

    @Argument(required = true, metaVar = "InputFileName", index = 1, usage = "path to Inputfilename.txt")
    private String inputFileName;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar [-v] [-r] [-i] word inputName.txt");
            parser.printUsage(System.err);
        }

        Grep grep = new Grep(_ignoreCase, _regex, _invertFilter);
        try {
           grep.filter(word, inputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


}
