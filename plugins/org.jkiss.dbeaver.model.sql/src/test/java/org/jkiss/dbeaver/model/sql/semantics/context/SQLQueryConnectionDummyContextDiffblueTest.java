package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.semantics.model.select.SQLQueryRowsSourceModel;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLQueryConnectionDummyContextDiffblueTest {
  /**
   * Test {@link SQLQueryConnectionDummyContext#isDummyObject(Object)}.
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#isDummyObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryConnectionDummyContext.isDummyObject(Object)"})
  public void testIsDummyObject() {
    // Arrange, Act and Assert
    assertFalse(SQLQueryConnectionDummyContext.isDummyObject("Obj"));
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Dummy data source for purposes of static query semantic analysis");
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    HashSet<String> knownColumnNames = new HashSet<>();
    knownColumnNames.add("Dummy data source for purposes of static query semantic analysis");
    knownColumnNames.add("DummyDataSource");

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect).getUnquotedIdentifier("DummyDataSource");
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext3() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Dummy schema for purposes of static query semantic analysis");
    stringList.add("Dummy data source for purposes of static query semantic analysis");
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext4() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    HashSet<String> knownColumnNames = new HashSet<>();
    knownColumnNames.add("Dummy data source for purposes of static query semantic analysis");

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Dummy data source for purposes of static query semantic analysis");
    stringList.add("DummyDataSource");
    stringList.add("DummyDataSource");

    ArrayList<String> stringList2 = new ArrayList<>();
    stringList2.add("42");
    stringList2.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList2);
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_given42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect).getUnquotedIdentifier("DummyDataSource");
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenArrayListAdd42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("DummyDataSource");

    ArrayList<String> stringList2 = new ArrayList<>();
    stringList2.add("42");
    stringList2.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList2);
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenArrayListAdd422() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("DummyDataSource");
    stringList.add("DummyDataSource");

    ArrayList<String> stringList2 = new ArrayList<>();
    stringList2.add("42");
    stringList2.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList2);
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenArrayListAdd423() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Dummy data source for purposes of static query semantic analysis");
    stringList.add("DummyDataSource");
    stringList.add("DummyDataSource");

    ArrayList<String> stringList2 = new ArrayList<>();
    stringList2.add("42");
    stringList2.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList2);
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenArrayListAddFoo() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    stringList.add("Dummy data source for purposes of static query semantic analysis");
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>When {@link SQLDialect}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenArrayListAddNull_whenSQLDialect() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add(null);

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@code DummyDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenDummyDataSource() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    HashSet<String> knownColumnNames = new HashSet<>();
    knownColumnNames.add("DummyDataSource");

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect).getUnquotedIdentifier("DummyDataSource");
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect).getUnquotedIdentifier("DummyDataSource");
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_givenNull2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Dummy data source for purposes of static query semantic analysis");
    stringList.add("DummyDataSource");

    HashSet<List<String>> knownTableNames = new HashSet<>();
    knownTableNames.add(stringList);

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, knownTableNames);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set,
   * Set)}.
   *
   * <ul>
   *   <li>When {@link SQLDialect}.
   *   <li>Then return Dummy.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#SQLQueryConnectionDummyContext(SQLDialect, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryConnectionDummyContext.<init>(SQLDialect, Set, Set)"})
  public void testNewSQLQueryConnectionDummyContext_whenSQLDialect_thenReturnDummy() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    // Act
    SQLQueryConnectionDummyContext actualSqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Assert
    assertTrue(actualSqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)"
  })
  public void testObtainRowsetPseudoColumns_whenNull_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act and Assert
    assertTrue(sqlQueryConnectionDummyContext.obtainRowsetPseudoColumns(null).isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#isDummy()}.
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#isDummy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryConnectionDummyContext.isDummy()"})
  public void testIsDummy() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act and Assert
    assertTrue(sqlQueryConnectionDummyContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_given42_whenArrayListAdd42_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add("42");
    tableName.add("foo");

    // Act
    List<DBSEntity> actualFindRealTablesResult =
        sqlQueryConnectionDummyContext.findRealTables(monitor, tableName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualFindRealTablesResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link ArrayList#ArrayList()} add empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_givenEmptyString_whenArrayListAddEmptyString_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add("");
    tableName.add("42");
    tableName.add("foo");

    // Act
    List<DBSEntity> actualFindRealTablesResult =
        sqlQueryConnectionDummyContext.findRealTables(monitor, tableName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualFindRealTablesResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_givenNull_whenArrayListAddNull_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add(null);

    // Act and Assert
    assertTrue(sqlQueryConnectionDummyContext.findRealTables(monitor, tableName).isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getUnquotedIdentifier(String)} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_givenSQLDialectGetUnquotedIdentifierReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add("foo");

    // Act
    List<DBSEntity> actualFindRealTablesResult =
        sqlQueryConnectionDummyContext.findRealTables(monitor, tableName);

    // Assert
    verify(dialect).getUnquotedIdentifier("foo");
    assertTrue(actualFindRealTablesResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_thenReturnSizeIsOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add("foo");

    // Act
    List<DBSEntity> actualFindRealTablesResult =
        sqlQueryConnectionDummyContext.findRealTables(monitor, tableName);

    // Assert
    verify(dialect).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealTablesResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_thenReturnSizeIsOne2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add("foo");
    tableName.add("foo");

    // Act
    List<DBSEntity> actualFindRealTablesResult =
        sqlQueryConnectionDummyContext.findRealTables(monitor, tableName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealTablesResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#findRealTables(DBRProgressMonitor,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealTables(DBRProgressMonitor, List)"
  })
  public void testFindRealTables_thenReturnSizeIsOne3() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> tableName = new ArrayList<>();
    tableName.add("foo");
    tableName.add("foo");
    tableName.add("foo");

    // Act
    List<DBSEntity> actualFindRealTablesResult =
        sqlQueryConnectionDummyContext.findRealTables(monitor, tableName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealTablesResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_given42_whenArrayListAdd42_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("42");
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsImplResult =
        sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualFindRealObjectsImplResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link ArrayList#ArrayList()} add empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_givenEmptyString_whenArrayListAddEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("");
    objectName.add("42");
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsImplResult =
        sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier(Mockito.<String>any());
    assertTrue(actualFindRealObjectsImplResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_givenNull_whenArrayListAddNull_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add(null);

    // Act and Assert
    assertTrue(sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName).isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getUnquotedIdentifier(String)} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_givenSQLDialectGetUnquotedIdentifierReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn(null);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsImplResult =
        sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName);

    // Assert
    verify(dialect).getUnquotedIdentifier("foo");
    assertTrue(actualFindRealObjectsImplResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_thenReturnSizeIsOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsImplResult =
        sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName);

    // Assert
    verify(dialect).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealObjectsImplResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_thenReturnSizeIsOne2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("foo");
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsImplResult =
        sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealObjectsImplResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionDummyContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionDummyContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_thenReturnSizeIsOne3() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("foo");
    objectName.add("foo");
    objectName.add("foo");

    // Act
    List<? extends DBSObject> actualFindRealObjectsImplResult =
        sqlQueryConnectionDummyContext.findRealObjectsImpl(monitor, objectName);

    // Assert
    verify(dialect, atLeast(1)).getUnquotedIdentifier("foo");
    assertEquals(1, actualFindRealObjectsImplResult.size());
  }

  /**
   * Test {@link SQLQueryConnectionDummyContext#resolveGlobalPseudoColumn(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionDummyContext#resolveGlobalPseudoColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryResultPseudoColumn SQLQueryConnectionDummyContext.resolveGlobalPseudoColumn(String)"
  })
  public void testResolveGlobalPseudoColumn_thenReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext sqlQueryConnectionDummyContext =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act and Assert
    assertNull(sqlQueryConnectionDummyContext.resolveGlobalPseudoColumn("Name"));
  }
}
