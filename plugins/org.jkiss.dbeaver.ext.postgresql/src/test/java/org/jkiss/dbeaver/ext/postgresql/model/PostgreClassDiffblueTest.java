package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreClass.RelKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreClassDiffblueTest {
  /**
   * Test RelKind getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RelKind#RelKind(String)}
   *   <li>{@link RelKind#getCode()}
   *   <li>{@link RelKind#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RelKind.<init>(String)",
    "String RelKind.getCode()",
    "String RelKind.toString()"
  })
  public void testRelKindGettersAndSetters() {
    // Arrange and Act
    RelKind actualRelKind = new RelKind("Code");
    String actualCode = actualRelKind.getCode();

    // Assert
    assertEquals("Code", actualCode);
    assertEquals("Code", actualRelKind.toString());
  }

  /**
   * Test RelKind {@link RelKind#valueOf(String)}.
   *
   * <ul>
   *   <li>When {@code Code}.
   *   <li>Then return {@code Code}.
   * </ul>
   *
   * <p>Method under test: {@link RelKind#valueOf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RelKind RelKind.valueOf(String)"})
  public void testRelKindValueOf_whenCode_thenReturnCode() {
    // Arrange and Act
    RelKind actualValueOfResult = RelKind.valueOf("Code");

    // Assert
    assertEquals("Code", actualValueOfResult.getCode());
    assertEquals("Code", actualValueOfResult.toString());
  }

  /**
   * Test RelKind {@link RelKind#valueOf(String)}.
   *
   * <ul>
   *   <li>When {@code I}.
   *   <li>Then return Code is {@code I}.
   * </ul>
   *
   * <p>Method under test: {@link RelKind#valueOf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RelKind RelKind.valueOf(String)"})
  public void testRelKindValueOf_whenI_thenReturnCodeIsI() {
    // Arrange and Act
    RelKind actualValueOfResult = RelKind.valueOf("I");

    // Assert
    assertEquals("I", actualValueOfResult.getCode());
    assertEquals("I", actualValueOfResult.toString());
  }
}
