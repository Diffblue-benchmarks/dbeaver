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

public class SQLBlockEndTokenDiffblueTest {
  /**
   * Test new {@link SQLBlockEndToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLBlockEndToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLBlockEndToken.<init>()"})
  public void testNewSQLBlockEndToken() {
    // Arrange and Act
    SQLBlockEndToken actualSqlBlockEndToken = new SQLBlockEndToken();

    // Assert
    TPTokenType data = actualSqlBlockEndToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_BLOCK_END, data);
    assertFalse(actualSqlBlockEndToken.isEOF());
    assertFalse(actualSqlBlockEndToken.isOther());
    assertFalse(actualSqlBlockEndToken.isUndefined());
    assertFalse(actualSqlBlockEndToken.isWhitespace());
  }
}
