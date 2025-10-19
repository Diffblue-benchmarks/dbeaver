package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultValueHandlerDiffblueTest {
  /**
   * Test {@link DefaultValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DefaultValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class DefaultValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType() {
    // Arrange and Act
    Class<Object> actualValueObjectType =
        DefaultValueHandler.INSTANCE.getValueObjectType(SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    Class<Object> expectedValueObjectType = Object.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link DefaultValueHandler#fetchValueObject(DBCSession, DBCResultSet, DBSTypedObject,
   * int)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>Then calls {@link LocalResultSet#getAttributeValue(int)}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultValueHandler#fetchValueObject(DBCSession, DBCResultSet,
   * DBSTypedObject, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DefaultValueHandler.fetchValueObject(DBCSession, DBCResultSet, DBSTypedObject, int)"
  })
  public void testFetchValueObject_givenRename_thenCallsGetAttributeValue() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);

    LocalResultSet<DBCStatement> resultSet = mock(LocalResultSet.class);
    when(resultSet.getAttributeValue(anyInt())).thenReturn(DBPEvent.RENAME);

    // Act
    DefaultValueHandler.INSTANCE.fetchValueObject(
        session, resultSet, SimpleTypedObject.DEFAULT_TYPE, 1);

    // Assert
    verify(resultSet).getAttributeValue(1);
  }

  /**
   * Test {@link DefaultValueHandler#bindValueObject(DBCSession, DBCStatement, DBSTypedObject, int,
   * Object)}.
   *
   * <p>Method under test: {@link DefaultValueHandler#bindValueObject(DBCSession, DBCStatement,
   * DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultValueHandler.bindValueObject(DBCSession, DBCStatement, DBSTypedObject, int, Object)"
  })
  public void testBindValueObject() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            DefaultValueHandler.INSTANCE.bindValueObject(
                session,
                new LocalStatement(mock(DBCSession.class), "Text"),
                SimpleTypedObject.DEFAULT_TYPE,
                1,
                DBPEvent.RENAME));
  }

  /**
   * Test {@link DefaultValueHandler#getValueFromObject(DBCSession, DBSTypedObject, Object, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link DefaultValueHandler#getValueFromObject(DBCSession, DBSTypedObject,
   * Object, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DefaultValueHandler.getValueFromObject(DBCSession, DBSTypedObject, Object, boolean, boolean)"
  })
  public void testGetValueFromObject() throws DBCException {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    Object actualValueFromObject =
        DefaultValueHandler.INSTANCE.getValueFromObject(
            mock(DBCSession.class), SimpleTypedObject.DEFAULT_TYPE, object, true, true);

    // Assert
    assertSame(object, actualValueFromObject);
  }

  /**
   * Test new {@link DefaultValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DefaultValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultValueHandler.<init>()"})
  public void testNewDefaultValueHandler() {
    // Arrange, Act and Assert
    assertNull(new DefaultValueHandler().getComparator());
  }
}
