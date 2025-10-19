package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.semantics.SQLQueryComplexName;
import org.jkiss.dbeaver.model.sql.semantics.SQLQuerySymbol;
import org.jkiss.dbeaver.model.sql.semantics.model.select.SQLQueryRowsSourceModel;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SourceResolutionResultDiffblueTest {
  /**
   * Test {@link SourceResolutionResult#SourceResolutionResult(SQLQueryRowsSourceModel,
   * SQLQueryComplexName, DBSEntity, SQLQuerySymbol)}.
   *
   * <ul>
   *   <li>Then {@link SourceResolutionResult#tableOrNull} return {@link DBVEntity}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SourceResolutionResult#SourceResolutionResult(SQLQueryRowsSourceModel, SQLQueryComplexName,
   * DBSEntity, SQLQuerySymbol)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SourceResolutionResult.<init>(SQLQueryRowsSourceModel, SQLQueryComplexName, DBSEntity, SQLQuerySymbol)"
  })
  public void testNewSourceResolutionResult_thenTableOrNullReturnDBVEntity() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity tableOrNull = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    SourceResolutionResult actualSourceResolutionResult =
        new SourceResolutionResult(null, null, tableOrNull, new SQLQuerySymbol("Name"));

    // Assert
    assertTrue(actualSourceResolutionResult.tableOrNull instanceof DBVEntity);
    assertNull(actualSourceResolutionResult.referenceName);
    assertNull(actualSourceResolutionResult.source);
    assertFalse(actualSourceResolutionResult.isCteSubquery);
  }
}
