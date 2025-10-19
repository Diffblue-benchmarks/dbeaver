package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.equinox.app.IApplication;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDummyNumberTransformerDiffblueTest {
  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_when05_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), -0.5f));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_when10e10_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), 1.0E-10d));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@code 2147483647}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_when2147483647_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), 2147483647L));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@link IApplication#EXIT_OK}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenExit_ok_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), IApplication.EXIT_OK));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@link Double#MAX_VALUE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenMax_value_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), Double.MAX_VALUE));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@link Float#MAX_VALUE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenMax_value_thenReturnNull2() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), Float.MAX_VALUE));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenMax_value_thenReturnNull3() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), Long.MAX_VALUE));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenNull_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), null));
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return longValue is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenOne_thenReturnLongValueIsOne() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertEquals(
        1L,
        dbDummyNumberTransformer
            .transform(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), 1L)
            .longValue());
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return floatValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenTen_thenReturnFloatValueIsTen() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertEquals(
        10.0f,
        dbDummyNumberTransformer
            .transform(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), 10.0f)
            .floatValue(),
        0.0f);
  }

  /**
   * Test {@link DBDummyNumberTransformer#transform(DBSObject, Number)} with {@code DBSObject},
   * {@code Number}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDummyNumberTransformer#transform(DBSObject, Number)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number DBDummyNumberTransformer.transform(DBSObject, Number)"})
  public void testTransformWithDBSObjectNumber_whenZero_thenReturnNull() {
    // Arrange
    DBDummyNumberTransformer dbDummyNumberTransformer = new DBDummyNumberTransformer();

    // Act and Assert
    assertNull(
        dbDummyNumberTransformer.transform(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), 0.0d));
  }
}
