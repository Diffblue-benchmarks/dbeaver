package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLScriptDiffblueTest {
  /**
   * Test {@link SQLScript#SQLScript(DBPDataSource, String, List)}.
   *
   * <ul>
   *   <li>Then return ScriptElements is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScript#SQLScript(DBPDataSource, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScript.<init>(DBPDataSource, String, List)"})
  public void testNewSQLScript_thenReturnScriptElementsIsArrayList() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    ArrayList<SQLScriptElement> scriptElements = new ArrayList<>();
    scriptElements.add(new SQLQuery(mock(DBPDataSource.class), " -- name : U"));

    // Act
    SQLScript actualSqlScript = new SQLScript(dataSource, "Text", scriptElements);

    // Assert
    assertSame(scriptElements, actualSqlScript.getScriptElements());
  }

  /**
   * Test {@link SQLScript#SQLScript(DBPDataSource, String, List)}.
   *
   * <ul>
   *   <li>Then return ScriptElements size is two.
   * </ul>
   *
   * <p>Method under test: {@link SQLScript#SQLScript(DBPDataSource, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScript.<init>(DBPDataSource, String, List)"})
  public void testNewSQLScript_thenReturnScriptElementsSizeIsTwo() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    ArrayList<SQLScriptElement> scriptElements = new ArrayList<>();
    scriptElements.add(new SQLQuery(mock(DBPDataSource.class), " -- name : U"));
    SQLQuery sqlQuery = new SQLQuery(mock(DBPDataSource.class), " -- name : U");
    scriptElements.add(sqlQuery);

    // Act
    SQLScript actualSqlScript = new SQLScript(dataSource, "Text", scriptElements);

    // Assert
    List<SQLScriptElement> scriptElements2 = actualSqlScript.getScriptElements();
    assertEquals(2, scriptElements2.size());
    assertTrue(scriptElements2.get(0) instanceof SQLQuery);
    SQLScriptElement getResult = scriptElements2.get(1);
    assertTrue(getResult instanceof SQLQuery);
    assertSame(sqlQuery, getResult);
  }

  /**
   * Test {@link SQLScript#SQLScript(DBPDataSource, String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return OriginalText is {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScript#SQLScript(DBPDataSource, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScript.<init>(DBPDataSource, String, List)"})
  public void testNewSQLScript_whenArrayList_thenReturnOriginalTextIsText() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    SQLScript actualSqlScript = new SQLScript(dataSource, "Text", new ArrayList<>());

    // Assert
    assertEquals("Text", actualSqlScript.getOriginalText());
    assertEquals("Text", actualSqlScript.getText());
    assertEquals("Text", actualSqlScript.toString());
    assertNull(actualSqlScript.getQueryTitle());
    assertEquals(4, actualSqlScript.getLength());
    assertTrue(actualSqlScript.getScriptElements().isEmpty());
  }

  /**
   * Test {@link SQLScript#SQLScript(DBPDataSource, String, List)}.
   *
   * <ul>
   *   <li>When {@code -- name : U}.
   *   <li>Then return OriginalText is {@code -- name : U}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScript#SQLScript(DBPDataSource, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScript.<init>(DBPDataSource, String, List)"})
  public void testNewSQLScript_whenNameU_thenReturnOriginalTextIsNameU() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    SQLScript actualSqlScript = new SQLScript(dataSource, " -- name : U", new ArrayList<>());

    // Assert
    assertEquals(" -- name : U", actualSqlScript.getOriginalText());
    assertEquals(" -- name : U", actualSqlScript.getText());
    assertEquals(" -- name : U", actualSqlScript.toString());
    assertEquals("U", actualSqlScript.getQueryTitle());
    assertEquals(12, actualSqlScript.getLength());
  }

  /**
   * Test {@link SQLScript#getScriptElements()}.
   *
   * <p>Method under test: {@link SQLScript#getScriptElements()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLScript.getScriptElements()"})
  public void testGetScriptElements() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    ArrayList<SQLScriptElement> scriptElements = new ArrayList<>();

    SQLScript sqlScript = new SQLScript(dataSource, "Text", scriptElements);

    // Act
    List<SQLScriptElement> actualScriptElements = sqlScript.getScriptElements();

    // Assert
    assertTrue(actualScriptElements.isEmpty());
    assertSame(scriptElements, actualScriptElements);
  }
}
