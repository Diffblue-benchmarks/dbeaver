package org.jkiss.dbeaver.model.sql.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
import java.util.Map;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionSource;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.impl.AbstractExecutionSource;
import org.jkiss.dbeaver.model.impl.OutputWriterAdapter;
import org.jkiss.dbeaver.model.impl.edit.TestCommandContext;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryDataContainerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLQueryDataContainer#SQLQueryDataContainer(DBPContextProvider, SQLQuery,
   *       SQLScriptContext, Log)}
   *   <li>{@link SQLQueryDataContainer#setQuery(SQLQuery)}
   *   <li>{@link SQLQueryDataContainer#toString()}
   *   <li>{@link SQLQueryDataContainer#getDescription()}
   *   <li>{@link SQLQueryDataContainer#getObjectImage()}
   *   <li>{@link SQLQueryDataContainer#getQuery()}
   *   <li>{@link SQLQueryDataContainer#getScriptContext()}
   *   <li>{@link SQLQueryDataContainer#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryDataContainer.<init>(DBPContextProvider, SQLQuery, SQLScriptContext, Log)",
    "String SQLQueryDataContainer.getDescription()",
    "DBPImage SQLQueryDataContainer.getObjectImage()",
    "SQLScriptElement SQLQueryDataContainer.getQuery()",
    "SQLScriptContext SQLQueryDataContainer.getScriptContext()",
    "boolean SQLQueryDataContainer.isPersisted()",
    "void SQLQueryDataContainer.setQuery(SQLQuery)",
    "String SQLQueryDataContainer.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    // Act
    SQLQueryDataContainer actualSqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    actualSqlQueryDataContainer.setQuery(query2);
    String actualToStringResult = actualSqlQueryDataContainer.toString();
    String actualDescription = actualSqlQueryDataContainer.getDescription();
    DBPImage actualObjectImage = actualSqlQueryDataContainer.getObjectImage();
    SQLScriptElement actualQuery = actualSqlQueryDataContainer.getQuery();
    SQLScriptContext actualScriptContext = actualSqlQueryDataContainer.getScriptContext();

    // Assert
    assertEquals("SQL Query", actualDescription);
    assertEquals("Text", actualToStringResult);
    assertFalse(actualSqlQueryDataContainer.isPersisted());
    assertSame(query2, actualQuery);
    assertSame(scriptContext, actualScriptContext);
    assertSame(((DBIcon) actualObjectImage).TREE_FILE, actualObjectImage);
  }

  /**
   * Test {@link SQLQueryDataContainer#getExecutionContext()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getExecutionContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionContext SQLQueryDataContainer.getExecutionContext()"})
  public void testGetExecutionContext() {
    // Arrange
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(mock(DBCExecutionContext.class));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider2,
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

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    // Act
    sqlQueryDataContainer.getExecutionContext();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
  }

  /**
   * Test {@link SQLQueryDataContainer#getSupportedFeatures()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getSupportedFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLQueryDataContainer.getSupportedFeatures()"})
  public void testGetSupportedFeatures() {
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

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    // Act
    String[] actualSupportedFeatures = sqlQueryDataContainer.getSupportedFeatures();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertArrayEquals(
        new String[] {"data.select", "data.count", "data.filter"}, actualSupportedFeatures);
  }

  /**
   * Test {@link SQLQueryDataContainer#countData(DBCExecutionSource, DBCSession, DBDDataFilter,
   * long)}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#countData(DBCExecutionSource, DBCSession,
   * DBDDataFilter, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long SQLQueryDataContainer.countData(DBCExecutionSource, DBCSession, DBDDataFilter, long)"
  })
  public void testCountData() throws DBCException {
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

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    when(contextProvider4.getExecutionContext()).thenReturn(dbcExecutionContext3);
    SQLScriptContext parentContext2 = mock(SQLScriptContext.class);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext2 =
        new SQLScriptContext(
            parentContext2,
            contextProvider4,
            sourceFile3,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass2 = Object.class;

    SQLQueryDataContainer dataContainer =
        new SQLQueryDataContainer(contextProvider5, query2, scriptContext2, Log.getLog(forClass2));
    AbstractExecutionSource source =
        new AbstractExecutionSource(dataContainer, mock(DBCExecutionContext.class), "Controller");
    DBCSession session = mock(DBCSession.class);

    // Act
    long actualCountDataResult =
        sqlQueryDataContainer.countData(source, session, new DBDDataFilter(), 3L);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider4).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
    assertEquals(-1L, actualCountDataResult);
  }

  /**
   * Test {@link SQLQueryDataContainer#getParentObject()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSObject SQLQueryDataContainer.getParentObject()"
  })
  public void testGetParentObject() {
    // Arrange
    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider2,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    // Act
    sqlQueryDataContainer.getParentObject();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
  }

  /**
   * Test {@link SQLQueryDataContainer#getDataSource()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource SQLQueryDataContainer.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider2,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    // Act
    sqlQueryDataContainer.getDataSource();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
  }

  /**
   * Test {@link SQLQueryDataContainer#getName()}.
   *
   * <ul>
   *   <li>Given {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and {@code Text}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryDataContainer.getName()"})
  public void testGetName_givenSQLQueryWithDataSourceIsDBPDataSourceAndText_thenReturnText() {
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

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    // Act
    String actualName = sqlQueryDataContainer.getName();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertEquals("Text", actualName);
  }

  /**
   * Test {@link SQLQueryDataContainer#getDataSourceContainer()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer SQLQueryDataContainer.getDataSourceContainer()"})
  public void testGetDataSourceContainer() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider2,
            sourceFile,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    // Act
    sqlQueryDataContainer.getDataSourceContainer();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
  }

  /**
   * Test {@link SQLQueryDataContainer#getDataSourceContainer()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer SQLQueryDataContainer.getDataSourceContainer()"})
  public void testGetDataSourceContainer2() {
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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
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
            parentContext2,
            contextProvider2,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    TestCommandContext contextProvider3 = new TestCommandContext(null, true);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    // Act
    DBPDataSourceContainer actualDataSourceContainer =
        sqlQueryDataContainer.getDataSourceContainer();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertNull(actualDataSourceContainer);
  }

  /**
   * Test {@link SQLQueryDataContainer#getDataSourceContainer()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer SQLQueryDataContainer.getDataSourceContainer()"})
  public void testGetDataSourceContainer3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext2);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer contextProvider4 =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource4 = mock(DBPDataSource.class);
    when(dbpDataSource4.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext4.getDataSource()).thenReturn(dbpDataSource4);

    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    when(contextProvider5.getExecutionContext()).thenReturn(dbcExecutionContext4);
    SQLScriptContext parentContext3 = mock(SQLScriptContext.class);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext4 =
        new SQLScriptContext(
            parentContext3,
            contextProvider5,
            sourceFile3,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer4.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource5 = mock(DBPDataSource.class);
    when(dbpDataSource5.getContainer()).thenReturn(dbpDataSourceContainer4);

    DBCExecutionContext dbcExecutionContext5 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext5.getDataSource()).thenReturn(dbpDataSource5);

    DBPContextProvider contextProvider6 = mock(DBPContextProvider.class);
    when(contextProvider6.getExecutionContext()).thenReturn(dbcExecutionContext5);
    Path sourceFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext2 =
        new SQLScriptContext(
            parentContext4,
            contextProvider6,
            sourceFile4,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass2 = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider4, query2, scriptContext2, Log.getLog(forClass2));

    // Act
    sqlQueryDataContainer.getDataSourceContainer();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(contextProvider5).getExecutionContext();
    verify(contextProvider6).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource3).getContainer();
    verify(dbpDataSource4).getContainer();
    verify(dbpDataSource5).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbpDataSourceContainer3).getConnectionConfiguration();
    verify(dbpDataSourceContainer4).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(dbcExecutionContext3).getDataSource();
    verify(dbcExecutionContext4).getDataSource();
    verify(dbcExecutionContext5).getDataSource();
  }

  /**
   * Test {@link SQLQueryDataContainer#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getDataSource()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer SQLQueryDataContainer.getDataSourceContainer()"})
  public void testGetDataSourceContainer_givenDBCExecutionContextGetDataSourceReturnNull() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);
    TestCommandContext contextProvider = new TestCommandContext(executionContext, true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    when(contextProvider2.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider2,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext2);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    // Act
    DBPDataSourceContainer actualDataSourceContainer =
        sqlQueryDataContainer.getDataSourceContainer();

    // Assert
    verify(contextProvider2).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(executionContext).getDataSource();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertNull(actualDataSourceContainer);
  }

  /**
   * Test {@link SQLQueryDataContainer#getQueryParameters()}.
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getQueryParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLQueryDataContainer.getQueryParameters()"})
  public void testGetQueryParameters() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    query.setParameters(new ArrayList<>());

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
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    // Act
    Map<String, Object> actualQueryParameters = sqlQueryDataContainer.getQueryParameters();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualQueryParameters.isEmpty());
  }

  /**
   * Test {@link SQLQueryDataContainer#getQueryParameters()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#getQueryParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLQueryDataContainer.getQueryParameters()"})
  public void testGetQueryParameters_thenReturnEmpty() {
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

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    // Act
    Map<String, Object> actualQueryParameters = sqlQueryDataContainer.getQueryParameters();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(actualQueryParameters.isEmpty());
  }

  /**
   * Test {@link SQLQueryDataContainer#equals(Object)}, and {@link
   * SQLQueryDataContainer#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryDataContainer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
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
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
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

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    when(contextProvider4.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            null,
            contextProvider4,
            sourceFile3,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer4.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource4 = mock(DBPDataSource.class);
    when(dbpDataSource4.getContainer()).thenReturn(dbpDataSourceContainer4);

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext4.getDataSource()).thenReturn(dbpDataSource4);

    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    when(contextProvider5.getExecutionContext()).thenReturn(dbcExecutionContext4);
    Path sourceFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext2 =
        new SQLScriptContext(
            parentContext2,
            contextProvider5,
            sourceFile4,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider6 = mock(DBPContextProvider.class);
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass2 = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer2 =
        new SQLQueryDataContainer(contextProvider6, query2, scriptContext2, Log.getLog(forClass2));

    // Act and Assert
    assertEquals(sqlQueryDataContainer, sqlQueryDataContainer2);
    assertNotEquals(sqlQueryDataContainer.hashCode(), sqlQueryDataContainer2.hashCode());
  }

  /**
   * Test {@link SQLQueryDataContainer#equals(Object)}, and {@link
   * SQLQueryDataContainer#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryDataContainer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));

    // Act and Assert
    assertEquals(sqlQueryDataContainer, sqlQueryDataContainer);
    int expectedHashCodeResult = sqlQueryDataContainer.hashCode();
    assertEquals(expectedHashCodeResult, sqlQueryDataContainer.hashCode());
  }

  /**
   * Test {@link SQLQueryDataContainer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryDataContainer.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new OutputWriterAdapter(null),
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
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "42");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer sqlQueryDataContainer =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    DBPDataSourceContainer dbpDataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer3.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource3 = mock(DBPDataSource.class);
    when(dbpDataSource3.getContainer()).thenReturn(dbpDataSourceContainer3);

    DBCExecutionContext dbcExecutionContext3 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext3.getDataSource()).thenReturn(dbpDataSource3);

    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    when(contextProvider4.getExecutionContext()).thenReturn(dbcExecutionContext3);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            null,
            contextProvider4,
            sourceFile3,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));

    DBPDataSourceContainer dbpDataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer4.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource4 = mock(DBPDataSource.class);
    when(dbpDataSource4.getContainer()).thenReturn(dbpDataSourceContainer4);

    DBCExecutionContext dbcExecutionContext4 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext4.getDataSource()).thenReturn(dbpDataSource4);

    DBPContextProvider contextProvider5 = mock(DBPContextProvider.class);
    when(contextProvider5.getExecutionContext()).thenReturn(dbcExecutionContext4);
    Path sourceFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext2 =
        new SQLScriptContext(
            parentContext2,
            contextProvider5,
            sourceFile4,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider6 = mock(DBPContextProvider.class);
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass2 = Object.class;

    // Act and Assert
    assertNotEquals(
        sqlQueryDataContainer,
        new SQLQueryDataContainer(contextProvider6, query2, scriptContext2, Log.getLog(forClass2)));
  }

  /**
   * Test {@link SQLQueryDataContainer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryDataContainer.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertNotEquals(
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass)),
        null);
  }

  /**
   * Test {@link SQLQueryDataContainer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContainer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryDataContainer.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new OutputWriterAdapter(null),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertNotEquals(
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass)),
        "Different type to SQLQueryDataContainer");
  }
}
