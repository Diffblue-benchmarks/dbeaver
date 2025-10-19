package org.jkiss.dbeaver.model.net.ssh.config;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSHPortForwardConfigurationDiffblueTest {
  /**
   * Test {@link SSHPortForwardConfiguration#toDisplayString()}.
   *
   * <p>Method under test: {@link SSHPortForwardConfiguration#toDisplayString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String SSHPortForwardConfiguration.toDisplayString()"})
  public void testToDisplayString() {
    // Arrange
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act and Assert
    assertEquals("localhost:8080 <- localhost:8080", sshPortForwardConfiguration.toDisplayString());
  }

  /**
   * Test {@link SSHPortForwardConfiguration#toString()}.
   *
   * <p>Method under test: {@link SSHPortForwardConfiguration#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String SSHPortForwardConfiguration.toString()"})
  public void testToString() {
    // Arrange
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act and Assert
    assertEquals("l*****t:8080 <- l*****t:8080", sshPortForwardConfiguration.toString());
  }
}
