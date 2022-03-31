import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {

    @Option(name = "-i", metaVar = "Ignore Case", usage = "Ignore case")
    private boolean _ignoreCase;

    @Option(name = "-v", metaVar = "Invert Filter", usage = "Reverse filter")
    private boolean _invertFilter;

    @Argument(metaVar = "-r", usage = "Regex filter")
    private boolean _regex;

    @Argument(required = true, metaVar = "word", index = 1, usage = "Word / Regex for searching")
    private String word;

    @Argument(required = true, metaVar = "Input File Name", index = 2, usage = "Input file name")
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
            String result = grep.filter(word, inputFileName);
            System.out.println(result);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


}
