package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraint;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDLogicalAssociationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDLogicalAssociation#ERDLogicalAssociation(ERDElement, String, String,
   *       ERDLogicalPrimaryKey)}
   *   <li>{@link ERDLogicalAssociation#getConstraintType()}
   *   <li>{@link ERDLogicalAssociation#getDescription()}
   *   <li>{@link ERDLogicalAssociation#getName()}
   *   <li>{@link ERDLogicalAssociation#getReferencedConstraint()}
   *   <li>{@link ERDLogicalAssociation#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDLogicalAssociation.<init>(ERDElement, String, String, ERDLogicalPrimaryKey)",
    "DBSEntityConstraintType ERDLogicalAssociation.getConstraintType()",
    "String ERDLogicalAssociation.getDescription()",
    "String ERDLogicalAssociation.getName()",
    "DBSEntityConstraint ERDLogicalAssociation.getReferencedConstraint()",
    "boolean ERDLogicalAssociation.isPersisted()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    // Act
    ERDLogicalAssociation actualErdLogicalAssociation =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    DBSEntityConstraintType actualConstraintType = actualErdLogicalAssociation.getConstraintType();
    String actualDescription = actualErdLogicalAssociation.getDescription();
    String actualName = actualErdLogicalAssociation.getName();
    DBSEntityConstraint actualReferencedConstraint =
        actualErdLogicalAssociation.getReferencedConstraint();

    // Assert
    assertEquals("Logical Key", actualConstraintType.getLocalizedName());
    assertEquals("Logical Key", actualConstraintType.getName());
    assertEquals("Name", actualName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("erdkey", actualConstraintType.getId());
    assertFalse(actualErdLogicalAssociation.isPersisted());
    assertFalse(actualConstraintType.isCustom());
    assertFalse(actualConstraintType.isUnique());
    assertTrue(actualConstraintType.isAssociation());
    assertTrue(actualConstraintType.isLogical());
    assertSame(pk, actualReferencedConstraint);
  }

  /**
   * Test {@link ERDLogicalAssociation#getAssociatedEntity()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDLogicalAssociation#getAssociatedEntity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntity ERDLogicalAssociation.getAssociatedEntity()"
  })
  public void testGetAssociatedEntity_thenReturnNull() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);

    // Act and Assert
    assertNull(erdLogicalAssociation.getAssociatedEntity());
  }

  /**
   * Test {@link ERDLogicalAssociation#getDataSource()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDLogicalAssociation#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDLogicalAssociation.getDataSource()"})
  public void testGetDataSource_thenReturnNull() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(null, "Name", "The characteristics of someone or something", pk);

    // Act and Assert
    assertNull(erdLogicalAssociation.getDataSource());
  }

  /**
   * Test {@link ERDLogicalAssociation#getParentObject()}.
   *
   * <p>Method under test: {@link ERDLogicalAssociation#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntity ERDLogicalAssociation.getParentObject()"
  })
  public void testGetParentObject() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);

    // Act and Assert
    assertNull(erdLogicalAssociation.getParentObject());
  }

  /**
   * Test {@link ERDLogicalAssociation#getParentObject()}.
   *
   * <p>Method under test: {@link ERDLogicalAssociation#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntity ERDLogicalAssociation.getParentObject()"
  })
  public void testGetParentObject2() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(null, "Name", "The characteristics of someone or something", pk);

    // Act and Assert
    assertNull(erdLogicalAssociation.getParentObject());
  }

  /**
   * Test {@link ERDLogicalAssociation#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link ERDLogicalAssociation#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List ERDLogicalAssociation.getAttributeReferences(DBRProgressMonitor)"
  })
  public void testGetAttributeReferences() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);

    // Act and Assert
    assertTrue(
        erdLogicalAssociation.getAttributeReferences(new LoggingProgressMonitor()).isEmpty());
  }
}
