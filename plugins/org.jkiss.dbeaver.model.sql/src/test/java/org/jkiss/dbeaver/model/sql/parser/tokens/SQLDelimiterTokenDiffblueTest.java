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

public class SQLDelimiterTokenDiffblueTest {
  /**
   * Test new {@link SQLDelimiterToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLDelimiterToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterToken.<init>()"})
  public void testNewSQLDelimiterToken() {
    // Arrange and Act
    SQLDelimiterToken actualSqlDelimiterToken = new SQLDelimiterToken();

    // Assert
    TPTokenType data = actualSqlDelimiterToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_DELIMITER, data);
    assertFalse(actualSqlDelimiterToken.isEOF());
    assertFalse(actualSqlDelimiterToken.isOther());
    assertFalse(actualSqlDelimiterToken.isUndefined());
    assertFalse(actualSqlDelimiterToken.isWhitespace());
  }
}
