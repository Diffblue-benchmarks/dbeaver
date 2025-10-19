package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedure;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedureParameter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGeneratorProcedureCallDiffblueTest {
  /**
   * Test {@link SQLGeneratorProcedureCall#supportCastParams()}.
   *
   * <p>Method under test: {@link SQLGeneratorProcedureCall#supportCastParams()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGeneratorProcedureCall.supportCastParams()"})
  public void testSupportCastParams() {
    // Arrange, Act and Assert
    assertTrue(new SQLGeneratorProcedureCall().supportCastParams());
  }

  /**
   * Test {@link SQLGeneratorProcedureCall#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSProcedure)} with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSProcedure}.
   *
   * <p>Method under test: {@link SQLGeneratorProcedureCall#generateSQL(DBRProgressMonitor,
   * StringBuilder, DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorProcedureCall.generateSQL(DBRProgressMonitor, StringBuilder, DBSProcedure)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSProcedure() throws DBException {
    // Arrange
    SQLGeneratorProcedureCall sqlGeneratorProcedureCall = new SQLGeneratorProcedureCall();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    DBSProcedure proc = mock(DBSProcedure.class);
    Mockito.<Collection<? extends DBSProcedureParameter>>when(
            proc.getParameters(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    // Act and Assert
    assertThrows(
        DBException.class, () -> sqlGeneratorProcedureCall.generateSQL(monitor, sql, proc));
    verify(proc).getParameters(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLGeneratorProcedureCall#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSProcedure)} with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSProcedure}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorProcedureCall#generateSQL(DBRProgressMonitor,
   * StringBuilder, DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorProcedureCall.generateSQL(DBRProgressMonitor, StringBuilder, DBSProcedure)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSProcedure_givenArrayList()
      throws DBException {
    // Arrange
    SQLGeneratorProcedureCall sqlGeneratorProcedureCall = new SQLGeneratorProcedureCall();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    doNothing()
        .when(sqlDialect)
        .generateStoredProcedureCall(
            Mockito.<StringBuilder>any(),
            Mockito.<DBSProcedure>any(),
            Mockito.<Collection<DBSProcedureParameter>>any(),
            anyBoolean());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSProcedure proc = mock(DBSProcedure.class);
    Mockito.<Collection<? extends DBSProcedureParameter>>when(
            proc.getParameters(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());
    when(proc.getDataSource()).thenReturn(dbpDataSource);

    // Act
    sqlGeneratorProcedureCall.generateSQL(monitor, sql, proc);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect)
        .generateStoredProcedureCall(
            isA(StringBuilder.class), isA(DBSProcedure.class), isA(Collection.class), eq(false));
    verify(proc).getDataSource();
    verify(proc).getParameters(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLGeneratorProcedureCall#generateSQL(DBRProgressMonitor, StringBuilder,
   * DBSProcedure)} with {@code DBRProgressMonitor}, {@code StringBuilder}, {@code DBSProcedure}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorProcedureCall#generateSQL(DBRProgressMonitor,
   * StringBuilder, DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLGeneratorProcedureCall.generateSQL(DBRProgressMonitor, StringBuilder, DBSProcedure)"
  })
  public void testGenerateSQLWithDBRProgressMonitorStringBuilderDBSProcedure_givenNull()
      throws DBException {
    // Arrange
    SQLGeneratorProcedureCall sqlGeneratorProcedureCall = new SQLGeneratorProcedureCall();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringBuilder sql = new StringBuilder("foo");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    doNothing()
        .when(sqlDialect)
        .generateStoredProcedureCall(
            Mockito.<StringBuilder>any(),
            Mockito.<DBSProcedure>any(),
            Mockito.<Collection<DBSProcedureParameter>>any(),
            anyBoolean());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSProcedure proc = mock(DBSProcedure.class);
    Mockito.<Collection<? extends DBSProcedureParameter>>when(
            proc.getParameters(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(null);
    when(proc.getDataSource()).thenReturn(dbpDataSource);

    // Act
    sqlGeneratorProcedureCall.generateSQL(monitor, sql, proc);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect)
        .generateStoredProcedureCall(
            isA(StringBuilder.class), isA(DBSProcedure.class), isA(Collection.class), eq(false));
    verify(proc).getDataSource();
    verify(proc).getParameters(isA(DBRProgressMonitor.class));
  }

  /**
   * Test new {@link SQLGeneratorProcedureCall} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLGeneratorProcedureCall}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorProcedureCall.<init>()"})
  public void testNewSQLGeneratorProcedureCall() {
    // Arrange and Act
    SQLGeneratorProcedureCall actualSqlGeneratorProcedureCall = new SQLGeneratorProcedureCall();

    // Assert
    assertNull(actualSqlGeneratorProcedureCall.getResult());
    assertNull(actualSqlGeneratorProcedureCall.getObjects());
    assertFalse(actualSqlGeneratorProcedureCall.isCompactSQL());
    assertFalse(actualSqlGeneratorProcedureCall.isExcludeAutoGeneratedColumn());
    assertFalse(actualSqlGeneratorProcedureCall.isIncludePermissions());
    assertFalse(actualSqlGeneratorProcedureCall.isShowCastParams());
    assertFalse(actualSqlGeneratorProcedureCall.isShowFullDdl());
    assertFalse(actualSqlGeneratorProcedureCall.isShowPartitionsDDL());
    assertFalse(actualSqlGeneratorProcedureCall.isUseCustomDataFormat());
    assertTrue(actualSqlGeneratorProcedureCall.isFullyQualifiedNames());
    assertTrue(actualSqlGeneratorProcedureCall.isShowComments());
    assertTrue(actualSqlGeneratorProcedureCall.isUseSeparateForeignKeys());
  }
}
