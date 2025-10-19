package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDAttributeConstraintDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Attribute Label}.
   *   <li>Then return {@code Attribute Label}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraint#DBDAttributeConstraint(String, String, int)}
   *   <li>{@link DBDAttributeConstraint#setPlainNameReference(boolean)}
   *   <li>{@link DBDAttributeConstraint#getAttribute()}
   *   <li>{@link DBDAttributeConstraint#getAttributeLabel()}
   *   <li>{@link DBDAttributeConstraint#getAttributeName()}
   *   <li>{@link DBDAttributeConstraint#getOriginalVisualPosition()}
   *   <li>{@link DBDAttributeConstraint#isPlainNameReference()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDAttributeConstraint.<init>(String, int)",
    "void DBDAttributeConstraint.<init>(String, String, int)",
    "DBSAttributeBase DBDAttributeConstraint.getAttribute()",
    "String DBDAttributeConstraint.getAttributeLabel()",
    "String DBDAttributeConstraint.getAttributeName()",
    "int DBDAttributeConstraint.getOriginalVisualPosition()",
    "boolean DBDAttributeConstraint.isPlainNameReference()",
    "void DBDAttributeConstraint.setPlainNameReference(boolean)"
  })
  public void testGettersAndSetters_whenAttributeLabel_thenReturnAttributeLabel() {
    // Arrange and Act
    DBDAttributeConstraint actualDbdAttributeConstraint =
        new DBDAttributeConstraint("Attribute Name", "Attribute Label", 1);
    actualDbdAttributeConstraint.setPlainNameReference(true);
    DBSAttributeBase actualAttribute = actualDbdAttributeConstraint.getAttribute();
    String actualAttributeLabel = actualDbdAttributeConstraint.getAttributeLabel();
    String actualAttributeName = actualDbdAttributeConstraint.getAttributeName();
    int actualOriginalVisualPosition = actualDbdAttributeConstraint.getOriginalVisualPosition();
    boolean actualIsPlainNameReferenceResult = actualDbdAttributeConstraint.isPlainNameReference();

    // Assert
    assertEquals("Attribute Label", actualAttributeLabel);
    assertEquals("Attribute Name", actualAttributeName);
    assertNull(actualDbdAttributeConstraint.getValue());
    assertNull(actualDbdAttributeConstraint.getOptions());
    assertNull(actualDbdAttributeConstraint.getCriteria());
    assertNull(actualDbdAttributeConstraint.getEntityAlias());
    assertNull(actualDbdAttributeConstraint.getOperator());
    assertNull(actualAttribute);
    assertEquals(0, actualDbdAttributeConstraint.getOrderPosition());
    assertEquals(0, actualDbdAttributeConstraint.getVisualPosition());
    assertEquals(1, actualOriginalVisualPosition);
    assertFalse(actualDbdAttributeConstraint.isOrderDescending());
    assertFalse(actualDbdAttributeConstraint.isReverseOperator());
    assertFalse(actualDbdAttributeConstraint.isVisible());
    assertTrue(actualIsPlainNameReferenceResult);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Attribute Name}.
   *   <li>Then return AttributeLabel is {@code Attribute Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraint#DBDAttributeConstraint(String, int)}
   *   <li>{@link DBDAttributeConstraint#setPlainNameReference(boolean)}
   *   <li>{@link DBDAttributeConstraint#getAttribute()}
   *   <li>{@link DBDAttributeConstraint#getAttributeLabel()}
   *   <li>{@link DBDAttributeConstraint#getAttributeName()}
   *   <li>{@link DBDAttributeConstraint#getOriginalVisualPosition()}
   *   <li>{@link DBDAttributeConstraint#isPlainNameReference()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDAttributeConstraint.<init>(String, int)",
    "void DBDAttributeConstraint.<init>(String, String, int)",
    "DBSAttributeBase DBDAttributeConstraint.getAttribute()",
    "String DBDAttributeConstraint.getAttributeLabel()",
    "String DBDAttributeConstraint.getAttributeName()",
    "int DBDAttributeConstraint.getOriginalVisualPosition()",
    "boolean DBDAttributeConstraint.isPlainNameReference()",
    "void DBDAttributeConstraint.setPlainNameReference(boolean)"
  })
  public void testGettersAndSetters_whenAttributeName_thenReturnAttributeLabelIsAttributeName() {
    // Arrange and Act
    DBDAttributeConstraint actualDbdAttributeConstraint =
        new DBDAttributeConstraint("Attribute Name", 1);
    actualDbdAttributeConstraint.setPlainNameReference(true);
    DBSAttributeBase actualAttribute = actualDbdAttributeConstraint.getAttribute();
    String actualAttributeLabel = actualDbdAttributeConstraint.getAttributeLabel();
    String actualAttributeName = actualDbdAttributeConstraint.getAttributeName();
    int actualOriginalVisualPosition = actualDbdAttributeConstraint.getOriginalVisualPosition();
    boolean actualIsPlainNameReferenceResult = actualDbdAttributeConstraint.isPlainNameReference();

    // Assert
    assertEquals("Attribute Name", actualAttributeLabel);
    assertEquals("Attribute Name", actualAttributeName);
    assertNull(actualDbdAttributeConstraint.getValue());
    assertNull(actualDbdAttributeConstraint.getOptions());
    assertNull(actualDbdAttributeConstraint.getCriteria());
    assertNull(actualDbdAttributeConstraint.getEntityAlias());
    assertNull(actualDbdAttributeConstraint.getOperator());
    assertNull(actualAttribute);
    assertEquals(0, actualDbdAttributeConstraint.getOrderPosition());
    assertEquals(0, actualDbdAttributeConstraint.getVisualPosition());
    assertEquals(1, actualOriginalVisualPosition);
    assertFalse(actualDbdAttributeConstraint.isOrderDescending());
    assertFalse(actualDbdAttributeConstraint.isReverseOperator());
    assertFalse(actualDbdAttributeConstraint.isVisible());
    assertTrue(actualIsPlainNameReferenceResult);
  }

  /**
   * Test {@link DBDAttributeConstraint#DBDAttributeConstraint(DBDAttributeConstraint)}.
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraint#DBDAttributeConstraint(DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraint.<init>(DBDAttributeConstraint)"})
  public void testNewDBDAttributeConstraint() {
    // Arrange
    DBDAttributeConstraint source = new DBDAttributeConstraint("Attribute Name", 1);

    // Act
    DBDAttributeConstraint actualDbdAttributeConstraint = new DBDAttributeConstraint(source);

    // Assert
    assertEquals(source, actualDbdAttributeConstraint);
  }

  /**
   * Test {@link DBDAttributeConstraint#DBDAttributeConstraint(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return AttributeName is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraint#DBDAttributeConstraint(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraint.<init>(DBDAttributeBinding)"})
  public void testNewDBDAttributeConstraint_givenOne_thenReturnAttributeNameIsName() {
    // Arrange
    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getOrdinalPosition()).thenReturn(1);
    when(attribute.getName()).thenReturn("Name");

    // Act
    DBDAttributeConstraint actualDbdAttributeConstraint = new DBDAttributeConstraint(attribute);

    // Assert
    verify(attribute).getName();
    verify(attribute, atLeast(1)).getOrdinalPosition();
    assertEquals("Name", actualDbdAttributeConstraint.getAttributeName());
    assertNull(actualDbdAttributeConstraint.getValue());
    assertNull(actualDbdAttributeConstraint.getOptions());
    assertNull(actualDbdAttributeConstraint.getAttributeLabel());
    assertNull(actualDbdAttributeConstraint.getFullAttributeName());
    assertNull(actualDbdAttributeConstraint.getCriteria());
    assertNull(actualDbdAttributeConstraint.getEntityAlias());
    assertNull(actualDbdAttributeConstraint.getOperator());
    assertEquals(0, actualDbdAttributeConstraint.getOrderPosition());
    assertEquals(1, actualDbdAttributeConstraint.getOriginalVisualPosition());
    assertEquals(1, actualDbdAttributeConstraint.getVisualPosition());
    assertFalse(actualDbdAttributeConstraint.hasFilter());
    assertFalse(actualDbdAttributeConstraint.isPlainNameReference());
    assertFalse(actualDbdAttributeConstraint.hasCondition());
    assertFalse(actualDbdAttributeConstraint.isOrderDescending());
    assertFalse(actualDbdAttributeConstraint.isReverseOperator());
    assertFalse(actualDbdAttributeConstraint.isVisible());
    assertTrue(actualDbdAttributeConstraint.isDirty());
    assertSame(attribute, actualDbdAttributeConstraint.getAttribute());
  }

  /**
   * Test {@link DBDAttributeConstraint#DBDAttributeConstraint(DBDAttributeBinding, int, int)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return AttributeName is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#DBDAttributeConstraint(DBDAttributeBinding,
   * int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraint.<init>(DBDAttributeBinding, int, int)"})
  public void testNewDBDAttributeConstraint_givenOne_thenReturnAttributeNameIsName2() {
    // Arrange
    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getOrdinalPosition()).thenReturn(1);
    when(attribute.getName()).thenReturn("Name");

    // Act
    DBDAttributeConstraint actualDbdAttributeConstraint =
        new DBDAttributeConstraint(attribute, 1, 1);

    // Assert
    verify(attribute).getName();
    verify(attribute).getOrdinalPosition();
    assertEquals("Name", actualDbdAttributeConstraint.getAttributeName());
    assertNull(actualDbdAttributeConstraint.getValue());
    assertNull(actualDbdAttributeConstraint.getOptions());
    assertNull(actualDbdAttributeConstraint.getAttributeLabel());
    assertNull(actualDbdAttributeConstraint.getFullAttributeName());
    assertNull(actualDbdAttributeConstraint.getCriteria());
    assertNull(actualDbdAttributeConstraint.getEntityAlias());
    assertNull(actualDbdAttributeConstraint.getOperator());
    assertEquals(0, actualDbdAttributeConstraint.getOrderPosition());
    assertEquals(1, actualDbdAttributeConstraint.getOriginalVisualPosition());
    assertEquals(1, actualDbdAttributeConstraint.getVisualPosition());
    assertFalse(actualDbdAttributeConstraint.hasFilter());
    assertFalse(actualDbdAttributeConstraint.isPlainNameReference());
    assertFalse(actualDbdAttributeConstraint.hasCondition());
    assertFalse(actualDbdAttributeConstraint.isOrderDescending());
    assertFalse(actualDbdAttributeConstraint.isReverseOperator());
    assertFalse(actualDbdAttributeConstraint.isVisible());
    assertTrue(actualDbdAttributeConstraint.isDirty());
    assertSame(attribute, actualDbdAttributeConstraint.getAttribute());
  }

  /**
   * Test {@link DBDAttributeConstraint#DBDAttributeConstraint(DBSAttributeBase, int)}.
   *
   * <ul>
   *   <li>Then Attribute return {@link LocalResultSetColumn}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#DBDAttributeConstraint(DBSAttributeBase,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraint.<init>(DBSAttributeBase, int)"})
  public void testNewDBDAttributeConstraint_thenAttributeReturnLocalResultSetColumn() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn attribute =
        new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN);

    // Act
    DBDAttributeConstraint actualDbdAttributeConstraint = new DBDAttributeConstraint(attribute, 1);

    // Assert
    DBSAttributeBase attribute2 = actualDbdAttributeConstraint.getAttribute();
    assertTrue(attribute2 instanceof LocalResultSetColumn);
    assertEquals("Label", actualDbdAttributeConstraint.getAttributeName());
    assertEquals("Label", actualDbdAttributeConstraint.getFullAttributeName());
    assertNull(actualDbdAttributeConstraint.getValue());
    assertNull(actualDbdAttributeConstraint.getOptions());
    assertNull(actualDbdAttributeConstraint.getAttributeLabel());
    assertNull(actualDbdAttributeConstraint.getCriteria());
    assertNull(actualDbdAttributeConstraint.getEntityAlias());
    assertNull(actualDbdAttributeConstraint.getOperator());
    assertEquals(0, actualDbdAttributeConstraint.getOrderPosition());
    assertEquals(1, actualDbdAttributeConstraint.getOriginalVisualPosition());
    assertEquals(1, actualDbdAttributeConstraint.getVisualPosition());
    assertFalse(actualDbdAttributeConstraint.hasFilter());
    assertFalse(actualDbdAttributeConstraint.isPlainNameReference());
    assertFalse(actualDbdAttributeConstraint.hasCondition());
    assertFalse(actualDbdAttributeConstraint.isOrderDescending());
    assertFalse(actualDbdAttributeConstraint.isReverseOperator());
    assertFalse(actualDbdAttributeConstraint.isVisible());
    assertTrue(actualDbdAttributeConstraint.isDirty());
    assertSame(attribute, attribute2);
  }

  /**
   * Test {@link DBDAttributeConstraint#isVisibleByDefault(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#isVisibleByDefault(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.isVisibleByDefault(DBDAttributeBinding)"})
  public void testIsVisibleByDefault_givenFalse_thenReturnTrue() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    when(binding.isPseudoAttribute()).thenReturn(false);

    // Act
    boolean actualIsVisibleByDefaultResult = DBDAttributeConstraint.isVisibleByDefault(binding);

    // Assert
    verify(binding).isPseudoAttribute();
    assertTrue(actualIsVisibleByDefaultResult);
  }

  /**
   * Test {@link DBDAttributeConstraint#isVisibleByDefault(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#isVisibleByDefault(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.isVisibleByDefault(DBDAttributeBinding)"})
  public void testIsVisibleByDefault_givenTrue_thenReturnFalse() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    when(binding.isPseudoAttribute()).thenReturn(true);

    // Act
    boolean actualIsVisibleByDefaultResult = DBDAttributeConstraint.isVisibleByDefault(binding);

    // Assert
    verify(binding).isPseudoAttribute();
    assertFalse(actualIsVisibleByDefaultResult);
  }

  /**
   * Test {@link DBDAttributeConstraint#setAttribute(DBSAttributeBase)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#setAttribute(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraint.setAttribute(DBSAttributeBase)"})
  public void testSetAttribute() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);
    LocalResultSetColumn binding =
        new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN);

    // Act
    dbdAttributeConstraint.setAttribute(binding);

    // Assert
    assertEquals("Label", dbdAttributeConstraint.getAttributeName());
    assertEquals("Label", dbdAttributeConstraint.getFullAttributeName());
    assertSame(binding, dbdAttributeConstraint.getAttribute());
  }

  /**
   * Test {@link DBDAttributeConstraint#getFullAttributeName()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#getFullAttributeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDAttributeConstraint.getFullAttributeName()"})
  public void testGetFullAttributeName() {
    // Arrange, Act and Assert
    assertEquals(
        "Attribute Name", new DBDAttributeConstraint("Attribute Name", 1).getFullAttributeName());
  }

  /**
   * Test {@link DBDAttributeConstraint#hasFilter()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.hasFilter()"})
  public void testHasFilter() {
    // Arrange, Act and Assert
    assertTrue(new DBDAttributeConstraint("Attribute Name", 1).hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraint#hasFilter()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.hasFilter()"})
  public void testHasFilter2() {
    // Arrange, Act and Assert
    assertFalse(new DBDAttributeConstraint("Attribute Name", 0).hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraint#hasFilter()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.hasFilter()"})
  public void testHasFilter3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    // Act and Assert
    assertTrue(dbdAttributeConstraint.hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraint#hasFilter()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.hasFilter()"})
  public void testHasFilter4() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisualPosition(-1);

    // Act and Assert
    assertFalse(dbdAttributeConstraint.hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraint#reset()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraint.reset()"})
  public void testReset() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act
    dbdAttributeConstraint.reset();

    // Assert
    assertEquals(1, dbdAttributeConstraint.getVisualPosition());
    assertFalse(dbdAttributeConstraint.hasFilter());
    assertFalse(dbdAttributeConstraint.isDirty());
    assertTrue(dbdAttributeConstraint.isVisible());
  }

  /**
   * Test {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    // Act and Assert
    assertFalse(
        dbdAttributeConstraint.equalFilters(new DBDAttributeConstraint("Attribute Name", 1), true));
  }

  /**
   * Test {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderDescending(true);

    // Act and Assert
    assertFalse(
        dbdAttributeConstraint.equalFilters(new DBDAttributeConstraint("Attribute Name", 1), true));
  }

  /**
   * Test {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setReverseOperator(true);

    // Act and Assert
    assertFalse(
        dbdAttributeConstraint.equalFilters(new DBDAttributeConstraint("Attribute Name", 1), true));
  }

  /**
   * Test {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertTrue(
        dbdAttributeConstraint.equalFilters(new DBDAttributeConstraint("Attribute Name", 1), true));
  }

  /**
   * Test {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_whenDBDAttributeConstraintBase() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertFalse(dbdAttributeConstraint.equalFilters(new DBDAttributeConstraintBase(), true));
  }

  /**
   * Test {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalFilters(DBDAttributeConstraintBase,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_whenFalse_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertTrue(
        dbdAttributeConstraint.equalFilters(
            new DBDAttributeConstraint("Attribute Name", 1), false));
  }

  /**
   * Test {@link DBDAttributeConstraint#equals(Object)}, and {@link
   * DBDAttributeConstraint#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraint#equals(Object)}
   *   <li>{@link DBDAttributeConstraint#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equals(Object)",
    "int DBDAttributeConstraint.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    DBDAttributeConstraint dbdAttributeConstraint2 =
        new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertEquals(dbdAttributeConstraint, dbdAttributeConstraint2);
    assertEquals(dbdAttributeConstraint.hashCode(), dbdAttributeConstraint2.hashCode());
  }

  /**
   * Test {@link DBDAttributeConstraint#equals(Object)}, and {@link
   * DBDAttributeConstraint#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraint#equals(Object)}
   *   <li>{@link DBDAttributeConstraint#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equals(Object)",
    "int DBDAttributeConstraint.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertEquals(dbdAttributeConstraint, dbdAttributeConstraint);
    int expectedHashCodeResult = dbdAttributeConstraint.hashCode();
    assertEquals(expectedHashCodeResult, dbdAttributeConstraint.hashCode());
  }

  /**
   * Test {@link DBDAttributeConstraint#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equals(Object)",
    "int DBDAttributeConstraint.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBDAttributeConstraint("Attribute Name", 1), 1);
  }

  /**
   * Test {@link DBDAttributeConstraint#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equals(Object)",
    "int DBDAttributeConstraint.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraint, new DBDAttributeConstraint("Attribute Name", 1));
  }

  /**
   * Test {@link DBDAttributeConstraint#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equals(Object)",
    "int DBDAttributeConstraint.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBDAttributeConstraint("Attribute Name", 1), null);
  }

  /**
   * Test {@link DBDAttributeConstraint#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraint.equals(Object)",
    "int DBDAttributeConstraint.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DBDAttributeConstraint("Attribute Name", 1),
        "Different type to DBDAttributeConstraint");
  }

  /**
   * Test {@link DBDAttributeConstraint#toString()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDAttributeConstraint.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("Attribute Name ", new DBDAttributeConstraint("Attribute Name", 1).toString());
  }

  /**
   * Test {@link DBDAttributeConstraint#matches(DBSAttributeBase, boolean)}.
   *
   * <ul>
   *   <li>When {@link AttributeMetaDataProxy#AttributeMetaDataProxy(DBSAttributeBase)} with
   *       attribute is {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#matches(DBSAttributeBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.matches(DBSAttributeBase, boolean)"})
  public void testMatches_whenAttributeMetaDataProxyWithAttributeIsNull_thenReturnFalse() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertFalse(dbdAttributeConstraint.matches(new AttributeMetaDataProxy(null), true));
  }

  /**
   * Test {@link DBDAttributeConstraint#matches(DBSAttributeBase, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#matches(DBSAttributeBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.matches(DBSAttributeBase, boolean)"})
  public void testMatches_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBDAttributeConstraint("Attribute Name", 1).matches(null, false));
  }

  /**
   * Test {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.equalVisibility(DBDAttributeConstraint)"})
  public void testEqualVisibility() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisible(true);

    // Act
    boolean actualEqualVisibilityResult =
        dbdAttributeConstraint.equalVisibility(new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.equalVisibility(DBDAttributeConstraint)"})
  public void testEqualVisibility2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisualPosition(3);

    // Act
    boolean actualEqualVisibilityResult =
        dbdAttributeConstraint.equalVisibility(new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.equalVisibility(DBDAttributeConstraint)"})
  public void testEqualVisibility3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOptions(new Object[] {DBPEvent.RENAME});

    // Act
    boolean actualEqualVisibilityResult =
        dbdAttributeConstraint.equalVisibility(new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraint#equalVisibility(DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraint.equalVisibility(DBDAttributeConstraint)"})
  public void testEqualVisibility_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act
    boolean actualEqualVisibilityResult =
        dbdAttributeConstraint.equalVisibility(new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertTrue(actualEqualVisibilityResult);
  }
}
