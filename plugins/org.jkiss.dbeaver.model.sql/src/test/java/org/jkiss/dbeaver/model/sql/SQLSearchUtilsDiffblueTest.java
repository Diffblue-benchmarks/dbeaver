package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.parser.SQLIdentifierDetector;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLSearchUtilsDiffblueTest {
  /**
   * Test {@link SQLSearchUtils#findObjectByFQN(DBRProgressMonitor, DBSObjectContainer,
   * DBCExecutionContext, List, boolean, SQLIdentifierDetector)} with {@code monitor}, {@code
   * objectContainer}, {@code executionContext}, {@code nameList}, {@code useAssistant}, {@code
   * identifierDetector}.
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectByFQN(DBRProgressMonitor,
   * DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SQLSearchUtils.findObjectByFQN(DBRProgressMonitor, DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector)"
  })
  public void
      testFindObjectByFQNWithMonitorObjectContainerExecutionContextNameListUseAssistantIdentifierDetector() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel objectContainer = new DBVModel(dataSourceContainer);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    DBSObject actualFindObjectByFQNResult =
        SQLSearchUtils.findObjectByFQN(
            monitor,
            objectContainer,
            executionContext,
            nameList,
            true,
            new SQLIdentifierDetector(dialect));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertNull(actualFindObjectByFQNResult);
  }

  /**
   * Test {@link SQLSearchUtils#findObjectByFQN(DBRProgressMonitor, DBSObjectContainer,
   * DBCExecutionContext, List, boolean, SQLIdentifierDetector)} with {@code monitor}, {@code
   * objectContainer}, {@code executionContext}, {@code nameList}, {@code useAssistant}, {@code
   * identifierDetector}.
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectByFQN(DBRProgressMonitor,
   * DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SQLSearchUtils.findObjectByFQN(DBRProgressMonitor, DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector)"
  })
  public void
      testFindObjectByFQNWithMonitorObjectContainerExecutionContextNameListUseAssistantIdentifierDetector2() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    ArrayList<String> nameList = new ArrayList<>();
    nameList.add("foo");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    DBSObject actualFindObjectByFQNResult =
        SQLSearchUtils.findObjectByFQN(
            monitor, null, executionContext, nameList, true, new SQLIdentifierDetector(dialect));

    // Assert
    verify(executionContext).getDataSource();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertNull(actualFindObjectByFQNResult);
  }

  /**
   * Test {@link SQLSearchUtils#findObjectsByFQN(DBRProgressMonitor, DBSObjectContainer,
   * DBCExecutionContext, List, boolean, SQLIdentifierDetector, boolean)} with {@code monitor},
   * {@code objectContainer}, {@code executionContext}, {@code nameList}, {@code useAssistant},
   * {@code identifierDetector}, {@code anyObject}.
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectsByFQN(DBRProgressMonitor,
   * DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLSearchUtils.findObjectsByFQN(DBRProgressMonitor, DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector, boolean)"
  })
  public void
      testFindObjectsByFQNWithMonitorObjectContainerExecutionContextNameListUseAssistantIdentifierDetectorAnyObject() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel objectContainer = new DBVModel(dataSourceContainer);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    List<? extends DBSObject> actualFindObjectsByFQNResult =
        SQLSearchUtils.findObjectsByFQN(
            monitor,
            objectContainer,
            executionContext,
            nameList,
            true,
            new SQLIdentifierDetector(dialect),
            true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualFindObjectsByFQNResult.isEmpty());
  }

  /**
   * Test {@link SQLSearchUtils#findObjectsByFQN(DBRProgressMonitor, DBSObjectContainer,
   * DBCExecutionContext, List, boolean, SQLIdentifierDetector, boolean, boolean)} with {@code
   * monitor}, {@code objectContainer}, {@code executionContext}, {@code nameList}, {@code
   * useAssistant}, {@code identifierDetector}, {@code isGlobalSearch}, {@code anyObject}.
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectsByFQN(DBRProgressMonitor,
   * DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLSearchUtils.findObjectsByFQN(DBRProgressMonitor, DBSObjectContainer, DBCExecutionContext, List, boolean, SQLIdentifierDetector, boolean, boolean)"
  })
  public void
      testFindObjectsByFQNWithMonitorObjectContainerExecutionContextNameListUseAssistantIdentifierDetectorIsGlobalSearchAnyObject() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel objectContainer = new DBVModel(dataSourceContainer);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    List<? extends DBSObject> actualFindObjectsByFQNResult =
        SQLSearchUtils.findObjectsByFQN(
            monitor,
            objectContainer,
            executionContext,
            nameList,
            true,
            new SQLIdentifierDetector(dialect),
            true,
            true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualFindObjectsByFQNResult.isEmpty());
  }

  /**
   * Test {@link SQLSearchUtils#findObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, SQLIdentifierDetector, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBCExecutionContext}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectByPath(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SQLSearchUtils.findObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean)"
  })
  public void testFindObjectByPath_given42_whenDBCExecutionContext_thenCallsGetId() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel objectContainer = new DBVModel(dataSourceContainer);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    DBSObject actualFindObjectByPathResult =
        SQLSearchUtils.findObjectByPath(
            monitor,
            executionContext,
            objectContainer,
            nameList,
            new SQLIdentifierDetector(dialect),
            true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertNull(actualFindObjectByPathResult);
  }

  /**
   * Test {@link SQLSearchUtils#findObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, SQLIdentifierDetector, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectByPath(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SQLSearchUtils.findObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean)"
  })
  public void testFindObjectByPath_whenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    DBVContainer objectContainer = new DBVContainer(mock(DBVContainer.class), "Name");
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    DBSObject actualFindObjectByPathResult =
        SQLSearchUtils.findObjectByPath(
            monitor,
            executionContext,
            objectContainer,
            nameList,
            new SQLIdentifierDetector(dialect),
            true);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertNull(actualFindObjectByPathResult);
  }

  /**
   * Test {@link SQLSearchUtils#findObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, SQLIdentifierDetector, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectByPath(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SQLSearchUtils.findObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean)"
  })
  public void testFindObjectByPath_whenNull_thenReturnNull() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    DBSObject actualFindObjectByPathResult =
        SQLSearchUtils.findObjectByPath(
            monitor, executionContext, null, nameList, new SQLIdentifierDetector(dialect), true);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertNull(actualFindObjectByPathResult);
  }

  /**
   * Test {@link SQLSearchUtils#findObjectsByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBCExecutionContext}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectsByPath(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLSearchUtils.findObjectsByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean, boolean)"
  })
  public void testFindObjectsByPath_given42_whenDBCExecutionContext_thenCallsGetId() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel sc = new DBVModel(dataSourceContainer);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    List<? extends DBSObject> actualFindObjectsByPathResult =
        SQLSearchUtils.findObjectsByPath(
            monitor,
            executionContext,
            sc,
            nameList,
            new SQLIdentifierDetector(dialect),
            true,
            true,
            true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualFindObjectsByPathResult.isEmpty());
  }

  /**
   * Test {@link SQLSearchUtils#findObjectsByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectsByPath(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLSearchUtils.findObjectsByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean, boolean)"
  })
  public void testFindObjectsByPath_whenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    DBVContainer sc = new DBVContainer(mock(DBVContainer.class), "Name");
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    List<? extends DBSObject> actualFindObjectsByPathResult =
        SQLSearchUtils.findObjectsByPath(
            monitor,
            executionContext,
            sc,
            nameList,
            new SQLIdentifierDetector(dialect),
            true,
            true,
            true);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualFindObjectsByPathResult.isEmpty());
  }

  /**
   * Test {@link SQLSearchUtils#findObjectsByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findObjectsByPath(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLSearchUtils.findObjectsByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, SQLIdentifierDetector, boolean, boolean, boolean)"
  })
  public void testFindObjectsByPath_whenNull_thenReturnEmpty() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<String> nameList = new ArrayList<>();

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    List<? extends DBSObject> actualFindObjectsByPathResult =
        SQLSearchUtils.findObjectsByPath(
            monitor,
            executionContext,
            null,
            nameList,
            new SQLIdentifierDetector(dialect),
            true,
            true,
            true);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualFindObjectsByPathResult.isEmpty());
  }

  /**
   * Test {@link SQLSearchUtils#findNestedObjects(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, List, boolean)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLSearchUtils#findNestedObjects(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, List, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLSearchUtils.findNestedObjects(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, List, boolean)"
  })
  public void testFindNestedObjects_whenArrayList_thenReturnEmpty() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel parent = new DBVModel(dataSourceContainer);

    // Act
    List<? extends DBSObject> actualFindNestedObjectsResult =
        SQLSearchUtils.findNestedObjects(
            monitor, executionContext, parent, new ArrayList<>(), true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualFindNestedObjectsResult.isEmpty());
  }
}
