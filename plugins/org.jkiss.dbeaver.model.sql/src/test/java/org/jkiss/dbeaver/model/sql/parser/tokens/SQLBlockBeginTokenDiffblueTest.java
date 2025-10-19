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

public class SQLBlockBeginTokenDiffblueTest {
  /**
   * Test new {@link SQLBlockBeginToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLBlockBeginToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLBlockBeginToken.<init>()"})
  public void testNewSQLBlockBeginToken() {
    // Arrange and Act
    SQLBlockBeginToken actualSqlBlockBeginToken = new SQLBlockBeginToken();

    // Assert
    TPTokenType data = actualSqlBlockBeginToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_BLOCK_BEGIN, data);
    assertFalse(actualSqlBlockBeginToken.isEOF());
    assertFalse(actualSqlBlockBeginToken.isOther());
    assertFalse(actualSqlBlockBeginToken.isUndefined());
    assertFalse(actualSqlBlockBeginToken.isWhitespace());
  }
}
