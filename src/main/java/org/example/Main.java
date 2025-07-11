package org.example;

import org.example.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner run(DataSource dataSource) {
        return args -> {
            Movie movie = new Movie("Inception", "Christopher Nolan");

            Movie createdMovie = save(dataSource, movie);

            LOG.warn("Saved movie: {} , {}" , createdMovie.getId() , createdMovie.getTitle());
        };
    }












    public Movie save(DataSource dataSource, Movie movie) throws SQLException {

        String sql = "INSERT INTO movie (title, directed_by) VALUE (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirectedBy());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                movie.setId(generatedKeys.getLong(1));
            }

        }

        return movie;

    }



}
