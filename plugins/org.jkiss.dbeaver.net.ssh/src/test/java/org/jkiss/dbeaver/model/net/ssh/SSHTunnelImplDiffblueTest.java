package org.jkiss.dbeaver.model.net.ssh;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSHTunnelImplDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SSHTunnelImpl}
   *   <li>{@link SSHTunnelImpl#getController()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SSHTunnelImpl.<init>()",
    "SSHSessionController SSHTunnelImpl.getController()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SSHTunnelImpl actualSshTunnelImpl = new SSHTunnelImpl();
    SSHSessionController actualController = actualSshTunnelImpl.getController();

    // Assert
    assertNull(actualSshTunnelImpl.getImplementation());
    assertNull(actualController);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SSHTunnelImpl}
   *   <li>{@link SSHTunnelImpl#getController()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SSHTunnelImpl.<init>()",
    "SSHSessionController SSHTunnelImpl.getController()"
  })
  public void testGettersAndSetters2() {
    // Arrange and Act
    SSHTunnelImpl actualSshTunnelImpl = new SSHTunnelImpl();
    SSHSessionController actualController = actualSshTunnelImpl.getController();

    // Assert
    assertNull(actualSshTunnelImpl.getImplementation());
    assertNull(actualController);
  }
}
