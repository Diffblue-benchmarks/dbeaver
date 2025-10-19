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

public class SQLMultilineCommentTokenDiffblueTest {
  /**
   * Test new {@link SQLMultilineCommentToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLMultilineCommentToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLMultilineCommentToken.<init>()"})
  public void testNewSQLMultilineCommentToken() {
    // Arrange and Act
    SQLMultilineCommentToken actualSqlMultilineCommentToken = new SQLMultilineCommentToken();

    // Assert
    TPTokenType data = actualSqlMultilineCommentToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_COMMENT, data);
    assertFalse(actualSqlMultilineCommentToken.isEOF());
    assertFalse(actualSqlMultilineCommentToken.isOther());
    assertFalse(actualSqlMultilineCommentToken.isUndefined());
    assertFalse(actualSqlMultilineCommentToken.isWhitespace());
  }
}
