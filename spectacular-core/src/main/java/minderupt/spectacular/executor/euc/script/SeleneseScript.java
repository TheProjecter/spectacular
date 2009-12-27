package minderupt.spectacular.executor.euc.script;

import minderupt.spectacular.util.TokenParser;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class SeleneseScript {

    private static final String LINESEP = System.getProperty("line.separator");

    private String body;
    private List<SelenesePart> flowParts;
    private List<SelenesePart> expectationParts;

    public SeleneseScript() {
        this.body = "";
        flowParts = new LinkedList<SelenesePart>();
        expectationParts = new LinkedList<SelenesePart>();
    }

    public SeleneseScript(String body) {
        this.body = body;
        flowParts = new LinkedList<SelenesePart>();
        expectationParts = new LinkedList<SelenesePart>();

        parse();

    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        parse();
    }

    public List<SelenesePart> getFlowParts() {
        return flowParts;
    }

    public void setFlowParts(List<SelenesePart> flowParts) {
        this.flowParts = flowParts;
    }

    public List<SelenesePart> getExpectationParts() {
        return expectationParts;
    }

    public void setExpectationParts(List<SelenesePart> expectationParts) {
        this.expectationParts = expectationParts;
    }

    private void parse() {


        SelenesePart currentPart = null;
        String partType = null;

        String[] lines = body.split(LINESEP);
        for (String line : lines) {

            // clean up whitespace
            line = line.trim();

            // is this a step definition?
            if (line.indexOf("Flow:") == 0 || line.indexOf("Expectation:") == 0) {

                // we hit a step definition.
                // if there is a current part, add to script
                if (currentPart != null) {
                    if (partType.equals("flow")) flowParts.add(currentPart);
                    if (partType.equals("expectation")) expectationParts.add(currentPart);
                }

                // if current part is null, then this is the first part!
                currentPart = new SelenesePart();

                // take everything after the Flow: or Expectation:
                if (line.toLowerCase().indexOf("flow:") == 0) {
                    String step = line.substring(5).trim();
                    partType = "flow";
                    currentPart.setStep(step);
                }

                if (line.toLowerCase().indexOf("expectation:") == 0) {
                    String step = line.substring(12).trim();
                    partType = "expectation";
                    currentPart.setStep(step);
                }


            } else if (line != null && line.length() > 3) {

                List<String> command = new TokenParser(line).parse();
                currentPart.addCommand(command);

            } else {
                continue;
            }

        }

        // take what we've done and commit!
        if (currentPart != null) {
            if (partType.equals("flow")) flowParts.add(currentPart);
            if (partType.equals("expectation")) expectationParts.add(currentPart);
        }

    }

    private String flipDelims(String currentDelim) {
        if (currentDelim.equals(" ")) {
            return ("\"");
        } else {
            return (" ");
        }
    }

}
