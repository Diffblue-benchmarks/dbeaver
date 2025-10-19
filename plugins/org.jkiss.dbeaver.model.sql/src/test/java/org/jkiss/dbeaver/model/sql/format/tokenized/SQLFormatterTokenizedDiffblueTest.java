package org.jkiss.dbeaver.model.sql.format.tokenized;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLFormatterTokenizedDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLFormatterTokenized#setCompact(boolean)}
   *   <li>{@link SQLFormatterTokenized#isCompact()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SQLFormatterTokenized.isCompact()",
    "void SQLFormatterTokenized.setCompact(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLFormatterTokenized sqlFormatterTokenized = new SQLFormatterTokenized();

    // Act
    sqlFormatterTokenized.setCompact(true);

    // Assert
    assertTrue(sqlFormatterTokenized.isCompact());
  }

  /**
   * Test new {@link SQLFormatterTokenized} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLFormatterTokenized}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFormatterTokenized.<init>()"})
  public void testNewSQLFormatterTokenized() {
    // Arrange, Act and Assert
    assertFalse(new SQLFormatterTokenized().isCompact());
  }
}
