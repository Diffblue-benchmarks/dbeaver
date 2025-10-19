package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.equinox.app.IApplication;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPositiveNumberTransformerDiffblueTest {
  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_when05_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, -0.5f));
  }

  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_when10e10_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, 1.0E-10d));
  }

  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@link IApplication#EXIT_OK}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_whenExit_ok_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, IApplication.EXIT_OK));
  }

  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, null));
  }

  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return longValue is one.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_whenOne_thenReturnLongValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1L, new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, 1L).longValue());
  }

  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return floatValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_whenTen_thenReturnFloatValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0f,
        new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, 10.0f).floatValue(),
        0.0f);
  }

  /**
   * Test {@link DBPositiveNumberTransformer#transform(Object, Number)} with {@code Object}, {@code
   * Number}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPositiveNumberTransformer#transform(Object, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBPositiveNumberTransformer.transform(Object, Number)"})
  public void testTransformWithObjectNumber_whenZero_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBPositiveNumberTransformer().transform(DBPEvent.RENAME, 0.0d));
  }
}
