package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMObjectPermissionsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMObjectPermissions#SMObjectPermissions(String, String[])}
   *   <li>{@link SMObjectPermissions#getObjectId()}
   *   <li>{@link SMObjectPermissions#getPermissions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMObjectPermissions.<init>(String, String[])",
    "String SMObjectPermissions.getObjectId()",
    "String[] SMObjectPermissions.getPermissions()"
  })
  public void testGettersAndSetters() {
    // Arrange
    String[] permissions = new String[] {"Permissions"};

    // Act
    SMObjectPermissions actualSmObjectPermissions = new SMObjectPermissions("42", permissions);
    String actualObjectId = actualSmObjectPermissions.getObjectId();
    String[] actualPermissions = actualSmObjectPermissions.getPermissions();

    // Assert
    assertEquals("42", actualObjectId);
    assertSame(permissions, actualPermissions);
    assertArrayEquals(new String[] {"Permissions"}, actualPermissions);
  }

  /**
   * Test {@link SMObjectPermissions#SMObjectPermissions(String, Collection)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then return Permissions is array of {@link String} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SMObjectPermissions#SMObjectPermissions(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMObjectPermissions.<init>(String, Collection)"})
  public void testNewSMObjectPermissions_givenFoo_thenReturnPermissionsIsArrayOfStringWithFoo() {
    // Arrange
    ArrayList<String> permissions = new ArrayList<>();
    permissions.add("foo");

    // Act
    SMObjectPermissions actualSmObjectPermissions = new SMObjectPermissions("42", permissions);

    // Assert
    assertEquals("42", actualSmObjectPermissions.getObjectId());
    assertArrayEquals(new String[] {"foo"}, actualSmObjectPermissions.getPermissions());
  }

  /**
   * Test {@link SMObjectPermissions#SMObjectPermissions(String, Collection)}.
   *
   * <ul>
   *   <li>Then return Permissions is array of {@link String} with {@code 42} and {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SMObjectPermissions#SMObjectPermissions(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMObjectPermissions.<init>(String, Collection)"})
  public void testNewSMObjectPermissions_thenReturnPermissionsIsArrayOfStringWith42AndFoo() {
    // Arrange
    ArrayList<String> permissions = new ArrayList<>();
    permissions.add("42");
    permissions.add("foo");

    // Act
    SMObjectPermissions actualSmObjectPermissions = new SMObjectPermissions("42", permissions);

    // Assert
    assertEquals("42", actualSmObjectPermissions.getObjectId());
    assertArrayEquals(new String[] {"42", "foo"}, actualSmObjectPermissions.getPermissions());
  }

  /**
   * Test {@link SMObjectPermissions#SMObjectPermissions(String, Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link SMObjectPermissions#SMObjectPermissions(String, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMObjectPermissions.<init>(String, Collection)"})
  public void testNewSMObjectPermissions_whenArrayList_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    SMObjectPermissions actualSmObjectPermissions =
        new SMObjectPermissions("42", new ArrayList<>());

    // Assert
    assertEquals("42", actualSmObjectPermissions.getObjectId());
    assertEquals(0, actualSmObjectPermissions.getPermissions().length);
  }
}
