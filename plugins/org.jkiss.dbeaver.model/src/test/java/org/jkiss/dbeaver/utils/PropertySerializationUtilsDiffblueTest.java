package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PropertySerializationUtilsDiffblueTest {
  /**
   * Test {@link PropertySerializationUtils#baseNonSecurePropertiesGsonBuilder()}.
   *
   * <p>Method under test: {@link PropertySerializationUtils#baseNonSecurePropertiesGsonBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder PropertySerializationUtils.baseNonSecurePropertiesGsonBuilder()"})
  public void testBaseNonSecurePropertiesGsonBuilder() {
    // Arrange, Act and Assert
    Gson createResult = PropertySerializationUtils.baseNonSecurePropertiesGsonBuilder().create();
    assertFalse(createResult.serializeNulls());
    assertTrue(createResult.htmlSafe());
  }

  /**
   * Test {@link PropertySerializationUtils#baseSecurePropertiesGsonBuilder()}.
   *
   * <p>Method under test: {@link PropertySerializationUtils#baseSecurePropertiesGsonBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GsonBuilder PropertySerializationUtils.baseSecurePropertiesGsonBuilder()"})
  public void testBaseSecurePropertiesGsonBuilder() {
    // Arrange, Act and Assert
    Gson createResult = PropertySerializationUtils.baseSecurePropertiesGsonBuilder().create();
    assertFalse(createResult.serializeNulls());
    assertTrue(createResult.htmlSafe());
  }
}
