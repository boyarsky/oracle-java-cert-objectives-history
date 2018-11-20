package com.coderanch.certs;

import org.apache.commons.lang3.*;
import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.nodes.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.*;

/*
 * Converts Oracle's HTML topic list into text. 
 * @author jeanne
 *
 */
public class TopicListParser {

	private String initialText;

	public TopicListParser(String initialText) {
		this.initialText = initialText;
	}

	public String convertToTextFormat() throws ParserException {
		StringBuilder result = new StringBuilder();
		Parser parser = Parser.createParser(initialText, "US-ASCII");
		NodeList list = parser.parse(createFilter());

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			String plainText = tag.toPlainTextString().trim();
			appendText(result, tag, plainText);
		}
		return result.toString();
	}

	private void appendText(StringBuilder result, Node tag, String plainText) {
		if (plainText.contains("retire")) {
			appendRetirementDate(result, tag);
		}
		else if (tag instanceof TextNode && !tag.getText().trim().isEmpty()) {
			result.append("\n");
			result.append(unescape(tag.getText()));
			result.append("\n");
		} else if (tag instanceof Bullet) {
			Node child = tag.getFirstChild();
			if (child instanceof Span) {
			    child = child.getFirstChild();
            }
			result.append("-");
            String unescaped = unescape(child.getText());
            // some random assumptions have CSS in them
            if (! unescaped.isEmpty() && ! unescaped.startsWith("style=\"")) {
                result.append(' ');
                result.append(unescaped);
            }
			result.append("\n");
		}
	}

	/*
	 * Oracle is using a mix of plaintext retirement dates and formatted ones.
	 */
	private void appendRetirementDate(StringBuilder result, Node tag) {
		Node paragraphTag = tag.getParent();
		result.append("\n");
		if (paragraphTag == null) {
			result.append(tag.toPlainTextString().trim());
		} else {
			NodeList children = paragraphTag.getChildren();
			for (int j = 0; j < children.size(); j++) {
				Node child = children.elementAt(j);
				result.append(child.toPlainTextString().trim() + " ");
			}
		}
		result.append("\n");
	}

	private String unescape(String text) {
		// unescaper turns into the unicode space so easier to convert first
		String html = text.replaceAll("&nbsp;", " ");
		// ignore if has random bolding
		html = html.replaceAll("strong", "");
		return StringEscapeUtils.unescapeHtml4(html).trim();
	}

	private NodeFilter createFilter() {
		// retirement date
		NodeFilter retires = new StringFilter("retire");

		// text considered a sibling of strong formatting
		NodeFilter objectives = new HasSiblingFilter(
				new TagNameFilter("strong"));
		// but a child of a list item
		NodeFilter subobjectives = new TagNameFilter("li");
		return new OrFilter(new NodeFilter[] { objectives, subobjectives, retires });
	}

}
