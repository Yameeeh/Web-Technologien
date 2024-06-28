package com.webtech.football.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LiveScoreService {
// Sollte für API genutzt werden

	private final RestTemplate restTemplate;
	private final String apiKey = "";
	private final String baseUrl = "https://soccer.sportmonks.com/api/v2.0/";

	public LiveScoreService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// Live Scores
	public Map<String, Object> getLiveScores() {
		String url = baseUrl + "livescores?api_token=" + apiKey;
		return restTemplate.getForObject(url, Map.class);
	}

	// Vergangene Scores
	public Map<String, Object> getPastScores() {
		String url = baseUrl + "fixtures?api_token=" + apiKey + "&status=finished";
		return restTemplate.getForObject(url, Map.class);
	}

	// Zukünftige Matches
	public Map<String, Object> getUpcomingScores() {
		String url = baseUrl + "fixtures?api_token=" + apiKey + "&status=upcoming";
		return restTemplate.getForObject(url, Map.class);
	}

}
