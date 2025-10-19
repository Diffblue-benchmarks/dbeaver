package org.jkiss.dbeaver.bundle;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.osgi.internal.framework.BundleContextImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;

@RunWith(MockitoJUnitRunner.class)
public class ModelActivatorDiffblueTest {
  @InjectMocks private ModelActivator modelActivator;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ModelActivator}
   *   <li>{@link ModelActivator#getInstance()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ModelActivator.<init>()", "ModelActivator ModelActivator.getInstance()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new ModelActivator().getInstance());
  }

  /**
   * Test {@link ModelActivator#start(BundleContext)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link BundleContextImpl} {@link BundleContextImpl#getBundle()} return {@code null}.
   *   <li>Then calls {@link BundleContextImpl#getBundle()}.
   * </ul>
   *
   * <p>Method under test: {@link ModelActivator#start(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ModelActivator.start(BundleContext)"})
  public void testStart_givenNull_whenBundleContextImplGetBundleReturnNull_thenCallsGetBundle()
      throws Exception {
    // Arrange
    BundleContextImpl context = mock(BundleContextImpl.class);
    when(context.getBundle()).thenReturn(null);

    // Act
    modelActivator.start(context);

    // Assert
    verify(context).getBundle();
  }
}
