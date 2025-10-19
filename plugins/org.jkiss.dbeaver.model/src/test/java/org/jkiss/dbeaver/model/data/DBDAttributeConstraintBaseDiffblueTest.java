package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDAttributeConstraintBaseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}
   *   <li>{@link DBDAttributeConstraintBase#setEntityAlias(String)}
   *   <li>{@link DBDAttributeConstraintBase#setOptions(Object[])}
   *   <li>{@link DBDAttributeConstraintBase#setOrderDescending(boolean)}
   *   <li>{@link DBDAttributeConstraintBase#setOrderPosition(int)}
   *   <li>{@link DBDAttributeConstraintBase#setReverseOperator(boolean)}
   *   <li>{@link DBDAttributeConstraintBase#setVisible(boolean)}
   *   <li>{@link DBDAttributeConstraintBase#setVisualPosition(int)}
   *   <li>{@link DBDAttributeConstraintBase#getCriteria()}
   *   <li>{@link DBDAttributeConstraintBase#getEntityAlias()}
   *   <li>{@link DBDAttributeConstraintBase#getOperator()}
   *   <li>{@link DBDAttributeConstraintBase#getOptions()}
   *   <li>{@link DBDAttributeConstraintBase#getOrderPosition()}
   *   <li>{@link DBDAttributeConstraintBase#getValue()}
   *   <li>{@link DBDAttributeConstraintBase#getVisualPosition()}
   *   <li>{@link DBDAttributeConstraintBase#isOrderDescending()}
   *   <li>{@link DBDAttributeConstraintBase#isReverseOperator()}
   *   <li>{@link DBDAttributeConstraintBase#isVisible()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDAttributeConstraintBase.<init>()",
    "String DBDAttributeConstraintBase.getCriteria()",
    "String DBDAttributeConstraintBase.getEntityAlias()",
    "DBCLogicalOperator DBDAttributeConstraintBase.getOperator()",
    "Object[] DBDAttributeConstraintBase.getOptions()",
    "int DBDAttributeConstraintBase.getOrderPosition()",
    "Object DBDAttributeConstraintBase.getValue()",
    "int DBDAttributeConstraintBase.getVisualPosition()",
    "boolean DBDAttributeConstraintBase.isOrderDescending()",
    "boolean DBDAttributeConstraintBase.isReverseOperator()",
    "boolean DBDAttributeConstraintBase.isVisible()",
    "void DBDAttributeConstraintBase.setEntityAlias(String)",
    "void DBDAttributeConstraintBase.setOptions(Object[])",
    "void DBDAttributeConstraintBase.setOrderDescending(boolean)",
    "void DBDAttributeConstraintBase.setOrderPosition(int)",
    "void DBDAttributeConstraintBase.setReverseOperator(boolean)",
    "void DBDAttributeConstraintBase.setVisible(boolean)",
    "void DBDAttributeConstraintBase.setVisualPosition(int)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBDAttributeConstraintBase actualDbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    actualDbdAttributeConstraintBase.setEntityAlias("Entity Alias");
    Object[] options = new Object[] {DBPEvent.RENAME};
    actualDbdAttributeConstraintBase.setOptions(options);
    actualDbdAttributeConstraintBase.setOrderDescending(true);
    actualDbdAttributeConstraintBase.setOrderPosition(1);
    actualDbdAttributeConstraintBase.setReverseOperator(true);
    actualDbdAttributeConstraintBase.setVisible(true);
    actualDbdAttributeConstraintBase.setVisualPosition(1);
    String actualCriteria = actualDbdAttributeConstraintBase.getCriteria();
    String actualEntityAlias = actualDbdAttributeConstraintBase.getEntityAlias();
    DBCLogicalOperator actualOperator = actualDbdAttributeConstraintBase.getOperator();
    Object[] actualOptions = actualDbdAttributeConstraintBase.getOptions();
    int actualOrderPosition = actualDbdAttributeConstraintBase.getOrderPosition();
    Object actualValue = actualDbdAttributeConstraintBase.getValue();
    int actualVisualPosition = actualDbdAttributeConstraintBase.getVisualPosition();
    boolean actualIsOrderDescendingResult = actualDbdAttributeConstraintBase.isOrderDescending();
    boolean actualIsReverseOperatorResult = actualDbdAttributeConstraintBase.isReverseOperator();

    // Assert
    assertEquals("Entity Alias", actualEntityAlias);
    assertNull(actualValue);
    assertNull(actualCriteria);
    assertNull(actualOperator);
    assertEquals(1, actualOrderPosition);
    assertEquals(1, actualVisualPosition);
    assertEquals(1, actualOptions.length);
    assertTrue(actualIsOrderDescendingResult);
    assertTrue(actualIsReverseOperatorResult);
    assertTrue(actualDbdAttributeConstraintBase.isVisible());
    assertSame(options, actualOptions);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase(DBDAttributeConstraintBase)}.
   *
   * <ul>
   *   <li>Then return {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#DBDAttributeConstraintBase(DBDAttributeConstraintBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.<init>(DBDAttributeConstraintBase)"})
  public void testNewDBDAttributeConstraintBase_thenReturnDBDAttributeConstraintBase() {
    // Arrange
    DBDAttributeConstraintBase source = new DBDAttributeConstraintBase();

    // Act
    DBDAttributeConstraintBase actualDbdAttributeConstraintBase =
        new DBDAttributeConstraintBase(source);

    // Assert
    assertEquals(source, actualDbdAttributeConstraintBase);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#setCriteria(String)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#setCriteria(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.setCriteria(String)"})
  public void testSetCriteria() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act
    dbdAttributeConstraintBase.setCriteria("Criteria");

    // Assert
    assertEquals("Criteria", dbdAttributeConstraintBase.getCriteria());
    assertTrue(dbdAttributeConstraintBase.hasCondition());
    assertTrue(dbdAttributeConstraintBase.hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#setOperator(DBCLogicalOperator)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#setOperator(DBCLogicalOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.setOperator(DBCLogicalOperator)"})
  public void testSetOperator() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act
    dbdAttributeConstraintBase.setOperator(DBCLogicalOperator.EQUALS);

    // Assert
    assertEquals(DBCLogicalOperator.EQUALS, dbdAttributeConstraintBase.getOperator());
    assertTrue(dbdAttributeConstraintBase.hasCondition());
    assertTrue(dbdAttributeConstraintBase.hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#setValue(Object)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#setValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.setValue(Object)"})
  public void testSetValue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    Object object = DBPEvent.RENAME;

    // Act
    dbdAttributeConstraintBase.setValue(object);

    // Assert
    assertSame(object, dbdAttributeConstraintBase.getValue());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasFilter()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasFilter()"})
  public void testHasFilter() {
    // Arrange, Act and Assert
    assertTrue(new DBDAttributeConstraint("Attribute Name", 1).hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasFilter()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} OrderPosition is
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasFilter()"})
  public void testHasFilter_givenDBDAttributeConstraintBaseOrderPositionIsOne_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOrderPosition(1);

    // Act and Assert
    assertTrue(dbdAttributeConstraintBase.hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasFilter()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasFilter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasFilter()"})
  public void testHasFilter_givenDBDAttributeConstraintBase_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDAttributeConstraintBase().hasFilter());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#isDirty()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.isDirty()"})
  public void testIsDirty() {
    // Arrange, Act and Assert
    assertTrue(new DBDAttributeConstraint("Attribute Name", 1).isDirty());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} Options is array of
   *       {@link Object} with {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.isDirty()"})
  public void testIsDirty_givenDBDAttributeConstraintBaseOptionsIsArrayOfObjectWithRename() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOptions(new Object[] {DBPEvent.RENAME});
    dbdAttributeConstraintBase.setVisible(true);

    // Act and Assert
    assertTrue(dbdAttributeConstraintBase.isDirty());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} Options is empty
   *       array of {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.isDirty()"})
  public void testIsDirty_givenDBDAttributeConstraintBaseOptionsIsEmptyArrayOfObject() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOptions(new Object[] {});
    dbdAttributeConstraintBase.setVisible(true);

    // Act and Assert
    assertFalse(dbdAttributeConstraintBase.isDirty());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} OrderPosition is
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.isDirty()"})
  public void testIsDirty_givenDBDAttributeConstraintBaseOrderPositionIsOne_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOrderPosition(1);

    // Act and Assert
    assertTrue(dbdAttributeConstraintBase.isDirty());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} Visible is {@code
   *       true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.isDirty()"})
  public void testIsDirty_givenDBDAttributeConstraintBaseVisibleIsTrue_thenReturnFalse() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setVisible(true);

    // Act and Assert
    assertFalse(dbdAttributeConstraintBase.isDirty());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#isDirty()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.isDirty()"})
  public void testIsDirty_givenDBDAttributeConstraintBase_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBDAttributeConstraintBase().isDirty());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasCondition()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasCondition()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasCondition()"})
  public void testHasCondition() {
    // Arrange, Act and Assert
    assertFalse(new DBDAttributeConstraintBase().hasCondition());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasOption(String)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasOption(String)"})
  public void testHasOption() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase =
        new DBDAttributeConstraintBase(new DBDAttributeConstraintBase());
    dbdAttributeConstraintBase.setOptions(new Object[] {DBPEvent.RENAME});

    // Act and Assert
    assertFalse(dbdAttributeConstraintBase.hasOption("Option"));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasOption(String)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} Option {@code
   *       Option} is {@link DBPEvent#RENAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasOption(String)"})
  public void testHasOption_givenDBDAttributeConstraintBaseOptionOptionIsRename_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOption("Option", DBPEvent.RENAME);

    // Act and Assert
    assertTrue(dbdAttributeConstraintBase.hasOption("Option"));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#hasOption(String)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#hasOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.hasOption(String)"})
  public void testHasOption_givenDBDAttributeConstraintBase_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBDAttributeConstraintBase().hasOption("Option"));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#getOption(String)}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDAttributeConstraintBase.getOption(String)"})
  public void testGetOption() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase =
        new DBDAttributeConstraintBase(new DBDAttributeConstraintBase());
    dbdAttributeConstraintBase.setOptions(new Object[] {DBPEvent.RENAME});

    // Act and Assert
    assertNull(dbdAttributeConstraintBase.getOption("Option"));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#getOption(String)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDAttributeConstraintBase.getOption(String)"})
  public void testGetOption_givenDBDAttributeConstraintBase_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBDAttributeConstraintBase().getOption("Option"));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#setOption(String, Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>Then first element is {@code Option}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#setOption(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.setOption(String, Object)"})
  public void testSetOption_givenDBDAttributeConstraintBase_thenFirstElementIsOption() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    Object object = DBPEvent.RENAME;

    // Act
    dbdAttributeConstraintBase.setOption("Option", object);

    // Assert
    Object[] options = dbdAttributeConstraintBase.getOptions();
    assertEquals("Option", options[0]);
    assertEquals(2, options.length);
    assertSame(object, options[1]);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#setOption(String, Object)}.
   *
   * <ul>
   *   <li>Then second element is {@code Option}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#setOption(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.setOption(String, Object)"})
  public void testSetOption_thenSecondElementIsOption() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase =
        new DBDAttributeConstraintBase(new DBDAttributeConstraintBase());
    dbdAttributeConstraintBase.setOptions(new Object[] {DBPEvent.RENAME});
    Object object = DBPEvent.RENAME;

    // Act
    dbdAttributeConstraintBase.setOption("Option", object);

    // Assert
    Object[] options = dbdAttributeConstraintBase.getOptions();
    assertEquals("Option", options[1]);
    assertEquals(3, options.length);
    assertSame(object, options[0]);
    assertSame(object, options[2]);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#removeOption(String)}.
   *
   * <ul>
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#removeOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.removeOption(String)"})
  public void testRemoveOption_thenArrayLengthIsOne() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase =
        new DBDAttributeConstraintBase(new DBDAttributeConstraintBase());
    dbdAttributeConstraintBase.setOptions(new Object[] {DBPEvent.RENAME});

    // Act
    boolean actualRemoveOptionResult = dbdAttributeConstraintBase.removeOption("Option");

    // Assert
    assertEquals(1, dbdAttributeConstraintBase.getOptions().length);
    assertFalse(actualRemoveOptionResult);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#removeOption(String)}.
   *
   * <ul>
   *   <li>Then array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#removeOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.removeOption(String)"})
  public void testRemoveOption_thenArrayLengthIsZero() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOption("Option", DBPEvent.RENAME);

    // Act
    boolean actualRemoveOptionResult = dbdAttributeConstraintBase.removeOption("Option");

    // Assert
    assertEquals(0, dbdAttributeConstraintBase.getOptions().length);
    assertTrue(actualRemoveOptionResult);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#removeOption(String)}.
   *
   * <ul>
   *   <li>Then {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} Options is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#removeOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDAttributeConstraintBase.removeOption(String)"})
  public void testRemoveOption_thenDBDAttributeConstraintBaseOptionsIsNull() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act
    boolean actualRemoveOptionResult = dbdAttributeConstraintBase.removeOption("Option");

    // Assert
    assertNull(dbdAttributeConstraintBase.getOptions());
    assertFalse(actualRemoveOptionResult);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#reset()}.
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.reset()"})
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
   * Test {@link DBDAttributeConstraintBase#reset()}.
   *
   * <ul>
   *   <li>Then {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} VisualPosition is
   *       zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDAttributeConstraintBase.reset()"})
  public void testReset_thenDBDAttributeConstraintBaseVisualPositionIsZero() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act
    dbdAttributeConstraintBase.reset();

    // Assert
    assertEquals(0, dbdAttributeConstraintBase.getVisualPosition());
    assertFalse(dbdAttributeConstraintBase.isDirty());
    assertTrue(dbdAttributeConstraintBase.isVisible());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertFalse(dbdAttributeConstraint.equalFilters(new DBDAttributeConstraintBase(), true));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters2() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertTrue(
        dbdAttributeConstraint.equalFilters(new DBDAttributeConstraint("Attribute Name", 1), true));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} OrderDescending is
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_givenDBDAttributeConstraintBaseOrderDescendingIsTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOrderDescending(true);

    // Act and Assert
    assertFalse(dbdAttributeConstraintBase.equalFilters(new DBDAttributeConstraintBase(), true));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} OrderPosition is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_givenDBDAttributeConstraintBaseOrderPositionIsOne() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOrderPosition(1);

    // Act and Assert
    assertFalse(dbdAttributeConstraintBase.equalFilters(new DBDAttributeConstraintBase(), true));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()} ReverseOperator is
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_givenDBDAttributeConstraintBaseReverseOperatorIsTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setReverseOperator(true);

    // Act and Assert
    assertFalse(dbdAttributeConstraintBase.equalFilters(new DBDAttributeConstraintBase(), true));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_givenDBDAttributeConstraintBase_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act and Assert
    assertTrue(dbdAttributeConstraintBase.equalFilters(new DBDAttributeConstraintBase(), true));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeConstraintBase#DBDAttributeConstraintBase()}.
   *   <li>When {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBDAttributeConstraintBase#equalFilters(DBDAttributeConstraintBase, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equalFilters(DBDAttributeConstraintBase, boolean)"
  })
  public void testEqualFilters_givenDBDAttributeConstraintBase_whenFalse_thenReturnTrue() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act and Assert
    assertTrue(dbdAttributeConstraintBase.equalFilters(new DBDAttributeConstraintBase(), false));
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}, and {@link
   * DBDAttributeConstraintBase#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraintBase#equals(Object)}
   *   <li>{@link DBDAttributeConstraintBase#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    DBDAttributeConstraintBase dbdAttributeConstraintBase2 = new DBDAttributeConstraintBase();

    // Act and Assert
    assertEquals(dbdAttributeConstraintBase, dbdAttributeConstraintBase2);
    assertEquals(dbdAttributeConstraintBase.hashCode(), dbdAttributeConstraintBase2.hashCode());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}, and {@link
   * DBDAttributeConstraintBase#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDAttributeConstraintBase#equals(Object)}
   *   <li>{@link DBDAttributeConstraintBase#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();

    // Act and Assert
    assertEquals(dbdAttributeConstraintBase, dbdAttributeConstraintBase);
    int expectedHashCodeResult = dbdAttributeConstraintBase.hashCode();
    assertEquals(expectedHashCodeResult, dbdAttributeConstraintBase.hashCode());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraint, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOrderPosition(1);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraintBase, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOrderDescending(true);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraintBase, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setReverseOperator(true);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraintBase, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setVisible(true);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraintBase, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setVisualPosition(1);

    // Act and Assert
    assertNotEquals(dbdAttributeConstraintBase, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    DBDAttributeConstraintBase dbdAttributeConstraintBase = new DBDAttributeConstraintBase();
    dbdAttributeConstraintBase.setOptions(new Object[] {DBPEvent.RENAME});

    // Act and Assert
    assertNotEquals(dbdAttributeConstraintBase, new DBDAttributeConstraintBase());
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBDAttributeConstraintBase(), null);
  }

  /**
   * Test {@link DBDAttributeConstraintBase#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeConstraintBase#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBDAttributeConstraintBase.equals(Object)",
    "int DBDAttributeConstraintBase.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DBDAttributeConstraintBase(), "Different type to DBDAttributeConstraintBase");
  }
}
