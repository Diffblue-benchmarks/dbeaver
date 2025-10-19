package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.data.DefaultValueHandler;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RowDataReceiverDiffblueTest {
  /**
   * Test {@link RowDataReceiver#fetchRow(DBCSession, DBCResultSet)}.
   *
   * <p>Method under test: {@link RowDataReceiver#fetchRow(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowDataReceiver.fetchRow(DBCSession, DBCResultSet)"})
  public void testFetchRow() throws DBCException {
    // Arrange
    DBDAttributeBinding[] curAttributes = new DBDAttributeBinding[] {null};
    RowDataReceiver rowDataReceiver = new RowDataReceiver(curAttributes);
    DBCSession session = mock(DBCSession.class);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement);

    // Act
    rowDataReceiver.fetchRow(session, resultSet);

    // Assert that nothing has changed
    assertNull(rowDataReceiver.getRowValues());
  }

  /**
   * Test {@link RowDataReceiver#fetchRow(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Then array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link RowDataReceiver#fetchRow(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowDataReceiver.fetchRow(DBCSession, DBCResultSet)"})
  public void testFetchRow_thenArrayLengthIsZero() throws DBCException {
    // Arrange
    RowDataReceiver rowDataReceiver = new RowDataReceiver(new DBDAttributeBinding[] {});
    DBCSession session = mock(DBCSession.class);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement);

    // Act
    rowDataReceiver.fetchRow(session, resultSet);

    // Assert
    assertEquals(0, rowDataReceiver.getRowValues().length);
  }

  /**
   * Test {@link RowDataReceiver#fetchRowValues(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link RowDataReceiver#fetchRowValues(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowDataReceiver.fetchRowValues(DBCSession, DBCResultSet)"})
  public void testFetchRowValues_givenRename_thenArrayLengthIsOne() throws DBCException {
    // Arrange
    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);
    DBDAttributeBinding[] curAttributes = new DBDAttributeBinding[] {dbdAttributeBindingCustom};
    RowDataReceiver rowDataReceiver = new RowDataReceiver(curAttributes);
    DBCSession session = mock(DBCSession.class);

    LocalResultSet<DBCStatement> resultSet = mock(LocalResultSet.class);
    when(resultSet.getAttributeValue(anyInt())).thenReturn(DBPEvent.RENAME);

    // Act
    rowDataReceiver.fetchRowValues(session, resultSet);

    // Assert
    verify(dbdAttributeBindingCustom).getValueHandler();
    verify(resultSet).getAttributeValue(0);
    assertEquals(1, rowDataReceiver.getRowValues().length);
  }

  /**
   * Test {@link RowDataReceiver#fetchRowValues(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Then array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link RowDataReceiver#fetchRowValues(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowDataReceiver.fetchRowValues(DBCSession, DBCResultSet)"})
  public void testFetchRowValues_thenArrayLengthIsZero() throws DBCException {
    // Arrange
    RowDataReceiver rowDataReceiver = new RowDataReceiver(new DBDAttributeBinding[] {});
    DBCSession session = mock(DBCSession.class);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement);

    // Act
    rowDataReceiver.fetchRowValues(session, resultSet);

    // Assert
    assertEquals(0, rowDataReceiver.getRowValues().length);
  }
}
