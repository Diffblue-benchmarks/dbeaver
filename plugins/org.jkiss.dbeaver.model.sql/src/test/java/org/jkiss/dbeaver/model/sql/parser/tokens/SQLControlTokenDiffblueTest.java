package org.jkiss.dbeaver.model.sql.parser.tokens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.text.parser.TPTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLControlTokenDiffblueTest {
  /**
   * Test {@link SQLControlToken#SQLControlToken()}.
   *
   * <p>Method under test: {@link SQLControlToken#SQLControlToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLControlToken.<init>()"})
  public void testNewSQLControlToken() {
    // Arrange and Act
    SQLControlToken actualSqlControlToken = new SQLControlToken();

    // Assert
    TPTokenType data = actualSqlControlToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertNull(actualSqlControlToken.getCommandId());
    assertEquals(SQLTokenType.T_CONTROL, data);
    assertFalse(actualSqlControlToken.isEOF());
    assertFalse(actualSqlControlToken.isOther());
    assertFalse(actualSqlControlToken.isUndefined());
    assertFalse(actualSqlControlToken.isWhitespace());
  }

  /**
   * Test {@link SQLControlToken#SQLControlToken(String)}.
   *
   * <p>Method under test: {@link SQLControlToken#SQLControlToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLControlToken.<init>(String)"})
  public void testNewSQLControlToken2() {
    // Arrange and Act
    SQLControlToken actualSqlControlToken = new SQLControlToken("42");

    // Assert
    TPTokenType data = actualSqlControlToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals("42", actualSqlControlToken.getCommandId());
    assertEquals(SQLTokenType.T_CONTROL, data);
    assertFalse(actualSqlControlToken.isEOF());
    assertFalse(actualSqlControlToken.isOther());
    assertFalse(actualSqlControlToken.isUndefined());
    assertFalse(actualSqlControlToken.isWhitespace());
  }

  /**
   * Test {@link SQLControlToken#getCommandId()}.
   *
   * <p>Method under test: {@link SQLControlToken#getCommandId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLControlToken.getCommandId()"})
  public void testGetCommandId() {
    // Arrange, Act and Assert
    assertNull(new SQLControlToken().getCommandId());
  }
}
