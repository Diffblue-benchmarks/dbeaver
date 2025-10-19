package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.semantics.SQLQuerySymbolDefinition;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSTypeDescriptor;
import org.jkiss.dbeaver.model.struct.DBSTypeDescriptor.Kind;
import org.jkiss.dbeaver.model.struct.DBSTypedObjectEx2;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLQueryExprTypeDiffblueTest {
  /**
   * Test {@link SQLQueryExprType#forPredefined(DBPDataKind)}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forPredefined(DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryExprType SQLQueryExprType.forPredefined(DBPDataKind)"})
  public void testForPredefined() {
    // Arrange and Act
    SQLQueryExprType actualForPredefinedResult =
        SQLQueryExprType.forPredefined(DBPDataKind.BOOLEAN);

    // Assert
    assertNull(actualForPredefinedResult.getDeclaratorDefinition());
    assertNull(actualForPredefinedResult.getTypedDbObject());
    assertEquals(DBPDataKind.BOOLEAN, actualForPredefinedResult.getDataKind());
  }

  /**
   * Test {@link SQLQueryExprType#forExplicitTypeRef(String)}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forExplicitTypeRef(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryExprType SQLQueryExprType.forExplicitTypeRef(String)"})
  public void testForExplicitTypeRef() {
    // Arrange and Act
    SQLQueryExprType actualForExplicitTypeRefResult =
        SQLQueryExprType.forExplicitTypeRef("Type Ref String");

    // Assert
    assertNull(actualForExplicitTypeRefResult.getDeclaratorDefinition());
    assertNull(actualForExplicitTypeRefResult.getTypedDbObject());
    assertEquals(DBPDataKind.UNKNOWN, actualForExplicitTypeRefResult.getDataKind());
  }

  /**
   * Test {@link SQLQueryExprType#forSynthesizedComposite(String, DBPDataSource,
   * SQLQuerySymbolDefinition, Map)}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forSynthesizedComposite(String, DBPDataSource,
   * SQLQuerySymbolDefinition, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forSynthesizedComposite(String, DBPDataSource, SQLQuerySymbolDefinition, Map)"
  })
  public void testForSynthesizedComposite() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForSynthesizedCompositeResult =
        SQLQueryExprType.forSynthesizedComposite(
            "Display Name", dataSource, declaratorDefinition, new HashMap<>());

    // Assert
    assertNull(actualForSynthesizedCompositeResult.getTypedDbObject());
    assertEquals(DBPDataKind.STRUCT, actualForSynthesizedCompositeResult.getDataKind());
    assertSame(declaratorDefinition, actualForSynthesizedCompositeResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forSynthesizedArray(String, SQLQuerySymbolDefinition,
   * SQLQueryExprType)}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forSynthesizedArray(String,
   * SQLQuerySymbolDefinition, SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forSynthesizedArray(String, SQLQuerySymbolDefinition, SQLQueryExprType)"
  })
  public void testForSynthesizedArray() {
    // Arrange
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForSynthesizedArrayResult =
        SQLQueryExprType.forSynthesizedArray(
            "Display Name", declaratorDefinition, SQLQueryExprType.BOOLEAN);

    // Assert
    assertNull(actualForSynthesizedArrayResult.getTypedDbObject());
    assertEquals(DBPDataKind.ARRAY, actualForSynthesizedArrayResult.getDataKind());
    assertSame(declaratorDefinition, actualForSynthesizedArrayResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any())).thenReturn(null);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, mock(SQLQuerySymbolDefinition.class));

    // Assert
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertNull(actualForTypedObjectResult.getDeclaratorDefinition());
    assertNull(actualForTypedObjectResult.getTypedDbObject());
    assertEquals(DBPDataKind.UNKNOWN, actualForTypedObjectResult.getDataKind());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any())).thenThrow(matchException);

    // Act and Assert
    assertThrows(
        MatchException.class,
        () ->
            SQLQueryExprType.forTypedObject(
                monitor, typedObj, mock(SQLQuerySymbolDefinition.class)));
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dbsTypeDescriptor.getKind()).thenThrow(matchException);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);

    // Act and Assert
    assertThrows(
        MatchException.class,
        () ->
            SQLQueryExprType.forTypedObject(
                monitor, typedObj, mock(SQLQuerySymbolDefinition.class)));
    verify(dbsTypeDescriptor).getKind();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getDataKind()).thenReturn(null);
    when(dbsTypeDescriptor.getUnderlyingType()).thenReturn(null);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.UNKNOWN);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, declaratorDefinition);

    // Assert
    verify(dbsTypeDescriptor).getDataKind();
    verify(dbsTypeDescriptor).getKind();
    verify(dbsTypeDescriptor).getUnderlyingType();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertNull(actualForTypedObjectResult.getTypedDbObject());
    assertEquals(DBPDataKind.UNKNOWN, actualForTypedObjectResult.getDataKind());
    assertSame(declaratorDefinition, actualForTypedObjectResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition5() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDataType dbsDataType = mock(DBSDataType.class);
    when(dbsDataType.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);
    when(dbsDataType.getComponentType(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(mock(DBSDataType.class));

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getUnderlyingType()).thenReturn(dbsDataType);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.UNKNOWN);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, declaratorDefinition);

    // Assert
    verify(dbsDataType).getComponentType(isA(DBRProgressMonitor.class));
    verify(dbsTypeDescriptor).getKind();
    verify(dbsTypeDescriptor, atLeast(1)).getUnderlyingType();
    verify(dbsDataType).getDataKind();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertEquals(DBPDataKind.BOOLEAN, actualForTypedObjectResult.getDataKind());
    assertSame(declaratorDefinition, actualForTypedObjectResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition6() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.SIMPLE);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, declaratorDefinition);

    // Assert
    verify(dbsTypeDescriptor, atLeast(1)).getDataKind();
    verify(dbsTypeDescriptor).getKind();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertNull(actualForTypedObjectResult.getTypedDbObject());
    assertEquals(DBPDataKind.BOOLEAN, actualForTypedObjectResult.getDataKind());
    assertSame(declaratorDefinition, actualForTypedObjectResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition7() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.INDEXABLE);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, declaratorDefinition);

    // Assert
    verify(dbsTypeDescriptor).getKind();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertNull(actualForTypedObjectResult.getTypedDbObject());
    assertEquals(DBPDataKind.ARRAY, actualForTypedObjectResult.getDataKind());
    assertSame(declaratorDefinition, actualForTypedObjectResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition8() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.COMPOSITE);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, declaratorDefinition);

    // Assert
    verify(dbsTypeDescriptor).getKind();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertNull(actualForTypedObjectResult.getTypedDbObject());
    assertEquals(DBPDataKind.STRUCT, actualForTypedObjectResult.getDataKind());
    assertSame(declaratorDefinition, actualForTypedObjectResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDataType#getDataKind()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition_thenCallsGetDataKind()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDataType dbsDataType = mock(DBSDataType.class);
    when(dbsDataType.getComponentType(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    when(dbsDataType.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getUnderlyingType()).thenReturn(dbsDataType);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.UNKNOWN);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);
    SQLQuerySymbolDefinition declaratorDefinition = mock(SQLQuerySymbolDefinition.class);

    // Act
    SQLQueryExprType actualForTypedObjectResult =
        SQLQueryExprType.forTypedObject(monitor, typedObj, declaratorDefinition);

    // Assert
    verify(dbsDataType).getComponentType(isA(DBRProgressMonitor.class));
    verify(dbsTypeDescriptor).getKind();
    verify(dbsTypeDescriptor, atLeast(1)).getUnderlyingType();
    verify(dbsDataType).getDataKind();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
    assertEquals(DBPDataKind.BOOLEAN, actualForTypedObjectResult.getDataKind());
    assertSame(declaratorDefinition, actualForTypedObjectResult.getDeclaratorDefinition());
  }

  /**
   * Test {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2,
   * SQLQuerySymbolDefinition)} with {@code monitor}, {@code typedObj}, {@code
   * declaratorDefinition}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryExprType#forTypedObject(DBRProgressMonitor,
   * DBSTypedObjectEx2, SQLQuerySymbolDefinition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.forTypedObject(DBRProgressMonitor, DBSTypedObjectEx2, SQLQuerySymbolDefinition)"
  })
  public void testForTypedObjectWithMonitorTypedObjDeclaratorDefinition_thenThrowDBException()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSDataType dbsDataType = mock(DBSDataType.class);
    when(dbsDataType.getComponentType(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    DBSTypeDescriptor dbsTypeDescriptor = mock(DBSTypeDescriptor.class);
    when(dbsTypeDescriptor.getUnderlyingType()).thenReturn(dbsDataType);
    when(dbsTypeDescriptor.getKind()).thenReturn(Kind.UNKNOWN);

    DBSTypedObjectEx2 typedObj = mock(DBSTypedObjectEx2.class);
    when(typedObj.getTypeDescriptor(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTypeDescriptor);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLQueryExprType.forTypedObject(
                monitor, typedObj, mock(SQLQuerySymbolDefinition.class)));
    verify(dbsDataType).getComponentType(isA(DBRProgressMonitor.class));
    verify(dbsTypeDescriptor).getKind();
    verify(dbsTypeDescriptor, atLeast(1)).getUnderlyingType();
    verify(typedObj).getTypeDescriptor(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#BOOLEAN}.
   *   <li>Then return DataKind is {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType,
   * SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)"
  })
  public void testTryCombineIfMatches_whenBoolean_thenReturnDataKindIsBoolean() {
    // Arrange and Act
    SQLQueryExprType actualTryCombineIfMatchesResult =
        SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType.BOOLEAN, SQLQueryExprType.BOOLEAN);

    // Assert
    assertNull(actualTryCombineIfMatchesResult.getDeclaratorDefinition());
    assertNull(actualTryCombineIfMatchesResult.getTypedDbObject());
    assertEquals(DBPDataKind.BOOLEAN, actualTryCombineIfMatchesResult.getDataKind());
  }

  /**
   * Test {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#DATETIME}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType,
   * SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)"
  })
  public void testTryCombineIfMatches_whenDatetime_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType.DATETIME, SQLQueryExprType.BOOLEAN));
  }

  /**
   * Test {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#DUMMY}.
   *   <li>Then return DataKind is {@code ANY}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType,
   * SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)"
  })
  public void testTryCombineIfMatches_whenDummy_thenReturnDataKindIsAny() {
    // Arrange and Act
    SQLQueryExprType actualTryCombineIfMatchesResult =
        SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType.DUMMY, SQLQueryExprType.BOOLEAN);

    // Assert
    assertNull(actualTryCombineIfMatchesResult.getDeclaratorDefinition());
    assertNull(actualTryCombineIfMatchesResult.getTypedDbObject());
    assertEquals(DBPDataKind.ANY, actualTryCombineIfMatchesResult.getDataKind());
  }

  /**
   * Test {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#DUMMY}.
   *   <li>Then return DataKind is {@code ANY}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryExprType#tryCombineIfMatches(SQLQueryExprType,
   * SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryExprType SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType, SQLQueryExprType)"
  })
  public void testTryCombineIfMatches_whenDummy_thenReturnDataKindIsAny2() {
    // Arrange and Act
    SQLQueryExprType actualTryCombineIfMatchesResult =
        SQLQueryExprType.tryCombineIfMatches(SQLQueryExprType.BOOLEAN, SQLQueryExprType.DUMMY);

    // Assert
    assertNull(actualTryCombineIfMatchesResult.getDeclaratorDefinition());
    assertNull(actualTryCombineIfMatchesResult.getTypedDbObject());
    assertEquals(DBPDataKind.ANY, actualTryCombineIfMatchesResult.getDataKind());
  }
}
