package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVEntityConstraintDiffblueTest {
  /**
   * Test {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity, DBVEntityConstraint)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return Attributes size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity,
   * DBVEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.<init>(DBVEntity, DBVEntityConstraint)"})
  public void testNewDBVEntityConstraint_givenName_thenReturnAttributesSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");
    copy.addAttribute("Name");

    // Act
    DBVEntityConstraint actualDbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Assert
    List<DBVEntityConstraintColumn> attributes = actualDbvEntityConstraint.getAttributes();
    assertEquals(1, attributes.size());
    DBVEntityConstraintColumn getResult = attributes.get(0);
    assertEquals("Name", getResult.getAttributeName());
    assertNull(getResult.getAttribute());
    assertTrue(actualDbvEntityConstraint.hasAttributes());
  }

  /**
   * Test {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity, DBVEntityConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity,
   * DBVEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.<init>(DBVEntity, DBVEntityConstraint)"})
  public void testNewDBVEntityConstraint_thenReturnName() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    // Act
    DBVEntityConstraint actualDbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Assert
    assertEquals("Name", actualDbvEntityConstraint.getName());
    assertNull(actualDbvEntityConstraint.getDescription());
    assertNull(actualDbvEntityConstraint.getDataSource());
    assertFalse(actualDbvEntityConstraint.hasAttributes());
    assertFalse(actualDbvEntityConstraint.isUseAllColumns());
    assertTrue(actualDbvEntityConstraint.getAttributes().isEmpty());
    assertTrue(actualDbvEntityConstraint.isPersisted());
    assertSame(entity, actualDbvEntityConstraint.getEntity());
    assertSame(entity, actualDbvEntityConstraint.getParentObject());
  }

  /**
   * Test {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity, DBSEntityConstraintType,
   * String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity,
   * DBSEntityConstraintType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.<init>(DBVEntity, DBSEntityConstraintType, String)"})
  public void testNewDBVEntityConstraint_whenName_thenReturnName() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntityConstraint actualDbvEntityConstraint =
        new DBVEntityConstraint(entity, DBSEntityConstraintType.ASSOCIATION, "Name");

    // Assert
    DBVEntity entity2 = actualDbvEntityConstraint.getEntity();
    DBVEntityConstraint bestIdentifier = entity2.getBestIdentifier();
    assertEquals("Name", actualDbvEntityConstraint.getName());
    assertEquals(1, entity2.getConstraints().size());
    DBVContainer expectedParentObject = entity2.getContainer();
    assertSame(expectedParentObject, entity2.getParentObject());
    assertSame(entity2, bestIdentifier.getEntity());
    assertSame(entity2, actualDbvEntityConstraint.getParentObject());
    assertSame(entity2, bestIdentifier.getParentObject());
  }

  /**
   * Test {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity, DBSEntityConstraintType,
   * String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Name is {@code Association}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#DBVEntityConstraint(DBVEntity,
   * DBSEntityConstraintType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.<init>(DBVEntity, DBSEntityConstraintType, String)"})
  public void testNewDBVEntityConstraint_whenNull_thenReturnNameIsAssociation() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntityConstraint actualDbvEntityConstraint =
        new DBVEntityConstraint(entity, DBSEntityConstraintType.ASSOCIATION, null);

    // Assert
    DBVEntity entity2 = actualDbvEntityConstraint.getEntity();
    DBVEntityConstraint bestIdentifier = entity2.getBestIdentifier();
    assertEquals("Association", actualDbvEntityConstraint.getName());
    assertEquals(1, entity2.getConstraints().size());
    DBVContainer expectedParentObject = entity2.getContainer();
    assertSame(expectedParentObject, entity2.getParentObject());
    assertSame(entity2, bestIdentifier.getEntity());
    assertSame(entity2, actualDbvEntityConstraint.getParentObject());
    assertSame(entity2, bestIdentifier.getParentObject());
  }

  /**
   * Test {@link DBVEntityConstraint#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntityConstraint.getAttributeReferences(DBRProgressMonitor)"})
  public void testGetAttributeReferences() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertTrue(dbvEntityConstraint.getAttributeReferences(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVEntityConstraint#setName(String)}
   *   <li>{@link DBVEntityConstraint#setUseAllColumns(boolean)}
   *   <li>{@link DBVEntityConstraint#getAttributes()}
   *   <li>{@link DBVEntityConstraint#getConstraintType()}
   *   <li>{@link DBVEntityConstraint#getDescription()}
   *   <li>{@link DBVEntityConstraint#getEntity()}
   *   <li>{@link DBVEntityConstraint#getName()}
   *   <li>{@link DBVEntityConstraint#isPersisted()}
   *   <li>{@link DBVEntityConstraint#isUseAllColumns()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntityConstraint.getAttributes()",
    "DBSEntityConstraintType DBVEntityConstraint.getConstraintType()",
    "String DBVEntityConstraint.getDescription()",
    "DBVEntity DBVEntityConstraint.getEntity()",
    "String DBVEntityConstraint.getName()",
    "boolean DBVEntityConstraint.isPersisted()",
    "boolean DBVEntityConstraint.isUseAllColumns()",
    "void DBVEntityConstraint.setName(String)",
    "void DBVEntityConstraint.setUseAllColumns(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act
    dbvEntityConstraint.setName("Name");
    dbvEntityConstraint.setUseAllColumns(true);
    List<DBVEntityConstraintColumn> actualAttributes = dbvEntityConstraint.getAttributes();
    DBSEntityConstraintType actualConstraintType = dbvEntityConstraint.getConstraintType();
    String actualDescription = dbvEntityConstraint.getDescription();
    DBVEntity actualEntity = dbvEntityConstraint.getEntity();
    String actualName = dbvEntityConstraint.getName();
    boolean actualIsPersistedResult = dbvEntityConstraint.isPersisted();
    boolean actualIsUseAllColumnsResult = dbvEntityConstraint.isUseAllColumns();

    // Assert
    assertEquals("Name", actualName);
    assertNull(actualDescription);
    assertTrue(actualAttributes.isEmpty());
    assertTrue(actualIsPersistedResult);
    assertTrue(actualIsUseAllColumnsResult);
    assertSame(entity, actualEntity);
    assertSame(DBSEntityConstraintType.ASSOCIATION, actualConstraintType);
  }

  /**
   * Test {@link DBVEntityConstraint#getParentObject()}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVEntityConstraint.getParentObject()"})
  public void testGetParentObject() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertSame(entity, dbvEntityConstraint.getParentObject());
  }

  /**
   * Test {@link DBVEntityConstraint#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntityConstraint.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act
    dbvEntityConstraint.getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntityConstraint#hasAttributes()}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#hasAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntityConstraint.hasAttributes()"})
  public void testHasAttributes() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);
    dbvEntityConstraint.setUseAllColumns(true);
    dbvEntityConstraint.addAttribute("Name");

    // Act and Assert
    assertTrue(dbvEntityConstraint.hasAttributes());
  }

  /**
   * Test {@link DBVEntityConstraint#hasAttributes()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#hasAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntityConstraint.hasAttributes()"})
  public void testHasAttributes_thenReturnFalse() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertFalse(dbvEntityConstraint.hasAttributes());
  }

  /**
   * Test {@link DBVEntityConstraint#hasAttributes()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraint#hasAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntityConstraint.hasAttributes()"})
  public void testHasAttributes_thenReturnTrue() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);
    dbvEntityConstraint.addAttribute("Name");

    // Act and Assert
    assertTrue(dbvEntityConstraint.hasAttributes());
  }

  /**
   * Test {@link DBVEntityConstraint#setAttributes(Collection)}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#setAttributes(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.setAttributes(Collection)"})
  public void testSetAttributes() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act
    dbvEntityConstraint.setAttributes(new ArrayList<>());

    // Assert that nothing has changed
    assertFalse(dbvEntityConstraint.hasAttributes());
    assertTrue(dbvEntityConstraint.getAttributes().isEmpty());
  }

  /**
   * Test {@link DBVEntityConstraint#setAttributes(Collection)}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#setAttributes(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.setAttributes(Collection)"})
  public void testSetAttributes2() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    ArrayList<DBSEntityAttribute> realAttributes = new ArrayList<>();
    DBVEntity entity3 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntity entity5 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity6 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute parent = new DBVEntityAttribute(entity6, null, "Name");
    DBVEntity entity7 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity7, null, "Name");

    DBVEntityAttribute copy3 = new DBVEntityAttribute(entity5, parent, copy2);

    DBVEntityAttribute parent2 = new DBVEntityAttribute(entity4, null, copy3);

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity3, parent2, "Name");
    realAttributes.add(dbvEntityAttribute);

    // Act
    dbvEntityConstraint.setAttributes(realAttributes);

    // Assert
    List<DBVEntityConstraintColumn> attributes = dbvEntityConstraint.getAttributes();
    assertEquals(1, attributes.size());
    DBVEntityConstraintColumn getResult = attributes.get(0);
    assertEquals("Name", getResult.getAttributeName());
    assertNull(getResult.getAttribute());
    assertTrue(dbvEntityConstraint.hasAttributes());
  }

  /**
   * Test {@link DBVEntityConstraint#setAttributes(Collection)}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#setAttributes(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.setAttributes(Collection)"})
  public void testSetAttributes3() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    ArrayList<DBSEntityAttribute> realAttributes = new ArrayList<>();
    DBVEntity entity3 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntity entity5 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity6 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute parent = new DBVEntityAttribute(entity6, null, "Name");
    DBVEntity entity7 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity7, null, "Name");

    DBVEntityAttribute copy3 = new DBVEntityAttribute(entity5, parent, copy2);

    DBVEntityAttribute parent2 = new DBVEntityAttribute(entity4, null, copy3);

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity3, parent2, "Name");
    realAttributes.add(dbvEntityAttribute);
    DBVEntity entity8 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity9 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntity entity10 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity11 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity11, null, "Name");
    DBVEntity entity12 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute copy4 = new DBVEntityAttribute(entity12, null, "Name");

    DBVEntityAttribute copy5 = new DBVEntityAttribute(entity10, parent3, copy4);

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity9, null, copy5);

    DBVEntityAttribute dbvEntityAttribute2 = new DBVEntityAttribute(entity8, parent4, "Name");
    realAttributes.add(dbvEntityAttribute2);

    // Act
    dbvEntityConstraint.setAttributes(realAttributes);

    // Assert
    List<DBVEntityConstraintColumn> attributes = dbvEntityConstraint.getAttributes();
    assertEquals(2, attributes.size());
    DBVEntityConstraintColumn getResult = attributes.get(0);
    assertEquals("Name", getResult.getAttributeName());
    DBVEntityConstraintColumn getResult2 = attributes.get(1);
    assertEquals("Name", getResult2.getAttributeName());
    assertNull(getResult.getAttribute());
    assertNull(getResult2.getAttribute());
    assertTrue(dbvEntityConstraint.hasAttributes());
  }

  /**
   * Test {@link DBVEntityConstraint#addAttribute(String)}.
   *
   * <p>Method under test: {@link DBVEntityConstraint#addAttribute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityConstraint.addAttribute(String)"})
  public void testAddAttribute() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint dbvEntityConstraint = new DBVEntityConstraint(entity, copy);

    // Act
    dbvEntityConstraint.addAttribute("Name");

    // Assert
    List<DBVEntityConstraintColumn> attributes = dbvEntityConstraint.getAttributes();
    assertEquals(1, attributes.size());
    DBVEntityConstraintColumn getResult = attributes.get(0);
    assertEquals("Name", getResult.getAttributeName());
    assertNull(getResult.getAttribute());
    assertTrue(dbvEntityConstraint.hasAttributes());
  }
}
