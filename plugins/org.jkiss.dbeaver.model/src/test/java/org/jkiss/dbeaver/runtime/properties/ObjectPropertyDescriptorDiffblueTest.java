package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.Method;
import org.jkiss.dbeaver.model.meta.Property;
import org.jkiss.dbeaver.model.preferences.DBPPropertySource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ObjectPropertyDescriptorDiffblueTest {
  /**
   * Test {@link ObjectPropertyDescriptor#ObjectPropertyDescriptor(DBPPropertySource,
   * ObjectPropertyGroupDescriptor, Property, Method, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ObjectPropertyDescriptor#ObjectPropertyDescriptor(DBPPropertySource,
   * ObjectPropertyGroupDescriptor, Property, Method, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ObjectPropertyDescriptor.<init>(DBPPropertySource, ObjectPropertyGroupDescriptor, Property, Method, String)"
  })
  public void testNewObjectPropertyDescriptor_thenThrowIllegalArgumentException() {
    // Arrange
    PropertySourceCustom source = new PropertySourceCustom();

    ObjectPropertyGroupDescriptor parent = mock(ObjectPropertyGroupDescriptor.class);
    org.mockito.Mockito.<Class<?>>when(parent.getDeclaringClass())
        .thenThrow(new IllegalArgumentException());

    Property propInfo = mock(Property.class);
    when(propInfo.order()).thenReturn(1);
    when(propInfo.id()).thenReturn("42");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new ObjectPropertyDescriptor(source, parent, propInfo, null, "en"));
    verify(propInfo).id();
    verify(propInfo).order();
    verify(parent).getDeclaringClass();
  }
}
