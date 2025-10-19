package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDDataFilterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDDataFilter#DBDDataFilter()}
   *   <li>{@link DBDDataFilter#setAnyConstraint(boolean)}
   *   <li>{@link DBDDataFilter#setOrder(String)}
   *   <li>{@link DBDDataFilter#setUseDisjunctiveNormalForm(boolean)}
   *   <li>{@link DBDDataFilter#setWhere(String)}
   *   <li>{@link DBDDataFilter#serialize(Map)}
   *   <li>{@link DBDDataFilter#getConstraints()}
   *   <li>{@link DBDDataFilter#getOrder()}
   *   <li>{@link DBDDataFilter#getWhere()}
   *   <li>{@link DBDDataFilter#isAnyConstraint()}
   *   <li>{@link DBDDataFilter#isUseDisjunctiveNormalForm()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDDataFilter.<init>()",
    "void DBDDataFilter.<init>(List)",
    "List DBDDataFilter.getConstraints()",
    "String DBDDataFilter.getOrder()",
    "String DBDDataFilter.getWhere()",
    "boolean DBDDataFilter.isAnyConstraint()",
    "boolean DBDDataFilter.isUseDisjunctiveNormalForm()",
    "void DBDDataFilter.serialize(Map)",
    "void DBDDataFilter.setAnyConstraint(boolean)",
    "void DBDDataFilter.setOrder(String)",
    "void DBDDataFilter.setUseDisjunctiveNormalForm(boolean)",
    "void DBDDataFilter.setWhere(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBDDataFilter actualDbdDataFilter = new DBDDataFilter();
    actualDbdDataFilter.setAnyConstraint(true);
    actualDbdDataFilter.setOrder("Order");
    actualDbdDataFilter.setUseDisjunctiveNormalForm(true);
    actualDbdDataFilter.setWhere("Where");
    actualDbdDataFilter.serialize(new HashMap<>());
    List<DBDAttributeConstraint> actualConstraints = actualDbdDataFilter.getConstraints();
    String actualOrder = actualDbdDataFilter.getOrder();
    String actualWhere = actualDbdDataFilter.getWhere();
    boolean actualIsAnyConstraintResult = actualDbdDataFilter.isAnyConstraint();
    boolean actualIsUseDisjunctiveNormalFormResult =
        actualDbdDataFilter.isUseDisjunctiveNormalForm();

    // Assert
    assertEquals("Order", actualOrder);
    assertEquals("Where", actualWhere);
    assertTrue(actualConstraints.isEmpty());
    assertTrue(actualIsAnyConstraintResult);
    assertTrue(actualIsUseDisjunctiveNormalFormResult);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Constraints is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDDataFilter#DBDDataFilter(List)}
   *   <li>{@link DBDDataFilter#setAnyConstraint(boolean)}
   *   <li>{@link DBDDataFilter#setOrder(String)}
   *   <li>{@link DBDDataFilter#setUseDisjunctiveNormalForm(boolean)}
   *   <li>{@link DBDDataFilter#setWhere(String)}
   *   <li>{@link DBDDataFilter#serialize(Map)}
   *   <li>{@link DBDDataFilter#getConstraints()}
   *   <li>{@link DBDDataFilter#getOrder()}
   *   <li>{@link DBDDataFilter#getWhere()}
   *   <li>{@link DBDDataFilter#isAnyConstraint()}
   *   <li>{@link DBDDataFilter#isUseDisjunctiveNormalForm()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDDataFilter.<init>()",
    "void DBDDataFilter.<init>(List)",
    "List DBDDataFilter.getConstraints()",
    "String DBDDataFilter.getOrder()",
    "String DBDDataFilter.getWhere()",
    "boolean DBDDataFilter.isAnyConstraint()",
    "boolean DBDDataFilter.isUseDisjunctiveNormalForm()",
    "void DBDDataFilter.serialize(Map)",
    "void DBDDataFilter.setAnyConstraint(boolean)",
    "void DBDDataFilter.setOrder(String)",
    "void DBDDataFilter.setUseDisjunctiveNormalForm(boolean)",
    "void DBDDataFilter.setWhere(String)"
  })
  public void testGettersAndSetters_whenArrayList_thenReturnConstraintsIsArrayList() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();

    // Act
    DBDDataFilter actualDbdDataFilter = new DBDDataFilter(constraints);
    actualDbdDataFilter.setAnyConstraint(true);
    actualDbdDataFilter.setOrder("Order");
    actualDbdDataFilter.setUseDisjunctiveNormalForm(true);
    actualDbdDataFilter.setWhere("Where");
    actualDbdDataFilter.serialize(new HashMap<>());
    List<DBDAttributeConstraint> actualConstraints = actualDbdDataFilter.getConstraints();
    String actualOrder = actualDbdDataFilter.getOrder();
    String actualWhere = actualDbdDataFilter.getWhere();
    boolean actualIsAnyConstraintResult = actualDbdDataFilter.isAnyConstraint();
    boolean actualIsUseDisjunctiveNormalFormResult =
        actualDbdDataFilter.isUseDisjunctiveNormalForm();

    // Assert
    assertEquals("Order", actualOrder);
    assertEquals("Where", actualWhere);
    assertTrue(actualConstraints.isEmpty());
    assertTrue(actualIsAnyConstraintResult);
    assertTrue(actualIsUseDisjunctiveNormalFormResult);
    assertSame(constraints, actualConstraints);
  }

  /**
   * Test {@link DBDDataFilter#DBDDataFilter(DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return Constraints size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#DBDDataFilter(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.<init>(DBDDataFilter)"})
  public void testNewDBDDataFilter_thenReturnConstraintsSizeIsOne() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    constraints.add(dbdAttributeConstraint);

    // Act
    DBDDataFilter actualDbdDataFilter = new DBDDataFilter(new DBDDataFilter(constraints));

    // Assert
    List<DBDAttributeConstraint> constraints2 = actualDbdDataFilter.getConstraints();
    assertEquals(1, constraints2.size());
    assertTrue(actualDbdDataFilter.hasFilters());
    assertTrue(actualDbdDataFilter.isDirty());
    assertEquals(dbdAttributeConstraint, constraints2.get(0));
  }

  /**
   * Test {@link DBDDataFilter#DBDDataFilter(DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return Order is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#DBDDataFilter(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.<init>(DBDDataFilter)"})
  public void testNewDBDDataFilter_whenDBDDataFilter_thenReturnOrderIsNull() {
    // Arrange and Act
    DBDDataFilter actualDbdDataFilter = new DBDDataFilter(new DBDDataFilter());

    // Assert
    assertNull(actualDbdDataFilter.getOrder());
    assertNull(actualDbdDataFilter.getWhere());
    assertEquals(0, actualDbdDataFilter.getMaxOrderingPosition());
    assertFalse(actualDbdDataFilter.hasConditions());
    assertFalse(actualDbdDataFilter.hasFilters());
    assertFalse(actualDbdDataFilter.hasHiddenAttributes());
    assertFalse(actualDbdDataFilter.hasOrdering());
    assertFalse(actualDbdDataFilter.hasPinnedAttributes());
    assertFalse(actualDbdDataFilter.isAnyConstraint());
    assertFalse(actualDbdDataFilter.isDirty());
    assertFalse(actualDbdDataFilter.isUseDisjunctiveNormalForm());
    assertTrue(actualDbdDataFilter.getConstraints().isEmpty());
    assertTrue(actualDbdDataFilter.getOrderConstraints().isEmpty());
    assertTrue(actualDbdDataFilter.getOrderedVisibleAttributes().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#hasHiddenAttributes()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasHiddenAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasHiddenAttributes()"})
  public void testHasHiddenAttributes() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasHiddenAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasHiddenAttributes()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasHiddenAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasHiddenAttributes()"})
  public void testHasHiddenAttributes2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisible(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasHiddenAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasHiddenAttributes()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasHiddenAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasHiddenAttributes()"})
  public void testHasHiddenAttributes_givenDBDDataFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().hasHiddenAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasPinnedAttributes()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasPinnedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasPinnedAttributes()"})
  public void testHasPinnedAttributes() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOptions(new Object[] {DBPEvent.RENAME});

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasPinnedAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasPinnedAttributes()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasPinnedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasPinnedAttributes()"})
  public void testHasPinnedAttributes2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOptions(
        new Object[] {
          new DBDAttributeConstraint(DBDAttributeConstraintBase.ATTR_OPTION_PINNED, 1)
        });

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasPinnedAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasPinnedAttributes()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasPinnedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasPinnedAttributes()"})
  public void testHasPinnedAttributes_givenDBDDataFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().hasPinnedAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasPinnedAttributes()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasPinnedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasPinnedAttributes()"})
  public void testHasPinnedAttributes_thenReturnFalse() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasPinnedAttributes());
  }

  /**
   * Test {@link DBDDataFilter#hasPinnedAttributes()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasPinnedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasPinnedAttributes()"})
  public void testHasPinnedAttributes_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOption(
        DBDAttributeConstraintBase.ATTR_OPTION_PINNED, DBPEvent.RENAME);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertTrue(new DBDDataFilter(constraints).hasPinnedAttributes());
  }

  /**
   * Test {@link DBDDataFilter#getConstraint(DBSAttributeBase, boolean)} with {@code attribute},
   * {@code metaChanged}.
   *
   * <p>Method under test: {@link DBDDataFilter#getConstraint(DBSAttributeBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDAttributeConstraint DBDDataFilter.getConstraint(DBSAttributeBase, boolean)"
  })
  public void testGetConstraintWithAttributeMetaChanged() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertSame(dbdAttributeConstraint, new DBDDataFilter(constraints).getConstraint(null, false));
  }

  /**
   * Test {@link DBDDataFilter#getConstraint(DBSAttributeBase, boolean)} with {@code attribute},
   * {@code metaChanged}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getConstraint(DBSAttributeBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDAttributeConstraint DBDDataFilter.getConstraint(DBSAttributeBase, boolean)"
  })
  public void testGetConstraintWithAttributeMetaChanged_givenDBDDataFilter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBDDataFilter().getConstraint(null, false));
  }

  /**
   * Test {@link DBDDataFilter#getConstraint(DBDAttributeBinding)} with {@code binding}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getConstraint(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDAttributeConstraint DBDDataFilter.getConstraint(DBDAttributeBinding)"})
  public void testGetConstraintWithBinding_givenDBDDataFilter_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBDDataFilter().getConstraint((DBDAttributeBinding) null));
  }

  /**
   * Test {@link DBDDataFilter#getConstraint(String)} with {@code name}.
   *
   * <p>Method under test: {@link DBDDataFilter#getConstraint(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDAttributeConstraint DBDDataFilter.getConstraint(String)"})
  public void testGetConstraintWithName() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertNull(new DBDDataFilter(constraints).getConstraint("Name"));
  }

  /**
   * Test {@link DBDDataFilter#getConstraint(String)} with {@code name}.
   *
   * <p>Method under test: {@link DBDDataFilter#getConstraint(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDAttributeConstraint DBDDataFilter.getConstraint(String)"})
  public void testGetConstraintWithName2() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Name", 1);
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertSame(dbdAttributeConstraint, new DBDDataFilter(constraints).getConstraint("Name"));
  }

  /**
   * Test {@link DBDDataFilter#getConstraint(String)} with {@code name}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getConstraint(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDAttributeConstraint DBDDataFilter.getConstraint(String)"})
  public void testGetConstraintWithName_givenDBDDataFilter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBDDataFilter().getConstraint("Name"));
  }

  /**
   * Test {@link DBDDataFilter#addConstraints(List)}.
   *
   * <ul>
   *   <li>Then {@link DBDDataFilter#DBDDataFilter()} Constraints is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#addConstraints(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.addConstraints(List)"})
  public void testAddConstraints_thenDBDDataFilterConstraintsIsArrayList() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act
    dbdDataFilter.addConstraints(constraints);

    // Assert
    assertTrue(dbdDataFilter.hasFilters());
    assertTrue(dbdDataFilter.isDirty());
    assertEquals(constraints, dbdDataFilter.getConstraints());
  }

  /**
   * Test {@link DBDDataFilter#addConstraints(List)}.
   *
   * <ul>
   *   <li>Then {@link DBDDataFilter#DBDDataFilter()} Constraints size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#addConstraints(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.addConstraints(List)"})
  public void testAddConstraints_thenDBDDataFilterConstraintsSizeIsOne() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    constraints.add(dbdAttributeConstraint);

    // Act
    dbdDataFilter.addConstraints(constraints);

    // Assert
    List<DBDAttributeConstraint> constraints2 = dbdDataFilter.getConstraints();
    assertEquals(1, constraints2.size());
    assertTrue(dbdDataFilter.hasFilters());
    assertTrue(dbdDataFilter.isDirty());
    assertSame(dbdAttributeConstraint, constraints2.get(0));
  }

  /**
   * Test {@link DBDDataFilter#addConstraints(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link DBDDataFilter#DBDDataFilter()} hasFilters.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#addConstraints(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.addConstraints(List)"})
  public void testAddConstraints_whenArrayList_thenNotDBDDataFilterHasFilters() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    // Act
    dbdDataFilter.addConstraints(new ArrayList<>());

    // Assert that nothing has changed
    assertFalse(dbdDataFilter.hasFilters());
    assertFalse(dbdDataFilter.isDirty());
    assertTrue(dbdDataFilter.getConstraints().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#getOrderedVisibleAttributes()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getOrderedVisibleAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDDataFilter.getOrderedVisibleAttributes()"})
  public void testGetOrderedVisibleAttributes_givenDBDDataFilter_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new DBDDataFilter().getOrderedVisibleAttributes().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#getOrderedVisibleAttributes()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getOrderedVisibleAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDDataFilter.getOrderedVisibleAttributes()"})
  public void testGetOrderedVisibleAttributes_thenReturnEmpty() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertTrue(new DBDDataFilter(constraints).getOrderedVisibleAttributes().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#getOrderedVisibleAttributes()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getOrderedVisibleAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDDataFilter.getOrderedVisibleAttributes()"})
  public void testGetOrderedVisibleAttributes_thenReturnSizeIsOne() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisible(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act
    List<DBSAttributeBase> actualOrderedVisibleAttributes =
        new DBDDataFilter(constraints).getOrderedVisibleAttributes();

    // Assert
    assertEquals(1, actualOrderedVisibleAttributes.size());
    assertNull(actualOrderedVisibleAttributes.get(0));
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertTrue(new DBDDataFilter(constraints).hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters2() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 0));

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertTrue(new DBDDataFilter(constraints).hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} Order is empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters_givenDBDDataFilterOrderIsEmptyString_thenReturnFalse() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setOrder("");

    // Act and Assert
    assertFalse(dbdDataFilter.hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} Order is {@code Order}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters_givenDBDDataFilterOrderIsOrder_thenReturnTrue() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setOrder("Order");

    // Act and Assert
    assertTrue(dbdDataFilter.hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} Where is {@code Where}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters_givenDBDDataFilterWhereIsWhere_thenReturnTrue() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setWhere("Where");

    // Act and Assert
    assertTrue(dbdDataFilter.hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasFilters()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasFilters()"})
  public void testHasFilters_givenDBDDataFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().hasFilters());
  }

  /**
   * Test {@link DBDDataFilter#hasConditions()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasConditions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasConditions()"})
  public void testHasConditions_givenDBDDataFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().hasConditions());
  }

  /**
   * Test {@link DBDDataFilter#hasConditions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasConditions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasConditions()"})
  public void testHasConditions_thenReturnFalse() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertFalse(dbdDataFilter.hasConditions());
  }

  /**
   * Test {@link DBDDataFilter#hasConditions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasConditions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasConditions()"})
  public void testHasConditions_thenReturnTrue() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setWhere("foo");

    // Act and Assert
    assertTrue(dbdDataFilter.hasConditions());
  }

  /**
   * Test {@link DBDDataFilter#hasOrdering()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasOrdering()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasOrdering()"})
  public void testHasOrdering() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasOrdering());
  }

  /**
   * Test {@link DBDDataFilter#hasOrdering()}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasOrdering()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasOrdering()"})
  public void testHasOrdering2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertTrue(new DBDDataFilter(constraints).hasOrdering());
  }

  /**
   * Test {@link DBDDataFilter#hasOrdering()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} Order is empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasOrdering()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasOrdering()"})
  public void testHasOrdering_givenDBDDataFilterOrderIsEmptyString_thenReturnFalse() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setOrder("");

    // Act and Assert
    assertFalse(dbdDataFilter.hasOrdering());
  }

  /**
   * Test {@link DBDDataFilter#hasOrdering()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} Order is {@code Order}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasOrdering()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasOrdering()"})
  public void testHasOrdering_givenDBDDataFilterOrderIsOrder_thenReturnTrue() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setOrder("Order");

    // Act and Assert
    assertTrue(dbdDataFilter.hasOrdering());
  }

  /**
   * Test {@link DBDDataFilter#hasOrdering()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasOrdering()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasOrdering()"})
  public void testHasOrdering_givenDBDDataFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().hasOrdering());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 0));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 0);
    dbdAttributeConstraint.setVisible(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty4() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 0);
    dbdAttributeConstraint.setOptions(new Object[] {DBPEvent.RENAME});
    dbdAttributeConstraint.setVisible(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty5() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 0);
    dbdAttributeConstraint.setOptions(new Object[] {});
    dbdAttributeConstraint.setVisible(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter(List)} with constraints is {@link
   *       ArrayList#ArrayList()} Order is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty_givenDBDDataFilterWithConstraintsIsArrayListOrderIsFoo() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("foo");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter(List)} with constraints is {@link
   *       ArrayList#ArrayList()} Where is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty_givenDBDDataFilterWithConstraintsIsArrayListWhereIsFoo() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("foo");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty_givenDBDDataFilter_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().isDirty());
  }

  /**
   * Test {@link DBDDataFilter#isDirty()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.isDirty()"})
  public void testIsDirty_thenReturnTrue() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder("");
    dbdDataFilter.setWhere("");

    // Act and Assert
    assertTrue(dbdDataFilter.isDirty());
  }

  /**
   * Test {@link DBDDataFilter#getOrderConstraints()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getOrderConstraints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDDataFilter.getOrderConstraints()"})
  public void testGetOrderConstraints_givenDBDDataFilter_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new DBDDataFilter().getOrderConstraints().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#getOrderConstraints()}.
   *
   * <ul>
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getOrderConstraints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDDataFilter.getOrderConstraints()"})
  public void testGetOrderConstraints_thenReturnArrayList() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertEquals(constraints, new DBDDataFilter(constraints).getOrderConstraints());
  }

  /**
   * Test {@link DBDDataFilter#getOrderConstraints()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getOrderConstraints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDDataFilter.getOrderConstraints()"})
  public void testGetOrderConstraints_thenReturnEmpty() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertTrue(new DBDDataFilter(constraints).getOrderConstraints().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#getMaxOrderingPosition()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getMaxOrderingPosition()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDDataFilter.getMaxOrderingPosition()"})
  public void testGetMaxOrderingPosition_givenDBDDataFilter_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBDDataFilter().getMaxOrderingPosition());
  }

  /**
   * Test {@link DBDDataFilter#getMaxOrderingPosition()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getMaxOrderingPosition()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDDataFilter.getMaxOrderingPosition()"})
  public void testGetMaxOrderingPosition_thenReturnOne() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act and Assert
    assertEquals(1, new DBDDataFilter(constraints).getMaxOrderingPosition());
  }

  /**
   * Test {@link DBDDataFilter#getMaxOrderingPosition()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#getMaxOrderingPosition()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDDataFilter.getMaxOrderingPosition()"})
  public void testGetMaxOrderingPosition_thenReturnZero() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertEquals(0, new DBDDataFilter(constraints).getMaxOrderingPosition());
  }

  /**
   * Test {@link DBDDataFilter#resetOrderBy()}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeConstraint#setOrderDescending(boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#resetOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.resetOrderBy()"})
  public void testResetOrderBy_thenCallsSetOrderDescending() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = mock(DBDAttributeConstraint.class);
    doNothing().when(dbdAttributeConstraint).setOrderDescending(anyBoolean());
    doNothing().when(dbdAttributeConstraint).setOrderPosition(anyInt());

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);

    // Act
    new DBDDataFilter(constraints).resetOrderBy();

    // Assert
    verify(dbdAttributeConstraint).setOrderDescending(false);
    verify(dbdAttributeConstraint).setOrderPosition(0);
  }

  /**
   * Test {@link DBDDataFilter#reset()}.
   *
   * <p>Method under test: {@link DBDDataFilter#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.reset()"})
  public void testReset() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    // Act
    dbdDataFilter.reset();

    // Assert
    List<DBSAttributeBase> orderedVisibleAttributes = dbdDataFilter.getOrderedVisibleAttributes();
    assertEquals(1, orderedVisibleAttributes.size());
    assertNull(orderedVisibleAttributes.get(0));
    List<DBDAttributeConstraint> constraints2 = dbdDataFilter.getConstraints();
    assertEquals(1, constraints2.size());
    DBDAttributeConstraint getResult = constraints2.get(0);
    assertEquals(1, getResult.getVisualPosition());
    assertFalse(getResult.hasFilter());
    assertFalse(getResult.isDirty());
    assertFalse(dbdDataFilter.hasFilters());
    assertFalse(dbdDataFilter.isDirty());
    assertTrue(getResult.isVisible());
  }

  /**
   * Test {@link DBDDataFilter#reset()}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then not {@link DBDDataFilter#DBDDataFilter()} hasFilters.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDataFilter.reset()"})
  public void testReset_givenDBDDataFilter_thenNotDBDDataFilterHasFilters() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    // Act
    dbdDataFilter.reset();

    // Assert that nothing has changed
    assertFalse(dbdDataFilter.hasFilters());
    assertFalse(dbdDataFilter.isDirty());
    assertTrue(dbdDataFilter.getOrderedVisibleAttributes().isEmpty());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}, and {@link DBDDataFilter#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    DBDDataFilter dbdDataFilter2 = new DBDDataFilter();

    // Act and Assert
    assertEquals(dbdDataFilter, dbdDataFilter2);
    assertNotEquals(dbdDataFilter.hashCode(), dbdDataFilter2.hashCode());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}, and {@link DBDDataFilter#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    // Act and Assert
    assertEquals(dbdDataFilter, dbdDataFilter);
    int expectedHashCodeResult = dbdDataFilter.hashCode();
    assertEquals(expectedHashCodeResult, dbdDataFilter.hashCode());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBDDataFilter(), 1);
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setAnyConstraint(true);

    // Act and Assert
    assertNotEquals(dbdDataFilter, new DBDDataFilter());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setOrder("Order");

    // Act and Assert
    assertNotEquals(dbdDataFilter, new DBDDataFilter());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setWhere("Where");

    // Act and Assert
    assertNotEquals(dbdDataFilter, new DBDDataFilter());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    // Act and Assert
    assertNotEquals(dbdDataFilter, new DBDDataFilter());
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBDDataFilter(), null);
  }

  /**
   * Test {@link DBDDataFilter#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBDDataFilter(), "Different type to DBDDataFilter");
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 18));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere(null);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(18);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere(null);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderDescending(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere(null);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters4() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setReverseOperator(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere(null);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} AnyConstraint is {@code true}.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_givenDBDDataFilterAnyConstraintIsTrue_whenDBDDataFilter() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setAnyConstraint(true);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(new DBDDataFilter(), true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()} Order is {@code Order}.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_givenDBDDataFilterOrderIsOrder_whenDBDDataFilter() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();
    dbdDataFilter.setOrder("Order");

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(new DBDDataFilter(), true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_givenDBDDataFilter_whenDBDDataFilter_thenReturnTrue() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    // Act and Assert
    assertTrue(dbdDataFilter.equalFilters(new DBDDataFilter(), true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_thenReturnTrue() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere(null);

    // Act and Assert
    assertTrue(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_whenArrayListAddNull_thenReturnFalse() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(null);
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere(null);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter(List)} with constraints is {@link
   *       ArrayList#ArrayList()} Order is {@code Source}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_whenDBDDataFilterWithConstraintsIsArrayListOrderIsSource() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder("Source");
    source.setWhere(null);

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter(List)} with constraints is {@link
   *       ArrayList#ArrayList()} Where is {@code Source}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalFilters(DBDDataFilter, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalFilters(DBDDataFilter, boolean)"})
  public void testEqualFilters_whenDBDDataFilterWithConstraintsIsArrayListWhereIsSource() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);
    dbdDataFilter.setOrder(null);
    dbdDataFilter.setWhere(null);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter source = new DBDDataFilter(constraints2);
    source.setOrder(null);
    source.setWhere("Source");

    // Act and Assert
    assertFalse(dbdDataFilter.equalFilters(source, true));
  }

  /**
   * Test {@link DBDDataFilter#equalVisibility(DBDDataFilter)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalVisibility(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalVisibility(DBDDataFilter)"})
  public void testEqualVisibility() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 18));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act
    boolean actualEqualVisibilityResult =
        dbdDataFilter.equalVisibility(new DBDDataFilter(constraints2));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDDataFilter#equalVisibility(DBDDataFilter)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalVisibility(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalVisibility(DBDDataFilter)"})
  public void testEqualVisibility2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisible(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act
    boolean actualEqualVisibilityResult =
        dbdDataFilter.equalVisibility(new DBDDataFilter(constraints2));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDDataFilter#equalVisibility(DBDDataFilter)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalVisibility(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalVisibility(DBDDataFilter)"})
  public void testEqualVisibility3() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setVisualPosition(18);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act
    boolean actualEqualVisibilityResult =
        dbdDataFilter.equalVisibility(new DBDDataFilter(constraints2));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDDataFilter#equalVisibility(DBDDataFilter)}.
   *
   * <p>Method under test: {@link DBDDataFilter#equalVisibility(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalVisibility(DBDDataFilter)"})
  public void testEqualVisibility4() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOptions(new Object[] {DBPEvent.RENAME});

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act
    boolean actualEqualVisibilityResult =
        dbdDataFilter.equalVisibility(new DBDDataFilter(constraints2));

    // Assert
    assertFalse(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDDataFilter#equalVisibility(DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalVisibility(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalVisibility(DBDDataFilter)"})
  public void testEqualVisibility_givenDBDDataFilter_whenDBDDataFilter_thenReturnTrue() {
    // Arrange
    DBDDataFilter dbdDataFilter = new DBDDataFilter();

    // Act and Assert
    assertTrue(dbdDataFilter.equalVisibility(new DBDDataFilter()));
  }

  /**
   * Test {@link DBDDataFilter#equalVisibility(DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#equalVisibility(DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.equalVisibility(DBDDataFilter)"})
  public void testEqualVisibility_thenReturnTrue() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    DBDDataFilter dbdDataFilter = new DBDDataFilter(constraints);

    ArrayList<DBDAttributeConstraint> constraints2 = new ArrayList<>();
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints2.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act
    boolean actualEqualVisibilityResult =
        dbdDataFilter.equalVisibility(new DBDDataFilter(constraints2));

    // Assert
    assertTrue(actualEqualVisibilityResult);
  }

  /**
   * Test {@link DBDDataFilter#hasNameDuplicates(String)}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasNameDuplicates(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasNameDuplicates(String)"})
  public void testHasNameDuplicates() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    // Act and Assert
    assertFalse(new DBDDataFilter(constraints).hasNameDuplicates("Name"));
  }

  /**
   * Test {@link DBDDataFilter#hasNameDuplicates(String)}.
   *
   * <p>Method under test: {@link DBDDataFilter#hasNameDuplicates(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasNameDuplicates(String)"})
  public void testHasNameDuplicates2() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(
        new DBDAttributeConstraint("org.jkiss.dbeaver.model.data.DBDAttributeBinding", 1));

    // Act and Assert
    assertFalse(
        new DBDDataFilter(constraints)
            .hasNameDuplicates("org.jkiss.dbeaver.model.data.DBDAttributeBinding"));
  }

  /**
   * Test {@link DBDDataFilter#hasNameDuplicates(String)}.
   *
   * <ul>
   *   <li>Given {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>When {@code Name}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasNameDuplicates(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasNameDuplicates(String)"})
  public void testHasNameDuplicates_givenDBDDataFilter_whenName_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDDataFilter().hasNameDuplicates("Name"));
  }

  /**
   * Test {@link DBDDataFilter#hasNameDuplicates(String)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataFilter#hasNameDuplicates(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDataFilter.hasNameDuplicates(String)"})
  public void testHasNameDuplicates_thenReturnTrue() {
    // Arrange
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(
        new DBDAttributeConstraint("org.jkiss.dbeaver.model.data.DBDAttributeBinding", 1));
    constraints.add(
        new DBDAttributeConstraint("org.jkiss.dbeaver.model.data.DBDAttributeBinding", 1));

    // Act and Assert
    assertTrue(
        new DBDDataFilter(constraints)
            .hasNameDuplicates("org.jkiss.dbeaver.model.data.DBDAttributeBinding"));
  }
}
