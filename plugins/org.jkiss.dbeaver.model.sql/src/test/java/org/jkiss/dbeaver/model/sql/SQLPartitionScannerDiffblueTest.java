package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.parser.SQLRuleManager;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.jkiss.dbeaver.model.text.parser.TPRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLPartitionScannerDiffblueTest {
  /**
   * Test {@link SQLPartitionScanner#SQLPartitionScanner(DBPDataSource, SQLDialect,
   * SQLRuleManager)}.
   *
   * <ul>
   *   <li>Given array of {@link TPRule} with {@link TPRule}.
   *   <li>Then calls {@link SQLRuleManager#getRulesByType(SQLTokenType)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLPartitionScanner#SQLPartitionScanner(DBPDataSource, SQLDialect,
   * SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLPartitionScanner.<init>(DBPDataSource, SQLDialect, SQLRuleManager)"})
  public void testNewSQLPartitionScanner_givenArrayOfTPRuleWithTPRule_thenCallsGetRulesByType() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    SQLDialect dialect = mock(SQLDialect.class);

    SQLRuleManager ruleManager = mock(SQLRuleManager.class);
    when(ruleManager.getRulesByType(Mockito.<SQLTokenType>any()))
        .thenReturn(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLPartitionScanner actualSqlPartitionScanner =
        new SQLPartitionScanner(dataSource, dialect, ruleManager);

    // Assert
    verify(ruleManager, atLeast(1)).getRulesByType(Mockito.<SQLTokenType>any());
    assertEquals("", actualSqlPartitionScanner.getScannedPartitionString());
    assertNull(actualSqlPartitionScanner.getLegalLineDelimiters());
    assertEquals(0, actualSqlPartitionScanner.getColumn());
    assertEquals(0, actualSqlPartitionScanner.getTokenLength());
    assertEquals(0, actualSqlPartitionScanner.getTokenOffset());
    assertEquals(0, actualSqlPartitionScanner.getOffset());
  }

  /**
   * Test {@link SQLPartitionScanner#SQLPartitionScanner(DBPDataSource, SQLDialect,
   * SQLRuleManager)}.
   *
   * <ul>
   *   <li>Given array of {@link TPRule} with {@link TPRule}.
   *   <li>Then calls {@link SQLRuleManager#getRulesByType(SQLTokenType)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLPartitionScanner#SQLPartitionScanner(DBPDataSource, SQLDialect,
   * SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLPartitionScanner.<init>(DBPDataSource, SQLDialect, SQLRuleManager)"})
  public void testNewSQLPartitionScanner_givenArrayOfTPRuleWithTPRule_thenCallsGetRulesByType2() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLRuleManager ruleManager = mock(SQLRuleManager.class);
    when(ruleManager.getRulesByType(Mockito.<SQLTokenType>any()))
        .thenReturn(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLPartitionScanner actualSqlPartitionScanner =
        new SQLPartitionScanner(dataSource, null, ruleManager);

    // Assert
    verify(ruleManager, atLeast(1)).getRulesByType(Mockito.<SQLTokenType>any());
    assertEquals("", actualSqlPartitionScanner.getScannedPartitionString());
    assertNull(actualSqlPartitionScanner.getLegalLineDelimiters());
    assertEquals(0, actualSqlPartitionScanner.getColumn());
    assertEquals(0, actualSqlPartitionScanner.getTokenLength());
    assertEquals(0, actualSqlPartitionScanner.getTokenOffset());
    assertEquals(0, actualSqlPartitionScanner.getOffset());
  }

  /**
   * Test {@link SQLPartitionScanner#SQLPartitionScanner(DBPDataSource, SQLDialect,
   * SQLRuleManager)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect}.
   *   <li>Then calls {@link SQLSyntaxManager#getDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLPartitionScanner#SQLPartitionScanner(DBPDataSource, SQLDialect,
   * SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLPartitionScanner.<init>(DBPDataSource, SQLDialect, SQLRuleManager)"})
  public void testNewSQLPartitionScanner_givenSQLDialect_thenCallsGetDialect() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    SQLDialect dialect = mock(SQLDialect.class);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    SQLRuleManager ruleManager = new SQLRuleManager(syntaxManager);

    // Act
    SQLPartitionScanner actualSqlPartitionScanner =
        new SQLPartitionScanner(dataSource, dialect, ruleManager);

    // Assert
    verify(syntaxManager).getDialect();
    assertEquals("", actualSqlPartitionScanner.getScannedPartitionString());
    assertNull(actualSqlPartitionScanner.getLegalLineDelimiters());
    assertEquals(0, actualSqlPartitionScanner.getColumn());
    assertEquals(0, actualSqlPartitionScanner.getTokenLength());
    assertEquals(0, actualSqlPartitionScanner.getTokenOffset());
    assertEquals(0, actualSqlPartitionScanner.getOffset());
  }

  /**
   * Test {@link SQLPartitionScanner#getScannedPartitionString()}.
   *
   * <p>Method under test: {@link SQLPartitionScanner#getScannedPartitionString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLPartitionScanner.getScannedPartitionString()"})
  public void testGetScannedPartitionString() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    SQLRuleManager ruleManager = new SQLRuleManager(syntaxManager);

    SQLPartitionScanner sqlPartitionScanner =
        new SQLPartitionScanner(mock(DBPDataSource.class), mock(SQLDialect.class), ruleManager);
    sqlPartitionScanner.setRange(new Document("Not all who wander are lost"), 2, 3);

    // Act
    String actualScannedPartitionString = sqlPartitionScanner.getScannedPartitionString();

    // Assert
    verify(syntaxManager).getDialect();
    assertEquals("", actualScannedPartitionString);
  }

  /**
   * Test {@link SQLPartitionScanner#getScannedPartitionString()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLPartitionScanner#getScannedPartitionString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLPartitionScanner.getScannedPartitionString()"})
  public void testGetScannedPartitionString_thenReturnEmptyString() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    SQLRuleManager ruleManager = new SQLRuleManager(syntaxManager);
    SQLPartitionScanner sqlPartitionScanner =
        new SQLPartitionScanner(mock(DBPDataSource.class), mock(SQLDialect.class), ruleManager);

    // Act
    String actualScannedPartitionString = sqlPartitionScanner.getScannedPartitionString();

    // Assert
    verify(syntaxManager).getDialect();
    assertEquals("", actualScannedPartitionString);
  }

  /**
   * Test {@link SQLPartitionScanner#getDocumentRegions(IDocument)}.
   *
   * <ul>
   *   <li>When {@link Document#Document()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLPartitionScanner#getDocumentRegions(IDocument)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ITypedRegion[] SQLPartitionScanner.getDocumentRegions(IDocument)"})
  public void testGetDocumentRegions_whenDocument_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    ITypedRegion[] actualDocumentRegions = SQLPartitionScanner.getDocumentRegions(new Document());

    // Assert
    assertEquals(0, actualDocumentRegions.length);
  }
}
