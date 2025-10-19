package org.jkiss.dbeaver.model.lsm.sql.dialect;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.jkiss.dbeaver.model.lsm.LSMAnalyzer;
import org.jkiss.dbeaver.model.lsm.LSMAnalyzerParameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLStandardAnalyzerFactoryDiffblueTest {
  /**
   * Test {@link SQLStandardAnalyzerFactory#createAnalyzer(LSMAnalyzerParameters)}.
   *
   * <p>Method under test: {@link SQLStandardAnalyzerFactory#createAnalyzer(LSMAnalyzerParameters)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LSMAnalyzer SQLStandardAnalyzerFactory.createAnalyzer(LSMAnalyzerParameters)"
  })
  public void testCreateAnalyzer() {
    // Arrange
    SQLStandardAnalyzerFactory sqlStandardAnalyzerFactory = new SQLStandardAnalyzerFactory();
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);

    // Act
    LSMAnalyzer actualCreateAnalyzerResult = sqlStandardAnalyzerFactory.createAnalyzer(parameters);

    // Assert
    assertTrue(actualCreateAnalyzerResult instanceof SQLStandardAnalyzer);
  }
}
