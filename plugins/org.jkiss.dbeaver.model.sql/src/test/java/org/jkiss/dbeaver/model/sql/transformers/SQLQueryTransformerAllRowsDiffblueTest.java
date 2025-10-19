package org.jkiss.dbeaver.model.sql.transformers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryTransformerAllRowsDiffblueTest {
  /**
   * Test {@link SQLQueryTransformerAllRows#transformQuery(DBPDataSource, SQLSyntaxManager,
   * SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@code Text}.
   *   <li>Then return OriginalText is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTransformerAllRows#transformQuery(DBPDataSource,
   * SQLSyntaxManager, SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQuery SQLQueryTransformerAllRows.transformQuery(DBPDataSource, SQLSyntaxManager, SQLQuery)"
  })
  public void testTransformQuery_givenText_thenReturnOriginalTextIsNull() throws DBException {
    // Arrange
    SQLQueryTransformerAllRows sqlQueryTransformerAllRows = new SQLQueryTransformerAllRows();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    SQLQuery query = mock(SQLQuery.class);
    when(query.getText()).thenReturn("Text");

    // Act
    SQLQuery actualTransformQueryResult =
        sqlQueryTransformerAllRows.transformQuery(dataSource, null, query);

    // Assert
    verify(query).getText();
    assertEquals("Text", actualTransformQueryResult.getText());
    assertEquals("Text", actualTransformQueryResult.toString());
    assertNull(actualTransformQueryResult.getOriginalText());
    assertNull(actualTransformQueryResult.getQueryTitle());
    assertEquals(0, actualTransformQueryResult.getLength());
    List<SQLScriptElement> scriptElements = actualTransformQueryResult.getScriptElements();
    assertEquals(1, scriptElements.size());
    assertSame(actualTransformQueryResult, scriptElements.get(0));
  }

  /**
   * Test {@link SQLQueryTransformerAllRows#transformQuery(DBPDataSource, SQLSyntaxManager,
   * SQLQuery)}.
   *
   * <ul>
   *   <li>Then return OriginalText is {@code -- name : U}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTransformerAllRows#transformQuery(DBPDataSource,
   * SQLSyntaxManager, SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQuery SQLQueryTransformerAllRows.transformQuery(DBPDataSource, SQLSyntaxManager, SQLQuery)"
  })
  public void testTransformQuery_thenReturnOriginalTextIsNameU() throws DBException {
    // Arrange
    SQLQueryTransformerAllRows sqlQueryTransformerAllRows = new SQLQueryTransformerAllRows();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    SQLQuery actualTransformQueryResult =
        sqlQueryTransformerAllRows.transformQuery(
            dataSource, null, new SQLQuery(mock(DBPDataSource.class), " -- name : U"));

    // Assert
    assertEquals(" -- name : U", actualTransformQueryResult.getOriginalText());
    assertEquals(" -- name : U", actualTransformQueryResult.getText());
    assertEquals(" -- name : U", actualTransformQueryResult.toString());
    assertEquals("U", actualTransformQueryResult.getQueryTitle());
    List<SQLScriptElement> scriptElements = actualTransformQueryResult.getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(12, actualTransformQueryResult.getLength());
    assertSame(actualTransformQueryResult, scriptElements.get(0));
  }

  /**
   * Test {@link SQLQueryTransformerAllRows#transformQuery(DBPDataSource, SQLSyntaxManager,
   * SQLQuery)}.
   *
   * <ul>
   *   <li>Then return OriginalText is {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTransformerAllRows#transformQuery(DBPDataSource,
   * SQLSyntaxManager, SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQuery SQLQueryTransformerAllRows.transformQuery(DBPDataSource, SQLSyntaxManager, SQLQuery)"
  })
  public void testTransformQuery_thenReturnOriginalTextIsText() throws DBException {
    // Arrange
    SQLQueryTransformerAllRows sqlQueryTransformerAllRows = new SQLQueryTransformerAllRows();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    SQLQuery actualTransformQueryResult =
        sqlQueryTransformerAllRows.transformQuery(
            dataSource, null, new SQLQuery(mock(DBPDataSource.class), "Text"));

    // Assert
    assertEquals("Text", actualTransformQueryResult.getOriginalText());
    assertEquals("Text", actualTransformQueryResult.getText());
    assertEquals("Text", actualTransformQueryResult.toString());
    assertNull(actualTransformQueryResult.getQueryTitle());
    List<SQLScriptElement> scriptElements = actualTransformQueryResult.getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(4, actualTransformQueryResult.getLength());
    assertSame(actualTransformQueryResult, scriptElements.get(0));
  }
}
