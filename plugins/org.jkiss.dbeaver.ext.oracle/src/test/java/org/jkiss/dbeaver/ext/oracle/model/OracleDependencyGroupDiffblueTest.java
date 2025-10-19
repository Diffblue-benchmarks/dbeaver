package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleDependencyGroupDiffblueTest {
  /**
   * Test {@link OracleDependencyGroup#of(DBSObject)}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#of(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection OracleDependencyGroup.of(DBSObject)"})
  public void testOf_thenReturnSizeIsTwo() {
    // Arrange and Act
    Collection<OracleDependencyGroup> actualOfResult =
        OracleDependencyGroup.of(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertEquals(2, actualOfResult.size());
  }

  /**
   * Test {@link OracleDependencyGroup#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Dependencies}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleDependencyGroup.getName()"})
  public void testGetName_thenReturnDependencies() {
    // Arrange
    DBSDocumentConstraint owner = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertEquals("Dependencies", new OracleDependencyGroup(owner, false).getName());
  }

  /**
   * Test {@link OracleDependencyGroup#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Dependent}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleDependencyGroup.getName()"})
  public void testGetName_thenReturnDependent() {
    // Arrange
    DBSDocumentConstraint owner = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertEquals("Dependent", new OracleDependencyGroup(owner, true).getName());
  }

  /**
   * Test {@link OracleDependencyGroup#getDescription()}.
   *
   * <ul>
   *   <li>Then return {@code The objects that depend on this object.}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleDependencyGroup.getDescription()"})
  public void testGetDescription_thenReturnTheObjectsThatDependOnThisObject() {
    // Arrange
    DBSDocumentConstraint owner = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertEquals(
        "The objects that depend on this object.",
        new OracleDependencyGroup(owner, true).getDescription());
  }

  /**
   * Test {@link OracleDependencyGroup#getDescription()}.
   *
   * <ul>
   *   <li>Then return {@code The objects this object depends on.}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleDependencyGroup.getDescription()"})
  public void testGetDescription_thenReturnTheObjectsThisObjectDependsOn() {
    // Arrange
    DBSDocumentConstraint owner = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertEquals(
        "The objects this object depends on.",
        new OracleDependencyGroup(owner, false).getDescription());
  }

  /**
   * Test {@link OracleDependencyGroup#isPersisted()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OracleDependencyGroup.isPersisted()"})
  public void testIsPersisted_thenReturnFalse() {
    // Arrange
    DBSDocumentConstraint owner = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertFalse(new OracleDependencyGroup(owner, true).isPersisted());
  }

  /**
   * Test {@link OracleDependencyGroup#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDependencyGroup#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource OracleDependencyGroup.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBSDocumentConstraint owner = new DBSDocumentConstraint(entity);

    // Act
    new OracleDependencyGroup(owner, true).getDataSource();

    // Assert
    verify(entity).getDataSource();
  }
}
