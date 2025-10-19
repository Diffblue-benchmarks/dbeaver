package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.semantics.SQLQuerySymbol;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryResultColumnDiffblueTest {
  /**
   * Test {@link SQLQueryResultColumn#withNewIndex(int)}.
   *
   * <ul>
   *   <li>Then {@link SQLQueryResultColumn#realSource} return {@link DBVEntity}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResultColumn#withNewIndex(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryResultColumn SQLQueryResultColumn.withNewIndex(int)"})
  public void testWithNewIndex_thenRealSourceReturnDBVEntity() {
    // Arrange
    SQLQuerySymbol symbol = new SQLQuerySymbol("Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity realSource = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute realAttr =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    SQLQueryResultColumn sqlQueryResultColumn =
        new SQLQueryResultColumn(1, symbol, null, realSource, realAttr, SQLQueryExprType.BOOLEAN);

    // Act
    SQLQueryResultColumn actualWithNewIndexResult = sqlQueryResultColumn.withNewIndex(1);

    // Assert
    assertTrue(actualWithNewIndexResult.realSource instanceof DBVEntity);
    assertTrue(actualWithNewIndexResult.realAttr instanceof DBVEntityAttribute);
    assertNull(actualWithNewIndexResult.source);
    assertEquals(1, actualWithNewIndexResult.index);
  }
}
