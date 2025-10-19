package org.jkiss.dbeaver.model.net.ssh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.jcraft.jsch.Identity;
import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.common.Buffer.BufferException;
import net.schmizz.sshj.common.LoggerFactory;
import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBeaverAuthAgentDiffblueTest {
  /**
   * Test {@link DBeaverAuthAgent#DBeaverAuthAgent(Identity)}.
   *
   * <p>Method under test: {@link DBeaverAuthAgent#DBeaverAuthAgent(Identity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverAuthAgent.<init>(Identity)"})
  public void testNewDBeaverAuthAgent() throws BufferException {
    // Arrange, Act and Assert
    assertEquals("publickey", new DBeaverAuthAgent(mock(Identity.class)).getName());
  }

  /**
   * Test {@link DBeaverAuthAgent#handle(Message, SSHPacket)}.
   *
   * <ul>
   *   <li>Given {@link DBeaverAuthAgent#DBeaverAuthAgent(Identity)} with {@link Identity}
   *       LoggerFactory is {@link LoggerFactory#DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverAuthAgent#handle(Message, SSHPacket)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverAuthAgent.handle(Message, SSHPacket)"})
  public void testHandle_givenDBeaverAuthAgentWithIdentityLoggerFactoryIsDefault()
      throws BufferException, TransportException, UserAuthException {
    // Arrange
    DBeaverAuthAgent dBeaverAuthAgent = new DBeaverAuthAgent(mock(Identity.class));
    dBeaverAuthAgent.setLoggerFactory(LoggerFactory.DEFAULT);

    // Act and Assert
    assertThrows(
        UserAuthException.class, () -> dBeaverAuthAgent.handle(Message.UNKNOWN, new SSHPacket()));
  }

  /**
   * Test {@link DBeaverAuthAgent#handle(Message, SSHPacket)}.
   *
   * <ul>
   *   <li>Given {@link DBeaverAuthAgent#DBeaverAuthAgent(Identity)} with {@link Identity}.
   *   <li>Then throw {@link UserAuthException}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverAuthAgent#handle(Message, SSHPacket)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverAuthAgent.handle(Message, SSHPacket)"})
  public void testHandle_givenDBeaverAuthAgentWithIdentity_thenThrowUserAuthException()
      throws BufferException, TransportException, UserAuthException {
    // Arrange
    DBeaverAuthAgent dBeaverAuthAgent = new DBeaverAuthAgent(mock(Identity.class));

    // Act and Assert
    assertThrows(
        UserAuthException.class, () -> dBeaverAuthAgent.handle(Message.UNKNOWN, new SSHPacket()));
  }
}
