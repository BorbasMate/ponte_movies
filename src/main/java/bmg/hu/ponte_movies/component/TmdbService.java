package bmg.hu.ponte_movies.component;

import bmg.hu.ponte_movies.dto.MovieListItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class TmdbService {

    @Value("${tmdb.api_baseUrl}")
    private String apiBaseUrl;

    @Value("${tmdb.api_key}")
    private String apiKey;

    public List<MovieListItem> getTopRatedMovies() throws Exception {
        List<MovieListItem> movies = new ArrayList<>();

        URL url = new URL(apiBaseUrl + "/top_rated?language=en-US&page=1&api_key=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = inputReader.readLine()) != null) {
            content.append(inputLine);
        }

        inputReader.close();
        connection.disconnect();

        JSONObject jsonResponse = new JSONObject(content.toString());
        JSONArray results = jsonResponse.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);
            MovieListItem movie = new MovieListItem();
            movie.setTitle(movieJson.getString("title"));
            movie.setReleaseDate(movieJson.getString("release_date"));
            movies.add(movie);
        }

        return movies;
    }
}



