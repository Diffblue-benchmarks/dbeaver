package org.jkiss.dbeaver.slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;

public class DBeaverLogServiceProviderDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBeaverLogServiceProvider}
   *   <li>{@link DBeaverLogServiceProvider#getLoggerFactory()}
   *   <li>{@link DBeaverLogServiceProvider#getMDCAdapter()}
   *   <li>{@link DBeaverLogServiceProvider#getMarkerFactory()}
   *   <li>{@link DBeaverLogServiceProvider#getRequestedApiVersion()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBeaverLogServiceProvider.<init>()",
    "ILoggerFactory DBeaverLogServiceProvider.getLoggerFactory()",
    "MDCAdapter DBeaverLogServiceProvider.getMDCAdapter()",
    "IMarkerFactory DBeaverLogServiceProvider.getMarkerFactory()",
    "java.lang.String DBeaverLogServiceProvider.getRequestedApiVersion()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBeaverLogServiceProvider actualDBeaverLogServiceProvider = new DBeaverLogServiceProvider();
    ILoggerFactory actualLoggerFactory = actualDBeaverLogServiceProvider.getLoggerFactory();
    MDCAdapter actualMDCAdapter = actualDBeaverLogServiceProvider.getMDCAdapter();
    IMarkerFactory actualMarkerFactory = actualDBeaverLogServiceProvider.getMarkerFactory();

    // Assert
    assertEquals("2.0.99", actualDBeaverLogServiceProvider.getRequestedApiVersion());
    assertNull(actualLoggerFactory);
    assertNull(actualMarkerFactory);
    assertNull(actualMDCAdapter);
  }

  /**
   * Test {@link DBeaverLogServiceProvider#initialize()}.
   *
   * <p>Method under test: {@link DBeaverLogServiceProvider#initialize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverLogServiceProvider.initialize()"})
  public void testInitialize() {
    // Arrange
    DBeaverLogServiceProvider dBeaverLogServiceProvider = new DBeaverLogServiceProvider();

    // Act
    dBeaverLogServiceProvider.initialize();

    // Assert
    assertTrue(dBeaverLogServiceProvider.getLoggerFactory() instanceof DefaultLoggerBinder);
    assertTrue(dBeaverLogServiceProvider.getMarkerFactory() instanceof BasicMarkerFactory);
    MDCAdapter mDCAdapter = dBeaverLogServiceProvider.getMDCAdapter();
    assertTrue(mDCAdapter instanceof NOPMDCAdapter);
    assertNull(mDCAdapter.getCopyOfContextMap());
  }
}
