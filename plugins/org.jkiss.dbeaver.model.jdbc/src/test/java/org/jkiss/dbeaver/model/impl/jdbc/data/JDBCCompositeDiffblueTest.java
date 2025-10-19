package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCStructImpl;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCComposite.StructAttribute;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCComposite.StructType;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCColumnMetaData;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetMetaDataImpl;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCCompositeDiffblueTest {
  /**
   * Test {@link JDBCComposite#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCComposite.isNull()"})
  public void testIsNull_thenReturnFalse() throws SQLException, DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));

    JDBCStructImpl contents = mock(JDBCStructImpl.class);
    when(contents.getAttributes()).thenReturn(new Object[] {"Attributes"});

    JDBCCompositeDynamic struct =
        new JDBCCompositeDynamic(session, contents, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    boolean actualIsNullResult = jdbcCompositeDynamic.isNull();

    // Assert
    verify(session).getDataSource();
    verify(contents).getAttributes();
    assertFalse(actualIsNullResult);
  }

  /**
   * Test {@link JDBCComposite#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCComposite.isNull()"})
  public void testIsNull_thenReturnTrue() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    boolean actualIsNullResult = jdbcCompositeDynamic.isNull();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualIsNullResult);
  }

  /**
   * Test {@link JDBCComposite#isModified()}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getDataSource()} return {@link DBPDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#isModified()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCComposite.isModified()"})
  public void testIsModified_givenDBCSessionGetDataSourceReturnDBPDataSource_thenReturnFalse()
      throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    boolean actualIsModifiedResult = jdbcCompositeDynamic.isModified();

    // Assert
    verify(session).getDataSource();
    assertFalse(actualIsModifiedResult);
  }

  /**
   * Test {@link JDBCComposite#release()}.
   *
   * <p>Method under test: {@link JDBCComposite#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCComposite.release()"})
  public void testRelease() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    jdbcCompositeDynamic.release();

    // Assert
    verify(session).getDataSource();
  }

  /**
   * Test {@link JDBCComposite#release()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCStructImpl#getAttributes()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCComposite.release()"})
  public void testRelease_thenCallsGetAttributes() throws SQLException, DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));

    JDBCStructImpl contents = mock(JDBCStructImpl.class);
    when(contents.getAttributes()).thenReturn(new Object[] {"Attributes"});

    JDBCCompositeDynamic struct =
        new JDBCCompositeDynamic(session, contents, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    jdbcCompositeDynamic.release();

    // Assert
    verify(session).getDataSource();
    verify(contents).getAttributes();
  }

  /**
   * Test {@link JDBCComposite#getTypeName()}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getDataSource()} return {@link DBPDataSource}.
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#getTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCComposite.getTypeName()"})
  public void testGetTypeName_givenDBCSessionGetDataSourceReturnDBPDataSource_thenReturnObject()
      throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    String actualTypeName = jdbcCompositeDynamic.getTypeName();

    // Assert
    verify(session).getDataSource();
    assertEquals("Object", actualTypeName);
  }

  /**
   * Test {@link JDBCComposite#getStringRepresentation()}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#getStringRepresentation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCComposite.getStringRepresentation()"})
  public void testGetStringRepresentation_thenReturnLeftSquareBracketRightSquareBracket()
      throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    String actualStringRepresentation = jdbcCompositeDynamic.getStringRepresentation();

    // Assert
    verify(session).getDataSource();
    assertEquals("[]", actualStringRepresentation);
  }

  /**
   * Test {@link JDBCComposite#getValues()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#getValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] JDBCComposite.getValues()"})
  public void testGetValues_thenReturnArrayLengthIsZero() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    Object[] actualValues = jdbcCompositeDynamic.getValues();

    // Assert
    verify(session).getDataSource();
    assertEquals(0, actualValues.length);
  }

  /**
   * Test {@link JDBCComposite#getAttributes()}.
   *
   * <ul>
   *   <li>Then return {@code DBSEntityAttribute[]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#getAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSAttributeBase[] JDBCComposite.getAttributes()"})
  public void testGetAttributes_thenReturnDBSEntityAttribute() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    DBSAttributeBase[] actualAttributes = jdbcCompositeDynamic.getAttributes();

    // Assert
    verify(session).getDataSource();
    assertTrue(actualAttributes instanceof DBSEntityAttribute[]);
    assertEquals(0, actualAttributes.length);
    assertSame(jdbcCompositeDynamic.attributes, actualAttributes);
  }

  /**
   * Test {@link JDBCComposite#getAttributeValue(String)} with {@code attrName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCComposite.getAttributeValue(String)"})
  public void testGetAttributeValueWithAttrName_thenReturnNull() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    Object actualAttributeValue = jdbcCompositeDynamic.getAttributeValue("Attr Name");

    // Assert
    verify(session).getDataSource();
    assertNull(actualAttributeValue);
  }

  /**
   * Test StructAttribute {@link StructAttribute#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource StructAttribute.getDataSource()"})
  public void testStructAttributeGetDataSource_thenCallsGetParameterCount()
      throws SQLException, DBException {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(dbsDocumentConstraint, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    new StructAttribute(type, 1, "Value").getDataSource();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    verify(entity).getDataSource();
  }

  /**
   * Test StructAttribute {@link StructAttribute#getParentObject()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.struct.DBSEntity StructAttribute.getParentObject()"})
  public void testStructAttributeGetParentObject_thenReturnNull() throws DBException {
    // Arrange, Act and Assert
    assertNull(new StructAttribute(null, 1, "Value").getParentObject());
  }

  /**
   * Test StructAttribute {@link StructAttribute#StructAttribute(String, DBSDataType, int, Object)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return TypeName is {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#StructAttribute(String, DBSDataType, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructAttribute.<init>(String, DBSDataType, int, Object)"})
  public void testStructAttributeNewStructAttribute_whenTrue_thenReturnTypeNameIsBoolean()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    StructAttribute actualStructAttribute = new StructAttribute("Name", type, 1, true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    DBSDataType dbsDataType = actualStructAttribute.type;
    DBSObject parentObject = dbsDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertTrue(dbsDataType instanceof JDBCDataType);
    assertEquals("BOOLEAN", actualStructAttribute.getTypeName());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getPrecision().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getScale().intValue());
    assertEquals(1, dbsDataType.getPrecision().intValue());
    assertEquals(1, dbsDataType.getScale().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getMinScale());
    assertEquals(1, dbsDataType.getMaxScale());
    assertEquals(1, dbsDataType.getMinScale());
    assertEquals(1, dbsDataType.getTypeID());
    assertEquals(DBPDataKind.BOOLEAN, actualStructAttribute.getDataKind());
    assertEquals(Short.SIZE, actualStructAttribute.getTypeID());
  }

  /**
   * Test StructAttribute {@link StructAttribute#StructAttribute(DBSDataType, int, Object)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return TypeName is {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#StructAttribute(DBSDataType, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructAttribute.<init>(DBSDataType, int, Object)"})
  public void testStructAttributeNewStructAttribute_whenTrue_thenReturnTypeNameIsBoolean2()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    StructAttribute actualStructAttribute = new StructAttribute(type, 1, true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    DBSDataType dbsDataType = actualStructAttribute.type;
    DBSObject parentObject = dbsDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertTrue(dbsDataType instanceof JDBCDataType);
    assertEquals("BOOLEAN", actualStructAttribute.getTypeName());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getPrecision().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getScale().intValue());
    assertEquals(1, dbsDataType.getPrecision().intValue());
    assertEquals(1, dbsDataType.getScale().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getMinScale());
    assertEquals(1, dbsDataType.getMaxScale());
    assertEquals(1, dbsDataType.getMinScale());
    assertEquals(1, dbsDataType.getTypeID());
    assertEquals(DBPDataKind.BOOLEAN, actualStructAttribute.getDataKind());
    assertEquals(Short.SIZE, actualStructAttribute.getTypeID());
  }

  /**
   * Test StructAttribute {@link StructAttribute#StructAttribute(String, DBSDataType, int, Object)}.
   *
   * <ul>
   *   <li>When valueOf one.
   *   <li>Then return TypeName is {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#StructAttribute(String, DBSDataType, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructAttribute.<init>(String, DBSDataType, int, Object)"})
  public void testStructAttributeNewStructAttribute_whenValueOfOne_thenReturnTypeNameIsNumeric()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);
    Integer valueOfResult = Integer.valueOf(1);

    // Act
    StructAttribute actualStructAttribute = new StructAttribute("Name", type, 1, valueOfResult);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    DBSDataType dbsDataType = actualStructAttribute.type;
    DBSObject parentObject = dbsDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertTrue(dbsDataType instanceof JDBCDataType);
    assertEquals("NUMERIC", actualStructAttribute.getTypeName());
    assertEquals(2, actualStructAttribute.getTypeID());
    assertEquals(DBPDataKind.NUMERIC, actualStructAttribute.getDataKind());
    assertSame(valueOfResult, ((JDBCDataType<DBSObject>) parentObject).getMinScale());
    assertSame(valueOfResult, ((JDBCDataType<DBSObject>) parentObject).getPrecision());
    assertSame(valueOfResult, ((JDBCDataType<DBSObject>) parentObject).getScale());
    assertSame(valueOfResult, dbsDataType.getMaxScale());
    assertSame(valueOfResult, dbsDataType.getMinScale());
    assertSame(valueOfResult, dbsDataType.getPrecision());
    assertSame(valueOfResult, dbsDataType.getScale());
    assertSame(valueOfResult, dbsDataType.getTypeID());
  }

  /**
   * Test StructAttribute {@link StructAttribute#StructAttribute(DBSDataType, int, Object)}.
   *
   * <ul>
   *   <li>When valueOf one.
   *   <li>Then return TypeName is {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#StructAttribute(DBSDataType, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructAttribute.<init>(DBSDataType, int, Object)"})
  public void testStructAttributeNewStructAttribute_whenValueOfOne_thenReturnTypeNameIsNumeric2()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);
    Integer valueOfResult = Integer.valueOf(1);

    // Act
    StructAttribute actualStructAttribute = new StructAttribute(type, 1, valueOfResult);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    DBSDataType dbsDataType = actualStructAttribute.type;
    DBSObject parentObject = dbsDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertTrue(dbsDataType instanceof JDBCDataType);
    assertEquals("NUMERIC", actualStructAttribute.getTypeName());
    assertEquals(2, actualStructAttribute.getTypeID());
    assertEquals(DBPDataKind.NUMERIC, actualStructAttribute.getDataKind());
    assertSame(valueOfResult, ((JDBCDataType<DBSObject>) parentObject).getMinScale());
    assertSame(valueOfResult, ((JDBCDataType<DBSObject>) parentObject).getPrecision());
    assertSame(valueOfResult, ((JDBCDataType<DBSObject>) parentObject).getScale());
    assertSame(valueOfResult, dbsDataType.getMaxScale());
    assertSame(valueOfResult, dbsDataType.getMinScale());
    assertSame(valueOfResult, dbsDataType.getPrecision());
    assertSame(valueOfResult, dbsDataType.getScale());
    assertSame(valueOfResult, dbsDataType.getTypeID());
  }

  /**
   * Test StructAttribute {@link StructAttribute#StructAttribute(String, DBSDataType, int, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return TypeName is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#StructAttribute(String, DBSDataType, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructAttribute.<init>(String, DBSDataType, int, Object)"})
  public void testStructAttributeNewStructAttribute_whenValue_thenReturnTypeNameIsString()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    StructAttribute actualStructAttribute = new StructAttribute("Name", type, 1, "Value");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    DBSDataType dbsDataType = actualStructAttribute.type;
    DBSObject parentObject = dbsDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertTrue(dbsDataType instanceof JDBCDataType);
    assertEquals("STRING", actualStructAttribute.getTypeName());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getPrecision().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getScale().intValue());
    assertEquals(1, dbsDataType.getPrecision().intValue());
    assertEquals(1, dbsDataType.getScale().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getMinScale());
    assertEquals(1, dbsDataType.getMaxScale());
    assertEquals(1, dbsDataType.getMinScale());
    assertEquals(1, dbsDataType.getTypeID());
    assertEquals(12, actualStructAttribute.getTypeID());
    assertEquals(DBPDataKind.STRING, actualStructAttribute.getDataKind());
  }

  /**
   * Test StructAttribute {@link StructAttribute#StructAttribute(DBSDataType, int, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return TypeName is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link StructAttribute#StructAttribute(DBSDataType, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructAttribute.<init>(DBSDataType, int, Object)"})
  public void testStructAttributeNewStructAttribute_whenValue_thenReturnTypeNameIsString2()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    StructAttribute actualStructAttribute = new StructAttribute(type, 1, "Value");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    DBSDataType dbsDataType = actualStructAttribute.type;
    DBSObject parentObject = dbsDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertTrue(dbsDataType instanceof JDBCDataType);
    assertEquals("STRING", actualStructAttribute.getTypeName());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getPrecision().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getScale().intValue());
    assertEquals(1, dbsDataType.getPrecision().intValue());
    assertEquals(1, dbsDataType.getScale().intValue());
    assertEquals(1, ((JDBCDataType<DBSObject>) parentObject).getMinScale());
    assertEquals(1, dbsDataType.getMaxScale());
    assertEquals(1, dbsDataType.getMinScale());
    assertEquals(1, dbsDataType.getTypeID());
    assertEquals(12, actualStructAttribute.getTypeID());
    assertEquals(DBPDataKind.STRING, actualStructAttribute.getDataKind());
  }

  /**
   * Test StructType {@link StructType#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StructType#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StructType.getAttributes(DBRProgressMonitor)"})
  public void testStructTypeGetAttributes_thenReturnEmpty() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    StructType structType = jdbcCompositeDynamic.new StructType(mock(DBPDataSource.class));

    // Act
    List<? extends DBSEntityAttribute> actualAttributes =
        structType.getAttributes(new LoggingProgressMonitor());

    // Assert
    verify(session).getDataSource();
    assertTrue(actualAttributes.isEmpty());
  }

  /**
   * Test StructType {@link StructType#getTypeID()}.
   *
   * <p>Method under test: {@link StructType#getTypeID()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int StructType.getTypeID()"})
  public void testStructTypeGetTypeID() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());

    // Act
    int actualTypeID = jdbcCompositeDynamic.new StructType(mock(DBPDataSource.class)).getTypeID();

    // Assert
    verify(session).getDataSource();
    assertEquals(2002, actualTypeID);
  }

  /**
   * Test {@link JDBCComposite#toString()}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCComposite#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCComposite.toString()"})
  public void testToString_thenReturnLeftSquareBracketRightSquareBracket() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    String actualToStringResult = jdbcCompositeDynamic.toString();

    // Assert
    verify(session).getDataSource();
    assertEquals("[]", actualToStringResult);
  }
}
