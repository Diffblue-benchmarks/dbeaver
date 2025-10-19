package org.jkiss.dbeaver.model.sql.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPIdentifierCase;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLFormatterConfigurationDiffblueTest {
  /**
   * Test {@link SQLFormatterConfiguration#SQLFormatterConfiguration(DBPDataSource,
   * SQLSyntaxManager, String)}.
   *
   * <ul>
   *   <li>Then return IndentString is space space space space.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#SQLFormatterConfiguration(DBPDataSource,
   * SQLSyntaxManager, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLFormatterConfiguration.<init>(DBPDataSource, SQLSyntaxManager, String)"
  })
  public void testNewSQLFormatterConfiguration_thenReturnIndentStringIsSpaceSpaceSpaceSpace() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);

    // Act
    SQLFormatterConfiguration actualSqlFormatterConfiguration =
        new SQLFormatterConfiguration(dataSource, syntaxManager, "42");

    // Assert
    verify(syntaxManager).getKeywordCase();
    assertEquals("    ", actualSqlFormatterConfiguration.getIndentString());
    assertEquals("42", actualSqlFormatterConfiguration.getFormatterId());
    assertEquals("UTF-8", actualSqlFormatterConfiguration.getSourceEncoding());
    assertNull(actualSqlFormatterConfiguration.getPreferenceStore());
    assertEquals(DBPIdentifierCase.UPPER, actualSqlFormatterConfiguration.getKeywordCase());
    assertSame(dataSource, actualSqlFormatterConfiguration.getDataSource());
    assertSame(syntaxManager, actualSqlFormatterConfiguration.getSyntaxManager());
  }

  /**
   * Test {@link SQLFormatterConfiguration#isFunction(String)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getFunctions()} return {@link
   *       ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#isFunction(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLFormatterConfiguration.isFunction(String)"})
  public void testIsFunction_givenSQLDialectGetFunctionsReturnArrayList_thenReturnFalse() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getFunctions()).thenReturn(new ArrayList<>());

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLFormatterConfiguration sqlFormatterConfiguration =
        new SQLFormatterConfiguration(mock(DBPDataSource.class), syntaxManager, "42");

    // Act
    boolean actualIsFunctionResult = sqlFormatterConfiguration.isFunction("Name");

    // Assert
    verify(sqlDialect).getFunctions();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getKeywordCase();
    assertFalse(actualIsFunctionResult);
  }

  /**
   * Test {@link SQLFormatterConfiguration#isIdentifier(String)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#isIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLFormatterConfiguration.isIdentifier(String)"})
  public void testIsIdentifier_givenSQLDialectValidIdentifierPartReturnFalse_thenReturnFalse() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLFormatterConfiguration sqlFormatterConfiguration =
        new SQLFormatterConfiguration(mock(DBPDataSource.class), syntaxManager, "42");

    // Act
    boolean actualIsIdentifierResult = sqlFormatterConfiguration.isIdentifier("Name");

    // Assert
    verify(sqlDialect).isQuotedIdentifier("Name");
    verify(sqlDialect).validIdentifierPart('N', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getKeywordCase();
    assertFalse(actualIsIdentifierResult);
  }

  /**
   * Test {@link SQLFormatterConfiguration#isIdentifier(String)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#isIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLFormatterConfiguration.isIdentifier(String)"})
  public void testIsIdentifier_givenSQLDialectValidIdentifierPartReturnTrue_thenReturnTrue() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLFormatterConfiguration sqlFormatterConfiguration =
        new SQLFormatterConfiguration(mock(DBPDataSource.class), syntaxManager, "42");

    // Act
    boolean actualIsIdentifierResult = sqlFormatterConfiguration.isIdentifier("Name");

    // Assert
    verify(sqlDialect).isQuotedIdentifier("Name");
    verify(sqlDialect, atLeast(1)).validIdentifierPart(anyChar(), eq(true));
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getKeywordCase();
    assertTrue(actualIsIdentifierResult);
  }

  /**
   * Test {@link SQLFormatterConfiguration#isIdentifier(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#isIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLFormatterConfiguration.isIdentifier(String)"})
  public void testIsIdentifier_whenEmptyString_thenReturnTrue() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLFormatterConfiguration sqlFormatterConfiguration =
        new SQLFormatterConfiguration(mock(DBPDataSource.class), syntaxManager, "42");

    // Act
    boolean actualIsIdentifierResult = sqlFormatterConfiguration.isIdentifier("");

    // Assert
    verify(sqlDialect).isQuotedIdentifier("");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getKeywordCase();
    assertTrue(actualIsIdentifierResult);
  }

  /**
   * Test {@link SQLFormatterConfiguration#getProperty(String)}.
   *
   * <ul>
   *   <li>Given {@link SQLSyntaxManager} {@link SQLSyntaxManager#getKeywordCase()} return {@code
   *       UPPER}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLFormatterConfiguration.getProperty(String)"})
  public void testGetProperty_givenSQLSyntaxManagerGetKeywordCaseReturnUpper_thenReturnNull() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    SQLFormatterConfiguration sqlFormatterConfiguration =
        new SQLFormatterConfiguration(mock(DBPDataSource.class), syntaxManager, "42");

    // Act
    Object actualProperty = sqlFormatterConfiguration.getProperty("Name");

    // Assert
    verify(syntaxManager).getKeywordCase();
    assertNull(actualProperty);
  }

  /**
   * Test {@link SQLFormatterConfiguration#setProperty(String, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLSyntaxManager#getKeywordCase()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLFormatterConfiguration#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFormatterConfiguration.setProperty(String, Object)"})
  public void testSetProperty_thenCallsGetKeywordCase() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    SQLFormatterConfiguration sqlFormatterConfiguration =
        new SQLFormatterConfiguration(mock(DBPDataSource.class), syntaxManager, "42");

    // Act
    sqlFormatterConfiguration.setProperty("Name", "Value");

    // Assert
    verify(syntaxManager).getKeywordCase();
  }
}
