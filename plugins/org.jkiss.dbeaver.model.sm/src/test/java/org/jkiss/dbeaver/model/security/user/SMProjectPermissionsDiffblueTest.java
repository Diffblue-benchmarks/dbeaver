package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMProjectPermissionsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMProjectPermissions#SMProjectPermissions(String, List)}
   *   <li>{@link SMProjectPermissions#getPermissions()}
   *   <li>{@link SMProjectPermissions#getProjectId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMProjectPermissions.<init>(String, List)",
    "List SMProjectPermissions.getPermissions()",
    "String SMProjectPermissions.getProjectId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<SMProjectPermission> permissions = new ArrayList<>();

    // Act
    SMProjectPermissions actualSmProjectPermissions =
        new SMProjectPermissions("myproject", permissions);
    List<SMProjectPermission> actualPermissions = actualSmProjectPermissions.getPermissions();

    // Assert
    assertEquals("myproject", actualSmProjectPermissions.getProjectId());
    assertTrue(actualPermissions.isEmpty());
    assertSame(permissions, actualPermissions);
  }
}
