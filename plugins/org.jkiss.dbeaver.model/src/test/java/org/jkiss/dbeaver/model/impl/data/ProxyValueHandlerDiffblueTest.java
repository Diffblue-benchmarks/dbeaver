package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDValueHandler;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ProxyValueHandlerDiffblueTest {
  /**
   * Test {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}.
   *
   * <p>Method under test: {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProxyValueHandler.<init>(DBDValueHandler)"})
  public void testNewProxyValueHandler() {
    // Arrange and Act
    ProxyValueHandler actualProxyValueHandler = new ProxyValueHandler(DefaultValueHandler.INSTANCE);

    // Assert
    DBDValueHandler dbdValueHandler = actualProxyValueHandler.target;
    assertTrue(dbdValueHandler instanceof DefaultValueHandler);
    assertNull(actualProxyValueHandler.getComparator());
    assertNull(dbdValueHandler.getComparator());
  }

  /**
   * Test {@link ProxyValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)} with target is {@link
   *       ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class ProxyValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_givenProxyValueHandlerWithTargetIsProxyValueHandler() {
    // Arrange and Act
    Class<?> actualValueObjectType =
        new ProxyValueHandler(new ProxyValueHandler(DefaultValueHandler.INSTANCE))
            .getValueObjectType(SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    Class<Object> expectedValueObjectType = Object.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link ProxyValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class ProxyValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_thenReturnObject() {
    // Arrange and Act
    Class<?> actualValueObjectType =
        new ProxyValueHandler(DefaultValueHandler.INSTANCE)
            .getValueObjectType(SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    Class<Object> expectedValueObjectType = Object.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link ProxyValueHandler#getValueContentType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)} with target is {@link
   *       ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getValueContentType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ProxyValueHandler.getValueContentType(DBSTypedObject)"})
  public void testGetValueContentType_givenProxyValueHandlerWithTargetIsProxyValueHandler() {
    // Arrange, Act and Assert
    assertEquals(
        "text/plain",
        new ProxyValueHandler(new ProxyValueHandler(DefaultValueHandler.INSTANCE))
            .getValueContentType(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link ProxyValueHandler#getValueContentType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return {@code text/plain}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getValueContentType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ProxyValueHandler.getValueContentType(DBSTypedObject)"})
  public void testGetValueContentType_thenReturnTextPlain() {
    // Arrange, Act and Assert
    assertEquals(
        "text/plain",
        new ProxyValueHandler(DefaultValueHandler.INSTANCE)
            .getValueContentType(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link ProxyValueHandler#fetchValueObject(DBCSession, DBCResultSet, DBSTypedObject, int)}.
   *
   * <ul>
   *   <li>Then calls {@link DefaultValueHandler#fetchValueObject(DBCSession, DBCResultSet,
   *       DBSTypedObject, int)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#fetchValueObject(DBCSession, DBCResultSet,
   * DBSTypedObject, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object ProxyValueHandler.fetchValueObject(DBCSession, DBCResultSet, DBSTypedObject, int)"
  })
  public void testFetchValueObject_thenCallsFetchValueObject() throws DBCException {
    // Arrange
    DefaultValueHandler target = mock(DefaultValueHandler.class);
    when(target.fetchValueObject(
            Mockito.<DBCSession>any(),
            Mockito.<DBCResultSet>any(),
            Mockito.<DBSTypedObject>any(),
            anyInt()))
        .thenReturn(DBPEvent.RENAME);
    ProxyValueHandler proxyValueHandler = new ProxyValueHandler(target);
    DBCSession session = mock(DBCSession.class);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement);

    // Act
    proxyValueHandler.fetchValueObject(session, resultSet, SimpleTypedObject.DEFAULT_TYPE, 1);

    // Assert
    verify(target)
        .fetchValueObject(
            isA(DBCSession.class), isA(DBCResultSet.class), isA(DBSTypedObject.class), eq(1));
  }

  /**
   * Test {@link ProxyValueHandler#getValueFromObject(DBCSession, DBSTypedObject, Object, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)} with target is {@link
   *       ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getValueFromObject(DBCSession, DBSTypedObject,
   * Object, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object ProxyValueHandler.getValueFromObject(DBCSession, DBSTypedObject, Object, boolean, boolean)"
  })
  public void testGetValueFromObject_givenProxyValueHandlerWithTargetIsProxyValueHandler()
      throws DBCException {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    Object actualValueFromObject =
        new ProxyValueHandler(new ProxyValueHandler(DefaultValueHandler.INSTANCE))
            .getValueFromObject(
                mock(DBCSession.class), SimpleTypedObject.DEFAULT_TYPE, object, true, true);

    // Assert
    assertSame(object, actualValueFromObject);
  }

  /**
   * Test {@link ProxyValueHandler#getValueFromObject(DBCSession, DBSTypedObject, Object, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getValueFromObject(DBCSession, DBSTypedObject,
   * Object, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object ProxyValueHandler.getValueFromObject(DBCSession, DBSTypedObject, Object, boolean, boolean)"
  })
  public void testGetValueFromObject_thenReturnRename() throws DBCException {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    Object actualValueFromObject =
        new ProxyValueHandler(DefaultValueHandler.INSTANCE)
            .getValueFromObject(
                mock(DBCSession.class), SimpleTypedObject.DEFAULT_TYPE, object, true, true);

    // Assert
    assertSame(object, actualValueFromObject);
  }

  /**
   * Test {@link ProxyValueHandler#createNewValueObject(DBCSession, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)} with target is {@link
   *       ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#createNewValueObject(DBCSession,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ProxyValueHandler.createNewValueObject(DBCSession, DBSTypedObject)"})
  public void testCreateNewValueObject_givenProxyValueHandlerWithTargetIsProxyValueHandler()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(
        new ProxyValueHandler(new ProxyValueHandler(DefaultValueHandler.INSTANCE))
            .createNewValueObject(mock(DBCSession.class), SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link ProxyValueHandler#createNewValueObject(DBCSession, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#createNewValueObject(DBCSession,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ProxyValueHandler.createNewValueObject(DBCSession, DBSTypedObject)"})
  public void testCreateNewValueObject_thenReturnNull() throws DBCException {
    // Arrange, Act and Assert
    assertNull(
        new ProxyValueHandler(DefaultValueHandler.INSTANCE)
            .createNewValueObject(mock(DBCSession.class), SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link ProxyValueHandler#getSupportedOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link ProxyValueHandler#getSupportedOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCLogicalOperator[] ProxyValueHandler.getSupportedOperators(DBSTypedObject)"
  })
  public void testGetSupportedOperators() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.EQUALS, DBCLogicalOperator.NOT_EQUALS},
        new ProxyValueHandler(DefaultValueHandler.INSTANCE)
            .getSupportedOperators(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link ProxyValueHandler#getSupportedOperators(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link ProxyValueHandler#ProxyValueHandler(DBDValueHandler)} with target is {@link
   *       ProxyValueHandler#ProxyValueHandler(DBDValueHandler)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyValueHandler#getSupportedOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCLogicalOperator[] ProxyValueHandler.getSupportedOperators(DBSTypedObject)"
  })
  public void testGetSupportedOperators_givenProxyValueHandlerWithTargetIsProxyValueHandler() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.EQUALS, DBCLogicalOperator.NOT_EQUALS},
        new ProxyValueHandler(new ProxyValueHandler(DefaultValueHandler.INSTANCE))
            .getSupportedOperators(SimpleTypedObject.DEFAULT_TYPE));
  }
}
