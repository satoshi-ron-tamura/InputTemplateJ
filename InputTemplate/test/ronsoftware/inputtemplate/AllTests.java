package ronsoftware.inputtemplate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ContentTest.class, ParserTest.class, TokenReaderTest.class })
public class AllTests {

}
