package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute.PropagationPolicy;
import org.jkiss.dbeaver.model.exec.DBCAttributeMetaData;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.impl.data.DefaultValueHandler;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
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
public class DBDAttributeBindingMetaDiffblueTest {
  @Mock private DBCAttributeMetaData dBCAttributeMetaData;

  @Mock private DBCSession dBCSession;

  @InjectMocks private DBDAttributeBindingMeta dBDAttributeBindingMeta;

  @Mock private DBSEntityAttribute dBSEntityAttribute;

  /**
   * Test {@link DBDAttributeBindingMeta#DBDAttributeBindingMeta(DBSDataContainer, DBCSession,
   * DBCAttributeMetaData)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@code null}.
   *   <li>Then return DataContainer is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#DBDAttributeBindingMeta(DBSDataContainer,
   * DBCSession, DBCAttributeMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDAttributeBindingMeta.<init>(DBSDataContainer, DBCSession, DBCAttributeMetaData)"
  })
  public void testNewDBDAttributeBindingMeta_givenNull_whenNull_thenReturnDataContainerIsNull() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(null);
    when(session.getDefaultValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);

    // Act
    DBDAttributeBindingMeta actualDbdAttributeBindingMeta =
        new DBDAttributeBindingMeta(null, session, new AttributeMetaDataProxy(null));

    // Assert
    verify(session).getDefaultValueHandler();
    verify(session).getDataSource();
    assertNull(actualDbdAttributeBindingMeta.getDataContainer());
    DBDAttributeBinding actualTopParent = actualDbdAttributeBindingMeta.getTopParent();
    assertSame(actualDbdAttributeBindingMeta, actualTopParent);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#DBDAttributeBindingMeta(DBSDataContainer, DBCSession,
   * DBCAttributeMetaData)}.
   *
   * <ul>
   *   <li>Then return DataContainer is {@link DBSDataContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#DBDAttributeBindingMeta(DBSDataContainer,
   * DBCSession, DBCAttributeMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDAttributeBindingMeta.<init>(DBSDataContainer, DBCSession, DBCAttributeMetaData)"
  })
  public void testNewDBDAttributeBindingMeta_thenReturnDataContainerIsDBSDataContainer() {
    // Arrange
    DBSDataContainer dataContainer = mock(DBSDataContainer.class);
    when(dataContainer.getDataSource()).thenReturn(null);

    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(null);
    when(session.getDefaultValueHandler()).thenReturn(DefaultValueHandler.INSTANCE);

    // Act
    DBDAttributeBindingMeta actualDbdAttributeBindingMeta =
        new DBDAttributeBindingMeta(dataContainer, session, new AttributeMetaDataProxy(null));

    // Assert
    verify(session).getDefaultValueHandler();
    verify(session).getDataSource();
    verify(dataContainer).getDataSource();
    DBDAttributeBinding actualTopParent = actualDbdAttributeBindingMeta.getTopParent();
    assertSame(actualDbdAttributeBindingMeta, actualTopParent);
    assertSame(dataContainer, actualDbdAttributeBindingMeta.getDataContainer());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getParentObject()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDAttributeBinding DBDAttributeBindingMeta.getParentObject()"})
  public void testGetParentObject() {
    // Arrange, Act and Assert
    assertNull(dBDAttributeBindingMeta.getParentObject());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getOrdinalPosition()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getOrdinalPosition()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDAttributeBindingMeta.getOrdinalPosition()"})
  public void testGetOrdinalPosition() {
    // Arrange
    when(dBCAttributeMetaData.getOrdinalPosition()).thenReturn(1);

    // Act
    int actualOrdinalPosition = dBDAttributeBindingMeta.getOrdinalPosition();

    // Assert
    verify(dBCAttributeMetaData).getOrdinalPosition();
    assertEquals(1, actualOrdinalPosition);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#isRequired()}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#isRequired()} return
   *       {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#isRequired()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeBindingMeta.isRequired()"})
  public void testIsRequired_givenDBCAttributeMetaDataIsRequiredReturnFalse_thenReturnFalse() {
    // Arrange
    when(dBCAttributeMetaData.isRequired()).thenReturn(false);

    // Act
    boolean actualIsRequiredResult = dBDAttributeBindingMeta.isRequired();

    // Assert
    verify(dBCAttributeMetaData).isRequired();
    assertFalse(actualIsRequiredResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#isRequired()}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#isRequired()} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#isRequired()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeBindingMeta.isRequired()"})
  public void testIsRequired_givenDBCAttributeMetaDataIsRequiredReturnTrue_thenReturnTrue() {
    // Arrange
    when(dBCAttributeMetaData.isRequired()).thenReturn(true);

    // Act
    boolean actualIsRequiredResult = dBDAttributeBindingMeta.isRequired();

    // Assert
    verify(dBCAttributeMetaData).isRequired();
    assertTrue(actualIsRequiredResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#isAutoGenerated()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#isAutoGenerated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeBindingMeta.isAutoGenerated()"})
  public void testIsAutoGenerated_thenReturnFalse() {
    // Arrange
    when(dBCAttributeMetaData.isAutoGenerated()).thenReturn(false);

    // Act
    boolean actualIsAutoGeneratedResult = dBDAttributeBindingMeta.isAutoGenerated();

    // Assert
    verify(dBCAttributeMetaData).isAutoGenerated();
    assertFalse(actualIsAutoGeneratedResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#isAutoGenerated()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#isAutoGenerated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeBindingMeta.isAutoGenerated()"})
  public void testIsAutoGenerated_thenReturnTrue() {
    // Arrange
    when(dBCAttributeMetaData.isAutoGenerated()).thenReturn(true);

    // Act
    boolean actualIsAutoGeneratedResult = dBDAttributeBindingMeta.isAutoGenerated();

    // Assert
    verify(dBCAttributeMetaData).isAutoGenerated();
    assertTrue(actualIsAutoGeneratedResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#isPseudoAttribute()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeBindingMeta}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#isPseudoAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeBindingMeta.isPseudoAttribute()"})
  public void testIsPseudoAttribute_givenDBDAttributeBindingMeta_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(dBDAttributeBindingMeta.isPseudoAttribute());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#isPseudoAttribute()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#isPseudoAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeBindingMeta.isPseudoAttribute()"})
  public void testIsPseudoAttribute_thenReturnTrue() {
    // Arrange
    DBDPseudoAttribute pseudoAttribute =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);
    dBDAttributeBindingMeta.setPseudoAttribute(pseudoAttribute);

    // Act and Assert
    assertTrue(dBDAttributeBindingMeta.isPseudoAttribute());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getTypeName()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDAttributeBindingMeta.getTypeName()"})
  public void testGetTypeName() {
    // Arrange
    when(dBCAttributeMetaData.getTypeName()).thenReturn("Type Name");

    // Act
    String actualTypeName = dBDAttributeBindingMeta.getTypeName();

    // Assert
    verify(dBCAttributeMetaData).getTypeName();
    assertEquals("Type Name", actualTypeName);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getTypeID()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getTypeID()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDAttributeBindingMeta.getTypeID()"})
  public void testGetTypeID() {
    // Arrange
    when(dBCAttributeMetaData.getTypeID()).thenReturn(1);

    // Act
    int actualTypeID = dBDAttributeBindingMeta.getTypeID();

    // Assert
    verify(dBCAttributeMetaData).getTypeID();
    assertEquals(1, actualTypeID);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getDataKind()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getDataKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind DBDAttributeBindingMeta.getDataKind()"})
  public void testGetDataKind() {
    // Arrange
    when(dBCAttributeMetaData.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);

    // Act
    DBPDataKind actualDataKind = dBDAttributeBindingMeta.getDataKind();

    // Assert
    verify(dBCAttributeMetaData).getDataKind();
    assertEquals(DBPDataKind.BOOLEAN, actualDataKind);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getScale()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getScale()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer DBDAttributeBindingMeta.getScale()"})
  public void testGetScale() {
    // Arrange
    when(dBCAttributeMetaData.getScale()).thenReturn(1);

    // Act
    Integer actualScale = dBDAttributeBindingMeta.getScale();

    // Assert
    verify(dBCAttributeMetaData).getScale();
    assertEquals(1, actualScale.intValue());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getPrecision()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getPrecision()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer DBDAttributeBindingMeta.getPrecision()"})
  public void testGetPrecision() {
    // Arrange
    when(dBCAttributeMetaData.getPrecision()).thenReturn(1);

    // Act
    Integer actualPrecision = dBDAttributeBindingMeta.getPrecision();

    // Assert
    verify(dBCAttributeMetaData).getPrecision();
    assertEquals(1, actualPrecision.intValue());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getMaxLength()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getMaxLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBDAttributeBindingMeta.getMaxLength()"})
  public void testGetMaxLength() {
    // Arrange
    when(dBCAttributeMetaData.getMaxLength()).thenReturn(3L);

    // Act
    long actualMaxLength = dBDAttributeBindingMeta.getMaxLength();

    // Assert
    verify(dBCAttributeMetaData).getMaxLength();
    assertEquals(3L, actualMaxLength);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getTypeModifiers()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getTypeModifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBDAttributeBindingMeta.getTypeModifiers()"})
  public void testGetTypeModifiers() {
    // Arrange
    when(dBCAttributeMetaData.getTypeModifiers()).thenReturn(1L);

    // Act
    long actualTypeModifiers = dBDAttributeBindingMeta.getTypeModifiers();

    // Assert
    verify(dBCAttributeMetaData).getTypeModifiers();
    assertEquals(1L, actualTypeModifiers);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getLabel()}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#getLabel()} return {@code
   *       Label}.
   *   <li>Then return {@code Label}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getLabel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDAttributeBindingMeta.getLabel()"})
  public void testGetLabel_givenDBCAttributeMetaDataGetLabelReturnLabel_thenReturnLabel() {
    // Arrange
    when(dBCAttributeMetaData.getLabel()).thenReturn("Label");

    // Act
    String actualLabel = dBDAttributeBindingMeta.getLabel();

    // Assert
    verify(dBCAttributeMetaData).getLabel();
    assertEquals("Label", actualLabel);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getLabel()}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#getName()} return {@code
   *       Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getLabel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDAttributeBindingMeta.getLabel()"})
  public void testGetLabel_givenDBCAttributeMetaDataGetNameReturnName_thenReturnName() {
    // Arrange
    when(dBCAttributeMetaData.getName()).thenReturn("Name");
    dBDAttributeBindingMeta.setShowLabel(false);

    // Act
    String actualLabel = dBDAttributeBindingMeta.getLabel();

    // Assert
    verify(dBCAttributeMetaData).getName();
    assertEquals("Name", actualLabel);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#getName()}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDAttributeBindingMeta.getName()"})
  public void testGetName() {
    // Arrange
    when(dBCAttributeMetaData.getName()).thenReturn("Name");

    // Act
    String actualName = dBDAttributeBindingMeta.getName();

    // Assert
    verify(dBCAttributeMetaData).getName();
    assertEquals("Name", actualName);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#extractNestedValue(Object, int)}.
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#extractNestedValue(Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDAttributeBindingMeta.extractNestedValue(Object, int)"})
  public void testExtractNestedValue() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class, () -> dBDAttributeBindingMeta.extractNestedValue(DBPEvent.RENAME, 42));
  }

  /**
   * Test {@link DBDAttributeBindingMeta#setEntityAttribute(DBSEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>Then {@link DBDAttributeBindingMeta} FullTypeName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#setEntityAttribute(DBSEntityAttribute,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.setEntityAttribute(DBSEntityAttribute, boolean)"
  })
  public void testSetEntityAttribute_thenDBDAttributeBindingMetaFullTypeNameIsNull() {
    // Arrange
    when(dBCAttributeMetaData.getTypeID()).thenReturn(1);
    when(dBCAttributeMetaData.getTypeName()).thenReturn("Type Name");
    when(dBCAttributeMetaData.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);
    when(dBSEntityAttribute.getTypeName()).thenReturn("Type Name");
    when(dBSEntityAttribute.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);
    when(dBSEntityAttribute.getTypeID()).thenReturn(1);

    // Act
    dBDAttributeBindingMeta.setEntityAttribute(dBSEntityAttribute, true);

    // Assert
    verify(dBCAttributeMetaData).getDataKind();
    verify(dBSEntityAttribute).getDataKind();
    verify(dBCAttributeMetaData).getTypeID();
    verify(dBSEntityAttribute).getTypeID();
    verify(dBSEntityAttribute).getTypeName();
    verify(dBCAttributeMetaData, atLeast(1)).getTypeName();
    assertNull(dBDAttributeBindingMeta.getFullTypeName());
    assertSame(dBSEntityAttribute, dBDAttributeBindingMeta.getAttribute());
    assertSame(dBSEntityAttribute, dBDAttributeBindingMeta.getPresentationAttribute());
    assertSame(dBSEntityAttribute, dBDAttributeBindingMeta.getEntityAttribute());
  }

  /**
   * Test {@link DBDAttributeBindingMeta#setEntityAttribute(DBSEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>Then {@link DBDAttributeBindingMeta} FullTypeName is {@code <unknown type>}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#setEntityAttribute(DBSEntityAttribute,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.setEntityAttribute(DBSEntityAttribute, boolean)"
  })
  public void testSetEntityAttribute_thenDBDAttributeBindingMetaFullTypeNameIsUnknownType() {
    // Arrange and Act
    boolean actualSetEntityAttributeResult = dBDAttributeBindingMeta.setEntityAttribute(null, true);

    // Assert
    assertEquals("<unknown type>", dBDAttributeBindingMeta.getFullTypeName());
    assertNull(dBDAttributeBindingMeta.getEntityAttribute());
    assertFalse(actualSetEntityAttributeResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#setEntityAttribute(DBSEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#setEntityAttribute(DBSEntityAttribute,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.setEntityAttribute(DBSEntityAttribute, boolean)"
  })
  public void testSetEntityAttribute_whenFalse() {
    // Arrange and Act
    boolean actualSetEntityAttributeResult =
        dBDAttributeBindingMeta.setEntityAttribute(null, false);

    // Assert
    assertEquals("<unknown type>", dBDAttributeBindingMeta.getFullTypeName());
    assertNull(dBDAttributeBindingMeta.getEntityAttribute());
    assertFalse(actualSetEntityAttributeResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#getDataKind()} return
   *       {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_givenDBCAttributeMetaDataGetDataKindReturnBoolean() {
    // Arrange
    when(dBCAttributeMetaData.getTypeID()).thenReturn(0);
    when(dBCAttributeMetaData.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);

    // Act
    boolean actualHaveEqualsTypesResult =
        DBDAttributeBindingMeta.haveEqualsTypes(
            dBDAttributeBindingMeta, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(dBCAttributeMetaData).getDataKind();
    verify(dBCAttributeMetaData).getTypeID();
    assertFalse(actualHaveEqualsTypesResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#getTypeID()} return one.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_givenDBCAttributeMetaDataGetTypeIDReturnOne() {
    // Arrange
    when(dBCAttributeMetaData.getTypeID()).thenReturn(1);

    // Act
    boolean actualHaveEqualsTypesResult =
        DBDAttributeBindingMeta.haveEqualsTypes(
            dBDAttributeBindingMeta, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(dBCAttributeMetaData).getTypeID();
    assertFalse(actualHaveEqualsTypesResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#getTypeName()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_givenDBCAttributeMetaDataGetTypeNameReturnFoo() {
    // Arrange
    when(dBCAttributeMetaData.getTypeName()).thenReturn("foo");
    when(dBCAttributeMetaData.getTypeID()).thenReturn(0);
    when(dBCAttributeMetaData.getDataKind()).thenReturn(DBPDataKind.OBJECT);

    // Act
    boolean actualHaveEqualsTypesResult =
        DBDAttributeBindingMeta.haveEqualsTypes(dBDAttributeBindingMeta, dBDAttributeBindingMeta);

    // Assert
    verify(dBCAttributeMetaData, atLeast(1)).getDataKind();
    verify(dBCAttributeMetaData, atLeast(1)).getTypeID();
    verify(dBCAttributeMetaData, atLeast(1)).getTypeName();
    assertTrue(actualHaveEqualsTypesResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBCAttributeMetaData} {@link DBCAttributeMetaData#getTypeName()} return
   *       {@code Type Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_givenDBCAttributeMetaDataGetTypeNameReturnTypeName() {
    // Arrange
    when(dBCAttributeMetaData.getTypeName()).thenReturn("Type Name");
    when(dBCAttributeMetaData.getTypeID()).thenReturn(0);
    when(dBCAttributeMetaData.getDataKind()).thenReturn(DBPDataKind.OBJECT);

    // Act
    boolean actualHaveEqualsTypesResult =
        DBDAttributeBindingMeta.haveEqualsTypes(
            dBDAttributeBindingMeta, SimpleTypedObject.DEFAULT_TYPE);

    // Assert
    verify(dBCAttributeMetaData).getDataKind();
    verify(dBCAttributeMetaData).getTypeID();
    verify(dBCAttributeMetaData, atLeast(1)).getTypeName();
    assertFalse(actualHaveEqualsTypesResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>Then calls {@link DBCSession#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_givenDBPDataSource_thenCallsGetDataSource() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn object1 =
        new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN);

    DBCSession session2 = mock(DBCSession.class);
    when(session2.getDataSource()).thenReturn(mock(DBPDataSource.class));
    LocalStatement localStatement2 = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet2 = new LocalResultSet<>(session2, localStatement2);

    // Act
    boolean actualHaveEqualsTypesResult =
        DBDAttributeBindingMeta.haveEqualsTypes(
            object1, new LocalResultSetColumn(resultSet2, 1, "Label", DBPDataKind.BOOLEAN));

    // Assert
    verify(session2).getDataSource();
    verify(session, atLeast(1)).getDataSource();
    assertTrue(actualHaveEqualsTypesResult);
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_whenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute object1 =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act and Assert
    assertFalse(DBDAttributeBindingMeta.haveEqualsTypes(object1, SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_whenDefault_type_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        DBDAttributeBindingMeta.haveEqualsTypes(
            SimpleTypedObject.DEFAULT_TYPE, SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link LocalStatement#LocalStatement(DBCSession, String)} with session is {@link
   *       DBCSession} and {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_whenLocalStatementWithSessionIsDBCSessionAndText() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertFalse(
        DBDAttributeBindingMeta.haveEqualsTypes(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN),
            SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with typeName is {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_whenSimpleTypedObjectWithTypeNameIsNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        DBDAttributeBindingMeta.haveEqualsTypes(
            new SimpleTypedObject(null), SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with {@code Type Name}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeBindingMeta#haveEqualsTypes(DBSTypedObject,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeBindingMeta.haveEqualsTypes(DBSTypedObject, DBSTypedObject)"
  })
  public void testHaveEqualsTypes_whenSimpleTypedObjectWithTypeName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        DBDAttributeBindingMeta.haveEqualsTypes(
            new SimpleTypedObject("Type Name"), SimpleTypedObject.DEFAULT_TYPE));
  }
}
