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

public class SQLVariableTokenDiffblueTest {
  /**
   * Test new {@link SQLVariableToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLVariableToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLVariableToken.<init>()"})
  public void testNewSQLVariableToken() {
    // Arrange and Act
    SQLVariableToken actualSqlVariableToken = new SQLVariableToken();

    // Assert
    TPTokenType data = actualSqlVariableToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_VARIABLE, data);
    assertFalse(actualSqlVariableToken.isEOF());
    assertFalse(actualSqlVariableToken.isOther());
    assertFalse(actualSqlVariableToken.isUndefined());
    assertFalse(actualSqlVariableToken.isWhitespace());
  }
}
