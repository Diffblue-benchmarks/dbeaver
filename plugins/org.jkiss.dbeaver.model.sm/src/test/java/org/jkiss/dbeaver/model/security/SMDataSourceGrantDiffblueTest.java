package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMDataSourceGrantDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMDataSourceGrant#SMDataSourceGrant(String, String, SMSubjectType)}
   *   <li>{@link SMDataSourceGrant#getConnectionId()}
   *   <li>{@link SMDataSourceGrant#getDataSourceId()}
   *   <li>{@link SMDataSourceGrant#getSubjectId()}
   *   <li>{@link SMDataSourceGrant#getSubjectType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMDataSourceGrant.<init>(String, String, SMSubjectType)",
    "String SMDataSourceGrant.getConnectionId()",
    "String SMDataSourceGrant.getDataSourceId()",
    "String SMDataSourceGrant.getSubjectId()",
    "SMSubjectType SMDataSourceGrant.getSubjectType()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMDataSourceGrant actualSmDataSourceGrant =
        new SMDataSourceGrant("42", "42", SMSubjectType.user);
    String actualConnectionId = actualSmDataSourceGrant.getConnectionId();
    String actualDataSourceId = actualSmDataSourceGrant.getDataSourceId();
    String actualSubjectId = actualSmDataSourceGrant.getSubjectId();

    // Assert
    assertEquals("42", actualConnectionId);
    assertEquals("42", actualDataSourceId);
    assertEquals("42", actualSubjectId);
    assertEquals(SMSubjectType.user, actualSmDataSourceGrant.getSubjectType());
  }
}
