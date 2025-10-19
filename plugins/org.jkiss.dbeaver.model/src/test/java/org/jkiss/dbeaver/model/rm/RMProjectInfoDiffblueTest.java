package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMProjectInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProjectInfo#RMProjectInfo()}
   *   <li>{@link RMProjectInfo#setDescription(String)}
   *   <li>{@link RMProjectInfo#setName(String)}
   *   <li>{@link RMProjectInfo#getDescription()}
   *   <li>{@link RMProjectInfo#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMProjectInfo.<init>()",
    "void RMProjectInfo.<init>(String, String)",
    "String RMProjectInfo.getDescription()",
    "String RMProjectInfo.getName()",
    "void RMProjectInfo.setDescription(String)",
    "void RMProjectInfo.setName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RMProjectInfo actualRmProjectInfo = new RMProjectInfo();
    actualRmProjectInfo.setDescription("The characteristics of someone or something");
    actualRmProjectInfo.setName("Name");
    String actualDescription = actualRmProjectInfo.getDescription();

    // Assert
    assertEquals("Name", actualRmProjectInfo.getName());
    assertEquals("The characteristics of someone or something", actualDescription);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProjectInfo#RMProjectInfo(String, String)}
   *   <li>{@link RMProjectInfo#setDescription(String)}
   *   <li>{@link RMProjectInfo#setName(String)}
   *   <li>{@link RMProjectInfo#getDescription()}
   *   <li>{@link RMProjectInfo#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMProjectInfo.<init>()",
    "void RMProjectInfo.<init>(String, String)",
    "String RMProjectInfo.getDescription()",
    "String RMProjectInfo.getName()",
    "void RMProjectInfo.setDescription(String)",
    "void RMProjectInfo.setName(String)"
  })
  public void testGettersAndSetters_whenName() {
    // Arrange and Act
    RMProjectInfo actualRmProjectInfo =
        new RMProjectInfo("Name", "The characteristics of someone or something");
    actualRmProjectInfo.setDescription("The characteristics of someone or something");
    actualRmProjectInfo.setName("Name");
    String actualDescription = actualRmProjectInfo.getDescription();

    // Assert
    assertEquals("Name", actualRmProjectInfo.getName());
    assertEquals("The characteristics of someone or something", actualDescription);
  }
}
