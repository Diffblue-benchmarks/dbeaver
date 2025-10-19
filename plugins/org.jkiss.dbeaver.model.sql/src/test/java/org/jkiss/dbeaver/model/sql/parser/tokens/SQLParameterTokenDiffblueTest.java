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

public class SQLParameterTokenDiffblueTest {
  /**
   * Test new {@link SQLParameterToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLParameterToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLParameterToken.<init>()"})
  public void testNewSQLParameterToken() {
    // Arrange and Act
    SQLParameterToken actualSqlParameterToken = new SQLParameterToken();

    // Assert
    TPTokenType data = actualSqlParameterToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_PARAMETER, data);
    assertFalse(actualSqlParameterToken.isEOF());
    assertFalse(actualSqlParameterToken.isOther());
    assertFalse(actualSqlParameterToken.isUndefined());
    assertFalse(actualSqlParameterToken.isWhitespace());
  }
}
