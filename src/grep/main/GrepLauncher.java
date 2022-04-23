package grep.main;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.nio.file.Path;

public class GrepLauncher {

    @Option(name = "-i", usage = "Ignore case")
    private boolean ignoreCase;

    @Option(name = "-v", usage = "Reverse filter")
    private boolean invertFilter;

    @Option(name = "-r", forbids = {"-i"}, usage = "Regex filter")
    private boolean regex;

    @Argument(required = true, metaVar = "word")
    private String word;

    @Argument(required = true, index = 1, metaVar = "InputFileName")
    private Path inputFileName;


    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar [-v] [-r] [-i] word inputFileName.txt");
            parser.printUsage(System.err);
            System.exit(-1);
        }

        Grep grep = new Grep(ignoreCase, regex, invertFilter);
        try {
            grep.filter(word, String.valueOf(inputFileName));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}