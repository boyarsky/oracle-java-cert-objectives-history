package com.coderanch.certs;

import java.math.BigDecimal;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.htmlparser.util.ParserException;

/**
 * Parses the JSON for a single exam page. Stored a sample in 
 * src/test/resources including the URL it came from. The destination
 * format is the one in src/main/resources (the old format we were comparing to)
 * 
 * @author jeanne
 */
public class SingleExamPageParser {

	class Container {
		Data data;
	}

	class Data {
		Page page;
	}

	class Page {
		Product product;
	}

	class Product {
		String duration;
		@SerializedName("x_numberOfQuestions")
		String numberOfQuestions;
		@SerializedName("x_passingScore")
		String passingScore;
		Object[] childSKUs;
		String examTopics;
	}

	// ---------------------------------------------------------------------

	private StringBuilder result;

	public SingleExamPageParser() {
		result = new StringBuilder();
	}

	// ---------------------------------------------------------------------

	public String convertToTextFormat(String json) throws ParserException {
		Gson gson = new Gson();
		Container container = gson.fromJson(json, Container.class);

		Product product = container.data.page.product;
		append("Duration", product.duration);
		append("# Questions", removeTags(product.numberOfQuestions));
		append("Passing Score", removeTags(product.passingScore));

        append("US exam cost", "US$ " + removeCents(getExamPriceInUsDollars(product.childSKUs)));

		TopicListParser topicsParser = new TopicListParser(product.examTopics);
		result.append(topicsParser.convertToTextFormat());

		return result.toString();
	}

    private Double getExamPriceInUsDollars(Object[] childSKUs) {
	    return Arrays.stream(childSKUs)
                // cast to right format
                .map(o -> (Map<String, Object> ) o)
                .map(m -> m.get("listPrices"))
                // find element in array with US price
                // cast to right format
                .map(o -> (Map<String, Double> ) o)
                .filter(m -> m.containsKey("US_Dollar"))
                // some legacy data has US_DOllar twice. Once with null and once with the value
                .filter(m -> m.get("US_Dollar") != null)
                .map(m -> m.get("US_Dollar"))
	            .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    private String removeTags(String html) {
		String openTag = "<";
		String closeTag = ">";
		String anyCharactersExceptCloseTag = "[^>]+";
		String regEx = openTag + anyCharactersExceptCloseTag + closeTag;
		String noTags = html.replaceAll(regEx, "");
		return noTags.replaceAll("&nbsp;", "");
	}

	private String removeCents(Object amount) {
		BigDecimal bigDecimal = BigDecimal.valueOf((double) amount);
		return "" + bigDecimal.intValue();
	}

	private void append(String key, Object value) {
		result.append(key + ": ");
		result.append(value);
		result.append('\n');
	}
}
