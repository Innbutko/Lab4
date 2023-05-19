package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.JournalRequest
import ua.kpi.its.lab.rest.dto.JournalResponse
import ua.kpi.its.lab.rest.dto.ScientificArticleRequest
import ua.kpi.its.lab.rest.dto.ScientificArticleResponse
import ua.kpi.its.lab.rest.entity.Journal
import ua.kpi.its.lab.rest.entity.ScientificArticle

interface JournalService {
    fun addJournal(request: JournalRequest): JournalResponse
    fun getAllJournals(): MutableList<Journal>
    fun deleteJournalById(id: Long)
    fun addArticleToJournal(journalId: Long, articleID: Long): Journal?
}

interface ScientificArticleService {
    fun addScientificArticle(request: ScientificArticleRequest): ScientificArticleResponse
    fun getAllScientificArticles(): MutableList<ScientificArticle>
    fun deleteScientificArticleById(id: Long)
    fun getScientificArticleById(id: Long): ScientificArticleResponse

}