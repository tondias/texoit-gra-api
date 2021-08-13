package com.texoit.wellington.gra;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.texoit.wellington.gra.domain.model.Movie;
import com.texoit.wellington.gra.domain.service.MovieService;
import com.texoit.wellington.gra.domain.service.ProducerService;
import com.texoit.wellington.gra.domain.service.StudioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private MovieService movieService;
	private StudioService studioService;
	private ProducerService producerService;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream in = classLoader.getResourceAsStream("movielist.csv");
			Reader reader = new InputStreamReader(in);
			CSVFormat format = CSVFormat.RFC4180.builder().setDelimiter(';')
					.setHeader("year", "title", "studios", "producers", "winner").setIgnoreEmptyLines(true).build();
			Iterable<CSVRecord> records = format.parse(reader);

			for (CSVRecord record : records) {
				if (record.getRecordNumber() == 1) {
					continue;
				}

				String winner = record.get("winner");
				Movie movie = movieService
						.save(new Movie(Integer.valueOf(record.get("year")), record.get("title"), winner));

				String studios = record.get("studios");
				studioService.saveStudios(movie, studios);

				String producers = record.get("producers");
				producerService.saveProducers(movie, producers);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
