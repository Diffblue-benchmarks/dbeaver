package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.struct.AbstractObjectType;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreNamespaceDiffblueTest {
  /**
   * Test {@link PostgreNamespace#supportsObjectType(DBSObjectType)}.
   *
   * <ul>
   *   <li>When {@code DBSObject}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreNamespace#supportsObjectType(DBSObjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostgreNamespace.supportsObjectType(DBSObjectType)"})
  public void testSupportsObjectType_whenOrgJkissDbeaverModelStructDBSObject_thenReturnFalse() {
    // Arrange
    DBPImage image = mock(DBPImage.class);
    Class<DBSObject> objectClass = DBSObject.class;

    AbstractObjectType objectType =
        new AbstractObjectType(
            "Type Name", "The characteristics of someone or something", image, objectClass);

    // Act and Assert
    assertFalse(PostgreNamespace.supportsObjectType(objectType));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostgreNamespace#PostgreNamespace(PostgreSchema)}
   *   <li>{@link PostgreNamespace#getNamespaceObjectTypes()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgreNamespace.<init>(PostgreSchema)",
    "DBSObjectType[] PostgreNamespace.getNamespaceObjectTypes()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertSame(
        PostgreNamespace.SUPPORTED_TYPES, new PostgreNamespace(null).getNamespaceObjectTypes());
  }
}
