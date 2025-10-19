package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGeneratorInsertDiffblueTest {
  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity() throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    sqlGeneratorInsert.generateSQL(monitor, sql, object);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertEquals("fooINSERT INTO 42A42\n()\nVALUES();\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity2() throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    sqlGeneratorInsert.generateSQL(monitor, sql, object);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertEquals("fooINSERT INTO 42A42\n()\nVALUES();\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity3() throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "\n");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    sqlGeneratorInsert.generateSQL(monitor, sql, object);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertEquals("fooINSERT INTO 42\n()\nVALUES();\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity4() throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    sqlGeneratorInsert.generateSQL(monitor, sql, object);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertEquals("fooINSERT INTO 42\n()\nVALUES();\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity5() throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    sqlGeneratorInsert.generateSQL(monitor, sql, object);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals("fooINSERT INTO 42\n()\nVALUES();\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity6() throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity object = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    object.addVirtualAttribute(attribute);

    // Act
    sqlGeneratorInsert.generateSQL(monitor, sql, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(parent, atLeast(1)).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertEquals("fooINSERT INTO 42A42\n()\nVALUES();\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)} with
   * {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSEntity}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorInsert#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.generateSQL(DBRProgressMonitor, StringBuilder, DBSEntity)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSEntity_thenThrowDBException()
      throws DBException {
    // Arrange
    SQLGeneratorInsert sqlGeneratorInsert = new SQLGeneratorInsert();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "INSERT INTO ");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(DBException.class, () -> sqlGeneratorInsert.generateSQL(monitor, sql, object));
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier(Mockito.<String>any(), eq(true), eq(false));
    verify(sqlDialect).getStructSeparator();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLGeneratorInsert}
   *   <li>{@link SQLGeneratorInsert#isInsertOption()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorInsert.<init>()",
    "boolean SQLGeneratorInsert.isInsertOption()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLGeneratorInsert actualSqlGeneratorInsert = new SQLGeneratorInsert();
    boolean actualIsInsertOptionResult = actualSqlGeneratorInsert.isInsertOption();

    // Assert
    assertNull(actualSqlGeneratorInsert.getResult());
    assertNull(actualSqlGeneratorInsert.getObjects());
    assertFalse(actualSqlGeneratorInsert.isCompactSQL());
    assertFalse(actualSqlGeneratorInsert.isExcludeAutoGeneratedColumn());
    assertFalse(actualSqlGeneratorInsert.isIncludePermissions());
    assertFalse(actualSqlGeneratorInsert.isShowCastParams());
    assertFalse(actualSqlGeneratorInsert.isShowFullDdl());
    assertFalse(actualSqlGeneratorInsert.isShowPartitionsDDL());
    assertFalse(actualSqlGeneratorInsert.isUseCustomDataFormat());
    assertTrue(actualSqlGeneratorInsert.isFullyQualifiedNames());
    assertTrue(actualSqlGeneratorInsert.isShowComments());
    assertTrue(actualSqlGeneratorInsert.isUseSeparateForeignKeys());
    assertTrue(actualIsInsertOptionResult);
  }
}
