package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.data.DBDFormatSettings;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCNumberValueHandlerDiffblueTest {
  @Mock private DBDFormatSettings dBDFormatSettings;

  @Mock private DBSTypedObject dBSTypedObject;

  @InjectMocks private JDBCNumberValueHandler jDBCNumberValueHandler;

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString() {
    // Arrange
    when(dBDFormatSettings.isUseScientificNumericFormat()).thenReturn(true);

    // Act
    String actualValueDisplayString =
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, 42, DBDDisplayFormat.EDIT);

    // Assert
    verify(dBDFormatSettings).isUseScientificNumericFormat();
    assertEquals("42", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString2() {
    // Arrange
    when(dBDFormatSettings.isUseScientificNumericFormat()).thenReturn(false);

    // Act
    String actualValueDisplayString =
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, 42, DBDDisplayFormat.EDIT);

    // Assert
    verify(dBDFormatSettings).isUseScientificNumericFormat();
    assertEquals("42", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDFormatSettings#getDataFormatterProfile()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenCallsGetDataFormatterProfile() {
    // Arrange
    when(dBDFormatSettings.getDataFormatterProfile()).thenThrow(new ClassCastException());

    // Act
    String actualValueDisplayString =
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, 42, DBDDisplayFormat.UI);

    // Assert
    verify(dBDFormatSettings).getDataFormatterProfile();
    assertEquals("42", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenReturn23() {
    // Arrange
    when(dBDFormatSettings.isUseScientificNumericFormat()).thenReturn(false);

    // Act
    String actualValueDisplayString =
        jDBCNumberValueHandler.getValueDisplayString(
            dBSTypedObject, new BigDecimal("2.3"), DBDDisplayFormat.EDIT);

    // Assert
    verify(dBDFormatSettings).isUseScientificNumericFormat();
    assertEquals("2.3", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then throw {@link NumberFormatException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenThrowNumberFormatException() {
    // Arrange
    when(dBDFormatSettings.isUseScientificNumericFormat()).thenThrow(new NumberFormatException());

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            jDBCNumberValueHandler.getValueDisplayString(
                dBSTypedObject, 42, DBDDisplayFormat.EDIT));
    verify(dBDFormatSettings).isUseScientificNumericFormat();
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   *   <li>Then return {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenBigDecimalWith23_thenReturn23() {
    // Arrange
    when(dBDFormatSettings.isUseScientificNumericFormat()).thenReturn(true);

    // Act
    String actualValueDisplayString =
        jDBCNumberValueHandler.getValueDisplayString(
            dBSTypedObject, new BigDecimal("2.3"), DBDDisplayFormat.EDIT);

    // Assert
    verify(dBDFormatSettings).isUseScientificNumericFormat();
    assertEquals("2.3", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code NATIVE}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNative() {
    // Arrange
    when(dBDFormatSettings.isUseScientificNumericFormat()).thenReturn(true);

    // Act
    String actualValueDisplayString =
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, 42, DBDDisplayFormat.NATIVE);

    // Assert
    verify(dBDFormatSettings).isUseScientificNumericFormat();
    assertEquals("42", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNull_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, null, DBDDisplayFormat.EDIT));
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, null, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link PrintStream#PrintStream(OutputStream)} with {@link
   *       ByteArrayOutputStream#ByteArrayOutputStream()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenPrintStreamWithByteArrayOutputStream() {
    // Arrange
    when(dBDFormatSettings.getDataFormatterProfile()).thenThrow(new ClassCastException());

    // Act
    jDBCNumberValueHandler.getValueDisplayString(
        dBSTypedObject, new PrintStream(new ByteArrayOutputStream()), DBDDisplayFormat.UI);

    // Assert
    verify(dBDFormatSettings).getDataFormatterProfile();
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCNumberValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        jDBCNumberValueHandler.getValueDisplayString(dBSTypedObject, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCNumberValueHandler#fetchColumnValue(DBCSession, JDBCResultSet, DBSTypedObject,
   * int)}.
   *
   * <ul>
   *   <li>Then return byteValue is {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#fetchColumnValue(DBCSession, JDBCResultSet,
   * DBSTypedObject, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object JDBCNumberValueHandler.fetchColumnValue(DBCSession, JDBCResultSet, DBSTypedObject, int)"
  })
  public void testFetchColumnValue_thenReturnByteValueIsA() throws SQLException, DBCException {
    // Arrange
    when(dBSTypedObject.getPrecision()).thenReturn(null);
    when(dBSTypedObject.getTypeID()).thenReturn(-7);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(anyInt())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    resultSet.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Object actualFetchColumnValueResult =
        jDBCNumberValueHandler.fetchColumnValue(null, resultSet, dBSTypedObject, 1);

    // Assert
    verify(callableStatement).getByte(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    verify(dBSTypedObject).getPrecision();
    verify(dBSTypedObject).getTypeID();
    assertEquals('A', ((Byte) actualFetchColumnValueResult).byteValue());
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute attribute = new DBVEntityAttribute(entity, parent, "Name");
    attribute.setScale(null);
    attribute.setPrecision(null);

    // Act
    Class<? extends Number> actualValueObjectType =
        jDBCNumberValueHandler.getValueObjectType(attribute);

    // Assert
    Class<Long> expectedValueObjectType = Long.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSTypedObject} {@link DBSTypedObject#getTypeID()} return eight.
   *   <li>Then return {@link Double}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_givenDBSTypedObjectGetTypeIDReturnEight_thenReturnDouble() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(8);
    when(dBSTypedObject.getScale()).thenReturn(1);

    // Act
    Class<? extends Number> actualValueObjectType =
        jDBCNumberValueHandler.getValueObjectType(dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
    Class<Double> expectedValueObjectType = Double.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSTypedObject} {@link DBSTypedObject#getTypeID()} return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_givenDBSTypedObjectGetTypeIDReturnThree() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(3);
    when(dBSTypedObject.getScale()).thenReturn(null);

    // Act
    Class<? extends Number> actualValueObjectType =
        jDBCNumberValueHandler.getValueObjectType(dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
    Class<BigDecimal> expectedValueObjectType = BigDecimal.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_givenOne() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute attribute = new DBVEntityAttribute(entity, parent, "Name");
    attribute.setScale(1);
    attribute.setPrecision(null);

    // Act
    Class<? extends Number> actualValueObjectType =
        jDBCNumberValueHandler.getValueObjectType(attribute);

    // Assert
    Class<Double> expectedValueObjectType = Double.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}.
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getNumberType(DBSTypedObject)"})
  public void testGetNumberType() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute type = new DBVEntityAttribute(entity, parent, "Name");
    type.setScale(null);
    type.setPrecision(null);

    // Act
    Class<? extends Number> actualNumberType = jDBCNumberValueHandler.getNumberType(type);

    // Assert
    Class<Long> expectedNumberType = Long.class;
    assertEquals(expectedNumberType, actualNumberType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSTypedObject} {@link DBSTypedObject#getTypeID()} return eight.
   *   <li>Then return {@link Double}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getNumberType(DBSTypedObject)"})
  public void testGetNumberType_givenDBSTypedObjectGetTypeIDReturnEight_thenReturnDouble() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(8);
    when(dBSTypedObject.getScale()).thenReturn(1);

    // Act
    Class<? extends Number> actualNumberType = jDBCNumberValueHandler.getNumberType(dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
    Class<Double> expectedNumberType = Double.class;
    assertEquals(expectedNumberType, actualNumberType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSTypedObject} {@link DBSTypedObject#getTypeID()} return three.
   *   <li>When {@link DBSTypedObject}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getNumberType(DBSTypedObject)"})
  public void testGetNumberType_givenDBSTypedObjectGetTypeIDReturnThree_whenDBSTypedObject() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(3);
    when(dBSTypedObject.getScale()).thenReturn(null);

    // Act
    Class<? extends Number> actualNumberType = jDBCNumberValueHandler.getNumberType(dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
    Class<BigDecimal> expectedNumberType = BigDecimal.class;
    assertEquals(expectedNumberType, actualNumberType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#getNumberType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCNumberValueHandler.getNumberType(DBSTypedObject)"})
  public void testGetNumberType_givenOne() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute type = new DBVEntityAttribute(entity, parent, "Name");
    type.setScale(1);
    type.setPrecision(null);

    // Act
    Class<? extends Number> actualNumberType = jDBCNumberValueHandler.getNumberType(type);

    // Assert
    Class<Double> expectedNumberType = Double.class;
    assertEquals(expectedNumberType, actualNumberType);
  }

  /**
   * Test {@link JDBCNumberValueHandler#generateDefaultValue(DBCSession, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSTypedObject} {@link DBSTypedObject#getTypeID()} return minus four.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#generateDefaultValue(DBCSession,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object JDBCNumberValueHandler.generateDefaultValue(DBCSession, DBSTypedObject)"
  })
  public void testGenerateDefaultValue_givenDBSTypedObjectGetTypeIDReturnMinusFour() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(-4);
    when(dBSTypedObject.getScale()).thenReturn(null);

    // Act
    Object actualGenerateDefaultValueResult =
        jDBCNumberValueHandler.generateDefaultValue(null, dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
    assertEquals(0L, ((Long) actualGenerateDefaultValueResult).longValue());
  }

  /**
   * Test {@link JDBCNumberValueHandler#generateDefaultValue(DBCSession, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSTypedObject} {@link DBSTypedObject#getTypeID()} return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#generateDefaultValue(DBCSession,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object JDBCNumberValueHandler.generateDefaultValue(DBCSession, DBSTypedObject)"
  })
  public void testGenerateDefaultValue_givenDBSTypedObjectGetTypeIDReturnThree() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(3);
    when(dBSTypedObject.getScale()).thenReturn(null);

    // Act
    jDBCNumberValueHandler.generateDefaultValue(null, dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
  }

  /**
   * Test {@link JDBCNumberValueHandler#generateDefaultValue(DBCSession, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return doubleValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCNumberValueHandler#generateDefaultValue(DBCSession,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object JDBCNumberValueHandler.generateDefaultValue(DBCSession, DBSTypedObject)"
  })
  public void testGenerateDefaultValue_thenReturnDoubleValueIsZero() {
    // Arrange
    when(dBSTypedObject.getTypeID()).thenReturn(8);
    when(dBSTypedObject.getScale()).thenReturn(1);

    // Act
    Object actualGenerateDefaultValueResult =
        jDBCNumberValueHandler.generateDefaultValue(null, dBSTypedObject);

    // Assert
    verify(dBSTypedObject).getScale();
    verify(dBSTypedObject).getTypeID();
    assertEquals(0.0d, ((Double) actualGenerateDefaultValueResult).doubleValue(), 0.0);
  }
}
