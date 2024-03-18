package bmg.hu.ponte_movies.component;

import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.exception.IllegalTmdbRequestException;
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

    private static final String HEADER_KEY = "Accept";
    private static final String HEADER_VALUE = "application/json";

    public List<MovieListItem> getTopRatedMovies() {
        try {
            List<MovieListItem> movies = new ArrayList<>();

            URL url = new URL(apiBaseUrl + "/top_rated?language=en-US&page=1&api_key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(HEADER_KEY, HEADER_VALUE);

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
                movie.setMovieId(movieJson.getLong("id"));
                movie.setOverView(movieJson.getString("overview"));
                movie.setOriginalLanguage(movieJson.getString("original_language"));
                movie.setPosterPath(movieJson.getString("poster_path"));

                movies.add(movie);
            }

            return movies;
        } catch (IOException | JSONException e) {
            throw new IllegalTmdbRequestException(e.getMessage());
        }
    }

    public void getActorsForMovie(MovieListItem movie) {
        try {
            Long movieId = movie.getMovieId();

            URL url = new URL(apiBaseUrl + "/" + movieId + "/credits?language=en-US&api_key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(HEADER_KEY, HEADER_VALUE);

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

        } catch (IOException | JSONException e) {
            throw new IllegalTmdbRequestException(e.getMessage());
        }

    }

    public void getProductionCompaniesForMovie(MovieListItem movie) {
        try {
            Long movieId = movie.getMovieId();

            URL url = new URL(apiBaseUrl + "/" + movieId + "?language=en-US&api_key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(HEADER_KEY, HEADER_VALUE);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = inputReader.readLine()) != null) {
                content.append(inputLine);
            }

            inputReader.close();
            connection.disconnect();

            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray results = jsonResponse.getJSONArray("production_companies");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movieJson = results.getJSONObject(i);
                movie.getProductionCompanies().add(movieJson.getString("name"));
            }

        } catch (IOException | JSONException e) {
            throw new IllegalTmdbRequestException(e.getMessage());
        }

    }

    public void getImagesForMovie(MovieListItem movie) {
        try {
            Long movieId = movie.getMovieId();

            URL url = new URL(apiBaseUrl + "/" + movieId + "/images?api_key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(HEADER_KEY, HEADER_VALUE);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = inputReader.readLine()) != null) {
                content.append(inputLine);
            }

            inputReader.close();
            connection.disconnect();

            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray results = jsonResponse.getJSONArray("backdrops");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movieJson = results.getJSONObject(i);
                movie.getImages().add(movieJson.getString("file_path"));
            }

        } catch (IOException | JSONException e) {
            throw new IllegalTmdbRequestException(e.getMessage());
        }

    }

}



