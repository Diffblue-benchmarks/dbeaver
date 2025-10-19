package org.jkiss.dbeaver.model.websocket.gson;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.FieldAttributes;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSClientSideExclusionStrategyDiffblueTest {
  /**
   * Test {@link WSClientSideExclusionStrategy#shouldSkipClass(Class)}.
   *
   * <p>Method under test: {@link WSClientSideExclusionStrategy#shouldSkipClass(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WSClientSideExclusionStrategy.shouldSkipClass(Class)"})
  public void testShouldSkipClass() {
    // Arrange
    WSClientSideExclusionStrategy wsClientSideExclusionStrategy =
        new WSClientSideExclusionStrategy();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(wsClientSideExclusionStrategy.shouldSkipClass(clazz));
  }

  /**
   * Test {@link WSClientSideExclusionStrategy#shouldSkipField(FieldAttributes)}.
   *
   * <ul>
   *   <li>Given {@link HiddenField}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WSClientSideExclusionStrategy#shouldSkipField(FieldAttributes)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WSClientSideExclusionStrategy.shouldSkipField(FieldAttributes)"})
  public void testShouldSkipField_givenHiddenField_thenReturnTrue() {
    // Arrange
    WSClientSideExclusionStrategy wsClientSideExclusionStrategy =
        new WSClientSideExclusionStrategy();

    FieldAttributes f = mock(FieldAttributes.class);
    when(f.getAnnotation(HiddenField.class)).thenReturn(mock(HiddenField.class));

    // Act
    boolean actualShouldSkipFieldResult = wsClientSideExclusionStrategy.shouldSkipField(f);

    // Assert
    verify(f).getAnnotation(isA(Class.class));
    assertTrue(actualShouldSkipFieldResult);
  }

  /**
   * Test {@link WSClientSideExclusionStrategy#shouldSkipField(FieldAttributes)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link WSClientSideExclusionStrategy#shouldSkipField(FieldAttributes)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WSClientSideExclusionStrategy.shouldSkipField(FieldAttributes)"})
  public void testShouldSkipField_givenNull_thenReturnFalse() {
    // Arrange
    WSClientSideExclusionStrategy wsClientSideExclusionStrategy =
        new WSClientSideExclusionStrategy();

    FieldAttributes f = mock(FieldAttributes.class);
    when(f.getAnnotation(HiddenField.class)).thenReturn(null);

    // Act
    boolean actualShouldSkipFieldResult = wsClientSideExclusionStrategy.shouldSkipField(f);

    // Assert
    verify(f).getAnnotation(isA(Class.class));
    assertFalse(actualShouldSkipFieldResult);
  }
}
