package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import org.eclipse.equinox.app.IApplication;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.data.DBDBinaryFormatter;
import org.jkiss.dbeaver.model.data.DBDDataFormatter;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.data.DBDNull;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.data.formatters.BinaryFormatterBase64;
import org.jkiss.dbeaver.model.impl.data.formatters.BinaryFormatterString;
import org.jkiss.dbeaver.model.impl.data.formatters.DefaultDataFormatter;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.impl.local.StatResultSet;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBValueFormattingDiffblueTest {
  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBPImage actualTypeImage = DBValueFormatting.getTypeImage(typedObject);
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    DBPImage icon = typedObject.getEntity().getEntityType().getIcon();
    assertTrue(icon instanceof DBIcon);
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("types/unknown.svg", actualTypeImage.getLocation());
    assertEquals("types/unknown.svg", actualLocation);
    assertEquals("unknown", ((DBIcon) actualTypeImage).getToken());
    assertSame(((DBIcon) actualTypeImage).TREE_TABLE, icon);
    assertSame(((DBIcon) actualTypeImage).TYPE_UNKNOWN, typedObject.getObjectImage());
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code array}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsArray() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.ARRAY));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("array", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/array.svg", actualTypeImage.getLocation());
    assertEquals("types/array.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code binary}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsBinary() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BINARY));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("binary", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/binary.svg", actualTypeImage.getLocation());
    assertEquals("types/binary.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code boolean}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsBoolean() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("boolean", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/boolean.svg", actualTypeImage.getLocation());
    assertEquals("types/boolean.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code datetime}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsDatetime() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.DATETIME));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("datetime", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/datetime.svg", actualTypeImage.getLocation());
    assertEquals("types/datetime.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code document}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsDocument() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.DOCUMENT));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("document", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/document.svg", actualTypeImage.getLocation());
    assertEquals("types/document.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code lob}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsLob() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.CONTENT));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("lob", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/lob.svg", actualTypeImage.getLocation());
    assertEquals("types/lob.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code number}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsNumber() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.NUMERIC));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("number", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/number.svg", actualTypeImage.getLocation());
    assertEquals("types/number.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code reference}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsReference() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.REFERENCE));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("reference", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/reference.svg", actualTypeImage.getLocation());
    assertEquals("types/reference.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code rowid}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsRowid() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.ROWID));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("rowid", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/rowid.svg", actualTypeImage.getLocation());
    assertEquals("types/rowid.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code string}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsString() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.STRING));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("string", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/string.svg", actualTypeImage.getLocation());
    assertEquals("types/string.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Token is {@code struct}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_thenReturnTokenIsStruct() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBPImage actualTypeImage =
        DBValueFormatting.getTypeImage(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.STRUCT));
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("struct", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/struct.svg", actualTypeImage.getLocation());
    assertEquals("types/struct.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return Token is {@code object}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getTypeImage(DBSTypedObject)"})
  public void testGetTypeImage_whenDefault_type_thenReturnTokenIsObject() {
    // Arrange and Act
    DBPImage actualTypeImage = DBValueFormatting.getTypeImage(SimpleTypedObject.DEFAULT_TYPE);
    String actualLocation = actualTypeImage.getLocation();

    // Assert
    assertTrue(actualTypeImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualTypeImage).getToken());
    assertEquals("types/object.svg", actualTypeImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.BOOLEAN);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("boolean", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/boolean.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.BOOLEAN, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage2() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.NUMERIC);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("number", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/number.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.NUMERIC, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage3() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.STRING);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("string", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/string.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.STRING, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage4() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.DATETIME);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("datetime", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/datetime.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.DATETIME, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage5() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.BINARY);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("binary", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/binary.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.BINARY, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage6() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.CONTENT);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("lob", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/lob.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.CONTENT, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage7() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.STRUCT);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("struct", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/struct.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.STRUCT, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage8() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.DOCUMENT);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("document", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/document.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.DOCUMENT, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage9() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.ARRAY);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("array", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/array.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.ARRAY, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage10() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement =
        new LocalStatement(mock(DBCSession.class), DBConstants.TYPE_NAME_UUID);

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.REFERENCE);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("reference", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/reference.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.REFERENCE, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage11() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement statement = new LocalStatement(mock(DBCSession.class), "?");

    StatResultSet resultSet = new StatResultSet(session, statement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.ROWID);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("rowid", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/rowid.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.ROWID, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage12() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement statement = new LocalStatement(mock(DBCSession.class), "?");

    StatResultSet resultSet = new StatResultSet(session, statement);
    LocalResultSetColumn typedObject =
        new LocalResultSetColumn(resultSet, 1, DBConstants.TYPE_NAME_UUID, DBPDataKind.ANY);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    verify(session).getDataSource();
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("?", typedObject.getTypeName());
    assertEquals("any", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/any.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getSource());
    assertNull(typedObject.getEntityName());
    assertNull(typedObject.getEntityMetaData());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(1, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.ANY, typedObject.getDataKind());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.isReadOnly());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getLabel());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Location is {@code types/unknown.svg}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage_thenReturnLocationIsTypesUnknownSvg() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), DBConstants.TYPE_NAME_UUID);
    DBVEntity entity =
        new DBVEntity(container, DBConstants.TYPE_NAME_UUID, DBConstants.TYPE_NAME_UUID);
    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), DBConstants.TYPE_NAME_UUID);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("types/unknown.svg", actualDefaultTypeImage.getLocation());
    assertEquals("unknown", ((DBIcon) actualDefaultTypeImage).getToken());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertNull(typedObject.getDefaultValue());
    assertNull(typedObject.getDescription());
    assertNull(typedObject.getExpression());
    assertNull(typedObject.getFullTypeName());
    assertNull(typedObject.getTypeName());
    assertNull(typedObject.getParsedExpression());
    assertNull(typedObject.getDataSource());
    assertNull(typedObject.getTransformSettings());
    assertEquals(-1, typedObject.getTypeID());
    assertEquals(-1L, typedObject.getMaxLength());
    assertEquals(0, typedObject.getOrdinalPosition());
    assertEquals(DBPDataKind.UNKNOWN, typedObject.getDataKind());
    assertFalse(typedObject.hasValuableData());
    assertFalse(typedObject.isAutoGenerated());
    assertFalse(typedObject.isCustom());
    assertFalse(typedObject.isRequired());
    assertTrue(typedObject.getChildren().isEmpty());
    assertTrue(typedObject.getProperties().isEmpty());
    assertTrue(typedObject.isPersisted());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getName());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.toString());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
    assertSame(entity, typedObject.getEntity());
    assertSame(entity, typedObject.getParentObject());
    assertSame(((DBIcon) actualDefaultTypeImage).TYPE_UNKNOWN, typedObject.getObjectImage());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return Location is {@code types/uuid.svg}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage_thenReturnLocationIsTypesUuidSvg() {
    // Arrange
    SimpleTypedObject typedObject = new SimpleTypedObject(DBConstants.TYPE_NAME_UUID);

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("types/uuid.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(DBPDataKind.OBJECT, typedObject.getDataKind());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getFullTypeName());
    assertEquals(DBConstants.TYPE_NAME_UUID, typedObject.getTypeName());
    assertEquals(DBConstants.TYPE_NAME_UUID2, ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then {@link SimpleTypedObject#DEFAULT_TYPE} FullTypeName is {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage_whenDefault_type_thenDefault_typeFullTypeNameIsObject() {
    // Arrange
    SimpleTypedObject typedObject = SimpleTypedObject.DEFAULT_TYPE;

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);
    String actualLocation = actualDefaultTypeImage.getLocation();

    // Assert
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("Object", typedObject.getFullTypeName());
    assertEquals("Object", typedObject.getTypeName());
    assertEquals("object", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/object.svg", actualDefaultTypeImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(DBPDataKind.OBJECT, typedObject.getDataKind());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then {@link SimpleTypedObject#DEFAULT_TYPE} FullTypeName is {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultTypeImage(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getDefaultTypeImage(DBSTypedObject)"})
  public void testGetDefaultTypeImage_whenDefault_type_thenDefault_typeFullTypeNameIsObject2() {
    // Arrange
    SimpleTypedObject typedObject = SimpleTypedObject.DEFAULT_TYPE;

    // Act
    DBPImage actualDefaultTypeImage = DBValueFormatting.getDefaultTypeImage(typedObject);

    // Assert
    assertTrue(actualDefaultTypeImage instanceof DBIcon);
    assertEquals("Object", typedObject.getFullTypeName());
    assertEquals("Object", typedObject.getTypeName());
    assertEquals("object", ((DBIcon) actualDefaultTypeImage).getToken());
    assertEquals("types/object.svg", actualDefaultTypeImage.getLocation());
    assertNull(typedObject.getPrecision());
    assertNull(typedObject.getScale());
    assertEquals(0, typedObject.getTypeID());
    assertEquals(DBPDataKind.OBJECT, typedObject.getDataKind());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, typedObject.getTypeModifiers());
  }

  /**
   * Test {@link DBValueFormatting#getObjectImage(DBPObject, boolean)} with {@code object}, {@code
   * useDefault}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getObjectImage(DBPObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getObjectImage(DBPObject, boolean)"})
  public void testGetObjectImageWithObjectUseDefault_whenDefault_type_thenReturnDBIcon() {
    // Arrange and Act
    DBPImage actualObjectImage =
        DBValueFormatting.getObjectImage(SimpleTypedObject.DEFAULT_TYPE, true);
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/object.svg", actualObjectImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getObjectImage(DBPObject, boolean)} with {@code object}, {@code
   * useDefault}.
   *
   * <ul>
   *   <li>When {@link DBDNull#INSTANCE}.
   *   <li>Then return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getObjectImage(DBPObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getObjectImage(DBPObject, boolean)"})
  public void testGetObjectImageWithObjectUseDefault_whenInstance_thenReturnDBIcon() {
    // Arrange and Act
    DBPImage actualObjectImage = DBValueFormatting.getObjectImage(DBDNull.INSTANCE, true);
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/object.svg", actualObjectImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getObjectImage(DBPObject)} with {@code object}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getObjectImage(DBPObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getObjectImage(DBPObject)"})
  public void testGetObjectImageWithObject_whenDefault_type() {
    // Arrange and Act
    DBPImage actualObjectImage = DBValueFormatting.getObjectImage(SimpleTypedObject.DEFAULT_TYPE);
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/object.svg", actualObjectImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getObjectImage(DBPObject)} with {@code object}.
   *
   * <ul>
   *   <li>When {@link DBDNull#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getObjectImage(DBPObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBValueFormatting.getObjectImage(DBPObject)"})
  public void testGetObjectImageWithObject_whenInstance() {
    // Arrange and Act
    DBPImage actualObjectImage = DBValueFormatting.getObjectImage(DBDNull.INSTANCE);
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/object.svg", actualObjectImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
  }

  /**
   * Test {@link DBValueFormatting#getBinaryPresentation(DBPDataSource)} with {@code dataSource}.
   *
   * <ul>
   *   <li>Given {@link NumberFormatException#NumberFormatException()}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getBinaryPresentation(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDBinaryFormatter DBValueFormatting.getBinaryPresentation(DBPDataSource)"})
  public void testGetBinaryPresentationWithDataSource_givenNumberFormatException() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new NumberFormatException());

    // Act and Assert
    assertThrows(
        NumberFormatException.class, () -> DBValueFormatting.getBinaryPresentation(dataSource));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link DBValueFormatting#getBinaryPresentation(DBPDataSource)} with {@code dataSource}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getPreferenceStore()}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getBinaryPresentation(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDBinaryFormatter DBValueFormatting.getBinaryPresentation(DBPDataSource)"})
  public void testGetBinaryPresentationWithDataSource_thenCallsGetPreferenceStore() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        NumberFormatException.class, () -> DBValueFormatting.getBinaryPresentation(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }

  /**
   * Test {@link DBValueFormatting#getBinaryPresentation(String)} with {@code id}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getBinaryPresentation(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDBinaryFormatter DBValueFormatting.getBinaryPresentation(String)"})
  public void testGetBinaryPresentationWithId_when42_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBValueFormatting.getBinaryPresentation("42"));
  }

  /**
   * Test {@link DBValueFormatting#getBinaryPresentation(String)} with {@code id}.
   *
   * <ul>
   *   <li>When {@code string}.
   *   <li>Then return {@link BinaryFormatterString}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getBinaryPresentation(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDBinaryFormatter DBValueFormatting.getBinaryPresentation(String)"})
  public void testGetBinaryPresentationWithId_whenString_thenReturnBinaryFormatterString() {
    // Arrange and Act
    DBDBinaryFormatter actualBinaryPresentation = DBValueFormatting.getBinaryPresentation("string");

    // Assert
    assertTrue(actualBinaryPresentation instanceof BinaryFormatterString);
    assertEquals("String", actualBinaryPresentation.getTitle());
    assertEquals("string", actualBinaryPresentation.getId());
  }

  /**
   * Test {@link DBValueFormatting#getDefaultBinaryFileEncoding(DBPDataSource)}.
   *
   * <ul>
   *   <li>Given {@link NumberFormatException#NumberFormatException()}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultBinaryFileEncoding(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBValueFormatting.getDefaultBinaryFileEncoding(DBPDataSource)"})
  public void testGetDefaultBinaryFileEncoding_givenNumberFormatException() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new NumberFormatException());

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DBValueFormatting.getDefaultBinaryFileEncoding(dataSource));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link DBValueFormatting#getDefaultBinaryFileEncoding(DBPDataSource)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getPreferenceStore()}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultBinaryFileEncoding(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBValueFormatting.getDefaultBinaryFileEncoding(DBPDataSource)"})
  public void testGetDefaultBinaryFileEncoding_thenCallsGetPreferenceStore() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DBValueFormatting.getDefaultBinaryFileEncoding(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenEmptyString_thenReturnNull() throws DBCException {
    // Arrange
    Class<Object> hintType = Object.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber("", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertNull(actualConvertStringToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Byte}.
   *   <li>Then return byteValue is {@code *}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenJavaLangByte_thenReturnByteValueIsAsterisk()
      throws DBCException {
    // Arrange
    Class<Byte> hintType = Byte.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "42", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals('*', ((Byte) actualConvertStringToNumberResult).byteValue());
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Byte}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenJavaLangByte_thenReturnText() throws DBCException {
    // Arrange
    Class<Byte> hintType = Byte.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "Text", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals("Text", actualConvertStringToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Double}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenJavaLangDouble_thenReturnDoubleValueIsFortyTwo()
      throws DBCException {
    // Arrange
    Class<Double> hintType = Double.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "42", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals(42.0d, ((Double) actualConvertStringToNumberResult).doubleValue(), 0.0);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Double}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenJavaLangDouble_thenReturnText() throws DBCException {
    // Arrange
    Class<Double> hintType = Double.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "Text", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals("Text", actualConvertStringToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Float}.
   *   <li>Then return floatValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenJavaLangFloat_thenReturnFloatValueIsFortyTwo()
      throws DBCException {
    // Arrange
    Class<Float> hintType = Float.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "42", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals(42.0f, ((Float) actualConvertStringToNumberResult).floatValue(), 0.0f);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Float}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenJavaLangFloat_thenReturnText() throws DBCException {
    // Arrange
    Class<Float> hintType = Float.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "Text", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals("Text", actualConvertStringToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenNull_thenReturnNull() throws DBCException {
    // Arrange
    Class<Object> hintType = Object.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            null, hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertNull(actualConvertStringToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#convertStringToNumber(String, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Text}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertStringToNumber(String, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertStringToNumber(String, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertStringToNumber_whenText_thenReturnText() throws DBCException {
    // Arrange
    Class<Object> hintType = Object.class;

    // Act
    Object actualConvertStringToNumberResult =
        DBValueFormatting.convertStringToNumber(
            "Text", hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals("Text", actualConvertStringToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}.
   *
   * <ul>
   *   <li>When {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   *   <li>Then return {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBValueFormatting.convertNumberToNativeString(Number, boolean)"})
  public void testConvertNumberToNativeString_whenBigDecimalWith23_thenReturn23() {
    // Arrange, Act and Assert
    assertEquals(
        "2.3", DBValueFormatting.convertNumberToNativeString(new BigDecimal("2.3"), false));
  }

  /**
   * Test {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}.
   *
   * <ul>
   *   <li>When {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   *   <li>Then return {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBValueFormatting.convertNumberToNativeString(Number, boolean)"})
  public void testConvertNumberToNativeString_whenBigDecimalWith23_thenReturn232() {
    // Arrange, Act and Assert
    assertEquals("2.3", DBValueFormatting.convertNumberToNativeString(new BigDecimal("2.3"), true));
  }

  /**
   * Test {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}.
   *
   * <ul>
   *   <li>When {@link IApplication#EXIT_OK}.
   *   <li>Then return {@code 0}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBValueFormatting.convertNumberToNativeString(Number, boolean)"})
  public void testConvertNumberToNativeString_whenExit_ok_thenReturn0() {
    // Arrange, Act and Assert
    assertEquals("0", DBValueFormatting.convertNumberToNativeString(IApplication.EXIT_OK, true));
  }

  /**
   * Test {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 10.0}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertNumberToNativeString(Number, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBValueFormatting.convertNumberToNativeString(Number, boolean)"})
  public void testConvertNumberToNativeString_whenTen_thenReturn100() {
    // Arrange, Act and Assert
    assertEquals("10.0", DBValueFormatting.convertNumberToNativeString(10.0f, false));
  }

  /**
   * Test {@link DBValueFormatting#convertDateToNumber(Date, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code Double}.
   *   <li>Then return doubleValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertDateToNumber(Date, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertDateToNumber(Date, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertDateToNumber_whenJavaLangDouble_thenReturnDoubleValueIsZero()
      throws DBCException {
    // Arrange
    Date date =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Class<Double> hintType = Double.class;

    // Act
    Object actualConvertDateToNumberResult =
        DBValueFormatting.convertDateToNumber(date, hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertEquals(0.0d, ((Double) actualConvertDateToNumberResult).doubleValue(), 0.0);
  }

  /**
   * Test {@link DBValueFormatting#convertDateToNumber(Date, Class, DBDDataFormatter, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#convertDateToNumber(Date, Class,
   * DBDDataFormatter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object DBValueFormatting.convertDateToNumber(Date, Class, DBDDataFormatter, boolean)"
  })
  public void testConvertDateToNumber_whenNull_thenReturnNull() throws DBCException {
    // Arrange
    Class<Object> hintType = Object.class;

    // Act
    Object actualConvertDateToNumberResult =
        DBValueFormatting.convertDateToNumber(null, hintType, DefaultDataFormatter.INSTANCE, true);

    // Assert
    assertNull(actualConvertDateToNumberResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat() throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat2()
      throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterBase64());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource, "AXAXAXAXAXAXAXAXAXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("QVhBWEFYQVhBWEFYQVhBWEFYQVhBWEFY", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat,
   * boolean)} with {@code dataSource}, {@code data}, {@code format}, {@code forceLimit}.
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat, boolean)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormatForceLimit()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new NumberFormatException());

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.UI, true));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat,
   * boolean)} with {@code dataSource}, {@code data}, {@code format}, {@code forceLimit}.
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat, boolean)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormatForceLimit2()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.UI, true));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat,
   * boolean)} with {@code dataSource}, {@code data}, {@code format}, {@code forceLimit}.
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat, boolean)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormatForceLimit3()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new NumberFormatException());

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE, true));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat,
   * boolean)} with {@code dataSource}, {@code data}, {@code format}, {@code forceLimit}.
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat, boolean)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormatForceLimit4()
      throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE, true));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat,
   * boolean)} with {@code dataSource}, {@code data}, {@code format}, {@code forceLimit}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat, boolean)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormatForceLimit_givenInstance()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE, true));
    verify(dataSource).getContainer();
    verify(dataSource).getSQLDialect();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code 0x}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_givenInstance_thenReturn0x() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(dataSource, new byte[] {}, DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("0x", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Given {@link NumberFormatException#NumberFormatException()}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_givenNumberFormatException()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new NumberFormatException());

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.UI));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getPreferenceStore()}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenCallsGetPreferenceStore()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DBValueFormatting.formatBinaryString(
                dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.UI));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return {@code 0x4158415841584158}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturn0x4158415841584158()
      throws UnsupportedEncodingException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("0x4158415841584158", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturnAxaxaxax()
      throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterString());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("AXAXAXAX", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturnEmptyString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterBase64());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(dataSource, new byte[] {}, DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturnEmptyString2() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterString());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(dataSource, new byte[] {}, DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return {@code QVhBWEFYQVg=}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturnQVhBWEFYQVg()
      throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterBase64());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource, "AXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("QVhBWEFYQVg=", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return {@code QVhBWEFYQVhBWEFYQVhBWA==}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturnQVhBWEFYQVhBWEFYQVhBWA()
      throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterBase64());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource, "AXAXAXAXAXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("QVhBWEFYQVhBWEFYQVhBWA==", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>Then return {@code ÿXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_thenReturnXaxaxax() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterString());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource,
            new byte[] {-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals("ÿXAXAXAX", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>When array of {@code byte} with {@link Byte#MAX_VALUE} and {@code X}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_whenArrayOfByteWithMax_valueAndX() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterString());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource,
            new byte[] {Byte.MAX_VALUE, 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals(" XAXAXAX", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)} with
   * {@code dataSource}, {@code data}, {@code format}.
   *
   * <ul>
   *   <li>When {@code XAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#formatBinaryString(DBPDataSource, byte[],
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.formatBinaryString(DBPDataSource, byte[], DBDDisplayFormat)"
  })
  public void testFormatBinaryStringWithDataSourceDataFormat_whenXaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenReturn(new BinaryFormatterString());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFormatBinaryStringResult =
        DBValueFormatting.formatBinaryString(
            dataSource, "\bXAXAXAX".getBytes("UTF-8"), DBDDisplayFormat.NATIVE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getNativeBinaryFormatter();
    assertEquals(" XAXAXAX", actualFormatBinaryStringResult);
  }

  /**
   * Test {@link DBValueFormatting#getDefaultValueDisplayString(Object, DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultValueDisplayString(Object,
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.getDefaultValueDisplayString(Object, DBDDisplayFormat)"
  })
  public void testGetDefaultValueDisplayString() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        DBValueFormatting.getDefaultValueDisplayString(
            new LocalNativeClientLocation("42", "java.lang"), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link DBValueFormatting#getDefaultValueDisplayString(Object, DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultValueDisplayString(Object,
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.getDefaultValueDisplayString(Object, DBDDisplayFormat)"
  })
  public void testGetDefaultValueDisplayString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals(
        "[]",
        DBValueFormatting.getDefaultValueDisplayString(new ArrayList<>(), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link DBValueFormatting#getDefaultValueDisplayString(Object, DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@link DBConstants#NULL_VALUE_LABEL}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultValueDisplayString(Object,
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.getDefaultValueDisplayString(Object, DBDDisplayFormat)"
  })
  public void testGetDefaultValueDisplayString_thenReturnNull_value_label() {
    // Arrange, Act and Assert
    assertEquals(
        DBConstants.NULL_VALUE_LABEL,
        DBValueFormatting.getDefaultValueDisplayString(
            new DBDDocumentXML(null), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link DBValueFormatting#getDefaultValueDisplayString(Object, DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultValueDisplayString(Object,
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.getDefaultValueDisplayString(Object, DBDDisplayFormat)"
  })
  public void testGetDefaultValueDisplayString_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", DBValueFormatting.getDefaultValueDisplayString("42", DBDDisplayFormat.UI));
  }

  /**
   * Test {@link DBValueFormatting#getDefaultValueDisplayString(Object, DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code EDIT}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultValueDisplayString(Object,
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.getDefaultValueDisplayString(Object, DBDDisplayFormat)"
  })
  public void testGetDefaultValueDisplayString_whenEdit_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        DBValueFormatting.getDefaultValueDisplayString(
            new DBDDocumentXML(null), DBDDisplayFormat.EDIT));
  }

  /**
   * Test {@link DBValueFormatting#getDefaultValueDisplayString(Object, DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link DBConstants#NULL_VALUE_LABEL}.
   * </ul>
   *
   * <p>Method under test: {@link DBValueFormatting#getDefaultValueDisplayString(Object,
   * DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBValueFormatting.getDefaultValueDisplayString(Object, DBDDisplayFormat)"
  })
  public void testGetDefaultValueDisplayString_whenNull_thenReturnNull_value_label() {
    // Arrange, Act and Assert
    assertEquals(
        DBConstants.NULL_VALUE_LABEL,
        DBValueFormatting.getDefaultValueDisplayString(null, DBDDisplayFormat.UI));
  }
}
