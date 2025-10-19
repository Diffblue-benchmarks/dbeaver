package org.jkiss.dbeaver.model.impl.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DirectObjectReferenceDiffblueTest {
  /**
   * Test {@link DirectObjectReference#DirectObjectReference(DBSObject, DBSObjectType, DBSObject)}.
   *
   * <p>Method under test: {@link DirectObjectReference#DirectObjectReference(DBSObject,
   * DBSObjectType, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DirectObjectReference.<init>(DBSObject, DBSObjectType, DBSObject)"})
  public void testNewDirectObjectReference() {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSObjectType type = RelationalObjectType.TYPE_CATALOG;
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DirectObjectReference actualDirectObjectReference =
        new DirectObjectReference(container, type, object);

    // Assert
    assertEquals("DocumentKey", actualDirectObjectReference.getName());
    assertNull(actualDirectObjectReference.getObjectDescription());
    Class<DBSDocumentConstraint> expectedObjectClass = DBSDocumentConstraint.class;
    assertEquals(expectedObjectClass, actualDirectObjectReference.getObjectClass());
    assertSame(container, actualDirectObjectReference.getContainer());
    assertSame(
        ((RelationalObjectType) type).TYPE_CATALOG, actualDirectObjectReference.getObjectType());
  }

  /**
   * Test {@link DirectObjectReference#resolveObject(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DirectObjectReference#resolveObject(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DirectObjectReference.resolveObject(DBRProgressMonitor)"})
  public void testResolveObject() throws DBException {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act and Assert
    assertSame(object, directObjectReference.resolveObject(new LoggingProgressMonitor()));
  }
}
