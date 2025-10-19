package org.jkiss.dbeaver.ext.sqlite.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.rdb.DBSTable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLiteInsertReplaceMethodIgnoreDiffblueTest {
  /**
   * Test {@link SQLiteInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link SQLiteInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String SQLiteInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause() {
    // Arrange
    SQLiteInsertReplaceMethodIgnore sqLiteInsertReplaceMethodIgnore =
        new SQLiteInsertReplaceMethodIgnore();
    DBSTable table = mock(DBSTable.class);

    // Act and Assert
    assertEquals(
        "INSERT OR IGNORE INTO",
        sqLiteInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link SQLiteInsertReplaceMethodIgnore#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLiteInsertReplaceMethodIgnore#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String SQLiteInsertReplaceMethodIgnore.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_thenReturnNull() {
    // Arrange
    SQLiteInsertReplaceMethodIgnore sqLiteInsertReplaceMethodIgnore =
        new SQLiteInsertReplaceMethodIgnore();
    DBSTable table = mock(DBSTable.class);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        sqLiteInsertReplaceMethodIgnore.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)}));
  }
}
