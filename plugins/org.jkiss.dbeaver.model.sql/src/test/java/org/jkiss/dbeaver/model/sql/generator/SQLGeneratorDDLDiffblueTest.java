package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPScriptObject;
import org.jkiss.dbeaver.model.DBPScriptObjectExt;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGeneratorDDLDiffblueTest {
  /**
   * Test {@link SQLGeneratorDDL#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link DefaultProgressMonitor} {@link DefaultProgressMonitor#isCanceled()} return
   *       {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorDDL#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorDDL.run(DBRProgressMonitor)"})
  public void testRun_givenFalse_whenDefaultProgressMonitorIsCanceledReturnFalse()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ArrayList<DBPScriptObject> objects = new ArrayList<>();
    objects.addAll(new ArrayList<>());

    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.initGenerator(objects);

    DefaultProgressMonitor monitor = mock(DefaultProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();

    // Act
    sqlGeneratorDDL.run(monitor);

    // Assert
    verify(monitor).beginTask("Sorting table list", 0);
    verify(monitor, atLeast(1)).done();
    verify(monitor).isCanceled();
    assertEquals("", sqlGeneratorDDL.getResult());
  }

  /**
   * Test {@link SQLGeneratorDDL#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor) UseSeparateForeignKeys is {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorDDL#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorDDL.run(DBRProgressMonitor)"})
  public void testRun_givenSQLGeneratorDDLUseSeparateForeignKeysIsFalse()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ArrayList<DBPScriptObject> objects = new ArrayList<>();
    objects.addAll(new ArrayList<>());

    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setUseSeparateForeignKeys(false);
    sqlGeneratorDDL.initGenerator(objects);

    DefaultProgressMonitor monitor = mock(DefaultProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();

    // Act
    sqlGeneratorDDL.run(monitor);

    // Assert
    verify(monitor).beginTask("Sorting table list", 0);
    verify(monitor, atLeast(1)).done();
    verify(monitor).isCanceled();
    assertEquals("", sqlGeneratorDDL.getResult());
  }

  /**
   * Test {@link SQLGeneratorDDL#run(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link DefaultProgressMonitor} {@link DefaultProgressMonitor#isCanceled()} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorDDL#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorDDL.run(DBRProgressMonitor)"})
  public void testRun_givenTrue_whenDefaultProgressMonitorIsCanceledReturnTrue()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    ArrayList<DBPScriptObject> objects = new ArrayList<>();
    objects.addAll(new ArrayList<>());

    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.initGenerator(objects);

    DefaultProgressMonitor monitor = mock(DefaultProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();

    // Act
    sqlGeneratorDDL.run(monitor);

    // Assert
    verify(monitor).beginTask("Sorting table list", 0);
    verify(monitor, atLeast(1)).done();
    verify(monitor).isCanceled();
    assertEquals("", sqlGeneratorDDL.getResult());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObject object = mock(DBPScriptObject.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn(null);

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    assertEquals("foo\n;\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject2()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObject object = mock(DBPScriptObject.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn("Object Definition Text");

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    assertEquals("foo\nObject Definition Text;\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject3()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObject object = mock(DBPScriptObject.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenThrow(new DBException("An error occurred"));

    // Act and Assert
    assertThrows(DBException.class, () -> sqlGeneratorDDL.generateSQL(monitor, sql, object));
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject4()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("");

    DBPScriptObject object = mock(DBPScriptObject.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn(null);

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    assertEquals(";\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject5()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObjectExt object = mock(DBPScriptObjectExt.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn(null);
    when(object.getExtendedDefinitionText(Mockito.<DBRProgressMonitor>any()))
        .thenReturn("not empty");

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    verify(object).getExtendedDefinitionText(isA(DBRProgressMonitor.class));
    assertEquals("foo\n;\n\nnot empty;\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject6()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObjectExt object = mock(DBPScriptObjectExt.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn(null);
    when(object.getExtendedDefinitionText(Mockito.<DBRProgressMonitor>any())).thenReturn("");

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    verify(object).getExtendedDefinitionText(isA(DBRProgressMonitor.class));
    assertEquals("foo\n;\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject7()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObjectExt object = mock(DBPScriptObjectExt.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn(null);
    when(object.getExtendedDefinitionText(Mockito.<DBRProgressMonitor>any())).thenReturn(null);

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    verify(object).getExtendedDefinitionText(isA(DBRProgressMonitor.class));
    assertEquals("foo\n;\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)}
   * with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBPScriptObject}.
   *
   * <ul>
   *   <li>Given {@code ;}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorDDL#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBPScriptObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorDDL.generateSQL(DBRProgressMonitor, StringBuilder, DBPScriptObject)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBPScriptObject_givenSemicolon()
      throws DBException {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBPScriptObject object = mock(DBPScriptObject.class);
    when(object.getObjectDefinitionText(
            Mockito.<DBRProgressMonitor>any(), Mockito.<Map<String, Object>>any()))
        .thenReturn(";");

    // Act
    sqlGeneratorDDL.generateSQL(monitor, sql, object);

    // Assert
    verify(object).getObjectDefinitionText(isA(DBRProgressMonitor.class), isA(Map.class));
    assertEquals("foo\n;\n", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorDDL#addOptions(Map)}.
   *
   * <p>Method under test: {@link SQLGeneratorDDL#addOptions(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorDDL.addOptions(Map)"})
  public void testAddOptions() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    HashMap<String, Object> options = new HashMap<>();

    // Act
    sqlGeneratorDDL.addOptions(options);

    // Assert
    assertEquals(13, options.size());
    assertFalse((Boolean) options.get("ddl.includeNestedObjects"));
    assertFalse((Boolean) options.get("ddl.includePermissions"));
    assertFalse((Boolean) options.get("script.exclude.autogenerated.column"));
    assertFalse((Boolean) options.get("script.use.custom.data.format"));
    assertFalse((Boolean) options.get("sql.castParameter"));
    assertTrue((Boolean) options.get("ddl.includeComments"));
    assertTrue((Boolean) options.get("ddl.separateForeignKeys"));
    assertTrue((Boolean) options.get("ddl.source"));
    assertTrue((Boolean) options.get("refresh"));
    assertTrue((Boolean) options.get("script.includeDrop"));
    assertTrue((Boolean) options.get("useFQN"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLGeneratorDDL}
   *   <li>{@link SQLGeneratorDDL#isDDLOption()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorDDL.<init>()", "boolean SQLGeneratorDDL.isDDLOption()"})
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLGeneratorDDL actualSqlGeneratorDDL = new SQLGeneratorDDL();
    boolean actualIsDDLOptionResult = actualSqlGeneratorDDL.isDDLOption();

    // Assert
    assertNull(actualSqlGeneratorDDL.getResult());
    assertNull(actualSqlGeneratorDDL.getObjects());
    assertFalse(actualSqlGeneratorDDL.isCompactSQL());
    assertFalse(actualSqlGeneratorDDL.isExcludeAutoGeneratedColumn());
    assertFalse(actualSqlGeneratorDDL.isIncludePermissions());
    assertFalse(actualSqlGeneratorDDL.isShowCastParams());
    assertFalse(actualSqlGeneratorDDL.isShowFullDdl());
    assertFalse(actualSqlGeneratorDDL.isShowPartitionsDDL());
    assertFalse(actualSqlGeneratorDDL.isUseCustomDataFormat());
    assertTrue(actualSqlGeneratorDDL.isFullyQualifiedNames());
    assertTrue(actualSqlGeneratorDDL.isShowComments());
    assertTrue(actualSqlGeneratorDDL.isUseSeparateForeignKeys());
    assertTrue(actualIsDDLOptionResult);
  }
}
