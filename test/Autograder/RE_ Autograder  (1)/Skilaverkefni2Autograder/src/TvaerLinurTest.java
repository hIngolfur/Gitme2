import com.gradescope.jh61b.grader.GradedTest;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/******************************************************************************
 *  Nafn    : nafn höfundar
 *  T-póstur: tölvupóstfang höfundar
 *
 *  Lýsing  : Lýsing á hvað forrit gerir, t.d. inntak, reikningar og úttak
 *
 *
 *****************************************************************************/

public class TvaerLinurTest {
    @Test
    @GradedTest(name = "TvaerLinur", max_score = 5)
    public void tvaerLinur() {
        String[] inntak = { };
        String uttak = "(?i)\\s*Fyrsta vikan í skólanum var skemmtileg\\s*\\n" +
                "\\s*Ég gat klárað þetta verkefni\\s*";

        // Endurbeina úttaki frá System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TvaerLinur.main(inntak);
        boolean b = Pattern.matches(uttak, outContent.toString());
        assertTrue(b);
    }
}
