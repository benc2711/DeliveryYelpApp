import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	//"OpenAI. (2023). Java Servlet Coding and Implementation Details. ChatGPT Session. General Reference"

    private final String YELP_API_URL = "https://api.yelp.com/v3/businesses/";
    private final String YELP_API_KEY = "orVv2bwrWxeq9gB7dxCv2p2XtkssopP_YA0klHGlcnluPL2aWliqA8xv9hIRbvvsuPA-y99qzPyAAbr02ueJWI8ZX3FzsoQFbh3ed4wfpQ6s0xcOwUazJjC4iNlnZXYx";
    		 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Did Get");
        if ("search".equals(action)) {
            String restaurantName = request.getParameter("restaurantName");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            String searchOption = request.getParameter("searchOption");
           
            System.out.println(restaurantName + latitude + longitude + searchOption );

            String yelpResultsJson = getYelpSearchResults(restaurantName, latitude, longitude, searchOption);
            sendResponse(response, yelpResultsJson);
        } else if ("details".equals(action)) {
        	System.out.println("Fetched Details");
            String businessId = request.getParameter("businessId");
            String businessDetailsJson = getYelpBusinessDetails(businessId);
            sendResponse(response, businessDetailsJson);
        }
    }

    private void sendResponse(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String getYelpSearchResults(String name, String lat, String lon, String option) {
        try {
            Map<String, String> optionMap = new HashMap<>();
            optionMap.put("bestMatch", "best_match");
            optionMap.put("rating", "rating");
            optionMap.put("reviewCount", "review_count");
            optionMap.put("distance", "distance");

            StringBuilder endpoint = new StringBuilder(YELP_API_URL + "search?");

            if (name != null && !name.isEmpty()) {
                endpoint.append("term=").append(URLEncoder.encode(name, "UTF-8"));
            }

            if (lat == null || lat.isEmpty()) {
                lat = "34"; 
            }
            if (lon == null || lon.isEmpty()) {
                lon = "-118"; 
            }
            endpoint.append("&latitude=").append(URLEncoder.encode(lat, "UTF-8"));
            endpoint.append("&longitude=").append(URLEncoder.encode(lon, "UTF-8"));

            
            if (option != null && !option.isEmpty()) {
                String apiOption = optionMap.getOrDefault(option, option).toLowerCase().replace(" ", "_");
                endpoint.append("&sort_by=").append(apiOption);
            }

            endpoint.append("&limit=10"); 
            System.out.println(endpoint.toString());
            return sendYelpRequest(endpoint.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Error while fetching Yelp search results.\"}";
        }
    }






    private String getYelpBusinessDetails(String businessId) {
        try {
            String endpoint = YELP_API_URL + URLEncoder.encode(businessId, "UTF-8");
            return sendYelpRequest(endpoint);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String sendYelpRequest(String endpoint) throws IOException {
        URL url = new URL(endpoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + YELP_API_KEY);
        connection.connect();

        Reader reader = new InputStreamReader(connection.getInputStream());
        JsonElement jsonResponse = JsonParser.parseReader(reader);

        String jsonResponseString = jsonResponse.getAsJsonObject().toString();

        System.out.println("Yelp API Response: ");

        return jsonResponseString;
    }

}
