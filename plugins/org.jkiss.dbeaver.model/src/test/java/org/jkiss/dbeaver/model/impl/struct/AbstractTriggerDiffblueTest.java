package org.jkiss.dbeaver.model.impl.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.struct.AbstractTrigger.TriggerTimingListProvider;
import org.jkiss.dbeaver.model.impl.struct.AbstractTrigger.TriggerTypeListProvider;
import org.jkiss.dbeaver.model.struct.DBSActionTiming;
import org.jkiss.dbeaver.model.struct.rdb.DBSManipulationType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractTriggerDiffblueTest {
  /**
   * Test TriggerTimingListProvider {@link TriggerTimingListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link TriggerTimingListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TriggerTimingListProvider.allowCustomValue()"})
  public void testTriggerTimingListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new TriggerTimingListProvider().allowCustomValue());
  }

  /**
   * Test TriggerTimingListProvider {@link TriggerTimingListProvider#getPossibleValues(Object)}.
   *
   * <p>Method under test: {@link TriggerTimingListProvider#getPossibleValues(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] TriggerTimingListProvider.getPossibleValues(Object)"})
  public void testTriggerTimingListProviderGetPossibleValues() {
    // Arrange and Act
    Object[] actualPossibleValues =
        new TriggerTimingListProvider().getPossibleValues(DBPEvent.RENAME);

    // Assert
    Object object = actualPossibleValues[0];
    assertTrue(object instanceof DBSActionTiming);
    Object object2 = actualPossibleValues[1];
    assertTrue(object2 instanceof DBSActionTiming);
    assertEquals("AFTER", ((DBSActionTiming) object2).getName());
    assertEquals("BEFORE", ((DBSActionTiming) object).getName());
    assertEquals(2, actualPossibleValues.length);
  }

  /**
   * Test TriggerTypeListProvider {@link TriggerTypeListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link TriggerTypeListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TriggerTypeListProvider.allowCustomValue()"})
  public void testTriggerTypeListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new TriggerTypeListProvider().allowCustomValue());
  }

  /**
   * Test TriggerTypeListProvider {@link TriggerTypeListProvider#getPossibleValues(Object)}.
   *
   * <p>Method under test: {@link TriggerTypeListProvider#getPossibleValues(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] TriggerTypeListProvider.getPossibleValues(Object)"})
  public void testTriggerTypeListProviderGetPossibleValues() {
    // Arrange and Act
    Object[] actualPossibleValues =
        new TriggerTypeListProvider().getPossibleValues(DBPEvent.RENAME);

    // Assert
    Object object = actualPossibleValues[0];
    assertTrue(object instanceof DBSManipulationType);
    Object object2 = actualPossibleValues[1];
    assertTrue(object2 instanceof DBSManipulationType);
    Object object3 = actualPossibleValues[2];
    assertTrue(object3 instanceof DBSManipulationType);
    assertEquals("DELETE", ((DBSManipulationType) object3).getName());
    assertEquals("INSERT", ((DBSManipulationType) object).getName());
    assertEquals("UPDATE", ((DBSManipulationType) object2).getName());
    assertEquals(3, actualPossibleValues.length);
  }
}
