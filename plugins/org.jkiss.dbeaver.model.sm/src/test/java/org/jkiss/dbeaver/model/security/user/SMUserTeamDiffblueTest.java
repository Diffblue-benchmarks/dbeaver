package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMUserTeamDiffblueTest {
  /**
   * Test {@link SMUserTeam#SMUserTeam(SMTeam, String)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link SMTeam#SMTeam(String)} with teamId is {@code 42} TeamName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SMUserTeam#SMUserTeam(SMTeam, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMUserTeam.<init>(SMTeam, String)"})
  public void testNewSMUserTeam_givenEmptyString_whenSMTeamWithTeamIdIs42TeamNameIsEmptyString() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");
    smTeam.setTeamName("");

    // Act
    SMUserTeam actualSmUserTeam = new SMUserTeam(smTeam, "Team Role");

    // Assert
    assertEquals("42", actualSmUserTeam.getName());
    assertEquals("42", actualSmUserTeam.getTeamName());
    assertNull(actualSmUserTeam.getDescription());
    assertTrue(actualSmUserTeam.isSecretStorage());
  }

  /**
   * Test {@link SMUserTeam#SMUserTeam(SMTeam, String)}.
   *
   * <ul>
   *   <li>Given {@code Sm Team}.
   *   <li>Then return Name is {@code Sm Team}.
   * </ul>
   *
   * <p>Method under test: {@link SMUserTeam#SMUserTeam(SMTeam, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMUserTeam.<init>(SMTeam, String)"})
  public void testNewSMUserTeam_givenSmTeam_thenReturnNameIsSmTeam() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");
    smTeam.setTeamName("Sm Team");

    // Act
    SMUserTeam actualSmUserTeam = new SMUserTeam(smTeam, "Team Role");

    // Assert
    assertEquals("Sm Team", actualSmUserTeam.getName());
    assertEquals("Sm Team", actualSmUserTeam.getTeamName());
    assertNull(actualSmUserTeam.getDescription());
    assertTrue(actualSmUserTeam.isSecretStorage());
  }

  /**
   * Test {@link SMUserTeam#SMUserTeam(SMTeam, String)}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SMUserTeam#SMUserTeam(SMTeam, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMUserTeam.<init>(SMTeam, String)"})
  public void testNewSMUserTeam_thenReturnName() {
    // Arrange
    SMTeam smTeam = new SMTeam("42", "Name", "The characteristics of someone or something", false);

    // Act
    SMUserTeam actualSmUserTeam = new SMUserTeam(smTeam, "Team Role");

    // Assert
    assertEquals("Name", actualSmUserTeam.getName());
    assertEquals("Name", actualSmUserTeam.getTeamName());
    assertEquals("The characteristics of someone or something", actualSmUserTeam.getDescription());
    assertFalse(actualSmUserTeam.isSecretStorage());
  }

  /**
   * Test {@link SMUserTeam#SMUserTeam(SMTeam, String)}.
   *
   * <ul>
   *   <li>When {@link SMTeam#SMTeam(String)} with teamId is {@code 42}.
   *   <li>Then return Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SMUserTeam#SMUserTeam(SMTeam, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMUserTeam.<init>(SMTeam, String)"})
  public void testNewSMUserTeam_whenSMTeamWithTeamIdIs42_thenReturnNameIs42() {
    // Arrange and Act
    SMUserTeam actualSmUserTeam = new SMUserTeam(new SMTeam("42"), "Team Role");

    // Assert
    assertEquals("42", actualSmUserTeam.getName());
    assertEquals("42", actualSmUserTeam.getTeamName());
    assertNull(actualSmUserTeam.getDescription());
    assertTrue(actualSmUserTeam.isSecretStorage());
  }

  /**
   * Test {@link SMUserTeam#getTeamRole()}.
   *
   * <p>Method under test: {@link SMUserTeam#getTeamRole()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMUserTeam.getTeamRole()"})
  public void testGetTeamRole() {
    // Arrange, Act and Assert
    assertEquals("Team Role", new SMUserTeam(new SMTeam("42"), "Team Role").getTeamRole());
  }
}
