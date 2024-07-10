package com.osuprun.pplaquestionsapp.controller

import com.osuprun.pplaquestionsapp.domain.Question
import com.osuprun.pplaquestionsapp.service.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/question")
class QuestionResource @Autowired constructor(
    private val questionService: QuestionService
) {

    @GetMapping("/random")
    fun getRandom(): Question {
        return questionService.findRandom()
    }
}
