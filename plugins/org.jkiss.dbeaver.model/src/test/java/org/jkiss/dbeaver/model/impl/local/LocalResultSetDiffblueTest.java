package org.jkiss.dbeaver.model.impl.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCAttributeMetaData;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCResultSetMetaData;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalResultSetDiffblueTest {
  /**
   * Test {@link LocalResultSet#LocalResultSet(DBCSession, DBCStatement)}.
   *
   * <p>Method under test: {@link LocalResultSet#LocalResultSet(DBCSession, DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalResultSet.<init>(DBCSession, DBCStatement)"})
  public void testNewLocalResultSet() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    LocalResultSet<DBCStatement> actualLocalResultSet =
        new LocalResultSet<>(session, localStatement);

    // Assert
    assertTrue(actualLocalResultSet.rows.isEmpty());
    assertSame(localStatement, actualLocalResultSet.getSourceStatement());
    assertSame(session, actualLocalResultSet.getSession());
  }

  /**
   * Test {@link LocalResultSet#getAttributeValue(String)} with {@code name}.
   *
   * <p>Method under test: {@link LocalResultSet#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LocalResultSet.getAttributeValue(String)"})
  public void testGetAttributeValueWithName() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertThrows(DBCException.class, () -> localResultSet.getAttributeValue("Name"));
  }

  /**
   * Test {@link LocalResultSet#getAttributeValue(String)} with {@code name}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LocalResultSet.getAttributeValue(String)"})
  public void testGetAttributeValueWithName_thenThrowDBCException() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    localResultSet.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act and Assert
    assertThrows(DBCException.class, () -> localResultSet.getAttributeValue("Name"));
  }

  /**
   * Test {@link LocalResultSet#nextRow()}.
   *
   * <p>Method under test: {@link LocalResultSet#nextRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalResultSet.nextRow()"})
  public void testNextRow() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    boolean actualNextRowResult = localResultSet.nextRow();

    // Assert
    assertEquals(-1, localResultSet.curPosition);
    assertFalse(actualNextRowResult);
  }

  /**
   * Test {@link LocalResultSet#nextRow()}.
   *
   * <p>Method under test: {@link LocalResultSet#nextRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalResultSet.nextRow()"})
  public void testNextRow2() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    localResultSet.addRow(DBPEvent.RENAME);

    // Act
    boolean actualNextRowResult = localResultSet.nextRow();

    // Assert
    assertEquals(0, localResultSet.curPosition);
    assertTrue(actualNextRowResult);
  }

  /**
   * Test {@link LocalResultSet#moveTo(int)}.
   *
   * <p>Method under test: {@link LocalResultSet#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalResultSet.moveTo(int)"})
  public void testMoveTo() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    boolean actualMoveToResult = localResultSet.moveTo(1);

    // Assert
    assertEquals(-1, localResultSet.curPosition);
    assertFalse(actualMoveToResult);
  }

  /**
   * Test {@link LocalResultSet#moveTo(int)}.
   *
   * <p>Method under test: {@link LocalResultSet#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalResultSet.moveTo(int)"})
  public void testMoveTo2() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    localResultSet.addRow(DBPEvent.RENAME);
    localResultSet.addRow(DBPEvent.RENAME);

    // Act
    boolean actualMoveToResult = localResultSet.moveTo(1);

    // Assert
    assertEquals(1, localResultSet.curPosition);
    assertTrue(actualMoveToResult);
  }

  /**
   * Test {@link LocalResultSet#moveTo(int)}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalResultSet.moveTo(int)"})
  public void testMoveTo_whenMinusOne() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    boolean actualMoveToResult = localResultSet.moveTo(-1);

    // Assert
    assertEquals(-1, localResultSet.curPosition);
    assertFalse(actualMoveToResult);
  }

  /**
   * Test {@link LocalResultSet#getMeta()}.
   *
   * <p>Method under test: {@link LocalResultSet#getMeta()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCResultSetMetaData LocalResultSet.getMeta()"})
  public void testGetMeta() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCResultSetMetaData actualMeta = localResultSet.getMeta();
    List<? extends DBCAttributeMetaData> actualAttributes = actualMeta.getAttributes();

    // Assert
    assertTrue(actualMeta instanceof LocalResultSetMeta);
    List<? extends DBCAttributeMetaData> attributes = actualMeta.getAttributes();
    assertTrue(attributes.isEmpty());
    assertSame(attributes, actualAttributes);
  }

  /**
   * Test {@link LocalResultSet#getFeature(String)}.
   *
   * <ul>
   *   <li>When {@code local}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#getFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LocalResultSet.getFeature(String)"})
  public void testGetFeature_whenLocal_thenReturnTrue() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertTrue((Boolean) localResultSet.getFeature("local"));
  }

  /**
   * Test {@link LocalResultSet#getFeature(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#getFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LocalResultSet.getFeature(String)"})
  public void testGetFeature_whenName_thenReturnNull() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertNull(localResultSet.getFeature("Name"));
  }

  /**
   * Test {@link LocalResultSet#getColumnCount()}.
   *
   * <p>Method under test: {@link LocalResultSet#getColumnCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LocalResultSet.getColumnCount()"})
  public void testGetColumnCount() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertEquals(0, localResultSet.getColumnCount());
  }

  /**
   * Test {@link LocalResultSet#addColumn(LocalResultSetColumn)} with {@code column}.
   *
   * <p>Method under test: {@link LocalResultSet#addColumn(LocalResultSetColumn)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalResultSet.addColumn(LocalResultSetColumn)"})
  public void testAddColumnWithColumn() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement2 = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement2);
    LocalResultSetColumn column =
        new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN);

    // Act
    localResultSet.addColumn(column);

    // Assert
    DBCResultSetMetaData meta = localResultSet.getMeta();
    assertTrue(meta instanceof LocalResultSetMeta);
    List<? extends DBCAttributeMetaData> attributes = meta.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals(1, localResultSet.getColumnCount());
    assertSame(column, attributes.get(0));
  }

  /**
   * Test {@link LocalResultSet#addColumn(String, DBPDataKind)} with {@code label}, {@code
   * dataKind}.
   *
   * <p>Method under test: {@link LocalResultSet#addColumn(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCAttributeMetaData LocalResultSet.addColumn(String, DBPDataKind)"})
  public void testAddColumnWithLabelDataKind() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCAttributeMetaData actualAddColumnResult =
        localResultSet.addColumn("Label", DBPDataKind.BOOLEAN);

    // Assert
    assertTrue(((LocalResultSetColumn) actualAddColumnResult).resultSet instanceof LocalResultSet);
    assertTrue(actualAddColumnResult instanceof LocalResultSetColumn);
    assertEquals("?", actualAddColumnResult.getTypeName());
    assertEquals("Label", actualAddColumnResult.getName());
    assertEquals("Label", actualAddColumnResult.getLabel());
    assertNull(actualAddColumnResult.getPrecision());
    assertNull(actualAddColumnResult.getScale());
    assertNull(actualAddColumnResult.getSource());
    assertNull(actualAddColumnResult.getEntityName());
    assertNull(actualAddColumnResult.getEntityMetaData());
    assertEquals(0, actualAddColumnResult.getOrdinalPosition());
    assertEquals(0, actualAddColumnResult.getTypeID());
    assertEquals(0L, actualAddColumnResult.getMaxLength());
    assertEquals(0L, actualAddColumnResult.getTypeModifiers());
    assertEquals(1, localResultSet.getColumnCount());
    assertEquals(DBPDataKind.BOOLEAN, actualAddColumnResult.getDataKind());
    assertFalse(actualAddColumnResult.isAutoGenerated());
    assertFalse(actualAddColumnResult.isRequired());
    assertTrue(actualAddColumnResult.isReadOnly());
  }

  /**
   * Test {@link LocalResultSet#addColumn(String, DBSTypedObject)} with {@code label}, {@code
   * typedObject}.
   *
   * <ul>
   *   <li>Then {@link LocalResultSetColumn#resultSet} return {@link LocalResultSet}.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#addColumn(String, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCAttributeMetaData LocalResultSet.addColumn(String, DBSTypedObject)"})
  public void testAddColumnWithLabelTypedObject_thenResultSetReturnLocalResultSet()
      throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCAttributeMetaData actualAddColumnResult =
        localResultSet.addColumn("Label", SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    DBCResultSet dbcResultSet = ((LocalResultSetColumn) actualAddColumnResult).resultSet;
    assertTrue(dbcResultSet instanceof LocalResultSet);
    assertTrue(actualAddColumnResult instanceof LocalResultSetColumn);
    DBCResultSetMetaData meta = dbcResultSet.getMeta();
    assertTrue(meta instanceof LocalResultSetMeta);
    assertEquals("Object", actualAddColumnResult.getFullTypeName());
    assertEquals("Object", actualAddColumnResult.getTypeName());
    List<? extends DBCAttributeMetaData> attributes = meta.getAttributes();
    assertEquals(1, attributes.size());
    assertEquals(DBPDataKind.OBJECT, actualAddColumnResult.getDataKind());
    assertSame(actualAddColumnResult, attributes.get(0));
  }

  /**
   * Test {@link LocalResultSet#addColumn(String, DBSTypedObject)} with {@code label}, {@code
   * typedObject}.
   *
   * <ul>
   *   <li>Then return TypeName is {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#addColumn(String, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCAttributeMetaData LocalResultSet.addColumn(String, DBSTypedObject)"})
  public void testAddColumnWithLabelTypedObject_thenReturnTypeNameIsQuestionMark() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    DBCSession session2 = mock(DBCSession.class);
    LocalStatement localStatement2 = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session2, localStatement2);

    // Act
    DBCAttributeMetaData actualAddColumnResult =
        localResultSet.addColumn(
            "Label", new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN));

    // Assert
    assertTrue(actualAddColumnResult instanceof LocalResultSetColumn);
    assertEquals("?", actualAddColumnResult.getTypeName());
    assertEquals("Label", actualAddColumnResult.getName());
    assertEquals("Label", actualAddColumnResult.getLabel());
    assertNull(actualAddColumnResult.getPrecision());
    assertNull(actualAddColumnResult.getScale());
    assertNull(actualAddColumnResult.getSource());
    assertNull(actualAddColumnResult.getEntityName());
    assertNull(actualAddColumnResult.getEntityMetaData());
    assertEquals(0, actualAddColumnResult.getOrdinalPosition());
    assertEquals(0, actualAddColumnResult.getTypeID());
    assertEquals(0L, actualAddColumnResult.getMaxLength());
    assertEquals(0L, actualAddColumnResult.getTypeModifiers());
    assertEquals(1, localResultSet.getColumnCount());
    assertEquals(DBPDataKind.BOOLEAN, actualAddColumnResult.getDataKind());
    assertFalse(actualAddColumnResult.isAutoGenerated());
    assertFalse(actualAddColumnResult.isRequired());
    assertTrue(actualAddColumnResult.isReadOnly());
  }

  /**
   * Test {@link LocalResultSet#getMetaColumn(int)}.
   *
   * <ul>
   *   <li>Then {@link LocalResultSetColumn#resultSet} return {@link LocalResultSet}.
   * </ul>
   *
   * <p>Method under test: {@link LocalResultSet#getMetaColumn(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCAttributeMetaData LocalResultSet.getMetaColumn(int)"})
  public void testGetMetaColumn_thenResultSetReturnLocalResultSet() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    localResultSet.addColumn("java.lang.Object[]", DBPDataKind.NUMERIC);
    localResultSet.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    DBCAttributeMetaData actualMetaColumn = localResultSet.getMetaColumn(1);

    // Assert
    assertTrue(((LocalResultSetColumn) actualMetaColumn).resultSet instanceof LocalResultSet);
    assertTrue(actualMetaColumn instanceof LocalResultSetColumn);
    assertEquals("?", actualMetaColumn.getTypeName());
    assertEquals("Label", actualMetaColumn.getName());
    assertEquals("Label", actualMetaColumn.getLabel());
    assertNull(actualMetaColumn.getPrecision());
    assertNull(actualMetaColumn.getScale());
    assertNull(actualMetaColumn.getSource());
    assertNull(actualMetaColumn.getEntityName());
    assertNull(actualMetaColumn.getEntityMetaData());
    assertEquals(0, actualMetaColumn.getTypeID());
    assertEquals(0L, actualMetaColumn.getMaxLength());
    assertEquals(0L, actualMetaColumn.getTypeModifiers());
    assertEquals(1, actualMetaColumn.getOrdinalPosition());
    assertEquals(DBPDataKind.BOOLEAN, actualMetaColumn.getDataKind());
    assertFalse(actualMetaColumn.isAutoGenerated());
    assertFalse(actualMetaColumn.isRequired());
    assertTrue(actualMetaColumn.isReadOnly());
  }

  /**
   * Test {@link LocalResultSet#addRow(Object[])}.
   *
   * <p>Method under test: {@link LocalResultSet#addRow(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalResultSet.addRow(Object[])"})
  public void testAddRow() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);
    Object[] values = new Object[] {DBPEvent.RENAME};

    // Act
    localResultSet.addRow(values);

    // Assert
    List<Object[]> objectArrayList = localResultSet.rows;
    assertEquals(1, objectArrayList.size());
    assertSame(values, objectArrayList.get(0));
  }
}
