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

public class SQLSetDelimiterTokenDiffblueTest {
  /**
   * Test new {@link SQLSetDelimiterToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLSetDelimiterToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSetDelimiterToken.<init>()"})
  public void testNewSQLSetDelimiterToken() {
    // Arrange and Act
    SQLSetDelimiterToken actualSqlSetDelimiterToken = new SQLSetDelimiterToken();

    // Assert
    TPTokenType data = actualSqlSetDelimiterToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_SET_DELIMITER, data);
    assertFalse(actualSqlSetDelimiterToken.isEOF());
    assertFalse(actualSqlSetDelimiterToken.isOther());
    assertFalse(actualSqlSetDelimiterToken.isUndefined());
    assertFalse(actualSqlSetDelimiterToken.isWhitespace());
  }
}
