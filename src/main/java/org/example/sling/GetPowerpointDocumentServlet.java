package org.example.sling;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.poi.sl.usermodel.TableCell.BorderEdge;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

@SuppressWarnings("serial")
@Component()
@Service(Servlet.class)
@Properties({ @Property(name = "sling.servlet.paths", value = "/apps/test/poi"),
    @Property(name = "sling.servlet.extensions", value = "html"),
    @Property(name = "sling.servlet.methods", value = "GET") })
public class GetPowerpointDocumentServlet extends SlingSafeMethodsServlet {

  private String OUTPUT_DIR = "";
  @Override
  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
    generatePowerPoint("doc-from-sling.pptx");
  }

  public void generatePowerPoint(String filename) {
    XMLSlideShow ppt = new XMLSlideShow();

    // XSLFSlide#createSlide() with no arguments creates a blank slide
    XSLFSlide slide = ppt.createSlide();

    XSLFTable tbl = slide.createTable();
    tbl.setAnchor(new Rectangle(50, 50, 450, 300));

    int numColumns = 3;
    int numRows = 5;
    XSLFTableRow headerRow = tbl.addRow();
    headerRow.setHeight(50);
    // header
    for (int i = 0; i < numColumns; i++) {
      XSLFTableCell th = headerRow.addCell();
      XSLFTextParagraph p = th.addNewTextParagraph();
      p.setTextAlign(TextAlign.CENTER);
      XSLFTextRun r = p.addNewTextRun();
      r.setText("Header " + (i + 1));
      r.setBold(true);
      r.setFontColor(Color.white);
      th.setFillColor(new Color(79, 129, 189));
      th.setBorderWidth(BorderEdge.bottom, 2.0);
      th.setBorderColor(BorderEdge.bottom, Color.white);

      tbl.setColumnWidth(i, 150); // all columns are equally sized
    }

    // rows

    for (int rownum = 0; rownum < numRows; rownum++) {
      XSLFTableRow tr = tbl.addRow();
      tr.setHeight(50);
      // header
      for (int i = 0; i < numColumns; i++) {
        XSLFTableCell cell = tr.addCell();
        XSLFTextParagraph p = cell.addNewTextParagraph();
        XSLFTextRun r = p.addNewTextRun();

        r.setText("Cell " + (i + 1));
        if (rownum % 2 == 0)
          cell.setFillColor(new Color(208, 216, 232));
        else
          cell.setFillColor(new Color(233, 247, 244));

      }
    }

    FileOutputStream out;
    try {
      out = new FileOutputStream(OUTPUT_DIR + System.getProperty("file.separator") + filename);
      ppt.write(out);
      out.close();
      ppt.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }  
}
