package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMTeamDiffblueTest {
  /**
   * Test {@link SMTeam#SMTeam(String)}.
   *
   * <p>Method under test: {@link SMTeam#SMTeam(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMTeam.<init>(String)"})
  public void testNewSMTeam() {
    // Arrange and Act
    SMTeam actualSmTeam = new SMTeam("42");

    // Assert
    assertEquals("42", actualSmTeam.getSubjectId());
    assertEquals("42", actualSmTeam.getName());
    assertEquals("42", actualSmTeam.getTeamId());
    assertEquals("42", actualSmTeam.getTeamName());
    assertNull(actualSmTeam.getDescription());
    assertTrue(actualSmTeam.getMetaParameters().isEmpty());
    assertTrue(actualSmTeam.getPermissions().isEmpty());
    assertTrue(actualSmTeam.isSecretStorage());
  }

  /**
   * Test {@link SMTeam#SMTeam(String, String, String, boolean)}.
   *
   * <p>Method under test: {@link SMTeam#SMTeam(String, String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMTeam.<init>(String, String, String, boolean)"})
  public void testNewSMTeam2() {
    // Arrange and Act
    SMTeam actualSmTeam =
        new SMTeam("42", "Name", "The characteristics of someone or something", true);

    // Assert
    assertEquals("42", actualSmTeam.getSubjectId());
    assertEquals("42", actualSmTeam.getTeamId());
    assertEquals("Name", actualSmTeam.getName());
    assertEquals("Name", actualSmTeam.getTeamName());
    assertEquals("The characteristics of someone or something", actualSmTeam.getDescription());
    assertTrue(actualSmTeam.getMetaParameters().isEmpty());
    assertTrue(actualSmTeam.getPermissions().isEmpty());
    assertTrue(actualSmTeam.isSecretStorage());
  }

  /**
   * Test {@link SMTeam#getName()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42} TeamName is empty string.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SMTeam#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMTeam.getName()"})
  public void testGetName_givenSMTeamWithTeamIdIs42TeamNameIsEmptyString_thenReturn42() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");
    smTeam.setTeamName("");

    // Act and Assert
    assertEquals("42", smTeam.getName());
  }

  /**
   * Test {@link SMTeam#getName()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42} TeamName is {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SMTeam#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMTeam.getName()"})
  public void testGetName_givenSMTeamWithTeamIdIs42TeamNameIsFoo_thenReturnFoo() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");
    smTeam.setTeamName("foo");

    // Act and Assert
    assertEquals("foo", smTeam.getName());
  }

  /**
   * Test {@link SMTeam#getName()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SMTeam#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMTeam.getName()"})
  public void testGetName_givenSMTeamWithTeamIdIs42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", new SMTeam("42").getName());
  }

  /**
   * Test {@link SMTeam#getTeamName()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42} TeamName is empty string.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SMTeam#getTeamName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMTeam.getTeamName()"})
  public void testGetTeamName_givenSMTeamWithTeamIdIs42TeamNameIsEmptyString_thenReturn42() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");
    smTeam.setTeamName("");

    // Act and Assert
    assertEquals("42", smTeam.getTeamName());
  }

  /**
   * Test {@link SMTeam#getTeamName()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42} TeamName is {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SMTeam#getTeamName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMTeam.getTeamName()"})
  public void testGetTeamName_givenSMTeamWithTeamIdIs42TeamNameIsFoo_thenReturnFoo() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");
    smTeam.setTeamName("foo");

    // Act and Assert
    assertEquals("foo", smTeam.getTeamName());
  }

  /**
   * Test {@link SMTeam#getTeamName()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SMTeam#getTeamName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMTeam.getTeamName()"})
  public void testGetTeamName_givenSMTeamWithTeamIdIs42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", new SMTeam("42").getTeamName());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMTeam#setDescription(String)}
   *   <li>{@link SMTeam#setPermissions(Set)}
   *   <li>{@link SMTeam#setTeamName(String)}
   *   <li>{@link SMTeam#toString()}
   *   <li>{@link SMTeam#getDescription()}
   *   <li>{@link SMTeam#getPermissions()}
   *   <li>{@link SMTeam#getTeamId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SMTeam.getDescription()",
    "Set SMTeam.getPermissions()",
    "String SMTeam.getTeamId()",
    "void SMTeam.setDescription(String)",
    "void SMTeam.setPermissions(Set)",
    "void SMTeam.setTeamName(String)",
    "String SMTeam.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");

    // Act
    smTeam.setDescription("The characteristics of someone or something");
    HashSet<String> permissions = new HashSet<>();
    smTeam.setPermissions(permissions);
    smTeam.setTeamName("Team Name");
    String actualToStringResult = smTeam.toString();
    String actualDescription = smTeam.getDescription();
    Set<String> actualPermissions = smTeam.getPermissions();

    // Assert
    assertEquals("42", smTeam.getTeamId());
    assertEquals("42", actualToStringResult);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(actualPermissions.isEmpty());
    assertSame(permissions, actualPermissions);
  }

  /**
   * Test {@link SMTeam#addPermission(String)}.
   *
   * <p>Method under test: {@link SMTeam#addPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMTeam.addPermission(String)"})
  public void testAddPermission() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");

    // Act
    smTeam.addPermission("Permission");

    // Assert
    Set<String> permissions = smTeam.getPermissions();
    assertEquals(1, permissions.size());
    assertTrue(permissions.contains("Permission"));
  }
}
