package com.tr1nks.model.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PdfFromHtmlCreator {
    private static final Pattern CSS_LINK_TAG_PATTERN = Pattern.compile("(?i)<link([^>]+)/>");
    private static final Pattern CSS_LINK_TAG_REMOVE_FRONT_PATTERN = Pattern.compile("<link.*href\\s*=\\s*[\"']\\s*");
    private static final Pattern CSS_LINK_TAG_REMOVE_TAIL_PATTERN = Pattern.compile("\\s*[\"']\\s*.*");

    public HtmlCssForPdfData loadHtmlCssData(String filename) {
        String html = loadText(filename);
        List<CssFile> css = new ArrayList<>();
        System.out.println("HTML is null=" + (html==null));
        System.out.println("CSS FILENAMES=" + getCssFileNames(html));
        for (String str : loadText(getCssFileNames(html))) {
            css.add(XMLWorkerHelper.getCSS(new ByteArrayInputStream(str.getBytes())));
        }
        return new HtmlCssForPdfData(html, css);
    }

    public byte[] create(HtmlCssForPdfData forPdfData, Map<Pattern, String> replaceMap) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            //CSS
            CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
            for (CssFile css : forPdfData.getCss()) {
                cssResolver.addCss(css);
            }
            // HTML
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            htmlContext.setImageProvider(new Base64ImageProviderForPDF());

            // Pipelines
            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

            // XML Worker
            XMLWorker worker = new XMLWorker(css, true);
            XMLParser p = new XMLParser(worker);
            if (null != replaceMap) {
                String temp = replaceWithMap(forPdfData.getHtml(), replaceMap);
                p.parse(new ByteArrayInputStream(temp.getBytes()));
            } else {
                p.parse(new ByteArrayInputStream(forPdfData.getHtml().getBytes()));
            }
            document.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getCssFileNames(String str) {
        Matcher matcher = CSS_LINK_TAG_PATTERN.matcher(str);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            String tmp1 = matcher.group();
            list.add(CSS_LINK_TAG_REMOVE_TAIL_PATTERN.matcher(CSS_LINK_TAG_REMOVE_FRONT_PATTERN.matcher(tmp1).replaceAll("")).replaceAll(""));
        }
        return list;
    }

    private String replaceWithMap(String string, Map<Pattern, String> replaceMap) {
        Matcher matcher;
        for (Pattern pattern : replaceMap.keySet()) {
            matcher = pattern.matcher(string);
            string = matcher.replaceAll(replaceMap.get(pattern));
        }
        return string;
    }


    private List<String> loadText(List<String> fileNames) {
        ArrayList<String> arr = new ArrayList<>();
        for (String filemane : fileNames) {
            arr.add(loadText(filemane));
        }
        return arr;
    }

    private String loadText(String filename) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(FileGenerator.PDF_RESOURCE_LOCATION + filename),"utf-8"))) {
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                builder.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString().replaceAll("\\s{2,}", " ");
    }
}

class HtmlCssForPdfData {
    private String html;
    private List<CssFile> css;

    public HtmlCssForPdfData(String html, List<CssFile> css) {
        this.html = html;
        this.css = css;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<CssFile> getCss() {
        return css;
    }

    public void setCss(List<CssFile> css) {
        this.css = css;
    }
}
