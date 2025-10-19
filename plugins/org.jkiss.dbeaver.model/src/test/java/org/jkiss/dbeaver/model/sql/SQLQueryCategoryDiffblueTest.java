package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCategoryDiffblueTest {
  /**
   * Test {@link SQLQueryCategory#categorizeScript(List)}.
   *
   * <p>Method under test: {@link SQLQueryCategory#categorizeScript(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SQLQueryCategory.categorizeScript(List)"})
  public void testCategorizeScript() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    SQLQuery sqlQuery = new SQLQuery(dataSource, "Text");

    ArrayList<SQLScriptElement> scriptElements = new ArrayList<>();
    DBPDataSource dataSource2 = mock(DBPDataSource.class);
    SQLControlCommand sqlControlCommand =
        new SQLControlCommand(dataSource2, "Text", "42", 2, 3, new HashMap<>());
    scriptElements.add(sqlControlCommand);
    scriptElements.add(sqlQuery);

    // Act
    Set<SQLQueryCategory> actualCategorizeScriptResult =
        SQLQueryCategory.categorizeScript(scriptElements);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals(1, actualCategorizeScriptResult.size());
    assertTrue(actualCategorizeScriptResult.contains(SQLQueryCategory.UNKNOWN));
  }

  /**
   * Test {@link SQLQueryCategory#categorizeScript(List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCategory#categorizeScript(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SQLQueryCategory.categorizeScript(List)"})
  public void testCategorizeScript_thenReturnSizeIsOne() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    SQLQuery sqlQuery = new SQLQuery(dataSource, "Text");

    ArrayList<SQLScriptElement> scriptElements = new ArrayList<>();
    scriptElements.add(sqlQuery);

    // Act
    Set<SQLQueryCategory> actualCategorizeScriptResult =
        SQLQueryCategory.categorizeScript(scriptElements);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals(1, actualCategorizeScriptResult.size());
    assertTrue(actualCategorizeScriptResult.contains(SQLQueryCategory.UNKNOWN));
  }

  /**
   * Test {@link SQLQueryCategory#categorizeScript(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCategory#categorizeScript(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SQLQueryCategory.categorizeScript(List)"})
  public void testCategorizeScript_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    Set<SQLQueryCategory> actualCategorizeScriptResult =
        SQLQueryCategory.categorizeScript(new ArrayList<>());

    // Assert
    assertTrue(actualCategorizeScriptResult.isEmpty());
  }
}
