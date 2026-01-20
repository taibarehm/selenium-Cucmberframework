package QA_Automation.Practice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DumpXPaths {
    public static void main(String[] args) throws Exception {
        String url = (args != null && args.length > 0) ? args[0]
                : "http://automationexercise.com";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.get(url);
            String script = "function getXPath(el) {"
                    + "    if (!el) return '';"
                    + "    var tag = el.tagName.toLowerCase();"
                    + "    // 1. Try unique ID"
                    + "    if (el.id) {"
                    + "        try {"
                    + "            if (document.getElementById(el.id) === el) {"
                    + "                return '//*[@id=\\'' + el.id.replace(/'/g, \"\\\\'\") + '\\']';"
                    + "            }"
                    + "        } catch (e) {}"
                    + "    }"
                    + "    // 2. Try unique name"
                    + "    if (el.name) {"
                    + "        var named = document.querySelectorAll(tag + '[name=\\'' + el.name.replace(/'/g, \"\\\\'\") + '\\']');"
                    + "        if (named.length === 1 && named[0] === el) {"
                    + "            return '//' + tag + '[@name=\\'' + el.name.replace(/'/g, \"\\\\'\") + '\\']';"
                    + "        }"
                    + "    }"
                    + "    // 3. Try unique class"
                    + "    if (el.className) {"
                    + "        var classed = document.querySelectorAll(tag + '[class=\\'' + el.className.replace(/'/g, \"\\\\'\") + '\\']');"
                    + "        if (classed.length === 1 && classed[0] === el) {"
                    + "            return '//' + tag + '[@class=\\'' + el.className.replace(/'/g, \"\\\\'\") + '\\']';"
                    + "        }"
                    + "    }"
                    + "    // 4. Short relative XPath (up to 3 levels)"
                    + "    var parts = [];"
                    + "    var current = el;"
                    + "    var depth = 0;"
                    + "    while (current && current.nodeType === 1 && depth < 3) {"
                    + "        var ctag = current.tagName.toLowerCase();"
                    + "        var sibling = current.previousElementSibling;"
                    + "        var idx = 1;"
                    + "        while (sibling) {"
                    + "            if (sibling.tagName.toLowerCase() === ctag) idx++;"
                    + "            sibling = sibling.previousElementSibling;"
                    + "        }"
                    + "        var part = ctag + (idx > 1 ? '[' + idx + ']' : '');"
                    + "        parts.unshift(part);"
                    + "        current = current.parentNode;"
                    + "        depth++;"
                    + "    }"
                    + "    return '//' + parts.join('/');"
                    + "}"
                    + ""
                    + "function validateXPath(el, xpath) {"
                    + "    try {"
                    + "        var result = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);"
                    + "        return result.singleNodeValue === el;"
                    + "    } catch (e) {"
                    + "        return false;"
                    + "    }"
                    + "}"
                    + ""
                    + "var info = {"
                    + "    title: document.title || '',"
                    + "    url: location.href,"
                    + "    ready: document.readyState,"
                    + "    totalElements: document.querySelectorAll('*').length"
                    + "};"
                    + ""
                    + "var tags = ['button', 'input', 'a', 'label', 'span', 'div', 'textarea', 'select'];"
                    + "var out = [];"
                    + ""
                    + "tags.forEach(function(t) {"
                    + "    document.querySelectorAll(t).forEach(function(el) {"
                    + "        try {"
                    + "            var x = getXPath(el);"
                    + "            var valid = validateXPath(el, x);"
                    + "            out.push({"
                    + "                tag: el.tagName.toLowerCase(),"
                    + "                id: el.id || '',"
                    + "                name: el.name || '',"
                    + "                classes: el.className || '',"
                    + "                text: (el.textContent || '').trim().slice(0, 120),"
                    + "                xpath: x,"
                    + "                validated: valid"
                    + "            });"
                    + "        } catch (e) {"
                    + "        }"
                    + "    });"
                    + "});"
                    + ""
                    + "var frames = [];"
                    + "var ifrs = document.querySelectorAll('iframe');"
                    + ""
                    + "ifrs.forEach(function(f) {"
                    + "    try {"
                    + "        var doc = f.contentDocument;"
                    + "        if (doc) {"
                    + "            frames.push({"
                    + "                src: f.src || '',"
                    + "                sameOrigin: true,"
                    + "                count: doc.querySelectorAll('*').length"
                    + "            });"
                    + "        } else {"
                    + "             // Handle case where contentDocument is null but same origin (unloaded/sandbox)"
                    + "             frames.push({ src: f.src || '', sameOrigin: true, count: 0 });"
                    + "        }"
                    + "    } catch (e) {"
                    + "        // Iframe is cross-origin"
                    + "        frames.push({"
                    + "            src: f.src || '',"
                    + "            sameOrigin: false"
                    + "        });"
                    + "    }"
                    + "});"
                    + ""
                    + "return {"
                    + "    info: info,"
                    + "    frames: frames,"
                    + "    elements: out"
                    + "};";

            Object raw = ((JavascriptExecutor) driver).executeScript(script);

            System.out.println("script:--------------------- \n" + script.replace("    ", "  "));

            if (raw instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) raw;
                @SuppressWarnings("unchecked")
                Map<String, Object> info = (Map<String, Object>) map.get("info");
                Object frames = map.get("frames");
                Object elements = map.get("elements");
                System.out.println("script result:--------------------- " + map.get("info"));
                System.out.println("----- Page Information -----");
                System.out.println("Page title: " + safe(info.get("title")));
                System.out.println("Page URL: " + safe(info.get("url")));
                System.out.println("Document readyState: " + safe(info.get("ready")));
                System.out.println("Total DOM elements: " + safe(info.get("totalElements")));

                if (frames instanceof List) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> fl = (List<Map<String, Object>>) frames;
                    System.out.println("Iframes found: " + fl.size());
                    for (Map<String, Object> f : fl) {
                        System.out.println("  frame src=" + safe(f.get("src")) + " sameOrigin="
                                + safe(f.get("sameOrigin")) + " count=" + safe(f.get("count")));
                    }
                }

                if (elements instanceof List) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> list = (List<Map<String, Object>>) elements;
                    System.out.println("Collected elements: " + list.size());

                    try (PrintWriter pw = new PrintWriter(new FileWriter("xpaths.csv"))) {
                        pw.println("tag,id,name,classes,text,xpath,validated");
                        for (Map<String, Object> row : list) {
                            String tag = safe(row.get("tag"));
                            String id = safe(row.get("id"));
                            String name = safe(row.get("name"));
                            String classes = safe(row.get("classes"));
                            String text = safe(row.get("text"));
                            String xpath = safe(row.get("xpath"));
                            boolean validated = Boolean.parseBoolean(safe(row.get("validated")));

                            System.out.println(tag + " | id=" + id + " | name=" + name + " | classes=" + classes);
                            System.out.println("  text='" + text + "'");
                            System.out.println("  xpath=" + xpath);
                            System.out.println("  validated=" + validated);
                            if (!validated) {
                                System.out
                                        .println("  WARNING: XPath failed validation - may not be unique or correct!");
                            }
                            System.out.println("----------------------------");

                            pw.println(csvEscape(tag) + "," + csvEscape(id) + "," + csvEscape(name) + ","
                                    + csvEscape(classes) + "," + csvEscape(text) + "," + csvEscape(xpath) + ","
                                    + validated);
                        }
                        pw.flush();
                    }

                    System.out.println("Results also saved to xpaths.csv");
                } else {
                    System.out.println("No elements array returned from script.");
                }

            } else {
                System.out.println("Unexpected script result type: " + (raw == null ? "null" : raw.getClass()));
            }

        } finally {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    private static String safe(Object o) {
        return o == null ? "" : o.toString();
    }

    private static String csvEscape(String s) {
        if (s == null)
            return "";
        // Replace a single quote (") with two quotes ("") for CSV quoting
        String escaped = s.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}