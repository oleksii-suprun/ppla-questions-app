package com.osuprun.pplaquestionsapp.repository

import com.osuprun.pplaquestionsapp.domain.Question

interface QuestionRepository {

    fun findAll(): List<Question>
}
