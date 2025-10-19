package org.jkiss.dbeaver.debug.sourcelookup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBGObjectLookupParticipantDiffblueTest {
  /**
   * Test {@link DBGObjectLookupParticipant#getSourceName(Object)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBGObjectLookupParticipant#getSourceName(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DBGObjectLookupParticipant.getSourceName(Object)"})
  public void testGetSourceName_whenObject_thenReturnObject() throws CoreException {
    // Arrange, Act and Assert
    assertEquals("Object", new DBGObjectLookupParticipant().getSourceName("Object"));
  }

  /**
   * Test {@link DBGObjectLookupParticipant#getSourceName(Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGObjectLookupParticipant#getSourceName(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DBGObjectLookupParticipant.getSourceName(Object)"})
  public void testGetSourceName_whenOne_thenReturnNull() throws CoreException {
    // Arrange, Act and Assert
    assertNull(new DBGObjectLookupParticipant().getSourceName(1));
  }

  /**
   * Test new {@link DBGObjectLookupParticipant} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * DBGObjectLookupParticipant}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGObjectLookupParticipant.<init>()"})
  public void testNewDBGObjectLookupParticipant() {
    // Arrange, Act and Assert
    assertFalse(new DBGObjectLookupParticipant().isFindDuplicates());
  }
}
