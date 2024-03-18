package bmg.hu.ponte_movies.component;

import bmg.hu.ponte_movies.dto.MovieListItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
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

    public List<MovieListItem> getTopRatedMovies() {
        try {
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
                movie.setId(movieJson.getLong("id"));
                movie.setOverView(movieJson.getString("overview"));
                movie.setOriginalLanguage(movieJson.getString("original_language"));

                movies.add(movie);
            }

            return movies;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public MovieListItem getActorsForMovie(MovieListItem movie) {
        try {
            Long movieId = movie.getId();

            URL url = new URL(apiBaseUrl + "/" + movieId + "/credits?language=en-US&api_key=" + apiKey);
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
            JSONArray results = jsonResponse.getJSONArray("cast");
            for (int i = 0; i < 5; i++) {
                JSONObject movieJson = results.getJSONObject(i);
                movie.getCast().add(movieJson.getString("name"));
            }

            return movie;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}



