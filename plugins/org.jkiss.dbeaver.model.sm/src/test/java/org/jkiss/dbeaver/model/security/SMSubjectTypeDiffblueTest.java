package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMSubjectTypeDiffblueTest {
  /**
   * Test {@link SMSubjectType#getCode()}.
   *
   * <p>Method under test: {@link SMSubjectType#getCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SMSubjectType.getCode()"})
  public void testGetCode() {
    // Arrange, Act and Assert
    assertEquals("U", SMSubjectType.valueOf("user").getCode());
  }

  /**
   * Test {@link SMSubjectType#fromCode(String)}.
   *
   * <ul>
   *   <li>When {@code Code}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SMSubjectType#fromCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSubjectType SMSubjectType.fromCode(String)"})
  public void testFromCode_whenCode_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SMSubjectType.fromCode("Code"));
  }

  /**
   * Test {@link SMSubjectType#fromCode(String)}.
   *
   * <ul>
   *   <li>When {@code U}.
   *   <li>Then return {@code user}.
   * </ul>
   *
   * <p>Method under test: {@link SMSubjectType#fromCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSubjectType SMSubjectType.fromCode(String)"})
  public void testFromCode_whenU_thenReturnUser() {
    // Arrange, Act and Assert
    assertEquals(SMSubjectType.user, SMSubjectType.fromCode("U"));
  }
}
