package org.jkiss.dbeaver.ext.mysql.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPAttributeReferencePurpose;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.rdb.DBSTable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MySQLInsertReplaceMethodUpdateDiffblueTest {
  /**
   * Test {@link MySQLInsertReplaceMethodUpdate#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link MySQLInsertReplaceMethodUpdate#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String MySQLInsertReplaceMethodUpdate.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause() {
    // Arrange
    MySQLInsertReplaceMethodUpdate mySQLInsertReplaceMethodUpdate =
        new MySQLInsertReplaceMethodUpdate();
    DBSTable table = mock(DBSTable.class);

    // Act and Assert
    assertEquals(
        "INSERT INTO",
        mySQLInsertReplaceMethodUpdate.getOpeningClause(table, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLInsertReplaceMethodUpdate#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return {@code ON DUPLICATE KEY UPDATE}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLInsertReplaceMethodUpdate#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String MySQLInsertReplaceMethodUpdate.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenTrue_thenReturnOnDuplicateKeyUpdate() {
    // Arrange
    MySQLInsertReplaceMethodUpdate mySQLInsertReplaceMethodUpdate =
        new MySQLInsertReplaceMethodUpdate();
    DBSTable table = mock(DBSTable.class);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.isPseudoAttribute()).thenReturn(true);

    // Act
    String actualTrailingClause =
        mySQLInsertReplaceMethodUpdate.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {dbdAttributeBindingCustom});

    // Assert
    verify(dbdAttributeBindingCustom).isPseudoAttribute();
    assertEquals(" ON DUPLICATE KEY UPDATE ", actualTrailingClause);
  }

  /**
   * Test {@link MySQLInsertReplaceMethodUpdate#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Then return {@code ON DUPLICATE KEY UPDATE Dr Jane Doe=VALUES(Dr Jane Doe)}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLInsertReplaceMethodUpdate#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String MySQLInsertReplaceMethodUpdate.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_thenReturnOnDuplicateKeyUpdateDrJaneDoeValuesDrJaneDoe() {
    // Arrange
    MySQLInsertReplaceMethodUpdate mySQLInsertReplaceMethodUpdate =
        new MySQLInsertReplaceMethodUpdate();

    DBSTable table = mock(DBSTable.class);
    when(table.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.isPseudoAttribute()).thenReturn(false);
    when(dbdAttributeBindingCustom.getFullyQualifiedName(
            Mockito.<DBPEvaluationContext>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Dr Jane Doe");

    // Act
    String actualTrailingClause =
        mySQLInsertReplaceMethodUpdate.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {dbdAttributeBindingCustom});

    // Assert
    verify(dbdAttributeBindingCustom)
        .getFullyQualifiedName(DBPEvaluationContext.DML, DBPAttributeReferencePurpose.UNSPECIFIED);
    verify(dbdAttributeBindingCustom, atLeast(1)).isPseudoAttribute();
    verify(table).getDataSource();
    assertEquals(" ON DUPLICATE KEY UPDATE Dr Jane Doe=VALUES(Dr Jane Doe)", actualTrailingClause);
  }
}
