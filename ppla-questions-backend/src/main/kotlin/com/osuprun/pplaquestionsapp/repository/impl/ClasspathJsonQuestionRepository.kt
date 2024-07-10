package com.osuprun.pplaquestionsapp.repository.impl

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.osuprun.pplaquestionsapp.domain.Question
import com.osuprun.pplaquestionsapp.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class ClasspathJsonQuestionRepository @Autowired constructor(
    private val objectMapper: ObjectMapper,
    @Value("classpath:ppla_pl.json") private val jsonResource: Resource
) : QuestionRepository {

    @Cacheable("questions")
    override fun findAll(): List<Question> {
        return objectMapper.readValue(jsonResource.inputStream, object : TypeReference<List<Question>>() {})
    }
}
