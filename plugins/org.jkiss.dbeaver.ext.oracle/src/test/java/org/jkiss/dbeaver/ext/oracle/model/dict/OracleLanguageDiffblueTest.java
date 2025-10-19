package org.jkiss.dbeaver.ext.oracle.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleLanguageDiffblueTest {
  /**
   * Test {@link OracleLanguage#getLanguage()}.
   *
   * <p>Method under test: {@link OracleLanguage#getLanguage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleLanguage.getLanguage()"})
  public void testGetLanguage() {
    // Arrange, Act and Assert
    assertEquals("AMERICAN", OracleLanguage.valueOf("AMERICAN").getLanguage());
  }
}
