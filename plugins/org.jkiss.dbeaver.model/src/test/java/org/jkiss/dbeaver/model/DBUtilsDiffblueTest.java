package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingMeta;
import org.jkiss.dbeaver.model.data.DBDFormatSettings;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute.PropagationPolicy;
import org.jkiss.dbeaver.model.data.DBDPseudoAttributeType;
import org.jkiss.dbeaver.model.data.DBDValueHandler;
import org.jkiss.dbeaver.model.exec.DBCAttributeMetaData;
import org.jkiss.dbeaver.model.exec.DBCEntityMetaData;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionContextDefaults;
import org.jkiss.dbeaver.model.exec.DBCExecutionSource;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.exec.DBCStatementType;
import org.jkiss.dbeaver.model.impl.AbstractExecutionSource;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.data.DBDValueError;
import org.jkiss.dbeaver.model.impl.data.DefaultValueHandler;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.impl.struct.DirectObjectReference;
import org.jkiss.dbeaver.model.impl.struct.RelationalObjectType;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseFolder;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSContextBoundAttribute;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityAttributeRef;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraint;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.DBSEntityReferrer;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.model.struct.DBSWrapper;
import org.jkiss.dbeaver.model.struct.rdb.DBSTableIndex;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityConstraint;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBUtilsDiffblueTest {
  /**
   * Test {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullQualifiedName(DBPDataSource, DBPNamedObject[])"})
  public void testGetFullQualifiedName_givenInstance_thenReturnName() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    // Act
    String actualFullQualifiedName = DBUtils.getFullQualifiedName(dataSource, dbpNamedObject);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(dbpNamedObject, atLeast(1)).getName();
    assertEquals("\"Name\"", actualFullQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBPNamedObject} {@link DBPNamedObject#getName()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullQualifiedName(DBPDataSource, DBPNamedObject[])"})
  public void testGetFullQualifiedName_givenNull_whenDBPNamedObjectGetNameReturnNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("");

    DBPNamedObject dbpNamedObject2 = mock(DBPNamedObject.class);
    when(dbpNamedObject2.getName()).thenReturn(null);

    DBPNamedObject dbpNamedObject3 = mock(DBPNamedObject.class);
    when(dbpNamedObject3.getName()).thenReturn("Name");

    // Act
    String actualFullQualifiedName =
        DBUtils.getFullQualifiedName(dataSource, dbpNamedObject, dbpNamedObject2, dbpNamedObject3);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(dbpNamedObject).getName();
    verify(dbpNamedObject2).getName();
    verify(dbpNamedObject3, atLeast(1)).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals("42", actualFullQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}.
   *
   * <ul>
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullQualifiedName(DBPDataSource, DBPNamedObject[])"})
  public void testGetFullQualifiedName_thenReturn42a42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    DBPNamedObject dbpNamedObject2 = mock(DBPNamedObject.class);
    when(dbpNamedObject2.getName()).thenReturn("Name");

    // Act
    String actualFullQualifiedName =
        DBUtils.getFullQualifiedName(dataSource, dbpNamedObject, dbpNamedObject2);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(dbpNamedObject, atLeast(1)).getName();
    verify(dbpNamedObject2, atLeast(1)).getName();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    assertEquals("42A42", actualFullQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}.
   *
   * <ul>
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullQualifiedName(DBPDataSource, DBPNamedObject[])"})
  public void testGetFullQualifiedName_thenReturn42a422() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("");

    DBPNamedObject dbpNamedObject2 = mock(DBPNamedObject.class);
    when(dbpNamedObject2.getName()).thenReturn("Name");

    DBPNamedObject dbpNamedObject3 = mock(DBPNamedObject.class);
    when(dbpNamedObject3.getName()).thenReturn("Name");

    // Act
    String actualFullQualifiedName =
        DBUtils.getFullQualifiedName(dataSource, dbpNamedObject, dbpNamedObject2, dbpNamedObject3);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(dbpNamedObject).getName();
    verify(dbpNamedObject2, atLeast(1)).getName();
    verify(dbpNamedObject3, atLeast(1)).getName();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    assertEquals("42A42", actualFullQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}.
   *
   * <ul>
   *   <li>When {@link DBPNamedObject}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullQualifiedName(DBPDataSource, DBPNamedObject[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullQualifiedName(DBPDataSource, DBPNamedObject[])"})
  public void testGetFullQualifiedName_whenDBPNamedObject_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    // Act
    String actualFullQualifiedName = DBUtils.getFullQualifiedName(dataSource, dbpNamedObject);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(dbpNamedObject, atLeast(1)).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals("42", actualFullQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code "Names"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullyQualifiedName(DBPDataSource, String[])"})
  public void testGetFullyQualifiedName_givenInstance_thenReturnNames() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualFullyQualifiedName = DBUtils.getFullyQualifiedName(dataSource, "Names");

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("\"Names\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}.
   *
   * <ul>
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullyQualifiedName(DBPDataSource, String[])"})
  public void testGetFullyQualifiedName_thenReturn42a42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFullyQualifiedName =
        DBUtils.getFullyQualifiedName(dataSource, "Names", null, "Names");

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Names", true, false);
    verify(sqlDialect).getStructSeparator();
    assertEquals("42A42", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}.
   *
   * <ul>
   *   <li>When {@code Names} and {@code null}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullyQualifiedName(DBPDataSource, String[])"})
  public void testGetFullyQualifiedName_whenNamesAndNull_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFullyQualifiedName = DBUtils.getFullyQualifiedName(dataSource, "Names", null);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Names", true, false);
    assertEquals("42", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}.
   *
   * <ul>
   *   <li>When {@code Names}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullyQualifiedName(DBPDataSource, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullyQualifiedName(DBPDataSource, String[])"})
  public void testGetFullyQualifiedName_whenNames_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFullyQualifiedName = DBUtils.getFullyQualifiedName(dataSource, "Names");

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Names", true, false);
    assertEquals("42", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBCExecutionContext}.
   *   <li>Then return {@link DBVModel}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DBUtils.getObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, String, String, String)"
  })
  public void testGetObjectByPath_given42_whenDBCExecutionContext_thenReturnDBVModel()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBSObject actualObjectByPath =
        DBUtils.getObjectByPath(
            monitor,
            executionContext,
            new DBVModel(dataSourceContainer),
            "Catalog Name",
            null,
            null);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualObjectByPath instanceof DBVModel);
    assertEquals("42", actualObjectByPath.toString());
    assertEquals("42", actualObjectByPath.getName());
    assertEquals("42", ((DBVModel) actualObjectByPath).getId());
    assertEquals("container", ((DBVModel) actualObjectByPath).getType());
    assertNull(actualObjectByPath.getDescription());
    assertNull(actualObjectByPath.getDataSource());
    assertNull(((DBVModel) actualObjectByPath).getProject());
    assertNull(actualObjectByPath.getParentObject());
    assertNull(((DBVModel) actualObjectByPath).getTransformSettings());
    assertFalse(((DBVModel) actualObjectByPath).hasValuableData());
    assertTrue(((DBVModel) actualObjectByPath).getContainers().isEmpty());
    assertTrue(((DBVModel) actualObjectByPath).getEntities().isEmpty());
    assertTrue(((DBVModel) actualObjectByPath).getProperties().isEmpty());
    assertTrue(actualObjectByPath.isPersisted());
  }

  /**
   * Test {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@code Catalog Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DBUtils.getObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, String, String, String)"
  })
  public void testGetObjectByPath_givenNull_whenCatalogName_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBSObject actualObjectByPath =
        DBUtils.getObjectByPath(
            monitor,
            executionContext,
            new DBVModel(dataSourceContainer),
            "Catalog Name",
            "Schema Name",
            "Object Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(executionContext).getContextDefaults();
    assertNull(actualObjectByPath);
  }

  /**
   * Test {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@code Catalog Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DBUtils.getObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, String, String, String)"
  })
  public void testGetObjectByPath_givenNull_whenCatalogName_thenReturnNull2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBSObject actualObjectByPath =
        DBUtils.getObjectByPath(
            monitor,
            executionContext,
            new DBVModel(dataSourceContainer),
            "Catalog Name",
            null,
            "Object Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(executionContext).getContextDefaults();
    assertNull(actualObjectByPath);
  }

  /**
   * Test {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DBUtils.getObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, String, String, String)"
  })
  public void testGetObjectByPath_givenNull_whenEmptyString_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBSObject actualObjectByPath =
        DBUtils.getObjectByPath(
            monitor,
            executionContext,
            new DBVModel(dataSourceContainer),
            "",
            "Schema Name",
            "Object Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(executionContext).getContextDefaults();
    assertNull(actualObjectByPath);
  }

  /**
   * Test {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DBUtils.getObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, String, String, String)"
  })
  public void testGetObjectByPath_givenNull_whenNull_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBSObject actualObjectByPath =
        DBUtils.getObjectByPath(
            monitor,
            executionContext,
            new DBVModel(dataSourceContainer),
            null,
            "Schema Name",
            "Object Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(executionContext).getContextDefaults();
    assertNull(actualObjectByPath);
  }

  /**
   * Test {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}.
   *
   * <ul>
   *   <li>Given {@link VoidExecutionContextDefaults} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectByPath(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DBUtils.getObjectByPath(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, String, String, String)"
  })
  public void testGetObjectByPath_givenVoidExecutionContextDefaults() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    DBSObject actualObjectByPath =
        DBUtils.getObjectByPath(
            monitor,
            executionContext,
            new DBVModel(dataSourceContainer),
            "Catalog Name",
            "Schema Name",
            "Object Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(executionContext).getContextDefaults();
    assertNull(actualObjectByPath);
  }

  /**
   * Test {@link DBUtils#findObject(Collection, String, boolean)} with {@code Collection}, {@code
   * String}, {@code boolean}.
   *
   * <ul>
   *   <li>Then calls {@link DBPNamedObject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(Collection, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(Collection, String, boolean)"})
  public void testFindObjectWithCollectionStringBoolean_thenCallsGetName() {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    ArrayList<DBPNamedObject> theList = new ArrayList<>();
    theList.add(dbpNamedObject);

    // Act
    DBPNamedObject actualFindObjectResult = DBUtils.findObject(theList, "Object Name", true);

    // Assert
    verify(dbpNamedObject).getName();
    assertNull(actualFindObjectResult);
  }

  /**
   * Test {@link DBUtils#findObject(Collection, String, boolean)} with {@code Collection}, {@code
   * String}, {@code boolean}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(Collection, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(Collection, String, boolean)"})
  public void testFindObjectWithCollectionStringBoolean_whenArrayList_thenReturnNull() {
    // Arrange and Act
    DBPNamedObject actualFindObjectResult =
        DBUtils.findObject(new ArrayList<>(), "Object Name", true);

    // Assert
    assertNull(actualFindObjectResult);
  }

  /**
   * Test {@link DBUtils#findObject(Collection, String, boolean)} with {@code Collection}, {@code
   * String}, {@code boolean}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(Collection, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(Collection, String, boolean)"})
  public void testFindObjectWithCollectionStringBoolean_whenNull_thenReturnNull() {
    // Arrange and Act
    DBPNamedObject actualFindObjectResult = DBUtils.findObject(null, "Object Name", false);

    // Assert
    assertNull(actualFindObjectResult);
  }

  /**
   * Test {@link DBUtils#findObject(Collection, String)} with {@code Collection}, {@code String}.
   *
   * <ul>
   *   <li>Then calls {@link DBPNamedObject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(Collection, String)"})
  public void testFindObjectWithCollectionString_thenCallsGetName() {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    ArrayList<DBPNamedObject> theList = new ArrayList<>();
    theList.add(dbpNamedObject);

    // Act
    DBPNamedObject actualFindObjectResult = DBUtils.findObject(theList, "Object Name");

    // Assert
    verify(dbpNamedObject).getName();
    assertNull(actualFindObjectResult);
  }

  /**
   * Test {@link DBUtils#findObject(Collection, String)} with {@code Collection}, {@code String}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(Collection, String)"})
  public void testFindObjectWithCollectionString_whenArrayList_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.findObject(new ArrayList<>(), "Object Name"));
  }

  /**
   * Test {@link DBUtils#findObject(Collection, String)} with {@code Collection}, {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(Collection, String)"})
  public void testFindObjectWithCollectionString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.findObject((Collection<DBPNamedObject>) null, "Object Name"));
  }

  /**
   * Test {@link DBUtils#findObject(DBPNamedObject[], String)} with {@code DBPNamedObject[]}, {@code
   * String}.
   *
   * <p>Method under test: {@link DBUtils#findObject(DBPNamedObject[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(DBPNamedObject[], String)"})
  public void testFindObjectWithDBPNamedObjectString() {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    // Act
    DBPNamedObject actualFindObjectResult =
        DBUtils.findObject(new DBPNamedObject[] {dbpNamedObject}, "Object Name");

    // Assert
    verify(dbpNamedObject).getName();
    assertNull(actualFindObjectResult);
  }

  /**
   * Test {@link DBUtils#findObject(DBPNamedObject[], String)} with {@code DBPNamedObject[]}, {@code
   * String}.
   *
   * <ul>
   *   <li>Given {@code Object Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(DBPNamedObject[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(DBPNamedObject[], String)"})
  public void testFindObjectWithDBPNamedObjectString_givenObjectName() {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Object Name");

    // Act
    DBUtils.findObject(
        new DBPNamedObject[] {
          dbpNamedObject, mock(DBPNamedObject.class), mock(DBPNamedObject.class)
        },
        "Object Name");

    // Assert
    verify(dbpNamedObject).getName();
  }

  /**
   * Test {@link DBUtils#findObject(DBPNamedObject[], String)} with {@code DBPNamedObject[]}, {@code
   * String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObject(DBPNamedObject[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPNamedObject DBUtils.findObject(DBPNamedObject[], String)"})
  public void testFindObjectWithDBPNamedObjectString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.findObject((DBPNamedObject[]) null, "Object Name"));
  }

  /**
   * Test {@link DBUtils#findObjects(Collection, String)}.
   *
   * <ul>
   *   <li>Given {@link DBPNamedObject} {@link DBPNamedObject#getName()} return {@code Name}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObjects(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.findObjects(Collection, String)"})
  public void testFindObjects_givenDBPNamedObjectGetNameReturnName_thenReturnEmpty() {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    ArrayList<DBPNamedObject> theList = new ArrayList<>();
    theList.add(dbpNamedObject);

    // Act
    List<DBPNamedObject> actualFindObjectsResult = DBUtils.findObjects(theList, "Object Name");

    // Assert
    verify(dbpNamedObject).getName();
    assertTrue(actualFindObjectsResult.isEmpty());
  }

  /**
   * Test {@link DBUtils#findObjects(Collection, String)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObjects(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.findObjects(Collection, String)"})
  public void testFindObjects_whenArrayList_thenReturnNull() {
    // Arrange and Act
    List<DBPNamedObject> actualFindObjectsResult =
        DBUtils.findObjects(new ArrayList<>(), "Object Name");

    // Assert
    assertNull(actualFindObjectsResult);
  }

  /**
   * Test {@link DBUtils#findObjects(Collection, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObjects(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.findObjects(Collection, String)"})
  public void testFindObjects_whenNull_thenReturnNull() {
    // Arrange and Act
    List<DBPNamedObject> actualFindObjectsResult = DBUtils.findObjects(null, "Object Name");

    // Assert
    assertNull(actualFindObjectsResult);
  }

  /**
   * Test {@link DBUtils#getAdapter(Class, Object)}.
   *
   * <ul>
   *   <li>When {@code Comparable}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAdapter(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBUtils.getAdapter(Class, Object)"})
  public void testGetAdapter_whenJavaLangComparable_thenReturnNull() {
    // Arrange
    Class<Comparable> adapterType = Comparable.class;

    // Act and Assert
    assertNull(DBUtils.getAdapter(adapterType, DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#getAdapter(Class, Object)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAdapter(Class, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBUtils.getAdapter(Class, Object)"})
  public void testGetAdapter_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Class<Object> adapterType = Object.class;

    // Act and Assert
    assertNull(DBUtils.getAdapter(adapterType, null));
  }

  /**
   * Test {@link DBUtils#getObjectFullId(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullId(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getObjectFullId(DBSObject)"})
  public void testGetObjectFullId_givenDBPDataSourceContainerGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getName()).thenReturn("Name");
    when(entity.getParentObject()).thenReturn(dbvModel);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getName()).thenReturn("Name");
    when(entity2.getParentObject()).thenReturn(dbsDocumentConstraint);
    DBSDocumentConstraint dbsDocumentConstraint2 = new DBSDocumentConstraint(entity2);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getName()).thenReturn("Name");
    when(entity3.getParentObject()).thenReturn(dbsDocumentConstraint2);
    DBSDocumentConstraint dbsDocumentConstraint3 = new DBSDocumentConstraint(entity3);

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getName()).thenReturn("Name");
    when(entity4.getParentObject()).thenReturn(dbsDocumentConstraint3);
    DBSDocumentConstraint dbsDocumentConstraint4 = new DBSDocumentConstraint(entity4);

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getName()).thenReturn("Name");
    when(entity5.getParentObject()).thenReturn(dbsDocumentConstraint4);
    DBSDocumentConstraint dbsDocumentConstraint5 = new DBSDocumentConstraint(entity5);

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getName()).thenReturn("Name");
    when(entity6.getParentObject()).thenReturn(dbsDocumentConstraint5);
    DBSDocumentConstraint dbsDocumentConstraint6 = new DBSDocumentConstraint(entity6);

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getName()).thenReturn("Name");
    when(entity7.getParentObject()).thenReturn(dbsDocumentConstraint6);
    DBSDocumentConstraint dbsDocumentConstraint7 = new DBSDocumentConstraint(entity7);

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getName()).thenReturn("Name");
    when(entity8.getParentObject()).thenReturn(dbsDocumentConstraint7);
    DBSDocumentConstraint dbsDocumentConstraint8 = new DBSDocumentConstraint(entity8);

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getName()).thenReturn("Name");
    when(entity9.getParentObject()).thenReturn(dbsDocumentConstraint8);
    DBSDocumentConstraint dbsDocumentConstraint9 = new DBSDocumentConstraint(entity9);

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getName()).thenReturn("Name");
    when(entity10.getParentObject()).thenReturn(dbsDocumentConstraint9);
    DBSDocumentConstraint dbsDocumentConstraint10 = new DBSDocumentConstraint(entity10);

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getName()).thenReturn("Name");
    when(entity11.getParentObject()).thenReturn(dbsDocumentConstraint10);
    DBSDocumentConstraint dbsDocumentConstraint11 = new DBSDocumentConstraint(entity11);

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getName()).thenReturn("Name");
    when(entity12.getParentObject()).thenReturn(dbsDocumentConstraint11);
    DBSDocumentConstraint dbsDocumentConstraint12 = new DBSDocumentConstraint(entity12);

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getName()).thenReturn("Name");
    when(entity13.getParentObject()).thenReturn(dbsDocumentConstraint12);
    DBSDocumentConstraint dbsDocumentConstraint13 = new DBSDocumentConstraint(entity13);

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getName()).thenReturn("Name");
    when(entity14.getParentObject()).thenReturn(dbsDocumentConstraint13);
    DBSDocumentConstraint dbsDocumentConstraint14 = new DBSDocumentConstraint(entity14);

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getName()).thenReturn("Name");
    when(entity15.getParentObject()).thenReturn(dbsDocumentConstraint14);
    DBSDocumentConstraint dbsDocumentConstraint15 = new DBSDocumentConstraint(entity15);

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getName()).thenReturn("Name");
    when(entity16.getParentObject()).thenReturn(dbsDocumentConstraint15);
    DBSDocumentConstraint dbsDocumentConstraint16 = new DBSDocumentConstraint(entity16);

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getName()).thenReturn("Name");
    when(entity17.getParentObject()).thenReturn(dbsDocumentConstraint16);
    DBSDocumentConstraint dbsDocumentConstraint17 = new DBSDocumentConstraint(entity17);

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getName()).thenReturn("Name");
    when(entity18.getParentObject()).thenReturn(dbsDocumentConstraint17);
    DBSDocumentConstraint dbsDocumentConstraint18 = new DBSDocumentConstraint(entity18);

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getName()).thenReturn("Name");
    when(entity19.getParentObject()).thenReturn(dbsDocumentConstraint18);
    DBSDocumentConstraint dbsDocumentConstraint19 = new DBSDocumentConstraint(entity19);

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getName()).thenReturn("Name");
    when(entity20.getParentObject()).thenReturn(dbsDocumentConstraint19);
    DBSDocumentConstraint dbsDocumentConstraint20 = new DBSDocumentConstraint(entity20);

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getName()).thenReturn("Name");
    when(entity21.getParentObject()).thenReturn(dbsDocumentConstraint20);
    DBSDocumentConstraint dbsDocumentConstraint21 = new DBSDocumentConstraint(entity21);

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getName()).thenReturn("Name");
    when(entity22.getParentObject()).thenReturn(dbsDocumentConstraint21);
    DBSDocumentConstraint dbsDocumentConstraint22 = new DBSDocumentConstraint(entity22);

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getName()).thenReturn("Name");
    when(entity23.getParentObject()).thenReturn(dbsDocumentConstraint22);
    DBSDocumentConstraint dbsDocumentConstraint23 = new DBSDocumentConstraint(entity23);

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getName()).thenReturn("Name");
    when(entity24.getParentObject()).thenReturn(dbsDocumentConstraint23);
    DBSDocumentConstraint dbsDocumentConstraint24 = new DBSDocumentConstraint(entity24);

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getName()).thenReturn("Name");
    when(entity25.getParentObject()).thenReturn(dbsDocumentConstraint24);
    DBSDocumentConstraint dbsDocumentConstraint25 = new DBSDocumentConstraint(entity25);

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getName()).thenReturn("Name");
    when(entity26.getParentObject()).thenReturn(dbsDocumentConstraint25);
    DBSDocumentConstraint dbsDocumentConstraint26 = new DBSDocumentConstraint(entity26);

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getName()).thenReturn("Name");
    when(entity27.getParentObject()).thenReturn(dbsDocumentConstraint26);
    DBSDocumentConstraint dbsDocumentConstraint27 = new DBSDocumentConstraint(entity27);

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getName()).thenReturn("Name");
    when(entity28.getParentObject()).thenReturn(dbsDocumentConstraint27);
    DBSDocumentConstraint dbsDocumentConstraint28 = new DBSDocumentConstraint(entity28);

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getName()).thenReturn("Name");
    when(entity29.getParentObject()).thenReturn(dbsDocumentConstraint28);
    DBSDocumentConstraint dbsDocumentConstraint29 = new DBSDocumentConstraint(entity29);

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getName()).thenReturn("Name");
    when(entity30.getParentObject()).thenReturn(dbsDocumentConstraint29);
    DBSDocumentConstraint dbsDocumentConstraint30 = new DBSDocumentConstraint(entity30);

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getName()).thenReturn("Name");
    when(entity31.getParentObject()).thenReturn(dbsDocumentConstraint30);
    DBSDocumentConstraint dbsDocumentConstraint31 = new DBSDocumentConstraint(entity31);

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getName()).thenReturn("Name");
    when(entity32.getParentObject()).thenReturn(dbsDocumentConstraint31);
    DBSDocumentConstraint dbsDocumentConstraint32 = new DBSDocumentConstraint(entity32);

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getName()).thenReturn("Name");
    when(entity33.getParentObject()).thenReturn(dbsDocumentConstraint32);
    DBSDocumentConstraint dbsDocumentConstraint33 = new DBSDocumentConstraint(entity33);

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getName()).thenReturn("Name");
    when(entity34.getParentObject()).thenReturn(dbsDocumentConstraint33);
    DBSDocumentConstraint dbsDocumentConstraint34 = new DBSDocumentConstraint(entity34);

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getName()).thenReturn("Name");
    when(entity35.getParentObject()).thenReturn(dbsDocumentConstraint34);
    DBSDocumentConstraint dbsDocumentConstraint35 = new DBSDocumentConstraint(entity35);

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getName()).thenReturn("Name");
    when(entity36.getParentObject()).thenReturn(dbsDocumentConstraint35);
    DBSDocumentConstraint dbsDocumentConstraint36 = new DBSDocumentConstraint(entity36);

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getName()).thenReturn("Name");
    when(entity37.getParentObject()).thenReturn(dbsDocumentConstraint36);
    DBSDocumentConstraint dbsDocumentConstraint37 = new DBSDocumentConstraint(entity37);

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getName()).thenReturn("Name");
    when(entity38.getParentObject()).thenReturn(dbsDocumentConstraint37);
    DBSDocumentConstraint dbsDocumentConstraint38 = new DBSDocumentConstraint(entity38);

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getName()).thenReturn("Name");
    when(entity39.getParentObject()).thenReturn(dbsDocumentConstraint38);
    DBSDocumentConstraint dbsDocumentConstraint39 = new DBSDocumentConstraint(entity39);

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getName()).thenReturn("Name");
    when(entity40.getParentObject()).thenReturn(dbsDocumentConstraint39);
    DBSDocumentConstraint dbsDocumentConstraint40 = new DBSDocumentConstraint(entity40);

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getName()).thenReturn("Name");
    when(entity41.getParentObject()).thenReturn(dbsDocumentConstraint40);
    DBSDocumentConstraint dbsDocumentConstraint41 = new DBSDocumentConstraint(entity41);

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getName()).thenReturn("Name");
    when(entity42.getParentObject()).thenReturn(dbsDocumentConstraint41);
    DBSDocumentConstraint dbsDocumentConstraint42 = new DBSDocumentConstraint(entity42);

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getName()).thenReturn("Name");
    when(entity43.getParentObject()).thenReturn(dbsDocumentConstraint42);
    DBSDocumentConstraint dbsDocumentConstraint43 = new DBSDocumentConstraint(entity43);

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getName()).thenReturn("Name");
    when(entity44.getParentObject()).thenReturn(dbsDocumentConstraint43);
    DBSDocumentConstraint dbsDocumentConstraint44 = new DBSDocumentConstraint(entity44);

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getName()).thenReturn("Name");
    when(entity45.getParentObject()).thenReturn(dbsDocumentConstraint44);
    DBSDocumentConstraint dbsDocumentConstraint45 = new DBSDocumentConstraint(entity45);

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getName()).thenReturn("Name");
    when(entity46.getParentObject()).thenReturn(dbsDocumentConstraint45);
    DBSDocumentConstraint dbsDocumentConstraint46 = new DBSDocumentConstraint(entity46);

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getName()).thenReturn("Name");
    when(entity47.getParentObject()).thenReturn(dbsDocumentConstraint46);
    DBSDocumentConstraint dbsDocumentConstraint47 = new DBSDocumentConstraint(entity47);

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getName()).thenReturn("Name");
    when(entity48.getParentObject()).thenReturn(dbsDocumentConstraint47);
    DBSDocumentConstraint dbsDocumentConstraint48 = new DBSDocumentConstraint(entity48);

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getName()).thenReturn("Name");
    when(entity49.getParentObject()).thenReturn(dbsDocumentConstraint48);
    DBSDocumentConstraint dbsDocumentConstraint49 = new DBSDocumentConstraint(entity49);

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getName()).thenReturn("Name");
    when(entity50.getParentObject()).thenReturn(dbsDocumentConstraint49);
    DBSDocumentConstraint dbsDocumentConstraint50 = new DBSDocumentConstraint(entity50);

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getName()).thenReturn("Name");
    when(entity51.getParentObject()).thenReturn(dbsDocumentConstraint50);
    DBSDocumentConstraint dbsDocumentConstraint51 = new DBSDocumentConstraint(entity51);

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getName()).thenReturn("Name");
    when(entity52.getParentObject()).thenReturn(dbsDocumentConstraint51);
    DBSDocumentConstraint dbsDocumentConstraint52 = new DBSDocumentConstraint(entity52);

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getName()).thenReturn("Name");
    when(entity53.getParentObject()).thenReturn(dbsDocumentConstraint52);
    DBSDocumentConstraint dbsDocumentConstraint53 = new DBSDocumentConstraint(entity53);

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getName()).thenReturn("Name");
    when(entity54.getParentObject()).thenReturn(dbsDocumentConstraint53);
    DBSDocumentConstraint dbsDocumentConstraint54 = new DBSDocumentConstraint(entity54);

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getName()).thenReturn("Name");
    when(entity55.getParentObject()).thenReturn(dbsDocumentConstraint54);
    DBSDocumentConstraint dbsDocumentConstraint55 = new DBSDocumentConstraint(entity55);

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getName()).thenReturn("Name");
    when(entity56.getParentObject()).thenReturn(dbsDocumentConstraint55);
    DBSDocumentConstraint dbsDocumentConstraint56 = new DBSDocumentConstraint(entity56);

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getName()).thenReturn("Name");
    when(entity57.getParentObject()).thenReturn(dbsDocumentConstraint56);
    DBSDocumentConstraint dbsDocumentConstraint57 = new DBSDocumentConstraint(entity57);

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getName()).thenReturn("Name");
    when(entity58.getParentObject()).thenReturn(dbsDocumentConstraint57);
    DBSDocumentConstraint dbsDocumentConstraint58 = new DBSDocumentConstraint(entity58);

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getName()).thenReturn("Name");
    when(entity59.getParentObject()).thenReturn(dbsDocumentConstraint58);
    DBSDocumentConstraint dbsDocumentConstraint59 = new DBSDocumentConstraint(entity59);

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getName()).thenReturn("Name");
    when(entity60.getParentObject()).thenReturn(dbsDocumentConstraint59);
    DBSDocumentConstraint dbsDocumentConstraint60 = new DBSDocumentConstraint(entity60);

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getName()).thenReturn("Name");
    when(entity61.getParentObject()).thenReturn(dbsDocumentConstraint60);
    DBSDocumentConstraint dbsDocumentConstraint61 = new DBSDocumentConstraint(entity61);

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getName()).thenReturn("Name");
    when(entity62.getParentObject()).thenReturn(dbsDocumentConstraint61);
    DBSDocumentConstraint dbsDocumentConstraint62 = new DBSDocumentConstraint(entity62);

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getName()).thenReturn("Name");
    when(entity63.getParentObject()).thenReturn(dbsDocumentConstraint62);
    DBSDocumentConstraint dbsDocumentConstraint63 = new DBSDocumentConstraint(entity63);

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getName()).thenReturn("Name");
    when(entity64.getParentObject()).thenReturn(dbsDocumentConstraint63);
    DBSDocumentConstraint dbsDocumentConstraint64 = new DBSDocumentConstraint(entity64);

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getName()).thenReturn("Name");
    when(entity65.getParentObject()).thenReturn(dbsDocumentConstraint64);
    DBSDocumentConstraint dbsDocumentConstraint65 = new DBSDocumentConstraint(entity65);

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getName()).thenReturn("Name");
    when(entity66.getParentObject()).thenReturn(dbsDocumentConstraint65);
    DBSDocumentConstraint dbsDocumentConstraint66 = new DBSDocumentConstraint(entity66);

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getName()).thenReturn("Name");
    when(entity67.getParentObject()).thenReturn(dbsDocumentConstraint66);
    DBSDocumentConstraint dbsDocumentConstraint67 = new DBSDocumentConstraint(entity67);

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getName()).thenReturn("Name");
    when(entity68.getParentObject()).thenReturn(dbsDocumentConstraint67);
    DBSDocumentConstraint dbsDocumentConstraint68 = new DBSDocumentConstraint(entity68);

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getName()).thenReturn("Name");
    when(entity69.getParentObject()).thenReturn(dbsDocumentConstraint68);
    DBSDocumentConstraint dbsDocumentConstraint69 = new DBSDocumentConstraint(entity69);

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getName()).thenReturn("Name");
    when(entity70.getParentObject()).thenReturn(dbsDocumentConstraint69);
    DBSDocumentConstraint dbsDocumentConstraint70 = new DBSDocumentConstraint(entity70);

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getName()).thenReturn("Name");
    when(entity71.getParentObject()).thenReturn(dbsDocumentConstraint70);
    DBSDocumentConstraint dbsDocumentConstraint71 = new DBSDocumentConstraint(entity71);

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getName()).thenReturn("Name");
    when(entity72.getParentObject()).thenReturn(dbsDocumentConstraint71);
    DBSDocumentConstraint dbsDocumentConstraint72 = new DBSDocumentConstraint(entity72);

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getName()).thenReturn("Name");
    when(entity73.getParentObject()).thenReturn(dbsDocumentConstraint72);
    DBSDocumentConstraint dbsDocumentConstraint73 = new DBSDocumentConstraint(entity73);

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getName()).thenReturn("Name");
    when(entity74.getParentObject()).thenReturn(dbsDocumentConstraint73);
    DBSDocumentConstraint dbsDocumentConstraint74 = new DBSDocumentConstraint(entity74);

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getName()).thenReturn("Name");
    when(entity75.getParentObject()).thenReturn(dbsDocumentConstraint74);
    DBSDocumentConstraint dbsDocumentConstraint75 = new DBSDocumentConstraint(entity75);

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getName()).thenReturn("Name");
    when(entity76.getParentObject()).thenReturn(dbsDocumentConstraint75);
    DBSDocumentConstraint dbsDocumentConstraint76 = new DBSDocumentConstraint(entity76);

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getName()).thenReturn("Name");
    when(entity77.getParentObject()).thenReturn(dbsDocumentConstraint76);
    DBSDocumentConstraint dbsDocumentConstraint77 = new DBSDocumentConstraint(entity77);

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getName()).thenReturn("Name");
    when(entity78.getParentObject()).thenReturn(dbsDocumentConstraint77);
    DBSDocumentConstraint dbsDocumentConstraint78 = new DBSDocumentConstraint(entity78);

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getName()).thenReturn("Name");
    when(entity79.getParentObject()).thenReturn(dbsDocumentConstraint78);

    // Act
    String actualObjectFullId = DBUtils.getObjectFullId(new DBSDocumentConstraint(entity79));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(entity79).getName();
    verify(entity78).getName();
    verify(entity77).getName();
    verify(entity76).getName();
    verify(entity75).getName();
    verify(entity74).getName();
    verify(entity73).getName();
    verify(entity72).getName();
    verify(entity71).getName();
    verify(entity70).getName();
    verify(entity69).getName();
    verify(entity68).getName();
    verify(entity67).getName();
    verify(entity66).getName();
    verify(entity65).getName();
    verify(entity64).getName();
    verify(entity63).getName();
    verify(entity62).getName();
    verify(entity61).getName();
    verify(entity60).getName();
    verify(entity59).getName();
    verify(entity58).getName();
    verify(entity57).getName();
    verify(entity56).getName();
    verify(entity55).getName();
    verify(entity54).getName();
    verify(entity53).getName();
    verify(entity52).getName();
    verify(entity51).getName();
    verify(entity50).getName();
    verify(entity49).getName();
    verify(entity48).getName();
    verify(entity47).getName();
    verify(entity46).getName();
    verify(entity45).getName();
    verify(entity44).getName();
    verify(entity43).getName();
    verify(entity42).getName();
    verify(entity41).getName();
    verify(entity40).getName();
    verify(entity39).getName();
    verify(entity38).getName();
    verify(entity37).getName();
    verify(entity36).getName();
    verify(entity35).getName();
    verify(entity34).getName();
    verify(entity33).getName();
    verify(entity32).getName();
    verify(entity31).getName();
    verify(entity30).getName();
    verify(entity29).getName();
    verify(entity28).getName();
    verify(entity27).getName();
    verify(entity26).getName();
    verify(entity25).getName();
    verify(entity24).getName();
    verify(entity23).getName();
    verify(entity22).getName();
    verify(entity21).getName();
    verify(entity20).getName();
    verify(entity19).getName();
    verify(entity18).getName();
    verify(entity17).getName();
    verify(entity16).getName();
    verify(entity15).getName();
    verify(entity14).getName();
    verify(entity13).getName();
    verify(entity12).getName();
    verify(entity11).getName();
    verify(entity10).getName();
    verify(entity9).getName();
    verify(entity8).getName();
    verify(entity7).getName();
    verify(entity6).getName();
    verify(entity5).getName();
    verify(entity4).getName();
    verify(entity3).getName();
    verify(entity2).getName();
    verify(entity).getName();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    assertEquals(
        "42/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey",
        actualObjectFullId);
  }

  /**
   * Test {@link DBUtils#getObjectFullId(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link DBSDocumentContainer} {@link DBSDocumentContainer#getParentObject()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullId(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getObjectFullId(DBSObject)"})
  public void testGetObjectFullId_givenDBSDocumentContainerGetParentObjectReturnNull() {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getName()).thenReturn("Name");
    when(entity.getParentObject()).thenReturn(null);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getName()).thenReturn("Name");
    when(entity2.getParentObject()).thenReturn(dbsDocumentConstraint);
    DBSDocumentConstraint dbsDocumentConstraint2 = new DBSDocumentConstraint(entity2);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getName()).thenReturn("Name");
    when(entity3.getParentObject()).thenReturn(dbsDocumentConstraint2);
    DBSDocumentConstraint dbsDocumentConstraint3 = new DBSDocumentConstraint(entity3);

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getName()).thenReturn("Name");
    when(entity4.getParentObject()).thenReturn(dbsDocumentConstraint3);
    DBSDocumentConstraint dbsDocumentConstraint4 = new DBSDocumentConstraint(entity4);

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getName()).thenReturn("Name");
    when(entity5.getParentObject()).thenReturn(dbsDocumentConstraint4);
    DBSDocumentConstraint dbsDocumentConstraint5 = new DBSDocumentConstraint(entity5);

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getName()).thenReturn("Name");
    when(entity6.getParentObject()).thenReturn(dbsDocumentConstraint5);
    DBSDocumentConstraint dbsDocumentConstraint6 = new DBSDocumentConstraint(entity6);

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getName()).thenReturn("Name");
    when(entity7.getParentObject()).thenReturn(dbsDocumentConstraint6);
    DBSDocumentConstraint dbsDocumentConstraint7 = new DBSDocumentConstraint(entity7);

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getName()).thenReturn("Name");
    when(entity8.getParentObject()).thenReturn(dbsDocumentConstraint7);
    DBSDocumentConstraint dbsDocumentConstraint8 = new DBSDocumentConstraint(entity8);

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getName()).thenReturn("Name");
    when(entity9.getParentObject()).thenReturn(dbsDocumentConstraint8);
    DBSDocumentConstraint dbsDocumentConstraint9 = new DBSDocumentConstraint(entity9);

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getName()).thenReturn("Name");
    when(entity10.getParentObject()).thenReturn(dbsDocumentConstraint9);
    DBSDocumentConstraint dbsDocumentConstraint10 = new DBSDocumentConstraint(entity10);

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getName()).thenReturn("Name");
    when(entity11.getParentObject()).thenReturn(dbsDocumentConstraint10);
    DBSDocumentConstraint dbsDocumentConstraint11 = new DBSDocumentConstraint(entity11);

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getName()).thenReturn("Name");
    when(entity12.getParentObject()).thenReturn(dbsDocumentConstraint11);
    DBSDocumentConstraint dbsDocumentConstraint12 = new DBSDocumentConstraint(entity12);

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getName()).thenReturn("Name");
    when(entity13.getParentObject()).thenReturn(dbsDocumentConstraint12);
    DBSDocumentConstraint dbsDocumentConstraint13 = new DBSDocumentConstraint(entity13);

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getName()).thenReturn("Name");
    when(entity14.getParentObject()).thenReturn(dbsDocumentConstraint13);
    DBSDocumentConstraint dbsDocumentConstraint14 = new DBSDocumentConstraint(entity14);

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getName()).thenReturn("Name");
    when(entity15.getParentObject()).thenReturn(dbsDocumentConstraint14);
    DBSDocumentConstraint dbsDocumentConstraint15 = new DBSDocumentConstraint(entity15);

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getName()).thenReturn("Name");
    when(entity16.getParentObject()).thenReturn(dbsDocumentConstraint15);
    DBSDocumentConstraint dbsDocumentConstraint16 = new DBSDocumentConstraint(entity16);

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getName()).thenReturn("Name");
    when(entity17.getParentObject()).thenReturn(dbsDocumentConstraint16);
    DBSDocumentConstraint dbsDocumentConstraint17 = new DBSDocumentConstraint(entity17);

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getName()).thenReturn("Name");
    when(entity18.getParentObject()).thenReturn(dbsDocumentConstraint17);
    DBSDocumentConstraint dbsDocumentConstraint18 = new DBSDocumentConstraint(entity18);

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getName()).thenReturn("Name");
    when(entity19.getParentObject()).thenReturn(dbsDocumentConstraint18);
    DBSDocumentConstraint dbsDocumentConstraint19 = new DBSDocumentConstraint(entity19);

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getName()).thenReturn("Name");
    when(entity20.getParentObject()).thenReturn(dbsDocumentConstraint19);
    DBSDocumentConstraint dbsDocumentConstraint20 = new DBSDocumentConstraint(entity20);

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getName()).thenReturn("Name");
    when(entity21.getParentObject()).thenReturn(dbsDocumentConstraint20);
    DBSDocumentConstraint dbsDocumentConstraint21 = new DBSDocumentConstraint(entity21);

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getName()).thenReturn("Name");
    when(entity22.getParentObject()).thenReturn(dbsDocumentConstraint21);
    DBSDocumentConstraint dbsDocumentConstraint22 = new DBSDocumentConstraint(entity22);

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getName()).thenReturn("Name");
    when(entity23.getParentObject()).thenReturn(dbsDocumentConstraint22);
    DBSDocumentConstraint dbsDocumentConstraint23 = new DBSDocumentConstraint(entity23);

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getName()).thenReturn("Name");
    when(entity24.getParentObject()).thenReturn(dbsDocumentConstraint23);
    DBSDocumentConstraint dbsDocumentConstraint24 = new DBSDocumentConstraint(entity24);

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getName()).thenReturn("Name");
    when(entity25.getParentObject()).thenReturn(dbsDocumentConstraint24);
    DBSDocumentConstraint dbsDocumentConstraint25 = new DBSDocumentConstraint(entity25);

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getName()).thenReturn("Name");
    when(entity26.getParentObject()).thenReturn(dbsDocumentConstraint25);
    DBSDocumentConstraint dbsDocumentConstraint26 = new DBSDocumentConstraint(entity26);

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getName()).thenReturn("Name");
    when(entity27.getParentObject()).thenReturn(dbsDocumentConstraint26);
    DBSDocumentConstraint dbsDocumentConstraint27 = new DBSDocumentConstraint(entity27);

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getName()).thenReturn("Name");
    when(entity28.getParentObject()).thenReturn(dbsDocumentConstraint27);
    DBSDocumentConstraint dbsDocumentConstraint28 = new DBSDocumentConstraint(entity28);

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getName()).thenReturn("Name");
    when(entity29.getParentObject()).thenReturn(dbsDocumentConstraint28);
    DBSDocumentConstraint dbsDocumentConstraint29 = new DBSDocumentConstraint(entity29);

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getName()).thenReturn("Name");
    when(entity30.getParentObject()).thenReturn(dbsDocumentConstraint29);
    DBSDocumentConstraint dbsDocumentConstraint30 = new DBSDocumentConstraint(entity30);

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getName()).thenReturn("Name");
    when(entity31.getParentObject()).thenReturn(dbsDocumentConstraint30);
    DBSDocumentConstraint dbsDocumentConstraint31 = new DBSDocumentConstraint(entity31);

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getName()).thenReturn("Name");
    when(entity32.getParentObject()).thenReturn(dbsDocumentConstraint31);
    DBSDocumentConstraint dbsDocumentConstraint32 = new DBSDocumentConstraint(entity32);

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getName()).thenReturn("Name");
    when(entity33.getParentObject()).thenReturn(dbsDocumentConstraint32);
    DBSDocumentConstraint dbsDocumentConstraint33 = new DBSDocumentConstraint(entity33);

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getName()).thenReturn("Name");
    when(entity34.getParentObject()).thenReturn(dbsDocumentConstraint33);
    DBSDocumentConstraint dbsDocumentConstraint34 = new DBSDocumentConstraint(entity34);

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getName()).thenReturn("Name");
    when(entity35.getParentObject()).thenReturn(dbsDocumentConstraint34);
    DBSDocumentConstraint dbsDocumentConstraint35 = new DBSDocumentConstraint(entity35);

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getName()).thenReturn("Name");
    when(entity36.getParentObject()).thenReturn(dbsDocumentConstraint35);
    DBSDocumentConstraint dbsDocumentConstraint36 = new DBSDocumentConstraint(entity36);

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getName()).thenReturn("Name");
    when(entity37.getParentObject()).thenReturn(dbsDocumentConstraint36);
    DBSDocumentConstraint dbsDocumentConstraint37 = new DBSDocumentConstraint(entity37);

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getName()).thenReturn("Name");
    when(entity38.getParentObject()).thenReturn(dbsDocumentConstraint37);
    DBSDocumentConstraint dbsDocumentConstraint38 = new DBSDocumentConstraint(entity38);

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getName()).thenReturn("Name");
    when(entity39.getParentObject()).thenReturn(dbsDocumentConstraint38);
    DBSDocumentConstraint dbsDocumentConstraint39 = new DBSDocumentConstraint(entity39);

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getName()).thenReturn("Name");
    when(entity40.getParentObject()).thenReturn(dbsDocumentConstraint39);
    DBSDocumentConstraint dbsDocumentConstraint40 = new DBSDocumentConstraint(entity40);

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getName()).thenReturn("Name");
    when(entity41.getParentObject()).thenReturn(dbsDocumentConstraint40);
    DBSDocumentConstraint dbsDocumentConstraint41 = new DBSDocumentConstraint(entity41);

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getName()).thenReturn("Name");
    when(entity42.getParentObject()).thenReturn(dbsDocumentConstraint41);
    DBSDocumentConstraint dbsDocumentConstraint42 = new DBSDocumentConstraint(entity42);

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getName()).thenReturn("Name");
    when(entity43.getParentObject()).thenReturn(dbsDocumentConstraint42);
    DBSDocumentConstraint dbsDocumentConstraint43 = new DBSDocumentConstraint(entity43);

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getName()).thenReturn("Name");
    when(entity44.getParentObject()).thenReturn(dbsDocumentConstraint43);
    DBSDocumentConstraint dbsDocumentConstraint44 = new DBSDocumentConstraint(entity44);

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getName()).thenReturn("Name");
    when(entity45.getParentObject()).thenReturn(dbsDocumentConstraint44);
    DBSDocumentConstraint dbsDocumentConstraint45 = new DBSDocumentConstraint(entity45);

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getName()).thenReturn("Name");
    when(entity46.getParentObject()).thenReturn(dbsDocumentConstraint45);
    DBSDocumentConstraint dbsDocumentConstraint46 = new DBSDocumentConstraint(entity46);

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getName()).thenReturn("Name");
    when(entity47.getParentObject()).thenReturn(dbsDocumentConstraint46);
    DBSDocumentConstraint dbsDocumentConstraint47 = new DBSDocumentConstraint(entity47);

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getName()).thenReturn("Name");
    when(entity48.getParentObject()).thenReturn(dbsDocumentConstraint47);
    DBSDocumentConstraint dbsDocumentConstraint48 = new DBSDocumentConstraint(entity48);

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getName()).thenReturn("Name");
    when(entity49.getParentObject()).thenReturn(dbsDocumentConstraint48);
    DBSDocumentConstraint dbsDocumentConstraint49 = new DBSDocumentConstraint(entity49);

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getName()).thenReturn("Name");
    when(entity50.getParentObject()).thenReturn(dbsDocumentConstraint49);
    DBSDocumentConstraint dbsDocumentConstraint50 = new DBSDocumentConstraint(entity50);

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getName()).thenReturn("Name");
    when(entity51.getParentObject()).thenReturn(dbsDocumentConstraint50);
    DBSDocumentConstraint dbsDocumentConstraint51 = new DBSDocumentConstraint(entity51);

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getName()).thenReturn("Name");
    when(entity52.getParentObject()).thenReturn(dbsDocumentConstraint51);
    DBSDocumentConstraint dbsDocumentConstraint52 = new DBSDocumentConstraint(entity52);

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getName()).thenReturn("Name");
    when(entity53.getParentObject()).thenReturn(dbsDocumentConstraint52);
    DBSDocumentConstraint dbsDocumentConstraint53 = new DBSDocumentConstraint(entity53);

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getName()).thenReturn("Name");
    when(entity54.getParentObject()).thenReturn(dbsDocumentConstraint53);
    DBSDocumentConstraint dbsDocumentConstraint54 = new DBSDocumentConstraint(entity54);

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getName()).thenReturn("Name");
    when(entity55.getParentObject()).thenReturn(dbsDocumentConstraint54);
    DBSDocumentConstraint dbsDocumentConstraint55 = new DBSDocumentConstraint(entity55);

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getName()).thenReturn("Name");
    when(entity56.getParentObject()).thenReturn(dbsDocumentConstraint55);
    DBSDocumentConstraint dbsDocumentConstraint56 = new DBSDocumentConstraint(entity56);

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getName()).thenReturn("Name");
    when(entity57.getParentObject()).thenReturn(dbsDocumentConstraint56);
    DBSDocumentConstraint dbsDocumentConstraint57 = new DBSDocumentConstraint(entity57);

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getName()).thenReturn("Name");
    when(entity58.getParentObject()).thenReturn(dbsDocumentConstraint57);
    DBSDocumentConstraint dbsDocumentConstraint58 = new DBSDocumentConstraint(entity58);

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getName()).thenReturn("Name");
    when(entity59.getParentObject()).thenReturn(dbsDocumentConstraint58);
    DBSDocumentConstraint dbsDocumentConstraint59 = new DBSDocumentConstraint(entity59);

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getName()).thenReturn("Name");
    when(entity60.getParentObject()).thenReturn(dbsDocumentConstraint59);
    DBSDocumentConstraint dbsDocumentConstraint60 = new DBSDocumentConstraint(entity60);

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getName()).thenReturn("Name");
    when(entity61.getParentObject()).thenReturn(dbsDocumentConstraint60);
    DBSDocumentConstraint dbsDocumentConstraint61 = new DBSDocumentConstraint(entity61);

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getName()).thenReturn("Name");
    when(entity62.getParentObject()).thenReturn(dbsDocumentConstraint61);
    DBSDocumentConstraint dbsDocumentConstraint62 = new DBSDocumentConstraint(entity62);

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getName()).thenReturn("Name");
    when(entity63.getParentObject()).thenReturn(dbsDocumentConstraint62);
    DBSDocumentConstraint dbsDocumentConstraint63 = new DBSDocumentConstraint(entity63);

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getName()).thenReturn("Name");
    when(entity64.getParentObject()).thenReturn(dbsDocumentConstraint63);
    DBSDocumentConstraint dbsDocumentConstraint64 = new DBSDocumentConstraint(entity64);

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getName()).thenReturn("Name");
    when(entity65.getParentObject()).thenReturn(dbsDocumentConstraint64);
    DBSDocumentConstraint dbsDocumentConstraint65 = new DBSDocumentConstraint(entity65);

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getName()).thenReturn("Name");
    when(entity66.getParentObject()).thenReturn(dbsDocumentConstraint65);
    DBSDocumentConstraint dbsDocumentConstraint66 = new DBSDocumentConstraint(entity66);

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getName()).thenReturn("Name");
    when(entity67.getParentObject()).thenReturn(dbsDocumentConstraint66);
    DBSDocumentConstraint dbsDocumentConstraint67 = new DBSDocumentConstraint(entity67);

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getName()).thenReturn("Name");
    when(entity68.getParentObject()).thenReturn(dbsDocumentConstraint67);
    DBSDocumentConstraint dbsDocumentConstraint68 = new DBSDocumentConstraint(entity68);

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getName()).thenReturn("Name");
    when(entity69.getParentObject()).thenReturn(dbsDocumentConstraint68);
    DBSDocumentConstraint dbsDocumentConstraint69 = new DBSDocumentConstraint(entity69);

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getName()).thenReturn("Name");
    when(entity70.getParentObject()).thenReturn(dbsDocumentConstraint69);
    DBSDocumentConstraint dbsDocumentConstraint70 = new DBSDocumentConstraint(entity70);

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getName()).thenReturn("Name");
    when(entity71.getParentObject()).thenReturn(dbsDocumentConstraint70);
    DBSDocumentConstraint dbsDocumentConstraint71 = new DBSDocumentConstraint(entity71);

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getName()).thenReturn("Name");
    when(entity72.getParentObject()).thenReturn(dbsDocumentConstraint71);
    DBSDocumentConstraint dbsDocumentConstraint72 = new DBSDocumentConstraint(entity72);

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getName()).thenReturn("Name");
    when(entity73.getParentObject()).thenReturn(dbsDocumentConstraint72);
    DBSDocumentConstraint dbsDocumentConstraint73 = new DBSDocumentConstraint(entity73);

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getName()).thenReturn("Name");
    when(entity74.getParentObject()).thenReturn(dbsDocumentConstraint73);
    DBSDocumentConstraint dbsDocumentConstraint74 = new DBSDocumentConstraint(entity74);

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getName()).thenReturn("Name");
    when(entity75.getParentObject()).thenReturn(dbsDocumentConstraint74);
    DBSDocumentConstraint dbsDocumentConstraint75 = new DBSDocumentConstraint(entity75);

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getName()).thenReturn("Name");
    when(entity76.getParentObject()).thenReturn(dbsDocumentConstraint75);
    DBSDocumentConstraint dbsDocumentConstraint76 = new DBSDocumentConstraint(entity76);

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getName()).thenReturn("Name");
    when(entity77.getParentObject()).thenReturn(dbsDocumentConstraint76);
    DBSDocumentConstraint dbsDocumentConstraint77 = new DBSDocumentConstraint(entity77);

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getName()).thenReturn("Name");
    when(entity78.getParentObject()).thenReturn(dbsDocumentConstraint77);
    DBSDocumentConstraint dbsDocumentConstraint78 = new DBSDocumentConstraint(entity78);

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getName()).thenReturn("Name");
    when(entity79.getParentObject()).thenReturn(dbsDocumentConstraint78);

    // Act
    String actualObjectFullId = DBUtils.getObjectFullId(new DBSDocumentConstraint(entity79));

    // Assert
    verify(entity79).getName();
    verify(entity78).getName();
    verify(entity77).getName();
    verify(entity76).getName();
    verify(entity75).getName();
    verify(entity74).getName();
    verify(entity73).getName();
    verify(entity72).getName();
    verify(entity71).getName();
    verify(entity70).getName();
    verify(entity69).getName();
    verify(entity68).getName();
    verify(entity67).getName();
    verify(entity66).getName();
    verify(entity65).getName();
    verify(entity64).getName();
    verify(entity63).getName();
    verify(entity62).getName();
    verify(entity61).getName();
    verify(entity60).getName();
    verify(entity59).getName();
    verify(entity58).getName();
    verify(entity57).getName();
    verify(entity56).getName();
    verify(entity55).getName();
    verify(entity54).getName();
    verify(entity53).getName();
    verify(entity52).getName();
    verify(entity51).getName();
    verify(entity50).getName();
    verify(entity49).getName();
    verify(entity48).getName();
    verify(entity47).getName();
    verify(entity46).getName();
    verify(entity45).getName();
    verify(entity44).getName();
    verify(entity43).getName();
    verify(entity42).getName();
    verify(entity41).getName();
    verify(entity40).getName();
    verify(entity39).getName();
    verify(entity38).getName();
    verify(entity37).getName();
    verify(entity36).getName();
    verify(entity35).getName();
    verify(entity34).getName();
    verify(entity33).getName();
    verify(entity32).getName();
    verify(entity31).getName();
    verify(entity30).getName();
    verify(entity29).getName();
    verify(entity28).getName();
    verify(entity27).getName();
    verify(entity26).getName();
    verify(entity25).getName();
    verify(entity24).getName();
    verify(entity23).getName();
    verify(entity22).getName();
    verify(entity21).getName();
    verify(entity20).getName();
    verify(entity19).getName();
    verify(entity18).getName();
    verify(entity17).getName();
    verify(entity16).getName();
    verify(entity15).getName();
    verify(entity14).getName();
    verify(entity13).getName();
    verify(entity12).getName();
    verify(entity11).getName();
    verify(entity10).getName();
    verify(entity9).getName();
    verify(entity8).getName();
    verify(entity7).getName();
    verify(entity6).getName();
    verify(entity5).getName();
    verify(entity4).getName();
    verify(entity3).getName();
    verify(entity2).getName();
    verify(entity).getName();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    assertEquals(
        "Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey/Name/DocumentKey"
            + "/Name/DocumentKey",
        actualObjectFullId);
  }

  /**
   * Test {@link DBUtils#getObjectNameFromId(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectNameFromId(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getObjectNameFromId(String)"})
  public void testGetObjectNameFromId_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", DBUtils.getObjectNameFromId("42"));
  }

  /**
   * Test {@link DBUtils#findObjectById(DBRProgressMonitor, DBPProject, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then calls {@link DBPDataSourceRegistry#getDataSource(String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findObjectById(DBRProgressMonitor, DBPProject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.findObjectById(DBRProgressMonitor, DBPProject, String)"})
  public void testFindObjectById_when42_thenCallsGetDataSource() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getDataSource(Mockito.<String>any()))
        .thenReturn(mock(DBPDataSourceContainer.class));

    DBPProject project = mock(DBPProject.class);
    when(project.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    DBUtils.findObjectById(monitor, project, "42");

    // Assert
    verify(dbpDataSourceRegistry).getDataSource("42");
    verify(project).getDataSourceRegistry();
  }

  /**
   * Test {@link DBUtils#findDataSourceByObjectId(DBPProject, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then calls {@link DBPDataSourceRegistry#getDataSource(String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findDataSourceByObjectId(DBPProject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBUtils.findDataSourceByObjectId(DBPProject, String)"})
  public void testFindDataSourceByObjectId_when42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getDataSource(Mockito.<String>any()))
        .thenReturn(mock(DBPDataSourceContainer.class));

    DBPProject project = mock(DBPProject.class);
    when(project.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    DBUtils.findDataSourceByObjectId(project, "42");

    // Assert
    verify(dbpDataSourceRegistry).getDataSource("42");
    verify(project).getDataSourceRegistry();
  }

  /**
   * Test {@link DBUtils#getAttributeBinding(DBSDataContainer, DBCSession, DBCAttributeMetaData)}.
   *
   * <ul>
   *   <li>Then return DataContainer is {@link DBSDataContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAttributeBinding(DBSDataContainer, DBCSession,
   * DBCAttributeMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDAttributeBindingMeta DBUtils.getAttributeBinding(DBSDataContainer, DBCSession, DBCAttributeMetaData)"
  })
  public void testGetAttributeBinding_thenReturnDataContainerIsDBSDataContainer() {
    // Arrange
    DBSDataContainer dataContainer = mock(DBSDataContainer.class);
    when(dataContainer.getDataSource()).thenReturn(null);

    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(null);
    when(session.getDefaultValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);

    // Act
    DBDAttributeBindingMeta actualAttributeBinding =
        DBUtils.getAttributeBinding(dataContainer, session, new AttributeMetaDataProxy(null));

    // Assert
    verify(session).getDefaultValueHandler();
    verify(session).getDataSource();
    verify(dataContainer).getDataSource();
    DBDAttributeBinding actualTopParent = actualAttributeBinding.getTopParent();
    assertSame(actualAttributeBinding, actualTopParent);
    assertSame(dataContainer, actualAttributeBinding.getDataContainer());
  }

  /**
   * Test {@link DBUtils#getAttributeBinding(DBSDataContainer, DBCSession, DBCAttributeMetaData)}.
   *
   * <ul>
   *   <li>Then return DataContainer is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAttributeBinding(DBSDataContainer, DBCSession,
   * DBCAttributeMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDAttributeBindingMeta DBUtils.getAttributeBinding(DBSDataContainer, DBCSession, DBCAttributeMetaData)"
  })
  public void testGetAttributeBinding_thenReturnDataContainerIsNull() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(null);
    when(session.getDefaultValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);

    // Act
    DBDAttributeBindingMeta actualAttributeBinding =
        DBUtils.getAttributeBinding(null, session, new AttributeMetaDataProxy(null));

    // Assert
    verify(session).getDefaultValueHandler();
    verify(session).getDataSource();
    assertNull(actualAttributeBinding.getDataContainer());
    DBDAttributeBinding actualTopParent = actualAttributeBinding.getTopParent();
    assertSame(actualAttributeBinding, actualTopParent);
  }

  /**
   * Test {@link DBUtils#findValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)} with
   * {@code dataSource}, {@code preferences}, {@code column}.
   *
   * <p>Method under test: {@link DBUtils#findValueHandler(DBPDataSource, DBDFormatSettings,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHandler DBUtils.findValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)"
  })
  public void testFindValueHandlerWithDataSourcePreferencesColumn() {
    // Arrange and Act
    DBDValueHandler actualFindValueHandlerResult =
        DBUtils.findValueHandler(null, null, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    assertTrue(actualFindValueHandlerResult instanceof DefaultValueHandler);
    assertNull(actualFindValueHandlerResult.getComparator());
  }

  /**
   * Test {@link DBUtils#findValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)} with
   * {@code dataSource}, {@code preferences}, {@code column}.
   *
   * <p>Method under test: {@link DBUtils#findValueHandler(DBPDataSource, DBDFormatSettings,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHandler DBUtils.findValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)"
  })
  public void testFindValueHandlerWithDataSourcePreferencesColumn2() {
    // Arrange
    DBDFormatSettings preferences = mock(DBDFormatSettings.class);
    when(preferences.getDefaultValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);

    // Act
    DBDValueHandler actualFindValueHandlerResult =
        DBUtils.findValueHandler(null, preferences, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(preferences).getDefaultValueHandler();
    assertTrue(actualFindValueHandlerResult instanceof DefaultValueHandler);
    assertNull(actualFindValueHandlerResult.getComparator());
  }

  /**
   * Test {@link DBUtils#findValueHandler(DBCSession, DBSTypedObject)} with {@code session}, {@code
   * column}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return {@link DefaultValueHandler}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findValueHandler(DBCSession, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDValueHandler DBUtils.findValueHandler(DBCSession, DBSTypedObject)"})
  public void testFindValueHandlerWithSessionColumn_givenNull_thenReturnDefaultValueHandler() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(null);
    when(session.getDefaultValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);

    // Act
    DBDValueHandler actualFindValueHandlerResult =
        DBUtils.findValueHandler(session, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(session).getDefaultValueHandler();
    verify(session).getDataSource();
    assertTrue(actualFindValueHandlerResult instanceof DefaultValueHandler);
    assertNull(actualFindValueHandlerResult.getComparator());
  }

  /**
   * Test {@link DBUtils#getDefaultDataTypeName(DBSObject, DBPDataKind)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultDataTypeName(DBSObject, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getDefaultDataTypeName(DBSObject, DBPDataKind)"})
  public void testGetDefaultDataTypeName() {
    // Arrange, Act and Assert
    assertEquals(
        "?",
        DBUtils.getDefaultDataTypeName(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), DBPDataKind.BOOLEAN));
  }

  /**
   * Test {@link DBUtils#findBinding(Collection, DBSAttributeBase)} with {@code Collection}, {@code
   * DBSAttributeBase}.
   *
   * <p>Method under test: {@link DBUtils#findBinding(Collection, DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDAttributeBinding DBUtils.findBinding(Collection, DBSAttributeBase)"})
  public void testFindBindingWithCollectionDBSAttributeBase() {
    // Arrange
    ArrayList<DBDAttributeBinding> bindings = new ArrayList<>();

    // Act
    DBDAttributeBinding actualFindBindingResult =
        DBUtils.findBinding(bindings, new AttributeMetaDataProxy(null));

    // Assert
    assertNull(actualFindBindingResult);
  }

  /**
   * Test {@link DBUtils#getAttributeReferrers(DBRProgressMonitor, DBSEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAttributeReferrers(DBRProgressMonitor,
   * DBSEntityAttribute, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAttributeReferrers(DBRProgressMonitor, DBSEntityAttribute, boolean)"
  })
  public void testGetAttributeReferrers_thenReturnEmpty() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute entityAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    List<DBSEntityReferrer> actualAttributeReferrers =
        DBUtils.getAttributeReferrers(monitor, entityAttribute, true);

    // Assert
    assertTrue(actualAttributeReferrers.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container = new DBVModel(dataSourceContainer, source);

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);
    entity2.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity2);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.PRIMARY_KEY, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);
    entity2.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity2);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier5() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsNullableUniqueConstraints()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer4);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container3 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.PRIMARY_KEY, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("Name");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    entity4.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity4);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dbpDataSourceInfo).supportsNullableUniqueConstraints();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier6() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsNullableUniqueConstraints()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    when(dataSourceContainer4.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container3 = new DBVModel(dataSourceContainer4);
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.PRIMARY_KEY, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("Name");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    entity4.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity4);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer4, atLeast(1)).getDataSource();
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dbpDataSourceInfo).supportsNullableUniqueConstraints();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier7() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsNullableUniqueConstraints()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    when(dataSourceContainer4.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container3 = new DBVModel(dataSourceContainer4);
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.PRIMARY_KEY, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("Name");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    entity4.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity4);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer4, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dbpDataSourceInfo).supportsNullableUniqueConstraints();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier8() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsNullableUniqueConstraints()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container3 = new DBVContainer(parent, DBConstants.MODEL_BUNDLE_ID);
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.PRIMARY_KEY, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("Name");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    entity4.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity4);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dbpDataSourceInfo).supportsNullableUniqueConstraints();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_givenNull_whenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_thenCallsGetContainer() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addVirtualAttribute(attribute);

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_thenThrowDBException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(DBException.class, () -> DBUtils.getBestTableIdentifier(monitor, entity));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_whenDBVModelWithIdIs42AndMapIsHashMap_thenReturnEmpty()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    List<? extends DBSEntityAttribute> actualBestTableIdentifier =
        DBUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link DBUtils#findEntityConstraint(DBRProgressMonitor, DBSEntity, Collection)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findEntityConstraint(DBRProgressMonitor, DBSEntity,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.findEntityConstraint(DBRProgressMonitor, DBSEntity, Collection)"
  })
  public void testFindEntityConstraint_whenDBVContainerWithParentIsDBVContainerAndName()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBSEntityConstraint actualFindEntityConstraintResult =
        DBUtils.findEntityConstraint(monitor, entity, new ArrayList<>());

    // Assert
    assertNull(actualFindEntityConstraintResult);
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes2() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("Error reading reference attributes"));

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes5() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes6() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container = new DBVModel(dataSourceContainer, source);

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes7() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);
    referrer.setUseAllColumns(true);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>Given {@link DBException#DBException(String)} with message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_givenDBExceptionWithMessageIsEmptyString()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException(""));

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>Given {@link DBException#DBException(String)} with message is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_givenDBExceptionWithMessageIsNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException(null));

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_givenName_whenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_thenCallsGetDataSource() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    entity.addVirtualAttribute(null);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy2);
    referrer.setUseAllColumns(true);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(Mockito.<DBRProgressMonitor>any());
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_thenReturnSizeIsOne() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertEquals(1, actualEntityAttributes.size());
    DBSEntityAttribute getResult = actualEntityAttributes.get(0);
    assertTrue(getResult instanceof DBVEntityAttribute);
    assertSame(dbvEntityAttribute, getResult);
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>When {@link DBSDocumentContainer} {@link
   *       DBSDocumentContainer#getDocumentAttribute(DBRProgressMonitor)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_whenDBSDocumentContainerGetDocumentAttributeReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any())).thenReturn(null);

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_whenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint referrer = new DBVEntityConstraint(entity, copy);
    referrer.addAttribute("Name");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, referrer);

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(monitor, new DBVEntityForeignKey(entity));

    // Assert
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityAttributes(DBRProgressMonitor,
   * DBSEntityReferrer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBUtils.getEntityAttributes(DBRProgressMonitor, DBSEntityReferrer)"})
  public void testGetEntityAttributes_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<DBSEntityAttribute> actualEntityAttributes =
        DBUtils.getEntityAttributes(new LoggingProgressMonitor(), null);

    // Assert
    assertTrue(actualEntityAttributes.isEmpty());
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, new DBSDocumentConstraint(entity), "Column Name");

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Column Name");
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);

    // Act
    DBUtils.getConstraintAttribute(monitor, new DBSDocumentConstraint(entity), "Column Name");

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any())).thenReturn(null);

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, new DBSDocumentConstraint(entity), "Column Name");

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Name");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, new DBVEntityForeignKey(entity), "Column Name");

    // Assert
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName5() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName6() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName7() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName8() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName9() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName10() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container = new DBVModel(dataSourceContainer, source);

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Name");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName11() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Name");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)} with
   * {@code monitor}, {@code constraint}, {@code columnName}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, String)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintColumnName12() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Name");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(
            entity,
            mock(DBVEntityAttribute.class),
            "org.jkiss.dbeaver.model.struct.DBSEntityAttribute");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model.struct.DBSEntityAttribute");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, "Column Name");

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer,
   * DBSEntityAttribute)} with {@code monitor}, {@code constraint}, {@code tableColumn}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, DBSEntityAttribute)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintTableColumn() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);
    DBSDocumentConstraint constraint = new DBSDocumentConstraint(entity);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute tableColumn =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, tableColumn);

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer,
   * DBSEntityAttribute)} with {@code monitor}, {@code constraint}, {@code tableColumn}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, DBSEntityAttribute)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintTableColumn2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey constraint = new DBVEntityForeignKey(entity);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute tableColumn =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, tableColumn);

    // Assert
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer,
   * DBSEntityAttribute)} with {@code monitor}, {@code constraint}, {@code tableColumn}.
   *
   * <p>Method under test: {@link DBUtils#getConstraintAttribute(DBRProgressMonitor,
   * DBSEntityReferrer, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityAttributeRef DBUtils.getConstraintAttribute(DBRProgressMonitor, DBSEntityReferrer, DBSEntityAttribute)"
  })
  public void testGetConstraintAttributeWithMonitorConstraintTableColumn3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute tableColumn =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBSEntityAttributeRef actualConstraintAttribute =
        DBUtils.getConstraintAttribute(monitor, constraint, tableColumn);

    // Assert
    assertNull(actualConstraintAttribute);
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement() throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement2() throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getDataSource()} return {@code null}.
   *   <li>When {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_givenDBPDataSourceGetDataSourceReturnNull_whenQuery()
      throws DBCException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource.getDataSource()).thenReturn(null);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(dbpDataSource).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceInfo} {@link DBPDataSourceInfo#supportsResultSetScroll()}
   *       return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_givenDBPDataSourceInfoSupportsResultSetScrollReturnTrue()
      throws DBCException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource.getDataSource()).thenReturn(null);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", true, false, false);
    verify(dbpDataSource).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with {@code First} and {@code Second} Second is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_givenPairWithFirstAndSecondSecondIsNull() throws DBCException {
    // Arrange
    Pair<String, String> pair = new Pair<>("First", "Second");
    pair.setSecond(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.formatStoredProcedureCall(Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Stored Procedure Call");
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(pair);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"foo", ""});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session)
        .prepareStatement(
            DBCStatementType.EXEC, "Format Stored Procedure Call", false, false, false);
    verify(sqlDialect).formatStoredProcedureCall(isA(DBPDataSource.class), eq(""));
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getExecuteKeywords()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_givenSQLDialectGetExecuteKeywordsReturnNull()
      throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getExecuteKeywords()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(sqlDialect).getExecuteKeywords();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_givenSQLDialectGetMultiLineCommentsReturnNull()
      throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(null);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#formatStoredProcedureCall(DBPDataSource, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_thenCallsFormatStoredProcedureCall() throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.formatStoredProcedureCall(Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Format Stored Procedure Call");
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"foo", ""});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session)
        .prepareStatement(
            DBCStatementType.EXEC, "Format Stored Procedure Call", false, false, false);
    verify(sqlDialect).formatStoredProcedureCall(isA(DBPDataSource.class), eq(""));
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isCRLFBroken()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_thenCallsIsCRLFBroken() throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isCRLFBroken()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_thenCallsIsCRLFBroken2() throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "", true);

    // Assert
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_thenThrowDBCException() throws DBCException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsResultSetScroll()).thenReturn(false);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);
    when(dbpDataSource.getDataSource()).thenReturn(null);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenThrow(new DBCException("An error occurred"));
    when(session.getDataSource()).thenReturn(dbpDataSource);

    // Act and Assert
    assertThrows(DBCException.class, () -> DBUtils.createStatement(session, "Query", true));
    verify(dbpDataSource).getInfo();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).supportsResultSetScroll();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(dbpDataSource).getDataSource();
  }

  /**
   * Test {@link DBUtils#createStatement(DBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then calls {@link SQLDialect#isCRLFBroken()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#createStatement(DBCSession, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement DBUtils.createStatement(DBCSession, String, boolean)"})
  public void testCreateStatement_whenFalse_thenCallsIsCRLFBroken() throws DBCException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBCStatement actualCreateStatementResult = DBUtils.createStatement(session, "Query", false);

    // Assert
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Query", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
    assertTrue(actualCreateStatementResult instanceof LocalStatement);
    assertEquals("Text", actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertEquals(0, actualCreateStatementResult.getStatementWarnings().length);
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE, actualCreateStatementResult.getUpdateRowCount());
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.fireObjectUpdate(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectUpdate(object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.fireObjectUpdate(new DBVEntityForeignKey(entity), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectUpdate(object, DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBVModel(dataSourceContainer), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBSDocumentConstraint(entity), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBSDocumentConstraint(entity), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBVContainer(parent, "Name"), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(object, DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Object)} with {@code object}, {@code data}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Object)"})
  public void testFireObjectUpdateWithObjectData_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(new DBVEntityForeignKey(entity), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(new DBVEntityForeignKey(entity), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled2() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.fireObjectUpdate(new DBVEntityForeignKey(entity), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectUpdate(object, true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBVModel(dataSourceContainer), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBSDocumentConstraint(entity), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBSDocumentConstraint(entity), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBVContainer(parent, "Name"), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, boolean)} with {@code object}, {@code enabled}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, boolean)"})
  public void testFireObjectUpdateWithObjectEnabled_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(object, true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData2() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel object = new DBVModel(dataSourceContainer);

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint object = new DBSDocumentConstraint(entity);

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint object = new DBSDocumentConstraint(entity);

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer object = new DBVContainer(parent, "Name");

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)} with {@code object}, {@code
   * options}, {@code data}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject, Map, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject, Map, Object)"})
  public void testFireObjectUpdateWithObjectOptionsData_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(object, new HashMap<>(), DBPEvent.RENAME);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectUpdate(new DBVContainer(parent, "Name"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectUpdate(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectUpdate(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectUpdate(DBSObject)"})
  public void testFireObjectUpdateWithObject_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectUpdate(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel object = new DBVModel(dataSourceContainer);

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint object = new DBSDocumentConstraint(entity);

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint object = new DBSDocumentConstraint(entity);

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer object = new DBVContainer(parent, "Name");

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectAdd(DBSObject, Map)}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectAdd(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectAdd(DBSObject, Map)"})
  public void testFireObjectAdd_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    // Act
    DBUtils.fireObjectAdd(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.fireObjectRemove(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectRemove(object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRemove(new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRemove(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRemove(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRemove(new DBVContainer(parent, "Name"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectRemove(object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRemove(DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRemove(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRemove(DBSObject)"})
  public void testFireObjectRemove_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectRemove(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.fireObjectSelect(
        new DBVEntityForeignKey(entity), true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectSelect(object, true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectSelect(
        new DBVModel(dataSourceContainer), true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectSelect(
        new DBSDocumentConstraint(entity), true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectSelect(
        new DBSDocumentConstraint(entity), true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectSelect(
        new DBVContainer(parent, "Name"), true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectSelect(object, true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelect(DBSObject, boolean, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectSelect(DBSObject, boolean, DBCExecutionContext)"})
  public void testFireObjectSelect_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectSelect(
        new DBVEntityForeignKey(entity), true, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.fireObjectRefresh(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBUtils.fireObjectRefresh(object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRefresh(new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRefresh(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRefresh(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectRefresh(new DBVContainer(parent, "Name"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity object = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectRefresh(object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectRefresh(DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectRefresh(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBUtils.fireObjectRefresh(DBSObject)"})
  public void testFireObjectRefresh_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectRefresh(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#findBestDataType(Collection, String[])}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findBestDataType(Collection, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDataType DBUtils.findBestDataType(Collection, String[])"
  })
  public void testFindBestDataType_whenArrayList_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.findBestDataType(new ArrayList<>(), "Type Names"));
  }

  /**
   * Test {@link DBUtils#getMoreCommonType(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return FullTypeName is {@code Type Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getMoreCommonType(DBSTypedObject, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSTypedObject DBUtils.getMoreCommonType(DBSTypedObject, DBSTypedObject)"})
  public void testGetMoreCommonType_thenReturnFullTypeNameIsTypeName() {
    // Arrange and Act
    DBSTypedObject actualMoreCommonType =
        DBUtils.getMoreCommonType(
            new SimpleTypedObject("Type Name"), SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    assertTrue(actualMoreCommonType instanceof SimpleTypedObject);
    assertEquals("Type Name", actualMoreCommonType.getFullTypeName());
    assertEquals("Type Name", actualMoreCommonType.getTypeName());
    assertNull(actualMoreCommonType.getPrecision());
    assertNull(actualMoreCommonType.getScale());
    assertEquals(0, actualMoreCommonType.getTypeID());
    assertEquals(DBPDataKind.OBJECT, actualMoreCommonType.getDataKind());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, actualMoreCommonType.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, actualMoreCommonType.getTypeModifiers());
  }

  /**
   * Test {@link DBUtils#getMoreCommonType(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return FullTypeName is {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getMoreCommonType(DBSTypedObject, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSTypedObject DBUtils.getMoreCommonType(DBSTypedObject, DBSTypedObject)"})
  public void testGetMoreCommonType_whenDefault_type_thenReturnFullTypeNameIsObject() {
    // Arrange and Act
    DBSTypedObject actualMoreCommonType =
        DBUtils.getMoreCommonType(SimpleTypedObject.DEFAULT_TYPE, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    assertTrue(actualMoreCommonType instanceof SimpleTypedObject);
    assertEquals("Object", actualMoreCommonType.getFullTypeName());
    assertEquals("Object", actualMoreCommonType.getTypeName());
    assertNull(actualMoreCommonType.getPrecision());
    assertNull(actualMoreCommonType.getScale());
    assertEquals(0, actualMoreCommonType.getTypeID());
    assertEquals(DBPDataKind.OBJECT, actualMoreCommonType.getDataKind());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, actualMoreCommonType.getMaxLength());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, actualMoreCommonType.getTypeModifiers());
  }

  /**
   * Test {@link DBUtils#getLocalDataType(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getLocalDataType(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDataType DBUtils.getLocalDataType(DBPDataSource, String)"
  })
  public void testGetLocalDataType_whenDBPDataSource() {
    // Arrange, Act and Assert
    assertNull(DBUtils.getLocalDataType(mock(DBPDataSource.class), "Dr Jane Doe"));
  }

  /**
   * Test {@link DBUtils#getLocalDataType(DBPDataSource, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getLocalDataType(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDataType DBUtils.getLocalDataType(DBPDataSource, String)"
  })
  public void testGetLocalDataType_whenNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.getLocalDataType(null, "Dr Jane Doe"));
  }

  /**
   * Test {@link DBUtils#getContainer(DBSObject)}.
   *
   * <p>Method under test: {@link DBUtils#getContainer(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBUtils.getContainer(DBSObject)"})
  public void testGetContainer() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBUtils.getContainer(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#getContainer(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getContainer(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBUtils.getContainer(DBSObject)"})
  public void testGetContainer_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.getContainer(new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#getContainer(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getContainer(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBUtils.getContainer(DBSObject)"})
  public void testGetContainer_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.getContainer(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#getContainer(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getContainer(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBUtils.getContainer(DBSObject)"})
  public void testGetContainer_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.getContainer(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#getContainer(DBSObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getContainer(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBUtils.getContainer(DBSObject)"})
  public void testGetContainer_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.getContainer(null));
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext)}
   * with {@code dataSource}, {@code object}, {@code context}.
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject,
   * DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBUtils.getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext)"
  })
  public void testGetObjectFullNameWithDataSourceObjectContext() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);

    // Act
    String actualObjectFullName =
        DBUtils.getObjectFullName(dataSource, object, DBPEvaluationContext.UI);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("\"Name\"", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext,
   * DBPAttributeReferencePurpose)} with {@code dataSource}, {@code object}, {@code context}, {@code
   * purpose}.
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject,
   * DBPEvaluationContext, DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBUtils.getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext, DBPAttributeReferencePurpose)"
  })
  public void testGetObjectFullNameWithDataSourceObjectContextPurpose() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);

    // Act
    String actualObjectFullName =
        DBUtils.getObjectFullName(
            dataSource, object, DBPEvaluationContext.UI, DBPAttributeReferencePurpose.UNSPECIFIED);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("\"Name\"", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext,
   * DBPAttributeReferencePurpose)} with {@code dataSource}, {@code object}, {@code context}, {@code
   * purpose}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject,
   * DBPEvaluationContext, DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBUtils.getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext, DBPAttributeReferencePurpose)"
  })
  public void testGetObjectFullNameWithDataSourceObjectContextPurpose_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPNamedObject object = mock(DBPNamedObject.class);
    when(object.getName()).thenReturn("Name");

    // Act
    String actualObjectFullName =
        DBUtils.getObjectFullName(
            dataSource,
            object,
            DBPEvaluationContext.UI,
            DBPAttributeReferencePurpose.UPDATE_TARGET);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(object).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals("42", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext,
   * DBPAttributeReferencePurpose)} with {@code dataSource}, {@code object}, {@code context}, {@code
   * purpose}.
   *
   * <ul>
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject,
   * DBPEvaluationContext, DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBUtils.getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext, DBPAttributeReferencePurpose)"
  })
  public void testGetObjectFullNameWithDataSourceObjectContextPurpose_thenReturnName() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPNamedObject object = mock(DBPNamedObject.class);
    when(object.getName()).thenReturn("Name");

    // Act
    String actualObjectFullName =
        DBUtils.getObjectFullName(
            dataSource,
            object,
            DBPEvaluationContext.UI,
            DBPAttributeReferencePurpose.UPDATE_TARGET);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(object).getName();
    assertEquals("\"Name\"", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext)}
   * with {@code dataSource}, {@code object}, {@code context}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject,
   * DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBUtils.getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext)"
  })
  public void testGetObjectFullNameWithDataSourceObjectContext_givenInstance_thenReturnName() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPNamedObject object = mock(DBPNamedObject.class);
    when(object.getName()).thenReturn("Name");

    // Act
    String actualObjectFullName =
        DBUtils.getObjectFullName(dataSource, object, DBPEvaluationContext.UI);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(object).getName();
    assertEquals("\"Name\"", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext)}
   * with {@code dataSource}, {@code object}, {@code context}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPDataSource, DBPNamedObject,
   * DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBUtils.getObjectFullName(DBPDataSource, DBPNamedObject, DBPEvaluationContext)"
  })
  public void testGetObjectFullNameWithDataSourceObjectContext_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPNamedObject object = mock(DBPNamedObject.class);
    when(object.getName()).thenReturn("Name");

    // Act
    String actualObjectFullName =
        DBUtils.getObjectFullName(dataSource, object, DBPEvaluationContext.UI);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(object).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals("42", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPNamedObject, DBPEvaluationContext)} with {@code
   * object}, {@code context}.
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPNamedObject, DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getObjectFullName(DBPNamedObject, DBPEvaluationContext)"})
  public void testGetObjectFullNameWithObjectContext() {
    // Arrange
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);

    // Act and Assert
    assertEquals("Name", DBUtils.getObjectFullName(object, DBPEvaluationContext.DML));
  }

  /**
   * Test {@link DBUtils#getObjectFullName(DBPNamedObject, DBPEvaluationContext)} with {@code
   * object}, {@code context}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then calls {@link DBPNamedObject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getObjectFullName(DBPNamedObject, DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getObjectFullName(DBPNamedObject, DBPEvaluationContext)"})
  public void testGetObjectFullNameWithObjectContext_givenName_thenCallsGetName() {
    // Arrange
    DBPNamedObject object = mock(DBPNamedObject.class);
    when(object.getName()).thenReturn("Name");

    // Act
    String actualObjectFullName = DBUtils.getObjectFullName(object, DBPEvaluationContext.UI);

    // Assert
    verify(object).getName();
    assertEquals("Name", actualObjectFullName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(dataSource, typedObject);

    // Assert
    assertEquals("<unknown type>", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject2() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getColumnTypeModifiers(
            Mockito.<DBPDataSource>any(),
            Mockito.<DBSTypedObject>any(),
            Mockito.<String>any(),
            Mockito.<DBPDataKind>any()))
        .thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(dataSource, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect)
        .getColumnTypeModifiers(
            isA(DBPDataSource.class),
            isA(DBSTypedObject.class),
            eq("Object"),
            eq(DBPDataKind.OBJECT));
    assertEquals("Object", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject3() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(dataSource, new SimpleTypedObject(""));

    // Assert
    assertEquals("<unknown type>", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject_givenInstance() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(dataSource, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("Object", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <ul>
   *   <li>Then return {@code ObjectColumn Type Modifiers}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject_thenReturnObjectColumnTypeModifiers() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getColumnTypeModifiers(
            Mockito.<DBPDataSource>any(),
            Mockito.<DBSTypedObject>any(),
            Mockito.<String>any(),
            Mockito.<DBPDataKind>any()))
        .thenReturn("Column Type Modifiers");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(dataSource, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect)
        .getColumnTypeModifiers(
            isA(DBPDataSource.class),
            isA(DBSTypedObject.class),
            eq("Object"),
            eq(DBPDataKind.OBJECT));
    assertEquals("ObjectColumn Type Modifiers", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <ul>
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject_thenReturnQuestionMark() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getColumnTypeModifiers(
            Mockito.<DBPDataSource>any(),
            Mockito.<DBSTypedObject>any(),
            Mockito.<String>any(),
            Mockito.<DBPDataKind>any()))
        .thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    String actualFullTypeName =
        DBUtils.getFullTypeName(
            dataSource, new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN));

    // Assert
    verify(dataSource).getSQLDialect();
    verify(session).getDataSource();
    verify(sqlDialect)
        .getColumnTypeModifiers(
            isA(DBPDataSource.class), isA(DBSTypedObject.class), eq("?"), eq(DBPDataKind.BOOLEAN));
    assertEquals("?", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)} with {@code dataSource},
   * {@code typedObject}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBPDataSource, DBSTypedObject)"})
  public void testGetFullTypeNameWithDataSourceTypedObject_whenNull_thenReturnObject() {
    // Arrange, Act and Assert
    assertEquals("Object", DBUtils.getFullTypeName(null, SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBSTypedObject)} with {@code typedObject}.
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBSTypedObject)"})
  public void testGetFullTypeNameWithTypedObject() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    typedObject.setTypeName("Type Name");

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(typedObject);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("Type Name", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBSTypedObject)} with {@code typedObject}.
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBSTypedObject)"})
  public void testGetFullTypeNameWithTypedObject2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    typedObject.setTypeName("Type Name");

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(typedObject);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("Type Name", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBSTypedObject)} with {@code typedObject}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>Then return {@code <unknown type>}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBSTypedObject)"})
  public void testGetFullTypeNameWithTypedObject_givenDBPDataSource_thenReturnUnknownType() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(typedObject);

    // Assert
    verify(parent).getDataSource();
    assertEquals("<unknown type>", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBSTypedObject)} with {@code typedObject}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBSTypedObject)"})
  public void testGetFullTypeNameWithTypedObject_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);

    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    typedObject.setTypeName("");

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(typedObject);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("<unknown type>", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getFullTypeName(DBSTypedObject)} with {@code typedObject}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFullTypeName(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getFullTypeName(DBSTypedObject)"})
  public void testGetFullTypeNameWithTypedObject_whenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);

    DBVEntityAttribute typedObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    typedObject.setTypeName("Type Name");

    // Act
    String actualFullTypeName = DBUtils.getFullTypeName(typedObject);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("Type Name", actualFullTypeName);
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.EQUALS, DBCLogicalOperator.NOT_EQUALS},
        DBUtils.getAttributeOperators(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators2() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {
          DBCLogicalOperator.IS_NULL,
          DBCLogicalOperator.IS_NOT_NULL,
          DBCLogicalOperator.EQUALS,
          DBCLogicalOperator.NOT_EQUALS
        },
        DBUtils.getAttributeOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators3() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCLogicalOperator[] actualAttributeOperators =
        DBUtils.getAttributeOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.NUMERIC));

    // Assert
    assertEquals(7, actualAttributeOperators.length);
    assertEquals(DBCLogicalOperator.GREATER, actualAttributeOperators[4]);
    assertEquals(DBCLogicalOperator.IN, actualAttributeOperators[6]);
    assertEquals(DBCLogicalOperator.LESS, actualAttributeOperators[5]);
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators4() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCLogicalOperator[] actualAttributeOperators =
        DBUtils.getAttributeOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.DATETIME));

    // Assert
    assertEquals(7, actualAttributeOperators.length);
    assertEquals(DBCLogicalOperator.GREATER, actualAttributeOperators[4]);
    assertEquals(DBCLogicalOperator.IN, actualAttributeOperators[6]);
    assertEquals(DBCLogicalOperator.LESS, actualAttributeOperators[5]);
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators5() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {
          DBCLogicalOperator.IS_NULL,
          DBCLogicalOperator.IS_NOT_NULL,
          DBCLogicalOperator.EQUALS,
          DBCLogicalOperator.NOT_EQUALS
        },
        DBUtils.getAttributeOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BINARY)));
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return array length is eight.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators_thenReturnArrayLengthIsEight() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCLogicalOperator[] actualAttributeOperators =
        DBUtils.getAttributeOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.STRING));

    // Assert
    assertEquals(8, actualAttributeOperators.length);
    assertEquals(DBCLogicalOperator.GREATER, actualAttributeOperators[4]);
    assertEquals(DBCLogicalOperator.IN, actualAttributeOperators[6]);
    assertEquals(DBCLogicalOperator.LESS, actualAttributeOperators[5]);
    assertEquals(DBCLogicalOperator.LIKE, actualAttributeOperators[7]);
  }

  /**
   * Test {@link DBUtils#getAttributeOperators(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getAttributeOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getAttributeOperators(DBSTypedObject)"})
  public void testGetAttributeOperators_whenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.IS_NULL, DBCLogicalOperator.IS_NOT_NULL},
        DBUtils.getAttributeOperators(attribute));
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.EQUALS, DBCLogicalOperator.NOT_EQUALS},
        DBUtils.getDefaultOperators(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators2() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {
          DBCLogicalOperator.IS_NULL,
          DBCLogicalOperator.IS_NOT_NULL,
          DBCLogicalOperator.EQUALS,
          DBCLogicalOperator.NOT_EQUALS
        },
        DBUtils.getDefaultOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators3() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCLogicalOperator[] actualDefaultOperators =
        DBUtils.getDefaultOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.NUMERIC));

    // Assert
    assertEquals(7, actualDefaultOperators.length);
    assertEquals(DBCLogicalOperator.GREATER, actualDefaultOperators[4]);
    assertEquals(DBCLogicalOperator.IN, actualDefaultOperators[6]);
    assertEquals(DBCLogicalOperator.LESS, actualDefaultOperators[5]);
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators4() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCLogicalOperator[] actualDefaultOperators =
        DBUtils.getDefaultOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.DATETIME));

    // Assert
    assertEquals(7, actualDefaultOperators.length);
    assertEquals(DBCLogicalOperator.GREATER, actualDefaultOperators[4]);
    assertEquals(DBCLogicalOperator.IN, actualDefaultOperators[6]);
    assertEquals(DBCLogicalOperator.LESS, actualDefaultOperators[5]);
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators5() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {
          DBCLogicalOperator.IS_NULL,
          DBCLogicalOperator.IS_NOT_NULL,
          DBCLogicalOperator.EQUALS,
          DBCLogicalOperator.NOT_EQUALS
        },
        DBUtils.getDefaultOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BINARY)));
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return array length is eight.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators_thenReturnArrayLengthIsEight() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCLogicalOperator[] actualDefaultOperators =
        DBUtils.getDefaultOperators(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.STRING));

    // Assert
    assertEquals(8, actualDefaultOperators.length);
    assertEquals(DBCLogicalOperator.GREATER, actualDefaultOperators[4]);
    assertEquals(DBCLogicalOperator.IN, actualDefaultOperators[6]);
    assertEquals(DBCLogicalOperator.LESS, actualDefaultOperators[5]);
    assertEquals(DBCLogicalOperator.LIKE, actualDefaultOperators[7]);
  }

  /**
   * Test {@link DBUtils#getDefaultOperators(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDefaultOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] DBUtils.getDefaultOperators(DBSTypedObject)"})
  public void testGetDefaultOperators_whenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.IS_NULL, DBCLogicalOperator.IS_NOT_NULL},
        DBUtils.getDefaultOperators(attribute));
  }

  /**
   * Test {@link DBUtils#findAttributeIndex(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#findAttributeIndex(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSTableIndex DBUtils.findAttributeIndex(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testFindAttributeIndex_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBSTableIndex actualFindAttributeIndexResult = DBUtils.findAttributeIndex(monitor, attribute);

    // Assert
    assertNull(actualFindAttributeIndexResult);
  }

  /**
   * Test {@link DBUtils#getFromObject(Object)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSWrapper#getObject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFromObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getFromObject(Object)"})
  public void testGetFromObject_thenCallsGetObject() {
    // Arrange
    DBSWrapper dbsWrapper = mock(DBSWrapper.class);
    when(dbsWrapper.getObject())
        .thenReturn(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    DBSObject actualFromObject = DBUtils.getFromObject(dbsWrapper);

    // Assert
    verify(dbsWrapper).getObject();
    assertTrue(actualFromObject instanceof DBSDocumentConstraint);
    assertEquals("DocumentKey", actualFromObject.getName());
    DBSEntityConstraintType constraintType =
        ((DBSDocumentConstraint) actualFromObject).getConstraintType();
    assertEquals("PRIMARY KEY", constraintType.getName());
    assertEquals("Primary Key", constraintType.getLocalizedName());
    assertEquals("pk", constraintType.getId());
    assertNull(actualFromObject.getDescription());
    assertNull(actualFromObject.getDataSource());
    assertFalse(actualFromObject.isPersisted());
    assertFalse(constraintType.isAssociation());
    assertFalse(constraintType.isCustom());
    assertFalse(constraintType.isLogical());
    assertTrue(constraintType.isUnique());
  }

  /**
   * Test {@link DBUtils#getFromObject(Object)}.
   *
   * <ul>
   *   <li>When {@link DBSDocumentConstraint#DBSDocumentConstraint(DBSDocumentContainer)} with
   *       entity is {@link DBSDocumentContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getFromObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getFromObject(Object)"})
  public void testGetFromObject_whenDBSDocumentConstraintWithEntityIsDBSDocumentContainer() {
    // Arrange and Act
    DBSObject actualFromObject =
        DBUtils.getFromObject(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertTrue(actualFromObject instanceof DBSDocumentConstraint);
    assertEquals("DocumentKey", actualFromObject.getName());
    DBSEntityConstraintType constraintType =
        ((DBSDocumentConstraint) actualFromObject).getConstraintType();
    assertEquals("PRIMARY KEY", constraintType.getName());
    assertEquals("Primary Key", constraintType.getLocalizedName());
    assertEquals("pk", constraintType.getId());
    assertNull(actualFromObject.getDescription());
    assertNull(actualFromObject.getDataSource());
    assertFalse(actualFromObject.isPersisted());
    assertFalse(constraintType.isAssociation());
    assertFalse(constraintType.isCustom());
    assertFalse(constraintType.isLogical());
    assertTrue(constraintType.isUnique());
  }

  /**
   * Test {@link DBUtils#getDefaultOrActiveObject(DBSInstance)}.
   *
   * <p>Method under test: {@link DBUtils#getDefaultOrActiveObject(DBSInstance)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getDefaultOrActiveObject(DBSInstance)"})
  public void testGetDefaultOrActiveObject() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.isConnectionRefreshing()).thenReturn(false);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextDefaults()).thenReturn(null);

    DBSInstance object = mock(DBSInstance.class);
    when(object.getDataSource()).thenReturn(dbpDataSource);
    when(object.getDefaultContext(Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(dbcExecutionContext);

    // Act
    DBUtils.getDefaultOrActiveObject(object);

    // Assert
    verify(dbpDataSource).isConnectionRefreshing();
    verify(dbcExecutionContext).getContextDefaults();
    verify(object).getDefaultContext(isA(DBRProgressMonitor.class), eq(true));
    verify(object, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link DBUtils#getDefaultOrActiveObject(DBSInstance)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDefaultOrActiveObject(DBSInstance)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getDefaultOrActiveObject(DBSInstance)"})
  public void testGetDefaultOrActiveObject_givenNull_thenReturnNull() {
    // Arrange
    DBSInstance object = mock(DBSInstance.class);
    when(object.getDataSource()).thenReturn(null);
    when(object.getDefaultContext(Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(null);

    // Act
    DBSObject actualDefaultOrActiveObject = DBUtils.getDefaultOrActiveObject(object);

    // Assert
    verify(object).getDefaultContext(isA(DBRProgressMonitor.class), eq(true));
    verify(object, atLeast(1)).getDataSource();
    assertNull(actualDefaultOrActiveObject);
  }

  /**
   * Test {@link DBUtils#getDefaultOrActiveObject(DBSInstance)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#isConnectionRefreshing()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDefaultOrActiveObject(DBSInstance)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getDefaultOrActiveObject(DBSInstance)"})
  public void testGetDefaultOrActiveObject_thenCallsIsConnectionRefreshing() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.isConnectionRefreshing()).thenReturn(false);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());

    DBSInstance object = mock(DBSInstance.class);
    when(object.getDataSource()).thenReturn(dbpDataSource);
    when(object.getDefaultContext(Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(dbcExecutionContext);

    // Act
    DBUtils.getDefaultOrActiveObject(object);

    // Assert
    verify(dbpDataSource).isConnectionRefreshing();
    verify(dbcExecutionContext).getContextDefaults();
    verify(object).getDefaultContext(isA(DBRProgressMonitor.class), eq(true));
    verify(object, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link DBUtils#getActiveInstanceObject(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getActiveInstanceObject(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getActiveInstanceObject(DBCExecutionContext)"})
  public void testGetActiveInstanceObject_givenNull() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(null);

    // Act
    DBSObject actualActiveInstanceObject = DBUtils.getActiveInstanceObject(executionContext);

    // Assert
    verify(executionContext).getContextDefaults();
    assertNull(actualActiveInstanceObject);
  }

  /**
   * Test {@link DBUtils#getActiveInstanceObject(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link VoidExecutionContextDefaults} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getActiveInstanceObject(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBUtils.getActiveInstanceObject(DBCExecutionContext)"})
  public void testGetActiveInstanceObject_givenVoidExecutionContextDefaults() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());

    // Act
    DBSObject actualActiveInstanceObject = DBUtils.getActiveInstanceObject(executionContext);

    // Assert
    verify(executionContext).getContextDefaults();
    assertNull(actualActiveInstanceObject);
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey oldDefaultObject = new DBVEntityForeignKey(entity);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBSDocumentConstraint(entity2), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(entity2).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute oldDefaultObject =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBVEntityForeignKey(entity2), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent2).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_given42_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel oldDefaultObject = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBSDocumentConstraint(entity), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_given42_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectSelectionChange(
        new DBVModel(dataSourceContainer), null, mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint oldDefaultObject = new DBSDocumentConstraint(entity);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(null);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBSDocumentConstraint(entity2), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(entity).getDataSource();
    verify(entity2).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint oldDefaultObject = new DBSDocumentConstraint(entity);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBSDocumentConstraint(entity2), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
    verify(entity2).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_thenCallsGetDataSource3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey oldDefaultObject = new DBVEntityForeignKey(entity);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBSDocumentConstraint(entity2), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity2).getDataSource();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_thenCallsGetDataSource4() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer oldDefaultObject = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBVEntityForeignKey(entity), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent2).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_thenCallsGetDataSource5() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity oldDefaultObject = new DBVEntity(container, "Name", "Description Column Names");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBVEntityForeignKey(entity), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent2).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_whenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey oldDefaultObject = new DBVEntityForeignKey(entity);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    DBUtils.fireObjectSelectionChange(
        oldDefaultObject, new DBVEntityForeignKey(entity2), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#fireObjectSelectionChange(DBSObject, DBSObject,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBUtils.fireObjectSelectionChange(DBSObject, DBSObject, DBCExecutionContext)"
  })
  public void testFireObjectSelectionChange_whenNull_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBUtils.fireObjectSelectionChange(
        null, new DBSDocumentConstraint(entity), mock(DBCExecutionContext.class));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBUtils#getChangeableObjectContainer(DBCExecutionContextDefaults,
   * DBSObjectContainer, Class)}.
   *
   * <ul>
   *   <li>When {@link DBCExecutionContextDefaults}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getChangeableObjectContainer(DBCExecutionContextDefaults,
   * DBSObjectContainer, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObjectContainer DBUtils.getChangeableObjectContainer(DBCExecutionContextDefaults, DBSObjectContainer, Class)"
  })
  public void testGetChangeableObjectContainer_whenDBCExecutionContextDefaults() {
    // Arrange
    DBCExecutionContextDefaults<?, ?> contextDefaults = mock(DBCExecutionContextDefaults.class);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel root = new DBVModel(dataSourceContainer);
    Class<DBSObject> childType = DBSObject.class;

    // Act
    DBSObjectContainer actualChangeableObjectContainer =
        DBUtils.getChangeableObjectContainer(contextDefaults, root, childType);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualChangeableObjectContainer);
  }

  /**
   * Test {@link DBUtils#getChangeableObjectContainer(DBCExecutionContextDefaults,
   * DBSObjectContainer, Class)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getChangeableObjectContainer(DBCExecutionContextDefaults,
   * DBSObjectContainer, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObjectContainer DBUtils.getChangeableObjectContainer(DBCExecutionContextDefaults, DBSObjectContainer, Class)"
  })
  public void testGetChangeableObjectContainer_whenNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel root = new DBVModel(dataSourceContainer);
    Class<DBSObject> childType = DBSObject.class;

    // Act
    DBSObjectContainer actualChangeableObjectContainer =
        DBUtils.getChangeableObjectContainer(null, root, childType);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualChangeableObjectContainer);
  }

  /**
   * Test {@link DBUtils#getDefaultContext(DBSObject, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#isConnectionRefreshing()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDefaultContext(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionContext DBUtils.getDefaultContext(DBSObject, boolean)"})
  public void testGetDefaultContext_thenCallsIsConnectionRefreshing() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.isConnectionRefreshing()).thenReturn(true);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getParentObject()).thenReturn(null);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBCExecutionContext actualDefaultContext =
        DBUtils.getDefaultContext(new DBSDocumentConstraint(entity), true);

    // Assert
    verify(dbpDataSource).isConnectionRefreshing();
    verify(entity).getDataSource();
    verify(entity).getParentObject();
    assertNull(actualDefaultContext);
  }

  /**
   * Test {@link DBUtils#getDefaultContext(DBSObject, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDefaultContext(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionContext DBUtils.getDefaultContext(DBSObject, boolean)"})
  public void testGetDefaultContext_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.getDefaultContext(null, true));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_when42_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, DBUtils.compareDataValues(42, "42"));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_when42_thenReturnZero2() {
    // Arrange, Act and Assert
    assertEquals(0, DBUtils.compareDataValues("42", 42));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return minus fifty-two.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenA_thenReturnMinusFiftyTwo() {
    // Arrange, Act and Assert
    assertEquals(-52, DBUtils.compareDataValues((byte) 'A', DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenA_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, DBUtils.compareDataValues(42, (byte) 'A'));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenA_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, DBUtils.compareDataValues((byte) 'A', "42"));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Cell1}.
   *   <li>Then return minus thirty-nine.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenCell1_thenReturnMinusThirtyNine() {
    // Arrange, Act and Assert
    assertEquals(-39, DBUtils.compareDataValues("Cell1", DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Cell1}.
   *   <li>Then return thirteen.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenCell1_thenReturnThirteen() {
    // Arrange, Act and Assert
    assertEquals(13, DBUtils.compareDataValues("Cell1", (byte) 'A'));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Cell2}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenCell2_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, DBUtils.compareDataValues("Cell1", "Cell2"));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenDBDDocumentXMLWithDocumentIsNull_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, DBUtils.compareDataValues(new DBDDocumentXML(null), DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenDBDDocumentXMLWithDocumentIsNull_thenReturnZero() {
    // Arrange
    DBDDocumentXML dbdDocumentXML = new DBDDocumentXML(null);

    // Act
    int actualCompareDataValuesResult =
        DBUtils.compareDataValues(dbdDocumentXML, new DBDDocumentXML(null));

    // Assert
    assertEquals(0, actualCompareDataValuesResult);
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@link DBDValueError#DBDValueError(Throwable)} with error is {@link
   *       Throwable#Throwable()}.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenDBDValueErrorWithErrorIsThrowable_thenReturnFive() {
    // Arrange, Act and Assert
    assertEquals(5, DBUtils.compareDataValues(new DBDValueError(new Throwable()), DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenEmptyString_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, DBUtils.compareDataValues(42, ""));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenEmptyString_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, DBUtils.compareDataValues("", 42));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return fifty-four.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenFortyTwo_thenReturnFiftyFour() {
    // Arrange, Act and Assert
    assertEquals(54, DBUtils.compareDataValues(DBPEvent.RENAME, 42));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return minus fifty-four.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenFortyTwo_thenReturnMinusFiftyFour() {
    // Arrange, Act and Assert
    assertEquals(-54, DBUtils.compareDataValues(42, DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenNull_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, DBUtils.compareDataValues(DBPEvent.RENAME, null));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenNull_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, DBUtils.compareDataValues(null, DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenOne_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, DBUtils.compareDataValues(42, 1));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenRename_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, DBUtils.compareDataValues(DBPEvent.RENAME, DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return minus fifty-seven.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenTen_thenReturnMinusFiftySeven() {
    // Arrange, Act and Assert
    assertEquals(-57, DBUtils.compareDataValues(10.0d, DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return minus fifty-seven.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenTen_thenReturnMinusFiftySeven2() {
    // Arrange, Act and Assert
    assertEquals(-57, DBUtils.compareDataValues(10.0f, DBPEvent.RENAME));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenTen_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, DBUtils.compareDataValues(10.0d, "42"));
  }

  /**
   * Test {@link DBUtils#compareDataValues(Object, Object)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#compareDataValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBUtils.compareDataValues(Object, Object)"})
  public void testCompareDataValues_whenTen_thenReturnMinusOne2() {
    // Arrange, Act and Assert
    assertEquals(-1, DBUtils.compareDataValues(10.0f, "42"));
  }

  /**
   * Test {@link DBUtils#getEntityFromMetaData(DBRProgressMonitor, DBCExecutionContext,
   * DBCEntityMetaData)} with {@code monitor}, {@code executionContext}, {@code entityMeta}.
   *
   * <p>Method under test: {@link DBUtils#getEntityFromMetaData(DBRProgressMonitor,
   * DBCExecutionContext, DBCEntityMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntity DBUtils.getEntityFromMetaData(DBRProgressMonitor, DBCExecutionContext, DBCEntityMetaData)"
  })
  public void testGetEntityFromMetaDataWithMonitorExecutionContextEntityMeta() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    DBSEntity actualEntityFromMetaData =
        DBUtils.getEntityFromMetaData(monitor, executionContext, mock(DBCEntityMetaData.class));

    // Assert
    verify(executionContext).getDataSource();
    assertNull(actualEntityFromMetaData);
  }

  /**
   * Test {@link DBUtils#getEntityFromMetaData(DBRProgressMonitor, DBCExecutionContext,
   * DBSObjectContainer, DBCEntityMetaData, boolean)} with {@code monitor}, {@code
   * executionContext}, {@code objectContainer}, {@code entityMeta}, {@code transformName}.
   *
   * <p>Method under test: {@link DBUtils#getEntityFromMetaData(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, DBCEntityMetaData, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntity DBUtils.getEntityFromMetaData(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, DBCEntityMetaData, boolean)"
  })
  public void
      testGetEntityFromMetaDataWithMonitorExecutionContextObjectContainerEntityMetaTransformName()
          throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());
    DBVModel objectContainer = new DBVModel("42", new HashMap<>());

    DBCEntityMetaData entityMeta = mock(DBCEntityMetaData.class);
    when(entityMeta.getCatalogName()).thenReturn("Catalog Name");
    when(entityMeta.getEntityName()).thenReturn("Entity Name");
    when(entityMeta.getSchemaName()).thenReturn("Schema Name");

    // Act
    DBSEntity actualEntityFromMetaData =
        DBUtils.getEntityFromMetaData(monitor, executionContext, objectContainer, entityMeta, true);

    // Assert
    verify(entityMeta).getCatalogName();
    verify(entityMeta).getEntityName();
    verify(entityMeta).getSchemaName();
    verify(executionContext).getContextDefaults();
    assertNull(actualEntityFromMetaData);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity dbsEntity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    dbsEntity.addConstraint(constraint);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(
            monitor,
            dbsEntity,
            new AttributeMetaDataProxy(
                new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container2 = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntity dbsEntity = new DBVEntity(container2, copy2, targetModel);
    dbsEntity.addConstraint(constraint);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(
            monitor,
            dbsEntity,
            new AttributeMetaDataProxy(
                new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent2).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container2 = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntity dbsEntity = new DBVEntity(container2, copy2, targetModel);
    dbsEntity.addConstraint(constraint);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(
            monitor,
            dbsEntity,
            new AttributeMetaDataProxy(
                new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container = new DBVModel(dataSourceContainer, source);

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("42");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container3 = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer3);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container4, "Name", "Description Column Names");

    DBVEntity dbsEntity = new DBVEntity(container3, copy2, targetModel);
    dbsEntity.addConstraint(constraint);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(
            monitor,
            dbsEntity,
            new AttributeMetaDataProxy(
                new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer3, atLeast(1)).getId();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint_givenDBVContainerGetRealContainerReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container2 = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntity dbsEntity = new DBVEntity(container2, copy2, targetModel);
    dbsEntity.addConstraint(constraint);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(
            monitor,
            dbsEntity,
            new AttributeMetaDataProxy(
                new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent2).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint_givenDBVModelWithIdIs42AndMapIsHashMap_thenCallsGetContainer()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("42");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container2 = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntity dbsEntity = new DBVEntity(container2, copy2, targetModel);
    dbsEntity.addConstraint(constraint);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(
            monitor,
            dbsEntity,
            new AttributeMetaDataProxy(
                new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)}.
   *
   * <ul>
   *   <li>When {@link AttributeMetaDataProxy#AttributeMetaDataProxy(DBSAttributeBase)} with
   *       attribute is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getConstraint(DBRProgressMonitor, DBSEntity,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBUtils.getConstraint(DBRProgressMonitor, DBSEntity, DBSAttributeBase)"
  })
  public void testGetConstraint_whenAttributeMetaDataProxyWithAttributeIsNull_thenReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbsEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBSEntityConstraint actualConstraint =
        DBUtils.getConstraint(monitor, dbsEntity, new AttributeMetaDataProxy(null));

    // Assert
    assertNull(actualConstraint);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);
    DBVEntity copy =
        new DBVEntity(
            container2,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("\"vfk_useFQN_?\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "");
    DBVEntity object =
        new DBVEntity(
            container,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"useFQN\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName3() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity object =
        new DBVEntity(
            container,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("\"useFQN\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName4() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint container = new DBSDocumentConstraint(entity);
    DBSDocumentConstraint object2 = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object2);

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, directObjectReference);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    verify(entity).getDataSource();
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_givenDBPDataSourceContainerGetIdReturn42() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBSDocumentConstraint object2 = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object2);

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, directObjectReference);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBSDocumentConstraint object2 = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object2);

    HashMap<String, Object> options = new HashMap<>();
    options.put("\"", DBPEvent.RENAME);
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, directObjectReference);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    verify(parent).getDataSource();
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_givenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);
    DBVEntity entity =
        new DBVEntity(
            container,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);
    DBVEntityForeignKey container2 = new DBVEntityForeignKey(entity);
    DBSDocumentConstraint object2 = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container2, RelationalObjectType.TYPE_CATALOG, object2);

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, directObjectReference);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    verify(parent).getDataSource();
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_givenEmptyString() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, "");

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   *       return {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_givenSQLDialectGetQuotedIdentifierReturn42_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint object = new DBSDocumentConstraint(entity);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("DocumentKey", true, false);
    verify(entity).getDataSource();
    assertEquals("42", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link HashMap#HashMap()} {@link DBPScriptObject#OPTION_FULLY_QUALIFIED_NAMES} is
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_givenTrue_whenHashMapOption_fully_qualified_namesIsTrue() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, true);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@code "42"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_thenReturn42() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel object = new DBVModel(dataSourceContainer);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("\"42\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@code "DocumentKey"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_thenReturnDocumentKey() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint object = new DBSDocumentConstraint(entity);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals("\"DocumentKey\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_thenReturnName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer object = new DBVContainer(parent, "Name");

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"Name\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_thenReturnName2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer object = new DBVContainer(parent, "Name");

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, DBPEvent.RENAME);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"Name\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@code "useFQN"."useFQN"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_thenReturnUseFQNUseFQN() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);
    DBVEntity object =
        new DBVEntity(
            container,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"useFQN\".\"useFQN\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@code "vfk_useFQN_?"}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_thenReturnVfkUseFQN() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);
    DBVEntity entity =
        new DBVEntity(
            container,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"vfk_useFQN_?\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and name is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_whenDBVContainerWithParentIsDBVContainerAndNameIsNull() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, null);
    DBVEntity object =
        new DBVEntity(
            container,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES,
            DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, new HashMap<>());

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"useFQN\"", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getEntityScriptName(DBSObject, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link DBPScriptObject#OPTION_FULLY_QUALIFIED_NAMES} is
   *       {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getEntityScriptName(DBSObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBUtils.getEntityScriptName(DBSObject, Map)"})
  public void testGetEntityScriptName_whenHashMapOption_fully_qualified_namesIsRename() {
    // Arrange
    DBSContextBoundAttribute object = mock(DBSContextBoundAttribute.class);
    when(object.formatMemberReference(
            anyBoolean(), Mockito.<String>any(), Mockito.<DBPAttributeReferencePurpose>any()))
        .thenReturn("Format Member Reference");

    HashMap<String, Object> options = new HashMap<>();
    options.put(DBPScriptObject.OPTION_FULLY_QUALIFIED_NAMES, DBPEvent.RENAME);

    // Act
    String actualEntityScriptName = DBUtils.getEntityScriptName(object, options);

    // Assert
    verify(object).formatMemberReference(false, null, DBPAttributeReferencePurpose.UNSPECIFIED);
    assertEquals("Format Member Reference", actualEntityScriptName);
  }

  /**
   * Test {@link DBUtils#getDataType(DBPDataSource, DBSTypedObject)} with {@code dataSource}, {@code
   * typedObject}.
   *
   * <p>Method under test: {@link DBUtils#getDataType(DBPDataSource, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDataType DBUtils.getDataType(DBPDataSource, DBSTypedObject)"
  })
  public void testGetDataTypeWithDataSourceTypedObject() {
    // Arrange, Act and Assert
    assertNull(DBUtils.getDataType(mock(DBPDataSource.class), SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBUtils#getDataType(DBSTypedObject)} with {@code typedObject}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#getDataType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDataType DBUtils.getDataType(DBSTypedObject)"
  })
  public void testGetDataTypeWithTypedObject_whenDefault_type_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBUtils.getDataType(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    List<DBSDataContainer> actualAllDataContainersFromParentContainer =
        DBUtils.getAllDataContainersFromParentContainer(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertTrue(actualAllDataContainersFromParentContainer.isEmpty());
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    LinkedHashSet<DBSObject> dbsObjectSet = new LinkedHashSet<>();
    dbsObjectSet.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    DBNDatabaseFolder parent = mock(DBNDatabaseFolder.class);
    when(parent.getChildrenObjects(Mockito.<DBRProgressMonitor>any())).thenReturn(dbsObjectSet);

    // Act
    List<DBSDataContainer> actualAllDataContainersFromParentContainer =
        DBUtils.getAllDataContainersFromParentContainer(monitor, parent);

    // Assert
    verify(parent).getChildrenObjects(isA(DBRProgressMonitor.class));
    assertTrue(actualAllDataContainersFromParentContainer.isEmpty());
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    LinkedHashSet<DBSObject> dbsObjectSet = new LinkedHashSet<>();
    dbsObjectSet.add(mock(DBSDataContainer.class));

    DBNDatabaseFolder parent = mock(DBNDatabaseFolder.class);
    when(parent.getChildrenObjects(Mockito.<DBRProgressMonitor>any())).thenReturn(dbsObjectSet);

    // Act
    List<DBSDataContainer> actualAllDataContainersFromParentContainer =
        DBUtils.getAllDataContainersFromParentContainer(monitor, parent);

    // Assert
    verify(parent).getChildrenObjects(isA(DBRProgressMonitor.class));
    assertEquals(1, actualAllDataContainersFromParentContainer.size());
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer_given42_thenCallsGetId()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    List<DBSDataContainer> actualAllDataContainersFromParentContainer =
        DBUtils.getAllDataContainersFromParentContainer(monitor, new DBVModel(dataSourceContainer));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualAllDataContainersFromParentContainer.isEmpty());
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer_thenCallsGetDataSource()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer parent = mock(DBPDataSourceContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    List<DBSDataContainer> actualAllDataContainersFromParentContainer =
        DBUtils.getAllDataContainersFromParentContainer(monitor, parent);

    // Assert
    verify(parent).getDataSource();
    assertTrue(actualAllDataContainersFromParentContainer.isEmpty());
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Then return first is {@link DBSDataContainer}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer_thenReturnFirstIsDBSDataContainer()
      throws DBException {
    // Arrange
    DBSDataContainer parent = mock(DBSDataContainer.class);

    // Act
    List<DBSDataContainer> actualAllDataContainersFromParentContainer =
        DBUtils.getAllDataContainersFromParentContainer(new LoggingProgressMonitor(), parent);

    // Assert
    assertEquals(1, actualAllDataContainersFromParentContainer.size());
    assertSame(parent, actualAllDataContainersFromParentContainer.get(0));
  }

  /**
   * Test {@link DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBUtils#getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBUtils.getAllDataContainersFromParentContainer(DBRProgressMonitor, DBSObject)"
  })
  public void testGetAllDataContainersFromParentContainer_thenThrowDBException()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBNDatabaseFolder parent = mock(DBNDatabaseFolder.class);
    when(parent.getChildrenObjects(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    // Act and Assert
    assertThrows(
        DBException.class, () -> DBUtils.getAllDataContainersFromParentContainer(monitor, parent));
    verify(parent).getChildrenObjects(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect3.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect3.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect3);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            DBUtils.countDataFromQuery(
                source,
                session,
                new SQLQuery(dataSource, "Row count query didn't return any value")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session)
        .prepareStatement(
            DBCStatementType.SCRIPT,
            "Row count query didn't return any value",
            false,
            false,
            false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect3).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect3).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect3).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery2() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            DBUtils.countDataFromQuery(
                source, session, new SQLQuery(mock(DBPDataSource.class), "")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery3() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect3.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect3.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect3);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "*")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "*", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect3).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect3).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect3).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery4() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect3.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect3.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect3);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            DBUtils.countDataFromQuery(
                source,
                session,
                new SQLQuery(dataSource, "^\\s*(?:--|//|/\\*)\\s*(?:name|title)\\s*:\\s*(.+)$")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session)
        .prepareStatement(
            DBCStatementType.SCRIPT,
            "^\\s*(?:--|//|/\\*)\\s*(?:name|title)\\s*:\\s*(.+)$",
            false,
            false,
            false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect3).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect3).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect3).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery5() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {""});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    SQLQuery query = new SQLQuery(dataSource, DBPDataSource.FEATURE_LIMIT_AFFECTS_DML, 2, 3);

    // Act and Assert
    assertThrows(DBCException.class, () -> DBUtils.countDataFromQuery(source, session, query));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session)
        .prepareStatement(
            DBCStatementType.SCRIPT, "datasource.limit-affects-dml", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link DBCException#DBCException(String)} with message is {@code An error
   *       occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenDBCExceptionWithMessageIsAnErrorOccurred()
      throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenThrow(new DBCException("An error occurred"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@link
   *       BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenDBPDataSourceGetSQLDialectReturnInstance()
      throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@link
   *       BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenDBPDataSourceGetSQLDialectReturnInstance2()
      throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then calls {@link SQLDialect#isCRLFBroken()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenInstance_thenCallsIsCRLFBroken() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getExecuteKeywords()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenSQLDialectGetExecuteKeywordsReturnNull()
      throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getExecuteKeywords()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(sqlDialect).getExecuteKeywords();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenSQLDialectGetMultiLineCommentsReturnNull()
      throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(null);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getSingleLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_givenSQLDialectGetSingleLineCommentsReturnNull()
      throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getSingleLineComments()).thenReturn(null);
    when(sqlDialect3.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect3);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect3).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect3).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }

  /**
   * Test {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getIdentifierQuoteStrings()}.
   * </ul>
   *
   * <p>Method under test: {@link DBUtils#countDataFromQuery(DBCExecutionSource, DBCSession,
   * SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBUtils.countDataFromQuery(DBCExecutionSource, DBCSession, SQLQuery)"})
  public void testCountDataFromQuery_thenCallsGetIdentifierQuoteStrings() throws DBCException {
    // Arrange
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isCRLFBroken()).thenReturn(true);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getDataSourceFeature(Mockito.<String>any())).thenReturn(DBPEvent.RENAME);
    when(dbpDataSource2.getDataSource()).thenReturn(dbpDataSource);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBCSession session = mock(DBCSession.class);
    when(session.prepareStatement(
            Mockito.<DBCStatementType>any(),
            Mockito.<String>any(),
            anyBoolean(),
            anyBoolean(),
            anyBoolean()))
        .thenReturn(new LocalStatement(mock(DBCSession.class), "Text"));
    when(session.getDataSource()).thenReturn(dbpDataSource2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect3.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect3.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect3);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> DBUtils.countDataFromQuery(source, session, new SQLQuery(dataSource, "Text")));
    verify(dbpDataSource2).getDataSourceFeature("datasource.limit-affects-dml");
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(dataSource).getSQLDialect();
    verify(session, atLeast(1)).getDataSource();
    verify(session).prepareStatement(DBCStatementType.SCRIPT, "Text", false, false, false);
    verify(sqlDialect, atLeast(1)).getExecuteKeywords();
    verify(sqlDialect3).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect3).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect3).getSingleLineComments();
    verify(sqlDialect2).isCRLFBroken();
    verify(dbpDataSource2).getDataSource();
  }
}
