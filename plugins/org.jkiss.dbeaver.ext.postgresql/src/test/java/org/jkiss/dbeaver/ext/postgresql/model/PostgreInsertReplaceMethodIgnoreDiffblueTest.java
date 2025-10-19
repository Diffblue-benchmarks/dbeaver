package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
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

public class PostgreInsertReplaceMethodIgnoreDiffblueTest {
  /**
   * Test {@link PostgreInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String PostgreInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause() {
    // Arrange
    PostgreInsertReplaceMethodIgnore postgreInsertReplaceMethodIgnore =
        new PostgreInsertReplaceMethodIgnore();
    DBSTable table = mock(DBSTable.class);

    // Act and Assert
    assertEquals(
        "INSERT INTO",
        postgreInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link PostgreInsertReplaceMethodIgnore#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Then return {@code ON CONFLICT DO NOTHING}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethodIgnore#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String PostgreInsertReplaceMethodIgnore.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_thenReturnOnConflictDoNothing() {
    // Arrange
    PostgreInsertReplaceMethodIgnore postgreInsertReplaceMethodIgnore =
        new PostgreInsertReplaceMethodIgnore();
    DBSTable table = mock(DBSTable.class);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertEquals(
        "ON CONFLICT DO NOTHING",
        postgreInsertReplaceMethodIgnore.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)}));
  }
}
