package org.jkiss.dbeaver.model.net.ssh.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.net.ssh.config.SSHAuthConfiguration.Agent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSHAuthConfigurationDiffblueTest {
  /**
   * Test Agent {@link Agent#equals(Object)}, and {@link Agent#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Agent#equals(Object)}
   *   <li>{@link Agent#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Agent.equals(Object)", "int Agent.hashCode()"})
  public void testAgentEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Agent agent = new Agent();
    Agent agent2 = new Agent();

    // Act and Assert
    assertEquals(agent, agent2);
    assertEquals(agent.hashCode(), agent2.hashCode());
  }

  /**
   * Test Agent {@link Agent#equals(Object)}, and {@link Agent#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Agent#equals(Object)}
   *   <li>{@link Agent#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Agent.equals(Object)", "int Agent.hashCode()"})
  public void testAgentEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Agent agent = new Agent();

    // Act and Assert
    assertEquals(agent, agent);
    int expectedHashCodeResult = agent.hashCode();
    assertEquals(expectedHashCodeResult, agent.hashCode());
  }

  /**
   * Test Agent {@link Agent#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Agent#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Agent.equals(Object)", "int Agent.hashCode()"})
  public void testAgentEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Agent(), null);
  }

  /**
   * Test Agent {@link Agent#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Agent#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Agent.equals(Object)", "int Agent.hashCode()"})
  public void testAgentEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Agent(), "Different type to Agent");
  }

  /**
   * Test Agent getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Agent}
   *   <li>{@link Agent#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Agent.<init>()", "java.lang.String Agent.toString()"})
  public void testAgentGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Agent[]", new Agent().toString());
  }
}
