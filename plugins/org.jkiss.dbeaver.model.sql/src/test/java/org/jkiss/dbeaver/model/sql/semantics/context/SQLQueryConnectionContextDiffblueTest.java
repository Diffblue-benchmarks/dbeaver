package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
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
import java.util.HashSet;
import java.util.List;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.impl.struct.AbstractObjectType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.jkiss.dbeaver.model.sql.data.SQLQueryDataContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLQueryConnectionContextDiffblueTest {
  /**
   * Test {@link SQLQueryConnectionContext#expandAliases(DBRProgressMonitor, DBSObject)}.
   *
   * <p>Method under test: {@link SQLQueryConnectionContext#expandAliases(DBRProgressMonitor,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SQLQueryConnectionContext.expandAliases(DBRProgressMonitor, DBSObject)"
  })
  public void testExpandAliases() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

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

    SQLQueryDataContainer obj =
        new SQLQueryDataContainer(contextProvider3, query, scriptContext, Log.getLog(forClass));

    // Act
    DBSObject actualExpandAliasesResult = SQLQueryConnectionContext.expandAliases(monitor, obj);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider2).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    assertTrue(
        ((SQLQueryDataContainer) actualExpandAliasesResult).getObjectImage() instanceof DBIcon);
    SQLScriptElement query2 = ((SQLQueryDataContainer) actualExpandAliasesResult).getQuery();
    assertTrue(query2 instanceof SQLQuery);
    assertTrue(actualExpandAliasesResult instanceof SQLQueryDataContainer);
    assertEquals("SQL Query", actualExpandAliasesResult.getDescription());
    assertEquals("Text", actualExpandAliasesResult.getName());
    assertNull(actualExpandAliasesResult.getDataSource());
    assertNull(((SQLQueryDataContainer) actualExpandAliasesResult).getDataSourceContainer());
    assertNull(((SQLQueryDataContainer) actualExpandAliasesResult).getExecutionContext());
    assertNull(actualExpandAliasesResult.getParentObject());
    assertEquals(
        3, ((SQLQueryDataContainer) actualExpandAliasesResult).getSupportedFeatures().length);
    assertFalse(actualExpandAliasesResult.isPersisted());
    assertTrue(((SQLQueryDataContainer) actualExpandAliasesResult).getQueryParameters().isEmpty());
    assertSame(query, query2);
    assertSame(
        scriptContext, ((SQLQueryDataContainer) actualExpandAliasesResult).getScriptContext());
  }

  /**
   * Test {@link SQLQueryConnectionContext#findRealObjects(DBRProgressMonitor, DBSObjectType,
   * List)}.
   *
   * <ul>
   *   <li>When {@code DBSObject}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionContext#findRealObjects(DBRProgressMonitor,
   * DBSObjectType, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionContext.findRealObjects(DBRProgressMonitor, DBSObjectType, List)"
  })
  public void testFindRealObjects_whenOrgJkissDbeaverModelStructDBSObject_thenReturnSizeIsOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPImage image = mock(DBPImage.class);
    Class<DBSObject> objectClass = DBSObject.class;

    AbstractObjectType objectType =
        new AbstractObjectType(
            "Type Name", "The characteristics of someone or something", image, objectClass);

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsResult =
        sqlQueryConnectionDummyContext.findRealObjects(monitor, objectType, objectName);

    // Assert
    verify(dialect).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealObjectsResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionContext#findRealObjects(DBRProgressMonitor, DBSObjectType,
   * List)}.
   *
   * <ul>
   *   <li>When {@code DBSObject}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionContext#findRealObjects(DBRProgressMonitor,
   * DBSObjectType, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionContext.findRealObjects(DBRProgressMonitor, DBSObjectType, List)"
  })
  public void testFindRealObjects_whenOrgJkissDbeaverModelStructDBSObject_thenReturnSizeIsOne2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPImage image = mock(DBPImage.class);
    Class<DBSObject> objectClass = DBSObject.class;

    AbstractObjectType objectType =
        new AbstractObjectType(
            "Type Name", "The characteristics of someone or something", image, objectClass);

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("foo");
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsResult =
        sqlQueryConnectionDummyContext.findRealObjects(monitor, objectType, objectName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealObjectsResult.size());
  }
}
