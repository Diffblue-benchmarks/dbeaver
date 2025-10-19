package org.jkiss.dbeaver.ext.firebird.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FireBirdPlanTokenMatcherDiffblueTest {
  /**
   * Test {@link FireBirdPlanTokenMatcher#FireBirdPlanTokenMatcher(String)}.
   *
   * <p>Method under test: {@link FireBirdPlanTokenMatcher#FireBirdPlanTokenMatcher(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanTokenMatcher.<init>(String)"})
  public void testNewFireBirdPlanTokenMatcher() {
    // Arrange and Act
    FireBirdPlanTokenMatcher actualFireBirdPlanTokenMatcher =
        new FireBirdPlanTokenMatcher("Hello from the Dreaming Spires");

    // Assert
    assertNull(actualFireBirdPlanTokenMatcher.getValue());
    assertNull(actualFireBirdPlanTokenMatcher.getToken());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FireBirdPlanTokenMatcher#getToken()}
   *   <li>{@link FireBirdPlanTokenMatcher#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "FireBirdPlanToken FireBirdPlanTokenMatcher.getToken()",
    "String FireBirdPlanTokenMatcher.getValue()"
  })
  public void testGettersAndSetters() {
    // Arrange
    FireBirdPlanTokenMatcher fireBirdPlanTokenMatcher =
        new FireBirdPlanTokenMatcher("Hello from the Dreaming Spires");

    // Act
    FireBirdPlanToken actualToken = fireBirdPlanTokenMatcher.getToken();

    // Assert
    assertNull(fireBirdPlanTokenMatcher.getValue());
    assertNull(actualToken);
  }

  /**
   * Test {@link FireBirdPlanTokenMatcher#find()}.
   *
   * <p>Method under test: {@link FireBirdPlanTokenMatcher#find()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanTokenMatcher.find()"})
  public void testFind() {
    // Arrange
    FireBirdPlanTokenMatcher fireBirdPlanTokenMatcher =
        new FireBirdPlanTokenMatcher("Hello from the Dreaming Spires");

    // Act
    fireBirdPlanTokenMatcher.find();

    // Assert
    assertEquals("Hello", fireBirdPlanTokenMatcher.getValue());
    assertEquals(FireBirdPlanToken.IDENTIFICATOR, fireBirdPlanTokenMatcher.getToken());
  }

  /**
   * Test {@link FireBirdPlanTokenMatcher#find()}.
   *
   * <p>Method under test: {@link FireBirdPlanTokenMatcher#find()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanTokenMatcher.find()"})
  public void testFind2() {
    // Arrange
    FireBirdPlanTokenMatcher fireBirdPlanTokenMatcher = new FireBirdPlanTokenMatcher("???");

    // Act
    fireBirdPlanTokenMatcher.find();

    // Assert
    assertEquals("???", fireBirdPlanTokenMatcher.getValue());
    assertEquals(FireBirdPlanToken.IDENTIFICATOR, fireBirdPlanTokenMatcher.getToken());
  }

  /**
   * Test {@link FireBirdPlanTokenMatcher#jump()}.
   *
   * <p>Method under test: {@link FireBirdPlanTokenMatcher#jump()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanTokenMatcher.jump()"})
  public void testJump() {
    // Arrange
    FireBirdPlanTokenMatcher fireBirdPlanTokenMatcher =
        new FireBirdPlanTokenMatcher("Hello from the Dreaming Spires");

    // Act
    fireBirdPlanTokenMatcher.jump();

    // Assert
    assertEquals("Hello", fireBirdPlanTokenMatcher.getValue());
    assertEquals(FireBirdPlanToken.IDENTIFICATOR, fireBirdPlanTokenMatcher.getToken());
  }

  /**
   * Test {@link FireBirdPlanTokenMatcher#jump()}.
   *
   * <p>Method under test: {@link FireBirdPlanTokenMatcher#jump()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanTokenMatcher.jump()"})
  public void testJump2() {
    // Arrange
    FireBirdPlanTokenMatcher fireBirdPlanTokenMatcher = new FireBirdPlanTokenMatcher("???");

    // Act
    fireBirdPlanTokenMatcher.jump();

    // Assert
    assertEquals("???", fireBirdPlanTokenMatcher.getValue());
    assertEquals(FireBirdPlanToken.IDENTIFICATOR, fireBirdPlanTokenMatcher.getToken());
  }
}
