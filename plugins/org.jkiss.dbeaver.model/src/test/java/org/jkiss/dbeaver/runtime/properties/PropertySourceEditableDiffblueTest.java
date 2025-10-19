package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.edit.DBECommandContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.edit.TestCommandContext;
import org.jkiss.dbeaver.model.meta.IPropertyValueTransformer;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertySourceEditableDiffblueTest {
  @InjectMocks private PropertySourceEditable propertySourceEditable;

  /**
   * Test {@link PropertySourceEditable#PropertySourceEditable(Object, Object)}.
   *
   * <p>Method under test: {@link PropertySourceEditable#PropertySourceEditable(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceEditable.<init>(Object, Object)"})
  public void testNewPropertySourceEditable() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    PropertySourceEditable actualPropertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, object);

    // Assert
    assertNull(actualPropertySourceEditable.getCommandContext());
    assertEquals(0, actualPropertySourceEditable.getProperties().length);
    assertTrue(actualPropertySourceEditable.getChangedPropertiesValues().isEmpty());
    assertTrue(actualPropertySourceEditable.getEnableFilters());
    assertTrue(actualPropertySourceEditable.isEmpty());
    assertTrue(actualPropertySourceEditable.isEnableFilters());
    assertSame(object, actualPropertySourceEditable.getEditableValue());
    assertSame(object, actualPropertySourceEditable.getSourceObject());
  }

  /**
   * Test {@link PropertySourceEditable#PropertySourceEditable(DBECommandContext, Object, Object)}.
   *
   * <p>Method under test: {@link PropertySourceEditable#PropertySourceEditable(DBECommandContext,
   * Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceEditable.<init>(DBECommandContext, Object, Object)"})
  public void testNewPropertySourceEditable2() {
    // Arrange
    TestCommandContext commandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    Object object = DBPEvent.RENAME;

    // Act
    PropertySourceEditable actualPropertySourceEditable =
        new PropertySourceEditable(commandContext, DBPEvent.RENAME, object);

    // Assert
    assertEquals(0, actualPropertySourceEditable.getProperties().length);
    assertTrue(actualPropertySourceEditable.getChangedPropertiesValues().isEmpty());
    assertTrue(actualPropertySourceEditable.getEnableFilters());
    assertTrue(actualPropertySourceEditable.isEmpty());
    assertTrue(actualPropertySourceEditable.isEnableFilters());
    assertSame(commandContext, actualPropertySourceEditable.getCommandContext());
    assertSame(object, actualPropertySourceEditable.getEditableValue());
    assertSame(object, actualPropertySourceEditable.getSourceObject());
  }

  /**
   * Test {@link PropertySourceEditable#isEditable(Object)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceEditable#isEditable(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceEditable.isEditable(Object)"})
  public void testIsEditable_thenReturnFalse() {
    // Arrange
    TestCommandContext commandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(commandContext, DBPEvent.RENAME, null);

    // Act and Assert
    assertFalse(propertySourceEditable.isEditable(DBPEvent.RENAME));
  }

  /**
   * Test {@link PropertySourceEditable#isEditable(Object)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceEditable#isEditable(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceEditable.isEditable(Object)"})
  public void testIsEditable_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME).isEditable(DBPEvent.RENAME));
  }

  /**
   * Test {@link PropertySourceEditable#getCommandContext()}.
   *
   * <p>Method under test: {@link PropertySourceEditable#getCommandContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBECommandContext PropertySourceEditable.getCommandContext()"})
  public void testGetCommandContext() {
    // Arrange, Act and Assert
    assertNull(new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME).getCommandContext());
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getValueTransformer()).thenReturn(null);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(DBPEvent.RENAME);

    // Act
    propertySourceEditable.setPropertyValue(monitor, DBPEvent.RENAME, prop, DBPEvent.RENAME);

    // Assert that nothing has changed
    verify(prop).getValueTransformer();
    verify(prop, atLeast(1)).readValue(isA(Object.class), isA(DBRProgressMonitor.class), eq(true));
    assertTrue(propertySourceEditable.getChangedPropertiesValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue2()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn(null);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.isNameProperty()).thenReturn(true);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(DBPEvent.RENAME);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);
    doNothing().when(prop).writeValue(Mockito.<Object>any(), Mockito.<Object>any());

    // Act
    propertySourceEditable.setPropertyValue(monitor, DBPEvent.RENAME, prop, DBPEvent.RENAME);

    // Assert
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop).isNameProperty();
    verify(prop, atLeast(1)).getValueTransformer();
    verify(prop, atLeast(1)).readValue(isA(Object.class), isA(DBRProgressMonitor.class), eq(true));
    verify(prop).writeValue(isA(Object.class), isNull());
    assertEquals(1, propertySourceEditable.getChangedPropertiesValues().size());
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue3()
      throws IllegalArgumentException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenThrow(new IllegalArgumentException());

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            propertySourceEditable.setPropertyValue(
                monitor, DBPEvent.RENAME, prop, DBPEvent.RENAME));
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop, atLeast(1)).getValueTransformer();
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue4()
      throws IllegalArgumentException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn(null);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.supportsPreview()).thenThrow(new IllegalArgumentException());
    when(prop.getId()).thenThrow(new IllegalArgumentException());
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            propertySourceEditable.setPropertyValue(null, DBPEvent.RENAME, prop, DBPEvent.RENAME));
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop, atLeast(1)).getValueTransformer();
    verify(prop).supportsPreview();
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue5()
      throws IllegalArgumentException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn(null);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getId()).thenReturn("42");
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean()))
        .thenThrow(new IllegalArgumentException());
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act
    propertySourceEditable.setPropertyValue(null, DBPEvent.RENAME, prop, DBPEvent.RENAME);

    // Assert that nothing has changed
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop, atLeast(1)).getId();
    verify(prop, atLeast(1)).isLazy(isA(Object.class), eq(true));
    verify(prop, atLeast(1)).getValueTransformer();
    assertTrue(propertySourceEditable.getChangedPropertiesValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue6()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);
    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("42");

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getPossibleValues(Mockito.<Object>any())).thenThrow(new IllegalArgumentException());
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(DBPEvent.RENAME);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            propertySourceEditable.setPropertyValue(
                null, dbsDocumentConstraint, prop, DBPEvent.RENAME));
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).getPossibleValues(isA(Object.class));
    verify(prop, atLeast(1)).getValueTransformer();
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop).supportsPreview();
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue7()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);
    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("42");

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    Mockito.<Class<?>>when(prop.getDataType()).thenThrow(new IllegalArgumentException());
    when(prop.getPossibleValues(Mockito.<Object>any())).thenReturn(new Object[] {DBPEvent.RENAME});
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(DBPEvent.RENAME);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            propertySourceEditable.setPropertyValue(
                null, dbsDocumentConstraint, prop, DBPEvent.RENAME));
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).getDataType();
    verify(prop).getPossibleValues(isA(Object.class));
    verify(prop, atLeast(1)).getValueTransformer();
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop).supportsPreview();
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue8()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("42");

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(prop.getDataType()).thenReturn(forNameResult);
    when(prop.getPossibleValues(Mockito.<Object>any())).thenReturn(new Object[] {});
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(null);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act
    propertySourceEditable.setPropertyValue(null, dbsDocumentConstraint, prop, DBPEvent.RENAME);

    // Assert that nothing has changed
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop, atLeast(1)).isLazy(isA(Object.class), eq(true));
    verify(prop).getDataType();
    verify(prop).getPossibleValues(isA(Object.class));
    verify(prop, atLeast(1)).getValueTransformer();
    verify(prop, atLeast(1)).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop, atLeast(1)).supportsPreview();
    assertTrue(propertySourceEditable.getChangedPropertiesValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code editableValue}, {@code prop},
   * {@code newValue}.
   *
   * <ul>
   *   <li>Then calls {@link LocalNativeClientLocation#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceEditable#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceEditable.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorEditableValuePropNewValue_thenCallsGetName()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertySourceEditable propertySourceEditable =
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME);
    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);

    IPropertyValueTransformer iPropertyValueTransformer = mock(IPropertyValueTransformer.class);
    when(iPropertyValueTransformer.transform(Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("42");

    LocalNativeClientLocation localNativeClientLocation = mock(LocalNativeClientLocation.class);
    when(localNativeClientLocation.getName()).thenThrow(new IllegalArgumentException());

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getPossibleValues(Mockito.<Object>any()))
        .thenReturn(new Object[] {localNativeClientLocation});
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(DBPEvent.RENAME);
    when(prop.getValueTransformer()).thenReturn(iPropertyValueTransformer);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            propertySourceEditable.setPropertyValue(
                null, dbsDocumentConstraint, prop, DBPEvent.RENAME));
    verify(localNativeClientLocation).getName();
    verify(iPropertyValueTransformer).transform(isA(Object.class), isA(Object.class));
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).getPossibleValues(isA(Object.class));
    verify(prop, atLeast(1)).getValueTransformer();
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop).supportsPreview();
  }

  /**
   * Test {@link PropertySourceEditable#isPropertyResettable(Object, ObjectPropertyDescriptor)} with
   * {@code object}, {@code prop}.
   *
   * <p>Method under test: {@link PropertySourceEditable#isPropertyResettable(Object,
   * ObjectPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PropertySourceEditable.isPropertyResettable(Object, ObjectPropertyDescriptor)"
  })
  public void testIsPropertyResettableWithObjectProp() {
    // Arrange, Act and Assert
    assertFalse(
        new PropertySourceEditable(DBPEvent.RENAME, DBPEvent.RENAME)
            .isPropertyResettable(DBPEvent.RENAME, null));
  }
}
