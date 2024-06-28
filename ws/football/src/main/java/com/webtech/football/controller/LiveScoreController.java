package com.webtech.football.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtech.football.services.LiveScoreService;

@RestController
@RequestMapping("/api")
public class LiveScoreController {

	private final LiveScoreService liveScoreService;

	public LiveScoreController(LiveScoreService liveScoreService) {
		this.liveScoreService = liveScoreService;
	}

	@GetMapping("/live-scores")
	public Map<String, Object> getLiveScores() {
		return liveScoreService.getLiveScores();
	}

	@GetMapping("/past-scores")
	public Map<String, Object> getPastScores() {
		return liveScoreService.getPastScores();
	}

	@GetMapping("/upcoming-scores")
	public Map<String, Object> getUpcomingScores() {
		return liveScoreService.getUpcomingScores();
	}

}
