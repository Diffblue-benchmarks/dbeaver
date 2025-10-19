package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.net.ssl.SSLSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NullHostnameVerifierDiffblueTest {
  /**
   * Test {@link NullHostnameVerifier#verify(String, SSLSession)}.
   *
   * <p>Method under test: {@link NullHostnameVerifier#verify(String, SSLSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NullHostnameVerifier.verify(String, SSLSession)"})
  public void testVerify() {
    // Arrange, Act and Assert
    assertTrue(NullHostnameVerifier.INSTANCE.verify("localhost", null));
  }
}
