package org.jkiss.dbeaver.model.sql.format.tokenized;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLFormatterCompactDiffblueTest {
  /**
   * Test new {@link SQLFormatterCompact} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLFormatterCompact}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFormatterCompact.<init>()"})
  public void testNewSQLFormatterCompact() {
    // Arrange, Act and Assert
    assertTrue(new SQLFormatterCompact().isCompact());
  }
}
