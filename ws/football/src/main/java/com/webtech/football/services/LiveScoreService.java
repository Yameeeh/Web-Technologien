package com.webtech.football.services;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class LiveScoreService {

	private final RestTemplate restTemplate;
	private final String apiKey = "DEIN_API_SCHLÜSSEL";
	private final String baseUrl = "https://soccer.sportmonks.com/api/v2.0/";

	public LiveScoreService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Map<String, Object> getLiveScores() {
		String url = baseUrl + "livescores?api_token=" + apiKey;
		return restTemplate.getForObject(url, Map.class);
	}

	public Map<String, Object> getPastScores() {
		String url = baseUrl + "fixtures?api_token=" + apiKey + "&status=finished";
		return restTemplate.getForObject(url, Map.class);
	}

	public Map<String, Object> getUpcomingScores() {
		String url = baseUrl + "fixtures?api_token=" + apiKey + "&status=upcoming";
		return restTemplate.getForObject(url, Map.class);
	}

}
