package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.output.DBCOutputWriter;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.jkiss.dbeaver.model.sql.data.SQLQueryDataContainer;
import org.jkiss.dbeaver.model.sql.semantics.SQLQuerySymbol;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.ContextObjectInfo;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLBuiltinFunctionCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLCompositeFieldCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLDbNamedObjectCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLDbObjectCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLProcedureCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLReservedWordCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLRowsSourceAliasCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLSpecialCompositeFieldCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLSpecialTextCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLTableNameCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryExprType;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryExprType.SQLQueryExprTypeMemberInfo;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryResultColumn;
import org.jkiss.dbeaver.model.sql.semantics.context.SourceResolutionResult;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedure;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLQueryCompletionItemDiffblueTest {
  /**
   * Test {@link SQLQueryCompletionItem#getScore()}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#getScore()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryCompletionItem.getScore()"})
  public void testGetScore() {
    // Arrange
    SQLReservedWordCompletionItem sqlReservedWordCompletionItem =
        new SQLReservedWordCompletionItem(3, new SQLQueryWordEntry(2, "String"), "Text");

    // Act and Assert
    assertEquals(3, sqlReservedWordCompletionItem.getScore());
  }

  /**
   * Test {@link SQLQueryCompletionItem#getFilterInfo()}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#getFilterInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryWordEntry SQLQueryCompletionItem.getFilterInfo()"})
  public void testGetFilterInfo() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    SQLReservedWordCompletionItem sqlReservedWordCompletionItem =
        new SQLReservedWordCompletionItem(3, filterKey, "Text");

    // Act and Assert
    assertSame(filterKey, sqlReservedWordCompletionItem.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#getObject()}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#getObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject SQLQueryCompletionItem.getObject()"})
  public void testGetObject() {
    // Arrange
    SQLReservedWordCompletionItem sqlReservedWordCompletionItem =
        new SQLReservedWordCompletionItem(3, new SQLQueryWordEntry(2, "String"), "Text");

    // Act and Assert
    assertNull(sqlReservedWordCompletionItem.getObject());
  }

  /**
   * Test {@link SQLQueryCompletionItem#apply(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#apply(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLQueryCompletionItem.apply(SQLQueryCompletionItemVisitor)"})
  public void testApply() {
    // Arrange
    SQLReservedWordCompletionItem sqlReservedWordCompletionItem =
        new SQLReservedWordCompletionItem(3, new SQLQueryWordEntry(2, "String"), "Text");

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitReservedWord(Mockito.<SQLReservedWordCompletionItem>any()))
        .thenReturn("Visit Reserved Word");

    // Act
    Object actualApplyResult = sqlReservedWordCompletionItem.apply(visitor);

    // Assert
    verify(visitor).visitReservedWord(isA(SQLReservedWordCompletionItem.class));
    assertEquals("Visit Reserved Word", actualApplyResult);
  }

  /**
   * Test {@link SQLQueryCompletionItem#forReservedWord(int, SQLQueryWordEntry, String)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forReservedWord(int, SQLQueryWordEntry,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forReservedWord(int, SQLQueryWordEntry, String)"
  })
  public void testForReservedWord() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

    // Act
    SQLQueryCompletionItem actualForReservedWordResult =
        SQLQueryCompletionItem.forReservedWord(3, filterKey, "Text");

    // Assert
    assertTrue(actualForReservedWordResult instanceof SQLReservedWordCompletionItem);
    assertEquals("Text", ((SQLReservedWordCompletionItem) actualForReservedWordResult).text);
    assertNull(actualForReservedWordResult.getObject());
    assertEquals(3, actualForReservedWordResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.RESERVED, actualForReservedWordResult.getKind());
    assertSame(filterKey, actualForReservedWordResult.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forSpecialText(int, SQLQueryWordEntry, String, String)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forSpecialText(int, SQLQueryWordEntry,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forSpecialText(int, SQLQueryWordEntry, String, String)"
  })
  public void testForSpecialText() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

    // Act
    SQLQueryCompletionItem actualForSpecialTextResult =
        SQLQueryCompletionItem.forSpecialText(
            3, filterKey, "Text", "The characteristics of someone or something");

    // Assert
    assertTrue(actualForSpecialTextResult instanceof SQLSpecialTextCompletionItem);
    assertEquals("Text", ((SQLSpecialTextCompletionItem) actualForSpecialTextResult).text);
    assertEquals(
        "The characteristics of someone or something",
        ((SQLSpecialTextCompletionItem) actualForSpecialTextResult).description);
    assertNull(actualForSpecialTextResult.getObject());
    assertEquals(3, actualForSpecialTextResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.UNKNOWN, actualForSpecialTextResult.getKind());
    assertSame(filterKey, actualForSpecialTextResult.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forRealTable(int, SQLQueryWordEntry, ContextObjectInfo,
   * DBSEntity, boolean, boolean)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forRealTable(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSEntity, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forRealTable(int, SQLQueryWordEntry, ContextObjectInfo, DBSEntity, boolean, boolean)"
  })
  public void testForRealTable() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    SQLQueryCompletionItem actualForRealTableResult =
        SQLQueryCompletionItem.forRealTable(3, filterKey, resolvedContext, table, true, true);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertTrue(actualForRealTableResult instanceof SQLTableNameCompletionItem);
    DBSObject object2 = actualForRealTableResult.getObject();
    assertTrue(object2 instanceof DBVEntity);
    assertEquals(3, actualForRealTableResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.RELATED_TABLE_NAME, actualForRealTableResult.getKind());
    assertTrue(((SQLTableNameCompletionItem) actualForRealTableResult).isRelated);
    assertTrue(((SQLTableNameCompletionItem) actualForRealTableResult).isUsed);
    assertSame(filterKey, actualForRealTableResult.getFilterInfo());
    assertSame(table, object2);
  }

  /**
   * Test {@link SQLQueryCompletionItem#forDbObject(int, SQLQueryWordEntry, ContextObjectInfo,
   * DBSObject)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forDbObject(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forDbObject(int, SQLQueryWordEntry, ContextObjectInfo, DBSObject)"
  })
  public void testForDbObject() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);

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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider3,
            sourceFile2,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

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

    SQLQueryDataContainer object2 =
        new SQLQueryDataContainer(contextProvider5, query2, scriptContext2, Log.getLog(forClass2));

    // Act
    SQLQueryCompletionItem actualForDbObjectResult =
        SQLQueryCompletionItem.forDbObject(3, filterKey, resolvedContext, object2);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
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
    DBSObject object3 = actualForDbObjectResult.getObject();
    assertTrue(object3 instanceof SQLQueryDataContainer);
    assertTrue(actualForDbObjectResult instanceof SQLDbNamedObjectCompletionItem);
    assertEquals(3, actualForDbObjectResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.UNKNOWN, actualForDbObjectResult.getKind());
    assertSame(object2, object3);
    assertSame(filterKey, actualForDbObjectResult.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forDbCatalogObject(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSObject)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forDbCatalogObject(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forDbCatalogObject(int, SQLQueryWordEntry, ContextObjectInfo, DBSObject)"
  })
  public void testForDbCatalogObject() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);

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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider3,
            sourceFile2,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

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

    SQLQueryDataContainer object2 =
        new SQLQueryDataContainer(contextProvider5, query2, scriptContext2, Log.getLog(forClass2));

    // Act
    SQLQueryCompletionItem actualForDbCatalogObjectResult =
        SQLQueryCompletionItem.forDbCatalogObject(3, filterKey, resolvedContext, object2);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
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
    DBSObject object3 = actualForDbCatalogObjectResult.getObject();
    assertTrue(object3 instanceof SQLQueryDataContainer);
    assertTrue(actualForDbCatalogObjectResult instanceof SQLDbNamedObjectCompletionItem);
    assertEquals(3, actualForDbCatalogObjectResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.CATALOG, actualForDbCatalogObjectResult.getKind());
    assertSame(object2, object3);
    assertSame(filterKey, actualForDbCatalogObjectResult.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forDbSchemaObject(int, SQLQueryWordEntry, ContextObjectInfo,
   * DBSObject)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forDbSchemaObject(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forDbSchemaObject(int, SQLQueryWordEntry, ContextObjectInfo, DBSObject)"
  })
  public void testForDbSchemaObject() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);

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

    SQLScriptContext parentContext2 =
        new SQLScriptContext(
            mock(SQLScriptContext.class),
            contextProvider3,
            sourceFile2,
            mock(DBCOutputWriter.class),
            mock(SQLParametersProvider.class));

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

    SQLQueryDataContainer object2 =
        new SQLQueryDataContainer(contextProvider5, query2, scriptContext2, Log.getLog(forClass2));

    // Act
    SQLQueryCompletionItem actualForDbSchemaObjectResult =
        SQLQueryCompletionItem.forDbSchemaObject(3, filterKey, resolvedContext, object2);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
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
    DBSObject object3 = actualForDbSchemaObjectResult.getObject();
    assertTrue(object3 instanceof SQLQueryDataContainer);
    assertTrue(actualForDbSchemaObjectResult instanceof SQLDbNamedObjectCompletionItem);
    assertEquals(3, actualForDbSchemaObjectResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.SCHEMA, actualForDbSchemaObjectResult.getKind());
    assertSame(object2, object3);
    assertSame(filterKey, actualForDbSchemaObjectResult.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forCompositeField(int, SQLQueryWordEntry,
   * DBSEntityAttribute, SQLQueryExprTypeMemberInfo)}.
   *
   * <ul>
   *   <li>Then return {@link SQLCompositeFieldCompletionItem}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forCompositeField(int, SQLQueryWordEntry,
   * DBSEntityAttribute, SQLQueryExprTypeMemberInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forCompositeField(int, SQLQueryWordEntry, DBSEntityAttribute, SQLQueryExprTypeMemberInfo)"
  })
  public void testForCompositeField_thenReturnSQLCompositeFieldCompletionItem() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity realSource = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute realAttr =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    SQLQueryResultColumn column =
        new SQLQueryResultColumn(1, symbol, null, realSource, realAttr, SQLQueryExprType.BOOLEAN);

    SQLQueryExprTypeMemberInfo memberInfo =
        new SQLQueryExprTypeMemberInfo(
            SQLQueryExprType.BOOLEAN, "Name", SQLQueryExprType.BOOLEAN, attribute2, column);

    // Act
    SQLQueryCompletionItem actualForCompositeFieldResult =
        SQLQueryCompletionItem.forCompositeField(3, filterKey, attribute, memberInfo);

    // Assert
    assertTrue(actualForCompositeFieldResult instanceof SQLCompositeFieldCompletionItem);
    DBSObject object = actualForCompositeFieldResult.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertNull(((SQLCompositeFieldCompletionItem) actualForCompositeFieldResult).resolvedContext);
    assertEquals(3, actualForCompositeFieldResult.getScore());
    assertEquals(
        SQLQueryCompletionItemKind.COMPOSITE_FIELD_NAME, actualForCompositeFieldResult.getKind());
    assertSame(filterKey, actualForCompositeFieldResult.getFilterInfo());
    assertSame(attribute, object);
  }

  /**
   * Test {@link SQLQueryCompletionItem#forSpecialCompositeField(int, SQLQueryWordEntry,
   * SQLQueryExprTypeMemberInfo)}.
   *
   * <ul>
   *   <li>Then return {@link SQLSpecialCompositeFieldCompletionItem}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forSpecialCompositeField(int,
   * SQLQueryWordEntry, SQLQueryExprTypeMemberInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forSpecialCompositeField(int, SQLQueryWordEntry, SQLQueryExprTypeMemberInfo)"
  })
  public void testForSpecialCompositeField_thenReturnSQLSpecialCompositeFieldCompletionItem() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity realSource = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute realAttr =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    SQLQueryResultColumn column =
        new SQLQueryResultColumn(1, symbol, null, realSource, realAttr, SQLQueryExprType.BOOLEAN);

    SQLQueryExprTypeMemberInfo memberInfo =
        new SQLQueryExprTypeMemberInfo(
            SQLQueryExprType.BOOLEAN, "Name", SQLQueryExprType.BOOLEAN, attribute, column);

    // Act
    SQLQueryCompletionItem actualForSpecialCompositeFieldResult =
        SQLQueryCompletionItem.forSpecialCompositeField(3, filterKey, memberInfo);

    // Assert
    assertTrue(
        actualForSpecialCompositeFieldResult instanceof SQLSpecialCompositeFieldCompletionItem);
    SQLQueryExprTypeMemberInfo sqlQueryExprTypeMemberInfo =
        ((SQLSpecialCompositeFieldCompletionItem) actualForSpecialCompositeFieldResult).memberInfo;
    DBSEntityAttribute attributeResult = sqlQueryExprTypeMemberInfo.attribute();
    assertTrue(attributeResult instanceof DBVEntityAttribute);
    assertEquals("Name", sqlQueryExprTypeMemberInfo.name());
    assertNull(actualForSpecialCompositeFieldResult.getObject());
    assertEquals(3, actualForSpecialCompositeFieldResult.getScore());
    assertEquals(
        SQLQueryCompletionItemKind.COMPOSITE_FIELD_NAME,
        actualForSpecialCompositeFieldResult.getKind());
    assertSame(filterKey, actualForSpecialCompositeFieldResult.getFilterInfo());
    assertSame(column, sqlQueryExprTypeMemberInfo.column());
    assertSame(attribute, attributeResult);
    SQLQueryExprType sqlQueryExprType = column.type;
    assertSame(sqlQueryExprType, sqlQueryExprTypeMemberInfo.declaratorType());
    assertSame(sqlQueryExprType, sqlQueryExprTypeMemberInfo.type());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forBuiltinFunction(int, SQLQueryWordEntry, String)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forBuiltinFunction(int, SQLQueryWordEntry,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forBuiltinFunction(int, SQLQueryWordEntry, String)"
  })
  public void testForBuiltinFunction() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

    // Act
    SQLQueryCompletionItem actualForBuiltinFunctionResult =
        SQLQueryCompletionItem.forBuiltinFunction(3, filterKey, "Name");

    // Assert
    assertTrue(actualForBuiltinFunctionResult instanceof SQLBuiltinFunctionCompletionItem);
    assertEquals("Name", ((SQLBuiltinFunctionCompletionItem) actualForBuiltinFunctionResult).name);
    assertNull(actualForBuiltinFunctionResult.getObject());
    assertEquals(3, actualForBuiltinFunctionResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.PROCEDURE, actualForBuiltinFunctionResult.getKind());
    assertSame(filterKey, actualForBuiltinFunctionResult.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionItem#forProcedureObject(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSProcedure)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionItem#forProcedureObject(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryCompletionItem SQLQueryCompletionItem.forProcedureObject(int, SQLQueryWordEntry, ContextObjectInfo, DBSProcedure)"
  })
  public void testForProcedureObject() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    DBSProcedure object2 = mock(DBSProcedure.class);

    // Act
    SQLQueryCompletionItem actualForProcedureObjectResult =
        SQLQueryCompletionItem.forProcedureObject(3, filterKey, resolvedContext, object2);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    ContextObjectInfo contextObjectInfo =
        ((SQLProcedureCompletionItem) actualForProcedureObjectResult).resolvedContext;
    DBSObject objectResult = contextObjectInfo.object();
    assertTrue(objectResult instanceof SQLQueryDataContainer);
    assertTrue(actualForProcedureObjectResult instanceof SQLProcedureCompletionItem);
    assertEquals("String", contextObjectInfo.string());
    assertEquals(3, actualForProcedureObjectResult.getScore());
    assertEquals(SQLQueryCompletionItemKind.PROCEDURE, actualForProcedureObjectResult.getKind());
    assertTrue(contextObjectInfo.preventFullName());
    assertSame(object, objectResult);
    assertSame(filterKey, actualForProcedureObjectResult.getFilterInfo());
    assertSame(object2, actualForProcedureObjectResult.getObject());
  }

  /**
   * Test SQLCompositeFieldCompletionItem {@link
   * SQLCompositeFieldCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <ul>
   *   <li>Then return {@code Visit Composite Field}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLCompositeFieldCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLCompositeFieldCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"
  })
  public void testSQLCompositeFieldCompletionItemApplyImpl_thenReturnVisitCompositeField() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVEntity realSource =
        new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute realAttr =
        new DBVEntityAttribute(mock(DBVEntity.class), mock(DBVEntityAttribute.class), "Name");

    SQLQueryResultColumn column =
        new SQLQueryResultColumn(1, symbol, null, realSource, realAttr, SQLQueryExprType.BOOLEAN);

    SQLQueryExprTypeMemberInfo memberInfo =
        new SQLQueryExprTypeMemberInfo(
            SQLQueryExprType.BOOLEAN, "Name", SQLQueryExprType.BOOLEAN, attribute2, column);

    SQLCompositeFieldCompletionItem sqlCompositeFieldCompletionItem =
        new SQLCompositeFieldCompletionItem(3, filterKey, attribute, memberInfo);

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitCompositeField(Mockito.<SQLCompositeFieldCompletionItem>any()))
        .thenReturn("Visit Composite Field");

    // Act
    Object actualApplyImplResult = sqlCompositeFieldCompletionItem.applyImpl(visitor);

    // Assert
    verify(visitor).visitCompositeField(isA(SQLCompositeFieldCompletionItem.class));
    assertEquals("Visit Composite Field", actualApplyImplResult);
  }

  /**
   * Test SQLDbNamedObjectCompletionItem {@link
   * SQLDbNamedObjectCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link
   * SQLDbNamedObjectCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLDbNamedObjectCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"
  })
  public void testSQLDbNamedObjectCompletionItemApplyImpl() {
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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBCExecutionContext dbcExecutionContext2 = mock(DBCExecutionContext.class);
    when(dbcExecutionContext2.getDataSource()).thenReturn(dbpDataSource2);

    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    when(contextProvider3.getExecutionContext()).thenReturn(dbcExecutionContext2);
    SQLScriptContext parentContext2 = mock(SQLScriptContext.class);
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext2 =
        new SQLScriptContext(
            parentContext2,
            contextProvider3,
            sourceFile2,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass2 = Object.class;

    SQLQueryDataContainer object2 =
        new SQLQueryDataContainer(contextProvider4, query2, scriptContext2, Log.getLog(forClass2));

    SQLDbNamedObjectCompletionItem sqlDbNamedObjectCompletionItem =
        new SQLDbNamedObjectCompletionItem(
            3,
            new SQLQueryWordEntry(2, "String"),
            resolvedContext,
            object2,
            SQLQueryCompletionItemKind.UNKNOWN);

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitNamedObject(Mockito.<SQLDbNamedObjectCompletionItem>any()))
        .thenReturn("Visit Named Object");

    // Act
    Object actualApplyImplResult = sqlDbNamedObjectCompletionItem.applyImpl(visitor);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(contextProvider3).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer2).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(dbcExecutionContext2).getDataSource();
    verify(visitor).visitNamedObject(isA(SQLDbNamedObjectCompletionItem.class));
    assertEquals("Visit Named Object", actualApplyImplResult);
  }

  /**
   * Test SQLDbNamedObjectCompletionItem getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLDbNamedObjectCompletionItem#SQLDbNamedObjectCompletionItem(int,
   *       SQLQueryWordEntry, ContextObjectInfo, DBSObject, SQLQueryCompletionItemKind)}
   *   <li>{@link SQLDbNamedObjectCompletionItem#getKind()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLDbNamedObjectCompletionItem.<init>(int, SQLQueryWordEntry, ContextObjectInfo, DBSObject, SQLQueryCompletionItemKind)",
    "SQLQueryCompletionItemKind SQLDbNamedObjectCompletionItem.getKind()"
  })
  public void testSQLDbNamedObjectCompletionItemGettersAndSetters() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    DBPContextProvider contextProvider3 = mock(DBPContextProvider.class);
    SQLQuery query2 = new SQLQuery(mock(DBPDataSource.class), "Text");
    Path sourceFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    SQLScriptContext parentContext =
        new SQLScriptContext(
            null,
            mock(DBPContextProvider.class),
            sourceFile2,
            (DBCOutputWriter) null,
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider4 = mock(DBPContextProvider.class);
    Path sourceFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext2 =
        new SQLScriptContext(
            parentContext,
            contextProvider4,
            sourceFile3,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass2 = Object.class;

    SQLQueryDataContainer object2 =
        new SQLQueryDataContainer(contextProvider3, query2, scriptContext2, Log.getLog(forClass2));

    // Act
    SQLDbNamedObjectCompletionItem actualSqlDbNamedObjectCompletionItem =
        new SQLDbNamedObjectCompletionItem(
            3, filterKey, resolvedContext, object2, SQLQueryCompletionItemKind.UNKNOWN);
    SQLQueryCompletionItemKind actualKind = actualSqlDbNamedObjectCompletionItem.getKind();

    // Assert
    ContextObjectInfo contextObjectInfo = actualSqlDbNamedObjectCompletionItem.resolvedContext;
    assertEquals("String", contextObjectInfo.string());
    assertEquals(3, actualSqlDbNamedObjectCompletionItem.getScore());
    assertEquals(SQLQueryCompletionItemKind.UNKNOWN, actualKind);
    assertTrue(contextObjectInfo.preventFullName());
    assertSame(object, contextObjectInfo.object());
    assertSame(object2, actualSqlDbNamedObjectCompletionItem.getObject());
    assertSame(filterKey, actualSqlDbNamedObjectCompletionItem.getFilterInfo());
  }

  /**
   * Test SQLDbObjectCompletionItem {@link SQLDbObjectCompletionItem#getObject()}.
   *
   * <p>Method under test: {@link SQLDbObjectCompletionItem#getObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject SQLDbObjectCompletionItem.getObject()"})
  public void testSQLDbObjectCompletionItemGetObject() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVEntity realSource =
        new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute realAttr =
        new DBVEntityAttribute(mock(DBVEntity.class), mock(DBVEntityAttribute.class), "Name");

    SQLQueryResultColumn column =
        new SQLQueryResultColumn(1, symbol, null, realSource, realAttr, SQLQueryExprType.BOOLEAN);

    SQLQueryExprTypeMemberInfo memberInfo =
        new SQLQueryExprTypeMemberInfo(
            SQLQueryExprType.BOOLEAN, "Name", SQLQueryExprType.BOOLEAN, attribute2, column);

    SQLCompositeFieldCompletionItem sqlCompositeFieldCompletionItem =
        new SQLCompositeFieldCompletionItem(3, filterKey, attribute, memberInfo);

    // Act
    DBSEntityAttribute actualObject = sqlCompositeFieldCompletionItem.getObject();

    // Assert
    assertSame(sqlCompositeFieldCompletionItem.object, actualObject);
  }

  /**
   * Test SQLProcedureCompletionItem {@link
   * SQLProcedureCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link
   * SQLProcedureCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLProcedureCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"})
  public void testSQLProcedureCompletionItemApplyImpl() {
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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    SQLProcedureCompletionItem sqlProcedureCompletionItem =
        new SQLProcedureCompletionItem(
            3, new SQLQueryWordEntry(2, "String"), resolvedContext, mock(DBSProcedure.class));

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitProcedure(Mockito.<SQLProcedureCompletionItem>any()))
        .thenReturn("Visit Procedure");

    // Act
    Object actualApplyImplResult = sqlProcedureCompletionItem.applyImpl(visitor);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(visitor).visitProcedure(isA(SQLProcedureCompletionItem.class));
    assertEquals("Visit Procedure", actualApplyImplResult);
  }

  /**
   * Test SQLProcedureCompletionItem getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLProcedureCompletionItem#SQLProcedureCompletionItem(int, SQLQueryWordEntry,
   *       ContextObjectInfo, DBSProcedure)}
   *   <li>{@link SQLProcedureCompletionItem#getKind()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLProcedureCompletionItem.<init>(int, SQLQueryWordEntry, ContextObjectInfo, DBSProcedure)",
    "SQLQueryCompletionItemKind SQLProcedureCompletionItem.getKind()"
  })
  public void testSQLProcedureCompletionItemGettersAndSetters() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    DBSProcedure object2 = mock(DBSProcedure.class);

    // Act
    SQLProcedureCompletionItem actualSqlProcedureCompletionItem =
        new SQLProcedureCompletionItem(3, filterKey, resolvedContext, object2);
    SQLQueryCompletionItemKind actualKind = actualSqlProcedureCompletionItem.getKind();

    // Assert
    ContextObjectInfo contextObjectInfo = actualSqlProcedureCompletionItem.resolvedContext;
    assertEquals("String", contextObjectInfo.string());
    assertEquals(3, actualSqlProcedureCompletionItem.getScore());
    assertEquals(SQLQueryCompletionItemKind.PROCEDURE, actualKind);
    assertTrue(contextObjectInfo.preventFullName());
    assertSame(object, contextObjectInfo.object());
    assertSame(filterKey, actualSqlProcedureCompletionItem.getFilterInfo());
    assertSame(object2, actualSqlProcedureCompletionItem.getObject());
  }

  /**
   * Test SQLReservedWordCompletionItem {@link
   * SQLReservedWordCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link
   * SQLReservedWordCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLReservedWordCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"
  })
  public void testSQLReservedWordCompletionItemApplyImpl() {
    // Arrange
    SQLReservedWordCompletionItem sqlReservedWordCompletionItem =
        new SQLReservedWordCompletionItem(3, new SQLQueryWordEntry(2, "String"), "Text");

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitReservedWord(Mockito.<SQLReservedWordCompletionItem>any()))
        .thenReturn("Visit Reserved Word");

    // Act
    Object actualApplyImplResult = sqlReservedWordCompletionItem.applyImpl(visitor);

    // Assert
    verify(visitor).visitReservedWord(isA(SQLReservedWordCompletionItem.class));
    assertEquals("Visit Reserved Word", actualApplyImplResult);
  }

  /**
   * Test SQLReservedWordCompletionItem getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLReservedWordCompletionItem#SQLReservedWordCompletionItem(int,
   *       SQLQueryWordEntry, String)}
   *   <li>{@link SQLReservedWordCompletionItem#getKind()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLReservedWordCompletionItem.<init>(int, SQLQueryWordEntry, String)",
    "SQLQueryCompletionItemKind SQLReservedWordCompletionItem.getKind()"
  })
  public void testSQLReservedWordCompletionItemGettersAndSetters() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

    // Act
    SQLReservedWordCompletionItem actualSqlReservedWordCompletionItem =
        new SQLReservedWordCompletionItem(3, filterKey, "Text");
    SQLQueryCompletionItemKind actualKind = actualSqlReservedWordCompletionItem.getKind();

    // Assert
    assertEquals(3, actualSqlReservedWordCompletionItem.getScore());
    assertEquals(SQLQueryCompletionItemKind.RESERVED, actualKind);
    assertSame(filterKey, actualSqlReservedWordCompletionItem.getFilterInfo());
  }

  /**
   * Test SQLRowsSourceAliasCompletionItem {@link
   * SQLRowsSourceAliasCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <ul>
   *   <li>Then return {@code Visit Subquery Alias}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLRowsSourceAliasCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLRowsSourceAliasCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"
  })
  public void testSQLRowsSourceAliasCompletionItemApplyImpl_thenReturnVisitSubqueryAlias() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity tableOrNull = new DBVEntity(container, "Name", "Description Column Names");
    SourceResolutionResult sourceInfo =
        new SourceResolutionResult(null, null, tableOrNull, new SQLQuerySymbol("Name"));

    SQLRowsSourceAliasCompletionItem sqlRowsSourceAliasCompletionItem =
        new SQLRowsSourceAliasCompletionItem(3, filterKey, symbol, sourceInfo, true);

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitSubqueryAlias(Mockito.<SQLRowsSourceAliasCompletionItem>any()))
        .thenReturn("Visit Subquery Alias");

    // Act
    Object actualApplyImplResult = sqlRowsSourceAliasCompletionItem.applyImpl(visitor);

    // Assert
    verify(visitor).visitSubqueryAlias(isA(SQLRowsSourceAliasCompletionItem.class));
    assertEquals("Visit Subquery Alias", actualApplyImplResult);
  }

  /**
   * Test SQLRowsSourceAliasCompletionItem {@link SQLRowsSourceAliasCompletionItem#getKind()}.
   *
   * <ul>
   *   <li>Then return {@code RELATED_SUBQUERY_ALIAS}.
   * </ul>
   *
   * <p>Method under test: {@link SQLRowsSourceAliasCompletionItem#getKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryCompletionItemKind SQLRowsSourceAliasCompletionItem.getKind()"})
  public void testSQLRowsSourceAliasCompletionItemGetKind_thenReturnRelatedSubqueryAlias() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity tableOrNull = new DBVEntity(container, "Name", "Description Column Names");
    SourceResolutionResult sourceInfo =
        new SourceResolutionResult(null, null, tableOrNull, new SQLQuerySymbol("Name"));

    SQLRowsSourceAliasCompletionItem sqlRowsSourceAliasCompletionItem =
        new SQLRowsSourceAliasCompletionItem(3, filterKey, symbol, sourceInfo, true);

    // Act and Assert
    assertEquals(
        SQLQueryCompletionItemKind.RELATED_SUBQUERY_ALIAS,
        sqlRowsSourceAliasCompletionItem.getKind());
  }

  /**
   * Test SQLRowsSourceAliasCompletionItem {@link SQLRowsSourceAliasCompletionItem#getKind()}.
   *
   * <ul>
   *   <li>Then return {@code SUBQUERY_ALIAS}.
   * </ul>
   *
   * <p>Method under test: {@link SQLRowsSourceAliasCompletionItem#getKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryCompletionItemKind SQLRowsSourceAliasCompletionItem.getKind()"})
  public void testSQLRowsSourceAliasCompletionItemGetKind_thenReturnSubqueryAlias() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity tableOrNull = new DBVEntity(container, "Name", "Description Column Names");
    SourceResolutionResult sourceInfo =
        new SourceResolutionResult(null, null, tableOrNull, new SQLQuerySymbol("Name"));

    SQLRowsSourceAliasCompletionItem sqlRowsSourceAliasCompletionItem =
        new SQLRowsSourceAliasCompletionItem(3, filterKey, symbol, sourceInfo, false);

    // Act and Assert
    assertEquals(
        SQLQueryCompletionItemKind.SUBQUERY_ALIAS, sqlRowsSourceAliasCompletionItem.getKind());
  }

  /**
   * Test SQLSpecialCompositeFieldCompletionItem {@link
   * SQLSpecialCompositeFieldCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link
   * SQLSpecialCompositeFieldCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLSpecialCompositeFieldCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"
  })
  public void testSQLSpecialCompositeFieldCompletionItemApplyImpl() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVEntity entity = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVEntity realSource =
        new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute realAttr =
        new DBVEntityAttribute(mock(DBVEntity.class), mock(DBVEntityAttribute.class), "Name");

    SQLQueryResultColumn column =
        new SQLQueryResultColumn(1, symbol, null, realSource, realAttr, SQLQueryExprType.BOOLEAN);

    SQLQueryExprTypeMemberInfo memberInfo =
        new SQLQueryExprTypeMemberInfo(
            SQLQueryExprType.BOOLEAN, "Name", SQLQueryExprType.BOOLEAN, attribute, column);

    SQLSpecialCompositeFieldCompletionItem sqlSpecialCompositeFieldCompletionItem =
        new SQLSpecialCompositeFieldCompletionItem(3, filterKey, memberInfo);

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitSpecialCompositeField(Mockito.<SQLSpecialCompositeFieldCompletionItem>any()))
        .thenReturn("Visit Special Composite Field");

    // Act
    Object actualApplyImplResult = sqlSpecialCompositeFieldCompletionItem.applyImpl(visitor);

    // Assert
    verify(visitor).visitSpecialCompositeField(isA(SQLSpecialCompositeFieldCompletionItem.class));
    assertEquals("Visit Special Composite Field", actualApplyImplResult);
  }

  /**
   * Test SQLSpecialTextCompletionItem {@link
   * SQLSpecialTextCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link
   * SQLSpecialTextCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLSpecialTextCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"
  })
  public void testSQLSpecialTextCompletionItemApplyImpl() {
    // Arrange
    SQLSpecialTextCompletionItem sqlSpecialTextCompletionItem =
        new SQLSpecialTextCompletionItem(
            3,
            new SQLQueryWordEntry(2, "String"),
            "Text",
            "The characteristics of someone or something");

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitSpecialText(Mockito.<SQLSpecialTextCompletionItem>any()))
        .thenReturn("Visit Special Text");

    // Act
    Object actualApplyImplResult = sqlSpecialTextCompletionItem.applyImpl(visitor);

    // Assert
    verify(visitor).visitSpecialText(isA(SQLSpecialTextCompletionItem.class));
    assertEquals("Visit Special Text", actualApplyImplResult);
  }

  /**
   * Test SQLSpecialTextCompletionItem getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLSpecialTextCompletionItem#SQLSpecialTextCompletionItem(int, SQLQueryWordEntry,
   *       String, String)}
   *   <li>{@link SQLSpecialTextCompletionItem#getKind()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLSpecialTextCompletionItem.<init>(int, SQLQueryWordEntry, String, String)",
    "SQLQueryCompletionItemKind SQLSpecialTextCompletionItem.getKind()"
  })
  public void testSQLSpecialTextCompletionItemGettersAndSetters() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");

    // Act
    SQLSpecialTextCompletionItem actualSqlSpecialTextCompletionItem =
        new SQLSpecialTextCompletionItem(
            3, filterKey, "Text", "The characteristics of someone or something");
    SQLQueryCompletionItemKind actualKind = actualSqlSpecialTextCompletionItem.getKind();

    // Assert
    assertEquals(3, actualSqlSpecialTextCompletionItem.getScore());
    assertEquals(SQLQueryCompletionItemKind.UNKNOWN, actualKind);
    assertSame(filterKey, actualSqlSpecialTextCompletionItem.getFilterInfo());
  }

  /**
   * Test SQLTableNameCompletionItem {@link
   * SQLTableNameCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}.
   *
   * <p>Method under test: {@link
   * SQLTableNameCompletionItem#applyImpl(SQLQueryCompletionItemVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLTableNameCompletionItem.applyImpl(SQLQueryCompletionItemVisitor)"})
  public void testSQLTableNameCompletionItemApplyImpl() {
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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    SQLTableNameCompletionItem sqlTableNameCompletionItem =
        new SQLTableNameCompletionItem(3, filterKey, resolvedContext, table, true, true);

    SQLQueryCompletionItemVisitor<Object> visitor = mock(SQLQueryCompletionItemVisitor.class);
    when(visitor.visitTableName(Mockito.<SQLTableNameCompletionItem>any()))
        .thenReturn("Visit Table Name");

    // Act
    Object actualApplyImplResult = sqlTableNameCompletionItem.applyImpl(visitor);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(visitor).visitTableName(isA(SQLTableNameCompletionItem.class));
    assertEquals("Visit Table Name", actualApplyImplResult);
  }

  /**
   * Test SQLTableNameCompletionItem {@link SQLTableNameCompletionItem#getKind()}.
   *
   * <ul>
   *   <li>Then return {@code NEW_TABLE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableNameCompletionItem#getKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryCompletionItemKind SQLTableNameCompletionItem.getKind()"})
  public void testSQLTableNameCompletionItemGetKind_thenReturnNewTableName() {
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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    SQLTableNameCompletionItem sqlTableNameCompletionItem =
        new SQLTableNameCompletionItem(3, filterKey, resolvedContext, table, false, false);

    // Act
    SQLQueryCompletionItemKind actualKind = sqlTableNameCompletionItem.getKind();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals(SQLQueryCompletionItemKind.NEW_TABLE_NAME, actualKind);
  }

  /**
   * Test SQLTableNameCompletionItem {@link SQLTableNameCompletionItem#getKind()}.
   *
   * <ul>
   *   <li>Then return {@code RELATED_TABLE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableNameCompletionItem#getKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryCompletionItemKind SQLTableNameCompletionItem.getKind()"})
  public void testSQLTableNameCompletionItemGetKind_thenReturnRelatedTableName() {
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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    SQLTableNameCompletionItem sqlTableNameCompletionItem =
        new SQLTableNameCompletionItem(3, filterKey, resolvedContext, table, true, true);

    // Act
    SQLQueryCompletionItemKind actualKind = sqlTableNameCompletionItem.getKind();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals(SQLQueryCompletionItemKind.RELATED_TABLE_NAME, actualKind);
  }

  /**
   * Test SQLTableNameCompletionItem {@link SQLTableNameCompletionItem#getKind()}.
   *
   * <ul>
   *   <li>Then return {@code USED_TABLE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableNameCompletionItem#getKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryCompletionItemKind SQLTableNameCompletionItem.getKind()"})
  public void testSQLTableNameCompletionItemGetKind_thenReturnUsedTableName() {
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

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    SQLTableNameCompletionItem sqlTableNameCompletionItem =
        new SQLTableNameCompletionItem(3, filterKey, resolvedContext, table, true, false);

    // Act
    SQLQueryCompletionItemKind actualKind = sqlTableNameCompletionItem.getKind();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals(SQLQueryCompletionItemKind.USED_TABLE_NAME, actualKind);
  }

  /**
   * Test SQLTableNameCompletionItem {@link
   * SQLTableNameCompletionItem#SQLTableNameCompletionItem(int, SQLQueryWordEntry,
   * ContextObjectInfo, DBSEntity, boolean, boolean)}.
   *
   * <p>Method under test: {@link SQLTableNameCompletionItem#SQLTableNameCompletionItem(int,
   * SQLQueryWordEntry, ContextObjectInfo, DBSEntity, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTableNameCompletionItem.<init>(int, SQLQueryWordEntry, ContextObjectInfo, DBSEntity, boolean, boolean)"
  })
  public void testSQLTableNameCompletionItemNewSQLTableNameCompletionItem() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider2,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider, query, scriptContext, Log.getLog(forClass));
    ContextObjectInfo resolvedContext = new ContextObjectInfo("String", object, true);
    DBVEntity table =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act
    SQLTableNameCompletionItem actualSqlTableNameCompletionItem =
        new SQLTableNameCompletionItem(3, filterKey, resolvedContext, table, true, true);

    // Assert
    ContextObjectInfo contextObjectInfo = actualSqlTableNameCompletionItem.resolvedContext;
    assertEquals("String", contextObjectInfo.string());
    assertEquals(3, actualSqlTableNameCompletionItem.getScore());
    assertTrue(contextObjectInfo.preventFullName());
    assertSame(object, contextObjectInfo.object());
    assertSame(filterKey, actualSqlTableNameCompletionItem.getFilterInfo());
    assertSame(table, actualSqlTableNameCompletionItem.getObject());
  }
}
