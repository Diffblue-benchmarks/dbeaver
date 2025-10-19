package org.jkiss.dbeaver.model.data.hints.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.EnumSet;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.data.DBDResultSetModel;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintStyle;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintType;
import org.jkiss.dbeaver.model.data.hints.standard.AttributeStatusHintProvider.ValueHintReadOnly;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AttributeStatusHintProviderDiffblueTest {
  /**
   * Test {@link AttributeStatusHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}.
   *
   * <ul>
   *   <li>Then first element return {@link ValueHintReadOnly}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeStatusHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeStatusHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenFirstElementReturnValueHintReadOnly() {
    // Arrange
    AttributeStatusHintProvider attributeStatusHintProvider = new AttributeStatusHintProvider();

    DBDResultSetModel model = mock(DBDResultSetModel.class);
    when(model.getReadOnlyStatus(Mockito.<DBPDataSourceContainer>any()))
        .thenReturn("Read Only Status");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeStatusHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(attribute).getDataSource();
    verify(model).getReadOnlyStatus(isA(DBPDataSourceContainer.class));
    DBDValueHint dbdValueHint = actualAttributeHints[0];
    assertTrue(dbdValueHint instanceof ValueHintReadOnly);
    assertEquals("Read-only: Read Only Status", dbdValueHint.getHintText());
    assertNull(dbdValueHint.getHintDescription());
    assertNull(dbdValueHint.getHintIcon());
    assertEquals(1, dbdValueHint.getHintOptions());
    assertEquals(1, actualAttributeHints.length);
    assertEquals(HintStyle.NORMAL, dbdValueHint.getHintStyle());
    assertEquals(HintType.STRING, dbdValueHint.getHintType());
  }

  /**
   * Test ValueHintReadOnly getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ValueHintReadOnly#ValueHintReadOnly(String)}
   *   <li>{@link ValueHintReadOnly#getHintOptions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ValueHintReadOnly.<init>(String)",
    "int ValueHintReadOnly.getHintOptions()"
  })
  public void testValueHintReadOnlyGettersAndSetters() {
    // Arrange and Act
    ValueHintReadOnly actualValueHintReadOnly = new ValueHintReadOnly("Text");
    int actualHintOptions = actualValueHintReadOnly.getHintOptions();

    // Assert
    assertEquals("Text", actualValueHintReadOnly.getHintText());
    assertNull(actualValueHintReadOnly.getHintDescription());
    assertNull(actualValueHintReadOnly.getHintIcon());
    assertEquals(1, actualHintOptions);
  }
}
