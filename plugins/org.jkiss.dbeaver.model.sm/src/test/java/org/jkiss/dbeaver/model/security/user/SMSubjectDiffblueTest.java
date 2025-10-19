package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMSubjectDiffblueTest {
  /**
   * Test {@link SMSubject#getSubjectId()}.
   *
   * <p>Method under test: {@link SMSubject#getSubjectId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMSubject.getSubjectId()"})
  public void testGetSubjectId() {
    // Arrange, Act and Assert
    assertEquals("42", new SMTeam("42").getSubjectId());
  }

  /**
   * Test {@link SMSubject#getMetaParameters()}.
   *
   * <p>Method under test: {@link SMSubject#getMetaParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SMSubject.getMetaParameters()"})
  public void testGetMetaParameters() {
    // Arrange, Act and Assert
    assertTrue(new SMTeam("42").getMetaParameters().isEmpty());
  }

  /**
   * Test {@link SMSubject#setMetaParameter(String, String)}.
   *
   * <p>Method under test: {@link SMSubject#setMetaParameter(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMSubject.setMetaParameter(String, String)"})
  public void testSetMetaParameter() {
    // Arrange
    SMTeam smTeam = new SMTeam("42");

    // Act
    smTeam.setMetaParameter("Name", "42");

    // Assert
    Map<String, String> metaParameters = smTeam.getMetaParameters();
    assertEquals(1, metaParameters.size());
    assertEquals("42", metaParameters.get("Name"));
  }

  /**
   * Test {@link SMSubject#isSecretStorage()}.
   *
   * <ul>
   *   <li>Given {@link SMTeam#SMTeam(String)} with teamId is {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SMSubject#isSecretStorage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SMSubject.isSecretStorage()"})
  public void testIsSecretStorage_givenSMTeamWithTeamIdIs42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new SMTeam("42").isSecretStorage());
  }

  /**
   * Test {@link SMSubject#isSecretStorage()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SMSubject#isSecretStorage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SMSubject.isSecretStorage()"})
  public void testIsSecretStorage_thenReturnFalse() {
    // Arrange
    SMTeam smTeam = new SMTeam("42", "Name", "The characteristics of someone or something", false);

    // Act and Assert
    assertFalse(smTeam.isSecretStorage());
  }
}
