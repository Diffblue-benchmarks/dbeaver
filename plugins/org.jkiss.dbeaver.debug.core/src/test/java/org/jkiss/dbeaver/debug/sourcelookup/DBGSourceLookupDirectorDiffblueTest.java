package org.jkiss.dbeaver.debug.sourcelookup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBGSourceLookupDirectorDiffblueTest {
  /**
   * Test {@link DBGSourceLookupDirector#initializeParticipants()}.
   *
   * <p>Method under test: {@link DBGSourceLookupDirector#initializeParticipants()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGSourceLookupDirector.initializeParticipants()"})
  public void testInitializeParticipants() {
    // Arrange
    DBGSourceLookupDirector dbgSourceLookupDirector = new DBGSourceLookupDirector();

    // Act
    dbgSourceLookupDirector.initializeParticipants();

    // Assert
    ISourceLookupParticipant[] participants = dbgSourceLookupDirector.getParticipants();
    ISourceLookupParticipant iSourceLookupParticipant = participants[0];
    assertTrue(iSourceLookupParticipant instanceof DBGObjectLookupParticipant);
    assertEquals(1, participants.length);
    assertFalse(((DBGObjectLookupParticipant) iSourceLookupParticipant).isFindDuplicates());
  }

  /**
   * Test new {@link DBGSourceLookupDirector} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DBGSourceLookupDirector}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGSourceLookupDirector.<init>()"})
  public void testNewDBGSourceLookupDirector() {
    // Arrange and Act
    DBGSourceLookupDirector actualDbgSourceLookupDirector = new DBGSourceLookupDirector();

    // Assert
    assertNull(actualDbgSourceLookupDirector.getId());
    assertNull(actualDbgSourceLookupDirector.getLaunchConfiguration());
    assertNull(actualDbgSourceLookupDirector.getCurrentParticipant());
    assertFalse(actualDbgSourceLookupDirector.isFindDuplicates());
  }
}
