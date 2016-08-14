package org.example.sling;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GetPowerpointDocumentServletTest {
  
  @Test
  public void generatePowerpointDocument() {
    GetPowerpointDocumentServlet servlet = new GetPowerpointDocumentServlet();
    servlet.generatePowerPoint("doc-from-junit.pptx");
  }
  
}
