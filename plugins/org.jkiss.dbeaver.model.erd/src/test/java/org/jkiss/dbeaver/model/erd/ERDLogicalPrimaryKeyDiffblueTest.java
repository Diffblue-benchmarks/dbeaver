package org.jkiss.dbeaver.model.erd;

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
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDLogicalPrimaryKeyDiffblueTest {
  /**
   * Test {@link ERDLogicalPrimaryKey#ERDLogicalPrimaryKey(ERDElement, String, String)}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDLogicalPrimaryKey#ERDLogicalPrimaryKey(ERDElement, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDLogicalPrimaryKey.<init>(ERDElement, String, String)"})
  public void testNewERDLogicalPrimaryKey_thenReturnName() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));

    // Act
    ERDLogicalPrimaryKey actualErdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    // Assert
    assertEquals("Name", actualErdLogicalPrimaryKey.getName());
    DBSEntityConstraintType constraintType = actualErdLogicalPrimaryKey.getConstraintType();
    assertEquals("PRIMARY KEY", constraintType.getName());
    assertEquals("Primary Key", constraintType.getLocalizedName());
    assertEquals(
        "The characteristics of someone or something", actualErdLogicalPrimaryKey.getDescription());
    assertEquals("pk", constraintType.getId());
    assertNull(actualErdLogicalPrimaryKey.getDataSource());
    assertNull(actualErdLogicalPrimaryKey.getParentObject());
    assertFalse(actualErdLogicalPrimaryKey.isPersisted());
    assertFalse(constraintType.isAssociation());
    assertFalse(constraintType.isCustom());
    assertFalse(constraintType.isLogical());
    assertTrue(constraintType.isUnique());
  }

  /**
   * Test {@link ERDLogicalPrimaryKey#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDLogicalPrimaryKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDLogicalPrimaryKey.getDataSource()"})
  public void testGetDataSource_givenERDEntityWithDataSourceIsDBPDataSource_thenReturnNull() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey erdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    // Act and Assert
    assertNull(erdLogicalPrimaryKey.getDataSource());
  }

  /**
   * Test {@link ERDLogicalPrimaryKey#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDLogicalPrimaryKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDLogicalPrimaryKey.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDLogicalPrimaryKey erdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(
            new ERDEntity(entity), "Name", "The characteristics of someone or something");

    // Act
    erdLogicalPrimaryKey.getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link ERDLogicalPrimaryKey#getParentObject()}.
   *
   * <p>Method under test: {@link ERDLogicalPrimaryKey#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntity ERDLogicalPrimaryKey.getParentObject()"
  })
  public void testGetParentObject() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDLogicalPrimaryKey erdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(
            new ERDEntity(entity), "Name", "The characteristics of someone or something");

    // Act and Assert
    assertSame(entity, erdLogicalPrimaryKey.getParentObject());
  }

  /**
   * Test {@link ERDLogicalPrimaryKey#getParentObject()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDLogicalPrimaryKey#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntity ERDLogicalPrimaryKey.getParentObject()"
  })
  public void testGetParentObject_givenERDEntityWithDataSourceIsDBPDataSource_thenReturnNull() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey erdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    // Act and Assert
    assertNull(erdLogicalPrimaryKey.getParentObject());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDLogicalPrimaryKey#getConstraintType()}
   *   <li>{@link ERDLogicalPrimaryKey#getDescription()}
   *   <li>{@link ERDLogicalPrimaryKey#getName()}
   *   <li>{@link ERDLogicalPrimaryKey#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraintType ERDLogicalPrimaryKey.getConstraintType()",
    "String ERDLogicalPrimaryKey.getDescription()",
    "String ERDLogicalPrimaryKey.getName()",
    "boolean ERDLogicalPrimaryKey.isPersisted()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey erdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    // Act
    DBSEntityConstraintType actualConstraintType = erdLogicalPrimaryKey.getConstraintType();
    String actualDescription = erdLogicalPrimaryKey.getDescription();
    String actualName = erdLogicalPrimaryKey.getName();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertFalse(erdLogicalPrimaryKey.isPersisted());
    assertSame(DBSEntityConstraintType.PRIMARY_KEY, actualConstraintType);
  }

  /**
   * Test {@link ERDLogicalPrimaryKey#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link ERDLogicalPrimaryKey#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List ERDLogicalPrimaryKey.getAttributeReferences(DBRProgressMonitor)"
  })
  public void testGetAttributeReferences() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey erdLogicalPrimaryKey =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    // Act and Assert
    assertTrue(erdLogicalPrimaryKey.getAttributeReferences(new LoggingProgressMonitor()).isEmpty());
  }
}
