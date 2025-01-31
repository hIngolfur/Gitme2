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

public class UtreikningurTest {
    @Test
    @GradedTest(name = "Utreikningur", max_score = 5)
    public void reikna() {
        String[] inntak = { };
        String uttak = "(?i)\\s*17\\s*";

        // Endurbeina úttaki frá System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Utreikningur.main(inntak);
        boolean b = Pattern.matches(uttak, outContent.toString());
        assertTrue(b);
    }
}
