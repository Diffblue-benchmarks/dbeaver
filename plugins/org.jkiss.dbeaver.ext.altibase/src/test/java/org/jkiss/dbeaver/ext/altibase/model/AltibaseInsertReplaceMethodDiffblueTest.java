package org.jkiss.dbeaver.ext.altibase.model;

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

public class AltibaseInsertReplaceMethodDiffblueTest {
  /**
   * Test {@link AltibaseInsertReplaceMethod#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link AltibaseInsertReplaceMethod#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String AltibaseInsertReplaceMethod.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause() {
    // Arrange
    AltibaseInsertReplaceMethod altibaseInsertReplaceMethod = new AltibaseInsertReplaceMethod();
    DBSTable table = mock(DBSTable.class);

    // Act and Assert
    assertEquals(
        "UPDATE OR INSERT INTO",
        altibaseInsertReplaceMethod.getOpeningClause(table, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link AltibaseInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String AltibaseInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_thenReturnNull() {
    // Arrange
    AltibaseInsertReplaceMethod altibaseInsertReplaceMethod = new AltibaseInsertReplaceMethod();
    DBSTable table = mock(DBSTable.class);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        altibaseInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)}));
  }
}
