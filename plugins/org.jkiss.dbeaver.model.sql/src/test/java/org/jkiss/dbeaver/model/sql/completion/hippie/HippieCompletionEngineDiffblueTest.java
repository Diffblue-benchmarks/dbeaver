package org.jkiss.dbeaver.model.sql.completion.hippie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HippieCompletionEngineDiffblueTest {
  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return size is five.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_thenReturnSizeIsFive() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    List<String> actualCompletionsForward =
        hippieCompletionEngine.getCompletionsForward(
            new Document("Not all who wander are lost"), "", 1, true);

    // Assert
    assertEquals(5, actualCompletionsForward.size());
    assertEquals("all", actualCompletionsForward.get(0));
    assertEquals("are", actualCompletionsForward.get(3));
    assertEquals("lost", actualCompletionsForward.get(4));
    assertEquals("wander", actualCompletionsForward.get(2));
    assertEquals("who", actualCompletionsForward.get(1));
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return size is twelve.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_thenReturnSizeIsTwelve() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    List<String> actualCompletionsForward =
        hippieCompletionEngine.getCompletionsForward(
            new Document("[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+"), "", 1, true);

    // Assert
    assertEquals(12, actualCompletionsForward.size());
    assertEquals("L", actualCompletionsForward.get(0));
    assertEquals("Mn", actualCompletionsForward.get(2));
    assertEquals("Nd", actualCompletionsForward.get(6));
    assertEquals("Nl", actualCompletionsForward.get(8));
    assertEquals("Pc", actualCompletionsForward.get(4));
    assertEquals("Sc", actualCompletionsForward.get(10));
    assertEquals("p", actualCompletionsForward.get(1));
    assertEquals("p", actualCompletionsForward.get(11));
    assertEquals("p", actualCompletionsForward.get(3));
    assertEquals("p", actualCompletionsForward.get(5));
    assertEquals("p", actualCompletionsForward.get(7));
    assertEquals("p", actualCompletionsForward.get(9));
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code instance}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenDocumentWithInitialContentIsInstance() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(
                new Document(" instance"), Plugin.PLUGIN_PREFERENCE_SCOPE, 1, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code Not all who wander
   *       are lost}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenDocumentWithInitialContentIsNotAllWhoWanderAreLost() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(
                new Document("Not all who wander are lost"),
                Plugin.PLUGIN_PREFERENCE_SCOPE,
                1,
                true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenEight_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 8, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenEmptyString_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(hippieCompletionEngine.getCompletionsForward(new Document(), "", 1, true).isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return size is five.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenFalse_thenReturnSizeIsFive() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    List<String> actualCompletionsForward =
        hippieCompletionEngine.getCompletionsForward(
            new Document("Not all who wander are lost"), "", 1, false);

    // Assert
    assertEquals(5, actualCompletionsForward.size());
    assertEquals("all", actualCompletionsForward.get(0));
    assertEquals("are", actualCompletionsForward.get(3));
    assertEquals("lost", actualCompletionsForward.get(4));
    assertEquals("wander", actualCompletionsForward.get(2));
    assertEquals("who", actualCompletionsForward.get(1));
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenFindReplaceDocumentAdapterWithDocumentIsDocument() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(
                document, new FindReplaceDocumentAdapter(new Document()), 1, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document(String)}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenFindReplaceDocumentAdapterWithDocumentIsDocument2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(
                document,
                new FindReplaceDocumentAdapter(new Document("Not all who wander are lost")),
                1,
                true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link Integer#MIN_VALUE}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenMin_value_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(
                new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, Integer.MIN_VALUE, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenMinusOne_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, -1, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenPLPMnPPcPNdPNlPSc_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(
                new Document(), "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+", 1, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link Plugin#PLUGIN_PREFERENCE_SCOPE}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenPlugin_preference_scope_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 1, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code \Q}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenQ_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine.getCompletionsForward(new Document(), "\\Q", 1, true).isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenZero_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 0, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsForward(IDocument, CharSequence, int,
   * boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsForward(IDocument,
   * CharSequence, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsForward(IDocument, CharSequence, int, boolean)"
  })
  public void testGetCompletionsForward_whenZero_thenReturnEmpty2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsForward(new Document("Not all who wander are lost"), "", 0, true)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(
                new Document("Not all who wander are lost"), Plugin.PLUGIN_PREFERENCE_SCOPE, 3)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenEmptyString_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(hippieCompletionEngine.getCompletionsBackwards(new Document(), "", 3).isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenEmptyString_thenReturnSizeIsOne() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    List<String> actualCompletionsBackwards =
        hippieCompletionEngine.getCompletionsBackwards(
            new Document("Not all who wander are lost"), "", 3);

    // Assert
    assertEquals(1, actualCompletionsBackwards.size());
    assertEquals("Not", actualCompletionsBackwards.get(0));
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenFindReplaceDocumentAdapterWithDocumentIsDocument() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(document, new FindReplaceDocumentAdapter(new Document()), 3)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document(String)}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenFindReplaceDocumentAdapterWithDocumentIsDocument2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(
                document,
                new FindReplaceDocumentAdapter(new Document("Not all who wander are lost")),
                3)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenOne_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 1)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenPLPMnPPcPNdPNlPSc_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(
                new Document(), "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+", 3)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenPLPMnPPcPNdPNlPSc_thenReturnEmpty2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(
                new Document("Not all who wander are lost"),
                "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+",
                3)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link Plugin#PLUGIN_PREFERENCE_SCOPE}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenPlugin_preference_scope_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(
        hippieCompletionEngine
            .getCompletionsBackwards(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 3)
            .isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getCompletionsBackwards(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@code \Q}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getCompletionsBackwards(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List HippieCompletionEngine.getCompletionsBackwards(IDocument, CharSequence, int)"
  })
  public void testGetCompletionsBackwards_whenQ_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(hippieCompletionEngine.getCompletionsBackwards(new Document(), "\\Q", 3).isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getPrefixString(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return {@code N}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getPrefixString(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String HippieCompletionEngine.getPrefixString(IDocument, int)"})
  public void testGetPrefixString_thenReturnN() throws BadLocationException {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertEquals(
        "N",
        hippieCompletionEngine.getPrefixString(new Document("Not all who wander are lost"), 1));
  }

  /**
   * Test {@link HippieCompletionEngine#getPrefixString(IDocument, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getPrefixString(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String HippieCompletionEngine.getPrefixString(IDocument, int)"})
  public void testGetPrefixString_whenZero_thenReturnNull() throws BadLocationException {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertNull(hippieCompletionEngine.getPrefixString(new Document(), 0));
  }

  /**
   * Test {@link HippieCompletionEngine#makeUnique(List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#makeUnique(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List HippieCompletionEngine.makeUnique(List)"})
  public void testMakeUnique_given42_whenArrayListAdd42_thenReturnArrayList() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    ArrayList<String> suggestions = new ArrayList<>();
    suggestions.add("42");
    suggestions.add("foo");

    // Act
    List<String> actualMakeUniqueResult = hippieCompletionEngine.makeUnique(suggestions);

    // Assert
    assertEquals(suggestions, actualMakeUniqueResult);
  }

  /**
   * Test {@link HippieCompletionEngine#makeUnique(List)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#makeUnique(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List HippieCompletionEngine.makeUnique(List)"})
  public void testMakeUnique_givenFoo_whenArrayListAddFoo_thenReturnSizeIsOne() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    ArrayList<String> suggestions = new ArrayList<>();
    suggestions.add("foo");
    suggestions.add("foo");

    // Act
    List<String> actualMakeUniqueResult = hippieCompletionEngine.makeUnique(suggestions);

    // Assert
    assertEquals(1, actualMakeUniqueResult.size());
    assertEquals("foo", actualMakeUniqueResult.get(0));
  }

  /**
   * Test {@link HippieCompletionEngine#makeUnique(List)}.
   *
   * <ul>
   *   <li>Given {@code Suggestions}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code Suggestions}.
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#makeUnique(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List HippieCompletionEngine.makeUnique(List)"})
  public void testMakeUnique_givenSuggestions_whenArrayListAddSuggestions_thenReturnArrayList() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    ArrayList<String> suggestions = new ArrayList<>();
    suggestions.add("Suggestions");

    // Act
    List<String> actualMakeUniqueResult = hippieCompletionEngine.makeUnique(suggestions);

    // Assert
    assertEquals(suggestions, actualMakeUniqueResult);
  }

  /**
   * Test {@link HippieCompletionEngine#makeUnique(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#makeUnique(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List HippieCompletionEngine.makeUnique(List)"})
  public void testMakeUnique_whenArrayList_thenReturnEmpty() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertTrue(hippieCompletionEngine.makeUnique(new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>Then return next is {@code all}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_thenReturnNextIsAll() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    Iterator<String> actualForwardIterator =
        hippieCompletionEngine.getForwardIterator(
            new Document("Not all who wander are lost"), "", 1, true);

    // Assert
    String actualNextResult = actualForwardIterator.next();
    String actualNextResult2 = actualForwardIterator.next();
    String actualNextResult3 = actualForwardIterator.next();
    String actualNextResult4 = actualForwardIterator.next();
    String actualNextResult5 = actualForwardIterator.next();
    assertEquals("all", actualNextResult);
    assertEquals("are", actualNextResult4);
    assertEquals("lost", actualNextResult5);
    assertEquals("wander", actualNextResult3);
    assertEquals("who", actualNextResult2);
    assertFalse(actualForwardIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>Then return next is {@code L}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_thenReturnNextIsL() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    Iterator<String> actualForwardIterator =
        hippieCompletionEngine.getForwardIterator(
            new Document("[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+"), "", 1, true);

    // Assert
    String actualNextResult = actualForwardIterator.next();
    String actualNextResult2 = actualForwardIterator.next();
    String actualNextResult3 = actualForwardIterator.next();
    String actualNextResult4 = actualForwardIterator.next();
    String actualNextResult5 = actualForwardIterator.next();
    String actualNextResult6 = actualForwardIterator.next();
    String actualNextResult7 = actualForwardIterator.next();
    String actualNextResult8 = actualForwardIterator.next();
    String actualNextResult9 = actualForwardIterator.next();
    String actualNextResult10 = actualForwardIterator.next();
    assertEquals("L", actualNextResult);
    assertEquals("Mn", actualNextResult3);
    assertEquals("Nd", actualNextResult7);
    assertEquals("Nl", actualNextResult9);
    assertEquals("Pc", actualNextResult5);
    assertEquals("p", actualNextResult2);
    assertEquals("p", actualNextResult4);
    assertEquals("p", actualNextResult6);
    assertEquals("p", actualNextResult8);
    assertEquals("p", actualNextResult10);
    assertTrue(actualForwardIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code instance}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenDocumentWithInitialContentIsInstance() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(new Document(" instance"), Plugin.PLUGIN_PREFERENCE_SCOPE, 1, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code Not all who wander
   *       are lost}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenDocumentWithInitialContentIsNotAllWhoWanderAreLost() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(
                new Document("Not all who wander are lost"),
                Plugin.PLUGIN_PREFERENCE_SCOPE,
                1,
                true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with {@code Initial Content}.
   *   <li>Then return next is {@code Content}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenDocumentWithInitialContent_thenReturnNextIsContent() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    Iterator<String> actualForwardIterator =
        hippieCompletionEngine.getForwardIterator(new Document("Initial Content"), "", 1, true);

    // Assert
    assertEquals("Content", actualForwardIterator.next());
    assertFalse(actualForwardIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenEight_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 8, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenEmptyString_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(hippieCompletionEngine.getForwardIterator(new Document(), "", 1, true).hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenFindReplaceDocumentAdapterWithDocumentIsDocument() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(document, new FindReplaceDocumentAdapter(new Document()), 1, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document(String)}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenFindReplaceDocumentAdapterWithDocumentIsDocument2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(
                document,
                new FindReplaceDocumentAdapter(new Document("Not all who wander are lost")),
                1,
                true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link Integer#MIN_VALUE}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenMin_value_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(
                new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, Integer.MIN_VALUE, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenMinusOne_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, -1, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenPLPMnPPcPNdPNlPSc_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(
                new Document(), "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+", 1, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@link Plugin#PLUGIN_PREFERENCE_SCOPE}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenPlugin_preference_scope_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 1, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When {@code \Q}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenQ_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine.getForwardIterator(new Document(), "\\Q", 1, true).hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenZero_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 0, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence, int, boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getForwardIterator(IDocument, CharSequence,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getForwardIterator(IDocument, CharSequence, int, boolean)"
  })
  public void testGetForwardIterator_whenZero_thenReturnNotHasNext2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getForwardIterator(new Document("Not all who wander are lost"), "", 0, true)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>Then return next is {@code and}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_thenReturnNextIsAnd() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    Iterator<String> actualBackwardIterator =
        hippieCompletionEngine.getBackwardIterator(
            new Document(
                "Position did not change in loop (this would lead to recursion -- and should never happen)."),
            "",
            92);

    // Assert
    String actualNextResult = actualBackwardIterator.next();
    String actualNextResult2 = actualBackwardIterator.next();
    String actualNextResult3 = actualBackwardIterator.next();
    String actualNextResult4 = actualBackwardIterator.next();
    String actualNextResult5 = actualBackwardIterator.next();
    String actualNextResult6 = actualBackwardIterator.next();
    String actualNextResult7 = actualBackwardIterator.next();
    String actualNextResult8 = actualBackwardIterator.next();
    String actualNextResult9 = actualBackwardIterator.next();
    String actualNextResult10 = actualBackwardIterator.next();
    assertEquals("and", actualNextResult4);
    assertEquals("happen", actualNextResult);
    assertEquals("lead", actualNextResult7);
    assertEquals("loop", actualNextResult10);
    assertEquals("never", actualNextResult2);
    assertEquals("recursion", actualNextResult5);
    assertEquals("should", actualNextResult3);
    assertEquals("this", actualNextResult9);
    assertEquals("to", actualNextResult6);
    assertEquals("would", actualNextResult8);
    assertTrue(actualBackwardIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenEight_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(
                new Document("Not all who wander are lost"), Plugin.PLUGIN_PREFERENCE_SCOPE, 8)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return next is {@code Not}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenEmptyString_thenReturnNextIsNot() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act
    Iterator<String> actualBackwardIterator =
        hippieCompletionEngine.getBackwardIterator(
            new Document("Not all who wander are lost"), "", 92);

    // Assert
    String actualNextResult = actualBackwardIterator.next();
    String actualNextResult2 = actualBackwardIterator.next();
    String actualNextResult3 = actualBackwardIterator.next();
    String actualNextResult4 = actualBackwardIterator.next();
    String actualNextResult5 = actualBackwardIterator.next();
    String actualNextResult6 = actualBackwardIterator.next();
    assertEquals("Not", actualNextResult6);
    assertEquals("all", actualNextResult5);
    assertEquals("are", actualNextResult2);
    assertEquals("lost", actualNextResult);
    assertEquals("wander", actualNextResult3);
    assertEquals("who", actualNextResult4);
    assertFalse(actualBackwardIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenEmptyString_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(hippieCompletionEngine.getBackwardIterator(new Document(), "", 92).hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenFindReplaceDocumentAdapterWithDocumentIsDocument() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(document, new FindReplaceDocumentAdapter(new Document()), 92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document(String)}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenFindReplaceDocumentAdapterWithDocumentIsDocument2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(
                document,
                new FindReplaceDocumentAdapter(new Document("Not all who wander are lost")),
                92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link FindReplaceDocumentAdapter#FindReplaceDocumentAdapter(IDocument)} with
   *       document is {@link Document#Document(String)}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenFindReplaceDocumentAdapterWithDocumentIsDocument3() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document("Not all who wander are lost");

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(
                document,
                new FindReplaceDocumentAdapter(new Document("Not all who wander are lost")),
                92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenOne_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 1)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenPLPMnPPcPNdPNlPSc_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(new Document(), "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+", 92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenPLPMnPPcPNdPNlPSc_thenReturnNotHasNext2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(
                new Document("Not all who wander are lost"),
                "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+",
                92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link Plugin#PLUGIN_PREFERENCE_SCOPE}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenPlugin_preference_scope_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(new Document(), Plugin.PLUGIN_PREFERENCE_SCOPE, 92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@link Plugin#PLUGIN_PREFERENCE_SCOPE}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenPlugin_preference_scope_thenReturnNotHasNext2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(
        hippieCompletionEngine
            .getBackwardIterator(
                new Document("Not all who wander are lost"), Plugin.PLUGIN_PREFERENCE_SCOPE, 92)
            .hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getBackwardIterator(IDocument, CharSequence, int)}.
   *
   * <ul>
   *   <li>When {@code \Q}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getBackwardIterator(IDocument,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getBackwardIterator(IDocument, CharSequence, int)"
  })
  public void testGetBackwardIterator_whenQ_thenReturnNotHasNext() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();

    // Act and Assert
    assertFalse(hippieCompletionEngine.getBackwardIterator(new Document(), "\\Q", 92).hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    ArrayList<IDocument> otherDocuments = new ArrayList<>();
    otherDocuments.add(new Document("Not all who wander are lost"));

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, otherDocuments, Plugin.PLUGIN_PREFERENCE_SCOPE, 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document("Not all who wander are lost");

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, new ArrayList<>(), Plugin.PLUGIN_PREFERENCE_SCOPE, 8);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator3() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();
    ArrayList<IDocument> otherDocuments = new ArrayList<>();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, otherDocuments, new FindReplaceDocumentAdapter(new Document()), 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>Given {@link Document#Document()}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_givenDocument_whenArrayListAddDocument() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    ArrayList<IDocument> otherDocuments = new ArrayList<>();
    otherDocuments.add(new Document());

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, otherDocuments, Plugin.PLUGIN_PREFERENCE_SCOPE, 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>Given {@link Document#Document()}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_givenDocument_whenArrayListAddDocument2() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    ArrayList<IDocument> otherDocuments = new ArrayList<>();
    otherDocuments.add(new Document());
    otherDocuments.add(new Document());

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, otherDocuments, Plugin.PLUGIN_PREFERENCE_SCOPE, 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>Then return next is {@code all}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_thenReturnNextIsAll() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document("Not all who wander are lost");

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(document, new ArrayList<>(), "", 1);

    // Assert
    String actualNextResult = actualMultipleDocumentsIterator.next();
    String actualNextResult2 = actualMultipleDocumentsIterator.next();
    String actualNextResult3 = actualMultipleDocumentsIterator.next();
    String actualNextResult4 = actualMultipleDocumentsIterator.next();
    String actualNextResult5 = actualMultipleDocumentsIterator.next();
    String actualNextResult6 = actualMultipleDocumentsIterator.next();
    assertEquals("", actualNextResult6);
    assertEquals("all", actualNextResult);
    assertEquals("are", actualNextResult4);
    assertEquals("lost", actualNextResult5);
    assertEquals("wander", actualNextResult3);
    assertEquals("who", actualNextResult2);
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return next is empty string.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_whenEight_thenReturnNextIsEmptyString() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, new ArrayList<>(), Plugin.PLUGIN_PREFERENCE_SCOPE, 8);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return next is empty string.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_whenEmptyString_thenReturnNextIsEmptyString() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(document, new ArrayList<>(), "", 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>When ninety-two.
   *   <li>Then return next is empty string.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_whenNinetyTwo_thenReturnNextIsEmptyString() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, new ArrayList<>(), Plugin.PLUGIN_PREFERENCE_SCOPE, 92);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>When {@code [\p{L}\p{Mn}\p{Pc}\p{Nd}\p{Nl}\p{Sc}]+}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_whenPLPMnPPcPNdPNlPSc() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, new ArrayList<>(), "[\\p{L}\\p{Mn}\\p{Pc}\\p{Nd}\\p{Nl}\\p{Sc}]+", 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>When {@link Plugin#PLUGIN_PREFERENCE_SCOPE}.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_whenPlugin_preference_scope() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(
            document, new ArrayList<>(), Plugin.PLUGIN_PREFERENCE_SCOPE, 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }

  /**
   * Test {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument, List, CharSequence,
   * int)}.
   *
   * <ul>
   *   <li>When {@code \Q}.
   *   <li>Then return next is empty string.
   * </ul>
   *
   * <p>Method under test: {@link HippieCompletionEngine#getMultipleDocumentsIterator(IDocument,
   * List, CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Iterator HippieCompletionEngine.getMultipleDocumentsIterator(IDocument, List, CharSequence, int)"
  })
  public void testGetMultipleDocumentsIterator_whenQ_thenReturnNextIsEmptyString() {
    // Arrange
    HippieCompletionEngine hippieCompletionEngine = new HippieCompletionEngine();
    Document document = new Document();

    // Act
    Iterator<String> actualMultipleDocumentsIterator =
        hippieCompletionEngine.getMultipleDocumentsIterator(document, new ArrayList<>(), "\\Q", 1);

    // Assert
    assertEquals("", actualMultipleDocumentsIterator.next());
    assertFalse(actualMultipleDocumentsIterator.hasNext());
  }
}
