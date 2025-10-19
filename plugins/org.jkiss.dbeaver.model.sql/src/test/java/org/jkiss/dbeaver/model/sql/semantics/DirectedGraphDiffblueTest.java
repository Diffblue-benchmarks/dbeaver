package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import org.jkiss.dbeaver.model.sql.semantics.DirectedGraph.Edge;
import org.jkiss.dbeaver.model.sql.semantics.DirectedGraph.Node;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class DirectedGraphDiffblueTest {
  /**
   * Test {@link DirectedGraph#createNode(String, String)}.
   *
   * <p>Method under test: {@link DirectedGraph#createNode(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Node DirectedGraph.createNode(String, String)"})
  public void testCreateNode() {
    // Arrange and Act
    Node actualCreateNodeResult = new DirectedGraph().createNode("Label", "Color");

    // Assert
    assertEquals("Color", actualCreateNodeResult.color);
    assertEquals("Label", actualCreateNodeResult.label);
    assertEquals(0, actualCreateNodeResult.id);
  }

  /**
   * Test {@link DirectedGraph#createEdge(Node, Node, String, String)}.
   *
   * <p>Method under test: {@link DirectedGraph#createEdge(Node, Node, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Edge DirectedGraph.createEdge(Node, Node, String, String)"})
  public void testCreateEdge() {
    // Arrange
    DirectedGraph directedGraph = new DirectedGraph();
    Node from = new DirectedGraph().new Node(1, "foo", "foo");
    Node resultTo = new DirectedGraph().new Node(1, "foo", "foo");

    // Act
    Edge actualCreateEdgeResult = directedGraph.createEdge(from, resultTo, "Label", "Color");

    // Assert
    assertEquals("Color", actualCreateEdgeResult.color);
    assertEquals("Label", actualCreateEdgeResult.label);
    Node node = actualCreateEdgeResult.from;
    assertEquals("foo", node.color);
    Node node2 = actualCreateEdgeResult.to;
    assertEquals("foo", node2.color);
    assertEquals("foo", node.label);
    assertEquals("foo", node2.label);
    assertEquals(1, node.id);
    assertEquals(1, node2.id);
  }

  /**
   * Test Edge {@link Edge#Edge(DirectedGraph, Node, Node, String, String)}.
   *
   * <p>Method under test: {@link Edge#Edge(DirectedGraph, Node, Node, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Edge.<init>(DirectedGraph, Node, Node, String, String)"})
  public void testEdgeNewEdge() {
    // Arrange
    DirectedGraph directedGraph = new DirectedGraph();
    Node node = new DirectedGraph().new Node(1, "foo", "foo");
    Node node2 = new DirectedGraph().new Node(1, "foo", "foo");

    // Act
    Edge actualEdge = directedGraph.new Edge(node, node2, "foo", "foo");

    // Assert
    assertEquals("foo", actualEdge.color);
    assertEquals("foo", actualEdge.label);
    Node node3 = actualEdge.from;
    assertEquals("foo", node3.color);
    Node node4 = actualEdge.to;
    assertEquals("foo", node4.color);
    assertEquals("foo", node3.label);
    assertEquals("foo", node4.label);
    assertEquals(1, node3.id);
    assertEquals(1, node4.id);
  }

  /**
   * Test Node {@link Node#Node(DirectedGraph, int, String, String)}.
   *
   * <p>Method under test: {@link Node#Node(DirectedGraph, int, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Node.<init>(DirectedGraph, int, String, String)"})
  public void testNodeNewNode() {
    // Arrange and Act
    Node actualNode = new DirectedGraph().new Node(1, "foo", "foo");

    // Assert
    assertEquals("foo", actualNode.color);
    assertEquals("foo", actualNode.label);
    assertEquals(1, actualNode.id);
  }

  /**
   * Test {@link DirectedGraph#saveToFile(String)}.
   *
   * <p>Method under test: {@link DirectedGraph#saveToFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DirectedGraph.saveToFile(String)"})
  public void testSaveToFile() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(
              () ->
                  Files.newBufferedWriter(
                      Mockito.<Path>any(), Mockito.<Charset>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));

      // Act
      new DirectedGraph().saveToFile("foo.txt");

      // Assert
      mockFiles.verify(
          () ->
              Files.newBufferedWriter(
                  Mockito.<Path>any(), Mockito.<Charset>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link DirectedGraph#saveToFile(String)}.
   *
   * <p>Method under test: {@link DirectedGraph#saveToFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DirectedGraph.saveToFile(String)"})
  public void testSaveToFile2() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(
              () ->
                  Files.newBufferedWriter(
                      Mockito.<Path>any(), Mockito.<Charset>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new PipedWriter(), 1));

      // Act
      new DirectedGraph().saveToFile("foo.txt");

      // Assert
      mockFiles.verify(
          () ->
              Files.newBufferedWriter(
                  Mockito.<Path>any(), Mockito.<Charset>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link DirectedGraph#saveToFile(String)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#newBufferedWriter(Path, Charset, OpenOption[])} throw
   *       {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link DirectedGraph#saveToFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DirectedGraph.saveToFile(String)"})
  public void testSaveToFile_givenFilesNewBufferedWriterThrowIOException() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(
              () ->
                  Files.newBufferedWriter(
                      Mockito.<Path>any(), Mockito.<Charset>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException());

      // Act
      new DirectedGraph().saveToFile("foo.txt");

      // Assert
      mockFiles.verify(
          () ->
              Files.newBufferedWriter(
                  Mockito.<Path>any(), Mockito.<Charset>any(), isA(OpenOption[].class)));
    }
  }
}
