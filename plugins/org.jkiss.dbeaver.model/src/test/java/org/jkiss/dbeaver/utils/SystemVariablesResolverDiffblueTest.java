package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Properties;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SystemVariablesResolverDiffblueTest {
  /**
   * Test {@link SystemVariablesResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link SystemVariablesResolver#INSTANCE}.
   *   <li>When {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SystemVariablesResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SystemVariablesResolver.get(String)"})
  public void testGet_givenInstance_whenName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SystemVariablesResolver.INSTANCE.get("Name"));
  }

  /**
   * Test {@link SystemVariablesResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link SystemVariablesResolver#INSTANCE}.
   *   <li>When {@code user.home}.
   *   <li>Then return Property is {@code user.home}.
   * </ul>
   *
   * <p>Method under test: {@link SystemVariablesResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SystemVariablesResolver.get(String)"})
  public void testGet_givenInstance_whenUserHome_thenReturnPropertyIsUserHome() {
    // Arrange, Act and Assert
    assertEquals(
        System.getProperty("user.home"), SystemVariablesResolver.INSTANCE.get("user.home"));
  }

  /**
   * Test {@link SystemVariablesResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link SystemVariablesResolver#INSTANCE}.
   *   <li>When {@link SystemVariablesResolver#VAR_HOME}.
   *   <li>Then return Property is {@code user.home}.
   * </ul>
   *
   * <p>Method under test: {@link SystemVariablesResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SystemVariablesResolver.get(String)"})
  public void testGet_givenInstance_whenVar_home_thenReturnPropertyIsUserHome() {
    // Arrange, Act and Assert
    assertEquals(
        System.getProperty("user.home"),
        SystemVariablesResolver.INSTANCE.get(SystemVariablesResolver.VAR_HOME));
  }

  /**
   * Test {@link SystemVariablesResolver#getUserHome()}.
   *
   * <p>Method under test: {@link SystemVariablesResolver#getUserHome()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SystemVariablesResolver.getUserHome()"})
  public void testGetUserHome() {
    // Arrange, Act and Assert
    assertEquals(System.getProperty("user.home"), SystemVariablesResolver.getUserHome());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SystemVariablesResolver}
   *   <li>{@link SystemVariablesResolver#setConfiguration(Properties)}
   *   <li>{@link SystemVariablesResolver#isResolveSystemVariables()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SystemVariablesResolver.<init>()",
    "boolean SystemVariablesResolver.isResolveSystemVariables()",
    "void SystemVariablesResolver.setConfiguration(Properties)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SystemVariablesResolver actualSystemVariablesResolver = new SystemVariablesResolver();
    actualSystemVariablesResolver.setConfiguration(new Properties());

    // Assert
    assertTrue(actualSystemVariablesResolver.isResolveSystemVariables());
  }
}
