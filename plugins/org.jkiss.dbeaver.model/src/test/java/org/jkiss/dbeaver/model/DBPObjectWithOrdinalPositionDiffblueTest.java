package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPObjectWithOrdinalPositionDiffblueTest {
  /**
   * Test {@link DBPObjectWithOrdinalPosition#compareTo(DBPObjectWithOrdinalPosition)} with {@code
   * DBPObjectWithOrdinalPosition}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPObjectWithOrdinalPosition#compareTo(DBPObjectWithOrdinalPosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBPObjectWithOrdinalPosition.compareTo(DBPObjectWithOrdinalPosition)"})
  public void testCompareToWithDBPObjectWithOrdinalPosition_thenReturnZero() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    AttributeMetaDataProxy attributeMetaDataProxy =
        new AttributeMetaDataProxy(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN));

    DBPObjectWithOrdinalPosition o = mock(DBPObjectWithOrdinalPosition.class);
    when(o.getOrdinalPosition()).thenReturn(1);

    // Act
    int actualCompareToResult = attributeMetaDataProxy.compareTo(o);

    // Assert
    verify(o).getOrdinalPosition();
    assertEquals(0, actualCompareToResult);
  }
}
