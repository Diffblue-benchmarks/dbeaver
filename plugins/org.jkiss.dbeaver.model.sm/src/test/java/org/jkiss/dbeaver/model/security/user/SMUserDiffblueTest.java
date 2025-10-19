package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMUserDiffblueTest {
  /**
   * Test {@link SMUser#SMUser(String, boolean, String)}.
   *
   * <p>Method under test: {@link SMUser#SMUser(String, boolean, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMUser.<init>(String, boolean, String)"})
  public void testNewSMUser() {
    // Arrange and Act
    SMUser actualSmUser = new SMUser("42", true, "Auth Role");

    // Assert
    assertEquals("42", actualSmUser.getSubjectId());
    assertEquals("42", actualSmUser.getName());
    assertEquals("42", actualSmUser.getUserId());
    assertEquals("Auth Role", actualSmUser.getAuthRole());
    assertNull(actualSmUser.getDisableReason());
    assertNull(actualSmUser.getDisabledBy());
    assertNull(actualSmUser.getDisableDate());
    assertEquals(0, actualSmUser.getUserTeams().length);
    assertTrue(actualSmUser.getMetaParameters().isEmpty());
    assertTrue(actualSmUser.isSecretStorage());
    assertTrue(actualSmUser.isEnabled());
  }

  /**
   * Test {@link SMUser#SMUser(String, boolean, String, boolean, Instant, String, String)}.
   *
   * <p>Method under test: {@link SMUser#SMUser(String, boolean, String, boolean, Instant, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUser.<init>(String, boolean, String, boolean, Instant, String, String)"
  })
  public void testNewSMUser2() {
    // Arrange and Act
    SMUser actualSmUser =
        new SMUser(
            "42",
            true,
            "Auth Role",
            true,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            "Disabled By",
            "Just cause");

    // Assert
    assertEquals("42", actualSmUser.getSubjectId());
    assertEquals("42", actualSmUser.getName());
    assertEquals("42", actualSmUser.getUserId());
    assertEquals("Auth Role", actualSmUser.getAuthRole());
    assertNull(actualSmUser.getDisableReason());
    assertNull(actualSmUser.getDisabledBy());
    assertNull(actualSmUser.getDisableDate());
    assertEquals(0, actualSmUser.getUserTeams().length);
    assertTrue(actualSmUser.getMetaParameters().isEmpty());
    assertTrue(actualSmUser.isSecretStorage());
    assertTrue(actualSmUser.isEnabled());
  }

  /**
   * Test {@link SMUser#SMUser(String, Map, String[], boolean, String, boolean, Instant, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#SMUser(String, Map, String[], boolean, String, boolean,
   * Instant, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUser.<init>(String, Map, String[], boolean, String, boolean, Instant, String, String)"
  })
  public void testNewSMUser_whenHashMap() {
    // Arrange
    String[] teams = new String[] {"Teams"};

    // Act
    SMUser actualSmUser =
        new SMUser(
            "42",
            new HashMap<>(),
            teams,
            true,
            "Auth Role",
            true,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            "Disabled By",
            "Just cause");

    // Assert
    assertEquals("42", actualSmUser.getSubjectId());
    assertEquals("42", actualSmUser.getName());
    assertEquals("42", actualSmUser.getUserId());
    assertEquals("Auth Role", actualSmUser.getAuthRole());
    assertNull(actualSmUser.getDisableReason());
    assertNull(actualSmUser.getDisabledBy());
    assertNull(actualSmUser.getDisableDate());
    assertTrue(actualSmUser.getMetaParameters().isEmpty());
    assertTrue(actualSmUser.isSecretStorage());
    assertTrue(actualSmUser.isEnabled());
    assertSame(teams, actualSmUser.getUserTeams());
  }

  /**
   * Test {@link SMUser#SMUser(String, Map, String[], boolean, String, boolean, Instant, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#SMUser(String, Map, String[], boolean, String, boolean,
   * Instant, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUser.<init>(String, Map, String[], boolean, String, boolean, Instant, String, String)"
  })
  public void testNewSMUser_whenNull() {
    // Arrange
    String[] teams = new String[] {"Teams"};

    // Act
    SMUser actualSmUser =
        new SMUser(
            "42",
            null,
            teams,
            true,
            "Auth Role",
            true,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            "Disabled By",
            "Just cause");

    // Assert
    assertEquals("42", actualSmUser.getSubjectId());
    assertEquals("42", actualSmUser.getName());
    assertEquals("42", actualSmUser.getUserId());
    assertEquals("Auth Role", actualSmUser.getAuthRole());
    assertNull(actualSmUser.getDisableReason());
    assertNull(actualSmUser.getDisabledBy());
    assertNull(actualSmUser.getDisableDate());
    assertTrue(actualSmUser.getMetaParameters().isEmpty());
    assertTrue(actualSmUser.isSecretStorage());
    assertTrue(actualSmUser.isEnabled());
    assertSame(teams, actualSmUser.getUserTeams());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMUser#enableUser(boolean)}
   *   <li>{@link SMUser#setAuthRole(String)}
   *   <li>{@link SMUser#setUserTeams(String[])}
   *   <li>{@link SMUser#getAuthRole()}
   *   <li>{@link SMUser#getName()}
   *   <li>{@link SMUser#getUserId()}
   *   <li>{@link SMUser#getUserTeams()}
   *   <li>{@link SMUser#isEnabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUser.enableUser(boolean)",
    "String SMUser.getAuthRole()",
    "String SMUser.getName()",
    "String SMUser.getUserId()",
    "String[] SMUser.getUserTeams()",
    "boolean SMUser.isEnabled()",
    "void SMUser.setAuthRole(String)",
    "void SMUser.setUserTeams(String[])"
  })
  public void testGettersAndSetters() {
    // Arrange
    SMUser smUser = new SMUser("42", true, "Auth Role");

    // Act
    smUser.enableUser(true);
    smUser.setAuthRole("Auth Role");
    String[] userTeams = new String[] {"User Teams"};
    smUser.setUserTeams(userTeams);
    String actualAuthRole = smUser.getAuthRole();
    String actualName = smUser.getName();
    String actualUserId = smUser.getUserId();
    String[] actualUserTeams = smUser.getUserTeams();

    // Assert
    assertEquals("42", actualName);
    assertEquals("42", actualUserId);
    assertEquals("Auth Role", actualAuthRole);
    assertTrue(smUser.isEnabled());
    assertSame(userTeams, actualUserTeams);
    assertArrayEquals(new String[] {"User Teams"}, actualUserTeams);
  }

  /**
   * Test {@link SMUser#getDisableDate()}.
   *
   * <ul>
   *   <li>Given {@link SMUser#SMUser(String, boolean, String)} with userId is {@code 42} and
   *       enabled is {@code false} and {@code Auth Role}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#getDisableDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Instant SMUser.getDisableDate()"})
  public void testGetDisableDate_givenSMUserWithUserIdIs42AndEnabledIsFalseAndAuthRole() {
    // Arrange, Act and Assert
    assertNull(new SMUser("42", false, "Auth Role").getDisableDate());
  }

  /**
   * Test {@link SMUser#getDisableDate()}.
   *
   * <ul>
   *   <li>Given {@link SMUser#SMUser(String, boolean, String)} with userId is {@code 42} and
   *       enabled is {@code true} and {@code Auth Role}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#getDisableDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Instant SMUser.getDisableDate()"})
  public void testGetDisableDate_givenSMUserWithUserIdIs42AndEnabledIsTrueAndAuthRole() {
    // Arrange, Act and Assert
    assertNull(new SMUser("42", true, "Auth Role").getDisableDate());
  }

  /**
   * Test {@link SMUser#getDisabledBy()}.
   *
   * <ul>
   *   <li>Given {@link SMUser#SMUser(String, boolean, String)} with userId is {@code 42} and
   *       enabled is {@code false} and {@code Auth Role}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#getDisabledBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMUser.getDisabledBy()"})
  public void testGetDisabledBy_givenSMUserWithUserIdIs42AndEnabledIsFalseAndAuthRole() {
    // Arrange, Act and Assert
    assertNull(new SMUser("42", false, "Auth Role").getDisabledBy());
  }

  /**
   * Test {@link SMUser#getDisabledBy()}.
   *
   * <ul>
   *   <li>Given {@link SMUser#SMUser(String, boolean, String)} with userId is {@code 42} and
   *       enabled is {@code true} and {@code Auth Role}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#getDisabledBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMUser.getDisabledBy()"})
  public void testGetDisabledBy_givenSMUserWithUserIdIs42AndEnabledIsTrueAndAuthRole() {
    // Arrange, Act and Assert
    assertNull(new SMUser("42", true, "Auth Role").getDisabledBy());
  }

  /**
   * Test {@link SMUser#getDisableReason()}.
   *
   * <ul>
   *   <li>Given {@link SMUser#SMUser(String, boolean, String)} with userId is {@code 42} and
   *       enabled is {@code false} and {@code Auth Role}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#getDisableReason()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMUser.getDisableReason()"})
  public void testGetDisableReason_givenSMUserWithUserIdIs42AndEnabledIsFalseAndAuthRole() {
    // Arrange, Act and Assert
    assertNull(new SMUser("42", false, "Auth Role").getDisableReason());
  }

  /**
   * Test {@link SMUser#getDisableReason()}.
   *
   * <ul>
   *   <li>Given {@link SMUser#SMUser(String, boolean, String)} with userId is {@code 42} and
   *       enabled is {@code true} and {@code Auth Role}.
   * </ul>
   *
   * <p>Method under test: {@link SMUser#getDisableReason()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMUser.getDisableReason()"})
  public void testGetDisableReason_givenSMUserWithUserIdIs42AndEnabledIsTrueAndAuthRole() {
    // Arrange, Act and Assert
    assertNull(new SMUser("42", true, "Auth Role").getDisableReason());
  }
}
