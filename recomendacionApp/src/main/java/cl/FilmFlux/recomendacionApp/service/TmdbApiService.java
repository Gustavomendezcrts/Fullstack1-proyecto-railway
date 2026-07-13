package cl.FilmFlux.recomendacionApp.service;

import cl.FilmFlux.recomendacionApp.DTO.TmdbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TmdbApiService {

    @Autowired
    @Qualifier("tmdbWebClient")
    private WebClient tmdbWebClient;

    public String obtenerUrlPortada(String imdbId) {

        try {

            TmdbDTO response = tmdbWebClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/find/{external_id}")
                            .queryParam("external_source", "imdb_id")
                            .queryParam("language", "es-ES")
                            .build(imdbId))
                    .retrieve()
                    .bodyToMono(TmdbDTO.class)
                    .block();
            if (response != null) {
                if (response.getMovieResults() != null &&
                        !response.getMovieResults().isEmpty() &&
                        response.getMovieResults().get(0).getPoster_path() != null) {
                    return "https://image.tmdb.org/t/p/w500"
                            + response.getMovieResults().get(0).getPoster_path();
                }

                if (response.getTvResults() != null &&
                        !response.getTvResults().isEmpty() &&
                        response.getTvResults().get(0).getPoster_path() != null) {
                    return "https://image.tmdb.org/t/p/w500"
                            + response.getTvResults().get(0).getPoster_path();
                }
            }

        } catch (Exception e) {
            System.err.println("Error en TMDb: " + e.getMessage());
        }
        return null;
    }
}