package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultValueEncryptorDiffblueTest {
  /**
   * Test {@link DefaultValueEncryptor#makeSecretKeyFromPassword(String)}.
   *
   * <p>Method under test: {@link DefaultValueEncryptor#makeSecretKeyFromPassword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SecretKey DefaultValueEncryptor.makeSecretKeyFromPassword(String)"})
  public void testMakeSecretKeyFromPassword() {
    // Arrange and Act
    SecretKey actualMakeSecretKeyFromPasswordResult =
        DefaultValueEncryptor.makeSecretKeyFromPassword("iloveyou");

    // Assert
    assertTrue(actualMakeSecretKeyFromPasswordResult instanceof SecretKeySpec);
    assertEquals("RAW", actualMakeSecretKeyFromPasswordResult.getFormat());
    assertFalse(actualMakeSecretKeyFromPasswordResult.isDestroyed());
    assertEquals(
        DefaultValueEncryptor.KEY_ALGORITHM, actualMakeSecretKeyFromPasswordResult.getAlgorithm());
    assertArrayEquals(
        new byte[] {'i', 'l', 'o', 'v', 'e', 'y', 'o', 'u', 0, 0, 0, 0, 0, 0, 0, 0},
        actualMakeSecretKeyFromPasswordResult.getEncoded());
  }
}
