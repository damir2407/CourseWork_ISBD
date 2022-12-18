package com.oasis.oasisapp.service;

import com.oasis.oasisapp.exception.GuideDataException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Guide;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GuideService {

    @Autowired
    private GuideRepository guideRepository;

    public void saveGuide(User user, Game game, String guide_text) {
        guideRepository.save(new Guide(user, game, guide_text, new Timestamp(System.currentTimeMillis())));
    }

    public List<Guide> getAllGuides() throws GuideDataException{
     return guideRepository.getAllGuides().orElseThrow(()-> new GuideDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
    }

    public List<Guide> getAllGuidesByGameName(String game_name) throws GuideDataException{
        return guideRepository.getAllGuidesByGameName(game_name).orElseThrow(()-> new GuideDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
    }
}
