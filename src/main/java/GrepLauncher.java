package main.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;

public class GrepLauncher {

    @Option(name = "-i", metaVar = "Ignore Case", usage = "Ignore case")
    private boolean _ignoreCase;

    @Option(name = "-v", metaVar = "Invert Filter", usage = "Reverse filter")
    private boolean _invertFilter;

    @Option(name = "-r", metaVar = "-r", usage = "Regex filter")
    private boolean _regex;

    @Argument(required = true, metaVar = "word", usage = "Word / Regex for searching")
    private String word;

    @Argument(required = true, metaVar = "Input File Name", index = 1, usage = "Input file name")
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
            ArrayList<String> result = grep.filter(word, inputFileName);
            for (Object output : result) {
                System.out.println(output);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


}
