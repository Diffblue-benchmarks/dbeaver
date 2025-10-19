package org.jkiss.dbeaver.ext.hana.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.hana.model.HANADependency.DummyObject;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HANADependencyDiffblueTest {
  /**
   * Test DummyObject getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DummyObject#DummyObject(HANADependency, String)}
   *   <li>{@link DummyObject#getDataSource()}
   *   <li>{@link DummyObject#getDescription()}
   *   <li>{@link DummyObject#getName()}
   *   <li>{@link DummyObject#getParentObject()}
   *   <li>{@link DummyObject#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DummyObject.<init>(HANADependency, String)",
    "DBPDataSource DummyObject.getDataSource()",
    "String DummyObject.getDescription()",
    "String DummyObject.getName()",
    "DBSObject DummyObject.getParentObject()",
    "boolean DummyObject.isPersisted()"
  })
  public void testDummyObjectGettersAndSetters() {
    // Arrange
    DBSDocumentConstraint dependentObject =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    HANADependency hanaDependency =
        new HANADependency(
            dependentObject, "Base Object Type", "Base Object Schema", "Base Object Name");

    // Act
    DummyObject actualDummyObject = hanaDependency.new DummyObject("foo");
    DBPDataSource actualDataSource = actualDummyObject.getDataSource();
    String actualDescription = actualDummyObject.getDescription();
    String actualName = actualDummyObject.getName();
    DBSObject actualParentObject = actualDummyObject.getParentObject();

    // Assert
    assertEquals("foo", actualName);
    assertNull(actualDescription);
    assertNull(actualDataSource);
    assertNull(actualParentObject);
    assertFalse(actualDummyObject.isPersisted());
  }
}
