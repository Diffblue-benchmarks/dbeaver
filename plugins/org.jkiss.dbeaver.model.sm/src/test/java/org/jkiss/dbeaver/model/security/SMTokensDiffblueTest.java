package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMTokensDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMTokens#SMTokens(String, String)}
   *   <li>{@link SMTokens#getSmAccessToken()}
   *   <li>{@link SMTokens#getSmRefreshToken()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMTokens.<init>(String, String)",
    "String SMTokens.getSmAccessToken()",
    "String SMTokens.getSmRefreshToken()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMTokens actualSmTokens = new SMTokens("ABC123", "ABC123");
    String actualSmAccessToken = actualSmTokens.getSmAccessToken();

    // Assert
    assertEquals("ABC123", actualSmAccessToken);
    assertEquals("ABC123", actualSmTokens.getSmRefreshToken());
  }
}
