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

public class SQLBlockHeaderTokenDiffblueTest {
  /**
   * Test new {@link SQLBlockHeaderToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLBlockHeaderToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLBlockHeaderToken.<init>()"})
  public void testNewSQLBlockHeaderToken() {
    // Arrange and Act
    SQLBlockHeaderToken actualSqlBlockHeaderToken = new SQLBlockHeaderToken();

    // Assert
    TPTokenType data = actualSqlBlockHeaderToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_BLOCK_HEADER, data);
    assertFalse(actualSqlBlockHeaderToken.isEOF());
    assertFalse(actualSqlBlockHeaderToken.isOther());
    assertFalse(actualSqlBlockHeaderToken.isUndefined());
    assertFalse(actualSqlBlockHeaderToken.isWhitespace());
  }
}
