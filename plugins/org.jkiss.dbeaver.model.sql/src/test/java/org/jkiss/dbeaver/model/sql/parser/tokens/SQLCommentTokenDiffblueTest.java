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

public class SQLCommentTokenDiffblueTest {
  /**
   * Test new {@link SQLCommentToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLCommentToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentToken.<init>()"})
  public void testNewSQLCommentToken() {
    // Arrange and Act
    SQLCommentToken actualSqlCommentToken = new SQLCommentToken();

    // Assert
    TPTokenType data = actualSqlCommentToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_COMMENT, data);
    assertFalse(actualSqlCommentToken.isEOF());
    assertFalse(actualSqlCommentToken.isOther());
    assertFalse(actualSqlCommentToken.isUndefined());
    assertFalse(actualSqlCommentToken.isWhitespace());
  }
}
