package org.jkiss.dbeaver.model.net.ssh.config;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.net.ssh.config.SSHAuthConfiguration.Agent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSHHostConfigurationDiffblueTest {
  /**
   * Test {@link SSHHostConfiguration#toDisplayString()}.
   *
   * <p>Method under test: {@link SSHHostConfiguration#toDisplayString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String SSHHostConfiguration.toDisplayString()"})
  public void testToDisplayString() {
    // Arrange
    SSHHostConfiguration sshHostConfiguration =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    // Act and Assert
    assertEquals("janedoe@localhost:8080", sshHostConfiguration.toDisplayString());
  }

  /**
   * Test {@link SSHHostConfiguration#toString()}.
   *
   * <p>Method under test: {@link SSHHostConfiguration#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String SSHHostConfiguration.toString()"})
  public void testToString() {
    // Arrange
    SSHHostConfiguration sshHostConfiguration =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    // Act and Assert
    assertEquals("j*****e@l*****t:8080", sshHostConfiguration.toString());
  }
}
