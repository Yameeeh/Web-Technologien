package com.webtech.football.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

import com.webtech.football.services.LiveScoreService;

public class LiveScoreController {

	private final LiveScoreService liveScoreService;

	public LiveScoreController(LiveScoreService liveScoreService) {
		this.liveScoreService = liveScoreService;
	}

	@GetMapping("/api/live-scores")
	public Map<String, Object> getLiveScores() {
		return liveScoreService.getLiveScores();
	}

	@GetMapping("/api/past-scores")
	public Map<String, Object> getPastScores() {
		return liveScoreService.getPastScores();
	}

	@GetMapping("/api/upcoming-scores")
	public Map<String, Object> getUpcomingScores() {
		return liveScoreService.getUpcomingScores();
	}

}
