package com.osuprun.pplaquestionsapp.service

import com.osuprun.pplaquestionsapp.domain.Question
import com.osuprun.pplaquestionsapp.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionService @Autowired constructor(
    private val questionRepository: QuestionRepository,
) {

    fun getQuestions(): List<Question> {
        return questionRepository.findAll()
    }

    fun findRandom(): Question {
        return getQuestions().random()
    }
}
