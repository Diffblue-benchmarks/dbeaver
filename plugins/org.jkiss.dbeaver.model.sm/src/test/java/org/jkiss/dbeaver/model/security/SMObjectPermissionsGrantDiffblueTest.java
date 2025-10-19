package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.security.SMObjectPermissionsGrant.Builder;
import org.jkiss.dbeaver.model.security.user.SMObjectPermissions;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMObjectPermissionsGrantDiffblueTest {
  /**
   * Test Builder {@link Builder#addPermission(String)}.
   *
   * <p>Method under test: {@link Builder#addPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPermission(String)"})
  public void testBuilderAddPermission() {
    // Arrange
    Builder builderResult = SMObjectPermissionsGrant.builder("42", SMSubjectType.user, "42");

    // Act
    Builder actualAddPermissionResult = builderResult.addPermission("Permission");

    // Assert
    assertSame(builderResult, actualAddPermissionResult);
    assertArrayEquals(
        new String[] {"Permission"}, builderResult.build().getObjectPermissions().getPermissions());
  }

  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Method under test: {@link Builder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>(String, SMSubjectType, String)",
    "SMObjectPermissionsGrant Builder.build()"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    SMObjectPermissionsGrant actualSmObjectPermissionsGrant =
        SMObjectPermissionsGrant.builder("42", SMSubjectType.user, "42").build();

    // Assert
    assertEquals("42", actualSmObjectPermissionsGrant.getSubjectId());
    SMObjectPermissions objectPermissions = actualSmObjectPermissionsGrant.getObjectPermissions();
    assertEquals("42", objectPermissions.getObjectId());
    assertEquals(0, objectPermissions.getPermissions().length);
    assertEquals(SMSubjectType.user, actualSmObjectPermissionsGrant.getSubjectType());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMObjectPermissionsGrant#SMObjectPermissionsGrant(String, SMSubjectType,
   *       SMObjectPermissions)}
   *   <li>{@link SMObjectPermissionsGrant#getObjectPermissions()}
   *   <li>{@link SMObjectPermissionsGrant#getSubjectId()}
   *   <li>{@link SMObjectPermissionsGrant#getSubjectType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMObjectPermissionsGrant.<init>(String, SMSubjectType, SMObjectPermissions)",
    "SMObjectPermissions SMObjectPermissionsGrant.getObjectPermissions()",
    "String SMObjectPermissionsGrant.getSubjectId()",
    "SMSubjectType SMObjectPermissionsGrant.getSubjectType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SMObjectPermissions objectPermissions = new SMObjectPermissions("42", new ArrayList<>());

    // Act
    SMObjectPermissionsGrant actualSmObjectPermissionsGrant =
        new SMObjectPermissionsGrant("42", SMSubjectType.user, objectPermissions);
    SMObjectPermissions actualObjectPermissions =
        actualSmObjectPermissionsGrant.getObjectPermissions();
    String actualSubjectId = actualSmObjectPermissionsGrant.getSubjectId();

    // Assert
    assertEquals("42", actualSubjectId);
    assertEquals(SMSubjectType.user, actualSmObjectPermissionsGrant.getSubjectType());
    assertSame(objectPermissions, actualObjectPermissions);
  }
}
