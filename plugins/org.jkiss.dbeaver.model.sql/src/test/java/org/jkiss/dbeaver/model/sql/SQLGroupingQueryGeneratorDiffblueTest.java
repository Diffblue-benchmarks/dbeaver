package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.sql.data.SQLQueryDataContainer;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLGroupingQueryGeneratorDiffblueTest {
  /**
   * Test {@link SQLGroupingQueryGenerator#SQLGroupingQueryGenerator(DBPDataSource,
   * DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)}.
   *
   * <p>Method under test: {@link SQLGroupingQueryGenerator#SQLGroupingQueryGenerator(DBPDataSource,
   * DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGroupingQueryGenerator.<init>(DBPDataSource, DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)"
  })
  public void testNewSQLGroupingQueryGenerator() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer container =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));
    SQLDialect dialect = mock(SQLDialect.class);
    ArrayList<SQLGroupingAttribute> groupAttributes = new ArrayList<>();

    // Act
    SQLGroupingQueryGenerator actualSqlGroupingQueryGenerator =
        new SQLGroupingQueryGenerator(
            dataSource, container, dialect, null, groupAttributes, new ArrayList<>(), true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertEquals(0, actualSqlGroupingQueryGenerator.getFuncAliases().length);
  }

  /**
   * Test {@link SQLGroupingQueryGenerator#SQLGroupingQueryGenerator(DBPDataSource,
   * DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGroupingQueryGenerator#SQLGroupingQueryGenerator(DBPDataSource,
   * DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGroupingQueryGenerator.<init>(DBPDataSource, DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)"
  })
  public void testNewSQLGroupingQueryGenerator_given42_whenArrayListAdd42() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer container =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));
    SQLDialect dialect = mock(SQLDialect.class);
    ArrayList<SQLGroupingAttribute> groupAttributes = new ArrayList<>();

    ArrayList<String> groupFunctions = new ArrayList<>();
    groupFunctions.add("42");
    groupFunctions.add("foo");

    // Act
    SQLGroupingQueryGenerator actualSqlGroupingQueryGenerator =
        new SQLGroupingQueryGenerator(
            dataSource, container, dialect, null, groupAttributes, groupFunctions, true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertEquals(0, actualSqlGroupingQueryGenerator.getFuncAliases().length);
  }

  /**
   * Test {@link SQLGroupingQueryGenerator#SQLGroupingQueryGenerator(DBPDataSource,
   * DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGroupingQueryGenerator#SQLGroupingQueryGenerator(DBPDataSource,
   * DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGroupingQueryGenerator.<init>(DBPDataSource, DBSDataContainer, SQLDialect, SQLSyntaxManager, List, List, boolean)"
  })
  public void testNewSQLGroupingQueryGenerator_givenFoo_whenArrayListAddFoo() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer container =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));
    SQLDialect dialect = mock(SQLDialect.class);
    ArrayList<SQLGroupingAttribute> groupAttributes = new ArrayList<>();

    ArrayList<String> groupFunctions = new ArrayList<>();
    groupFunctions.add("foo");

    // Act
    SQLGroupingQueryGenerator actualSqlGroupingQueryGenerator =
        new SQLGroupingQueryGenerator(
            dataSource, container, dialect, null, groupAttributes, groupFunctions, true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertEquals(0, actualSqlGroupingQueryGenerator.getFuncAliases().length);
  }

  /**
   * Test {@link SQLGroupingQueryGenerator#generateGroupingQuery(String)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGroupingQueryGenerator#generateGroupingQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLGroupingQueryGenerator.generateGroupingQuery(String)"})
  public void testGenerateGroupingQuery_thenThrowDBException() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new SQLScriptContext(
        parentContext,
        contextProvider,
        sourceFile,
        new StringWriter(),
        mock(SQLParametersProvider.class));
    SQLDialect dialect = mock(SQLDialect.class);
    ArrayList<SQLGroupingAttribute> groupAttributes = new ArrayList<>();

    SQLGroupingQueryGenerator sqlGroupingQueryGenerator =
        new SQLGroupingQueryGenerator(
            null, null, dialect, null, groupAttributes, new ArrayList<>(), true);

    // Act and Assert
    assertThrows(DBException.class, () -> sqlGroupingQueryGenerator.generateGroupingQuery(null));
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
  }
}
