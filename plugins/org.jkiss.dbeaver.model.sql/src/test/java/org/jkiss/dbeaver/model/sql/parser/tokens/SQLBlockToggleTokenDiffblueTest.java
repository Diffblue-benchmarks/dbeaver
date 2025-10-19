package org.jkiss.dbeaver.model.sql.parser.tokens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.text.parser.TPTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLBlockToggleTokenDiffblueTest {
  /**
   * Test new {@link SQLBlockToggleToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLBlockToggleToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLBlockToggleToken.<init>()"})
  public void testNewSQLBlockToggleToken() {
    // Arrange and Act
    SQLBlockToggleToken actualSqlBlockToggleToken = new SQLBlockToggleToken();

    // Assert
    TPTokenType data = actualSqlBlockToggleToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_BLOCK_TOGGLE, data);
    assertFalse(actualSqlBlockToggleToken.isEOF());
    assertFalse(actualSqlBlockToggleToken.isOther());
    assertFalse(actualSqlBlockToggleToken.isUndefined());
    assertFalse(actualSqlBlockToggleToken.isWhitespace());
  }
}
