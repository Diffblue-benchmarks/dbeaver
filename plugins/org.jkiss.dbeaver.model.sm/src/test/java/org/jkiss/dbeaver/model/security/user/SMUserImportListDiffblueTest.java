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

public class SMUserImportListDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMUserImportList#SMUserImportList(List, String)}
   *   <li>{@link SMUserImportList#getAuthRole()}
   *   <li>{@link SMUserImportList#getUsers()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUserImportList.<init>(List, String)",
    "String SMUserImportList.getAuthRole()",
    "List SMUserImportList.getUsers()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<SMUserProvisioning> users = new ArrayList<>();

    // Act
    SMUserImportList actualSmUserImportList = new SMUserImportList(users, "Auth Role");
    String actualAuthRole = actualSmUserImportList.getAuthRole();
    List<SMUserProvisioning> actualUsers = actualSmUserImportList.getUsers();

    // Assert
    assertEquals("Auth Role", actualAuthRole);
    assertTrue(actualUsers.isEmpty());
    assertSame(users, actualUsers);
  }
}
