package ua.kpi.its.lab.rest.entity

import jakarta.persistence.*
import java.time.LocalDate

//Сутність, що представляє журнал з відповідними полями
@Entity
@Table(name = "journals")
data class Journal(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    val name: String, //Назва журналу
    val topic: String, //тема журналу
    val language: String, //мова журналу
    val foundingDate: String, //дата заснування журналу
    val issn: String, //ідентифікаційний код журналу
    val price: Double, //ціна журналу
    val isPeriodic: Boolean, //періодичний чи ні журнал
    @OneToMany(mappedBy = "journal", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var articlesList: MutableList<ScientificArticle> = mutableListOf() //список наукових статей у журналі
) : Comparable<Journal> {
    override fun compareTo(other: Journal): Int {
        //Сортування за іменем журналу, а якщо імена різні - то за датою заснування.
        return if (this.name == other.name) {
            this.foundingDate.compareTo(other.foundingDate)
        } else {
            this.name.compareTo(other.name)
        }
    }
}

//Сутність, що представляє наукову статтю з відповідними полями
@Entity
@Table(name = "scientific_articles")
data class ScientificArticle(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 1,
    val title:String, //заголовок статті
    val author:String, //автор статті
    val dateWritten : String, //дата написання статті
    val wordCount:Int, //кількість слів у статті
    val referenceCount:Int, //кількість посилань у статті
    val originalLanguage:Boolean, //оригінальна мова статті
    @ManyToOne()
    @JoinColumn(name = "journal_id")
    var journal: Journal? = null
) : Comparable<ScientificArticle> {
    override fun compareTo(other: ScientificArticle): Int {
        //Сортування за датою написання. Якщо збігається - за заголовком
        return if (this.dateWritten == other.dateWritten) {
            this.title.compareTo(other.title)
        } else {
            this.dateWritten.compareTo(other.dateWritten)
        }
    }

}

